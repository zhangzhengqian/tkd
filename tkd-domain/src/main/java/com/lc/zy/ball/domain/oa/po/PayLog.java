package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* PayLog
* table:oa_pay_log
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-07-31 11:07:31
*/
public class PayLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 交易流水号id
     */
    private String tradeNo;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 订单总金额（金额 *100）
     */
    private Integer fee;

    /**
     * 应收金额（金额 *100）
     */
    private Integer finalFee;

    /**
     * 订单状态：NEW，TRADE_SUCCESS
     */
    private String status;

    /**
     * 支付类型：支付宝：0 微信app：1 微信公众平台：2  银联：3  其他：4
     */
    private Integer payType;

    /**
     * 第三方交易流水号
     */
    private String outTradeNo;

    /**
     * 创建时间
     */
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 交易流水号id
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * @param tradeNo 
	 *            交易流水号id
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    /**
     * @return 订单号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId 
	 *            订单号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId 
	 *            用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return 订单总金额（金额 *100）
     */
    public Integer getFee() {
        return fee;
    }

    /**
     * @param fee 
	 *            订单总金额（金额 *100）
     */
    public void setFee(Integer fee) {
        this.fee = fee;
    }

    /**
     * @return 应收金额（金额 *100）
     */
    public Integer getFinalFee() {
        return finalFee;
    }

    /**
     * @param finalFee 
	 *            应收金额（金额 *100）
     */
    public void setFinalFee(Integer finalFee) {
        this.finalFee = finalFee;
    }

    /**
     * @return 订单状态：NEW，TRADE_SUCCESS
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            订单状态：NEW，TRADE_SUCCESS
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 支付类型：支付宝：0 微信app：1 微信公众平台：2  银联：3  其他：4
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * @param payType 
	 *            支付类型：支付宝：0 微信app：1 微信公众平台：2  银联：3  其他：4
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    /**
     * @return 第三方交易流水号
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * @param outTradeNo 
	 *            第三方交易流水号
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    /**
     * @return 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 
	 *            创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PayLog other = (PayLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTradeNo() == null ? other.getTradeNo() == null : this.getTradeNo().equals(other.getTradeNo()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getFee() == null ? other.getFee() == null : this.getFee().equals(other.getFee()))
            && (this.getFinalFee() == null ? other.getFinalFee() == null : this.getFinalFee().equals(other.getFinalFee()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getPayType() == null ? other.getPayType() == null : this.getPayType().equals(other.getPayType()))
            && (this.getOutTradeNo() == null ? other.getOutTradeNo() == null : this.getOutTradeNo().equals(other.getOutTradeNo()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTradeNo() == null) ? 0 : getTradeNo().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getFee() == null) ? 0 : getFee().hashCode());
        result = prime * result + ((getFinalFee() == null) ? 0 : getFinalFee().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getPayType() == null) ? 0 : getPayType().hashCode());
        result = prime * result + ((getOutTradeNo() == null) ? 0 : getOutTradeNo().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}