package com.lc.zy.ball.boss.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.enums.Roles;
import com.lc.zy.ball.domain.oa.mapper.OrganizationMapper;
import com.lc.zy.ball.domain.oa.mapper.RoleMapper;
import com.lc.zy.ball.domain.oa.mapper.UserMapper;
import com.lc.zy.ball.domain.oa.mapper.UserRoleMapper;
import com.lc.zy.ball.domain.oa.po.Organization;
import com.lc.zy.ball.domain.oa.po.OrganizationCriteria;
import com.lc.zy.ball.domain.oa.po.Role;
import com.lc.zy.ball.domain.oa.po.RoleCriteria;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.ball.domain.oa.po.UserCriteria;
import com.lc.zy.ball.domain.oa.po.UserRoleCriteria;
import com.lc.zy.ball.domain.oa.po.UserRoleKey;

/**
 * 封装一些通用 业务方法
 * @author liangc
 */
@Component
public class CommonService extends AbstractCacheService{
	
	private OrganizationMapper organizationMapper ;
	private UserMapper userMapper = null;
	private RoleMapper roleMapper = null;
	private UserRoleMapper userRoleMapper = null;
	
	@Autowired
	public CommonService(OrganizationMapper organizationMapper,
			UserMapper userMapper, RoleMapper roleMapper,
			UserRoleMapper userRoleMapper) {
		super();
		this.organizationMapper = organizationMapper;
		this.userMapper = userMapper;
		this.roleMapper = roleMapper;
		this.userRoleMapper = userRoleMapper;
	}

	/**
	 * 根据组织编码获取组织名称
	 * TODO : 如果访问量大，可以用 redis 来存 orgCode 和 orgObject 的映射
	 * @param code
	 * @return
	 */
	public String getOrgNameByCode(String code){
		try{
			OrganizationCriteria organizationCriteria = new OrganizationCriteria();
			organizationCriteria.createCriteria().andOrgCodeEqualTo(code);
			List<Organization>  list = organizationMapper.selectByExample(organizationCriteria);
			Organization org = list.get(0);
			return org.getOrgName();
		}catch(Exception e){
			return "";
		}
	}
	
	/**
	 * 获取当前用户所在组织，并以此组织为根，构建组织树；
	 * @return
	 * @throws Exception
	 */
	public String getOrgTreeOfCurrentUser() throws Exception {
		//在当前登陆人信息中取出组织id
		User user =  SessionUtil.currentUser();
		String orgCode = user.getOrgCode();
		if("root".equals(user.getLoginName())){
			orgCode = "master";
		}
		List<Organization> orgList = organizationMapper.selectByExample(new OrganizationCriteria());
		
		//相同pid的节点，都被放在同一个集合中，方便构建树
		Map<String,List<Organization>> pidKeyMap = new HashMap<String,List<Organization>>();		
		Organization currentOrg = null;
		for(Organization org: orgList){
			if(org.getOrgCode().equals(orgCode)){
				currentOrg = org;
			}
			List<Organization> nl  = new ArrayList<Organization>();
			//由pid为key，组成的节点字典
			if ( pidKeyMap.get(org.getPid()) != null){
				nl = pidKeyMap.get(org.getPid());
			}
			nl.add(org);
			pidKeyMap.put(org.getPid(), nl);
		}
		List<Map<String,Object>> tree = new ArrayList<Map<String,Object>>();
		Map<String,Object> root = new HashMap<String,Object>();
		root.put("id", 0);
		root.put("name", "选择组织");
		root.put("open", true);
		root.put("nocheck", true);
		root.put("chkDisabled", true);
		tree.add(root);
		List<Organization> result = new ArrayList<Organization>();
		buildTree(currentOrg,pidKeyMap,result);
		for(Organization o : result){
			System.out.println("pid="+o.getPid()+" ; name="+o.getOrgName());
		}
		for(Organization org : result){
			Map<String,Object> node = new HashMap<String,Object>();
			node.put("id", org.getId());
			node.put("pId", org.getPid());
			node.put("name", org.getOrgName());
			node.put("orgCode", org.getOrgCode());
			node.put("open", true);
			node.put("nocheck", false);
			if(currentOrg.getOrgCode().equals(org.getOrgCode())){
				node.put("checked", true);
			}
			tree.add(node);
		}
		return new Gson().toJson(tree);
	}
	/**
	 * 构建组织树
	 */
	private void  buildTree(Organization org,Map<String,List<Organization>> pidKeyMap,List<Organization> result){
		result.add(org);
		String orgId = org.getId();
		if (pidKeyMap.get(orgId)!=null){
			List<Organization> nl = pidKeyMap.get(orgId);
			for(Organization n: nl){
				buildTree(n ,pidKeyMap,result);
			}
		}
	}
	
	/**
	 * 根据 角色编码 与 组织编码 获取用户
	 * @param roleCode 如果角色编码为空，则忽略此条件
	 * @param orgCode 如果组织编码为空，则忽略此条件
	 * @return
	 */
	public List<User> findUserByRoleAndOrgCode(Roles roles,String orgCode)  throws Exception {
		Role r = null;
		//得到角色对象，根据角色编码
		try{
			if(roles!=null){
				RoleCriteria roleCriteria = new RoleCriteria();
				roleCriteria.createCriteria().andRoleCodeEqualTo(roles.getCode());
				List<Role> rl = roleMapper.selectByExample(roleCriteria);
				r = rl.get(0);
			}
		}catch(Exception e){
			throw e;
		}
		UserCriteria userCriteria = new UserCriteria();
		UserCriteria.Criteria cri =  userCriteria.createCriteria();
		if(StringUtils.isNotEmpty(orgCode)){
			cri.andOrgCodeEqualTo(orgCode);
		}
		//根据角色找到对应的用户
		if(r!=null){
			UserRoleCriteria userRoleCriteria = new UserRoleCriteria();
			userRoleCriteria.createCriteria().andRoleIdEqualTo(r.getRoleId());
			List<UserRoleKey> urlist = userRoleMapper.selectByExample(userRoleCriteria);
			List<String> ulist = new ArrayList<String>();
			for(UserRoleKey ur : urlist){
				ulist.add(ur.getUserId());
			}
			cri.andUserIdIn(ulist);
		}
		List<User> list = userMapper.selectByExample(userCriteria);
		return list;
	}
	
}
