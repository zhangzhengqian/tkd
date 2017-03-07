package com.lc.zy.ball.boss.framework.statium.vo;

import com.lc.zy.ball.domain.oa.po.StatiumAccredit;
import com.lc.zy.common.util.MyGson;

public class StatiumAccreditVo extends StatiumAccredit {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8567492181370062085L;
	private String statiumName;

	public String getStatiumName() {
		return statiumName;
	}

	public void setStatiumName(String statiumName) {
		this.statiumName = statiumName;
	}
	@Override
	public String toString() {
		return MyGson.getInstance().toJson(this);
	}
}
