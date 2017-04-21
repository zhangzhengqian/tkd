package com.lc.zy.ball.crm.common;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

/**
 * 消息工具类
 * 
 * @author wang.haibin
 * 
 */
public class FlashAttributeUtil {

	public static String build(boolean success, String message) {
		Map<String, Object> map = new HashMap<>();
		map.put("result", success);
		map.put("content", message);
		return new Gson().toJson(map);
	}

}
