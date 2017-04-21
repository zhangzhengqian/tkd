package com.lc.zy.ball.crm.common.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShiroCacheManager {
	
	private static final Logger log = LoggerFactory.getLogger(ShiroCacheManager.class);
	
	
	@Autowired
	private ShiroDbRealm shiroDbRealm;
	
	public void clear() {
		log.debug("isAuthenticationCachingEnabled = {}", shiroDbRealm.isAuthenticationCachingEnabled());
		log.debug("isAuthorizationCachingEnabled = {}", shiroDbRealm.isAuthorizationCachingEnabled());
		
		if (shiroDbRealm.isAuthenticationCachingEnabled()) {
			log.debug("AuthenticationCacheName = {}", shiroDbRealm.getAuthenticationCacheName());
			Cache<Object, AuthenticationInfo> cache = shiroDbRealm.getAuthenticationCache();
			if (cache != null) cache.clear();
		}
		
		if (shiroDbRealm.isAuthorizationCachingEnabled()) {
			log.debug("AuthorizationCacheName = {}", shiroDbRealm.getAuthorizationCacheName());
			Cache<Object, AuthorizationInfo> cache = shiroDbRealm.getAuthorizationCache();
			if (cache != null) cache.clear();
		}
	}
}
