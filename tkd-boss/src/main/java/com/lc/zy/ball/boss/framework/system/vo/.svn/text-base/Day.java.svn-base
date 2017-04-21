package com.lc.zy.ball.boss.framework.system.vo;

import java.util.Date;

import com.lc.zy.common.util.DateUtils;

public class Day {
	
	public Day() {
		super();
	}

	public Day(Date d) {
		super();
		String s = DateUtils.formatDate(d);
		String[] arr = s.split("-");
		this.year =  arr[0];
		this.month =  arr[1];
		this.day = arr[2];
		this.week = DateUtils.getWeek(d);
		this.date = year+"-"+month+"-"+day;
	}

	public Day(String year, String month, String day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		String d = year+"-"+month+"-"+day;
		Date dd = DateUtils.getDate(d);
		this.week = DateUtils.getWeek(dd);
		this.date = year+"-"+month+"-"+day;
	}
	
	String date = null;
	String year = null;
	String month = null;
	String day = null;
	/**
	 * 周几
	 */
	String week = null;
	
	boolean holiday = false;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public boolean isHoliday() {
		return holiday;
	}

	public void setHoliday(boolean holiday) {
		this.holiday = holiday;
	}

	@Override
	public String toString() {
		return "Day [year=" + year + ", month=" + month + ", day=" + day + ", week=" + week + ", holiday=" + holiday + "]";
	}
	
}
