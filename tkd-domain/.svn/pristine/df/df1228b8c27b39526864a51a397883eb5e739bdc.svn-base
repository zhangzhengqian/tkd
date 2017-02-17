package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* StatiumAuditLog
* table:oa_statium_audit_log
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-09-05 14:36:28
*/
public class StatiumAuditLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 场馆id
     */
    private String statiumId;

    /**
     * 审核人
     */
    private String cb;

    /**
     * 创建时间
     */
    private Date ct;

    /**
     * 描述
     */
    private String description;

    /**
     * 动作（提交审核、审核通过、审核拒绝）
     */
    private String action;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 场馆id
     */
    public String getStatiumId() {
        return statiumId;
    }

    /**
     * @param statiumId 
	 *            场馆id
     */
    public void setStatiumId(String statiumId) {
        this.statiumId = statiumId;
    }

    /**
     * @return 审核人
     */
    public String getCb() {
        return cb;
    }

    /**
     * @param cb 
	 *            审核人
     */
    public void setCb(String cb) {
        this.cb = cb;
    }

    /**
     * @return 创建时间
     */
    public Date getCt() {
        return ct;
    }

    /**
     * @param ct 
	 *            创建时间
     */
    public void setCt(Date ct) {
        this.ct = ct;
    }

    /**
     * @return 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description 
	 *            描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return 动作（提交审核、审核通过、审核拒绝）
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action 
	 *            动作（提交审核、审核通过、审核拒绝）
     */
    public void setAction(String action) {
        this.action = action;
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
        StatiumAuditLog other = (StatiumAuditLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStatiumId() == null ? other.getStatiumId() == null : this.getStatiumId().equals(other.getStatiumId()))
            && (this.getCb() == null ? other.getCb() == null : this.getCb().equals(other.getCb()))
            && (this.getCt() == null ? other.getCt() == null : this.getCt().equals(other.getCt()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getAction() == null ? other.getAction() == null : this.getAction().equals(other.getAction()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStatiumId() == null) ? 0 : getStatiumId().hashCode());
        result = prime * result + ((getCb() == null) ? 0 : getCb().hashCode());
        result = prime * result + ((getCt() == null) ? 0 : getCt().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getAction() == null) ? 0 : getAction().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}