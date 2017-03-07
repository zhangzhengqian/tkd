package com.lc.zy.ball.boss.framework.push.service;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.google.gson.reflect.TypeToken;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.carousel.service.CarouselService;
import com.lc.zy.ball.boss.framework.push.vo.PushVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.ActivityMapper;
import com.lc.zy.ball.domain.oa.mapper.EmsgServerMapper;
import com.lc.zy.ball.domain.oa.mapper.GamesMapper;
import com.lc.zy.ball.domain.oa.mapper.PushLogMapper;
import com.lc.zy.ball.domain.oa.mapper.PushMapper;
import com.lc.zy.ball.domain.oa.mapper.StatisticUserMobileMapper;
import com.lc.zy.ball.domain.oa.mapper.ex.PushMapperEx;
import com.lc.zy.ball.domain.oa.po.Activity;
import com.lc.zy.ball.domain.oa.po.ActivityCriteria;
import com.lc.zy.ball.domain.oa.po.EmsgServer;
import com.lc.zy.ball.domain.oa.po.EmsgServerCriteria;
import com.lc.zy.ball.domain.oa.po.Games;
import com.lc.zy.ball.domain.oa.po.GamesCriteria;
import com.lc.zy.ball.domain.oa.po.Push;
import com.lc.zy.ball.domain.oa.po.PushCriteria;
import com.lc.zy.ball.domain.oa.po.PushLog;
import com.lc.zy.ball.domain.oa.po.PushLogCriteria;
import com.lc.zy.ball.domain.oa.po.StatisticUserMobile;
import com.lc.zy.ball.domain.oa.po.StatisticUserMobileCriteria;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.ball.domain.oa.po.ex.PushEx;
import com.lc.zy.common.Constants;
import com.lc.zy.common.cache.RedisPool;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.exception.ServiceException;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.util.Zonemap;

/**
 * 素材管理
 * 
 * @author sl
 */
@Component
@Transactional(readOnly = false)
public class PushService extends AbstractCacheService {
	private static final Logger logger = LoggerFactory.getLogger(CarouselService.class);

	@Autowired
	private PushMapper pushMapper;

	@Autowired
	private PushMapperEx pushMapperEx;

	@Autowired
	private PushLogMapper pushLogMapper;

	@Autowired
	private StatisticUserMobileMapper statisticUserMobileMapper;

	@Autowired
	private EmsgServerMapper emsgServerMapper = null;

	@Autowired
	private RedisPool redisPool = null;

	@Autowired
	private ActivityMapper activityMapper;

	@Autowired
	private GamesMapper gamesMapper;

	/**
	 * 
	 * <保存/更新信息><功能具体实现>
	 *
	 * @create：2015年9月25日 上午11:27:07
	 * @author： sl
	 * @param f
	 */
	@Transactional(readOnly = false)
	public Push savePush(Push f) {
		try {
			// 当前用户
			User user = SessionUtil.currentUser();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date time = sdf.parse(sdf.format(new Date()));
			if (StringUtils.isNotBlank(f.getId())) {
				// 更新人
				f.setEb(user.getUserId());
				// 更新时间
				f.setEt(time);
				pushMapper.updateByPrimaryKeySelective(f);
			} else {
				// 类型 0:图文，"1":文字
				f.setType("0");
				// 排序值
				if (!"0".equals(f.getGroupId())) {
					int seq = countByPId(f.getGroupId());
					f.setSort(String.valueOf(seq + 2));
				} else {
					f.setSort("1");
				}
				// 创建人
				f.setCb(user.getUserId());
				// 创建时间
				f.setCt(time);
				// 更新人
				f.setEb(user.getUserId());
				// 更新时间
				f.setEt(time);
				String id = UUID.get();
				f.setId(id);
				pushMapper.insertSelective(f);
			}

		} catch (Exception e) {
			throw new ServiceException("保存or更新Push时发生异常", e);
		}
		return f;
	}

	/**
	 * 
	 * <根据id获取信息><功能具体实现>
	 *
	 * @create：2015年9月25日 上午11:30:06
	 * @author： sl
	 * @param fid
	 * @return
	 */
	@Transactional(readOnly = true)
	public Push getPush(String fid) {
		try {
			return pushMapper.selectByPrimaryKey(fid);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("查询Push时发生异常", e);
		}
	}

	/**
	 * 
	 * <功能描述><功能具体实现>
	 *
	 * @create：2015年9月25日 下午6:08:08
	 * @author： sl
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Push> findByPId(String id) {
		PushCriteria fc = new PushCriteria();
		PushCriteria.Criteria cri = fc.createCriteria();
		cri.andGroupIdEqualTo(id);

		fc.setOrderByClause("sort asc");

		return pushMapper.selectByExample(fc);
	}

	/**
	 * 
	 * <更具groupId查询数量><功能具体实现>
	 *
	 * @create：2015年9月25日 下午6:08:15
	 * @author： sl
	 * @param id
	 * @return
	 */
	public int countByPId(String id) {
		PushCriteria fc = new PushCriteria();
		PushCriteria.Criteria cri = fc.createCriteria();
		cri.andGroupIdEqualTo(id);
		return pushMapper.countByExample(fc);
	}

	public List<Push> findAll() {
		return pushMapper.selectByExample(null);
	}

	public List<PushEx> findExByPId(String pid) {
		return pushMapperEx.selectByPid(pid);
	}

	/**
	 * 
	 * <根据groupId获取素材><功能具体实现>
	 *
	 * @create：2015年10月12日 下午3:19:24
	 * @author： sl
	 * @param groupId
	 * @return
	 */
	public List<Push> pushListByGroupId(String groupId) {
		List<Push> pushList = new ArrayList<Push>();
		try {
			PushCriteria pushCriteria = new PushCriteria();
			PushCriteria.Criteria criteria = pushCriteria.createCriteria();
			criteria.andGroupIdEqualTo(groupId);
			pushList = pushMapper.selectByExample(pushCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("查询Push时发生异常", e);
		}
		return pushList;
	}

	/**
	 * 
	 * <获取素材><功能具体实现>
	 *
	 * @create：2015年10月12日 下午3:19:28
	 * @author： sl
	 * @param pageRequest
	 * @param groupId
	 * @param num
	 * @return
	 */
	public Page<PushVo> findPushList(PageRequest pageRequest, int num) {
		int total = 0;
		List<Push> pushList = new ArrayList<Push>();
		List<PushVo> pushVos = new ArrayList<PushVo>();
		try {
			PushCriteria pushCriteria = new PushCriteria();
			PushCriteria.Criteria criteria = pushCriteria.createCriteria();
			criteria.andGroupIdEqualTo("");
			pushCriteria.setOrderByClause("ct desc");
			int offSet = num;
			int length = num + 2;
			pushCriteria.setMysqlOffset(offSet);
			pushCriteria.setMysqlLength(length);
			total = pushMapper.countByExample(pushCriteria);
			pushList = pushMapper.selectByExample(pushCriteria);
			for (Push push : pushList) {
				PushVo pushVo = new PushVo();
				List<Push> pushs = pushListByGroupId(push.getId());
				pushVo.setChildPush(pushs);
				pushVo.setId(push.getId());
				pushVo.setImage(push.getImage());
				pushVo.setTitle(push.getTitle());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				pushVo.setPushCt(sdf.format(push.getCt()));
				pushVos.add(pushVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("查询Push时发生异常", e);
		}
		return new PageImpl<>(pushVos, pageRequest, total);
	}

	/**
	 * 
	 * <获取推送log><功能具体实现>
	 *
	 * @create：2015年10月21日 下午8:09:37
	 * @author： sl
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<PushVo> findPushLogList(PageRequest pageRequest, Map<String, Object> searchParams) {
		int total = 0;
		List<PushVo> list = new ArrayList<PushVo>();
		List<PushLog> pushLogs = new ArrayList<PushLog>();
		try {
			PushLogCriteria pushLogCriteria = new PushLogCriteria();
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			pushLogCriteria.setMysqlOffset(pageRequest.getOffset());
			pushLogCriteria.setMysqlLength(pageRequest.getPageSize());
			PushLogCriteria.Criteria criteria = pushLogCriteria.createCriteria();
			pushLogCriteria.setOrderByClause("fact_push_time desc");
			Criterias.bySearchFilter(criteria, filters.values());
			total = pushLogMapper.countByExample(pushLogCriteria);
			pushLogs = pushLogMapper.selectByExample(pushLogCriteria);
			for (PushLog pushLog : pushLogs) {
				PushVo pushVo = new PushVo();
				if (pushLog.getPushType() == 0) {
					pushVo.setType("专题");
					Push push = new Push();
					push = this.selectByPrimaryKey(Push.class, pushLog.getGroupId());
					pushVo.setTitle(push.getTitle());
					pushVo.setId(push.getId());
					pushVo.setImage(push.getImage());
				} else if (pushLog.getPushType() == 1) {
					pushVo.setType("活动");
					Activity activity = new Activity();
					activity = this.selectByPrimaryKey(Activity.class, pushLog.getGroupId());
					pushVo.setTitle(activity.getName());
					pushVo.setId(activity.getId());
					pushVo.setImage(activity.getPhoto());
				} else if (pushLog.getPushType() == 2) {
					pushVo.setType("赛事");
					Games game = new Games();
					game = this.selectByPrimaryKey(Games.class, pushLog.getGroupId());
					pushVo.setTitle(game.getName());
					pushVo.setId(game.getId());
					pushVo.setImage(game.getShowLogo());
				} else if (pushLog.getPushType() == 3) {
					pushVo.setType("文本");
					Push push = new Push();
					push = this.selectByPrimaryKey(Push.class, pushLog.getGroupId());
					pushVo.setTitle(push.getContent());
					pushVo.setId(push.getId());
				}
				pushVo.setPushTitle(pushLog.getPushTitle());
				pushVo.setPushRange(pushLog.getPushRange());
				pushVo.setPushDate(pushLog.getCt());
				pushVo.setLogId(pushLog.getId());
				pushVo.setIsPush(String.valueOf(pushLog.getIsPush()));
				pushVo.setpState(String.valueOf(pushLog.getPushState()));
				if (pushLog.getFactPushTime() != null){
					pushVo.setFactPushTime(DateUtil.formatDate(pushLog.getFactPushTime(), "yyyy-MM-dd HH:mm:ss"));
				}
				list.add(pushVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取推送loglist(PushService--findPushLogList)" + e.getMessage());
		}
		return new PageImpl<>(list, pageRequest, total);
	}

	/**
	 * 
	 * <获取素材信息><功能具体实现>
	 *
	 * @create：2015年10月22日 下午5:01:21
	 * @author： sl
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<PushVo> pushManageList(PageRequest pageRequest, Map<String, Object> searchParams) {
		int total = 0;
		List<PushVo> listVo = new ArrayList<PushVo>();
		List<Push> pushList = new ArrayList<Push>();
		try {
			PushCriteria pushCriteria = new PushCriteria();
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			pushCriteria.setMysqlOffset(pageRequest.getOffset());
			pushCriteria.setMysqlLength(pageRequest.getPageSize());
			PushCriteria.Criteria criteria = pushCriteria.createCriteria();
			criteria.andGroupIdEqualTo("");
			pushCriteria.setOrderByClause("et desc");
			Criterias.bySearchFilter(criteria, filters.values());
			total = pushMapper.countByExample(pushCriteria);
			pushList = pushMapper.selectByExample(pushCriteria);
			for (Push push : pushList) {
				PushVo pushVo = new PushVo();
				List<Push> pushs = pushListByGroupId(push.getId());
				pushVo.setChildPush(pushs);
				pushVo.setId(push.getId());
				pushVo.setImage(push.getImage());
				pushVo.setTitle(push.getTitle());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				pushVo.setPushCt(sdf.format(push.getEt()));
				listVo.add(pushVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取素材list(PushService--pushManageList)" + e.getMessage());
		}
		return new PageImpl<>(listVo, pageRequest, total);
	}

	/**
	 * 
	 * <删除素材list><功能具体实现>
	 *
	 * @create：2015年10月22日 下午5:39:16
	 * @author： sl
	 * @param sourceId
	 */
	@Transactional(readOnly = false)
	public Map<String, List<Push>> delete(String sourceId) {
		Map<String, List<Push>> pMap = new HashMap<String, List<Push>>();
		try {
			Push push = this.selectByPrimaryKey(Push.class, sourceId);
			this.deleteByPrimaryKey(Push.class, sourceId);
			// 判断是否是全部删除
			if (StringUtils.isBlank(push.getGroupId())) {
				// 删除子
				PushCriteria pushCriteria = new PushCriteria();
				PushCriteria.Criteria criteria = pushCriteria.createCriteria();
				criteria.andGroupIdEqualTo(sourceId);
				pushMapper.deleteByExample(pushCriteria);
			} else {
				pMap = getPushByPid(push.getGroupId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除素材list(PushService--delete)" + e.getMessage());
		}
		return pMap;
	}

	/**
	 * 
	 * <删除素材组><功能具体实现>
	 *
	 * @create：2015年12月1日 上午9:36:53
	 * @author： sl
	 * @param sourceId
	 */
	@Transactional(readOnly = false)
	public void deleteSourceGroup(String sourceId) {
		try {
			this.deleteByPrimaryKey(Push.class, sourceId);
			PushCriteria pushCriteria = new PushCriteria();
			PushCriteria.Criteria criteria = pushCriteria.createCriteria();
			criteria.andGroupIdEqualTo(sourceId);
			pushMapper.deleteByExample(pushCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除素材组(PushService--deleteSourceGroup)" + e.getMessage());
		}
	}

	/**
	 * 
	 * <获取推送群体><功能具体实现>
	 *
	 * @create：2015年10月23日 下午5:06:38
	 * @author： sl
	 * @param sex
	 * @param city
	 * @param province
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<StatisticUserMobile> getPushUsers(String city, String province) {
		List<StatisticUserMobile> ssoUsers = new ArrayList<StatisticUserMobile>();
		try {
			StatisticUserMobileCriteria statisticUserMobileCriteria = new StatisticUserMobileCriteria();
			StatisticUserMobileCriteria.Criteria criteria = statisticUserMobileCriteria.createCriteria();
			if (StringUtils.isNotBlank(city)) {
				criteria.andCityLike(city);
			}
			if (StringUtils.isNotBlank(province)) {
				criteria.andProvinceLike(province);
			}
			criteria.andQiuyounoEqualTo("10004323");
			ssoUsers = statisticUserMobileMapper.selectByExample(statisticUserMobileCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取推送群体(PushService--getPushUsers)" + e.getMessage());
		}
		return ssoUsers;
	}

	/**
	 * 
	 * <保存推送的文本信息><功能具体实现>
	 *
	 * @create：2015年10月23日 下午5:37:16
	 * @author： sl
	 * @param content
	 */
	@Transactional(readOnly = false)
	public String saveText(String content) {
		// 当前用户
		User user = SessionUtil.currentUser();
		String id = UUID.get();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date time = sdf.parse(sdf.format(new Date()));
			Push push = new Push();
			// 主键id
			push.setId(id);
			// 内容
			push.setContent(content);
			// 类型 0:图文，"1":文字
			push.setType("1");
			// 创建人
			push.setCb(user.getUserId());
			// 创建时间
			push.setCt(time);
			// 更新人
			push.setEb(user.getUserId());
			// 更新时间
			push.setEt(time);
			// 保存
			this.insertSelective(push, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存推送的文本信息(PushService--saveText)" + e.getMessage());
		}
		return id;
	}

	/**
	 * 
	 * <推送专题日志保存><功能具体实现>
	 *
	 * @create：2015年10月23日 下午5:46:37
	 * @author： sl
	 * @param pushId
	 * @param pushRange
	 */
	@Transactional(readOnly = false)
	public void savePushLog(String pushId, String pushRange, int pushType, String pushTitle, String pushTime) {
		String id = UUID.get();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date time = sdf.parse(sdf.format(new Date()));
			PushLog pushLog = new PushLog();
			// 主键id
			pushLog.setId(id);
			// 推送范围
			Map<String, String> areaMap = Zonemap.split(pushRange);
			String province = areaMap.get("province");
			String city = areaMap.get("city");
			if (StringUtils.isNotEmpty(city)) {
				if ("市辖区".equals(city)) {
					city = province.substring(0, province.length() - 1);
				} else {
					city = city.substring(0, city.length() - 1);
				}
			}
			if (StringUtils.isNotEmpty(province)) {
				province = province.substring(0, province.length() - 1);
			}
			String pRange = "";
			if (StringUtils.isNotEmpty(city)) {
				pRange = province + "," + city;
			} else {
				pRange = province;
			}
			pushLog.setPushRange(pRange);
			// 推送专题id
			pushLog.setGroupId(pushId);
			// 推送日期
			pushLog.setCt(time);
			// 推送类型
			pushLog.setPushType(pushType);
			// 推送标题
			pushLog.setPushTitle(pushTitle);
			// 推送人
			pushLog.setCb(SessionUtil.currentUser().getUserId());
			// 推送状态
			pushLog.setPushState(0);
			// 定时推送日期
			pushLog.setPushTime(pushTime);
			// 推送类型(定时)
			pushLog.setIsPush(Constants.isPush.PUSH_RYPE_PUNLIMIT);
			pushLog.setAreaCode(pushRange);

			// 保存
			this.insertSelective(pushLog, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("推送专题日志保存(PushService--savePushLog)" + e.getMessage());
		}
	}

	/**
	 * 
	 * <根据父id获取所有相关专题><功能具体实现>
	 *
	 * @create：2015年11月27日 下午6:55:03
	 * @author： sl
	 * @param pid
	 * @return
	 */
	public Map<String, List<Push>> getPushByPid(String pid) {
		Map<String, List<Push>> pMap = new HashMap<String, List<Push>>();
		List<Push> pList = new ArrayList<Push>();
		List<Push> cList = new ArrayList<Push>();
		try {
			// 父
			Push pPush = this.selectByPrimaryKey(Push.class, pid);
			pList.add(pPush);
			pMap.put("pPush", pList);
			// 子
			PushCriteria pushCriteria = new PushCriteria();
			PushCriteria.Criteria criteria = pushCriteria.createCriteria();
			criteria.andGroupIdEqualTo(pid);
			pushCriteria.setOrderByClause("et asc");
			cList = pushMapper.selectByExample(pushCriteria);
			pMap.put("cPush", cList);
		} catch (Exception e) {
			logger.debug("根据父id获取所有相关专题", e.getMessage());
		}
		return pMap;
	}

	/**
	 * 
	 * <删除><功能具体实现>
	 *
	 * @create：2015年11月27日 下午7:50:15
	 * @author： sl
	 * @param id
	 * @return
	 */
	public Map<String, List<Push>> getPushBydeleteId(String id) {
		Map<String, List<Push>> pMap = new HashMap<String, List<Push>>();
		try {
			Push push = this.selectByPrimaryKey(Push.class, id);
			if (StringUtils.isNotBlank(push.getGroupId())) {
				pMap = getPushByPid(push.getGroupId());
			}
		} catch (Exception e) {
			logger.debug("删除", e.getMessage());
		}
		return pMap;
	}

	/**
	 * 
	 * <获取emsg_server><功能具体实现>
	 *
	 * @create：2015年12月1日 下午1:42:19
	 * @author： sl
	 * @return
	 */
	public EmsgServer getEmsgServer() {
		Type typeToken = new TypeToken<EmsgServer>() {
		}.getType();
		String server = redisPool.getStr(com.lc.zy.common.Constants.EMSG_SERVER);
		EmsgServer emsgServer = MyGson.getInstance().fromJson(server, typeToken);
		if (emsgServer == null) {
			EmsgServerCriteria emsgServerCriteria = new EmsgServerCriteria();
			List<EmsgServer> emsgServerList = emsgServerMapper.selectByExample(emsgServerCriteria);
			if (CollectionUtils.isNotEmpty(emsgServerList)) {
				emsgServer = emsgServerList.get(0);
				emsgServer.setCb(null);
				emsgServer.setCt(null);
				redisPool.setStr(com.lc.zy.common.Constants.EMSG_SERVER, MyGson.getInstance().toJson(emsgServer));
				return emsgServer;
			} else {
				return null;
			}
		} else {
			return emsgServer;
		}
	}

	/**
	 * 
	 * <根据id删除推送记录><功能具体实现>
	 *
	 * @create：2015年12月1日 下午2:46:50
	 * @author： sl
	 * @param id
	 */
	public void deleteById(String id) {
		try {
			this.deleteByPrimaryKey(PushLog.class, id);
		} catch (Exception e) {
			logger.debug("根据id删除推送记录", e.getMessage());
		}
	}

	/**
	 * 
	 * <获得活动信息><功能具体实现>
	 *
	 * @create：2015年12月2日 下午4:15:59
	 * @author： sl
	 * @param pageRequest
	 * @param num
	 * @param areaCode
	 * @return
	 */
	public Page<Activity> findActivityList(PageRequest pageRequest, int num, String areaCode) {
		int total = 0;
		List<Activity> activityList = new ArrayList<Activity>();
		try {
			Map<String, String> areaMap = Zonemap.split(areaCode);
			String province = areaMap.get("province");
			String city = areaMap.get("city");

			ActivityCriteria activityCriteria = new ActivityCriteria();
			ActivityCriteria.Criteria criteria = activityCriteria.createCriteria();
			criteria.andCityEqualTo(city);
			criteria.andProvinceEqualTo(province);
			activityCriteria.setOrderByClause("update_time desc");
			int offSet = num;
			int length = num + 2;
			activityCriteria.setMysqlOffset(offSet);
			activityCriteria.setMysqlLength(length);
			total = activityMapper.countByExample(activityCriteria);
			activityList = activityMapper.selectByExample(activityCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("查询Push时发生异常", e);
		}
		return new PageImpl<>(activityList, pageRequest, total);
	}

	/**
	 * 
	 * <活动赛事信息><功能具体实现>
	 *
	 * @create：2015年12月2日 下午4:19:24
	 * @author： sl
	 * @param pageRequest
	 * @param num
	 * @param areaCode
	 * @return
	 */
	public Page<Games> findEventList(PageRequest pageRequest, int num, String areaCode) {
		int total = 0;
		List<Games> eventList = new ArrayList<Games>();
		try {
			Map<String, String> areaMap = Zonemap.split(areaCode);
			String province = areaMap.get("province");
			String city = areaMap.get("city");

			GamesCriteria gamesCriteria = new GamesCriteria();
			GamesCriteria.Criteria criteria = gamesCriteria.createCriteria();
			criteria.andProvinceEqualTo(province);
			criteria.andCityEqualTo(city);
			gamesCriteria.setOrderByClause("et desc");
			int offSet = num;
			int length = num + 2;
			gamesCriteria.setMysqlOffset(offSet);
			gamesCriteria.setMysqlLength(length);
			total = gamesMapper.countByExample(gamesCriteria);
			eventList = gamesMapper.selectByExample(gamesCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("查询Push时发生异常", e);
		}
		return new PageImpl<>(eventList, pageRequest, total);
	}

	/**
	 * 
	 * <更新活动推送方式--定时推送><功能具体实现>
	 *
	 * @create：2015年12月16日 上午10:31:20
	 * @author： sl
	 * @param activityId
	 * @param pType
	 * @param pushTime
	 */
	public void updateActivity(String activityId, String pType, String pushTime) {
		try {
			Activity activity = new Activity();
			activity.setId(activityId);
			activity.setIsPush(Integer.valueOf(pType));
			activity.setPushTime(pushTime);
			this.updateByPrimaryKeySelective(activity, activityId);
		} catch (Exception e) {
			logger.debug("更新活动推送方式--定时推送", e.getMessage());
		}

	}

	/**
	 * 
	 * <更新赛事推送方式--定时推送><功能具体实现>
	 *
	 * @create：2015年12月16日 上午10:31:03
	 * @author： sl
	 * @param eventId
	 * @param pType
	 * @param pushTime
	 */
	public void updateEvent(String eventId, String pType, String pushTime) {
		try {
			Games games = new Games();
			games.setId(eventId);
			games.setIsPush(Integer.valueOf(pType));
			games.setPushTime(pushTime);
			this.updateByPrimaryKeySelective(games, eventId);
		} catch (Exception e) {
			logger.debug("更新赛事推送方式--定时推送", e.getMessage());
		}

	}

}
