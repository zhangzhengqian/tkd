package com.lc.zy.ball.app.service.account.bean;

import java.util.List;

public class UserAccountLogDetailVo {
	
	//交易类型，0、办卡 1、激活 2、注销 3、转卡 4、充值,5退款
	private Integer type;
	
	//类型描述
	private String typeDesc;
	
	//金额
	private String amount;
	
	//账户余额
	private String balance;
	
	//订单id，充值不需要
	private String orderId;
	
	//充值支付方式：支付宝/微信
	private String tradeType;
	
	//交易时间
	private String time;
	
	//提现详情，状态更改记录
	private List<WithdrawCashLogDetailVo> list;

	// 道馆名称
	private String statiumName;

	// 课程名称
	private String className;

	// 活动名称
	private String activityName;

	// 教练
	private String coachName;

	// 订单类型
	private Integer orderType;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getStatiumName() {
		return statiumName;
	}

	public void setStatiumName(String statiumName) {
		this.statiumName = statiumName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getCoachName() {
		return coachName;
	}

	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<WithdrawCashLogDetailVo> getList() {
		return list;
	}

	public void setList(List<WithdrawCashLogDetailVo> list) {
		this.list = list;
	}
	
}
