package com.lc.zy.ball.boss.framework.orders.vo;

import com.lc.zy.ball.domain.oa.po.Order;

public class OrderBillVo extends Order {

	private static final long serialVersionUID = 1806543094689368452L;

	private String statiumName;
	private String adress;
	private String phone;
	private String area;
	private String orderDate;
	private int costPrice;
	private String sportType;
	private String bankAccountName;
	private String bankaccountno;
	private String userName;

	public String getStatiumName() {
		return statiumName;
	}

	public void setStatiumName(String statiumName) {
		this.statiumName = statiumName;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(int costPrice) {
		this.costPrice = costPrice;
	}

	public String getSportType() {
		return sportType;
	}

	public void setSportType(String sportType) {
		this.sportType = sportType;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankaccountno() {
		return bankaccountno;
	}

	public void setBankaccountno(String bankaccountno) {
		this.bankaccountno = bankaccountno;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
