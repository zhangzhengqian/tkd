package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* CrmCardLog
* table:crm_card_log
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-12-02 15:05:11
*/
public class CrmCardLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 道馆id
     */
    private Integer statiumId;

    /**
     * crm卡id
     */
    private String cardId;

    /**
     * 卡片类型 1、储值卡 2、期限卡
     */
    private Integer cardType;

    /**
     * 充值金额*100
     */
    private Integer amount;

    /**
     * 赠送金额*100
     */
    private Integer giftAmount;

    /**
     * 操作类型：0、办卡 1、激活 2、注销 3、转卡 4、充值,5退款
     */
    private Integer type;

    private Date ct;

    private String cb;

    /**
     * 付款渠道：0:app 1:线下
     */
    private Integer buyType;

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
     * @return 道馆id
     */
    public Integer getStatiumId() {
        return statiumId;
    }

    /**
     * @param statiumId 
	 *            道馆id
     */
    public void setStatiumId(Integer statiumId) {
        this.statiumId = statiumId;
    }

    /**
     * @return crm卡id
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * @param cardId 
	 *            crm卡id
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    /**
     * @return 卡片类型 1、储值卡 2、期限卡
     */
    public Integer getCardType() {
        return cardType;
    }

    /**
     * @param cardType 
	 *            卡片类型 1、储值卡 2、期限卡
     */
    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    /**
     * @return 充值金额*100
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * @param amount 
	 *            充值金额*100
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * @return 赠送金额*100
     */
    public Integer getGiftAmount() {
        return giftAmount;
    }

    /**
     * @param giftAmount 
	 *            赠送金额*100
     */
    public void setGiftAmount(Integer giftAmount) {
        this.giftAmount = giftAmount;
    }

    /**
     * @return 操作类型：0、办卡 1、激活 2、注销 3、转卡 4、充值,5退款
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type 
	 *            操作类型：0、办卡 1、激活 2、注销 3、转卡 4、充值,5退款
     */
    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCt() {
        return ct;
    }

    public void setCt(Date ct) {
        this.ct = ct;
    }

    public String getCb() {
        return cb;
    }

    public void setCb(String cb) {
        this.cb = cb;
    }

    /**
     * @return 付款渠道：0:app 1:线下
     */
    public Integer getBuyType() {
        return buyType;
    }

    /**
     * @param buyType 
	 *            付款渠道：0:app 1:线下
     */
    public void setBuyType(Integer buyType) {
        this.buyType = buyType;
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
        CrmCardLog other = (CrmCardLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getStatiumId() == null ? other.getStatiumId() == null : this.getStatiumId().equals(other.getStatiumId()))
            && (this.getCardId() == null ? other.getCardId() == null : this.getCardId().equals(other.getCardId()))
            && (this.getCardType() == null ? other.getCardType() == null : this.getCardType().equals(other.getCardType()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getGiftAmount() == null ? other.getGiftAmount() == null : this.getGiftAmount().equals(other.getGiftAmount()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getCt() == null ? other.getCt() == null : this.getCt().equals(other.getCt()))
            && (this.getCb() == null ? other.getCb() == null : this.getCb().equals(other.getCb()))
            && (this.getBuyType() == null ? other.getBuyType() == null : this.getBuyType().equals(other.getBuyType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getStatiumId() == null) ? 0 : getStatiumId().hashCode());
        result = prime * result + ((getCardId() == null) ? 0 : getCardId().hashCode());
        result = prime * result + ((getCardType() == null) ? 0 : getCardType().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getGiftAmount() == null) ? 0 : getGiftAmount().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCt() == null) ? 0 : getCt().hashCode());
        result = prime * result + ((getCb() == null) ? 0 : getCb().hashCode());
        result = prime * result + ((getBuyType() == null) ? 0 : getBuyType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}