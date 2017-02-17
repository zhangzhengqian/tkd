package com.lc.zy.common.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 一个键值对格式的 entity
 * @author cc
 *
 */
public class KeyValueEntity {
	
	Map<String,Object> dict = new HashMap<String,Object>();
	
	public KeyValueEntity(){
		
	}
	
	public KeyValueEntity(String k,Object v){
		append(k,v);
	}
	
	/**
	 * 添加一个键值对到对象中
	 * @param k
	 * @param v
	 * @return
	 */
	public KeyValueEntity append(String k,Object v){
		dict.put(k, v);
		return this;
	}
	
	/**
	 * 转换成一个 Map 
	 * @return
	 */
	public Map<String,Object> asMap(){
		return this.dict;
	}
	
}
