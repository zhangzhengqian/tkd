package com.lc.zy.ball.boss.framework.prize.vo;

import java.util.ArrayList;
import java.util.List;

public class UserPrizeByStatiumVo {
	private String statiumId;
	private String statiumName;
	private String statiumAddress;
	private List<Integer> prizeNum=new ArrayList<>();
	
	public String getStatiumAddress() {
		return statiumAddress;
	}
	public void setStatiumAddress(String statiumAddress) {
		this.statiumAddress = statiumAddress;
	}
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
	public List<Integer> getPrizeNum() {
		return prizeNum;
	}
	public void setPrizeNum(List<Integer> prizeNum) {
		this.prizeNum = prizeNum;
	}
}
