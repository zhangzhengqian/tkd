package com.lc.zy.ball.boss.framework.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.domain.oa.mapper.OrganizationMapper;
import com.lc.zy.ball.domain.oa.mapper.ex.OrganizationMapperEx;
import com.lc.zy.ball.domain.oa.po.Organization;
import com.lc.zy.ball.domain.oa.po.OrganizationCriteria;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.ball.domain.oa.po.ex.OrganizationEx;
import com.lc.zy.common.exception.ServiceException;
import com.lc.zy.common.util.UUID;

/**
 * 组织管理
 * @author liangc
 */
@Component
@Transactional(readOnly = false)
public class OrgService extends AbstractCacheService {
	
	@Autowired
	private OrganizationMapper organizationMapper ;
	
	public String getOrgTree(String uid) throws Exception{
		User u = null;
		if(StringUtils.isNotEmpty(uid)){
			u = selectByPrimaryKey(User.class, uid);
		}
		//l = [{'name': '根', 'open': True, 'id':0 , 'nocheck':True }]
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> root = new HashMap<String,Object>();
		root.put("id", 0);
		root.put("name", "所属组织");
		root.put("open", true);
		root.put("nocheck", true);
		root.put("chkDisabled", true);
		
		list.add(root);
		List<Organization> orgList = organizationMapper.selectByExample(new OrganizationCriteria());
		for(Organization org : orgList){
			Map<String,Object> node = new HashMap<String,Object>();
			node.put("id", org.getId());
			node.put("pId", org.getPid());
			node.put("name", org.getOrgName());
			node.put("orgCode", org.getOrgCode());
			node.put("open", true);
			node.put("nocheck", false);
			if(u!=null && org.getOrgCode().equals(u.getOrgCode()) ){
				node.put("checked", true);
			}
			list.add(node);
		}
		return new Gson().toJson(list);
	}
	
	@Transactional(readOnly = false)
	public void saveOrganization(Organization f) {
		try {
			if (StringUtils.isNotBlank(f.getId())) {
				organizationMapper.updateByPrimaryKeySelective(f);
			} else {
				String id = UUID.get();
				f.setId(id);
				organizationMapper.insertSelective(f);
			}
			
		} catch (Exception e) {
			throw new ServiceException("保存or更新Organization时发生异常", e);
		}
	}
	
	@Transactional(readOnly = false)
	public void delOrganization(String fid) {
		organizationMapper.deleteByPrimaryKey(fid);
	}
	


	public Organization getOrganization(String fid) {
		try {
			return organizationMapper.selectByPrimaryKey(fid);
		} catch (Exception e) {
			throw new ServiceException("查询Organization时发生异常", e);
		}
	}

	public List<Organization> findByPId(String id) {
		OrganizationCriteria fc = new OrganizationCriteria();
		OrganizationCriteria.Criteria cri = fc.createCriteria();
		cri.andPidEqualTo(id);
		
		fc.setOrderByClause("seq_num");
		
		return organizationMapper.selectByExample(fc);
	}
	
	public int countByPId(String id) {
		OrganizationCriteria fc = new OrganizationCriteria();
		OrganizationCriteria.Criteria cri = fc.createCriteria();
		cri.andPidEqualTo(id);
		return organizationMapper.countByExample(fc);
	}

	public Organization getByNameAndPId(String orgName, String pId) {
		OrganizationCriteria fc = new OrganizationCriteria();
		OrganizationCriteria.Criteria cri = fc.createCriteria();
		cri.andPidEqualTo(pId);
		cri.andOrgNameEqualTo(orgName);
		
		List<Organization> list = organizationMapper.selectByExample(fc);
		
		return (list.size() > 0 ? list.get(0) : null);
	}

	public List<Organization> findAll() {
		return organizationMapper.selectByExample(null);
	}
	
	
	@Autowired
	private OrganizationMapperEx organizationMapperEx ;
	
	public List<OrganizationEx> findExByPId(String pid) {
		return organizationMapperEx.selectByPid(pid);
	}

}
