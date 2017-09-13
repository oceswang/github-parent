package com.github.sso.core.web;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.sso.core.service.AuthService;
import com.github.sso.core.utils.SSOConstants;
import com.github.user.api.client.ClientClient;
import com.github.user.api.client.UserClient;
import com.github.user.api.dto.UserDTO;

@Controller
public class MainController
{
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	@Autowired
	private UserClient userClient;
	@Autowired
	private ClientClient clientClient; 
	@Autowired
	private AuthService authService;
	/**
	 * OAuth2.0
	 * 客户端申请认证的URI，包含以下参数:
	 * response_type：表示授权类型，必选项，此处的值固定为"code"
	 * client_id：表示客户端的ID，必选项
	 * redirect_uri：表示重定向URI，可选项
	 * scope：表示申请的权限范围，可选项
	 * state：表示客户端的当前状态，可以指定任意值，认证服务器会原封不动地返回这个值。
	 * 
	 * 服务器回应客户端的URI包含以下参数：
	 * code：表示授权码，必选项。该码的有效期应该很短，通常设为10分钟，客户端只能使用该码一次，否则会被授权服务器拒绝。该码与客户端ID和重定向URI，是一一对应关系。
	 * state：如果客户端的请求中包含这个参数，认证服务器的回应也必须一模一样包含这个参数。
	 * @param request
	 * @param response
	 * @throws OAuthSystemException 
	 * @throws URISyntaxException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/authorize", method=RequestMethod.GET)
	public Object authorize(HttpServletRequest request, HttpServletResponse response) throws OAuthSystemException, URISyntaxException
	{
		try
		{
			OAuthAuthzRequest req = new OAuthAuthzRequest(request);
			String clientId = req.getClientId();
			String redirectUri = req.getRedirectURI();
			String responseType = req.getResponseType();
			UserDTO user = (UserDTO)request.getSession(true).getAttribute(SSOConstants.SESSION_USER_LOGIN);
			if(user == null)
			{
				if(!checkLogin(request,response))
				{
					return "/page-login";
				}
			}
			user = (UserDTO)request.getSession(true).getAttribute(SSOConstants.SESSION_USER_LOGIN);
			//验证client id
			if(!clientClient.checkClient(clientId))
			{
				OAuthResponse resp = new OAuthResponse.OAuthErrorResponseBuilder(HttpStatus.BAD_REQUEST.value())
										.setError(OAuthError.TokenResponse.INVALID_CLIENT)
										.setErrorDescription("Invalid client id")
										.buildJSONMessage();
				return new ResponseEntity<String>(resp.getBody(),HttpStatus.BAD_REQUEST);
			}
			//设置code并跳转
			OAuthASResponse.OAuthAuthorizationResponseBuilder builder = new OAuthASResponse.OAuthAuthorizationResponseBuilder(request, HttpStatus.FOUND.value());
			OAuthIssuerImpl issuer = new OAuthIssuerImpl(new MD5Generator());
			if(ResponseType.CODE.toString().equals(responseType))
			{
				String code = issuer.authorizationCode();
				builder.setCode(code);
				authService.addCode(code, user.getOpenId());
			}
			OAuthResponse resp = builder.location(redirectUri).buildQueryMessage();
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(new URI(resp.getLocationUri()));
			return new ResponseEntity(headers,HttpStatus.FOUND);
			
			
		} catch (OAuthProblemException e)
		{
			logger.error("认证失败",e);
			String redirectUri = e.getRedirectUri();
			if(OAuthUtils.isEmpty(redirectUri))
			{
				return new ResponseEntity<String>("Redirect url is required",HttpStatus.BAD_REQUEST);
			}
			OAuthResponse resp = new OAuthResponse.OAuthErrorResponseBuilder(HttpStatus.BAD_REQUEST.value())
					.setError(e.getMessage())
					.location(redirectUri)
					.buildQueryMessage();
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(new URI(resp.getLocationUri()));
			return new ResponseEntity(headers,HttpStatus.BAD_REQUEST);
		}
	}
	/**
	 * 客户端向认证服务器申请令牌的HTTP请求，包含以下参数：
	 * grant_type：表示使用的授权模式，必选项，此处的值固定为"authorization_code"。
	 * code：表示上一步获得的授权码，必选项。
	 * redirect_uri：表示重定向URI，必选项，且必须与A步骤中的该参数值保持一致。
	 * client_id：表示客户端ID，必选项。
	 * 
	 * 认证服务器发送的HTTP回复，包含以下参数：
	 * access_token：表示访问令牌，必选项。
	 * token_type：表示令牌类型，该值大小写不敏感，必选项，可以是bearer类型或mac类型。
	 * expires_in：表示过期时间，单位为秒。如果省略该参数，必须其他方式设置过期时间。
	 * refresh_token：表示更新令牌，用来获取下一次的访问令牌，可选项。
	 * scope：表示权限范围，如果与客户端申请的范围一致，此项可省略。
	 * @param request
	 * @param response
	 * @throws OAuthSystemException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/token", method=RequestMethod.POST)
	public Object token(HttpServletRequest request, HttpServletResponse response) throws OAuthSystemException
	{
		try
		{
			OAuthTokenRequest req = new OAuthTokenRequest(request);
			String grantType = req.getGrantType();
			String code = req.getCode();
			String clientId = req.getClientId();
			String clientSecret = req.getClientSecret();
			
			//验证client id and secret
			if(!clientClient.checkClient(clientId) || !clientClient.checkClientAndSecret(clientId,clientSecret))
			{
				OAuthResponse resp = new OAuthResponse
										.OAuthErrorResponseBuilder(HttpStatus.UNAUTHORIZED.value())
										.setError(OAuthError.TokenResponse.UNAUTHORIZED_CLIENT)
										.setErrorDescription("Invalid client id")
										.buildJSONMessage();
				return new ResponseEntity<String>(resp.getBody(),HttpStatus.UNAUTHORIZED);
			}
			//验证code
			if(GrantType.AUTHORIZATION_CODE.toString().equals(grantType))
			{
				if(!authService.checkCode(code))
				{
					OAuthResponse resp = new OAuthResponse
											.OAuthErrorResponseBuilder(HttpStatus.BAD_REQUEST.value())
											.setError(OAuthError.TokenResponse.INVALID_GRANT)
											.setErrorDescription("Invalid code")
											.buildJSONMessage();
					return new ResponseEntity<String>(resp.getBody(),HttpStatus.BAD_REQUEST);
				}
			}
			//生成token
			OAuthIssuerImpl issuer = new OAuthIssuerImpl(new MD5Generator());
			String accessToken = issuer.accessToken();
			String refreshToken = issuer.refreshToken();
			authService.addToken(accessToken, code);
			OAuthResponse resp = new OAuthASResponse.OAuthTokenResponseBuilder(HttpStatus.OK.value())
													.setAccessToken(accessToken)
													.setTokenType(OAuth.DEFAULT_TOKEN_TYPE.toString())
													.setRefreshToken(refreshToken)
													.setExpiresIn("3600")
													.buildJSONMessage();
			return new ResponseEntity(resp.getBody(),HttpStatus.OK);
		}	
		 catch (OAuthProblemException e)
		{
			 logger.error("获取令牌失败",e);
			 OAuthResponse resp = new OAuthResponse
					 				.OAuthErrorResponseBuilder(HttpStatus.BAD_REQUEST.value())
								    .setError(e.getMessage())
								    .buildJSONMessage();
			return new ResponseEntity(resp.getBody(),HttpStatus.BAD_REQUEST);
		}
		 
	}
	/**
	 * 获取用户唯一标识
	 * 请求方式:<br>
	 * <code>
	 * Host: authserver.com <br>
	 * Authorization: Bearer lkj12341eklqrjq
	 * </code>
	 * @param request
	 * @param response
	 * @return
	 * @throws OAuthSystemException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/me", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object me(HttpServletRequest request, HttpServletResponse response) throws OAuthSystemException
	{
		try
		{
			OAuthAccessResourceRequest req = new OAuthAccessResourceRequest(request);
			String accessToken = req.getAccessToken();
			if(!authService.checkToken(accessToken))
			{
				OAuthResponse resp = new OAuthResponse.OAuthErrorResponseBuilder(HttpStatus.UNAUTHORIZED.value())
										.setError(OAuthError.ResourceResponse.INVALID_TOKEN)
										.buildHeaderMessage();
				HttpHeaders headers = new HttpHeaders();
				headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, resp.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
				return new ResponseEntity(headers,HttpStatus.UNAUTHORIZED);
			}
			String openId = authService.getOpenIdByToken(accessToken);
			return new ResponseEntity(openId,HttpStatus.OK);
			
		} catch (OAuthProblemException e)
		{
			logger.error("获取OpenId失败",e);
			String error = e.getError();
			if(StringUtils.isEmpty(error))
			{
				OAuthResponse resp = new OAuthResponse
						.OAuthErrorResponseBuilder(HttpStatus.UNAUTHORIZED.value())
						.setErrorDescription(e.getDescription())
						.buildHeaderMessage();
				HttpHeaders headers = new HttpHeaders();
				headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, resp.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
				return new ResponseEntity(headers,HttpStatus.UNAUTHORIZED);		
			}
			OAuthResponse resp = new OAuthResponse
					.OAuthErrorResponseBuilder(HttpStatus.BAD_REQUEST.value())
					.setError(e.getError())
					.setErrorDescription(e.getDescription())
					.setErrorUri(e.getUri())
					.buildHeaderMessage();
			HttpHeaders headers = new HttpHeaders();
			headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, resp.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
			return new ResponseEntity(headers,HttpStatus.BAD_REQUEST);	
		}
	}
	
	public boolean checkLogin(HttpServletRequest request, HttpServletResponse response)
	{
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		if(StringUtils.isEmpty(login) || StringUtils.isEmpty(password))
		{
			return false;
		}
		try
		{
			UserDTO po = userClient.login(login, password);
			request.getSession(true).setAttribute(SSOConstants.SESSION_USER_LOGIN, po);
			logger.info(String.format("Login Success,User Login=%s", login));
			return true;
		} catch (Exception e)
		{
			logger.error("Login error, login="+login,e);
			return false;
		}
	}
	
	
	
	
}
