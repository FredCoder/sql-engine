package com.k.engine;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * <p>Title:EngineConext</p>
 * <p>Description: SQLEngine配置缓存  </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: XXXX</p>
 * <p>Date:2015年1月3日</p>
 * @author CHEN_JIAN
 * @version 1.0
 */
public class EngineContext {

	Logger logger = Logger.getLogger(EngineContext.class);

	
	
	private static Map<String,JSONObject> appContextMap;
	
	static {
		
		appContextMap= new HashMap<String,JSONObject>();
	}
	
	/**
	 * 获取缓存数据值
	 * @param key忽略大小写
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject get(String key) throws Exception{
		  if(!appContextMap.containsKey(key.toLowerCase())){
			  throw new Exception("通过key["+key+"]没有找到对应的SQL配置");
		  }
		return appContextMap.get(key.toLowerCase());
	} 
	
	/**
	 * 缓存键值对，key忽略大小写
	 * @param key
	 * @param value
	 */
	public static void put(String key,JSONObject value){
		
		appContextMap.put(key.toLowerCase(), value);
	}
	
	/**
	 * 是否存在key
	 * @param key
	 * @return
	 */
	public static boolean containsKey(String key){
		
		return appContextMap.containsKey(key.toLowerCase());
	}
	
	public static void write(){
		System.out.println(JSON.toJSONString(appContextMap));
	}
}
