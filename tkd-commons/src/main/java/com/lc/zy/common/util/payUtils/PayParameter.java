package com.lc.zy.common.util.payUtils;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lc.zy.ball.domain.oa.mapper.OrganizationMapper;
import com.lc.zy.ball.domain.oa.po.Organization;
import com.lc.zy.ball.domain.oa.po.OrganizationCriteria;

@Service
public class PayParameter {
	
	@Autowired
	private OrganizationMapper organizationMapper;

	private static Logger logger = LoggerFactory.getLogger(PayParameter.class);
	/**
	 * 
	 * <根据orgCode获取组织支付账号信息>
	 *
	 * @create：2015年9月10日 下午4:31:31
	 * @author： sl
	 * @param orgCode
	 * @return
	 */
	public Organization payKeys(String orgCode) {
		// 组织代码为空，就选总公司；教陪练、活动都取总公司
		if (orgCode == null){
			orgCode = "master";
		}
		Organization org = new Organization();
		try {
			// 根据组织代码获取组织信息
			OrganizationCriteria organizationCriteria = new OrganizationCriteria();
			OrganizationCriteria.Criteria criteria = organizationCriteria.createCriteria();
			criteria.andOrgCodeEqualTo(orgCode);
			List<Organization> organization = organizationMapper.selectByExample(organizationCriteria);
			String appid = organization.get(0).getAppid();
			// 判断组织信息是否为空
			if (appid == null || "".equals(appid)) {
				org = parentBYChild(organization.get(0).getPid());
			} else {
				org = organization.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return org;
	}

	/**
	 * 
	 * <根据子节点存的父节点id去获取父节点信息，直到获取到为止>
	 *
	 * @create：2015年9月10日 下午4:33:06
	 * @author： sl
	 * @param pid
	 * @return
	 */
	public Organization parentBYChild(String pid) {
		Organization organization = new Organization();
		try {
			organization = organizationMapper.selectByPrimaryKey(pid);
			while(StringUtils.isEmpty(organization.getPartner())){
				organization = organizationMapper.selectByPrimaryKey(organization.getPid());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return organization;
	}
	
	public static void main(String[] args) {
		PayParameter pp = new PayParameter();
		pp.payKeys("qingdaoshi");
	}

}
