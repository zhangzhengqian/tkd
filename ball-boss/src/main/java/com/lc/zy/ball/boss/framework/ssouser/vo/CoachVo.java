package com.lc.zy.ball.boss.framework.ssouser.vo;

import java.util.List;

import com.lc.zy.ball.domain.oa.po.Coacher;

@SuppressWarnings("serial")
public class CoachVo extends Coacher {
	private String photo;
	private String sprotTypeZWStr;// 中文串
	private String registSourceStr;
	private String signUserName;// 签约人名字
	private String nickName;
	private String sign;// 签名
	private String areaStr;// 地区
	private Integer price;// 价格
	private String statiumIds;
	private String resumeJsons;
	private String statiumViews;
	private String oldPhone;
	private List<String> personStyles;
	private String userStateStr;
	
	private String payee;
	private String bankName;
	private String cardNo;	//银行卡号
	private String bankCity;//银行所在地区
	private String zhcStatiums;
	
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getAreaStr() {
		return areaStr;
	}

	public void setAreaStr(String areaStr) {
		this.areaStr = areaStr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSignUserName() {
		return signUserName;
	}

	public void setSignUserName(String signUserName) {
		this.signUserName = signUserName;
	}

	public String getRegistSourceStr() {
		return registSourceStr;
	}

	public void setRegistSourceStr(String registSourceStr) {
		this.registSourceStr = registSourceStr;
	}

	public String getSprotTypeZWStr() {
		return sprotTypeZWStr;
	}

	public void setSprotTypeZWStr(String sprotTypeZWStr) {
		this.sprotTypeZWStr = sprotTypeZWStr;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getOldPhone() {
		return oldPhone;
	}

	public void setOldPhone(String oldPhone) {
		this.oldPhone = oldPhone;
	}

	public CoachVo() {

	}

	public String getStatiumIds() {
		return statiumIds;
	}

	public void setStatiumIds(String statiumIds) {
		this.statiumIds = statiumIds;
	}

	public String getResumeJsons() {
		return resumeJsons;
	}

	public void setResumeJsons(String resumeJsons) {
		this.resumeJsons = resumeJsons;
	}

	public List<String> getPersonStyles() {
		return personStyles;
	}

	public void setPersonStyles(List<String> personStyles) {
		this.personStyles = personStyles;
	}

	public String getStatiumViews() {
		return statiumViews;
	}

	public void setStatiumViews(String statiumViews) {
		this.statiumViews = statiumViews;
	}
	
	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getBankCity() {
		return bankCity;
	}

	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}

	public String getZhcStatiums() {
		return zhcStatiums;
	}

	public void setZhcStatiums(String zhcStatiums) {
		this.zhcStatiums = zhcStatiums;
	}

	public String getUserStateStr() {
		return userStateStr;
	}

	public void setUserStateStr(String userStateStr) {
		this.userStateStr = userStateStr;
	}
	
}
