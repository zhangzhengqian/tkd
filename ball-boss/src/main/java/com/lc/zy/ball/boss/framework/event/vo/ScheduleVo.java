package com.lc.zy.ball.boss.framework.event.vo;


import com.lc.zy.ball.domain.oa.po.GamesSchedule;

@SuppressWarnings("serial")
public class ScheduleVo extends GamesSchedule {
	
	private String logoA;
	
	private String logoB;
	
	private String nameA;
	
	private String nameB;
	
	
    
	public String getLogoA() {
		return logoA;
	}

	public void setLogoA(String logoA) {
		this.logoA = logoA;
	}

	public String getLogoB() {
		return logoB;
	}

	public void setLogoB(String logoB) {
		this.logoB = logoB;
	}

	public String getNameA() {
		return nameA;
	}

	public void setNameA(String nameA) {
		this.nameA = nameA;
	}

	public String getNameB() {
		return nameB;
	}

	public void setNameB(String nameB) {
		this.nameB = nameB;
	}

	private String  state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
