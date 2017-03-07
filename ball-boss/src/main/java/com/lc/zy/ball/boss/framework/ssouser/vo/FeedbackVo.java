package com.lc.zy.ball.boss.framework.ssouser.vo;

import com.lc.zy.ball.domain.oa.po.Feedback;

public class FeedbackVo extends Feedback {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String qiuyouno;

	private String nickName;

	private String sex;

	private String type;

	private String phone;

	public String getQiuyouno() {
		return qiuyouno;
	}

	public void setQiuyouno(String qiuyouno) {
		this.qiuyouno = qiuyouno;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
