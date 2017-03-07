package com.lc.zy.ball.boss.common;

import org.apache.shiro.SecurityUtils;

import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.ball.boss.common.security.ShiroDbRealm.ShiroUser;

public class SessionUtil {
	
	/**
	 * 获取当前用户
	 * 
	 * @return
	 */
	public static User currentUser() {
		try{			
			return ((ShiroUser) SecurityUtils.getSubject().getPrincipal()).getUser();
		}catch(Exception e){
			return null;
		}
	}

	/**
	 * 获取当前用户id
	 * 
	 * @return
	 */
	public static String currentUserId() {
		try {
			return currentUser().getUserId();
		} catch (Exception e) {
			return null;
		}
	}
	

	/**
	 * 获取当前用户名
	 * 
	 * @return
	 */
	public static String currentUsername() {
		try {
			return ((ShiroUser) SecurityUtils.getSubject().getPrincipal()).getLoginName();
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 获取用户的角色id
	 * 
	 * @return
	 */
	public static String currentUserRole() {
		try {
			String roleId=((ShiroUser) SecurityUtils.getSubject().getPrincipal()).getUser().getRoleId();
			return roleId;
		} catch (Exception e) {
			return null;
		}
	}
}
