package com.lc.zy.ball.app.service.account.bean;

public class UserAccountLogVo {
	
	private String id;
	
	//收支类型：0 充值，1 消费，2 提现，3 退款
	private Integer type;
	
	//金额
	private String amount;
	
	//账户余额
	private String balance;
	
	//描述
	private String describe;
	
	//日期2015-10-09
	private String date;
	
	//提现状态
	private String state;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
