package com.lc.zy.ball.crm.framework.system.cardManage.vo;

/**
 * Created by sl on 2016/12/6.
 */
public class TurnCardVo {
    // new姓名
    private String newName;
    // new手机号
    private String newPhone;
    // 成本
    private int cardCost;
    // 转卡原因
    private String turnReason;
    // new开始日期
    private String newStartDate;
    // new到期日期
    private String newEndDate;
    // old期限
    private String oldCardDate;
    // oldUserId
    private String oldUserId;
    // oldBalance
    private Integer oldBalance;
    // oldAccountId
    private String oldAccountId;
    // newUserId
    private String newUserId;
    // 卡片类型
    private Integer cardType;
    // cardId
    private String cardId;
    // orderId
    private String orderId;
    // newAccountId
    private String newAccountId;

    public String getNewAccountId() {
        return newAccountId;
    }

    public void setNewAccountId(String newAccountId) {
        this.newAccountId = newAccountId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getNewUserId() {
        return newUserId;
    }

    public void setNewUserId(String newUserId) {
        this.newUserId = newUserId;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewPhone() {
        return newPhone;
    }

    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }

    public int getCardCost() {
        return cardCost;
    }

    public void setCardCost(int cardCost) {
        this.cardCost = cardCost;
    }

    public String getTurnReason() {
        return turnReason;
    }

    public void setTurnReason(String turnReason) {
        this.turnReason = turnReason;
    }

    public String getNewStartDate() {
        return newStartDate;
    }

    public void setNewStartDate(String newStartDate) {
        this.newStartDate = newStartDate;
    }

    public String getNewEndDate() {
        return newEndDate;
    }

    public void setNewEndDate(String newEndDate) {
        this.newEndDate = newEndDate;
    }

    public String getOldCardDate() {
        return oldCardDate;
    }

    public void setOldCardDate(String oldCardDate) {
        this.oldCardDate = oldCardDate;
    }

    public String getOldUserId() {
        return oldUserId;
    }

    public void setOldUserId(String oldUserId) {
        this.oldUserId = oldUserId;
    }

    public Integer getOldBalance() {
        return oldBalance;
    }

    public void setOldBalance(Integer oldBalance) {
        this.oldBalance = oldBalance;
    }

    public String getOldAccountId() {
        return oldAccountId;
    }

    public void setOldAccountId(String oldAccountId) {
        this.oldAccountId = oldAccountId;
    }
}
