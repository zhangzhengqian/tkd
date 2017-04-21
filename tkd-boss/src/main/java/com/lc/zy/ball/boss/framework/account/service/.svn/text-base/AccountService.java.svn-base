package com.lc.zy.ball.boss.framework.account.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.account.AccountVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.AreaMapper;
import com.lc.zy.ball.domain.oa.mapper.RoleMapper;
import com.lc.zy.ball.domain.oa.mapper.UserMapper;
import com.lc.zy.ball.domain.oa.mapper.UserRoleMapper;
import com.lc.zy.ball.domain.oa.po.Area;
import com.lc.zy.ball.domain.oa.po.AreaCriteria;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.ball.domain.oa.po.UserCriteria;
import com.lc.zy.ball.domain.oa.po.UserRoleCriteria;
import com.lc.zy.ball.domain.oa.po.UserRoleKey;
import com.lc.zy.common.Constants.UserRole;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.Encrypts;
import com.lc.zy.common.util.UUID;

@Service
@Transactional(readOnly = true)
public class AccountService extends AbstractCacheService {

	private Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	public UserMapper userMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private AreaMapper areaMapper;

	/**
	 * 
	 * <账户列表><功能具体实现>
	 *
	 * @create：2017年4月12日 下午5:40:18
	 * @author：ywl
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<User> list(PageRequest pageRequest, Map<String, Object> searchParams) {
		int total = 0;
		List<User> list = null;
		try {
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			UserCriteria userCriteria = new UserCriteria();
			UserCriteria.Criteria cri = userCriteria.createCriteria();
			userCriteria.setMysqlOffset(pageRequest.getOffset());
			userCriteria.setMysqlLength(pageRequest.getPageSize());
			cri.andAreacodeIsNotNull();
			cri.andOrgCodeEqualTo("county");
			String userRoleId = SessionUtil.currentUserRole();
			User user = SessionUtil.currentUser();
			if (userRoleId.equals("1002")) {
				cri.andProvinceEqualTo(user.getProvince());
				cri.andUserIdNotEqualTo(user.getUserId());
			} else if (userRoleId.equals("1003")) {
				cri.andProvinceEqualTo(user.getProvince());
				cri.andCityEqualTo(user.getCity());
				cri.andUserIdNotEqualTo(user.getUserId());
			} else if (userRoleId.equals("1004")) {
				cri.andProvinceEqualTo(user.getProvince());
				cri.andCityEqualTo(user.getCity());
				cri.andAreaEqualTo(user.getArea());
				cri.andUserIdNotEqualTo(user.getUserId());
			}
			Criterias.bySearchFilter(cri, filters.values());
			total = userMapper.countByExample(userCriteria);
			list = userMapper.selectByExample(userCriteria);
		} catch (Exception e) {
			logger.debug("账户列表：{}" + e.getMessage());
		}
		return new PageImpl<User>(list, pageRequest, total);
	}

	@Transactional(readOnly = false)
	public void save(AccountVo vo) {
		User user = new User();
		String roleId = vo.getRoleIds();
		String userId = SessionUtil.currentUserId();
		String areaId = vo.getAreaId();
		// Area area = areaMapper.selectByPrimaryKey(areaId);
		/*
		 * String parentId = area.getParentId(); if(!parentId.equals("0") ||
		 * parentId != "0"){ Area areas =
		 * areaMapper.selectByPrimaryKey(parentId); }
		 */
		/*
		 * if(StringUtils.isNotEmpty(vo.getAreacode())){ List<String> map =
		 * Zonemap.getAreaCode(vo.getAreacode()); String province = map.get(0);
		 * String city = map.get(1); String area = map.get(2);
		 * logger.debug("areacode:{}"+map);
		 * logger.debug("areacode:{}"+province);
		 * logger.debug("areacode:{}"+city); logger.debug("areacode:{}"+area); }
		 * if(StringUtils.isNotEmpty(vo.getAreacode())){ Map<String, String>
		 * areaMap = Zonemap.split(vo.getAreacode());
		 * logger.debug("areaMap====:{}"+areaMap); if(roleId.equals("1002") ||
		 * roleId == "1002"){ if("市辖区".equals(areaMap.get("city")) ||
		 * areaMap.get("city") == null){ user.setCity(areaMap.get("province"));
		 * }else{ user.setCity(areaMap.get("city")); }
		 * //user.setProvince(areaMap.get("province")); } }
		 */

		String[] hash = Encrypts.hashPassword(vo.getPassword());
		try {
			BeanUtils.copyProperties(vo, user);
			user.setUserId(UUID.get());
			user.setNickname(vo.getNickname());
			user.setRealname(vo.getNickname());
			user.setLoginName(vo.getLoginName());
			user.setPassword(hash[0]);
			user.setSalt(hash[1]);
			user.setStatus("0");
			user.setCreateTime(new Date());
			user.setCb(userId);
			// user.setAreacode(area.getParentId());
			userMapper.insertSelective(user);

			UserRoleCriteria userRoleCri = new UserRoleCriteria();
			UserRoleCriteria.Criteria cri = userRoleCri.createCriteria();
			cri.andUserIdEqualTo(user.getUserId());
			userRoleMapper.deleteByExample(userRoleCri);

			UserRoleKey userRole = new UserRoleKey();
			userRole.setRoleId(vo.getRoleIds());
			userRole.setUserId(user.getUserId());
			userRoleMapper.insert(userRole);

		} catch (Exception e) {
			logger.debug("保存账户：{}" + e.getMessage());
		}
	}

	/**
	 * 
	 * <删除账号><功能具体实现>
	 *
	 * @create：2017年4月13日 下午2:10:50
	 * @author：ywl
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void delAccount(String userId) {
		try {

			UserRoleCriteria userRoleCri = new UserRoleCriteria();
			UserRoleCriteria.Criteria cri = userRoleCri.createCriteria();
			cri.andUserIdEqualTo(userId);
			userRoleMapper.deleteByExample(userRoleCri);

			userMapper.deleteByPrimaryKey(userId);

		} catch (Exception e) {
			logger.debug("删除账号{}" + e.getMessage());
		}
	}

	/**
	 * 
	 * <获取地区><功能具体实现>
	 *
	 * @create：2017年4月13日 下午3:06:50
	 * @author：ywl
	 * @param parentId
	 * @return
	 */
	public List<Area> getArea(String parentId) {
		AreaCriteria areaCriteria = new AreaCriteria();
		AreaCriteria.Criteria cri = areaCriteria.createCriteria();
		cri.andParentIdEqualTo(parentId);
		List<Area> list = areaMapper.selectByExample(areaCriteria);
		return list;
	}

	public void resetPwd(String userId) {
		try {
			String[] hash = Encrypts.hashPassword("123456");
			User user = this.userMapper.selectByPrimaryKey(userId);
			user.setPassword(hash[0]);
			user.setSalt(hash[1]);
			this.userMapper.updateByPrimaryKey(user);
		} catch (Exception e) {
			logger.debug("初始化密码{}" + e.getMessage());
		}
	}
}
