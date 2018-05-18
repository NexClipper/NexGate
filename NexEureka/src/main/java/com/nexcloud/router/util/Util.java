package com.nexcloud.router.util;

import java.util.UUID;

import com.google.gson.Gson;


public class Util {
	/**
	 * Bean to Json String Convert
	 * 
	 * @param src
	 * @return
	 */
	public static String beanToJson(Object src)
	{
		return new Gson().toJson(src); 
	}
	
	
	public static synchronized String getUUID()
	{
		return UUID.randomUUID().toString();
	}
	
	/**
	 * Json String to Bean  Convert
	 * 
	 * @param src
	 * @return
	 */
	public static <T> T JsonTobean( String json, Class<T> classOfT )
	{
		if( classOfT == null )
			return null;
		
		return new Gson().fromJson(json, classOfT );
	}
}
