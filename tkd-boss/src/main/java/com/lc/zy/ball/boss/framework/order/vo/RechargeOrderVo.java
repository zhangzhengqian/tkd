package com.lc.zy.ball.boss.framework.order.vo;

public class RechargeOrderVo {
	//卡片名称
	private String cardName;
	//卡片购买日期
	private String cardBuyDate;
	//卡片类型
	private Integer cardType;
	//道馆名称
	private String statiumName;
	//卡片金额
	private Integer finalFee;
	//卡片赠送金额
	private Integer giftFee;
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	
	
	public Integer getFinalFee() {
		return finalFee;
	}
	public void setFinalFee(Integer finalFee) {
		this.finalFee = finalFee;
	}
	public Integer getGiftFee() {
		return giftFee;
	}
	public void setGiftFee(Integer giftFee) {
		this.giftFee = giftFee;
	}
	public String getCardBuyDate() {
		return cardBuyDate;
	}
	public void setCardBuyDate(String cardBuyDate) {
		this.cardBuyDate = cardBuyDate;
	}
	public Integer getCardType() {
		return cardType;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	public String getStatiumName() {
		return statiumName;
	}
	public void setStatiumName(String statiumName) {
		this.statiumName = statiumName;
	}
}
