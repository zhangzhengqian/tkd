package com.lc.zy.ball.boss.framework.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumInfosVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.ManageSsoMapper;
import com.lc.zy.ball.domain.oa.mapper.PmwInfoimgMapper;
import com.lc.zy.ball.domain.oa.mapper.PmwMemberMapper;
import com.lc.zy.ball.domain.oa.mapper.SsoUserMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumInfosMapper;
import com.lc.zy.ball.domain.oa.po.ManageSso;
import com.lc.zy.ball.domain.oa.po.ManageSsoCriteria;
import com.lc.zy.ball.domain.oa.po.PmwInfoimg;
import com.lc.zy.ball.domain.oa.po.PmwInfoimgCriteria;
import com.lc.zy.ball.domain.oa.po.PmwMember;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.SsoUserCriteria;
import com.lc.zy.ball.domain.oa.po.StatiumInfos;
import com.lc.zy.ball.domain.oa.po.StatiumInfosCriteria;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.mq.QueueNames;
import com.lc.zy.common.mq.QueueProducer;
import com.lc.zy.common.util.UUID;

@Service
@Transactional
public class StatiumsService extends AbstractCacheService{
	private Logger logger = LoggerFactory.getLogger(StatiumsService.class);
	@Autowired
	private StatiumInfosMapper statiumInfosMapper;

	@Autowired
	private PmwMemberMapper pmwMemberMapper;

	@Autowired
	private PmwInfoimgMapper pmwInfoimgMapper;

	@Autowired
	private QueueProducer queueProducer = null;
	
	@Autowired
	private SsoUserMapper ssoUserMapper;
	
	@Autowired
	private ManageSsoMapper manageSsoMapper;

	@Resource(name = "oaJdbcTemplate")
	private JdbcTemplate jdbcTempalte;

	/**
	 * 
	 * <根据道馆id获取道馆信息><功能具体实现>
	 *
	 * @create：2016年5月3日 下午3:15:50
	 * @author：sl
	 * @param id
	 * @return
	 */
	public StatiumInfos getStatiumInfosByDgid(Integer id) {
		StatiumInfos statiumInfos = new StatiumInfos();
		try {
			StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
			StatiumInfosCriteria.Criteria statiumInfosCa = statiumInfosCriteria
					.createCriteria();
			statiumInfosCa.andDgIdEqualTo(id);
			List<StatiumInfos> statiumInfoList = statiumInfosMapper
					.selectByExample(statiumInfosCriteria);
			if (statiumInfoList.size() > 0) {
				statiumInfos = statiumInfoList.get(0);
			} else {// 道馆明细表不存在道馆信息，pmw老表查询
				setStatiumInfos(id, statiumInfos);
			}
		} catch (Exception e) {
			logger.debug("根据道馆id获取道馆信息:{}", e.getMessage());
		}
		return statiumInfos;
	}

	/**
	 * 
	 * <道馆信息保存><功能具体实现>
	 *
	 * @create：2016年5月3日 下午3:33:48
	 * @author：sl
	 * @param myForm
	 */
	@Transactional(readOnly = false)
	public void save(StatiumInfosVo myForm) {
		try {
			StatiumInfos statiumInfos = new StatiumInfos();
			BeanUtils.copyProperties(myForm, statiumInfos);
			String id = statiumInfos.getId();
			// date
			Date now = new Date();
			// userId
			String uId = SessionUtil.currentUserId();
			if (StringUtils.isBlank(id)) {
				// id
				id = UUID.get();
				statiumInfos.setId(id);
				// ct
				statiumInfos.setCt(now);
				// cb
				statiumInfos.setCb(uId);
				// et
				statiumInfos.setEt(now);
				// eb
				statiumInfos.setEb(uId);
				this.insertSelective(statiumInfos, id);
			} else {
				// et
				statiumInfos.setEt(now);
				// eb
				statiumInfos.setEb(uId);
				this.updateByPrimaryKeySelective(statiumInfos, id);
			}
			// MQ同步道馆信息
			queueProducer.push(QueueNames.SYNC_STATIUM_QUEUE, myForm.getDgId()
					.toString());
		} catch (Exception e) {
			logger.debug("道馆信息保存", e.getMessage());
		}
	}

	/**
	 * 
	 * <道馆明细表不存在道馆信息，pmw老表查询><功能具体实现>
	 *
	 * @create：2016年5月3日 下午3:28:56
	 * @author：sl
	 * @param dgId
	 * @param statiumInfos
	 */
	private void setStatiumInfos(Integer dgId, StatiumInfos statiumInfos) {
		try {
			PmwMember pmwMember = pmwMemberMapper.selectByPrimaryKey(dgId);
			statiumInfos.setDgId(dgId);
			statiumInfos.setDgName(pmwMember.getA2());
			statiumInfos.setAddress(pmwMember.getA3());
			statiumInfos.setContact(pmwMember.getCnname());
			statiumInfos.setTel(pmwMember.getA5());
			// 根据道馆Id获取原数据库中的道馆地图信息
			PmwInfoimgCriteria pmwInfoimgCriteria = new PmwInfoimgCriteria();
			PmwInfoimgCriteria.Criteria infoCriteria = pmwInfoimgCriteria
					.createCriteria();
			infoCriteria.andHyIdEqualTo(dgId);
			List<PmwInfoimg> pmwInfoImgs = pmwInfoimgMapper
					.selectByExample(pmwInfoimgCriteria);
			if (!pmwInfoImgs.isEmpty() && pmwInfoImgs.size() > 0) {
				PmwInfoimg pmwInfoImg = pmwInfoImgs.get(0);
				statiumInfos.setLng(Double.valueOf(pmwInfoImg.getLng()));
				statiumInfos.setLat(Double.valueOf(pmwInfoImg.getLat()));
			}
		} catch (Exception e) {
			logger.debug("道馆明细表不存在道馆信息，pmw老表查询:{}", e.getMessage());
		}

	}

	/**
	 * 
	 * <获取道馆list><功能具体实现>
	 *
	 * @create：2016年5月3日 下午2:22:10
	 * @author：sl
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<StatiumInfosVo> list(PageRequest pageRequest,
			Map<String, Object> searchParams) {
		int total = 0;
		List<StatiumInfosVo> voList = new ArrayList<StatiumInfosVo>();
		try {
			Map<String, SearchFilter> filters = SearchFilter
					.parse(searchParams);
			StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
			statiumInfosCriteria.setMysqlLength(pageRequest.getPageSize());
			statiumInfosCriteria.setMysqlOffset(pageRequest.getOffset());
			StatiumInfosCriteria.Criteria cri = statiumInfosCriteria
					.createCriteria();

			Criterias.bySearchFilter(cri, filters.values());
			String userRoleId=SessionUtil.currentUserRole();
			User user=SessionUtil.currentUser();
			if (userRoleId.equals("1002")){
				cri.andProvinceEqualTo(user.getProvince());
			}else if (userRoleId.equals("1003")){
				cri.andProvinceEqualTo(user.getProvince());
				cri.andCityEqualTo(user.getCity());
			}else if (userRoleId.equals("1004")){
				cri.andProvinceEqualTo(user.getProvince());
				cri.andCityEqualTo(user.getCity());
				cri.andAreaEqualTo(user.getArea());
			}
			statiumInfosCriteria.setOrderByClause("et desc");
			total = statiumInfosMapper.countByExample(statiumInfosCriteria);
			List<StatiumInfos> list = statiumInfosMapper
					.selectByExample(statiumInfosCriteria);
			for (StatiumInfos sc : list) {
				StatiumInfosVo InfosVo = new StatiumInfosVo();
				ConvertStatiumVo(InfosVo, sc);
				voList.add(InfosVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取道馆list(StatiumClassService--list)" + e.getMessage());
		}
		return new PageImpl<StatiumInfosVo>(voList, pageRequest, total);
	}

	private void ConvertStatiumVo(StatiumInfosVo InfosVo, StatiumInfos sc) {
		BeanUtils.copyProperties(sc, InfosVo);
		/*
		 * classVo.setFlPrice(sc.getPrice() / 100F); if(sc.getPrice()==0F){
		 * classVo.setFlDiscount(null); }else{
		 * classVo.setFlDiscount(sc.getDiscount() / 100F); }
		 * if(sc.getDiscountPrice()==0F){ classVo.setFlDiscountPrice(null);
		 * }else{ classVo.setFlDiscountPrice(sc.getDiscountPrice() / 100F); }
		 * if(sc.getLimitPrice()==0F){ classVo.setFlLimitPrice(null); }else{
		 * classVo.setFlLimitPrice(sc.getLimitPrice() / 100F); }
		 */
		InfosVo.setA3(sc.getAddress());
		InfosVo.setA5(sc.getTel());
		InfosVo.setA2(sc.getDgName());
		InfosVo.setCnname(sc.getContact());

	}
	
	public Page<SsoUser> findSsoUserList(PageRequest pageRequest,
			Map<String, Object> searchParams, boolean isPage, boolean isHasCount,Integer statiumId) {
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
			cri.andStatiumIdEqualTo(statiumId);
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
	
	public Page<SsoUser> findSsoUser(PageRequest pageRequest,
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
}
