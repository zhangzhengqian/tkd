package com.lc.zy.ball.boss.framework.event.service;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import com.google.gson.reflect.TypeToken;
import com.lc.zy.ball.boss.common.pay.wxap.util.MD5Util;
import com.lc.zy.ball.domain.oa.mapper.*;
import com.lc.zy.ball.domain.oa.po.*;
import com.lc.zy.common.web.EnjoyGameSiteManage;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.Zonemap;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.event.vo.EnjoyGameSiteVo;
import com.lc.zy.ball.boss.framework.event.vo.EnjoyGameVo;
import com.lc.zy.ball.boss.framework.event.vo.EnjoyMemberVo;
import com.lc.zy.ball.boss.framework.event.vo.ExportPlayer;
import com.lc.zy.ball.boss.framework.event.vo.GameScheduleVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.common.data.pageable.Pageable;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.gamegroup.GameDraw;
import com.lc.zy.common.gamegroup.ScheduleArrange;
import com.lc.zy.common.gamegroup.bean.GamePoint;
import com.lc.zy.common.gamegroup.bean.GamesScheduleModel;
import com.lc.zy.common.gamegroup.bean.GroupGameVo;
import com.lc.zy.common.gamegroup.bean.PlayerInfo;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.FreeMarkerUtils;
import com.lc.zy.common.util.MessageUtil;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.zoo.SEQGenerate;

@Service
@Transactional(readOnly = true)
public class EnjoyRaceService extends AbstractCacheService {

	private static final Logger logger = LoggerFactory.getLogger(EnjoyRaceService.class);

	private final static String SPLIT = "\01";

	@Autowired
	private EnjoyGameMapper enjoyGameMapper;

	@Autowired
	private EnjoyMemberMapper enjoyMemberMapper;

	@Autowired
	private GamesScoreMapper gamesScoreMapper;

	@Autowired
	private EnjoyGroupScheduleMapper enjoyGroupScheduleMapper;

	@Autowired
	private EnjoyGroupMapper enjoyGroupMapper;

	@Resource(name = "oaJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PushLogMapper pushLogMapper;

	@Autowired
	private EnjoyGameSiteMapper enjoyGameSiteMapper;

	@Autowired
	private SEQGenerate seqGenerate;

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private QiuyouCardLogMapper qiuyouCardLogMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private EnjoyEliminationMapper eliminationMapper;

	@Autowired
	private StatiumAuditLogMapper statiumAuditLogMapper;

	@Autowired
	private GamesPlayerMapper gamesPlayerMapper;

	@Autowired
	private SsoUserMapper ssoUserMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderLogMapper orderLogMapper;

	@Autowired
	private EnjoyGameSiteManage enjoyGameSiteManage;

	/**
	 *
	 * <乐享赛列表><功能具体实现>
	 *
	 * @param pageable
	 * @param searchParams
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年2月18日 下午3:00:27
	 */
	public PageImpl<EnjoyGameVo> list(Pageable pageable, Map<String, Object> searchParams, boolean isPage)
			throws Exception {
		logger.debug(searchParams.toString());
		// 包含置顶比赛方式是比赛
		List<String> idList = new ArrayList<>();
		String gameType = searchParams.get("EQ_gameType") != null ? searchParams.get("EQ_gameType").toString() : "";
		if (gameType != "") {
			searchParams.remove("EQ_gameType");
			EnjoyGameCriteria gameCriteria = new EnjoyGameCriteria();
			EnjoyGameCriteria.Criteria gameCri = gameCriteria.createCriteria();
			gameCri.andGameTypeEqualTo(Integer.valueOf(gameType));
			gameCri.andDeleteFlagEqualTo(0);
			gameCri.andStateIsNotNull();
			if (enjoyGameMapper.countByExample(gameCriteria) > 0) {
				for (EnjoyGame tmp : enjoyGameMapper.selectByExample(gameCriteria)) {
					if (org.apache.commons.lang.StringUtils.isEmpty(tmp.getGid())) {
						idList.add(tmp.getId());
					} else {
						idList.add(tmp.getGid());
					}
				}
			}
		}

		List<EnjoyGameVo> voList = new ArrayList<EnjoyGameVo>();
		EnjoyGameCriteria criteria = new EnjoyGameCriteria();
		EnjoyGameCriteria.Criteria cri = criteria.createCriteria();
		cri.andDeleteFlagEqualTo(0);
		cri.andStateIsNotNull();
		cri.andGidIsNull(); // 多个项目的赛事只查询一条
		if (CollectionUtils.isNotEmpty(idList)) {
			cri.andIdIn(idList);
		}
		String city = searchParams.get("LIKE_city") != null ? searchParams.get("LIKE_city").toString() : null;
		if (searchParams.get("LIKE_city") != null
				&& (city.contains("上海") || city.contains("北京") || city.contains("天津") || city.contains("重庆"))) {
			searchParams.put("LIKE_province", city);
			searchParams.remove("LIKE_city");
		}
		if (searchParams != null) {
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			Criterias.bySearchFilter(cri, filters.values());
		}
		if (isPage) {
			criteria.setMysqlLength(pageable.getPageSize());
			criteria.setMysqlOffset(pageable.getOffset());
		}
		criteria.setOrderByClause("stick desc,start_time desc");
		List<EnjoyGame> list = enjoyGameMapper.selectByExample(criteria);
		int total = enjoyGameMapper.countByExample(criteria);
		if (CollectionUtils.isEmpty(list)) {
			list = new ArrayList<EnjoyGame>();
		} else {
			for (EnjoyGame enjoyGame : list) {
				EnjoyGameVo vo = new EnjoyGameVo();
				BeanUtils.copyProperties(enjoyGame, vo);
				switch (vo.getGameLevel()) {
				case 1:
					vo.setGameLevelName("乐享一级");
					break;
				case 2:
					vo.setGameLevelName("乐享二级");
					break;
				case 3:
					vo.setGameLevelName("乐享三级");
					break;
				case 4:
					vo.setGameLevelName("乐享四级");
					break;
				default:
					vo.setGameLevelName("");
					break;
				}
				switch (vo.getGameType()) {
				case 1:
					vo.setGameTypeName("男单");
					break;
				case 2:
					vo.setGameTypeName("女单");
					break;
				case 3:
					vo.setGameTypeName("男双");
					break;
				case 4:
					vo.setGameTypeName("女双");
					break;
				case 5:
					vo.setGameTypeName("混双");
					break;
				case 6:
					vo.setGameTypeName("混单");
					break;
				case 7:
					vo.setGameTypeName("无性别限制双打");
					break;
				default:
					vo.setGameTypeName("");
					break;
				}
				switch (vo.getIsScoreNotice()) {
				case 0:
					vo.setIsScoreNoticeName("未发布公告");
					break;
				case 1:
					vo.setIsScoreNoticeName("已发布公告");
					break;
				case 2:
					vo.setIsScoreNoticeName("待发布公告");
					break;
				default:
					vo.setIsScoreNoticeName("");
					break;
				}
				if (vo.getCityHoldTimes() > 0) {
					vo.setCityHoldTimesName(vo.getProvince() + "-" + vo.getCity() + "第" + vo.getCityHoldTimes() + "站");
				} else {
					vo.setCityHoldTimesName(vo.getProvince() + "-" + vo.getCity());
				}
				switch (vo.getState()) {
				case 0:
					vo.setStateName("待审核");
					break;
				case 1:
					vo.setStateName("通过");
					break;
				case 2:
					vo.setStateName("不通过");
					break;
				default:
					vo.setStateName("");
					break;
				}
				switch (vo.getIsOffical()) {
				case 0:
					vo.setIsOfficalName("OSS发布");
					break;
				case 1:
					vo.setIsOfficalName("CRM发布");
					break;
				default:
					vo.setIsOfficalName("");
					break;
				}
				logger.debug("enjoyGame={}", enjoyGame.toString());
				logger.debug("vo={}", vo.toString());
				vo.setGamesLength(0);
				EnjoyGameCriteria c2 = new EnjoyGameCriteria();
				EnjoyGameCriteria.Criteria cri2 = c2.createCriteria();
				cri2.andDeleteFlagEqualTo(0);
				cri2.andGidEqualTo(vo.getId());
				c2.setOrderByClause("game_type");
				int total2 = enjoyGameMapper.countByExample(c2);
				List<EnjoyGame> list2 = new ArrayList<EnjoyGame>();
				List<EnjoyGameVo> list2Export = new ArrayList<EnjoyGameVo>();
				if (total2 > 0) {
					list2 = enjoyGameMapper.selectByExample(c2);
					for (EnjoyGame game_ : list2) {
						EnjoyGameVo exportGame = new EnjoyGameVo();
						BeanUtils.copyProperties(game_, exportGame);
						switch (exportGame.getGameType()) {
						case 1:
							exportGame.setGameTypeName("男单");
							break;
						case 2:
							exportGame.setGameTypeName("女单");
							break;
						case 3:
							exportGame.setGameTypeName("男双");
							break;
						case 4:
							exportGame.setGameTypeName("女双");
							break;
						case 5:
							exportGame.setGameTypeName("混双");
							break;
						case 6:
							exportGame.setGameTypeName("混单");
							break;
						case 7:
							exportGame.setGameTypeName("无性别限制双打");
							break;
						default:
							exportGame.setGameTypeName("");
							break;
						}
						switch (exportGame.getIsScoreNotice()) {
						case 0:
							exportGame.setIsScoreNoticeName("未发布公告");
							break;
						case 1:
							exportGame.setIsScoreNoticeName("已发布公告");
							break;
						case 2:
							exportGame.setIsScoreNoticeName("待发布公告");
							break;
						default:
							exportGame.setIsScoreNoticeName("");
							break;
						}

						EnjoyMemberCriteria criteria_ = new EnjoyMemberCriteria();
						EnjoyMemberCriteria.Criteria cri_ = criteria_.createCriteria();
						cri_.andGameIdEqualTo(game_.getId());
						cri_.andUserIdIsNotNull();
						cri_.andStateEqualTo(1);
						int total_ = enjoyMemberMapper.countByExample(criteria_);
						game_.setApplicantNumber(total_);
						exportGame.setApplicantNumber(total_);
						list2Export.add(exportGame);
					}
					vo.setGameVoList(list2Export);
					vo.setGames(list2);
					vo.setGamesLength(total2);
				}
				// 设置赛事状态
				Date endTime = DateUtils.getDate(vo.getEndTime(), DateUtils.DEFAULT_DATETIME2_FORMAT);
				Date startTime = DateUtils.getDate(vo.getStartTime(), DateUtils.DEFAULT_DATETIME2_FORMAT);
				Date expiryDate = DateUtils.getDate(vo.getExpiryDate(), DateUtils.DEFAULT_DATETIME2_FORMAT);
				Date now = new Date();
				// 报名状态:0已结束1进行中2报名中3报名截止
				if (now.compareTo(endTime) >= 0) {
					vo.setStateValue("已结束");
					vo.setStatus(3);
				} else if (now.compareTo(startTime) >= 0) {
					vo.setStateValue("进行中");
					vo.setStatus(1);
				} else if (now.compareTo(expiryDate) < 0) {
					EnjoyMemberCriteria memberCriteria = new EnjoyMemberCriteria();
					EnjoyMemberCriteria.Criteria memberCri = memberCriteria.createCriteria();
					if (CollectionUtils.isNotEmpty(list2)) {
						StringBuilder idBd = new StringBuilder("");
						for (EnjoyGame g : list2) {
							idBd.append(g.getId()).append(",");
						}
						memberCri.andGameIdIn(Arrays.asList(idBd.append(vo.getId()).toString()));
					} else {
						memberCri.andGameIdEqualTo(vo.getId());
					}
					if (enjoyMemberMapper.countByExample(memberCriteria) > 0) {
						vo.setStateValue("报名中");
						vo.setStatus(2);
					} else {
						vo.setStateValue("");
						vo.setStatus(-1);
					}
				} else if (now.compareTo(expiryDate) >= 0) {
					vo.setStateValue("报名截止");
					vo.setStatus(3);
				}
				boolean isPauseFlag = true;
				for (EnjoyGame tmp : enjoyGameMapper.selectByExample(criteria)) {
					if (tmp.getPauseFlag() == null || tmp.getPauseFlag() == 0) {
						isPauseFlag = false;
						break;
					}
				}
				if (isPauseFlag) {
					vo.setStateValue("已暂停");
					vo.setStatus(6);
				}
				if (vo.getDeleteFlag() == 2) {
					vo.setStateValue("已取消");
					vo.setStatus(5);
				}

				if (StringUtils.isNotBlank(vo.getSiteId())) {
					EnjoyGameSite site = this.selectByPrimaryKey(EnjoyGameSite.class, vo.getSiteId());
					if (site != null) {
						if (site.getCity().equals("市辖区")) {
							vo.setSiteName(site.getProvince());
						} else {
							vo.setSiteName(site.getProvince() + " " + site.getCity());
						}
					}
				}
				StatiumDetail statiumDetail = this.selectByPrimaryKey(StatiumDetail.class, vo.getStatiumId());
				if (statiumDetail != null) {
					vo.setStatiumName(statiumDetail.getName());
				}

				EnjoyMemberCriteria criteria_ = new EnjoyMemberCriteria();
				EnjoyMemberCriteria.Criteria cri_ = criteria_.createCriteria();
				cri_.andGameIdEqualTo(vo.getId());
				cri_.andUserIdIsNotNull();
				cri_.andStateEqualTo(1);
				int total_ = enjoyMemberMapper.countByExample(criteria_);

				vo.setApplicantNumber(total_);
				// ((gameFormat == 1 && gameState == 2)||(gameFormat != 1 &&
				// gameState==1))
				// && isScoreNotice != 1
				// && （(isOffical == 1 && isScoreNotice == 2) || isOffical == 0
				// ））
				if (vo.getGameFormat() == null) {
					vo.setIsScoreNotice(0); // 未发布公告
				} else if (vo.getGameState() == null) {
					vo.setIsScoreNotice(0); // 未发布公告
				} else if (((vo.getGameFormat() == 1 && vo.getGameState() == 2)
						|| (vo.getGameFormat() != 1 && vo.getGameState() == 1)) && vo.getIsScoreNotice() != 1
						&& ((vo.getIsOffical() == 1 && vo.getIsScoreNotice() == 2) || vo.getIsOffical() == 0)) {
					vo.setIsScoreNotice(2); // 待发布公告
				} else if (vo.getIsScoreNotice() == 1) {
					vo.setIsScoreNotice(1); // 已发布公告
				} else {
					vo.setIsScoreNotice(0); // 未发布公告
				}
				voList.add(vo);
			}
		}
		return new PageImpl<>(voList, pageable, total);
	}

	/**
	 *
	 * <奖金管理列表><功能具体实现>
	 * 
	 * @param pageable
	 * @param searchParams
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年4月11日 下午7:37:26
	 */
	public PageImpl<EnjoyGameVo> bonusList(Pageable pageable, Map<String, Object> searchParams, boolean isPage)
			throws Exception {
		logger.debug(searchParams.toString());
		List<EnjoyGameVo> voList = new ArrayList<EnjoyGameVo>();

		// 包含置顶比赛方式是比赛
		List<String> idList = new ArrayList<>();
		String gameType = searchParams.get("EQ_gameType") != null ? searchParams.get("EQ_gameType").toString() : "";
		if (gameType != "") {
			searchParams.remove("EQ_gameType");
			EnjoyGameCriteria gameCriteria = new EnjoyGameCriteria();
			EnjoyGameCriteria.Criteria gameCri = gameCriteria.createCriteria();
			gameCri.andGameTypeEqualTo(Integer.valueOf(gameType));
			gameCri.andDeleteFlagEqualTo(0);
			gameCri.andStateIsNotNull();
			if (enjoyGameMapper.countByExample(gameCriteria) > 0) {
				for (EnjoyGame tmp : enjoyGameMapper.selectByExample(gameCriteria)) {
					if (org.apache.commons.lang.StringUtils.isEmpty(tmp.getGid())) {
						idList.add(tmp.getId());
					} else {
						idList.add(tmp.getGid());
					}
				}
			}
		}

		EnjoyGameCriteria criteria = new EnjoyGameCriteria();
		EnjoyGameCriteria.Criteria cri = criteria.createCriteria();
		if (searchParams != null) {
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			Criterias.bySearchFilter(cri, filters.values());
		}
		if (isPage) {
			criteria.setMysqlLength(pageable.getPageSize());
			criteria.setMysqlOffset(pageable.getOffset());
		}
		criteria.setOrderByClause("start_time desc");
		cri.andIsScoreNoticeEqualTo(1);
		if (CollectionUtils.isNotEmpty(idList)) {
			cri.andIdIn(idList);
		}
		List<EnjoyGame> list = enjoyGameMapper.selectByExample(criteria);
		int total = enjoyGameMapper.countByExample(criteria);
		if (CollectionUtils.isEmpty(list)) {
			list = new ArrayList<EnjoyGame>();
		} else {
			for (EnjoyGame enjoyGame : list) {
				EnjoyGameVo vo = new EnjoyGameVo();
				BeanUtils.copyProperties(enjoyGame, vo);
				if (StringUtils.isNotBlank(vo.getGid())) {
					EnjoyGame tmp = this.selectByPrimaryKey(EnjoyGame.class, vo.getGid());
					if (tmp != null) {
						vo.setName(tmp.getName());
						vo.setGameLevel(tmp.getGameLevel());
						vo.setSiteId(tmp.getSiteId());
						vo.setStatiumId(tmp.getStatiumId());
					}
				}
				switch (vo.getGameLevel()) {
				case 1:
					vo.setGameLevelName("乐享一级");
					break;
				case 2:
					vo.setGameLevelName("乐享二级");
					break;
				case 3:
					vo.setGameLevelName("乐享三级");
					break;
				case 4:
					vo.setGameLevelName("乐享四级");
					break;
				default:
					vo.setGameLevelName("");
					break;
				}
				switch (vo.getGameType()) {
				case 1:
					vo.setGameTypeName("男单");
					break;
				case 2:
					vo.setGameTypeName("女单");
					break;
				case 3:
					vo.setGameTypeName("男双");
					break;
				case 4:
					vo.setGameTypeName("女双");
					break;
				case 5:
					vo.setGameTypeName("混双");
					break;
				case 6:
					vo.setGameTypeName("混单");
					break;
				case 7:
					vo.setGameTypeName("无性别限制双打");
					break;
				default:
					vo.setGameTypeName("");
					break;
				}
				switch (vo.getIsIssueBonus()) {
				case 0:
					vo.setIsIssueBonusName("未发放");
					break;
				case 1:
					vo.setIsIssueBonusName("已发放");
					break;
				case 2:
					vo.setIsIssueBonusName("发放中");
					break;
				default:
					vo.setIsIssueBonusName("");
					break;
				}
				if (StringUtils.isNotBlank(vo.getSiteId())) {
					EnjoyGameSite site = this.selectByPrimaryKey(EnjoyGameSite.class, vo.getSiteId());
					if (site != null) {
						vo.setSiteName(site.getProvince() + " " + site.getCity());
					}
				}
				StatiumDetail statiumDetail = this.selectByPrimaryKey(StatiumDetail.class, vo.getStatiumId());
				if (statiumDetail != null) {
					vo.setStatiumName(statiumDetail.getName());
				}

				// 设置整个赛事某一个项目的奖金发放状态 stateValue
				// 所有参赛人数
				EnjoyMemberCriteria memberC = new EnjoyMemberCriteria();
				EnjoyMemberCriteria.Criteria memCri = memberC.createCriteria();
				memCri.andGameIdEqualTo(vo.getId()); // 赛事id
				memCri.andStateEqualTo(1); // 审核通过的人员
				memCri.andUserIdIsNotNull();
				memCri.andBonusGreaterThan(0);

				int countAll = enjoyMemberMapper.countByExample(memberC);
				// 已分配未审核人数
				EnjoyMemberCriteria memberC0 = new EnjoyMemberCriteria();
				EnjoyMemberCriteria.Criteria memCri0 = memberC0.createCriteria();
				memCri0.andGameIdEqualTo(vo.getId()); // 赛事id
				memCri0.andStateEqualTo(1); // 审核通过的人员
				memCri0.andUserIdIsNotNull();
				memCri0.andBonusGreaterThan(0);
				memCri0.andIsIssueBonusEqualTo(0);
				int count0 = enjoyMemberMapper.countByExample(memberC0);
				// 全部发放的人数
				EnjoyMemberCriteria memberC1 = new EnjoyMemberCriteria();
				EnjoyMemberCriteria.Criteria memCri1 = memberC1.createCriteria();
				memCri1.andGameIdEqualTo(vo.getId()); // 赛事id
				memCri1.andStateEqualTo(1); // 审核通过的人员
				memCri1.andUserIdIsNotNull();
				memCri1.andIsIssueBonusEqualTo(1);
				memCri0.andBonusGreaterThan(0);
				int count1 = enjoyMemberMapper.countByExample(memberC1);
				if (countAll == count0) {
					vo.setStateValue("未发放");
					vo.setState(0);
				} else if (countAll == count1) {
					vo.setStateValue("已发放");
					vo.setState(1);
				} else {
					vo.setStateValue("发放中");
					vo.setState(2);
				}
				voList.add(vo);
			}
		}
		return new PageImpl<>(voList, pageable, total);
	}

	/**
	 *
	 * <乐享赛列表><功能具体实现>
	 *
	 * @param pageable
	 * @param searchParams
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年2月18日 下午3:00:27
	 */
	public PageImpl<EnjoyGameSiteVo> siteList(Pageable pageable, Map<String, Object> searchParams) throws Exception {
		logger.debug(searchParams.toString());
		List<EnjoyGameSiteVo> voList = new ArrayList<EnjoyGameSiteVo>();
		EnjoyGameSiteCriteria criteria = new EnjoyGameSiteCriteria();
		EnjoyGameSiteCriteria.Criteria cri = criteria.createCriteria();
		if (searchParams != null) {
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			Criterias.bySearchFilter(cri, filters.values());
		}
		cri.andStatusNotEqualTo(3);
		criteria.setMysqlLength(pageable.getPageSize());
		criteria.setMysqlOffset(pageable.getOffset());
		criteria.setOrderByClause("province desc,ct desc");
		List<EnjoyGameSite> list = enjoyGameSiteMapper.selectByExample(criteria);
		int total = enjoyGameSiteMapper.countByExample(criteria);
		if (CollectionUtils.isEmpty(list)) {
			list = new ArrayList<EnjoyGameSite>();
		} else {
			String sqlList = FreeMarkerUtils.format("/template/game/site.ftl", null);
			List<Map<String, Object>> cityList = jdbcTemplate.queryForList(sqlList);
			Map<String, Integer> cityMap = new HashMap<String, Integer>();
			for (Map<String, Object> map : cityList) {
				String year = String.valueOf(map.get("year"));
				String province = String.valueOf(map.get("province"));
				String city = String.valueOf(map.get("city"));
				int _count = Integer.valueOf(map.get("_count").toString());
				cityMap.put(year + "_" + province + "_" + city, _count);
			}

			for (EnjoyGameSite enjoyGameSite : list) {
				EnjoyGameSiteVo vo = new EnjoyGameSiteVo();
				BeanUtils.copyProperties(enjoyGameSite, vo);
				StatiumDetail staium = this.selectByPrimaryKey(StatiumDetail.class, enjoyGameSite.getStatiumId());
				if (staium != null) {
					vo.setStatiumName(staium.getName());
				}
				if (cityMap.containsKey(vo.getYear() + "_" + vo.getProvince() + "_" + vo.getCity())) {
					vo.setCityStatus(0);
				} else {
					vo.setCityStatus(1);
				}
				voList.add(vo);
			}
		}
		return new PageImpl<>(voList, pageable, total);
	}

	/**
	 *
	 * <保存乐享赛><功能具体实现>
	 *
	 * @param game
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年2月23日 下午2:30:31
	 */
	@Transactional(readOnly = false)
	public String createOrUpdate(EnjoyGame game, String eventId) throws Exception {
		if (game != null) {
			if (StringUtils.isNotEmpty(game.getAreaCode())) {
				Map<String, String> areaMap = Zonemap.split(game.getAreaCode());
				game.setProvince(areaMap.get("province"));
				game.setCity(areaMap.get("city"));
				game.setArea(areaMap.get("area"));
			}

			if (StringUtils.isBlank(eventId)) {
				if (game.getIsPush() == 1) {
					// 若为定时推送，推送状态置为0，未推送状态
					game.setPushState(0);
				} else if (game.getIsPush() == 0) {
					game.setPushState(1);
				}
			}

			if (StringUtils.isBlank(game.getId())) {
				game.setId(UUID.get());
				game.setCt(new Date());
				game.setCb(SessionUtil.currentUserId());
				game.setState(0);
				int holdTimes = 1;
				if (StringUtils.isNotBlank(eventId)) {
					game.setGid(eventId);
					holdTimes = 0;
				}
				game.setHoldTimes(updateSiteholkTimes(game.getSiteId(), holdTimes));
				game.setIsOffical(0);
				this.insertSelective(game, game.getId());
			} else {
				game.setEt(new Date());
				game.setEb(SessionUtil.currentUserId());
				this.updateByPrimaryKeySelective(game, game.getId());
			}
			enjoyGameSiteManage.updateEnjoyGameHoldTimes(game.getSiteId());
		}
		return game.getId();
	}

	/**
	 *
	 * <累加或减少站点已举办次数><功能具体实现>
	 * 
	 * @param siteId
	 * @param num
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年3月31日 上午11:51:37
	 */
	@Transactional(readOnly = false)
	public Integer updateSiteholkTimes(String siteId, int num) throws Exception {
		EnjoyGameSite site = this.selectByPrimaryKey(EnjoyGameSite.class, siteId);
		logger.debug("site.getHoldTimes()={},num={}", site.getHoldTimes(), num);
		if (site != null) {
			if (site.getHoldTimes() != null) {
				site.setHoldTimes(site.getHoldTimes() + num);
			} else {
				site.setHoldTimes(1);
			}
			this.updateByPrimaryKeySelective(site, site.getId());
		}
		logger.debug("return site.getHoldTimes()=", site.getHoldTimes());
		return site.getHoldTimes();
	}

	/**
	 *
	 * <根据gid获取赛事><功能具体实现>
	 * 
	 * @param game
	 * @return
	 * @author liangsh
	 * @date 2016年3月30日 下午3:26:37
	 */
	public Map<Integer, EnjoyGame> gameVo(EnjoyGame game) throws Exception {
		Map<Integer, EnjoyGame> map = new HashMap<Integer, EnjoyGame>();
		EnjoyGameVo vo = new EnjoyGameVo();
		BeanUtils.copyProperties(game, vo);
		map.put(game.getGameType(), game);
		EnjoyGameCriteria c2 = new EnjoyGameCriteria();
		EnjoyGameCriteria.Criteria cri2 = c2.createCriteria();
		cri2.andDeleteFlagEqualTo(0);
		cri2.andGidEqualTo(game.getId());
		c2.setOrderByClause("game_type");
		int total2 = enjoyGameMapper.countByExample(c2);
		if (total2 > 0) {
			List<EnjoyGame> list2 = enjoyGameMapper.selectByExample(c2);
			vo.setGamesLength(total2);
			for (EnjoyGame enjoyGame : list2) {
				map.put(enjoyGame.getGameType(), enjoyGame);
			}
		}
		return map;
	}

	/**
	 * 导出
	 * 
	 * @param gameId
	 * @throws Exception
	 */
	public List<ExportPlayer> getMembers(String gameId) throws Exception {
		EnjoyMemberCriteria criteria = new EnjoyMemberCriteria();
		EnjoyMemberCriteria.Criteria cri = criteria.createCriteria();
		cri.andGameIdEqualTo(gameId);
		cri.andUserIdIsNotNull();
		cri.andStateEqualTo(1);
		List<EnjoyMember> members = enjoyMemberMapper.selectByExample(criteria);
		List<ExportPlayer> players = new ArrayList<ExportPlayer>();
		ExportPlayer exportPlayer;
		for (EnjoyMember member : members) {
			exportPlayer = new ExportPlayer();
			String teammate = member.getTeammate();
			String playerId = member.getPlayerId();
			GamesPlayer player = this.selectByPrimaryKey(GamesPlayer.class, playerId);
			String userId = player.getUserId();
			SsoUser user = this.selectByPrimaryKey(SsoUser.class, userId);
			String name = player.getName();
			String cardNo = player.getCardNo();
			String phone = user.getPhone();
			String gender = player.getSex() == 0 ? "女" : "男";
			exportPlayer.setName(name);
			exportPlayer.setCardNo(cardNo);
			exportPlayer.setGender(gender);
			exportPlayer.setPhone(phone);
			exportPlayer.setSeedNum(String.valueOf(
					member.getSeedNum() == null || member.getSeedNum() == 0 ? "非种子" : member.getSeedNum() + "号种子"));
			if (StringUtils.isNotBlank(teammate)) {// 有队友
				GamesPlayer player_ = this.selectByPrimaryKey(GamesPlayer.class, teammate);
				String userId_ = player_.getUserId();
				SsoUser user_ = this.selectByPrimaryKey(SsoUser.class, userId_);
				String name_ = player_.getName();
				String cardNo_ = player_.getCardNo();
				String phone_ = user_.getPhone();
				String gender_ = player_.getSex() == 0 ? "女" : "男";
				exportPlayer.setName1(name_);
				exportPlayer.setCardNo1(cardNo_);
				exportPlayer.setGender1(gender_);
				exportPlayer.setPhone1(phone_);
				exportPlayer.setHasTeammate(true);
			} else {
				exportPlayer.setHasTeammate(false);
			}
			players.add(exportPlayer);
		}
		return players;
	}

	/**
	 *
	 * <获取参赛成员列表><功能具体实现>
	 *
	 * @param pageable
	 * @param searchParams
	 * @param gameId
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年2月23日 下午2:30:14
	 */
	public PageImpl<EnjoyMemberVo> findMemberList(Pageable pageable, Map<String, Object> searchParams, String gameId)
			throws Exception {
		EnjoyMemberCriteria criteria = new EnjoyMemberCriteria();
		EnjoyMemberCriteria.Criteria cri = criteria.createCriteria();
		if (searchParams != null) {
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			Criterias.bySearchFilter(cri, filters.values());
		}
		cri.andUserIdIsNotNull();
		cri.andStateIn(Arrays.asList(0,1, 2)); // 未审核的、审核通过的和审核不通过的
		cri.andGameIdEqualTo(gameId);
		criteria.setMysqlOffset(pageable.getOffset());
		criteria.setMysqlLength(pageable.getPageSize());
		// criteria.setOrderByClause("state asc,is_seed desc,seed_num
		// asc,integral desc");
		criteria.setOrderByClause("ct asc");
		List<EnjoyMember> memberList = enjoyMemberMapper.selectByExample(criteria);
		List<EnjoyMemberVo> list = new ArrayList<EnjoyMemberVo>();
		int total = enjoyMemberMapper.countByExample(criteria);
		if (CollectionUtils.isNotEmpty(memberList)) {
			for (EnjoyMember member : memberList) {
				EnjoyMemberVo vo = new EnjoyMemberVo();
				GamesPlayer playerA = this.selectByPrimaryKey(GamesPlayer.class, member.getPlayerId());
				if (playerA != null) {
					vo.setCtaIntegral(member.getCtaIntegral());
					vo.setIdA(member.getId());
					vo.setIsSeed(member.getIsSeed());
					vo.setNameA(playerA.getName());
					vo.setCardNoA(playerA.getCardNo());
					vo.setSexA(playerA.getSex());
					vo.setReason(member.getRemark());
					SsoUser userA = this.selectByPrimaryKey(SsoUser.class, playerA.getUserId());
					if (userA != null) {
						vo.setPhoneA(userA.getPhone());
					}
					vo.setDoubleFlag(member.getDoubleFlag());
					vo.setState(member.getState());
					vo.setCt(DateUtils.forDatetime(member.getCt()));
					if (member.getDoubleFlag() == 1 && org.apache.commons.lang.StringUtils.isNotEmpty(member.getTeammate())) {
						GamesPlayer playerB = this.selectByPrimaryKey(GamesPlayer.class, member.getTeammate());
						if (playerB == null) {
							list.add(vo);
							continue;
						}
						EnjoyMemberCriteria mCriteria = new EnjoyMemberCriteria();
						EnjoyMemberCriteria.Criteria criteria2 = mCriteria.createCriteria();
						criteria2.andPlayerIdEqualTo(member.getTeammate());
						criteria2.andGameIdEqualTo(gameId);
						criteria2.andStateEqualTo(member.getState());
						if (enjoyMemberMapper.countByExample(mCriteria) > 0) {
							EnjoyMember memberB = enjoyMemberMapper.selectByExample(mCriteria).get(0);
							vo.setIdB(memberB.getId());
						}
						vo.setNameB(playerB.getName());
						vo.setCardNoB(playerB.getCardNo());
						vo.setSexB(playerB.getSex());
						SsoUser userB = this.selectByPrimaryKey(SsoUser.class, playerB.getUserId());
						if (userB != null) {
							vo.setPhoneB(userB.getPhone());
						}
					}
					list.add(vo);
				}
			}
		}
		return new PageImpl<>(list, pageable, total);
	}

	/**
	 *
	 * <审核参赛成员><功能具体实现>
	 *
	 * @param memberId
	 * @param status
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年2月23日 下午2:29:52
	 */
	@Transactional(readOnly = false)
	public String updateMember(String memberId, Integer status, String reason) throws Exception {
		EnjoyMember member = this.selectByPrimaryKey(EnjoyMember.class, memberId);
		Order order = this.selectByPrimaryKey(Order.class, member.getOrderId());
		EnjoyMember memberB = null;
		Order orderB = null;
		String result="";
		if (member != null) {
			//双人赛--有teammate
			if (StringUtils.isNotEmpty(member.getTeammate())) {
				//获取队友
				EnjoyMemberCriteria c=new EnjoyMemberCriteria();
				EnjoyMemberCriteria.Criteria cri=c.createCriteria();
				cri.andPlayerIdEqualTo(member.getTeammate()).andGameIdEqualTo(member.getGameId()).andStateEqualTo(1);
				memberB = enjoyMemberMapper.selectByExample(c).get(0);
				//获取队友订单
				if (memberB !=null && member.getPayDouble()==0) {
					orderB= this.selectByPrimaryKey(Order.class, memberB.getOrderId());
					result=updateMemberMethod(memberB.getId(), status, reason, memberB, orderB);
				}
			}
			result=updateMemberMethod(member.getId(), status, reason, member, order);
		}
		if (StringUtils.isEmpty(result)||result=="") {
			return "";
		}
		return member.getGameId();
	}
	public String updateMemberMethod(String memberId, Integer status, String reason, EnjoyMember member, Order order) throws Exception {
		member.setState(status);
		member.setEt(new Date());
		member.setEb(SessionUtil.currentUser().getUserId());
		// 当审核通过时，并且订单已支付时 修改订单状态为已审核
		if (status == 1 && order.getStatus().equals(Constants.OrderStatus.PAIED)) { //
			// 审核通过，已支付的订单
			order.setStatus(Constants.OrderStatus.VERIFY); // 更成已确认订单
			order.setEt(new Date());
			order.setEb(SessionUtil.currentUser().getUserId());
			this.updateByPrimaryKeySelective(order, member.getOrderId());
			this.updateByPrimaryKeySelective(member, memberId);
			return "1";
		} else if (status == 1 && order.getStatus().equals(Constants.OrderStatus.NEW)) {//
			// 审核通过，未支付的订单，客服通知用户取消订单
			return "";
		} else if (status == 2 && (order.getStatus().equals(Constants.OrderStatus.PAIED)
				|| order.getStatus().equals(Constants.OrderStatus.VERIFY)
				|| order.getStatus().equals(Constants.OrderStatus.PLAYING))) {// 审核不通过，已支付的订单
			// 审核不通过的已支付的订单，需要先退款
			order.setStatus(Constants.OrderStatus.REFUNDING);
			order.setEt(new Date());
			order.setEb(SessionUtil.currentUser().getUserId());
			this.updateByPrimaryKeySelective(order, member.getOrderId());
			member.setRemark(reason);
			member.setState(2);
			this.updateByPrimaryKeySelective(member, memberId);
			// 当使用优惠券付款是，把优惠券也退还给用户
			if (!StringUtils.isEmpty(order.getCouponId())) {
				logger.debug("退还优惠券  {}", order.getCouponId());
				CouponHistory couponHistory = this.selectByPrimaryKey(CouponHistory.class, order.getCouponId());
				couponHistory.setSubAmount(null);
				Date endTime = couponHistory.getEndTime();
				Date currTime = new Date();
				if (endTime.compareTo(currTime) <= 0) { // 优惠券结束时间小于或者等于当前时间把优惠券状态改为2过期
					couponHistory.setIsUse(2);
				} else {
					couponHistory.setIsUse(0);
				}
				couponHistory.setOrderId(null);
				couponHistory.setUseTime(null);
				couponHistory.setOccName(null);
				couponHistory.setOrderTime(null);
				this.updateByPrimaryKeySelective(couponHistory, order.getCouponId());
			}
			if (order.getQiuyouFee() != null && order.getQiuyouFee() > 0) {
				QiuyouCardUser account = this.selectByPrimaryKey(QiuyouCardUser.class, order.getCustomerId());
				Integer balanceInit = account.getBalance();
				Integer balanceResult = balanceInit + order.getQiuyouFee();
				// 余额更新
				account.setBalance(balanceResult);
				account.setEt(new Date());
				account.setEb("1");
				this.updateByPrimaryKeySelective(account, order.getCustomerId());
				// 创建球友卡消费日志
				QiuyouCardLog qycl = new QiuyouCardLog();
				qycl.setId(UUID.get());
				qycl.setType(2); // 0充值1消费
				qycl.setUserId(order.getCustomerId());
				qycl.setAmount(order.getQiuyouFee());
				qycl.setBalance(balanceResult);
				qycl.setOrderId(order.getId());
				qycl.setDescription("退回");
				qycl.setCt(new Date());
				qiuyouCardLogMapper.insert(qycl);
			}
			EnjoyGame enjoyGame = this.selectByPrimaryKey(EnjoyGame.class, order.getStatiumId());
			if (StringUtils.isNotBlank(enjoyGame.getGid())) {
				EnjoyGame tmp = this.selectByPrimaryKey(EnjoyGame.class, enjoyGame.getGid());
				enjoyGame.setName(tmp.getName());
				enjoyGame.setStatiumName(tmp.getStatiumName());
				enjoyGame.setHoldTimes(tmp.getHoldTimes());
			}
			// 场馆名称 +赛事名称+报名项目
			String gameType = "";
			switch (enjoyGame.getGameType()) {
			case 1:
				gameType = "男单";
				break;
			case 2:
				gameType = "女单";
				break;
			case 3:
				gameType = "男双";
				break;
			case 4:
				gameType = "女双";
				break;
			case 5:
				gameType = "混双";
				break;
			case 6:
				gameType = "混单";
				break;
			case 7:
				gameType = "无性别限制双打";
				break;
			}
			String gameName = enjoyGame.getStatiumName() + enjoyGame.getName() + "杯CTA-Open乐享赛第" + enjoyGame.getHoldTimes() + "站" + gameType;
			Map<String, Object> root = new HashMap<String, Object>();
			// 您“北京水立方网球馆 “cta网球乐享赛(北京站)” 男子单打 的订单（订单号：1111）已被取消，请修改后重新下单。
			root.put("gameName", gameName);
			root.put("orderNo", order.getId());
			String msg = FreeMarkerUtils.format("/template/sms/orderEventCancel.ftl", root);
			GamesPlayer player = this.selectByPrimaryKey(GamesPlayer.class, member.getPlayerId());
			if (player != null) {
				SsoUser user = this.selectByPrimaryKey(SsoUser.class, player.getUserId());
				if (user != null) {
					messageUtil.sendSms(user.getPhone(), msg);
				}
			}
			if (member.getDoubleFlag() == 1) {
				if (StringUtils.isNotBlank(member.getTeammate())) {
					GamesPlayer player2 = this.selectByPrimaryKey(GamesPlayer.class, member.getTeammate());
					if (player2 != null) {
						SsoUser user2 = this.selectByPrimaryKey(SsoUser.class, player2.getUserId());
						if (user2 != null) {
							messageUtil.sendSms(user2.getPhone(), msg);
						}
					}
				}
			}
			// 记录订单日志
			OrderLog log = new OrderLog();
			log.setId(UUID.get());
			log.setCt(new Date());
			log.setOrderId(order.getId());
			log.setUserId(SessionUtil.currentUserId());
			log.setAction(Constants.OrderAction.APPLY_REFUND);
			log.setComment("订单号: " + order.getId() + "申请退款!退款原因：乐享赛报名被拒绝");
			orderLogMapper.insert(log);
			// 给客服发送消息提醒
			RoleCriteria roleCriteria = new RoleCriteria();
			RoleCriteria.Criteria rCriteria = roleCriteria.createCriteria();
			rCriteria.andRoleCodeEqualTo("customer");
			List<Role> roles = roleMapper.selectByExample(roleCriteria);
			Role role = roles.get(0);
			String roleId = role.getRoleId();
			UserRoleCriteria userRoleCriteria = new UserRoleCriteria();
			UserRoleCriteria.Criteria criteria2 = userRoleCriteria.createCriteria();
			criteria2.andRoleIdEqualTo(roleId);
			List<UserRoleKey> userRoles = userRoleMapper.selectByExample(userRoleCriteria);
			for (UserRoleKey roleKey : userRoles) {
				String userid = roleKey.getUserId();
				messageUtil.sendLoc("1", userid, "乐享赛报名(订单号:" + order.getId() + ")被拒绝",
						"乐享赛报名(订单号:" + order.getId() + ")被拒绝");
			}
			return "1";
		} else {// 审核不通过，未支付的订单
			member.setRemark(reason);
			member.setState(2);
			this.updateByPrimaryKeySelective(member, memberId);
			return "1";
		}
	}

	@Transactional(readOnly = false)
	public void markSeed(String userAId, String eventId, Integer isSeed) throws Exception {
		EnjoyMember member = this.selectByPrimaryKey(EnjoyMember.class, userAId);
		member.setIsSeed(isSeed);
		member.setEb(SessionUtil.currentUserId());
		member.setEt(new Date());
		if (isSeed == 0) {
			member.setSeedNum(null);
		} else {
			EnjoyMemberCriteria c = new EnjoyMemberCriteria();
			EnjoyMemberCriteria.Criteria cri = c.createCriteria();
			cri.andGameIdEqualTo(eventId);
			cri.andIsSeedEqualTo(1);
			cri.andSeedNumIsNotNull();
			c.setOrderByClause("seed_num desc");
			List<EnjoyMember> list = enjoyMemberMapper.selectByExample(c);
			if (CollectionUtils.isNotEmpty(list)) {
				member.setSeedNum(list.get(0).getSeedNum() + 1);
			} else {
				member.setSeedNum(1);
			}
		}
		this.updateByPrimaryKey(member, userAId);
	}

	/**
	 *
	 * <验证种子合法性><功能具体实现>
	 * 
	 * @param gameId
	 * @return
	 * @author liangsh
	 * @date 2016年4月8日 下午7:22:55
	 */
	public boolean getSeedSetable(String gameId) {
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("gameId", gameId);
		String sqlList = FreeMarkerUtils.format("/template/game/seed_count.ftl", paras);
		List<Map<String, Object>> seedInfos = jdbcTemplate.queryForList(sqlList);
		Map<String, Object> seedInfo = seedInfos.get(0);
		Integer seedNum = ((Long) seedInfo.get("seed")).intValue();
		Integer subed = ((Long) seedInfo.get("subed")).intValue();
		int totalMan = subed;
		int totalLabel = 1;
		if (totalMan < 8) {
			totalLabel = 8;
		} else {
			do {
				totalLabel = totalLabel * 2;
			} while ((totalMan = totalMan / 2) != 1);
			if ((Math.log(totalMan) / Math.log(2)) == ((int) (Math.log(totalMan) / Math.log(2)))) {
			} else {
				totalLabel = totalLabel * 2;
			}
		}

		int maxSeedNum = totalLabel / 4;
		int minSeedNum = maxSeedNum / 2;
		int seedNumable;
		if (subed < totalLabel * 3 / 4) {
			seedNumable = minSeedNum;
		} else {
			seedNumable = maxSeedNum;
		}
		if (seedNum >= seedNumable) {
			return false;
		}
		return true;
	}

	/**
	 *
	 * <种子列表><功能具体实现>
	 *
	 * @param eventId
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年2月23日 下午2:29:30
	 */
	public List<EnjoyMemberVo> getSeedList(String eventId) throws Exception {
		EnjoyMemberCriteria c = new EnjoyMemberCriteria();
		EnjoyMemberCriteria.Criteria cri = c.createCriteria();
		cri.andGameIdEqualTo(eventId);
		cri.andIsSeedEqualTo(1);
		cri.andStateNotEqualTo(3); // 非删除的成员
		c.setOrderByClause("seed_num asc,integral desc"); // 优先种子排位排序，再根据积分排序
		List<EnjoyMember> list = enjoyMemberMapper.selectByExample(c);
		List<EnjoyMemberVo> voList = new ArrayList<EnjoyMemberVo>();
		if (CollectionUtils.isNotEmpty(list)) {
			for (EnjoyMember enjoyMember : list) {
				EnjoyMemberVo vo = new EnjoyMemberVo();
				GamesPlayer userA = this.selectByPrimaryKey(GamesPlayer.class, enjoyMember.getPlayerId());
				if (userA != null) {
					GamesScoreCriteria sA = new GamesScoreCriteria();
					GamesScoreCriteria.Criteria sCriteriaA = sA.createCriteria();
					sCriteriaA.andPlayerIdEqualTo(userA.getId());
					List<GamesScore> scoreAList = gamesScoreMapper.selectByExample(sA);
					if (CollectionUtils.isNotEmpty(scoreAList)) {
						vo.setScoreA(scoreAList.get(0).getInteg());
					}
					vo.setIdA(enjoyMember.getId());
					vo.setIsSeed(enjoyMember.getIsSeed());
					vo.setNameA(userA.getName());
					vo.setCardNoA(userA.getCardNo());
					vo.setSexA(userA.getSex());
					vo.setPhoneA(userA.getPhone());
					vo.setSeedNum(enjoyMember.getSeedNum());
					if (enjoyMember.getDoubleFlag() == 1) {
						GamesPlayer userB = this.selectByPrimaryKey(GamesPlayer.class, enjoyMember.getTeammate());
						vo.setIdB(enjoyMember.getTeammate());
						vo.setNameB(userB.getName());
						vo.setCardNoB(userB.getCardNo());
						vo.setSexB(userB.getSex());
						vo.setPhoneB(userB.getPhone());
						GamesScoreCriteria sB = new GamesScoreCriteria();
						GamesScoreCriteria.Criteria sCriteriaB = sB.createCriteria();
						sCriteriaB.andPlayerIdEqualTo(userA.getId());
						List<GamesScore> scoreBList = gamesScoreMapper.selectByExample(sB);
						if (CollectionUtils.isNotEmpty(scoreBList)) {
							vo.setScoreA(scoreBList.get(0).getInteg());
						}
					}
					voList.add(vo);
				}
			}
		}
		return voList;
	}

	/**
	 *
	 * <查看是否可以生成赛程><功能具体实现>
	 * 
	 * @param gameList
	 * @return
	 * @author liangsh
	 * @date 2016年4月14日 下午2:28:53
	 */
	public boolean checkSignedCount(List<EnjoyGame> gameList) {
		// 赛事每个项目不少于8人时才可开赛
		boolean allow = true;
		for (EnjoyGame enjoyGame : gameList) {
			EnjoyMemberCriteria c = new EnjoyMemberCriteria();
			EnjoyMemberCriteria.Criteria cri = c.createCriteria();
			cri.andGameIdEqualTo(enjoyGame.getId());
			cri.andStateIn(Arrays.asList(0, 1));
			cri.andUserIdIsNotNull();
			int count = enjoyMemberMapper.countByExample(c);
			if (count < 8) {
				allow = false;
				break;
			}
		}
		return allow;
	}

	/**
	 *
	 * <循环＋淘汰赛之循环赛赛程生成><功能具体实现>
	 *
	 * @param gameFormat
	 * @param snum
	 * @param gnum
	 * @param eventId
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年2月24日 下午12:17:37
	 */
	public GroupGameVo createSchedule1(Integer gameFormat, Integer snum, Integer gnum, String eventId)
			throws Exception {
		List<String> seeds = this.seeds(eventId);
		List<String> whites = this.whites(eventId);
		// 初始化
		GameDraw gameDraw = new GameDraw();
		gameDraw.init(seeds, whites, eventId, SessionUtil.currentUserId(), snum, gnum);
		// 生成赛程
		return gameDraw.produceGroup();
	}

	/**
	 *
	 * <循环＋淘汰赛之淘汰赛赛程生成><功能具体实现>
	 * 
	 * @param gameFormat
	 * @param snum
	 * @param gnum
	 * @param eventId
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年2月29日 下午3:49:49
	 */
	@Transactional(readOnly = false)
	public GamesScheduleModel createSchedule12(Integer gameFormat, Integer snum, Integer gnum, String eventId,
			Map<String, Map<String, String>> drawResult) throws Exception {
		// 初始化
		GameDraw gameDraw = new GameDraw();
		Map<String, Object> results = gameDraw.setOutGame(eventId, drawResult);
		if (drawResult.isEmpty() && (boolean) results.get("isDraw")) {
			GamesScheduleModel schedule = new GamesScheduleModel();
			Map<String, Map<String, List<PlayerInfo>>> result = (Map<String, Map<String, List<PlayerInfo>>>) results
					.get("draw");
			schedule.setSameScores(result);
			return schedule;
		}
		GamesScheduleModel schedule = (GamesScheduleModel) results.get("root");
		// 生成赛程
		return schedule;
	}

	/**
	 * 查看是否能进入淘汰赛
	 * 
	 * @param gameId
	 * @return
	 */
	public String checkOutable(String gameId) {
		EnjoyGroupScheduleCriteria criteria = new EnjoyGroupScheduleCriteria();
		EnjoyGroupScheduleCriteria.Criteria cri = criteria.createCriteria();
		cri.andGameIdEqualTo(gameId);
		cri.andScore1IsNull();
		int notBeginCount = enjoyGroupScheduleMapper.countByExample(criteria);
		if (notBeginCount != 0) {
			return "小组赛未结束，不能进入淘汰赛！";
		}
		return null;
	}

	/**
	 *
	 * <淘汰赛赛程生成><功能具体实现>
	 *
	 * @param gameFormat
	 * @param snum
	 * @param eventId
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年2月24日 上午11:51:33
	 */
	public GamesScheduleModel createSchedule2(Integer gameFormat, Integer snum, String eventId) throws Exception {
		List<String> seeds = this.seeds(eventId);
		List<String> whites = this.whites(eventId);
		// 初始化
		GameDraw gameDraw = new GameDraw();
		gameDraw.init(seeds, whites, eventId, SessionUtil.currentUserId(), snum);
		// 生成赛程
		return gameDraw.produceOut();
	}

	/**
	 *
	 * <循环赛赛程生成><功能具体实现>
	 *
	 * @param gameFormat
	 * @param snum
	 * @param gnum
	 * @param eventId
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年2月24日 上午11:54:00
	 */
	public GroupGameVo createSchedule3(Integer gameFormat, Integer snum, Integer gnum, String eventId)
			throws Exception {
		List<String> seeds = this.seeds(eventId);
		List<String> whites = this.whites(eventId);
		// 初始化
		GameDraw gameDraw = new GameDraw();
		gameDraw.init(seeds, whites, eventId, SessionUtil.currentUserId(), snum, gnum);
		// 生成赛程
		return gameDraw.produceLoop();
	}

	/**
	 *
	 * <保存赛程循环赛+淘汰赛><功能具体实现>
	 *
	 * @param groupGameVo
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年2月25日 下午2:32:43
	 */
	@Transactional(readOnly = false)
	public void saveSchedule1(GroupGameVo groupGameVo) throws Exception {
		GameDraw darDraw = new GameDraw();
		darDraw.saveGroupSchedule(groupGameVo);
	}

	/**
	 *
	 * <保存赛程淘汰赛><功能具体实现>
	 *
	 * @param treeMap
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年2月25日 下午2:33:13
	 */
	@Transactional(readOnly = false)
	public void saveSchedule2(GamesScheduleModel treeMap) throws Exception {
		GameDraw darDraw = new GameDraw();
		darDraw.saveTree(treeMap);
	}

	/**
	 *
	 * <保存赛程循环赛><功能具体实现>
	 *
	 * @param map
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年2月25日 下午2:33:38
	 */
	@Transactional(readOnly = false)
	public void saveSchedule3(GroupGameVo map) throws Exception {
		GameDraw darDraw = new GameDraw();
		darDraw.saveGroupSchedule(map);
	}

	/**
	 *
	 * <获取种子><功能具体实现>
	 *
	 * @param eventId
	 * @return
	 * @author liangsh
	 * @date 2016年2月24日 上午11:51:50
	 */
	private List<String> seeds(String eventId) {
		// 获取所有种子memberIdList
		List<String> seeds = new ArrayList<String>();
		// 根据赛事id或去所有的种子选手
		EnjoyMemberCriteria c = new EnjoyMemberCriteria();
		EnjoyMemberCriteria.Criteria cri = c.createCriteria();
		cri.andGameIdEqualTo(eventId);
		cri.andStateEqualTo(1); // 审核通过的选手
		cri.andIsSeedEqualTo(1); // 是种子选手
		cri.andUserIdIsNotNull();
		c.setOrderByClause("seed_num asc"); // 按种子排位排序
		List<EnjoyMember> members = enjoyMemberMapper.selectByExample(c);
		if (CollectionUtils.isNotEmpty(members)) {
			for (EnjoyMember enjoyMember : members) {
				seeds.add(enjoyMember.getId());
			}
		}
		return seeds;
	}

	/**
	 *
	 * <获取白人><功能具体实现>
	 *
	 * @param eventId
	 * @return
	 * @author liangsh
	 * @date 2016年2月24日 上午11:51:59
	 */
	public List<String> whites(String eventId) {
		// 获取所有白人memberIdList
		List<String> whites = new ArrayList<String>();
		EnjoyMemberCriteria c2 = new EnjoyMemberCriteria();
		EnjoyMemberCriteria.Criteria cri2 = c2.createCriteria();
		cri2.andGameIdEqualTo(eventId);
		cri2.andStateEqualTo(1); // 审核通过的选手
		cri2.andIsSeedNotEqualTo(1); // 非种子选手
		cri2.andUserIdIsNotNull();
		c2.setOrderByClause("integral desc"); // 按积分排序
		List<EnjoyMember> members2 = enjoyMemberMapper.selectByExample(c2);
		if (CollectionUtils.isNotEmpty(members2)) {
			for (EnjoyMember enjoyMember : members2) {
				whites.add(enjoyMember.getId());
			}
		}
		return whites;
	}

	/**
	 *
	 * <根据赛事id获取循环赛赛程><功能具体实现>
	 *
	 * @create：2016年2月25日 上午11:40:45 @author： sl
	 * @param enjoyId
	 * @return
	 */
	public List<EnjoyGroupSchedule> enjoyGroupSchedules(String enjoyId) {
		List<EnjoyGroupSchedule> enjoyGroupSchedules = new ArrayList<EnjoyGroupSchedule>();
		try {
			EnjoyGroupScheduleCriteria enjoyGroupScheduleCriteria = new EnjoyGroupScheduleCriteria();
			EnjoyGroupScheduleCriteria.Criteria criteria = enjoyGroupScheduleCriteria.createCriteria();
			criteria.andGameIdEqualTo(enjoyId);
			enjoyGroupSchedules = enjoyGroupScheduleMapper.selectByExample(enjoyGroupScheduleCriteria);
		} catch (Exception e) {
			logger.debug("根据赛事id获取循环赛赛程： {}", e.getMessage());
		}
		return enjoyGroupSchedules;
	}

	/**
	 *
	 * <比分保存><功能具体实现>
	 *
	 * @create：2016年2月25日 下午7:14:11 @author： sl
	 * @param schedule
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public void saveGroupScore(EnjoyGroupSchedule schedule) throws Exception {
		enjoyGroupScheduleMapper.updateByPrimaryKeySelective(schedule);

		String p1 = schedule.getClasli1();
		String p2 = schedule.getClasli2();
		String gameId = schedule.getGameId();

		EnjoyGroupCriteria criteria = new EnjoyGroupCriteria();
		EnjoyGroupCriteria.Criteria cri = criteria.createCriteria();
		cri.andGameIdEqualTo(gameId);
		cri.andMemberIdEqualTo(p1);
		List<EnjoyGroup> groups = enjoyGroupMapper.selectByExample(criteria);

		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("mem", p1);
		String sqlList = FreeMarkerUtils.format("/template/event/member_score.ftl", paras);
		List<Map<String, Object>> score = jdbcTemplate.queryForList(sqlList);
		Map<String, Object> sc = score.get(0);
		int win = ((BigDecimal) sc.get("win")).intValue();
		int draw = ((BigDecimal) sc.get("draw")).intValue();
		int loose = ((BigDecimal) sc.get("loose")).intValue();
		int integ = win * 3 + draw * 1 + loose * 0;
		EnjoyGroup group = groups.get(0);
		group.setWin(win);
		group.setDraw(draw);
		group.setLoss(loose);
		group.setInteg(integ);
		enjoyGroupMapper.updateByPrimaryKey(group);

		paras.put("mem", p2);
		sqlList = FreeMarkerUtils.format("/template/event/member_score.ftl", paras);
		score = jdbcTemplate.queryForList(sqlList);
		sc = score.get(0);
		win = ((BigDecimal) sc.get("win")).intValue();
		draw = ((BigDecimal) sc.get("draw")).intValue();
		loose = ((BigDecimal) sc.get("loose")).intValue();
		integ = win * 3 + draw * 1 + loose * 0;
		criteria = new EnjoyGroupCriteria();
		cri = criteria.createCriteria();
		cri.andMemberIdEqualTo(p2);
		groups = enjoyGroupMapper.selectByExample(criteria);
		group = groups.get(0);
		group.setWin(win);
		group.setDraw(draw);
		group.setLoss(loose);
		group.setInteg(integ);
		enjoyGroupMapper.updateByPrimaryKey(group);
	}

	/**
	 *
	 * <获取淘汰赛节点详情><功能具体实现>
	 * 
	 * @param lId
	 * @param rId
	 * @return
	 * @author liangsh
	 * @date 2016年3月1日 上午10:52:06
	 */
	public EnjoyMemberVo getEliDetail(String id, String lId, String rId) throws Exception {
		EnjoyMemberVo vo = new EnjoyMemberVo();
		EnjoyElimination lElimination = this.selectByPrimaryKey(EnjoyElimination.class, lId);
		if (lElimination != null) {
			vo.setIdA(lElimination.getWinner());
			EnjoyMember lMember = this.selectByPrimaryKey(EnjoyMember.class, lElimination.getWinner());
			if (lMember != null) {
				vo.setIsSeed(lMember.getIsSeed());
				vo.setSeedNum(lMember.getSeedNum());
				GamesPlayer lPlayer = this.selectByPrimaryKey(GamesPlayer.class, lMember.getPlayerId());
				if (lPlayer != null) {
					vo.setNameA(lPlayer.getName());
				}
			}
		}
		EnjoyElimination rElimination = this.selectByPrimaryKey(EnjoyElimination.class, rId);
		if (rElimination != null) {
			vo.setIdB(rElimination.getWinner());
			EnjoyMember rMember = this.selectByPrimaryKey(EnjoyMember.class, rElimination.getWinner());
			if (rMember != null) {
				vo.setIsSeedB(rMember.getIsSeed());
				vo.setSeedNumB(rMember.getSeedNum());
				GamesPlayer rPlayer = this.selectByPrimaryKey(GamesPlayer.class, rMember.getPlayerId());
				if (rPlayer != null) {
					vo.setNameB(rPlayer.getName());
				}
			}
		}
		vo.setScoreB(0);
		vo.setTurnNo(rElimination.getTurn() + 1);
		vo.setEliId(id);
		EnjoyElimination elimination = this.selectByPrimaryKey(EnjoyElimination.class, id);
		vo.setScoreA(elimination.getLscore());
		vo.setScoreB(elimination.getRscore());
		return vo;
	}

	@Transactional(readOnly = false)
	public void saveScoreEli(EnjoyElimination elimination) throws Exception {
		elimination.setEb(SessionUtil.currentUserId());
		elimination.setEt(DateUtils.forDatetime(new Date()));
		this.updateByPrimaryKeySelective(elimination, elimination.getId());
	}

	/**
	 *
	 * <赛事通知><功能具体实现>
	 *
	 * @create：2016年2月29日 下午4:24:39 @author： sl
	 * @param pageRequest
	 * @param gameId
	 * @return
	 */
	public Page<PushLog> eventMsg(PageRequest pageRequest, String gameId) {
		List<PushLog> pushLogs = new ArrayList<PushLog>();
		int total = 0;
		try {
			PushLogCriteria pushLogCriteria = new PushLogCriteria();
			PushLogCriteria.Criteria criteria = pushLogCriteria.createCriteria();
			criteria.andGroupIdEqualTo(gameId);
			criteria.andPushTypeEqualTo(4);
			pushLogCriteria.setMysqlOffset(pageRequest.getOffset());
			pushLogCriteria.setMysqlLength(pageRequest.getPageSize());
			pushLogCriteria.setOrderByClause("ct desc");
			pushLogs = pushLogMapper.selectByExample(pushLogCriteria);
			total = pushLogMapper.countByExample(pushLogCriteria);
		} catch (Exception e) {
			logger.debug("赛事通知：{}", e.getMessage());
		}
		return new PageImpl<>(pushLogs, pageRequest, total);
	}

	/**
	 *
	 * <根据赛事id获取报名人员信息><功能具体实现>
	 *
	 * @create：2016年2月29日 下午5:17:53 @author： sl
	 * @param msgId
	 * @return
	 */
	public List<EnjoyMember> enjoyMembersByGameId(String msgId) {
		List<EnjoyMember> enjoyMembers = new ArrayList<EnjoyMember>();
		try {
			EnjoyMemberCriteria enjoyMemberCriteria = new EnjoyMemberCriteria();
			EnjoyMemberCriteria.Criteria criteria = enjoyMemberCriteria.createCriteria();
			criteria.andGameIdEqualTo(msgId);
			enjoyMembers = enjoyMemberMapper.selectByExample(enjoyMemberCriteria);
		} catch (Exception e) {
			logger.debug("根据赛事id获取报名人员信息", e.getMessage());
		}
		return enjoyMembers;
	}

	/**
	 *
	 * <根据乐享赛主gameId获取所有game><功能具体实现>
	 * 
	 * @param gameId
	 * @return
	 * @author liangsh
	 * @date 2016年4月6日 下午3:45:27
	 */
	public List<EnjoyGame> gameList(String gameId) {
		List<EnjoyGame> list = new ArrayList<EnjoyGame>();
		EnjoyGameCriteria criteria = new EnjoyGameCriteria();
		EnjoyGameCriteria.Criteria cri = criteria.createCriteria();
		cri.andIdEqualTo(gameId);
		EnjoyGameCriteria.Criteria cri2 = criteria.createCriteria();
		cri2.andGidEqualTo(gameId);
		criteria.or(cri2);
		if (enjoyGameMapper.countByExample(criteria) > 0) {
			list = enjoyGameMapper.selectByExample(criteria);
		}
		return list;
	}

	/**
	 *
	 * <><功能具体实现>
	 * 
	 * @param status
	 * @return
	 * @author liangsh
	 * @date 2016年4月6日 下午3:45:22
	 */
	public List<EnjoyGameSite> siteList(String id, String status) throws Exception {
		List<EnjoyGameSite> siteList = new ArrayList<EnjoyGameSite>();
		EnjoyGameSite site = this.selectByPrimaryKey(EnjoyGameSite.class, id);
		if (status.equals("all")) {// 该城市所有场馆
			EnjoyGameSiteCriteria c = new EnjoyGameSiteCriteria();
			EnjoyGameSiteCriteria.Criteria cri = c.createCriteria();
			cri.andCityEqualTo(site.getCity());
			String date = DateUtils.formatDate(new Date(), "yyyy-MM-dd");
			cri.andStartLessThanOrEqualTo(date);
			cri.andEndGreaterThanOrEqualTo(date);
			if (enjoyGameSiteMapper.countByExample(c) > 0) {
				siteList = enjoyGameSiteMapper.selectByExample(c);
			}
		} else { // 单个场馆
			siteList.add(site);
		}
		return siteList;
	}

	/**
	 *
	 * <校验场馆发布赛事是否已达到上限><功能具体实现>
	 * 
	 * @param siteId
	 * @return
	 * @author liangsh
	 * @throws Exception
	 * @date 2016年4月11日 下午5:23:05
	 */
	public boolean chcekHoldTimes(String siteId) throws Exception {
		EnjoyGameSite site = this.selectByPrimaryKey(EnjoyGameSite.class, siteId);
		if (site.getHoldTimes() < site.getNum()) {
			logger.debug("holTime={},num={}", site.getHoldTimes(), site.getNum());
			return true;
		} else {
			logger.debug("holTime >= num");
			return false;
		}
	}

	/**
	 *
	 * <发布成绩并分配奖金><功能具体实现>
	 * 
	 * @param eventId
	 * @author liangsh
	 * @date 2016年4月11日 下午6:28:27
	 */
	@Transactional(readOnly = false)
	public void notice(String eventId, Integer gameType, Integer gameLevel) throws Exception {

		EnjoyGame game = this.selectByPrimaryKey(EnjoyGame.class, eventId);

		if (game.getGameFormat() == 1 && game.getGameState() != 2) {
			throw new Exception("比赛未结束，不能发布成绩公告");
		}

		EnjoyEliminationCriteria criteria_ = new EnjoyEliminationCriteria();
		EnjoyEliminationCriteria.Criteria cri_ = criteria_.createCriteria();
		cri_.andGameIdEqualTo(eventId);
		criteria_.setOrderByClause("turn desc");
		List<EnjoyElimination> eliminations = eliminationMapper.selectByExample(criteria_);
		EnjoyElimination first = eliminations.get(0);
		if (StringUtils.isEmpty(first.getWinner())) {
			throw new Exception("比赛未结束，不能发布成绩公告");
		}

		// 更改赛事表中是否有发布公告的状态
		EnjoyGame tmp = game;
		if (game.getGid() != null) {
			tmp = this.selectByPrimaryKey(EnjoyGame.class, game.getGid());
		}

		if (tmp.getIsOffical() != 1) {
			for (EnjoyElimination eli : eliminations) {
				String winner = eli.getWinner();
				if (StringUtils.isEmpty(winner)) {
					continue;
				}
				EnjoyMember mem = new EnjoyMember();
				mem.setId(winner);
				EnjoyMember mem_ = this.selectByPrimaryKey(EnjoyMember.class, winner);
				Integer integ = mem_.getIntegral();
				if (integ == null) {
					integ = 0;
				}
				mem.setIntegral(integ + 1);
				this.updateByPrimaryKeySelective(mem, winner);
			}
		}

		Map<String, Object> paras = new HashMap<String, Object>();
		String sql = FreeMarkerUtils.format("/template/game/game_integral_rank_230.ftl", paras);
		// 获取赛事的参赛人数
		EnjoyMemberCriteria c = new EnjoyMemberCriteria();
		EnjoyMemberCriteria.Criteria cri = c.createCriteria();
		cri.andGameIdEqualTo(eventId);
		cri.andStateEqualTo(1);
		cri.andUserIdIsNotNull();
		if (enjoyMemberMapper.countByExample(c) > 0) {
			int totalNumber = enjoyMemberMapper.countByExample(c);
			logger.debug("sql={},gameLevel={}", sql, gameLevel);
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[] { eventId, gameType });
			for (Map<String, Object> map : list) {
				EnjoyMember member = new EnjoyMember();
				member.setId(map.get("memberId").toString());
				Double rank = Double.parseDouble(map.get("rank").toString());
				member.setRank(rank.intValue());
				member.setEt(new Date());
				member.setEb(SessionUtil.currentUserId());
				member.setIsIssueBonus(0);
				switch (gameLevel) {
				//@date 2017.1.12   分配奖金时同时设置证书
				case 1:
					if (member.getRank() == 1) {
						member.setBonus(500);
						member.setCertificate(1);
					} else if (member.getRank() == 2) {
						member.setBonus(300);
						member.setCertificate(2);
					} else if (member.getRank() == 3) {
						member.setBonus(200);
						member.setCertificate(3);
					} else if (member.getRank() == 5 && totalNumber >= 32) {
						member.setBonus(100);
						member.setCertificate(5);
					} else {
						member.setBonus(0);
					}
					break;
				case 2:
				case 3:
					if (member.getRank() == 1) {
						member.setBonus(300);
						member.setCertificate(1);
					} else if (member.getRank() == 2) {
						member.setBonus(200);
						member.setCertificate(2);
					} else if (member.getRank() == 3) {
						member.setBonus(100);
						member.setCertificate(3);
					} else if (member.getRank() == 5 && totalNumber >= 32) {
						member.setBonus(50);
						member.setCertificate(5);
					} else {
						member.setBonus(0);
					}
					break;
				case 4:
					if (member.getRank() == 1) {
						member.setBonus(200);
						member.setCertificate(1);
					} else if (member.getRank() == 2) {
						member.setBonus(100);
						member.setCertificate(2);
					} else if (member.getRank() == 3) {
						member.setBonus(50);
						member.setCertificate(3);
					} else {
						member.setBonus(0);
					}
					break;
				case 5:
					member.setBonus(0);// 奖金
					break;
				}

				if (gameType == 3 || gameType == 4 || gameType == 5|| gameType == 7) {
					BigDecimal decimal1 = new BigDecimal(member.getBonus());
					BigDecimal decimal2 = new BigDecimal(2);
					member.setBonus(decimal1.divide(decimal2).intValue());
				}
				this.updateByPrimaryKeySelective(member, member.getId());
			}
			if (game != null) {
				game.setIsScoreNotice(1);
				game.setEt(new Date());
				game.setEb(SessionUtil.currentUserId());
				this.updateByPrimaryKeySelective(game, game.getId());
			}

		}
	}

	/**
	 *
	 * <前3名或前4名参赛人员><功能具体实现>
	 * 
	 * @param pageable
	 * @param searchParams
	 * @param gameId
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年4月11日 下午7:49:53
	 */
	public PageImpl<EnjoyMemberVo> bonusMemberList(Pageable pageable, Map<String, Object> searchParams, String gameId,
			Integer gameLevel) throws Exception {
		EnjoyMemberCriteria criteria = new EnjoyMemberCriteria();
		EnjoyMemberCriteria.Criteria cri = criteria.createCriteria();
		if (searchParams != null) {
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			Criterias.bySearchFilter(cri, filters.values());
		}
		cri.andUserIdIsNotNull();
		cri.andGameIdEqualTo(gameId);
		cri.andStateEqualTo(1);
		criteria.setMysqlOffset(pageable.getOffset());
		criteria.setMysqlLength(pageable.getPageSize());
		cri.andRankIsNotNull();
		criteria.setOrderByClause("rank asc,integral desc");
		List<EnjoyMember> memberList = enjoyMemberMapper.selectByExample(criteria);
		List<EnjoyMemberVo> list = new ArrayList<EnjoyMemberVo>();
		int total = enjoyMemberMapper.countByExample(criteria);
		if (CollectionUtils.isNotEmpty(memberList)) {
			for (EnjoyMember member : memberList) {
				if (gameLevel <= 3 && memberList.size() >= 32 && member.getRank() > 5) {
					break;
				} else if (gameLevel <= 3 && memberList.size() < 32 && member.getRank() > 3) {
					break;
				} else if (gameLevel == 4 && member.getRank() > 3) {
					break;
				} else {
					EnjoyMemberVo vo = new EnjoyMemberVo();
					GamesPlayer playerA = this.selectByPrimaryKey(GamesPlayer.class, member.getPlayerId());
					if (playerA != null) {
						GamesScoreCriteria sA = new GamesScoreCriteria();
						GamesScoreCriteria.Criteria sCriteriaA = sA.createCriteria();
						sCriteriaA.andPlayerIdEqualTo(playerA.getId());
						List<GamesScore> scoreAList = gamesScoreMapper.selectByExample(sA);
						if (CollectionUtils.isNotEmpty(scoreAList)) {
							vo.setScoreA(scoreAList.get(0).getInteg());
						}
						vo.setIdA(member.getId());
						vo.setIsSeed(member.getIsSeed());
						vo.setNameA(playerA.getName());
						vo.setCardNoA(playerA.getCardNo());
						vo.setSexA(playerA.getSex());
						SsoUser userA = this.selectByPrimaryKey(SsoUser.class, playerA.getUserId());
						if (userA != null) {
							vo.setPhoneA(userA.getPhone());
						}
						vo.setDoubleFlag(member.getDoubleFlag());
						vo.setCtaIntegral(member.getCtaIntegral());
						vo.setState(member.getState());
						vo.setRank(member.getRank());
						vo.setBonus(member.getBonus());
						vo.setIsIssueBonus(member.getIsIssueBonus());
						//@date 2017.1.12   显示奖金时同时返回证书相关信息
						vo.setCertificate(member.getCertificate());
						vo.setIsIssueCertificate(member.getIsIssueCertificate());
						StatiumAuditLogCriteria logCriteria = new StatiumAuditLogCriteria();
						StatiumAuditLogCriteria.Criteria logCriteria2 = logCriteria.createCriteria();
						logCriteria2.andStatiumIdEqualTo(member.getId());
						if (statiumAuditLogMapper.countByExample(logCriteria) > 0) {
							StringBuilder reason = new StringBuilder("");
							List<StatiumAuditLog> logs = statiumAuditLogMapper.selectByExample(logCriteria);
							for (StatiumAuditLog statiumAuditLog : logs) {
								reason.append(DateUtil.formatDate(statiumAuditLog.getCt(), "yyyy-MM-dd HH:mm"));
								reason.append(" ").append(statiumAuditLog.getDescription());
							}
							vo.setReason(reason.toString());
						}
						if (member.getDoubleFlag() == 1) {
							GamesPlayer playerB = this.selectByPrimaryKey(GamesPlayer.class, member.getTeammate());
							EnjoyMember memberB = this.getEnjoyMemberByGameIdAndPlayerId(gameId, playerB.getId(), 0, 1);
							vo.setIdB(memberB.getId());
							vo.setNameB(playerB.getName());
							vo.setCardNoB(playerB.getCardNo());
							vo.setSexB(playerB.getSex());
							SsoUser userB = this.selectByPrimaryKey(SsoUser.class, playerB.getUserId());
							if (userB != null) {
								vo.setPhoneB(userB.getPhone());
							}
						}
						list.add(vo);
					}
				}
			}
		}
		return new PageImpl<>(list, pageable, total);
	}

	/**
	 *
	 * <运营确认奖金><功能具体实现>
	 * 
	 * @param memberId
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年4月27日 下午2:28:33
	 */
	@Transactional(readOnly = false)
	public void auditBonus(String memberId) throws Exception {
		EnjoyMember member = this.selectByPrimaryKey(EnjoyMember.class, memberId);
		if (member != null) {
			member.setIsIssueBonus(2);
			//@date 2017.1.12    设置奖金状态时同时设置证书状态
			member.setIsIssueCertificate(2);
			member.setEt(new Date());
			member.setEb(SessionUtil.currentUserId());
			this.updateByPrimaryKeySelective(member, memberId);
		}
	}

	/**
	 *
	 * <财务确认发放奖金><功能具体实现>
	 * 
	 * @param memberId
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年4月12日 下午2:17:58
	 */
	@Transactional(readOnly = false)
	public void confirmBonus(String memberId, String gameId) throws Exception {
		EnjoyGame enjoyGame = this.selectByPrimaryKey(EnjoyGame.class, gameId);
		Map<String, Object> root = new HashMap<String, Object>();
		if (enjoyGame != null) {
			if (StringUtils.isNotBlank(enjoyGame.getGid())) {
				EnjoyGame tmp = this.selectByPrimaryKey(EnjoyGame.class, enjoyGame.getGid());
				enjoyGame.setStatiumName(tmp.getStatiumName());
				enjoyGame.setProvince(tmp.getProvince());
				enjoyGame.setCity(tmp.getCity());
				if (tmp.getCity().contains("市辖区")) {
					enjoyGame.setCity(tmp.getProvince());
				}
				enjoyGame.setHoldTimes(tmp.getHoldTimes());
			}
			String gameType = "";
			switch (enjoyGame.getGameType()) {
			case 1:
				gameType = "男单";
				break;
			case 2:
				gameType = "女单";
				break;
			case 3:
				gameType = "男双";
				break;
			case 4:
				gameType = "女双";
				break;
			case 5:
				gameType = "混双";
				break;
			case 6:
				gameType = "混单";
				break;
			case 7:
				gameType = "无性别限制双打";
				break;
			}
			EnjoyMember member = this.selectByPrimaryKey(EnjoyMember.class, memberId);
			BigDecimal bigDecimal = new BigDecimal(member.getBonus());
			BigDecimal decimal = new BigDecimal(100);
			int bonus = bigDecimal.multiply(decimal).intValue();
			GamesPlayer userA = this.selectByPrimaryKey(GamesPlayer.class, member.getPlayerId());
			if (member.getDoubleFlag() == 1) {
				GamesPlayer userB = this.selectByPrimaryKey(GamesPlayer.class, member.getTeammate());
				if (userB != null) {
					if (StringUtils.isNotBlank(userB.getUserId())) {
						EnjoyMemberCriteria memberC_B = new EnjoyMemberCriteria();
						EnjoyMemberCriteria.Criteria memberCri_B = memberC_B.createCriteria();
						memberCri_B.andPlayerIdEqualTo(userB.getId());
						memberCri_B.andGameIdEqualTo(gameId);
						memberCri_B.andTeammateEqualTo(userA.getId());
						// 正常肯定会有记录
						EnjoyMember memberB = enjoyMemberMapper.selectByExample(memberC_B).get(0);
						UserAccount acountB = this.selectByPrimaryKey(UserAccount.class, userB.getUserId());
						SsoUserBonusAccount bonusAccountB = this.selectByPrimaryKey(SsoUserBonusAccount.class,
								userB.getUserId());
						int resultB = 0;
						if (acountB == null) {
							// 创建账户
							acountB = new UserAccount();
							acountB.setUserId(userB.getUserId());
							acountB.setBalance(0);
							acountB.setFrozen(0); // 冻结金额为不能消费且不能体现
							acountB.setIsfreeze(0); // 0为正常账户，1为冻结账户
							acountB.setCt(new Date());
							acountB.setEt(new Date());
							acountB.setCb(SessionUtil.currentUserId());
							acountB.setEb(SessionUtil.currentUserId());
							resultB = this.insertSelective(acountB, userB.getUserId());
						}

						if (bonusAccountB == null) {
							// 创建账户
							bonusAccountB = new SsoUserBonusAccount();
							bonusAccountB.setUserId(userB.getUserId());
							bonusAccountB.setBalance(bonus);
							bonusAccountB.setFrozen(0); // 冻结金额为不能消费且不能体现
							bonusAccountB.setIsfreeze(0); // 0为正常账户，1为冻结账户
							bonusAccountB.setCt(new Date());
							bonusAccountB.setEt(new Date());
							bonusAccountB.setCb(SessionUtil.currentUserId());
							bonusAccountB.setEb(SessionUtil.currentUserId());
							resultB = this.insertSelective(bonusAccountB, userB.getUserId());
						} else {
							// 发放奖金
							bonusAccountB.setBalance(bonusAccountB.getBalance() + bonus);
							bonusAccountB.setEb(SessionUtil.currentUserId());
							bonusAccountB.setEt(new Date());
							resultB = this.updateByPrimaryKeySelective(bonusAccountB, bonusAccountB.getUserId());
						}
						// 记录发放日志
						if (resultB > 0) {
							//
							memberB.setRank(member.getRank());
							memberB.setIntegral(member.getIntegral());
							memberB.setIsIssueBonus(1);
							//@date 2017.1.12    设置奖金状态时同时设置证书状态
							memberB.setIsIssueCertificate(1);
							memberB.setEt(new Date());
							memberB.setEb(SessionUtil.currentUserId());
							this.updateByPrimaryKeySelective(memberB, memberB.getId());
							root.put("eventName", enjoyGame.getCity() + enjoyGame.getStatiumName());
							root.put("holdTime", enjoyGame.getHoldTimes());
							root.put("gameType", gameType);
							root.put("rank", member.getRank());
							root.put("bonus", member.getBonus());
							root.put("phone", userB.getPhone());
							String msgB = FreeMarkerUtils.format("/template/sms/bonusSuccess.ftl", root);
							messageUtil.sendSms(userB.getPhone(), msgB);
							SsoUserBonusAccountLog ualB = new SsoUserBonusAccountLog();
							String ualIdB = UUID.get();
							ualB.setId(ualIdB);
							ualB.setAmount(bonus);
							// 0 奖金，1 奖金消费，2 奖金提现，3 奖金退款，4 其他
							ualB.setType(Constants.BonusAccountUseType.BONUS);
							ualB.setUserId(acountB.getUserId());
							ualB.setBalance(bonusAccountB.getBalance());
							// 0未完成1完成
							ualB.setStatus(1);
							ualB.setDescription("乐享赛奖金发放");
							ualB.setCt(new Date());
							this.insertSelective(ualB, ualIdB);
							UserAccountLog ual = new UserAccountLog();
							String ualId = UUID.get();
							ual.setId(ualId);
							ual.setAmount(bonus);
							// 0 充值，1消费，2 提现，3 退款，4奖金 5其他
							ual.setType(Constants.AccountUseType.BONUS);
							ual.setUserId(acountB.getUserId());
							ual.setBalance(acountB.getBalance() + bonusAccountB.getBalance());
							// 0未完成1完成
							ual.setStatus(1);
							ual.setDescription("乐享赛奖金发放");
							ual.setCt(new Date());
							this.insertSelective(ual, ualId);
						}
					}
				}
			}
			int resultA = 0;
			// 查看报名人账户是否存在
			UserAccount acountA = this.selectByPrimaryKey(UserAccount.class, member.getUserId());
			// 查看报名人奖金账户是否存在
			SsoUserBonusAccount bonusAccount = this.selectByPrimaryKey(SsoUserBonusAccount.class, member.getUserId());
			if (acountA == null) {
				acountA = new UserAccount();
				acountA.setUserId(member.getUserId());
				acountA.setBalance(0);
				acountA.setFrozen(0); // 冻结金额为不能消费且不能体现
				acountA.setIsfreeze(0); // 0为正常账户，1为冻结账户
				acountA.setCt(new Date());
				acountA.setEt(new Date());
				acountA.setCb(SessionUtil.currentUserId());
				acountA.setEb(SessionUtil.currentUserId());
				resultA = this.insertSelective(acountA, member.getUserId());
			}
			if (bonusAccount == null) {
				bonusAccount = new SsoUserBonusAccount();
				bonusAccount.setUserId(member.getUserId());
				bonusAccount.setBalance(bonus);
				bonusAccount.setFrozen(0); // 冻结金额为不能消费且不能体现
				bonusAccount.setIsfreeze(0); // 0为正常账户，1为冻结账户
				bonusAccount.setCt(new Date());
				bonusAccount.setEt(new Date());
				bonusAccount.setCb(SessionUtil.currentUserId());
				bonusAccount.setEb(SessionUtil.currentUserId());
				resultA = this.insertSelective(bonusAccount, member.getUserId());
			} else {
				// 发放奖金
				bonusAccount.setBalance(bonusAccount.getBalance() + bonus);
				bonusAccount.setEb(SessionUtil.currentUserId());
				bonusAccount.setEt(new Date());
				resultA = this.updateByPrimaryKeySelective(bonusAccount, bonusAccount.getUserId());
			}

			// 记录发放日志
			if (resultA > 0) {
				member.setIsIssueBonus(1);
				//@date 2017.1.12    设置奖金状态时同时设置证书状态
				member.setIsIssueCertificate(1);
				member.setEt(new Date());
				member.setEb(SessionUtil.currentUserId());

				this.updateByPrimaryKeySelective(member, member.getId());
				root = new HashMap<String, Object>();
				root.put("eventName", enjoyGame.getCity() + enjoyGame.getStatiumName());
				root.put("holdTime", enjoyGame.getHoldTimes());
				root.put("gameType", gameType);
				root.put("rank", member.getRank());
				root.put("bonus", member.getBonus());
				root.put("phone", userA.getPhone());
				String msgA = FreeMarkerUtils.format("/template/sms/bonusSuccess.ftl", root);
				messageUtil.sendSms(userA.getPhone(), msgA);
				SsoUserBonusAccountLog ualA = new SsoUserBonusAccountLog();
				String ualIdA = UUID.get();
				ualA.setId(ualIdA);
				ualA.setAmount(bonus);
				// 0 奖金，1 奖金消费，2 奖金提现，3 奖金退款，4 其他
				ualA.setType(Constants.BonusAccountUseType.BONUS);
				ualA.setUserId(bonusAccount.getUserId());
				ualA.setBalance(bonusAccount.getBalance());
				// 0未完成1完成
				ualA.setStatus(1);
				ualA.setDescription("乐享赛奖金发放");
				ualA.setCt(new Date());
				this.insertSelective(ualA, ualIdA);
				UserAccountLog ual0 = new UserAccountLog();
				String ualId0 = UUID.get();
				ual0.setId(ualId0);
				ual0.setAmount(bonus);
				// 0 充值，1消费，2 提现，3 退款，4奖金 5其他
				ual0.setType(Constants.AccountUseType.BONUS);
				ual0.setUserId(acountA.getUserId());
				ual0.setBalance(acountA.getBalance() + bonusAccount.getBalance());
				// 0未完成1完成
				ual0.setStatus(1);
				ual0.setDescription("乐享赛奖金发放");
				ual0.setCt(new Date());
				this.insertSelective(ual0, ualId0);
			}
		}
		int bonusType = updateEnjoyGameBonusType(enjoyGame.getId());
		enjoyGame.setIsIssueBonus(bonusType);
		//@date 2017.1.12   更新赛事的证书方法状态
		int certificateType = updateEnjoyGameCertificateType(enjoyGame.getId());
		enjoyGame.setIsIssueCertificate(certificateType);
		this.updateByPrimaryKeySelective(enjoyGame, enjoyGame.getId());
	}

	public int updateEnjoyGameBonusType(String gameId) throws Exception {
		// 所有参赛人数
		EnjoyMemberCriteria memberC = new EnjoyMemberCriteria();
		EnjoyMemberCriteria.Criteria memCri = memberC.createCriteria();
		memCri.andGameIdEqualTo(gameId); // 赛事id
		memCri.andStateEqualTo(1); // 审核通过的人员
		memCri.andUserIdIsNotNull();
		memCri.andBonusGreaterThan(0);

		int countAll = enjoyMemberMapper.countByExample(memberC);
		// 已分配未审核人数
		EnjoyMemberCriteria memberC0 = new EnjoyMemberCriteria();
		EnjoyMemberCriteria.Criteria memCri0 = memberC0.createCriteria();
		memCri0.andGameIdEqualTo(gameId); // 赛事id
		memCri0.andStateEqualTo(1); // 审核通过的人员
		memCri0.andUserIdIsNotNull();
		memCri0.andBonusGreaterThan(0);
		memCri0.andIsIssueBonusEqualTo(0);
		int count0 = enjoyMemberMapper.countByExample(memberC0);
		// 全部发放的人数
		EnjoyMemberCriteria memberC1 = new EnjoyMemberCriteria();
		EnjoyMemberCriteria.Criteria memCri1 = memberC1.createCriteria();
		memCri1.andGameIdEqualTo(gameId); // 赛事id
		memCri1.andStateEqualTo(1); // 审核通过的人员
		memCri1.andUserIdIsNotNull();
		memCri1.andIsIssueBonusEqualTo(1);
		memCri0.andBonusGreaterThan(0);
		int count1 = enjoyMemberMapper.countByExample(memberC1);
		if (countAll == count0) {
			return 0;
		} else if (countAll == count1) {
			return 1;
		} else {
			return 2;
		}
	}
	//@date 2017.1.12   更新赛事的证书方法状态
	public int updateEnjoyGameCertificateType(String gameId) throws Exception {
		// 所有参赛人数
		EnjoyMemberCriteria memberC = new EnjoyMemberCriteria();
		EnjoyMemberCriteria.Criteria memCri = memberC.createCriteria();
		memCri.andGameIdEqualTo(gameId); // 赛事id
		memCri.andStateEqualTo(1); // 审核通过的人员
		memCri.andUserIdIsNotNull();
		memCri.andBonusGreaterThan(0);

		int countAll = enjoyMemberMapper.countByExample(memberC);
		// 已分配未审核人数
		EnjoyMemberCriteria memberC0 = new EnjoyMemberCriteria();
		EnjoyMemberCriteria.Criteria memCri0 = memberC0.createCriteria();
		memCri0.andGameIdEqualTo(gameId); // 赛事id
		memCri0.andStateEqualTo(1); // 审核通过的人员
		memCri0.andUserIdIsNotNull();
		memCri0.andBonusGreaterThan(0);
		memCri0.andIsIssueCertificateEqualTo(0);
		int count0 = enjoyMemberMapper.countByExample(memberC0);
		// 全部发放的人数
		EnjoyMemberCriteria memberC1 = new EnjoyMemberCriteria();
		EnjoyMemberCriteria.Criteria memCri1 = memberC1.createCriteria();
		memCri1.andGameIdEqualTo(gameId); // 赛事id
		memCri1.andStateEqualTo(1); // 审核通过的人员
		memCri1.andUserIdIsNotNull();
		memCri0.andIsIssueCertificateEqualTo(1);
		memCri0.andBonusGreaterThan(0);
		int count1 = enjoyMemberMapper.countByExample(memberC1);
		if (countAll == count0) {
			return 0;
		} else if (countAll == count1) {
			return 1;
		} else {
			return 2;
		}
	}

	/**
	 *
	 * <><功能具体实现>
	 * 
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年5月7日 下午6:39:46
	 */
	public EnjoyMember getEnjoyMemberByGameIdAndPlayerId(String gameId, String playerId, Integer type, Integer state)
			throws Exception {
		EnjoyMember member = new EnjoyMember();
		EnjoyMemberCriteria criteria = new EnjoyMemberCriteria();
		EnjoyMemberCriteria.Criteria criteria2 = criteria.createCriteria();
		if (type == 0) {
			criteria2.andPlayerIdEqualTo(playerId);
		} else {
			criteria2.andTeammateEqualTo(playerId);
		}
		criteria2.andGameIdEqualTo(gameId);
		if (state == 0) {
			criteria2.andStateIn(Arrays.asList(0, 1));
		} else {
			criteria2.andStateEqualTo(1);
		}
		if (enjoyMemberMapper.countByExample(criteria) > 0) {
			member = enjoyMemberMapper.selectByExample(criteria).get(0);
		}
		return member;
	}

	/**
	 *
	 * <修改报名人员信息><功能具体实现>
	 * 
	 * @param vo
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年5月7日 下午6:53:26
	 */
	@Transactional(readOnly = false)
	public void updatePlayer(EnjoyMemberVo vo) throws Exception {
		String ctaIntegral = vo.getCtaIntegral();
		if (StringUtils.isNotBlank(vo.getIdB())) {
			GamesPlayer playerB = this.selectByPrimaryKey(GamesPlayer.class, vo.getIdB());
			if (playerB != null) {
				playerB.setCardNo(vo.getCardNoB());
				playerB.setSex(vo.getSexB());
				playerB.setName(vo.getNameB());
				this.updateByPrimaryKeySelective(playerB, playerB.getId());
				SsoUser userB = this.selectByPrimaryKey(SsoUser.class, playerB.getUserId());
				if (userB != null) {
					userB.setPhone(vo.getPhoneB());
					userB.setUsername(vo.getPhoneB());
					this.updateByPrimaryKeySelective(userB, userB.getId());
				}
				EnjoyMember memberB = this.getEnjoyMemberByGameIdAndPlayerId(vo.getEliId(), playerB.getId(), 1, 0);
				if (memberB != null) {
					memberB.setCtaIntegral(ctaIntegral);
					;
					memberB.setEt(new Date());
					memberB.setEb(SessionUtil.currentUserId());
					this.updateByPrimaryKeySelective(memberB, memberB.getId());
				}
			}
		}
		GamesPlayer playerA = this.selectByPrimaryKey(GamesPlayer.class, vo.getIdA());
		if (playerA != null) {
			playerA.setCardNo(vo.getCardNoA());
			playerA.setSex(vo.getSexA());
			playerA.setName(vo.getNameA());
			this.updateByPrimaryKeySelective(playerA, playerA.getId());
			SsoUser userA = this.selectByPrimaryKey(SsoUser.class, playerA.getUserId());
			if (userA != null) {
				userA.setPhone(vo.getPhoneA());
				userA.setUsername(vo.getPhoneA());
				this.updateByPrimaryKeySelective(userA, userA.getId());
			}
			EnjoyMember memberA = this.getEnjoyMemberByGameIdAndPlayerId(vo.getEliId(), playerA.getId(), 0, 1);
			if (memberA != null) {
				memberA.setCtaIntegral(ctaIntegral);
				memberA.setEt(new Date());
				memberA.setEb(SessionUtil.currentUserId());
				this.updateByPrimaryKeySelective(memberA, memberA.getId());
			}
		}
	}

	/**
	 *
	 * <奖金审核日子><功能具体实现>
	 * 
	 * @param memberId
	 * @param cb
	 * @param action
	 * @param reason
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年5月9日 下午2:09:26
	 */
	@Transactional(readOnly = false)
	public void createAuditLog(String memberId, String cb, String action, String reason) throws Exception {
		Date now = DateUtils.now();
		StatiumAuditLog statiumAuditLog = new StatiumAuditLog();
		statiumAuditLog.setId(UUID.get());
		statiumAuditLog.setCb(cb);
		statiumAuditLog.setCt(now);
		statiumAuditLog.setDescription(reason);
		statiumAuditLog.setAction(action);
		statiumAuditLog.setStatiumId(memberId);
		super.insertSelective(statiumAuditLog, statiumAuditLog.getId());
	}

	/**
	 * 汇总积分
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void collectIntegral(String id) throws Exception {
		EnjoyGame game = this.selectByPrimaryKey(EnjoyGame.class, id);

		String siteId = game.getSiteId();
		EnjoyGameSite site = this.selectByPrimaryKey(EnjoyGameSite.class, siteId);

		String city = site.getCity();
		if ("市辖区".equals(city)) {
			city = site.getProvince();
		}

		int gameType = game.getGameType();

		if (gameType == 1 || gameType == 2 || gameType == 6) {
			gameType = 1;
		} else {
			gameType = 2;
		}

		EnjoyMemberCriteria criteria = new EnjoyMemberCriteria();
		EnjoyMemberCriteria.Criteria cri = criteria.createCriteria();

		cri.andGameIdEqualTo(id);
		cri.andUserIdIsNotNull();
		cri.andStateEqualTo(1);

		List<EnjoyMember> members = enjoyMemberMapper.selectByExample(criteria);

		List<String> integrals = new ArrayList<String>();
		for (EnjoyMember member : members) {
			GamesPlayer player = this.selectByPrimaryKey(GamesPlayer.class, member.getPlayerId());
			integrals.add(member.getPlayerId() + SPLIT + player.getName() + SPLIT + player.getSex());
			if (member.getDoubleFlag() == 1) {
				String teammateId = member.getTeammate();
				player = this.selectByPrimaryKey(GamesPlayer.class, teammateId);
				integrals.add(teammateId + SPLIT + player.getName() + player.getSex());
			}
		}

		for (String playerIntegral : integrals) {
			String playerId = playerIntegral.split(SPLIT)[0];
			String name = playerIntegral.split(SPLIT)[1];
			int gender = Integer.parseInt(playerIntegral.split(SPLIT)[2]);
			int gameType_ = 0;
			if (gender == 1 && gameType == 1) {
				gameType_ = 1;
			} else if (gender == 0 && gameType == 1) {
				gameType_ = 2;
			} else if (gender == 1 && gameType == 2) {
				gameType_ = 3;
			} else if (gender == 0 && gameType == 2) {
				gameType_ = 4;
			}
			Map<String, Object> paras = new HashMap<String, Object>();
			paras.put("city", city);
			paras.put("playId", playerId);
			String sqlList = FreeMarkerUtils.format("/template/game/member_integral.ftl", paras);
			List<Map<String, Object>> memberIntegers = jdbcTemplate.queryForList(sqlList);
			Double integral = (Double) (memberIntegers.get(0).get("integral"));
			String key = MD5Util.MD5Encode(playerId + "_" + city + "_" + gameType_, "utf-8");
			EnjoySiteIntegral siteIntegral = this.selectByPrimaryKey(EnjoySiteIntegral.class, key);
			if (siteIntegral == null) {
				siteIntegral = new EnjoySiteIntegral();
				siteIntegral.setId(key);
				siteIntegral.setPlayerId(playerId);
				siteIntegral.setCity(city);
				siteIntegral.setName(name);
				siteIntegral.setIntegral(integral.toString());
				siteIntegral.setGameType(gameType_);
				this.insertSelective(siteIntegral, key);
			} else {
				siteIntegral.setIntegral(integral.toString());
				this.updateByPrimaryKeySelective(siteIntegral, key);
			}
		}

	}

	public Map<String, List<EnjoyGroupSchedule>> getScoreByField(String id) {
		Map<String, List<EnjoyGroupSchedule>> scoreByField = new HashMap<String, List<EnjoyGroupSchedule>>();

		EnjoyGroupScheduleCriteria criteria = new EnjoyGroupScheduleCriteria();
		EnjoyGroupScheduleCriteria.Criteria cri = criteria.createCriteria();

		cri.andGameIdEqualTo(id);

		List<EnjoyGroupSchedule> schedules = enjoyGroupScheduleMapper.selectByExample(criteria);

		for (EnjoyGroupSchedule schedule : schedules) {
			if (scoreByField.containsKey(schedule.getFieldNo())) {
				List<EnjoyGroupSchedule> tmp = scoreByField.get(schedule.getFieldNo());
				tmp.add(schedule);
			} else {
				List<EnjoyGroupSchedule> tmp = new ArrayList<EnjoyGroupSchedule>();
				tmp.add(schedule);
				scoreByField.put(schedule.getFieldNo(), tmp);
			}
		}
		return scoreByField;
	}

	/**
	 * <修改报名人员历史信息><功能具体实现>
	 * 
	 * @param pageable
	 * @param searchParams
	 * @return
	 * @throws Exception
	 */
	public PageImpl<EnjoyMemberVo> playersList(Pageable pageable, Map<String, Object> searchParams) throws Exception {
		List<EnjoyMemberVo> list = new ArrayList<EnjoyMemberVo>();
		GamesPlayerCriteria c = new GamesPlayerCriteria();
		GamesPlayerCriteria.Criteria cri = c.createCriteria();
		if (searchParams != null) {
			String phone = searchParams.get("EQ_phone") != null ? searchParams.get("EQ_phone").toString() : null;
			if (org.apache.commons.lang.StringUtils.isNotEmpty(phone)) {
				searchParams.remove("EQ_phone");
				SsoUserCriteria userC = new SsoUserCriteria();
				SsoUserCriteria.Criteria userCri = userC.createCriteria();
				userCri.andPhoneEqualTo(phone);
				if (ssoUserMapper.countByExample(userC) > 0) {
					cri.andUserIdEqualTo(ssoUserMapper.selectByExample(userC).get(0).getId());
				}
			}
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			Criterias.bySearchFilter(cri, filters.values());
		}
		c.setMysqlOffset(pageable.getOffset());
		c.setMysqlLength(pageable.getPageSize());
		int total = gamesPlayerMapper.countByExample(c);
		List<GamesPlayer> playerList = new ArrayList<GamesPlayer>();
		if (total > 0) {
			playerList = gamesPlayerMapper.selectByExample(c);
			for (GamesPlayer tmp : playerList) {
				EnjoyMemberVo vo = new EnjoyMemberVo();
				vo.setIdA(tmp.getId());
				SsoUser user = this.selectByPrimaryKey(SsoUser.class, tmp.getUserId());
				if (user != null) {
					vo.setPhoneA(user.getPhone());
					vo.setState(user.getAuthority());
				} else {
					vo.setPhoneA(tmp.getPhone());
					vo.setState(0);
				}
				vo.setCardNoA(tmp.getCardNo());
				vo.setSexA(tmp.getSex());
				vo.setNameA(tmp.getName());
				vo.setEliId(tmp.getUserId()); // ssouserId
				list.add(vo);
			}
		}
		return new PageImpl<>(list, pageable, total);
	}

	public List<EnjoyGame> getSub(String gameId) throws Exception {
		EnjoyGameCriteria criteria = new EnjoyGameCriteria();
		EnjoyGameCriteria.Criteria cri = criteria.createCriteria();
		cri.andGidEqualTo(gameId);
		List<EnjoyGame> subs = enjoyGameMapper.selectByExample(criteria);
		return subs;
	}

	public Map<String, Object> getSchedules(String gameId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			EnjoyGame game = this.selectByPrimaryKey(EnjoyGame.class, gameId);
			String gid = "";
			if (StringUtils.isNotBlank(game.getGid())) {
				gid = game.getGid();
				game = this.selectByPrimaryKey(EnjoyGame.class, gid);
			} else {
				gid = game.getId();
			}
			List<EnjoyGame> games = this.getSub(gid);
			games.add(game);
			Collections.sort(games, new Comparator<EnjoyGame>() {
				@Override
				public int compare(EnjoyGame arg0, EnjoyGame arg1) {
					if (arg0.getGameType() > arg1.getGameType()) {
						return 1;
					} else if (arg0.getGameType() < arg1.getGameType()) {
						return -1;
					} else {
						return 0;
					}
				}
			});
			result.put("games", games);
			List<String> gameIds = new ArrayList<String>();
			for (EnjoyGame tmp : games) {
				gameIds.add(tmp.getId());
			}
			Map<String, Object> paras = new HashMap<String, Object>();
			paras.put("gameIds", gameIds.toArray(new String[gameIds.size()]));
			String sqlList = FreeMarkerUtils.format("/template/game/game_field_list.ftl", paras);
			List<Map<String, Object>> schedules = jdbcTemplate.queryForList(sqlList);
			TreeMap<String, List<Map<String, Object>>> schedule_fields = new TreeMap<String, List<Map<String, Object>>>();
			for (Map<String, Object> schedule : schedules) {
				String gameId_ = (String) schedule.get("game_id");
				EnjoyGame game_ = this.selectByPrimaryKey(EnjoyGame.class, gameId_);
				schedule.put("game_type", game_.getGameType());
				String p1 = (String) schedule.get("clasli1");
				EnjoyMember m1 = enjoyMemberMapper.selectByPrimaryKey(p1);
				String playerId1 = m1.getPlayerId();
				GamesPlayer player1 = this.selectByPrimaryKey(GamesPlayer.class, playerId1);

				int doubleFlag = m1.getDoubleFlag();
				if (doubleFlag == 1) {
					String pid_ = m1.getTeammate();
					GamesPlayer pl_ = gamesPlayerMapper.selectByPrimaryKey(pid_);
					schedule.put("p1", player1.getName() + "/" + pl_.getName());
				} else {
					schedule.put("p1", player1.getName());
				}

				String p2 = (String) schedule.get("clasli2");
				EnjoyMember m2 = enjoyMemberMapper.selectByPrimaryKey(p2);
				String playerId2 = m2.getPlayerId();
				GamesPlayer player2 = gamesPlayerMapper.selectByPrimaryKey(playerId2);

				doubleFlag = m2.getDoubleFlag();
				if (doubleFlag == 1) {
					String pid_ = m2.getTeammate();
					GamesPlayer pl_ = gamesPlayerMapper.selectByPrimaryKey(pid_);
					schedule.put("p2", player2.getName() + "/" + pl_.getName());
				} else {
					schedule.put("p2", player2.getName());
				}

				if (schedule_fields.containsKey((String) schedule.get("field_no"))) {
					schedule_fields.get((String) schedule.get("field_no")).add(schedule);
				} else {
					List<Map<String, Object>> schedules_ = new ArrayList<Map<String, Object>>();
					schedules_.add(schedule);
					schedule_fields.put((String) schedule.get("field_no"), schedules_);
				}
			}
			result.put("schedule_fields", schedule_fields);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.put("exception", e);
		}
		return result;
	}

	@Transactional(readOnly = false)
	public Map<String, Object> setGameSchedule(List<GameScheduleVo> gameSchedules) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		ScheduleArrange arrange = new ScheduleArrange(MyGson.getInstance().toJson(gameSchedules),
				SessionUtil.currentUserId());
		Map<String, Object> schedules = arrange.produce();
		result.put(Constants.Result.RESULT, true);
		result.put(Constants.Result.DATA, schedules);
		return result;
	}

	@Transactional(readOnly = false)
	public void saveSchedule(String gameId, Map<String, Object> gameSchedule) throws Exception {
		logger.debug("schedule,{}", MyGson.getInstance().toJson(gameSchedule));
		GameDraw draw = new GameDraw();
		if (gameSchedule.containsKey("fileds")) {
			draw.saveFieldSchedules((Map<Integer, List<Map<String, Object>>>) gameSchedule.get("fileds"));
		}
		if (gameSchedule.containsKey("groups")) {
			draw.saveGroups((Map<String, Object>) gameSchedule.get("groups"));
		}

		List<Map<String, Object>> gameSchedules = (List<Map<String, Object>>) gameSchedule.get("games");

		for (Map<String, Object> vo : gameSchedules) {
			Type type = new TypeToken<GameScheduleVo>() {
			}.getType();
			GameScheduleVo vo_ = MyGson.getInstance().fromJson(MyGson.getInstance().toJson(vo), type);
			EnjoyGame game = new EnjoyGame();
			game.setId(vo_.getGameId());
			game.setGameState(1);
			game.setGameFormat(Integer.parseInt(vo_.getGameFormat()));
			game.setGameFormat2(Integer.parseInt(vo_.getGameFormat1()));
			this.updateByPrimaryKeySelective(game, game.getId());
		}

	}

	public List<EnjoyGameVo> balanceList(String gameId) throws Exception {
		EnjoyGame game = this.selectByPrimaryKey(EnjoyGame.class, gameId);
		int gameLevel = game.getGameLevel();
		String name = game.getName();
		String start = game.getStartTime();
		String end = game.getEndTime();
		String gid = "";
		if (StringUtils.isNotBlank(game.getGid())) {
			gid = game.getGid();
			game = this.selectByPrimaryKey(EnjoyGame.class, gid);
		} else {
			gid = game.getId();
		}
		List<EnjoyGame> games = new ArrayList<EnjoyGame>();
		games.add(game);
		games.addAll(this.getSub(gid));
		EnjoyGameVo vo = null;
		List<EnjoyGameVo> vos = new ArrayList<EnjoyGameVo>();
		int orderCnt_ = 0;
		int cbNum_ = 0;
		for (EnjoyGame g : games) {
			vo = new EnjoyGameVo();
			BeanUtils.copyProperties(g, vo);
			vo.setName(name);
			vo.setStartTime(start);
			vo.setEndTime(end);
			vo.setGameLevel(gameLevel);
			String id = g.getId();
			OrderCriteria criteria = new OrderCriteria();
			OrderCriteria.Criteria cri = criteria.createCriteria();
			cri.andStatiumIdEqualTo(id);
			List<String> statuses = new ArrayList<String>();
			statuses.add(Constants.OrderStatus.PAIED);
			statuses.add(Constants.OrderStatus.VERIFY);
			statuses.add(Constants.OrderStatus.PLAYING);
			cri.andStatusIn(statuses);
			List<Order> orders = orderMapper.selectByExample(criteria);
			int orderCnt = 0;
			for(Order order : orders){
				logger.debug("g.getPrice(),{}",g.getPrice());
				logger.debug("order.getFee(),{}",order.getFee());
				logger.debug("g.getPrice()==order.getFee(),{}",g.getPrice()==order.getFee());
				if(g.getPrice().equals(order.getFee())){
					orderCnt += 2;
				}else{
					orderCnt += 1;
				}
			}
			orderCnt = orderCnt/2;
			orderCnt_+=orderCnt;
			vo.setOrderCnt(orderCnt);
			int price = g.getPrice() / 100;
			int base = 1;
			int gameType = vo.getGameType();
			if (gameType == 1) {
			} else if (gameType == 2) {

			} else if (gameType == 3) {
				base = 2;
			} else if (gameType == 4) {
				base = 2;
			} else if (gameType == 5) {
				base = 2;
			} else if (gameType == 6) {
				
			}else if (gameType == 7) {
				base = 2;
			}

			if (vo.getGameLevel() == 1) {
				vo.setCbNum((price - 40 * base) * orderCnt);
			} else if (vo.getGameLevel() == 2) {
				vo.setCbNum((price - 30 * base) * orderCnt);
			} else if (vo.getGameLevel() == 3) {
				vo.setCbNum((price - 20 * base) * orderCnt);
			} else if (vo.getGameLevel() == 4) {
				vo.setCbNum((price - 10 * base) * orderCnt);
			}
			cbNum_ += vo.getCbNum();
			vos.add(vo);
		}
		vo = new EnjoyGameVo();
		vo.setName("总计");
		vo.setOrderCnt(orderCnt_);
		vo.setCbNum(cbNum_);
		vos.add(vo);
		return vos;

	}

	public TreeMap<String, List<Map<String, Object>>> getScheduleFields(String gameId) {
		try {
			EnjoyGame game = this.selectByPrimaryKey(EnjoyGame.class, gameId);
			String gid = "";
			if (StringUtils.isNotBlank(game.getGid())) {
				gid = game.getGid();
				game = this.selectByPrimaryKey(EnjoyGame.class, gid);
			} else {
				gid = game.getId();
			}
			List<EnjoyGame> games = this.getSub(gid);
			games.add(game);
			Collections.sort(games, new Comparator<EnjoyGame>() {
				@Override
				public int compare(EnjoyGame arg0, EnjoyGame arg1) {
					if (arg0.getGameType() > arg1.getGameType()) {
						return 1;
					} else if (arg0.getGameType() < arg1.getGameType()) {
						return -1;
					} else {
						return 0;
					}
				}
			});
			List<String> gameIds = new ArrayList<String>();
			for (EnjoyGame tmp : games) {
				gameIds.add(tmp.getId());
			}
			Map<String, Object> paras = new HashMap<String, Object>();
			paras.put("gameIds", gameIds.toArray(new String[gameIds.size()]));
			String sqlList = FreeMarkerUtils.format("/template/game/game_field_list.ftl", paras);
			List<Map<String, Object>> schedules = jdbcTemplate.queryForList(sqlList);
			TreeMap<String, List<Map<String, Object>>> schedule_fields = new TreeMap<String, List<Map<String, Object>>>();
			for (Map<String, Object> schedule : schedules) {
				String gameId_ = (String) schedule.get("game_id");
				EnjoyGame game_ = this.selectByPrimaryKey(EnjoyGame.class, gameId_);
				schedule.put("game_type", game_.getGameType());
				String p1 = (String) schedule.get("clasli1");
				EnjoyMember m1 = enjoyMemberMapper.selectByPrimaryKey(p1);
				String playerId1 = m1.getPlayerId();
				GamesPlayer player1 = this.selectByPrimaryKey(GamesPlayer.class, playerId1);

				int doubleFlag = m1.getDoubleFlag();
				if (doubleFlag == 1) {
					String pid_ = m1.getTeammate();
					GamesPlayer pl_ = gamesPlayerMapper.selectByPrimaryKey(pid_);
					schedule.put("p1", player1.getName() + "、" + pl_.getName());
				} else {
					schedule.put("p1", player1.getName());
				}

				String p2 = (String) schedule.get("clasli2");
				EnjoyMember m2 = enjoyMemberMapper.selectByPrimaryKey(p2);
				String playerId2 = m2.getPlayerId();
				GamesPlayer player2 = gamesPlayerMapper.selectByPrimaryKey(playerId2);

				doubleFlag = m2.getDoubleFlag();
				if (doubleFlag == 1) {
					String pid_ = m2.getTeammate();
					GamesPlayer pl_ = gamesPlayerMapper.selectByPrimaryKey(pid_);
					schedule.put("p2", player2.getName() + "、" + pl_.getName());
				} else {
					schedule.put("p2", player2.getName());
				}

				if (schedule_fields.containsKey((String) schedule.get("field_no"))) {
					schedule_fields.get((String) schedule.get("field_no")).add(schedule);
				} else {
					List<Map<String, Object>> schedules_ = new ArrayList<Map<String, Object>>();
					schedules_.add(schedule);
					schedule_fields.put((String) schedule.get("field_no"), schedules_);
				}
			}
			return schedule_fields;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public EnjoyGame getGameById(String gameId) throws Exception {
		EnjoyGame game = this.selectByPrimaryKey(EnjoyGame.class, gameId);
		if (game != null) {
			if (StringUtils.isNotBlank(game.getGid())) {
				EnjoyGame game2 = this.selectByPrimaryKey(EnjoyGame.class, game.getGid());
				if (game2 != null) {
					game.setGameLevel(game2.getGameLevel());
				}
			}
		}
		return game;
	}

}
