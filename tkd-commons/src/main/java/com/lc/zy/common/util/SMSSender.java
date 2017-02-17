package com.lc.zy.common.util;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
/**
 * @author liangc
 */
public class SMSSender {
	
	private static Logger logger = LoggerFactory.getLogger(SMSSender.class);
	
	private static String url = "http://www.lx198.com/sdk/send";
	private static String sign = "球友圈";
	private static String username = "18612013831";
	private static String pwd = "cc14514";
	
	private static Gson gson = MyGson.getInstance();
	
	private static void log(Object obj){
		System.out.println(obj.toString());
		logger.debug(obj.toString());
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(send("18810159163","验证码是：624198。5分钟内有效。如非本人操作，请忽略。"+DateUtils.now()));
		System.out.println(send("18612013831","验证码是：624198。5分钟内有效。如非本人操作，请忽略。"+DateUtils.now()));
//		System.out.println(MD5.getMd5String(pwd));
	}
	
	public static String send(String phoneNumber,String msg) {
		try{
			return send(url,username,pwd,phoneNumber,msg,sign);
		}catch(Exception e){
			e.printStackTrace();
			return e.getMessage();
		}
	}

	public static String send(String url,String userId,String password,String phoneNumber,String msg,String smsSign) throws Exception{
		String[] params = new String[]{
				"accName",userId,
				"accPwd",MD5.getMd5String(password),
				"aimcodes",phoneNumber,
				"content",msg+"【"+smsSign+"】",
				"bizId",BizNumberUtil.createBizId(),
				"dataType","string"
		};
		String res = PostRequest.postText(url, params);
		log("【www.lx198.com】发送短信 input="+gson.toJson(params));
		log("【www.lx198.com】发送短信 output="+res);
		return res;
	}
	
	static class BizNumberUtil {
		public static  int curttNo;
		private final static String dataFormatString="yyMMddHHmmss";
		public  synchronized static final String createBizId(){
			if(curttNo<999) {
				curttNo++;
			}else{
				curttNo=1;
			}
			String curttNoStr=String.valueOf(curttNo);
			while(curttNoStr.length()<3){;
				curttNoStr="0"+curttNoStr;
			}
			return new SimpleDateFormat(dataFormatString).format(new Date())+curttNoStr;
		}
	}
	static class MD5 {
		private final static char[] hexDigits = { '0', '1', '2', '3', '4', '5','6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		private static String bytes2hex(byte[] bytes) {
			StringBuffer sb = new StringBuffer();
			int t;
			for (int i = 0; i < 16; i++) {// 16 == bytes.length;
				t = bytes[i];
				if (t < 0)
					t += 256;
				sb.append(hexDigits[(t >>> 4)]);
				sb.append(hexDigits[(t % 16)]);
			}
			return sb.toString();
		}
		public static String getMd5String(String strSrc) {
			try {
				// 确定计算方法
				MessageDigest md5 = MessageDigest.getInstance("MD5");
				// 加密后的字符串
				return bytes2hex(md5.digest(strSrc.getBytes()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}

