package com.lc.zy.common.push;

import java.util.HashMap;
import java.util.Map;

import javapns.Push;
import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.PushNotificationPayload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 给 IOS 推送消息
 * @author liangc
 */
public class PushApn {
	
	private static Logger logger = LoggerFactory.getLogger(PushApn.class);
	
	static String def_cert = System.getProperty("user.home")+"/.ssh/qiuyouzone.p12";

	
	public static void push(PushApnBean obj) throws Exception {
		push(obj.getDeviceToken(),obj.getMsg(),obj.getPwd(),obj.isDebug(),obj.getParams(),obj.getBadge(),obj.getCert());
	}
	
	/**
	 * 推送 APNS 
	 * @param deviceToken 设备token
	 * @param msg 消息内容
	 * @param pwd 密码
	 * @param debug 是否是测试证书
	 * @param params 属性字典
	 * @param badge 未读 新消息数，暂时默认成 1 吧，后台还没计算
	 * @param cert 证书 p12 
	 * @throws Exception
	 */
	public static void push(String deviceToken,String msg,String pwd,boolean debug,Map<String,String> params,int badge,String cert) throws Exception {
		try {
			if(cert==null||"".equals(cert)){
				cert = def_cert;
				logger.debug("use_def_cert");
			}else{
//				cert = System.getProperty("user.home")+"\\ssh\\"+cert;
				cert = System.getProperty("user.home")+"/.ssh/"+cert;
			}
			PushNotificationPayload payLoad = new PushNotificationPayload();
			payLoad.addSound("default"); // 铃音 默认
			payLoad.addBadge(badge);
			payLoad.addAlert(msg);
			if(params!=null){
				for(String k : params.keySet()){
					String v = params.get(k);
					payLoad.addCustomDictionary(k, v);
				}
			}
			Device device = new BasicDevice();
			device.setToken(deviceToken);
			//2014-7-10 : 确定不了哪个是生产哪个是测试，而且总改证书名和密码，所以 生产/测试 都试一下，必然影响效率
			Push.payload(payLoad, cert, pwd , debug , device);
			logger.info("push_success__deviceToken="+deviceToken+" ; cert="+cert+" ; pwd="+pwd+" ; debug="+debug+" ; msg="+msg+" ; params="+params); 
		} catch (Exception e) {
			logger.info("push_error__deviceToken="+deviceToken+" ; cert="+cert+" ; pwd="+pwd+" ; debug="+debug+" ; msg="+msg+" ; params="+params); 
			logger.error("push_error",e);
			throw e;
		}
	}

	public static void main(String[] args) throws Exception {
	    long s = System.currentTimeMillis();
        String token = "bfdf36b47c1bc1b7bfca2e3c3682208883046047c0c76bb1b65ea5458ee91076";
        Map<String,String> map = new HashMap<String,String>();
        map.put("msgtype", "normalchat");
        push(token,"收到请回复。。。。。。。小星星","123",false,map,1,"ios_Debug_APNs.p12");
        System.out.println("OK...");
        long e = System.currentTimeMillis();
        System.out.println(e-s);
	}
	
}
