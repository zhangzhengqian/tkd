package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* SsoUserAccountLog
* table:sso_user_account_log
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-08-30 15:02:41
*/
public class SsoUserAccountLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String userId;

    /**
     * 收支类型：0 充值，1 消费，2 提现，3 退款，4 其他
     */
    private Integer type;

    /**
     * 金额*100
     */
    private Integer amount;

    /**
     * 账户操作之后余额*100
     */
    private Integer balance;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 交易类型：1支付宝，2微信
     */
    private Integer tradetype;

    /**
     * 交易流水号
     */
    private String tradeno;

    /**
     * 支付宝或微信流水号
     */
    private String number;

    /**
     * 提现账户信息：支付宝账号或微信openId
     */
    private String attrinfo;

    /**
     * 交易描述
     */
    private String description;

    /**
     * 账户操作日志完成状态0：未完成1：已完成
     */
    private Integer status;

    private Date ct;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return 收支类型：0 充值，1 消费，2 提现，3 退款，4 其他
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type 
	 *            收支类型：0 充值，1 消费，2 提现，3 退款，4 其他
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return 金额*100
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * @param amount 
	 *            金额*100
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * @return 账户操作之后余额*100
     */
    public Integer getBalance() {
        return balance;
    }

    /**
     * @param balance 
	 *            账户操作之后余额*100
     */
    public void setBalance(Integer balance) {
        this.balance = balance;
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
     * @return 交易类型：1支付宝，2微信
     */
    public Integer getTradetype() {
        return tradetype;
    }

    /**
     * @param tradetype 
	 *            交易类型：1支付宝，2微信
     */
    public void setTradetype(Integer tradetype) {
        this.tradetype = tradetype;
    }

    /**
     * @return 交易流水号
     */
    public String getTradeno() {
        return tradeno;
    }

    /**
     * @param tradeno 
	 *            交易流水号
     */
    public void setTradeno(String tradeno) {
        this.tradeno = tradeno;
    }

    /**
     * @return 支付宝或微信流水号
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number 
	 *            支付宝或微信流水号
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return 提现账户信息：支付宝账号或微信openId
     */
    public String getAttrinfo() {
        return attrinfo;
    }

    /**
     * @param attrinfo 
	 *            提现账户信息：支付宝账号或微信openId
     */
    public void setAttrinfo(String attrinfo) {
        this.attrinfo = attrinfo;
    }

    /**
     * @return 交易描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description 
	 *            交易描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return 账户操作日志完成状态0：未完成1：已完成
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            账户操作日志完成状态0：未完成1：已完成
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCt() {
        return ct;
    }

    public void setCt(Date ct) {
        this.ct = ct;
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
        SsoUserAccountLog other = (SsoUserAccountLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getTradetype() == null ? other.getTradetype() == null : this.getTradetype().equals(other.getTradetype()))
            && (this.getTradeno() == null ? other.getTradeno() == null : this.getTradeno().equals(other.getTradeno()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getAttrinfo() == null ? other.getAttrinfo() == null : this.getAttrinfo().equals(other.getAttrinfo()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCt() == null ? other.getCt() == null : this.getCt().equals(other.getCt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getTradetype() == null) ? 0 : getTradetype().hashCode());
        result = prime * result + ((getTradeno() == null) ? 0 : getTradeno().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getAttrinfo() == null) ? 0 : getAttrinfo().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCt() == null) ? 0 : getCt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}