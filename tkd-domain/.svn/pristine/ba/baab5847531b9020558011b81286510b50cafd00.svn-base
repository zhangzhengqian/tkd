package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* StatiumActivityMember
* table:oa_statium_activity_member
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-07-28 12:01:12
*/
public class StatiumActivityMember implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 活动id
     */
    private String activityId;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 头像
     */
    private String photo;

    private Date ct;

    private Date et;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 状态
     */
    private Integer state;

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
     * @return 活动id
     */
    public String getActivityId() {
        return activityId;
    }

    /**
     * @param activityId 
	 *            活动id
     */
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    /**
     * @return 用户名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            用户名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 头像
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @param photo 
	 *            头像
     */
    public void setPhoto(String photo) {
        this.photo = photo;
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

    /**
     * @return 状态
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state 
	 *            状态
     */
    public void setState(Integer state) {
        this.state = state;
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
        StatiumActivityMember other = (StatiumActivityMember) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getActivityId() == null ? other.getActivityId() == null : this.getActivityId().equals(other.getActivityId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPhoto() == null ? other.getPhoto() == null : this.getPhoto().equals(other.getPhoto()))
            && (this.getCt() == null ? other.getCt() == null : this.getCt().equals(other.getCt()))
            && (this.getEt() == null ? other.getEt() == null : this.getEt().equals(other.getEt()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getActivityId() == null) ? 0 : getActivityId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPhoto() == null) ? 0 : getPhoto().hashCode());
        result = prime * result + ((getCt() == null) ? 0 : getCt().hashCode());
        result = prime * result + ((getEt() == null) ? 0 : getEt().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}