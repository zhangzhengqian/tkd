package com.lc.zy.common.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
/**
 * 行政区域编码于名称的字典
 * @author liangc
 */
public class Zonemap {
	
	/**
	 * 根据编码可以获取名称
	 */
	public static Map<String,String> dict = new HashMap<String,String>();
	
	static{
		Gson gson = new Gson();
		InputStream is = Zonemap.class.getClassLoader().getResourceAsStream("zone.json");
		Reader r = new InputStreamReader(is);
		dict = gson.fromJson(r, new TypeToken<Map<String,String>>(){}.getType());  
	}
	
	public static void main(String[] args) {
		System.out.println(dict);
	}
	
	/**
	 * 将一个编码，转换成字典, {"province":"省","city":"市","area":"区"}
	 * @param args
	 * @return
	 */
	public static Map<String,String> split(String args){
		Map<String,String> m = new HashMap<String,String>();
		String province = args.substring(0, 2)+"0000";
		province = dict.get(province);
		m.put("province", province);
		if(!args.endsWith("0000")){
			String city = args.substring(0, 4)+"00";
			city = dict.get(city);
			m.put("city", city);
		}
		if(!args.endsWith("00")){			
			String area = dict.get(args);
			m.put("area", area);
		}
		return m;
	}

}
