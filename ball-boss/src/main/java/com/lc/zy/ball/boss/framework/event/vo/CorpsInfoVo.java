package com.lc.zy.ball.boss.framework.event.vo;

import com.lc.zy.ball.domain.oa.po.CorpsInfo;

@SuppressWarnings("serial")
public class CorpsInfoVo extends CorpsInfo {
	private String corpsId; // 在gamesSchedule表中的唯一标识

	public String getCorpsId() {
		return corpsId;
	}

	public void setCorpsId(String corpsId) {
		this.corpsId = corpsId;
	}

}
