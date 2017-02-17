package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* SsoUserAccount
* table:sso_user_account
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-08-30 15:02:41
*/
public class SsoUserAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 是否被冻结
     */
    private Integer isfreeze;

    /**
     * 冻结金额
     */
    private Integer frozen;

    /**
     * 支付密码
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
     * @return 是否被冻结
     */
    public Integer getIsfreeze() {
        return isfreeze;
    }

    /**
     * @param isfreeze 
	 *            是否被冻结
     */
    public void setIsfreeze(Integer isfreeze) {
        this.isfreeze = isfreeze;
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
     * @return 支付密码
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * @param passwd 
	 *            支付密码
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
        SsoUserAccount other = (SsoUserAccount) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getIsfreeze() == null ? other.getIsfreeze() == null : this.getIsfreeze().equals(other.getIsfreeze()))
            && (this.getFrozen() == null ? other.getFrozen() == null : this.getFrozen().equals(other.getFrozen()))
            && (this.getPasswd() == null ? other.getPasswd() == null : this.getPasswd().equals(other.getPasswd()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getCt() == null ? other.getCt() == null : this.getCt().equals(other.getCt()))
            && (this.getEt() == null ? other.getEt() == null : this.getEt().equals(other.getEt()))
            && (this.getCb() == null ? other.getCb() == null : this.getCb().equals(other.getCb()))
            && (this.getEb() == null ? other.getEb() == null : this.getEb().equals(other.getEb()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getIsfreeze() == null) ? 0 : getIsfreeze().hashCode());
        result = prime * result + ((getFrozen() == null) ? 0 : getFrozen().hashCode());
        result = prime * result + ((getPasswd() == null) ? 0 : getPasswd().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getCt() == null) ? 0 : getCt().hashCode());
        result = prime * result + ((getEt() == null) ? 0 : getEt().hashCode());
        result = prime * result + ((getCb() == null) ? 0 : getCb().hashCode());
        result = prime * result + ((getEb() == null) ? 0 : getEb().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}