package com.lc.zy.ball.crm.common;

import org.apache.shiro.SecurityUtils;

import com.lc.zy.ball.crm.common.security.ShiroDbRealm.ShiroUser;
import com.lc.zy.ball.domain.oa.po.CrmUser;

public class SessionUtil {
	
	/**
	 * 获取当前用户
	 * 
	 * @return
	 */
	public static CrmUser currentUser() {
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
	 *
	 * <获取当前用户道馆id><功能具体实现>
	 *
	 * @create：2016/11/21 下午8:14
	 * @author：sl
	 * @param
	 * @return java.lang.String
	 */
	public static String currentStatium() {
		try {
			return currentUser().getStatiumId();
		} catch (Exception e) {
			return null;
		}
	}
	
}
