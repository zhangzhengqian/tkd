package com.lc.zy.ball.app.service.statium.bean;

import com.lc.zy.ball.domain.oa.po.StatiumClassInfo;

public class ClassDetalVo extends StatiumClassInfo {

	private static final long serialVersionUID = 1L;
	
	// 课程名称
	private String className;
	// 课程简介
	private String brief;
	// 场馆地址
	private String addr;
	// 咨询电话
	private String tel;
	// 原价
	private int price;
	// 折扣价
	private int discountPrice;
	// 最小人数
	private int minPeople;
	// 最大人数
	private int maxPeople;
	// 执教类型
	private int type;
	// 课程状态
	private int classStatus;
	// 体验次数
	private int isExperience;

	public int getIsExperience() {
		return isExperience;
	}

	public void setIsExperience(int isExperience) {
		this.isExperience = isExperience;
	}

	public int getClassStatus() {
		return classStatus;
	}

	public void setClassStatus(int classStatus) {
		this.classStatus = classStatus;
	}

	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}
	public int getMinPeople() {
		return minPeople;
	}
	public void setMinPeople(int minPeople) {
		this.minPeople = minPeople;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getMaxPeople() {
		return maxPeople;
	}
	public void setMaxPeople(int maxPeople) {
		this.maxPeople = maxPeople;
	}

}
