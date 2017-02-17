package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* StatiumComment
* table:oa_statium_comment
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-09-05 14:36:28
*/
public class StatiumComment implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 道馆id
     */
    private Integer statiumId;

    /**
     * 评星
     */
    private Integer grade;

    /**
     * 订单id
     */
    private String orderId;

    private Date ct;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 用户id
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid 
	 *            用户id
     */
    public void setUid(String uid) {
        this.uid = uid;
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
     * @return 评星
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * @param grade 
	 *            评星
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * @return 订单id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId 
	 *            订单id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
        StatiumComment other = (StatiumComment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getStatiumId() == null ? other.getStatiumId() == null : this.getStatiumId().equals(other.getStatiumId()))
            && (this.getGrade() == null ? other.getGrade() == null : this.getGrade().equals(other.getGrade()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getCt() == null ? other.getCt() == null : this.getCt().equals(other.getCt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getStatiumId() == null) ? 0 : getStatiumId().hashCode());
        result = prime * result + ((getGrade() == null) ? 0 : getGrade().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getCt() == null) ? 0 : getCt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}