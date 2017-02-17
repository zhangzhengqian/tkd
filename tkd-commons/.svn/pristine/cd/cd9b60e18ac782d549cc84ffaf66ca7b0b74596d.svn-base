package com.lc.zy.common.mq.bean;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class Evnelope {
	
	/**
	 * 发送人，非必填
	 */
	private String from = null;
	
	/**
	 * 收件人，一组人
	 */
	private List<EnvelopeTo> to = new ArrayList<EnvelopeTo>();
	
	/**
	 * 发送时间，用 10位 时间戳
	 */
	private String ct = "";
	
	/**
	 * 邮件： 发送的主题 
	 */
	private String subject = null;
	/**
	 *  发送的内容
	 */
	private String message = null;
	
	/**
	 * 批量推送： 专题id
	 */
	private String pushId = null;

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public List<EnvelopeTo> getTo() {
		return to;
	}

	public void setTo(List<EnvelopeTo> to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}

	public static void main(String[] args) {
		Gson g = new Gson();
		Evnelope e = new Evnelope();
		String j = g.toJson(e);
		System.out.println(j);
	}
}
