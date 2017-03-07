package com.lc.zy.ball.boss.framework.ssouser.vo;

import java.util.ArrayList;
import java.util.List;

import com.lc.zy.ball.domain.oa.po.SsoUserBonusAccountLog;
import com.lc.zy.ball.domain.oa.po.UserAccountLog;

public class AccountVo {
	
	private String qiuyouNo;
	
	private String name;
	
	private String phone;
	
	private String state;
	
	private Double total;
	
	private Double freez;
	
	private String userId;

	private Double bonus;

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	private List<UserAccountLog> logs = new ArrayList<UserAccountLog>();

	private List<SsoUserBonusAccountLog> bonusLogs = new ArrayList<SsoUserBonusAccountLog>();

	public String getQiuyouNo() {
		return qiuyouNo;
	}

	public void setQiuyouNo(String qiuyouNo) {
		this.qiuyouNo = qiuyouNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getFreez() {
		return freez;
	}

	public void setFreez(Double freez) {
		this.freez = freez;
	}

	public List<UserAccountLog> getLogs() {
		return logs;
	}

	public void setLogs(List<UserAccountLog> logs) {
		this.logs = logs;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
