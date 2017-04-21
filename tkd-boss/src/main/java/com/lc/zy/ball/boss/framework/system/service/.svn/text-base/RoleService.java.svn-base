package com.lc.zy.ball.boss.framework.system.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.google.common.collect.Lists;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.RoleFuncMapper;
import com.lc.zy.ball.domain.oa.mapper.RoleMapper;
import com.lc.zy.ball.domain.oa.mapper.UserRoleMapper;
import com.lc.zy.ball.domain.oa.po.Role;
import com.lc.zy.ball.domain.oa.po.RoleCriteria;
import com.lc.zy.ball.domain.oa.po.RoleFuncCriteria;
import com.lc.zy.ball.domain.oa.po.RoleFuncKey;
import com.lc.zy.ball.domain.oa.po.UserRoleCriteria;
import com.lc.zy.ball.domain.oa.po.UserRoleKey;
import com.lc.zy.ball.boss.common.security.ShiroCacheManager;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.exception.ServiceException;
import com.lc.zy.common.util.UUID;

@Component
//@Transactional(value="core", readOnly = true)
@Transactional(readOnly=true)
public class RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	
	@Autowired
	private ShiroCacheManager shiroCacheManager;

	public List<Role> findAll() {
		RoleCriteria rc = new RoleCriteria();
		RoleCriteria.Criteria cri = rc.createCriteria();
		cri.andRoleCodeNotEqualTo("channel");
		rc.setOrderByClause("convert(ROLE_NAME USING gbk) COLLATE gbk_chinese_ci");
		return (List<Role>) roleMapper.selectByExample(rc);
	}
	
	
	public List<Role> findChannelRole() {
		RoleCriteria rc = new RoleCriteria();
		RoleCriteria.Criteria cri = rc.createCriteria();
		cri.andRoleCodeEqualTo("channel");
		return (List<Role>) roleMapper.selectByExample(rc);
	}
	
	public List<Role> find(Map<String, Object> map) {
		if (map == null || map.get("roleName") == null) {
			return roleMapper.selectByExample(null);
		} else {
			RoleCriteria rc = new RoleCriteria();
			RoleCriteria.Criteria cri = rc.createCriteria();
			cri.andRoleNameLike("%" + map.get("roleName") + "%");
			return roleMapper.selectByExample(rc);
		}
	}
	
	/**
	 * 分页查询对象.
	 * 
	 * @param searchParams 查询条件.
	 * @param page 分页页号, 基于0.
	 * @param size 分页大小.
	 * @return 分页数据.
	 * @throws Exception 
	 */
	public Page<Role> find(Map<String, Object> searchParams, int page, int size) throws Exception {
		
		PageRequest pageable = new PageRequest(page, size);
		
		RoleCriteria rc = new RoleCriteria();
		rc.setMysqlOffset(pageable.getOffset());
		rc.setMysqlLength(pageable.getPageSize());
		
		if (searchParams != null) {
			RoleCriteria.Criteria cri = rc.createCriteria();
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			Criterias.bySearchFilter(cri, filters.values());
		}
		
		rc.setOrderByClause("role_code asc");
		
		int total = roleMapper.countByExample(rc);
		List<Role> list = roleMapper.selectByExample(rc);
		
		return new PageImpl<>(list, pageable, total);
	}

	public Role getRole(String id) throws Exception {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Transactional(readOnly=false)
	public void saveRole(Role role) {
		if (StringUtils.isBlank(role.getRoleId())) {
			String uid = UUID.get();
			role.setRoleId(uid);
			try {
				roleMapper.insert(role);
			} catch (Exception e) {
				throw new ServiceException(e);
			}
		} else {
			try {
				roleMapper.updateByPrimaryKeySelective(role);
			} catch (Exception e) {
				throw new ServiceException(e);
			}
		}
	}

	public Role findByRoleName(String name) {
		RoleCriteria rc = new RoleCriteria();
		RoleCriteria.Criteria cri = rc.createCriteria();
		cri.andRoleNameEqualTo(name);
		List<Role> list = roleMapper.selectByExample(rc);
		return list == null || list.isEmpty() ? null : list.get(0);
	}
	
	/**
	 * 根据角色编码获取角色对象.
	 * 
	 * @param code
	 * @return 角色.
	 */
	public Role findByRoleCode(String code) {
		RoleCriteria rc = new RoleCriteria();
		RoleCriteria.Criteria cri = rc.createCriteria();
		cri.andRoleCodeEqualTo(code);
		List<Role> list = roleMapper.selectByExample(rc);
		return list == null || list.isEmpty() ? null : list.get(0);
	}

	/**
	 * 查询用户拥有的角色列表.
	 * 
	 * @param userId
	 * @return 角色列表.
	 */
	public List<Role> findByUserId(String userId) {
		UserRoleCriteria urc = new UserRoleCriteria();
		UserRoleCriteria.Criteria cri = urc.createCriteria();
		cri.andUserIdEqualTo(userId);
		List<Role> roles = Lists.newArrayList();
		List<UserRoleKey> list = userRoleMapper.selectByExample(urc);
		if (list != null && list.size() > 0) {
			for(UserRoleKey ur : list) {
				try {
					roles.add(getRole(ur.getRoleId()));
				} catch (Exception e) {
				}
			}
		}
		return roles;
	}
	
	private RoleFuncMapper roleFuncMapper;

	@Autowired
	public void setRoleFuncMapper(RoleFuncMapper roleFuncMapper) {
		this.roleFuncMapper = roleFuncMapper;
	}
	
	
	/**
	 * 保存角色功能关系.
	 * @param rid 角色ID 
	 * @param fids 功能ID集合
	 */
	@Transactional(readOnly=false)
	public void saveRoleFunc(String rid, Collection<String> fids) {
		//删除已有关系
		RoleFuncCriteria rfc = new RoleFuncCriteria();
		RoleFuncCriteria.Criteria cri = rfc.createCriteria();
		cri.andRoleIdEqualTo(rid);
		roleFuncMapper.deleteByExample(rfc);
		
		//保存
		for(String fid : fids) {
			RoleFuncKey rf = new RoleFuncKey();
			rf.setRoleId(rid);
			rf.setFuncId(fid);
			
			roleFuncMapper.insert(rf);
		}
		
		shiroCacheManager.clear();
	}

	/**
	 * 删除角色.
	 * @param roleId
	 * @throws Exception 
	 */
	@Transactional(readOnly = false)
	public void delete(String roleId) throws Exception {
		//1、删除用户角色关系
		UserRoleCriteria userRoleCriteria = new UserRoleCriteria();
		UserRoleCriteria.Criteria urc = userRoleCriteria.createCriteria();
		urc.andRoleIdEqualTo(roleId);
		userRoleMapper.deleteByExample(userRoleCriteria);
		
		//2、删除角色功能关系
		RoleFuncCriteria roleFuncCriteria = new RoleFuncCriteria();
		RoleFuncCriteria.Criteria rfc = roleFuncCriteria.createCriteria();
		rfc.andRoleIdEqualTo(roleId);
		roleFuncMapper.deleteByExample(roleFuncCriteria);
		
		//3、最后删除角色
		roleMapper.deleteByPrimaryKey(roleId);
		
		
		shiroCacheManager.clear();
		
	}

	/**
	 * 批量删除角色.
	 * @param ids 角色ID数组.
	 * @throws Exception 
	 */
	@Transactional(readOnly = false)
	public void delete(String[] ids) throws Exception {
		for(String id : ids) {
			delete(id);
		}
		
		shiroCacheManager.clear();
	}

	public List<RoleFuncKey> findRoleFunc(String id) {
		RoleFuncCriteria rfc = new RoleFuncCriteria();
		RoleFuncCriteria.Criteria cri = rfc.createCriteria();
		cri.andRoleIdEqualTo(id);
		
		return roleFuncMapper.selectByExample(rfc);
	}
	
}
