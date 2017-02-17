package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* CrmUserCardLog
* table:crm_user_card_log
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-12-14 17:13:45
*/
public class CrmUserCardLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 会员卡id（储值卡为会员卡账户id、期限卡为期限卡id）
     */
    private String cardId;

    /**
     * 收支类型：0 办卡，1 消费，2 转卡，3 卡种变更，4 、续费,5退款
     */
    private Integer type;

    /**
     * 会员卡金额*100 储值卡使用  消费为此次金额*100
     */
    private Integer amount;

    /**
     * 账户操作之后余额*100 储值卡使用
     */
    private Integer balance;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 交易类型：1、储值卡，2、期限卡
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
     * 交易描述（ex：期限卡转储值卡）
     */
    private String description;

    /**
     * app办卡，卡状态转变。0、未激活 1、激活
     */
    private Integer status;

    private Date ct;

    /**
     * 0、app 1、线下
     */
    private Integer orderType;

    /**
     * 卡片类型 1:储值卡 2:期限卡
     */
    private Integer cardType;

    /**
     * 账户id 储值卡唯一 期限卡多个
     */
    private String accountId;

    /**
     * 期限卡开始日期
     */
    private Date startDate;

    /**
     * 期限卡结束日期
     */
    private Date endDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
     * @return 会员卡id（储值卡为会员卡账户id、期限卡为期限卡id）
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * @param cardId 
	 *            会员卡id（储值卡为会员卡账户id、期限卡为期限卡id）
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    /**
     * @return 收支类型：0 办卡，1 消费，2 转卡，3 卡种变更，4 、续费,5退款
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type 
	 *            收支类型：0 办卡，1 消费，2 转卡，3 卡种变更，4 、续费,5退款
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return 会员卡金额*100 储值卡使用  消费为此次金额*100
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * @param amount 
	 *            会员卡金额*100 储值卡使用  消费为此次金额*100
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * @return 账户操作之后余额*100 储值卡使用
     */
    public Integer getBalance() {
        return balance;
    }

    /**
     * @param balance 
	 *            账户操作之后余额*100 储值卡使用
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
     * @return 交易类型：1、储值卡，2、期限卡
     */
    public Integer getTradetype() {
        return tradetype;
    }

    /**
     * @param tradetype 
	 *            交易类型：1、储值卡，2、期限卡
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
     * @return 交易描述（ex：期限卡转储值卡）
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description 
	 *            交易描述（ex：期限卡转储值卡）
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return app办卡，卡状态转变。0、未激活 1、激活
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            app办卡，卡状态转变。0、未激活 1、激活
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

    /**
     * @return 0、app 1、线下
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * @param orderType 
	 *            0、app 1、线下
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * @return 卡片类型 1:储值卡 2:期限卡
     */
    public Integer getCardType() {
        return cardType;
    }

    /**
     * @param cardType 
	 *            卡片类型 1:储值卡 2:期限卡
     */
    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    /**
     * @return 账户id 储值卡唯一 期限卡多个
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @param accountId 
	 *            账户id 储值卡唯一 期限卡多个
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * @return 期限卡开始日期
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate 
	 *            期限卡开始日期
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return 期限卡结束日期
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate 
	 *            期限卡结束日期
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        CrmUserCardLog other = (CrmUserCardLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCardId() == null ? other.getCardId() == null : this.getCardId().equals(other.getCardId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getTradetype() == null ? other.getTradetype() == null : this.getTradetype().equals(other.getTradetype()))
            && (this.getTradeno() == null ? other.getTradeno() == null : this.getTradeno().equals(other.getTradeno()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCt() == null ? other.getCt() == null : this.getCt().equals(other.getCt()))
            && (this.getOrderType() == null ? other.getOrderType() == null : this.getOrderType().equals(other.getOrderType()))
            && (this.getCardType() == null ? other.getCardType() == null : this.getCardType().equals(other.getCardType()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCardId() == null) ? 0 : getCardId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getTradetype() == null) ? 0 : getTradetype().hashCode());
        result = prime * result + ((getTradeno() == null) ? 0 : getTradeno().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCt() == null) ? 0 : getCt().hashCode());
        result = prime * result + ((getOrderType() == null) ? 0 : getOrderType().hashCode());
        result = prime * result + ((getCardType() == null) ? 0 : getCardType().hashCode());
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}