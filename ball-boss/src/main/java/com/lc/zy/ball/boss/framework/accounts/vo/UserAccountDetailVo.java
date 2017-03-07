package com.lc.zy.ball.boss.framework.accounts.vo;

public class UserAccountDetailVo {
	
	private String userId;
	
	private String qiuyouNo;
	
	private String nickName;
	
	private String phone;
	
	private String status;
	
	private double total;
	
	private double frozen;

	private double bonus;

	private double frozenBonus;

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public double getFrozenBonus() {
		return frozenBonus;
	}

	public void setFrozenBonus(double frozenBonus) {
		this.frozenBonus = frozenBonus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getQiuyouNo() {
		return qiuyouNo;
	}

	public void setQiuyouNo(String qiuyouNo) {
		this.qiuyouNo = qiuyouNo;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getFrozen() {
		return frozen;
	}

	public void setFrozen(double frozen) {
		this.frozen = frozen;
	}
	
}
