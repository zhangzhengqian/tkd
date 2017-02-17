package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* BillTerm
* table:oa_bill_term
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-09-18 14:06:21
*/
public class BillTerm implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 开始时间戳+结束时间戳
     */
    private String id;

    /**
     * 结账场馆数
     */
    private Integer statiumNum;

    /**
     * 结账订单总数
     */
    private Integer orderNum;

    /**
     * 结账总金额
     */
    private Integer totalAmount;

    private Date ct;

    private String cb;

    private Integer type;

    /**
     * @return 开始时间戳+结束时间戳
     */
    public String getId() {
        return id;
    }

    /**
     * @param id 
	 *            开始时间戳+结束时间戳
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 结账场馆数
     */
    public Integer getStatiumNum() {
        return statiumNum;
    }

    /**
     * @param statiumNum 
	 *            结账场馆数
     */
    public void setStatiumNum(Integer statiumNum) {
        this.statiumNum = statiumNum;
    }

    /**
     * @return 结账订单总数
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * @param orderNum 
	 *            结账订单总数
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * @return 结账总金额
     */
    public Integer getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount 
	 *            结账总金额
     */
    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
        BillTerm other = (BillTerm) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStatiumNum() == null ? other.getStatiumNum() == null : this.getStatiumNum().equals(other.getStatiumNum()))
            && (this.getOrderNum() == null ? other.getOrderNum() == null : this.getOrderNum().equals(other.getOrderNum()))
            && (this.getTotalAmount() == null ? other.getTotalAmount() == null : this.getTotalAmount().equals(other.getTotalAmount()))
            && (this.getCt() == null ? other.getCt() == null : this.getCt().equals(other.getCt()))
            && (this.getCb() == null ? other.getCb() == null : this.getCb().equals(other.getCb()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStatiumNum() == null) ? 0 : getStatiumNum().hashCode());
        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());
        result = prime * result + ((getTotalAmount() == null) ? 0 : getTotalAmount().hashCode());
        result = prime * result + ((getCt() == null) ? 0 : getCt().hashCode());
        result = prime * result + ((getCb() == null) ? 0 : getCb().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}