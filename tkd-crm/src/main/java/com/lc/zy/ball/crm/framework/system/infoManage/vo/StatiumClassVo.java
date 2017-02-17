package com.lc.zy.ball.crm.framework.system.infoManage.vo;

import com.lc.zy.ball.domain.oa.po.StatiumClass;

public class StatiumClassVo extends StatiumClass {

	private static final long serialVersionUID = 1L;
	/**
	 * 原价Float类型
	 */
	private Float flPrice;

	/**
	 * 折扣
	 */
	private Integer flDiscount;

	/**
	 * 折扣价
	 */
	private Float flDiscountPrice;

	/**
	 * 限时价
	 */
	private Float flLimitPrice;

	public Float getFlPrice() {
		return flPrice;
	}

	public void setFlPrice(Float flPrice) {
		this.flPrice = flPrice;
	}

	public Integer getFlDiscount() {
		return flDiscount;
	}

	public void setFlDiscount(Integer flDiscount) {
		this.flDiscount = flDiscount;
	}

	public Float getFlDiscountPrice() {
		return flDiscountPrice;
	}

	public void setFlDiscountPrice(Float flDiscountPrice) {
		this.flDiscountPrice = flDiscountPrice;
	}

	public Float getFlLimitPrice() {
		return flLimitPrice;
	}

	public void setFlLimitPrice(Float flLimitPrice) {
		this.flLimitPrice = flLimitPrice;
	}

}
