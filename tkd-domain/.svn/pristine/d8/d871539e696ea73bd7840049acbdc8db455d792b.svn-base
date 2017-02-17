package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* CrmUserCardAccount
* table:crm_user_card_account
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-12-05 14:53:27
*/
public class CrmUserCardAccount implements Serializable {
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
     * 冻结金额
     */
    private Integer frozen;

    /**
     * 用户状态：0、冻结 1、激活 2、注销
     */
    private Integer status;

    /**
     * 支付密码(储值卡、期限卡消费验证)
     */
    private String passwd;

    /**
     * 可用金额*100,用于消费提现
     */
    private Integer balance;

    private Date ct;

    private Date et;

    private String cb;

    private String eb;

    /**
     * 期限卡id
     */
    private String cardId;

    /**
     * 期限卡开始日期
     */
    private Date startDate;

    /**
     * 期限卡到期类型
     */
    private Date endDate;

    /**
     * 卡片类型，1：储值卡(个人账户) 2:期限卡 默认储值卡
     */
    private Integer cardType;

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
     * @return 冻结金额
     */
    public Integer getFrozen() {
        return frozen;
    }

    /**
     * @param frozen 
	 *            冻结金额
     */
    public void setFrozen(Integer frozen) {
        this.frozen = frozen;
    }

    /**
     * @return 用户状态：0、冻结 1、激活 2、注销
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            用户状态：0、冻结 1、激活 2、注销
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return 支付密码(储值卡、期限卡消费验证)
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * @param passwd 
	 *            支付密码(储值卡、期限卡消费验证)
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    /**
     * @return 可用金额*100,用于消费提现
     */
    public Integer getBalance() {
        return balance;
    }

    /**
     * @param balance 
	 *            可用金额*100,用于消费提现
     */
    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Date getCt() {
        return ct;
    }

    public void setCt(Date ct) {
        this.ct = ct;
    }

    public Date getEt() {
        return et;
    }

    public void setEt(Date et) {
        this.et = et;
    }

    public String getCb() {
        return cb;
    }

    public void setCb(String cb) {
        this.cb = cb;
    }

    public String getEb() {
        return eb;
    }

    public void setEb(String eb) {
        this.eb = eb;
    }

    /**
     * @return 期限卡id
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * @param cardId 
	 *            期限卡id
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
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
     * @return 期限卡到期类型
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate 
	 *            期限卡到期类型
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return 卡片类型，1：储值卡(个人账户) 2:期限卡 默认储值卡
     */
    public Integer getCardType() {
        return cardType;
    }

    /**
     * @param cardType 
	 *            卡片类型，1：储值卡(个人账户) 2:期限卡 默认储值卡
     */
    public void setCardType(Integer cardType) {
        this.cardType = cardType;
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
        CrmUserCardAccount other = (CrmUserCardAccount) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getStatiumId() == null ? other.getStatiumId() == null : this.getStatiumId().equals(other.getStatiumId()))
            && (this.getFrozen() == null ? other.getFrozen() == null : this.getFrozen().equals(other.getFrozen()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getPasswd() == null ? other.getPasswd() == null : this.getPasswd().equals(other.getPasswd()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getCt() == null ? other.getCt() == null : this.getCt().equals(other.getCt()))
            && (this.getEt() == null ? other.getEt() == null : this.getEt().equals(other.getEt()))
            && (this.getCb() == null ? other.getCb() == null : this.getCb().equals(other.getCb()))
            && (this.getEb() == null ? other.getEb() == null : this.getEb().equals(other.getEb()))
            && (this.getCardId() == null ? other.getCardId() == null : this.getCardId().equals(other.getCardId()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()))
            && (this.getCardType() == null ? other.getCardType() == null : this.getCardType().equals(other.getCardType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getStatiumId() == null) ? 0 : getStatiumId().hashCode());
        result = prime * result + ((getFrozen() == null) ? 0 : getFrozen().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getPasswd() == null) ? 0 : getPasswd().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getCt() == null) ? 0 : getCt().hashCode());
        result = prime * result + ((getEt() == null) ? 0 : getEt().hashCode());
        result = prime * result + ((getCb() == null) ? 0 : getCb().hashCode());
        result = prime * result + ((getEb() == null) ? 0 : getEb().hashCode());
        result = prime * result + ((getCardId() == null) ? 0 : getCardId().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        result = prime * result + ((getCardType() == null) ? 0 : getCardType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}