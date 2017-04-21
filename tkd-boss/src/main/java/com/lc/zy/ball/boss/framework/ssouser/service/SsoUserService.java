package com.lc.zy.ball.boss.framework.ssouser.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.ManageSsoMapper;
import com.lc.zy.ball.domain.oa.mapper.SsoUserMapper;
import com.lc.zy.ball.domain.oa.po.ManageSso;
import com.lc.zy.ball.domain.oa.po.ManageSsoCriteria;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.SsoUserCriteria;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.UUID;

@Service
public class SsoUserService extends AbstractCacheService {

	// log
	private static final Logger logger = LoggerFactory
			.getLogger(SsoUserService.class);

	@Autowired
	private SsoUserMapper ssoUserMapper;

	@Autowired
	private ManageSsoMapper manageSsoMapper;

	/**
	 * 
	 * <获取用户list>
	 *
	 * @create：2015年8月4日 上午10:55:33
	 * @author： sl
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<SsoUser> findSsoUserList(PageRequest pageRequest,
			Map<String, Object> searchParams, boolean isPage, boolean isHasCount) {
		// 数量
		int total = 0;
		// list
		List<SsoUser> list = new ArrayList<SsoUser>();
		try {
			SsoUserCriteria ssoUserCriteria = new SsoUserCriteria();
			// 查询条件
			Map<String, SearchFilter> filters = SearchFilter
					.parse(searchParams);
			// 分页
			if (isPage) {
				ssoUserCriteria.setMysqlLength(pageRequest.getPageSize());
				ssoUserCriteria.setMysqlOffset(pageRequest.getOffset());
			}
			SsoUserCriteria.Criteria cri = ssoUserCriteria.createCriteria();
			Criterias.bySearchFilter(cri, filters.values());
			// 用户状态(正常:1、删除:2、冻结:3)
			cri.andStateNotEqualTo(2);
			// 排序
			ssoUserCriteria.setOrderByClause("update_time desc");
			// 数量
			if (isHasCount) {
				total = ssoUserMapper.countByExample(ssoUserCriteria);
			}
			// list
			list = ssoUserMapper.selectByExample(ssoUserCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取用户list(SsoUserService--findSsoUserList)"
					+ e.getMessage());
		}
		return new PageImpl<>(list, pageRequest, total);
	}

	/**
	 * 
	 * <删除用户>
	 *
	 * @create：2015年8月4日 下午2:25:32
	 * @author： sl
	 * @param id
	 */
	public void deleteSsoUserById(String id) {
		try {
			SsoUser ssoUser = new SsoUser();
			ssoUser.setId(id);
			ssoUser.setState(2);
			// 删除用户
			this.updateByPrimaryKeySelective(ssoUser, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除用户(SsoUserService--deleteSsoUser)" + e.getMessage());
		}
	}

	/**
	 * 添加营销账号
	 * 
	 * @param id
	 */
	public void addMarketUser(String id) {
		ManageSso sso = new ManageSso();
		sso.setId(UUID.get());
		sso.setSsoUserId(id);
		sso.setUserId(SessionUtil.currentUserId());
		manageSsoMapper.insert(sso);
	}

	/**
	 * 判断是否已经添加
	 * 
	 * @param id
	 */
	public boolean checkMarketUser(String id) {
		ManageSsoCriteria criteria = new ManageSsoCriteria();
		ManageSsoCriteria.Criteria cri = criteria.createCriteria();
		cri.andSsoUserIdEqualTo(id);
		cri.andUserIdEqualTo(SessionUtil.currentUserId());
		if (manageSsoMapper.countByExample(criteria) > 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 
	 * <冻结用户>
	 *
	 * @create：2015年8月4日 下午3:36:38
	 * @author： sl
	 * @param id
	 */
	public void freezeSsoUser(String id) {
		try {
			SsoUser ssoUser = new SsoUser();
			ssoUser.setId(id);
			ssoUser.setState(3);
			this.updateByPrimaryKeySelective(ssoUser, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("冻结用户(SsoUserService--freezeSsoUser)" + e.getMessage());
		}
	}

	/**
	 * 
	 * <解冻用户>
	 *
	 * @create：2015年8月4日 下午3:38:48
	 * @author： sl
	 * @param id
	 */
	public void unfreezeSsoUser(String id) {
		try {
			SsoUser ssoUser = new SsoUser();
			ssoUser.setId(id);
			ssoUser.setState(1);
			this.updateByPrimaryKeySelective(ssoUser, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("解冻用户(SsoUserService--freezeSsoUser)" + e.getMessage());
		}
	}

	/**
	 * 
	 * <查看用户信息>
	 *
	 * @create：2015年8月4日 下午5:20:50
	 * @author： sl
	 * @param id
	 * @return
	 */
	public SsoUser viewSsoUser(String id) {
		SsoUser ssoUser = new SsoUser();
		try {
			ssoUser = this.selectByPrimaryKey(SsoUser.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查看用户信息(SsoUserService--viewSsoUser)" + e.getMessage());
		}
		return ssoUser;
	}

	/**
	 * 
	 * <用户添加>
	 *
	 * @create：2015年8月4日 下午8:49:55
	 * @author： sl
	 * @param ssoUser
	 */
	public void ssoUserForm(SsoUser ssoUser) {
		try {
			String id = UUID.get();
			ssoUser.setId(id);
			this.insertSelective(ssoUser, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户添加(SsoUserService--ssoUserForm)" + e.getMessage());
		}
	}

	/**
	 * 
	 * <更新用户信息>
	 *
	 * @create：2015年8月5日 下午8:41:29
	 * @author： sl
	 * @param ssoUser
	 */
	public void updateSsoUser(SsoUser ssoUser) {
		try {
			this.updateByPrimaryKeySelective(ssoUser, ssoUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户添加(SsoUserService--ssoUserForm)" + e.getMessage());
		}
	}
	
	/**
	 * 
	 * <解绑道馆><功能具体实现>
	 *
	 * @create：2016年8月17日 下午6:31:04
	 * @author：zzq
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SsoUser unbundling(String id) {
		SsoUser ssoUser = ssoUserMapper.selectByPrimaryKey(id);
		//解绑用户与道馆的关系就是将用户的道馆id置为空
		if (ssoUser.getStatiumId() != null) {
			ssoUser.setStatiumId(null);
		}
		try {
			this.updateByPrimaryKey(ssoUser, ssoUser.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ssoUser;
	}
}
