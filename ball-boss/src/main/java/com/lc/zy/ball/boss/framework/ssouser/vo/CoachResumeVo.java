package com.lc.zy.ball.boss.framework.ssouser.vo;

import com.lc.zy.ball.domain.oa.po.CoachResume;

public class CoachResumeVo extends CoachResume {
	private static final long serialVersionUID = -8392490398947157821L;
	
	private String startTimeStr;
	
	private String endTimeStr;

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}
	
}
