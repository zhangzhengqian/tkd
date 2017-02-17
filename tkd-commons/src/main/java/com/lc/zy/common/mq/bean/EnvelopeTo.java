package com.lc.zy.common.mq.bean;

public class EnvelopeTo {
	public EnvelopeTo() {
	}
		
	public EnvelopeTo(String to) {
		super();
		this.to = to;
	}

	public EnvelopeTo(String type, String to) {
		super();
		this.type = type;
		this.to = to;
	}
	//站内信 LOC / 短信 SMS / 邮件 EMAIL
	private String type = "SMS";
	private String to = null;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	
	
}
