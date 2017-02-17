package com.lc.zy.ball.crm.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;

import com.google.gson.Gson;

/**
 * 行政区域编码于名称的字典
 * @author liangc
 */
@SuppressWarnings("unchecked")
public class Zonemap {
	
	/**
	 * 根据编码可以获取名称
	 */
	public static Map<String,String> dict = new HashMap<String,String>();
	
	static{
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("zone.json");
		StringBuilder sb = new StringBuilder();
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String tmp = "";
			
			while((tmp = reader.readLine()) != null){
				sb.append(tmp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String j = sb.toString();
		Gson g = new Gson();
		dict = (Map<String,String>)g.fromJson(j,HashMap.class);
		//JSONObject jsonObject = JSONObject.fromObject(sb.toString());
		//dict = ((Map<String, String>) JSONObject.toBean(jsonObject, Map.class));
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
	/**
	 * 地区字符串, 河北省-石家庄市-新乐市
	 * @param args
	 * @return
	 */
	public static String toString(String code){
		if(StringUtils.isBlank(code)){
			return "";
		}
		String c2 = code.substring(0, 4) + "00";
		String c1 = code.substring(0, 2) + "0000";
		
		StringBuffer sb = new StringBuffer(dict.get(c1));
		if(!code.substring(0, 4).endsWith("00")){
			sb.append("-").append(dict.get(c2));
			if(!code.endsWith("00")){
				sb.append("-").append(dict.get(code));
			}
		}
		return sb.toString();
	}	

}
