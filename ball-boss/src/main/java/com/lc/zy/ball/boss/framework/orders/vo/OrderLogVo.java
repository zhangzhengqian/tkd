package com.lc.zy.ball.boss.framework.orders.vo;

import com.lc.zy.ball.domain.oa.po.OrderLog;

@SuppressWarnings("serial")
public class OrderLogVo extends OrderLog {
	private String operatorName;
	private String operatorType;
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getOperatorType() {
		return operatorType;
	}
	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}
	public OrderLogVo() {
	}

}
