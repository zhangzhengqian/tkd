package com.lc.zy.ball.boss.framework.activationCode.vo;

/**
 * 场馆活动码使用情况
 * @author sl
 *
 */
public class CodeUseVo {
	// 场馆ID
	private String statiumId;

	// 场馆名称
	private String statiumName;
	
	// 使用数量
	private int useNum;

	public String getStatiumId() {
		return statiumId;
	}

	public void setStatiumId(String statiumId) {
		this.statiumId = statiumId;
	}

	public String getStatiumName() {
		return statiumName;
	}

	public void setStatiumName(String statiumName) {
		this.statiumName = statiumName;
	}

	public int getUseNum() {
		return useNum;
	}

	public void setUseNum(int useNum) {
		this.useNum = useNum;
	}
	
}
