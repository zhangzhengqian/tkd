package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* CrmFunction
* table:crm_function
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-11-01 16:47:31
*/
public class CrmFunction implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 功能ID
     */
    private String funcId;

    /**
     * 功能名称
     */
    private String funcName;

    private String parentId;

    /**
     * 活动/URI:定义功能对应的活动或者URI
     */
    private String action;

    /**
     * 权限编码
     */
    private String permission;

    /**
     * 1:是/0:否
     */
    private Short isMenu;

    private Integer seqNum;

    /**
     * @return 功能ID
     */
    public String getFuncId() {
        return funcId;
    }

    /**
     * @param funcId 
	 *            功能ID
     */
    public void setFuncId(String funcId) {
        this.funcId = funcId;
    }

    /**
     * @return 功能名称
     */
    public String getFuncName() {
        return funcName;
    }

    /**
     * @param funcName 
	 *            功能名称
     */
    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * @return 活动/URI:定义功能对应的活动或者URI
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action 
	 *            活动/URI:定义功能对应的活动或者URI
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return 权限编码
     */
    public String getPermission() {
        return permission;
    }

    /**
     * @param permission 
	 *            权限编码
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * @return 1:是/0:否
     */
    public Short getIsMenu() {
        return isMenu;
    }

    /**
     * @param isMenu 
	 *            1:是/0:否
     */
    public void setIsMenu(Short isMenu) {
        this.isMenu = isMenu;
    }

    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
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
        CrmFunction other = (CrmFunction) that;
        return (this.getFuncId() == null ? other.getFuncId() == null : this.getFuncId().equals(other.getFuncId()))
            && (this.getFuncName() == null ? other.getFuncName() == null : this.getFuncName().equals(other.getFuncName()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getAction() == null ? other.getAction() == null : this.getAction().equals(other.getAction()))
            && (this.getPermission() == null ? other.getPermission() == null : this.getPermission().equals(other.getPermission()))
            && (this.getIsMenu() == null ? other.getIsMenu() == null : this.getIsMenu().equals(other.getIsMenu()))
            && (this.getSeqNum() == null ? other.getSeqNum() == null : this.getSeqNum().equals(other.getSeqNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFuncId() == null) ? 0 : getFuncId().hashCode());
        result = prime * result + ((getFuncName() == null) ? 0 : getFuncName().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getAction() == null) ? 0 : getAction().hashCode());
        result = prime * result + ((getPermission() == null) ? 0 : getPermission().hashCode());
        result = prime * result + ((getIsMenu() == null) ? 0 : getIsMenu().hashCode());
        result = prime * result + ((getSeqNum() == null) ? 0 : getSeqNum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}