package com.lc.zy.ball.boss.framework.conpon.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.lc.zy.ball.domain.oa.po.Coupon;

public class CouponVo extends Coupon{
	
    private static final long serialVersionUID = 1L;

    private String couponId;

    private String couponType;

    private Integer couponValue;

    private Integer giveValue;

    private Integer orderValue;

    private Integer couponCount;
    
    private String validTime;
    
    private Integer hasMoney;
    
    private String couponInfoType;
    
    private Integer sumValue;
    
    private Integer getCount;
    
    private String loginName;
    
    private Integer sendMoney;
    
    // 实际发放总金额
    private Integer realAmount;
    
    // 优惠券id
    private String couponVoId;
    
    // 优惠券描述
    private String couponVoDescribe;
    
    // 优惠券名称
    private String couponVoName;
    
    // 优惠券发放方式
    private Integer voAmountType; 
    
    // 优惠券总金额
    private BigDecimal voTotalAmount;
    
    // 优惠券类型 0：首单福利 1：支付成功分享返券
    private Integer voType;
    
    // 优惠券上传时间
    private Date voCreateTime;
    
    // 优惠券上传人员
    private String voCreatePerson;
    
    // 优惠券状态
    private Integer voStatus;
    
    // 开始时间
    private String voStartTime;
		
    // 结束时间
    private String voEndTime;
    
    // 发放总金额
    private String tAmount;
    
    // 产生最大金额
    private String maxAmount;
    
    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId == null ? null : couponId.trim();
    }
  
    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public Integer getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(Integer couponValue) {
        this.couponValue = couponValue;
    }

    public Integer getGiveValue() {
        return giveValue;
    }

    public void setGiveValue(Integer giveValue) {
        this.giveValue = giveValue;
    }

    public Integer getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(Integer orderValue) {
        this.orderValue = orderValue;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

	public String getCouponInfoType() {
		return couponInfoType;
	}

	public void setCouponInfoType(String couponInfoType) {
		this.couponInfoType = couponInfoType;
	}

	public Integer getHasMoney() {
		return hasMoney;
	}

	public void setHasMoney(Integer hasMoney) {
		this.hasMoney = hasMoney;
	}

	public Integer getSumValue() {
		return sumValue;
	}

	public void setSumValue(Integer sumValue) {
		this.sumValue = sumValue;
	}

	public Integer getGetCount() {
		return getCount;
	}

	public void setGetCount(Integer getCount) {
		this.getCount = getCount;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Integer getSendMoney() {
		return sendMoney;
	}

	public void setSendMoney(Integer sendMoney) {
		this.sendMoney = sendMoney;
	}

	public Integer getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(Integer realAmount) {
		this.realAmount = realAmount;
	}

	public String getCouponVoId() {
		return couponVoId;
	}

	public void setCouponVoId(String couponVoId) {
		this.couponVoId = couponVoId;
	}

	public String getCouponVoDescribe() {
		return couponVoDescribe;
	}

	public void setCouponVoDescribe(String couponVoDescribe) {
		this.couponVoDescribe = couponVoDescribe;
	}

	public String getCouponVoName() {
		return couponVoName;
	}

	public void setCouponVoName(String couponVoName) {
		this.couponVoName = couponVoName;
	}

	public Integer getVoAmountType() {
		return voAmountType;
	}

	public void setVoAmountType(Integer voAmountType) {
		this.voAmountType = voAmountType;
	}

	public BigDecimal getVoTotalAmount() {
		return voTotalAmount;
	}

	public void setVoTotalAmount(BigDecimal voTotalAmount) {
		this.voTotalAmount = voTotalAmount;
	}

	public Integer getVoType() {
		return voType;
	}

	public void setVoType(Integer voType) {
		this.voType = voType;
	}

	public Date getVoCreateTime() {
		return voCreateTime;
	}

	public void setVoCreateTime(Date voCreateTime) {
		this.voCreateTime = voCreateTime;
	}

	public String getVoCreatePerson() {
		return voCreatePerson;
	}

	public void setVoCreatePerson(String voCreatePerson) {
		this.voCreatePerson = voCreatePerson;
	}

	public Integer getVoStatus() {
		return voStatus;
	}

	public void setVoStatus(Integer voStatus) {
		this.voStatus = voStatus;
	}

	public String getVoStartTime() {
		return voStartTime;
	}

	public void setVoStartTime(String voStartTime) {
		this.voStartTime = voStartTime;
	}

	public String getVoEndTime() {
		return voEndTime;
	}

	public void setVoEndTime(String voEndTime) {
		this.voEndTime = voEndTime;
	}

	public String gettAmount() {
		return tAmount;
	}

	public void settAmount(String tAmount) {
		this.tAmount = tAmount;
	}

	public String getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(String maxAmount) {
		this.maxAmount = maxAmount;
	}

}
