package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* CrmCard
* table:crm_card
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-11-17 14:33:14
*/
public class CrmCard implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 卡片类型：1、储值卡 2、期限卡
     */
    private Integer cardType;

    /**
     * 卡片金额
     */
    private Integer cardAmount;

    /**
     * 卡片赠送
     */
    private Integer cardGift;

    private Date et;

    private String eb;

    private Date ct;

    private String cb;

    /**
     * 卡片名称
     */
    private String cardName;

    /**
     * 卡片状态 0、未激活 1、激活
     */
    private Integer status;

    /**
     * 道馆id
     */
    private Integer statiumId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 卡片类型：1、储值卡 2、期限卡
     */
    public Integer getCardType() {
        return cardType;
    }

    /**
     * @param cardType 
	 *            卡片类型：1、储值卡 2、期限卡
     */
    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    /**
     * @return 卡片金额
     */
    public Integer getCardAmount() {
        return cardAmount;
    }

    /**
     * @param cardAmount 
	 *            卡片金额
     */
    public void setCardAmount(Integer cardAmount) {
        this.cardAmount = cardAmount;
    }

    /**
     * @return 卡片赠送
     */
    public Integer getCardGift() {
        return cardGift;
    }

    /**
     * @param cardGift 
	 *            卡片赠送
     */
    public void setCardGift(Integer cardGift) {
        this.cardGift = cardGift;
    }

    public Date getEt() {
        return et;
    }

    public void setEt(Date et) {
        this.et = et;
    }

    public String getEb() {
        return eb;
    }

    public void setEb(String eb) {
        this.eb = eb;
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
     * @return 卡片名称
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * @param cardName 
	 *            卡片名称
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * @return 卡片状态 0、未激活 1、激活
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            卡片状态 0、未激活 1、激活
     */
    public void setStatus(Integer status) {
        this.status = status;
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
        CrmCard other = (CrmCard) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCardType() == null ? other.getCardType() == null : this.getCardType().equals(other.getCardType()))
            && (this.getCardAmount() == null ? other.getCardAmount() == null : this.getCardAmount().equals(other.getCardAmount()))
            && (this.getCardGift() == null ? other.getCardGift() == null : this.getCardGift().equals(other.getCardGift()))
            && (this.getEt() == null ? other.getEt() == null : this.getEt().equals(other.getEt()))
            && (this.getEb() == null ? other.getEb() == null : this.getEb().equals(other.getEb()))
            && (this.getCt() == null ? other.getCt() == null : this.getCt().equals(other.getCt()))
            && (this.getCb() == null ? other.getCb() == null : this.getCb().equals(other.getCb()))
            && (this.getCardName() == null ? other.getCardName() == null : this.getCardName().equals(other.getCardName()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getStatiumId() == null ? other.getStatiumId() == null : this.getStatiumId().equals(other.getStatiumId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCardType() == null) ? 0 : getCardType().hashCode());
        result = prime * result + ((getCardAmount() == null) ? 0 : getCardAmount().hashCode());
        result = prime * result + ((getCardGift() == null) ? 0 : getCardGift().hashCode());
        result = prime * result + ((getEt() == null) ? 0 : getEt().hashCode());
        result = prime * result + ((getEb() == null) ? 0 : getEb().hashCode());
        result = prime * result + ((getCt() == null) ? 0 : getCt().hashCode());
        result = prime * result + ((getCb() == null) ? 0 : getCb().hashCode());
        result = prime * result + ((getCardName() == null) ? 0 : getCardName().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getStatiumId() == null) ? 0 : getStatiumId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}