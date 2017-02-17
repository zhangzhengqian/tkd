package com.lc.zy.ball.crm.framework.system.cardManage.vo;

/**
 * Created by sl on 2016/12/14.
 */
public class RechargeCardVo {
    // 账户id
    private String accountId;
    // 用户id
    private String userId;
    // 卡片id
    private String cardId;
    // 充值金额
    private int amount;
    // 开始日期
    private String startDate;
    // 结束日期
    private String endDate;
    // 套餐面值
    private int cardAmount;
    // 赠送金额
    private int cardGift;
    // 卡片类型
    private int cardType;
    // 充值类型（随机or套餐）
    private int payType;
    // 账户余额
    private int balance;
    // 订单id
    private String orderId;
    // 原期限
    private String cardTime;
    // 原开始日期
    private String oldStartDate;

    public String getOldStartDate() {
        return oldStartDate;
    }

    public void setOldStartDate(String oldStartDate) {
        this.oldStartDate = oldStartDate;
    }

    public String getCardTime() {
        return cardTime;
    }

    public void setCardTime(String cardTime) {
        this.cardTime = cardTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(int cardAmount) {
        this.cardAmount = cardAmount;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getCardGift() {
        return cardGift;
    }

    public void setCardGift(int cardGift) {
        this.cardGift = cardGift;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }
}
