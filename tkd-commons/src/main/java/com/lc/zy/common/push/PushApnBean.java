package com.lc.zy.common.push;

import java.util.HashMap;
import java.util.Map;

/**
 * 推送对象
 * 
 * @author liangc
 */
public class PushApnBean {
	
	/**
	 * 推送对象
	 * @param deviceToken 
	 * @param msg 消息内容
	 * @param pwd 密码
	 * @param debug 是否调试
	 * @param params 属性字典
	 * @param badge 未读消息数量
	 * @param cert 证书名称
	 */
	public PushApnBean(String deviceToken, String msg, String pwd, boolean debug, Map<String, String> params, int badge, String cert) {
		super();
		this.deviceToken = deviceToken;
		this.msg = msg;
		this.pwd = pwd;
		this.debug = debug;
		this.params = params;
		this.badge = badge;
		this.cert = cert;
	}
	
	String deviceToken = null;
	String msg = null;
	String pwd = null;
	boolean debug = false;
	Map<String,String> params = new HashMap<String,String>();
	int badge = 1;
	String cert = null;
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public boolean isDebug() {
		return debug;
	}
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	public int getBadge() {
		return badge;
	}
	public void setBadge(int badge) {
		this.badge = badge;
	}
	public String getCert() {
		return cert;
	}
	public void setCert(String cert) {
		this.cert = cert;
	}
	
	
}
