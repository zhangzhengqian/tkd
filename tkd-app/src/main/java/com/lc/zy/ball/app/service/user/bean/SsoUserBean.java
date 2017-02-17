package com.lc.zy.ball.app.service.user.bean;

import com.lc.zy.ball.domain.oa.po.SsoUser;

/**
 * 用户信息
 * 
 * @author liangc
 */
public class SsoUserBean extends SsoUser {

	private static final long serialVersionUID = 1L;

	private String token = null;

	private String coachId;

	private String realPrice;

	// 是否是新用户0老用户1新用户
	private Integer isNewUser;

	// 是否已经设置支付密码0未设置1已设置
	private Integer isSetPayPwd;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCoachId() {
		return coachId;
	}

	public void setCoachId(String coachId) {
		this.coachId = coachId;
	}

	public String getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(String realPrice) {
		this.realPrice = realPrice;
	}

	public Integer getIsSetPayPwd() {
		return isSetPayPwd;
	}

	public void setIsSetPayPwd(Integer isSetPayPwd) {
		this.isSetPayPwd = isSetPayPwd;
	}

	public Integer getIsNewUser() {
		return isNewUser;
	}

	public void setIsNewUser(Integer isNewUser) {
		this.isNewUser = isNewUser;
	}

}
