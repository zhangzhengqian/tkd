package com.lc.zy.ball.boss.framework.orders.vo;

import com.lc.zy.ball.domain.oa.po.PayLog;
import com.lc.zy.ball.domain.oa.po.SsoUser;

@SuppressWarnings("serial")
public class PayLogVo extends PayLog {
	
	private SsoUser ssoUser;//会员
	private String userNickName;//名称 excel
	private String statusStr;//类型 excel
	private String feeStr;//应收 excel
	private String finalFeeStr;//实收 excel
	private String payTypeStr; //支付类型 excel
	private String tradeNoStr;

	public String getTradeNoStr() {
		return tradeNoStr;
	}

	public void setTradeNoStr(String tradeNoStr) {
		this.tradeNoStr = tradeNoStr;
	}

	public String getPayTypeStr() {
		return payTypeStr;
	}

	public void setPayTypeStr(String payTypeStr) {
		this.payTypeStr = payTypeStr;
	}

	public String getFeeStr() {
		return feeStr;
	}

	public void setFeeStr(String feeStr) {
		this.feeStr = feeStr;
	}

	public String getFinalFeeStr() {
		return finalFeeStr;
	}

	public void setFinalFeeStr(String finalFeeStr) {
		this.finalFeeStr = finalFeeStr;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public SsoUser getSsoUser() {
		return ssoUser;
	}

	public void setSsoUser(SsoUser ssoUser) {
		this.ssoUser = ssoUser;
	}

	public PayLogVo() {
	}
}
