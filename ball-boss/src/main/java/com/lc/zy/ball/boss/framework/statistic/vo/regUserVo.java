package com.lc.zy.ball.boss.framework.statistic.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class regUserVo implements Serializable {
	private String date;

	private String province;
	private String city;

	private int regNum;

	private int actNum;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getRegNum() {
		return regNum;
	}

	public void setRegNum(int regNum) {
		this.regNum = regNum;
	}

	public int getActNum() {
		return actNum;
	}

	public void setActNum(int actNum) {
		this.actNum = actNum;
	}

}
