package com.lc.zy.common.util.wxap.util;

import org.apache.commons.lang3.StringUtils;

import com.lc.zy.common.util.wxap.RequestHandler;
import com.lc.zy.common.util.wxap.config.WxapConfig;


/**
 * access_token工具类
 * 
 * @author tiger
 *
 */
public class WxTokenUtil {
	/**
	 * 互斥锁
	 */
	private static String lock = "";

	/**
	 * 上次获取的时间
	 */
	private static long lastTime;
	
	/**
	 * 上次获取的access_token
	 */
	private static String accessToken;
	
	/**
	 * 获取access_token
	 * 
	 * @return
	 */
	public static String getToken() {
		if (StringUtils.isBlank(accessToken) || lastTime + 1000*3600 < System.currentTimeMillis()) {
			synchronized(lock) {
				if (StringUtils.isBlank(accessToken) || lastTime + 1000*3600 < System.currentTimeMillis()) {
					RequestHandler reqHandler = new RequestHandler(null, null);
//					reqHandler.init(WxapConfig.appid, WxapConfig.app_secret, WxapConfig.partner_key);
					String token = reqHandler.GetToken();
					if (StringUtils.isNotBlank(token)) {
						accessToken = token;
						lastTime = System.currentTimeMillis();
					}
				}
			}
		}
		return accessToken;
	}
}
