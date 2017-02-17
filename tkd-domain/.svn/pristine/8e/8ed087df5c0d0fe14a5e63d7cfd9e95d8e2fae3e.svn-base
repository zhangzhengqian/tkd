package com.lc.zy.ball.domain.oa.po.ex;

import java.util.List;

import com.lc.zy.ball.domain.oa.po.Role;
import com.lc.zy.ball.domain.oa.po.User;

@SuppressWarnings("serial")
public class UserEx extends User {
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	private String roleName;

	public String getRoleName() {
		if (roleName == null) {
			if (roles != null) {
				StringBuilder sb = new StringBuilder();
				for(Role role : roles) {
					if (sb.length() > 0) {
						sb.append(", ");
					}
					sb.append(role.getRoleName());
				}
				roleName = sb.toString();
			}
		}
		return roleName;
	}
	
	private String[] roleIds;

	public String[] getRoleIds() {
		if (roleIds == null && roles != null && roles.size() > 0) {
			roleIds = new String[roles.size()];
			for(int i = 0, len = roles.size(); i < len; i++) {
				roleIds[i] = roles.get(i).getRoleId();
			}
		}
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
	
	private String roleId;

	public String getRoleId() {
		if (roleId == null && roles != null && roles.size() > 0) {
			roleId = roles.get(0).getRoleId();
		}
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	
	/**
	 * 当前用户是否包含指定编码的角色。
	 * @param roleCode 角色编码
	 * @return 包含roleCode指定的角色则返回true，否则返回false。
	 */
	public boolean hasRole(String roleCode) {
		boolean hasRole = false;
		if (roles != null) {
			for(Role role : roles) {
				if (role.getRoleCode().equalsIgnoreCase(roleCode)) {
					hasRole = true;
					break;
				}
			}
		}
		return hasRole;
	}
	
}
