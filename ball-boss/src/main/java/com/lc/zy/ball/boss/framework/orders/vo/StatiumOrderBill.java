package com.lc.zy.ball.boss.framework.orders.vo;

import com.lc.zy.ball.domain.oa.po.OrderBill;

public class StatiumOrderBill extends OrderBill {

	
	private static final long serialVersionUID = -5210918126413077945L;
	
	private String startTime;
	private String endTime;
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	

}
