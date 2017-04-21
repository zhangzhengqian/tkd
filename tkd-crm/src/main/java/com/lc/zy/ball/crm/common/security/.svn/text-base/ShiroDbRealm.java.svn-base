/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.lc.zy.ball.crm.common.security;

import com.google.common.base.Objects;
import com.lc.zy.ball.domain.oa.mapper.CrmFunctionMapper;
import com.lc.zy.ball.domain.oa.mapper.CrmUserFuncMapper;
import com.lc.zy.ball.domain.oa.mapper.CrmUserMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumInfosMapper;
import com.lc.zy.ball.domain.oa.po.*;
import com.lc.zy.common.util.Encrypts;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springside.modules.utils.Encodes;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
public class ShiroDbRealm extends AuthorizingRealm {
	
	private static final Logger log = LoggerFactory.getLogger(ShiroDbRealm.class);
	
	@Autowired private CrmUserFuncMapper userFuncMapper;
	@Autowired private CrmFunctionMapper functionMapper;

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		
		CrmUser user = null;
		try {
			user = getCrmUserByLoginName(token.getUsername());
		} catch (Exception e) {
			log.error("Can't find by loginName: " + token.getUsername(), e);
		}
		
		if (user != null) {
			byte[] salt = Encodes.decodeHex(user.getSalt());
			return new SimpleAuthenticationInfo(new ShiroUser(user), user.getPassword(), ByteSource.Util.bytes(salt), getName());
		} else {
			return null;
		}
	}
	
	
	@Autowired
	private CrmUserMapper userMapper;
	
	private CrmUser getCrmUserByLoginName(String loginName) throws Exception {
		CrmUserCriteria crmUserCriteria = new CrmUserCriteria();
		crmUserCriteria.createCriteria().andLoginNameEqualTo(loginName);
		List<CrmUser> list = userMapper.selectByExample(crmUserCriteria);
		log.debug("crmUser {}", list.get(0).getLoginName());
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}	
	
	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		CrmUser user = (CrmUser)shiroUser.getUser();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		CrmUserFuncCriteria crmUserFuncCriteria = new CrmUserFuncCriteria();
		crmUserFuncCriteria.createCriteria().andUserIdEqualTo(user.getUserId());
		List<CrmUserFunc> list = userFuncMapper.selectByExample(crmUserFuncCriteria);
		List<String> p = new ArrayList<String>();
		for (CrmUserFunc uf : list) {
			CrmFunction f = functionMapper.selectByPrimaryKey(uf.getFuncId());
			//基于Permission的权限信息
			p.add(f.getFuncId());	
			p.add(f.getFuncName());
			p.add(f.getPermission());
		}
		info.addStringPermissions(p);
		return info;
	}

	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Encrypts.HASH_ALGORITHM);
		matcher.setHashIterations(Encrypts.HASH_ITERATIONS);

		setCredentialsMatcher(matcher);
	}

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;
		
		private CrmUser user;

		public ShiroUser(CrmUser user) {
			this.user = user;
		}
		
		public CrmUser getUser() {
			return user;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Autowired
		StatiumInfosMapper statiumInfosMapper;
		public String toString() {

			return user.getNickname();
		}

		public int hashCode() {
			return user.hashCode();
		}
		
		public String getLoginName() {
			return user.getLoginName();
		}

		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			
			ShiroUser that = (ShiroUser) obj;
			
			return Objects.equal(this.getLoginName(), that.getLoginName());
		}
	}
	
	
}
