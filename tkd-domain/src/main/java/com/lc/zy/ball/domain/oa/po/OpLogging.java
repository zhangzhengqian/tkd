package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* OpLogging
* table:c_op_logging
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-15 18:07:50
*/
public class OpLogging implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    private String logId;

    /**
     * 动作类型
     */
    private String actionType;

    /**
     * 动作描述
     */
    private String description;

    /**
     * 原始值
     */
    private String oldVal;

    /**
     * 更新值
     */
    private String newVal;

    /**
     * 操作人
     */
    private String userId;

    /**
     * 操作时间
     */
    private Date createTime;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 备注
     */
    private String memo;

    /**
     * @return 日志ID
     */
    public String getLogId() {
        return logId;
    }

    /**
     * @param logId 
	 *            日志ID
     */
    public void setLogId(String logId) {
        this.logId = logId;
    }

    /**
     * @return 动作类型
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * @param actionType 
	 *            动作类型
     */
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    /**
     * @return 动作描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description 
	 *            动作描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return 原始值
     */
    public String getOldVal() {
        return oldVal;
    }

    /**
     * @param oldVal 
	 *            原始值
     */
    public void setOldVal(String oldVal) {
        this.oldVal = oldVal;
    }

    /**
     * @return 更新值
     */
    public String getNewVal() {
        return newVal;
    }

    /**
     * @param newVal 
	 *            更新值
     */
    public void setNewVal(String newVal) {
        this.newVal = newVal;
    }

    /**
     * @return 操作人
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId 
	 *            操作人
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return 操作时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 
	 *            操作时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return IP地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip 
	 *            IP地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return 备注
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo 
	 *            备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
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
        OpLogging other = (OpLogging) that;
        return (this.getLogId() == null ? other.getLogId() == null : this.getLogId().equals(other.getLogId()))
            && (this.getActionType() == null ? other.getActionType() == null : this.getActionType().equals(other.getActionType()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getOldVal() == null ? other.getOldVal() == null : this.getOldVal().equals(other.getOldVal()))
            && (this.getNewVal() == null ? other.getNewVal() == null : this.getNewVal().equals(other.getNewVal()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLogId() == null) ? 0 : getLogId().hashCode());
        result = prime * result + ((getActionType() == null) ? 0 : getActionType().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getOldVal() == null) ? 0 : getOldVal().hashCode());
        result = prime * result + ((getNewVal() == null) ? 0 : getNewVal().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}