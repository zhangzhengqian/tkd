package com.lc.zy.ball.boss.framework.goods.vo;

import com.lc.zy.ball.domain.oa.po.CrmMallGoods;

public class GoodVo extends CrmMallGoods{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1556548101220477334L;
	private Double fee;
	private String feeView;

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public String getFeeView() {
		return feeView;
	}

	public void setFeeView(String feeView) {
		this.feeView = feeView;
	}
	
}
