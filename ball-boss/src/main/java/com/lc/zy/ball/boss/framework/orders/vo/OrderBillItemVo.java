package com.lc.zy.ball.boss.framework.orders.vo;

public class OrderBillItemVo {
	private int orderCount;
	private int totalFinalFee;
	private int totalFee;
	private String perDate;
	private int subsidy;
	private int costFee;
	public int getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	public int getTotalFinalFee() {
		return totalFinalFee;
	}
	public void setTotalFinalFee(int totalFinalFee) {
		this.totalFinalFee = totalFinalFee;
	}
	public int getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}
	public String getPerDate() {
		return perDate;
	}
	public void setPerDate(String perDate) {
		this.perDate = perDate;
	}
	public int getSubsidy() {
		return subsidy;
	}
	public void setSubsidy(int subsidy) {
		this.subsidy = subsidy;
	}
	public int getCostFee() {
		return costFee;
	}
	public void setCostFee(int costFee) {
		this.costFee = costFee;
	}
	
}
