package com.lc.zy.ball.crm.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lc.zy.ball.crm.common.security.ShiroDbRealm.ShiroUser;
import com.lc.zy.ball.domain.oa.po.CrmUser;
import com.lc.zy.common.util.SpringUtils;

public abstract class UserContext {
	
	private static final Logger log = LoggerFactory.getLogger(UserContext.class);

	/**
	 * @return 当前登录用户对象.
	 */
	public static CrmUser getCurrent() {
		try {
			if (SecurityUtils.getSubject().isAuthenticated()) {
				return ((ShiroUser)SecurityUtils.getSubject().getPrincipal()).getUser();
			}
		} catch (Exception e) {
			log.warn("获取当前用户发生异常", e);
		}
		
		return null;
	}
	
	/**
	 * @return 当前登录用户是否是“系统用户”
	 */
	public static boolean isSysUser() {
		
		return getCurrent() != null && (StringUtils.isBlank(getCurrent().getCustId()) || 
						"0".equals(getCurrent().getCustId()));
	}
	
	
	/**
	 * 用于定时任务上下文中获取系统用户.
	 * 
	 * @return 系统用户.
	 */
	public static CrmUser getSystemUser() {
		bindSecurityManager();
		if (getCurrent() == null || !"root".equals(getCurrent().getLoginName())) {
			return loginAsSystemUser();
		} else {
			return getCurrent();
		}
	}

	private static void bindSecurityManager() {
		if (ThreadContext.getSecurityManager() == null) {
			log.debug("Binding SecurityManager...");
			ThreadContext.bind(SpringUtils.getBean(SecurityManager.class));
		}
	}
	
	private static CrmUser loginAsSystemUser() {
		bindSecurityManager();
		ThreadContext.unbindSubject();
		
		Subject subject = SecurityUtils.getSubject();
		
		log.debug("Subject loging as root...");
		//subject.login(new UsernamePasswordToken("root", password));
		
		return getCurrent();
	}
	
	
	public static final String SYSTEM_PASSWORD_KEY = "system.password";
	
	// ENCODE_KEY = AESCodec.initKey();
	public static final String ENCODE_KEY = "f5cJRPkyNeEdkCFI7Kx8XA==";
	
	
}
