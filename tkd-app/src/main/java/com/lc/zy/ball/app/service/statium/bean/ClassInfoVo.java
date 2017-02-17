package com.lc.zy.ball.app.service.statium.bean;

import com.lc.zy.ball.domain.oa.po.StatiumClassInfo;

public class ClassInfoVo extends StatiumClassInfo {

	private static final long serialVersionUID = 1L;

	// 日期
	private String date;
	
	// 星期
	private String week;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}
	
}
