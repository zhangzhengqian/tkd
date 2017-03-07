package com.lc.zy.ball.boss.framework.event.vo;

import com.lc.zy.ball.domain.oa.po.EnjoyGameSite;

public class EnjoyGameSiteVo extends EnjoyGameSite {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String statiumName;

	private Integer cityStatus;

	public Integer getCityStatus() {
		return cityStatus;
	}

	public void setCityStatus(Integer cityStatus) {
		this.cityStatus = cityStatus;
	}

	public String getStatiumName() {
		return statiumName;
	}

	public void setStatiumName(String statiumName) {
		this.statiumName = statiumName;
	}

}
