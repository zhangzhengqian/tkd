package com.lc.zy.ball.boss.framework.event.vo;

import com.lc.zy.ball.domain.oa.po.Games;

@SuppressWarnings("serial")
public class GamesVo extends Games {
	private String lngLat;

	private String avgMoney;
	
	private String userName;
	
	private String corpsName;
	

	public String getLngLat() {
		return lngLat;
	}

	public void setLngLat(String lngLat) {
		this.lngLat = lngLat;
	}

	public String getAvgMoney() {
		return avgMoney;
	}

	public void setAvgMoney(String avgMoney) {
		this.avgMoney = avgMoney;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCorpsName() {
		return corpsName;
	}

	public void setCorpsName(String corpsName) {
		this.corpsName = corpsName;
	}

}
