package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* Role
* table:c_role
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-15 18:07:50
*/
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * @return 角色ID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId 
	 *            角色ID
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * @return 角色编码
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * @param roleCode 
	 *            角色编码
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * @return 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName 
	 *            角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
        Role other = (Role) that;
        return (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getRoleCode() == null ? other.getRoleCode() == null : this.getRoleCode().equals(other.getRoleCode()))
            && (this.getRoleName() == null ? other.getRoleName() == null : this.getRoleName().equals(other.getRoleName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getRoleCode() == null) ? 0 : getRoleCode().hashCode());
        result = prime * result + ((getRoleName() == null) ? 0 : getRoleName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}