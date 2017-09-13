package com.github.common.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Utils
{
	public static String encrypt(String val)
	{
		MessageDigest md;
		try
		{
			md = MessageDigest.getInstance("MD5");
			md.update(val.getBytes());
			val = new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return val;
	}
	
	public static void main(String[] args)
	{
		System.out.println(encrypt("123456"));
		System.out.println(encrypt("123456"));
	}
	
}
