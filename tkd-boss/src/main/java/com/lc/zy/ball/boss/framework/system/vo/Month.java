package com.lc.zy.ball.boss.framework.system.vo;

import java.util.LinkedHashMap;
import java.util.List;

public class Month {
	
	String month = null;
	List<Day> dayList = null;

	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public List<Day> getDayList() {
		return dayList;
	}
	public void setDayList(List<Day> dayList) {
		this.dayList = dayList;
	}
	
	public static void main(String[] args) {
		LinkedHashMap<String, Object> m = new LinkedHashMap<String, Object>();
		m.put("1","11");
		m.put("2","22");
		m.put("3","33");
		m.put("4","44");
		m.put("6","44");
		m.put("5","44");
		
		System.out.println(m);
	}
	
}
