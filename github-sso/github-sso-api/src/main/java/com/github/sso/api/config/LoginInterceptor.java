package com.github.sso.api.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.druid.util.StringUtils;

public class LoginInterceptor extends HandlerInterceptorAdapter implements EnvironmentAware
{
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Value("${oauth.authLocation}")
	private String authLocation;

	@Value("${oauth.tokenLocation}")
	private String tokenLocation;

	@Value("${oauth.meLocation}")
	private String meLocation;

	@Value("${oauth.client_id}")
	private String clientId;

	@Value("${oauth.client_secret}")
	private String clientSecret;

	@Value("${oauth.redirect_uri}")
	private String defaultRedirectUri;

	private String[] excludes;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		String uri = request.getRequestURI();
		if (uri != null && excludes != null && excludes.length > 0)
		{
			for (String exclude : excludes)
			{
				if (uri.indexOf(exclude) > -1)
				{
					return true;
				}
			}
		}
		Object user = request.getSession().getAttribute(SSOConstants.SESSION_OPEN_ID);
		if (user != null)
		{
			return true;
		}

		String redirectUrl = defaultRedirectUri;
		if (!isAjax(request))
		{
			redirectUrl = request.getRequestURL().toString();
		} else
		{
			String refere = request.getHeader("Refere");
			if (!StringUtils.isEmpty(refere))
			{
				redirectUrl = refere;
			}
		}

		String code = request.getParameter("code");
		if (StringUtils.isEmpty(code))
		{
			authorize(request, response, redirectUrl);
		} else
		{
			return checkToken(request, code, redirectUrl);
		}

		return false;
	}

	public void authorize(HttpServletRequest request, HttpServletResponse response, String redirectUri) throws Exception
	{
		OAuthClientRequest req = OAuthClientRequest.authorizationLocation(authLocation).setClientId(clientId)
				.setRedirectURI(redirectUri).setResponseType(ResponseType.CODE.toString().toLowerCase())
				.buildQueryMessage();
		response.sendRedirect(req.getLocationUri());
	}

	public boolean checkToken(HttpServletRequest request, String code, String redirectUri) throws Exception
	{
		OAuthClientRequest req = OAuthClientRequest.tokenLocation(tokenLocation)
				.setGrantType(GrantType.AUTHORIZATION_CODE).setClientId(clientId).setClientSecret(clientSecret)
				.setRedirectURI(redirectUri).setCode(code).buildQueryMessage();
		OAuthClient client = new OAuthClient(new URLConnectionClient());
		OAuthJSONAccessTokenResponse resp = client.accessToken(req);
		String accessToken = resp.getAccessToken();
		logger.info("Got Token : " + accessToken);
		request.getSession().setAttribute(SSOConstants.SESSION_TOKEN_KEY, resp.getOAuthToken());

		OAuthClientRequest clientReq = new OAuthBearerClientRequest(meLocation).setAccessToken(accessToken)
				.buildHeaderMessage();
		OAuthResourceResponse resResp = client.resource(clientReq, OAuth.HttpMethod.POST, OAuthResourceResponse.class);
		String openId = resResp.getBody();
		logger.info("Got Open ID : " + openId);

		request.getSession().setAttribute(SSOConstants.SESSION_OPEN_ID, openId);
		return true;
	}

	@Override
	public void setEnvironment(Environment environment)
	{
		String excludeStr = environment.getProperty("loginFilter.excludes");
		if (excludeStr != null)
		{
			excludes = excludeStr.split(",");
		}

	}

	private boolean isAjax(HttpServletRequest request)
	{
		return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
	}

}
