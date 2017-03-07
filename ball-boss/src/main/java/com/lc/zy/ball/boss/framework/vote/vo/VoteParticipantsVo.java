package com.lc.zy.ball.boss.framework.vote.vo;

import com.lc.zy.ball.domain.oa.po.VoteParticipants;

public class VoteParticipantsVo extends VoteParticipants{
	public String theme;
	public String candidate;
	public String createTime;
	public String phone;
	public String registTime;
	public String getRegistTime() {
		return registTime;
	}
	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getCandidate() {
		return candidate;
	}
	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}
	
}
