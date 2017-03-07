package com.lc.zy.ball.boss.framework.event.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.Zonemap;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.corps.vo.CorpsVo;
import com.lc.zy.ball.boss.framework.event.vo.CorpsInfoVo;
import com.lc.zy.ball.boss.framework.event.vo.GamesCorpVo;
import com.lc.zy.ball.boss.framework.event.vo.GamesMemberVo;
import com.lc.zy.ball.boss.framework.event.vo.GamesScheduleVo;
import com.lc.zy.ball.boss.framework.event.vo.GamesScheduleVo.GroupInfo;
import com.lc.zy.ball.boss.framework.event.vo.ScheduleVo;
import com.lc.zy.ball.boss.framework.event.vo.ScoreInfoVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.CorpsInfoMapper;
import com.lc.zy.ball.domain.oa.mapper.GamesCorpsMapper;
import com.lc.zy.ball.domain.oa.mapper.GamesMapper;
import com.lc.zy.ball.domain.oa.mapper.GamesMemberMapper;
import com.lc.zy.ball.domain.oa.mapper.GamesScheduleMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderMapper;
import com.lc.zy.ball.domain.oa.mapper.SsoUserMapper;
import com.lc.zy.ball.domain.oa.mapper.ex.GamesScheduleMapperEx;
import com.lc.zy.ball.domain.oa.po.CorpsInfo;
import com.lc.zy.ball.domain.oa.po.CorpsInfoCriteria;
import com.lc.zy.ball.domain.oa.po.Games;
import com.lc.zy.ball.domain.oa.po.GamesCorps;
import com.lc.zy.ball.domain.oa.po.GamesCorpsCriteria;
import com.lc.zy.ball.domain.oa.po.GamesCriteria;
import com.lc.zy.ball.domain.oa.po.GamesSchedule;
import com.lc.zy.ball.domain.oa.po.GamesScheduleCriteria;
import com.lc.zy.ball.domain.oa.po.Order;
import com.lc.zy.ball.domain.oa.po.OrderCriteria;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.SsoUserCriteria;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.common.Constants;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.FreeMarkerUtils;
import com.lc.zy.common.util.UUID;

/**
 * 运行管理 - 赛事管理
 * 
 * @author liangsh
 *
 */
@Component
@Transactional(readOnly = true)
public class EventService extends AbstractCacheService {
	private static Logger logger = LoggerFactory.getLogger(EventService.class);

	@Autowired
	private GamesMapper gamesMapper;

	@Autowired
	private GamesMemberMapper gamesMemberMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private GamesCorpsMapper gamesCorpsMapper;

	@Autowired
	private GamesScheduleMapper gamesScheduleMapper;

	@Autowired
	private SsoUserMapper ssoUserMapper;

	@Autowired
	private CorpsInfoMapper corpsInfoMapper;

	@Resource(name = "oaJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private GamesScheduleMapperEx gamesScheduleMapperEx;

	/**
	 * 
	 * <获取赛事列表><功能具体实现>
	 *
	 * @create：2015年10月15日 下午3:08:57 @author： liangsh
	 * @param searchParams
	 * @param page
	 * @param size
	 * @param isPage
	 * @return
	 * @throws Exception
	 */
	public List<Games> find(Map<String, Object> searchParams, boolean isPage) throws Exception {
		GamesCriteria cc = new GamesCriteria();
		GamesCriteria.Criteria cri = cc.createCriteria();
		cri.andDeleteFlagIsNull();
		if (searchParams != null) {
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			Criterias.bySearchFilter(cri, filters.values());
		}
		cc.setOrderByClause("stick desc,seq desc,ct desc");
		List<Games> list = gamesMapper.selectByExample(cc);
		return list;
	}

	/**
	 * 
	 * <更新或新增赛事><功能具体实现>
	 *
	 * @create：2015年10月15日 下午3:08:32 @author： liangsh
	 * @param games
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public String createOrUpdateEvent(Games games) throws Exception {
		String eventId = "";
		if (games != null) {
			if (StringUtils.isNotEmpty(games.getAreaCode())) {
				Map<String, String> areaMap = Zonemap.split(games.getAreaCode());
				games.setProvince(areaMap.get("province"));
				games.setCity(areaMap.get("city"));
				games.setArea(areaMap.get("area"));
			}

			if (StringUtils.isNotEmpty(games.getScopeAreaCode())) {
				Map<String, String> scopeAreaMap = Zonemap.split(games.getScopeAreaCode());
				games.setScopeProvince(scopeAreaMap.get("province"));
				games.setScopeCity(scopeAreaMap.get("city"));
			}

			String id = "";
			if (CommonUtils.isNotEmpty(games.getId())) {
				eventId = games.getId();
				id = games.getId();
				User user = SessionUtil.currentUser();
				games.setEb(user.getUserId());
				games.setEt(new Date());
				// add by sl 2015-12-16 推送状态
				if (games.getIsPush() == 1) {
					// 若为定时推送，推送状态置为0，未推送状态
					games.setPushState(0);
				} else if (games.getIsPush() == 0) {
					games.setPushState(1);
				}
				this.updateByPrimaryKeySelective(games, id);
			} else {
				id = UUID.get();
				eventId = id;
				games.setId(id);
				User user = SessionUtil.currentUser();
				games.setEb(user.getUserId());
				games.setCt(new Date());
				games.setCb(user.getUserId());
				games.setEt(new Date());
				// add by sl 2015-12-16 推送状态
				if (games.getIsPush() == 1) {
					// 若为定时推送，推送状态置为0，未推送状态
					games.setPushState(0);
				} else if (games.getIsPush() == 0) {
					games.setPushState(1);
				}
				this.insertSelective(games, id);
			}
		}
		return eventId;
	}

	public Games getEvent(String id) throws Exception {
		return gamesMapper.selectByPrimaryKey(id);
	}

	@Transactional(readOnly = false)
	public void update(Games game) throws Exception {
		this.updateByPrimaryKeySelective(game, game.getId());
	}

	/**
	 * 
	 * <个人赛成员列表><功能具体实现>
	 *
	 * @create：2015年10月14日 上午10:18:28 @author： liangsh
	 * @param searchParams
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<GamesMemberVo> findMemberList(Map<String, Object> searchParams, String id) throws Exception {
		Games games = gamesMapper.selectByPrimaryKey(id);
		if (games != null) {
			GamesCorpsCriteria c = new GamesCorpsCriteria();
			GamesCorpsCriteria.Criteria cri = c.createCriteria();
			cri.andGamesIdEqualTo(id);
			c.setOrderByClause("ct asc");
			List<GamesCorps> list = gamesCorpsMapper.selectByExample(c);
			if (CollectionUtils.isNotEmpty(list)) {
				List<GamesMemberVo> memberList = new ArrayList<GamesMemberVo>();
				for (GamesCorps gamesCorps : list) {
					String orderId = gamesCorps.getOrderId();
					Order order = this.selectByPrimaryKey(Order.class, orderId);
					if (!com.lc.zy.ball.boss.common.Constants.sta.contains(order.getStatus())) {
						continue;
					}
					if (games.getType() == 0) {
						SsoUser user = this.selectByPrimaryKey(SsoUser.class, gamesCorps.getCorpsId());
						if (user != null) {
							GamesMemberVo vo = new GamesMemberVo();
							vo.setUserId(user.getId());
							vo.setMemberId(gamesCorps.getId());
							vo.setQiuyouNo(user.getQiuyouno());
							vo.setNickName(user.getNickName());
							vo.setPhone(user.getPhone());
							vo.setAddress(user.getAddress());
							vo.setCreateDate(gamesCorps.getCt());
							vo.setStatus(gamesCorps.getStatus());
							vo.setCardId(user.getCardId());
							vo.setName(user.getName());
							vo.setEventId(id);
							memberList.add(vo);
						}
					}
				}
				return memberList;
			} else {
				return new ArrayList<GamesMemberVo>();
			}
		} else {
			return new ArrayList<GamesMemberVo>();
		}
	}

	/**
	 * 
	 * <参赛战队><功能具体实现>
	 *
	 * @create：2015年12月4日 下午6:03:40 @author： liangsh
	 * @param searchParams
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<GamesCorpVo> findGamesCorpsList(Map<String, Object> searchParams, String id) throws Exception {
		Games games = gamesMapper.selectByPrimaryKey(id);
		if (games != null) {
			GamesCorpsCriteria c = new GamesCorpsCriteria();
			GamesCorpsCriteria.Criteria cri = c.createCriteria();
			cri.andGamesIdEqualTo(id);
			c.setOrderByClause("ct asc");
			List<GamesCorps> list = gamesCorpsMapper.selectByExample(c);
			if (CollectionUtils.isNotEmpty(list)) {
				List<GamesCorpVo> CorpsList = new ArrayList<GamesCorpVo>();
				for (GamesCorps gamesCorps : list) {
					String orderId = gamesCorps.getOrderId();
					Order order = this.selectByPrimaryKey(Order.class, orderId);
					if (!com.lc.zy.ball.boss.common.Constants.sta.contains(order.getStatus())) {
						continue;
					}
					if (games.getType() == 1) {
						CorpsInfo corps = this.selectByPrimaryKey(CorpsInfo.class, gamesCorps.getCorpsId());
						if (corps != null) {
							GamesCorpVo vo = new GamesCorpVo();
							BeanUtils.copyProperties(vo, corps);
							vo.setCt(gamesCorps.getCt());
							vo.setGamesId(id);
							vo.setId(gamesCorps.getId());
							CorpsList.add(vo);
						}
					} else {
						SsoUser user = this.selectByPrimaryKey(SsoUser.class, gamesCorps.getCorpsId());
						if (user != null) {
							GamesCorpVo vo = new GamesCorpVo();
							vo.setCaptain(user.getId());
							vo.setName(user.getNickName());
							vo.setCurrentNumber(1);
							vo.setCt(gamesCorps.getCt());
							vo.setGamesId(id);
							vo.setId(gamesCorps.getId());
							vo.setLogo(user.getPhoto());
							CorpsList.add(vo);
						}
					}
				}
				return CorpsList;
			} else {
				return new ArrayList<GamesCorpVo>();
			}
		} else {
			return new ArrayList<GamesCorpVo>();
		}
	}

	public SsoUser getUserByKey(String id) throws Exception {
		return this.selectByPrimaryKey(SsoUser.class, id);
	}

	public List<Order> findOrder(String userId, String eventId) throws Exception {
		OrderCriteria c = new OrderCriteria();
		OrderCriteria.Criteria cri = c.createCriteria();
		cri.andStatiumIdEqualTo(eventId);
		cri.andCustomerIdEqualTo(userId);
		cri.andOrdersTypeEqualTo(Constants.OrderType.EVENT);
		cri.andOrderTypeEqualTo(Constants.OrderStatus.PAIED);
		List<Order> list = orderMapper.selectByExample(c);
		return list;
	}

	/**
	 * 
	 * <审核成员><功能具体实现>
	 *
	 * @create：2015年10月14日 上午10:18:16 @author： liangsh
	 * @param memberId
	 * @param status
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public String updaeMember(String memberId, Integer status) throws Exception {
		GamesCorps member = this.selectByPrimaryKey(GamesCorps.class, memberId);
		Order order = this.selectByPrimaryKey(Order.class, member.getOrderId());
		if (member != null) {
			member.setStatus(status);
			member.setSt(new Date());
			member.setSb(SessionUtil.currentUser().getUserId());
			// 当审核通过时，并且订单已支付时 修改订单状态为已审核
			if (status == 1 && order.getStatus().equals(Constants.OrderStatus.PAIED)) { // 审核通过，已支付的订单
				order.setStatus(Constants.OrderStatus.VERIFY); // 更成已确认订单
				order.setEt(new Date());
				order.setEb(SessionUtil.currentUser().getUserId());
				this.updateByPrimaryKeySelective(order, member.getOrderId());
				this.updateByPrimaryKeySelective(member, memberId);
			} else if (status == 1 && order.getStatus().equals(Constants.OrderStatus.NEW)) {// 审核通过，未支付的订单，客服通知用户取消订单
				return member.getGamesId() + "/" + memberId + "/" + member.getCorpsId() + "/0";
			} else if (status == 2 && (order.getStatus().equals(Constants.OrderStatus.PAIED)
					|| order.getStatus().equals(Constants.OrderStatus.VERIFY))) {// 审核不通过，已支付的订单
				// 审核不通过的未支付的订单，需要先退款
				order.setStatus(Constants.OrderStatus.REFUNDING);
				order.setEt(new Date());
				order.setEb(SessionUtil.currentUser().getUserId());
				this.updateByPrimaryKeySelective(order, member.getOrderId());
				this.updateByPrimaryKeySelective(member, memberId);
			} else {// 审核不通过，未支付的订单
				this.updateByPrimaryKeySelective(member, memberId);
			}
		}
		return member.getGamesId();
	}

	/**
	 * 
	 * <获取赛程列表><功能具体实现>
	 *
	 * @create：2015年12月7日 下午4:53:37 @author： liangsh
	 * @param id
	 * @return
	 */
	public List<GamesScheduleVo> getScheduleList(String gamesId) throws Exception {
		GamesScheduleCriteria c = new GamesScheduleCriteria();
		GamesScheduleCriteria.Criteria cri = c.createCriteria();
		cri.andGamesIdEqualTo(gamesId);
		c.setOrderByClause("turn asc,ct asc,game_time asc");
		List<GamesSchedule> list = gamesScheduleMapper.selectByExample(c);
		List<GamesScheduleVo> voList = new ArrayList<GamesScheduleVo>();
		// 该赛事存在战队
		if (CollectionUtils.isNotEmpty(list)) {
			int turn = 1; // 记录轮次
			String groupName = ""; // 记录小组名称
			GamesScheduleVo vo = new GamesScheduleVo();
			List<CorpsInfoVo> corpsList = new ArrayList<CorpsInfoVo>();
			List<GroupInfo> groupList = new ArrayList<GamesScheduleVo.GroupInfo>();
			for (int i = 0; i < list.size(); i++) {
				GamesSchedule gamesSchedule = list.get(i);
				int tmp_t = gamesSchedule.getTurn();
				String tmp_g = gamesSchedule.getGameGroup();
				if (turn != tmp_t) { // 不同轮次，重新定义战队组集合
					turn = gamesSchedule.getTurn();
					vo.setGroupList(new ArrayList<GamesScheduleVo.GroupInfo>()); // 组集合
					vo.setTurnNo(turn); // 轮次
				}
				if (groupName.equals(tmp_g)) { // 不同组，重新定义战队集合
					corpsList = new ArrayList<CorpsInfoVo>(); // 战队集合
					groupName = gamesSchedule.getGameGroup(); // 组
				}
				// 战队信息
				CorpsInfo corpsA = this.selectByPrimaryKey(CorpsInfo.class, gamesSchedule.getMarinesIdA());
				CorpsInfo corpsB = this.selectByPrimaryKey(CorpsInfo.class, gamesSchedule.getMarinesIdB());
				CorpsInfoVo A = new CorpsInfoVo();
				CorpsInfoVo B = new CorpsInfoVo();
				BeanUtils.copyProperties(A, corpsA);
				BeanUtils.copyProperties(B, corpsB);
				A.setCorpsId(gamesSchedule.getId()); // 删除赛程的唯一标识
				A.setStatus(0); // 默认正常状态，页面若删除设置成 1
				B.setStatus(0); // 默认正常状态，页面若删除设置成 1
				B.setCorpsId(gamesSchedule.getId()); // 删除赛程的唯一标识
				corpsList.add(A);
				corpsList.add(B);
				if (i < list.size() - 1) { // 非最后一条数据则判断下一条数据是否属于同一轮 或 同一组
					int tmp_t2 = list.get(i + 1).getTurn();
					String tmp_g2 = list.get(i + 1).getGameGroup();
					if (turn != tmp_t2) { // 下一条数据，与当前不同轮，将组集合信息放入轮次
						// 战队信息
						GroupInfo groupInfo = new GroupInfo();
						groupInfo.setGroupName(groupName); // 小组名称
						groupInfo.setGameTime(gamesSchedule.getGameTime()); // 小组赛时间
						groupInfo.setType(gamesSchedule.getGameType()); // 小组赛比赛类型
						// 战队信息放入组
						groupInfo.setCorps(corpsList);
						// 组信息放入组集合
						groupList.add(groupInfo);
						// 组集合信息放入轮次
						vo.setGroupList(groupList);
						// 轮次信息放入轮次集合
						voList.add(vo);
					} else if (turn == tmp_t2) { // 同轮次，不同组
						if (!groupName.equals(tmp_g2)) {// 不容组
							// 战队信息
							GroupInfo groupInfo = new GroupInfo();
							groupInfo.setGroupName(groupName); // 小组名称
							groupInfo.setGameTime(gamesSchedule.getGameTime()); // 小组赛时间
							groupInfo.setType(gamesSchedule.getGameType()); // 小组赛比赛类型
							// 战队信息放入组
							groupInfo.setCorps(corpsList);
							// 组信息放入组集合
							groupList.add(groupInfo);
						}
					}
				} else {
					// 战队信息
					GroupInfo groupInfo = new GroupInfo();
					groupInfo.setGroupName(groupName); // 小组名称
					groupInfo.setGameTime(gamesSchedule.getGameTime()); // 小组赛时间
					groupInfo.setType(gamesSchedule.getGameType()); // 小组赛比赛类型
					// 战队信息放入组
					groupInfo.setCorps(corpsList);
					// 组信息放入组集合
					groupList.add(groupInfo);
					// 组集合信息放入轮次
					vo.setGroupList(groupList);
					// 轮次信息放入轮次集合
					voList.add(vo);
				}
			}
		}
		return voList;
	}

	/**
	 * 
	 * <保存赛程><功能具体实现>
	 *
	 * @create：2015年12月7日 下午7:27:32 @author： liangsh
	 * @param gamesScheduleVo
	 * @throws Exception
	 */
	public void saveScheduleList(List<GamesScheduleVo> voList, String eventId) throws Exception {
		for (GamesScheduleVo gamesScheduleVo : voList) { // 遍历每一轮
			if (CollectionUtils.isNotEmpty(gamesScheduleVo.getGroupList())) {
				List<GroupInfo> list = gamesScheduleVo.getGroupList();
				for (GroupInfo groupInfo : list) { // 遍历每轮内组
					List<CorpsInfoVo> corps = groupInfo.getCorps();
					if (CollectionUtils.isNotEmpty(corps)) {
						for (int i = 0; i < corps.size(); i++) {// 遍历每轮内每组内战队
							if ((i + 1) % 2 == 1) {
								CorpsInfoVo corpsVo = corps.get(i);
								if (corpsVo.getStatus() == 1) {
									// 删除A、B对战
									if (StringUtils.isNotBlank(corpsVo.getCorpsId())) {
										this.deleteByPrimaryKey(GamesSchedule.class, corpsVo.getCorpsId());
									}
									i++;
								} else {
									// 保存A、B对战
									GamesSchedule info = new GamesSchedule();
									if (StringUtils.isBlank(corpsVo.getCorpsId())) {// 新增对战
										info.setId(UUID.get());
										info.setCt(new Date());
										info.setCb(SessionUtil.currentUserId());
									} else {// 修改对战
										info.setId(corpsVo.getCorpsId());
									}
									info.setEt(new Date());
									info.setEb(SessionUtil.currentUserId());
									info.setGameGroup(groupInfo.getGroupName()); // 组名
									info.setGamesId(eventId); // 赛事id
									info.setGameTime(groupInfo.getGameTime()); // 比赛时间
									info.setGameType(groupInfo.getType()); // 比赛类型：循环赛、淘汰赛。。。
									info.setMarinesIdA(corpsVo.getId()); // A战队
									info.setMarinesIdB(corps.get(i + 1).getId()); // B战队
									info.setTurn(gamesScheduleVo.getTurnNo()); // 轮次
									this.insertSelective(info, info.getId());
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * <战队列表><功能具体实现>
	 *
	 * @create：2015年12月10日 上午9:44:05 @author： liangsh
	 * @param searchParams
	 * @return
	 * @throws Exception
	 */
	public List<CorpsVo> listCorps(Map<String, Object> searchParams) throws Exception {
		CorpsInfoCriteria criteria = new CorpsInfoCriteria();
		CorpsInfoCriteria.Criteria cri = criteria.createCriteria();
		String EQ_sportType = (String) searchParams.get("EQ_sportType");
		if (StringUtils.isNotBlank(EQ_sportType)) {
			cri.andSportTypeEqualTo(EQ_sportType);
		}
		String EQ_name = (String) searchParams.get("EQ_name");
		if (StringUtils.isNotBlank(EQ_name)) {
			cri.andNameEqualTo(EQ_name);
		}
		String EQ_username = (String) searchParams.get("EQ_username");
		if (StringUtils.isNotBlank(EQ_username)) {
			SsoUserCriteria ssoUserCriteria = new SsoUserCriteria();
			SsoUserCriteria.Criteria ssoCri = ssoUserCriteria.createCriteria();
			ssoCri.andNickNameEqualTo(EQ_username);
			List<SsoUser> users = ssoUserMapper.selectByExample(ssoUserCriteria);
			if (CollectionUtils.isEmpty(users)) {
				return new ArrayList<CorpsVo>();
			}
			SsoUser user = users.get(0);
			String userid = user.getId();
			cri.andCaptainEqualTo(userid);
		}
		int count = corpsInfoMapper.countByExample(criteria);
		if (count == 0) {
			return new ArrayList<CorpsVo>();
		}
		if (searchParams.get("EQ_sortBy") != null && StringUtils.isNotBlank((String) searchParams.get("EQ_sortBy"))) {
			Integer EQ_sortBy = Integer.parseInt((String) searchParams.get("EQ_sortBy"));
			// 当前人数排序
			if (EQ_sortBy == 0) {
				criteria.setOrderByClause("current_number desc");
			} else if (EQ_sortBy == 1) {// 积分升序
				criteria.setOrderByClause("integral asc");
			} else if (EQ_sortBy == 2) {// 积分降序
				criteria.setOrderByClause("integral desc");
			}
		} else {
			criteria.setOrderByClause("integral desc");
		}
		List<CorpsInfo> corpsInfos = corpsInfoMapper.selectByExample(criteria);
		List<CorpsVo> voList = new ArrayList<CorpsVo>();
		CorpsVo vo = null;
		for (CorpsInfo info : corpsInfos) {
			vo = new CorpsVo();
			vo.setId(info.getId());
			vo.setName(info.getName());
			vo.setCurrentNumber(info.getCurrentNumber());
			vo.setSportType(CommonOAUtils.sportsEToC(info.getSportType()));
			SsoUser captain = this.selectByPrimaryKey(SsoUser.class, info.getCaptain());
			vo.setCaptainStr(captain.getNickName());
			vo.setIntegral(info.getIntegral());
			vo.setPhone(info.getPhone());
			vo.setArea(info.getArea());
			vo.setActivityArea(info.getActivityArea());
			vo.setCt(info.getCt());
			Map<String, Object> paras = new HashMap<String, Object>();
			paras.put("corpsId", info.getId());
			String sqlCount = FreeMarkerUtils.format("/template/corps/corps_game_count.ftl", paras);
			List<Map<String, Object>> gameCounts = jdbcTemplate.queryForList(sqlCount);
			int gameCount = new BigDecimal((long) gameCounts.get(0).get("cont")).intValue();
			vo.setGameCount(gameCount);
			paras.clear();
			paras.put("area", info.getArea());
			paras.put("corpsId", info.getId());
			sqlCount = FreeMarkerUtils.format("/template/corps/corps_integral_rank.ftl", paras);
			List<Map<String, Object>> integralNumMap = jdbcTemplate.queryForList(sqlCount);
			Object integralNum = integralNumMap.get(0).get("rank");
			vo.setIntegralNum(String.valueOf(integralNum));
			voList.add(vo);
		}
		return voList;
	}

	/**
	 * 
	 * <获取比赛轮数><功能具体实现>
	 *
	 * @create：2015年12月8日 下午2:45:36 @author： sl
	 * @param gameId
	 * @return
	 * @throws Exception
	 */
	public int getTurnNum(String gameId) throws Exception {
		// GamesScheduleCriteria scheduleCriteria = new GamesScheduleCriteria();
		// scheduleCriteria.createCriteria().andGamesIdEqualTo(gameId);
		// scheduleCriteria.setOrderByClause("group by turn");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gameId", gameId);
		int turnNum = gamesScheduleMapperEx.selectTurnNum(gameId).size();
		return turnNum;
	}

	/**
	 * 
	 * <根据赛事id、轮数获取赛事Num><功能具体实现>
	 *
	 * @create：2015年12月8日 下午4:32:02 @author： sl
	 * @param gameId
	 * @param turnNum
	 * @return
	 */
	public int getGroupNum(String gameId, int turnNum) {
		// GamesScheduleCriteria scheduleCriteria = new GamesScheduleCriteria();
		// scheduleCriteria.createCriteria().andGamesIdEqualTo(gameId);
		// scheduleCriteria.createCriteria().andTurnEqualTo(turnNum);
		// scheduleCriteria.setOrderByClause("group by game_group");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gameId", gameId);
		map.put("turnNum", turnNum);
		int groupNum = gamesScheduleMapperEx.selectGroupNum(map).size();
		return groupNum;
	}

	/**
	 * 
	 * <根据赛事id、轮数获取赛事信息><功能具体实现>
	 *
	 * @create：2015年12月8日 下午4:32:43 @author： sl
	 * @param gameId
	 * @param turnNum
	 * @return
	 */
	public List<GamesSchedule> getGroups(String gameId, int turnNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gameId", gameId);
		map.put("turnNum", turnNum);
		// GamesScheduleCriteria scheduleCriteria = new GamesScheduleCriteria();
		// scheduleCriteria.createCriteria().andGamesIdEqualTo(gameId);
		// scheduleCriteria.createCriteria().andTurnEqualTo(turnNum);
		// scheduleCriteria.setOrderByClause("group by game_group");
		List<GamesSchedule> gamesSchedules = gamesScheduleMapperEx.countGroups(map);
		return gamesSchedules;
	}

	/**
	 * 
	 * <根据赛事id、轮数、组名获取赛事信息><功能具体实现>
	 *
	 * @create：2015年12月8日 下午4:33:13 @author： sl
	 * @param gameId
	 * @param turnNum
	 * @param groupName
	 * @return
	 */
	public List<GamesSchedule> getGroupsByName(String gameId, int turnNum, String groupName) {
		GamesScheduleCriteria scheduleCriteria = new GamesScheduleCriteria();
		scheduleCriteria.createCriteria().andGamesIdEqualTo(gameId).andTurnEqualTo(turnNum)
				.andGameGroupEqualTo(groupName);
		scheduleCriteria.setOrderByClause("game_time asc");
		List<GamesSchedule> gamesSchedules = gamesScheduleMapper.selectByExample(scheduleCriteria);
		return gamesSchedules;
	}

	/**
	 * 
	 * <根据战队id获取战队信息><功能具体实现>
	 *
	 * @create：2015年12月8日 下午5:04:01 @author： sl
	 * @param corpId
	 * @return
	 * @throws Exception
	 */
	public CorpsInfo getCorpsInfoById(String corpId) throws Exception {
		return this.selectByPrimaryKey(CorpsInfo.class, corpId);
	}

	/**
	 * 
	 * <保存赛程><功能具体实现>
	 *
	 * @create：2015年12月16日 下午8:05:34 @author： sl
	 * @param scoreInfoVo
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public void saveScore(ScoreInfoVo scoreInfoVo) throws Exception {
		String[] scores = scoreInfoVo.getScore().split(":");
		GamesSchedule gamesSchedule = new GamesSchedule();
		// 战队A比分
		gamesSchedule.setScoreA(Integer.valueOf(scores[0]));
		// 战队B比分
		gamesSchedule.setScoreB(Integer.valueOf(scores[1]));
		// 战队A积分
		gamesSchedule.setIntegrationA(Integer.valueOf(scoreInfoVo.getInA()));
		// 战队B积分
		gamesSchedule.setIntegrationB(Integer.valueOf(scoreInfoVo.getInB()));
		// id
		gamesSchedule.setId(scoreInfoVo.getgScheduleId());
		if (Integer.valueOf(scores[0]) > Integer.valueOf(scores[1])) {
			gamesSchedule.setVictoryA(1);
			gamesSchedule.setVictoryB(-1);
		} else if (Integer.valueOf(scores[0]) == Integer.valueOf(scores[1])) {
			gamesSchedule.setVictoryA(0);
			gamesSchedule.setVictoryB(0);
		} else if (Integer.valueOf(scores[0]) < Integer.valueOf(scores[1])) {
			gamesSchedule.setVictoryA(-1);
			gamesSchedule.setVictoryB(1);
		}

		gamesSchedule.setGameStatus(1);

		this.updateByPrimaryKeySelective(gamesSchedule, scoreInfoVo.getgScheduleId());

		// 更新战队表
		CorpsInfo corpsInfoANew = new CorpsInfo();
		CorpsInfo corpsInfoBNew = new CorpsInfo();

		// A队信息
		CorpsInfo corpsInfoA = this.selectByPrimaryKey(CorpsInfo.class, scoreInfoVo.getIdA());
		// B队信息
		CorpsInfo corpsInfoB = this.selectByPrimaryKey(CorpsInfo.class, scoreInfoVo.getIdB());

		// A队信息
		corpsInfoANew.setId(scoreInfoVo.getIdA());
		corpsInfoANew.setIntegral(Integer.valueOf(scoreInfoVo.getInA()));
		// B队信息
		corpsInfoBNew.setId(scoreInfoVo.getIdB());
		corpsInfoBNew.setIntegral(Integer.valueOf(scoreInfoVo.getInB()));
		if (Integer.valueOf(scores[0]) > Integer.valueOf(scores[1])) {
			// A队胜、B队败
			// A队信息
			if (corpsInfoA.getWinCount() == null) {
				corpsInfoANew.setWinCount(1);
			} else {
				corpsInfoANew.setWinCount(corpsInfoA.getWinCount() + 1);
			}
			if (corpsInfoA.getTotalNumber() == null) {
				corpsInfoANew.setTotalNumber(1);
			} else {
				corpsInfoANew.setTotalNumber(corpsInfoA.getTotalNumber() + 1);
			}

			// B队信息
			if (corpsInfoB.getLooseCount() == null) {
				corpsInfoBNew.setLooseCount(1);
			} else {
				corpsInfoBNew.setLooseCount(corpsInfoB.getLooseCount() + 1);
			}
			if (corpsInfoB.getTotalNumber() == null) {
				corpsInfoBNew.setTotalNumber(1);
			} else {
				corpsInfoBNew.setTotalNumber(corpsInfoB.getTotalNumber() + 1);
			}
		} else if (Integer.valueOf(scores[0]) == Integer.valueOf(scores[1])) {
			// A队、B队平
			// A队信息
			if (corpsInfoA.getDrawCount() == null) {
				corpsInfoANew.setDrawCount(1);
			} else {
				corpsInfoANew.setDrawCount(corpsInfoA.getDrawCount() + 1);
			}
			if (corpsInfoA.getTotalNumber() == null) {
				corpsInfoANew.setTotalNumber(1);
			} else {
				corpsInfoANew.setTotalNumber(corpsInfoA.getTotalNumber() + 1);
			}

			// B队信息
			if (corpsInfoB.getDrawCount() == null) {
				corpsInfoBNew.setDrawCount(1);
			} else {
				corpsInfoBNew.setDrawCount(corpsInfoB.getDrawCount() + 1);
			}
			if (corpsInfoB.getTotalNumber() == null) {
				corpsInfoBNew.setTotalNumber(1);
			} else {
				corpsInfoBNew.setTotalNumber(corpsInfoB.getTotalNumber() + 1);
			}
		} else if (Integer.valueOf(scores[0]) < Integer.valueOf(scores[1])) {
			// A队负、B队胜
			// A队信息
			if (corpsInfoA.getLooseCount() == null) {
				corpsInfoANew.setLooseCount(1);
			} else {
				corpsInfoANew.setLooseCount(corpsInfoA.getLooseCount() + 1);
			}
			if (corpsInfoA.getTotalNumber() == null) {
				corpsInfoANew.setTotalNumber(1);
			} else {
				corpsInfoANew.setTotalNumber(corpsInfoA.getTotalNumber() + 1);
			}

			// B队信息
			if (corpsInfoB.getWinCount() == null) {
				corpsInfoBNew.setWinCount(1);
			} else {
				corpsInfoBNew.setWinCount(corpsInfoB.getWinCount() + 1);
			}
			if (corpsInfoB.getTotalNumber() == null) {
				corpsInfoBNew.setTotalNumber(1);
			} else {
				corpsInfoBNew.setTotalNumber(corpsInfoB.getTotalNumber() + 1);
			}
		}
		this.updateByPrimaryKeySelective(corpsInfoANew, scoreInfoVo.getIdA());
		this.updateByPrimaryKeySelective(corpsInfoBNew, scoreInfoVo.getIdB());
	}

	public List<ScheduleVo> getScheduleVoList(String gamesId) throws Exception {
		GamesScheduleCriteria c = new GamesScheduleCriteria();
		GamesScheduleCriteria.Criteria cri = c.createCriteria();
		cri.andGamesIdEqualTo(gamesId);
		c.setOrderByClause("turn asc,ct asc,game_time asc");
		List<GamesSchedule> list = gamesScheduleMapper.selectByExample(c);
		List<ScheduleVo> voList = new ArrayList<ScheduleVo>();
		Games games = this.selectByPrimaryKey(Games.class, gamesId);
		// 该赛事存在战队
		if (CollectionUtils.isNotEmpty(list)) {
			for (GamesSchedule gamesSchedule : list) {
				ScheduleVo vo = new ScheduleVo();
				BeanUtils.copyProperties(vo, gamesSchedule);
				String curDate = DateUtil.nowDateString();
				Date cur_Date = DateUtils.getDate(curDate, "yyyy-MM-dd");
				Date start_Date = DateUtils.getDate(gamesSchedule.getGameTime(), "yyyy-MM-dd");
				if (DateUtil.compareDate(cur_Date, start_Date) == 1) {
					vo.setState("已结束");
				} else if (DateUtil.compareDate(cur_Date, start_Date) == -1) {
					vo.setState("未开始");
				} else {
					vo.setState("进行中");
				}
				if (games.getType() == 0) {
					SsoUser userA = this.selectByPrimaryKey(SsoUser.class, vo.getMarinesIdA());
					if (userA != null) {
						vo.setMarinesIdA(userA.getNickName());
					}
					SsoUser userB = this.selectByPrimaryKey(SsoUser.class, vo.getMarinesIdB());
					if (userA != null) {
						vo.setMarinesIdB(userB.getNickName());
					}
				} else {
					CorpsInfo corpsA = this.selectByPrimaryKey(CorpsInfo.class, vo.getMarinesIdA());
					if (corpsA != null) {
						vo.setMarinesIdA(corpsA.getName());
					}

					if (StringUtils.isNotBlank(vo.getMarinesIdB())) { // 循环赛
						CorpsInfo corpsB = this.selectByPrimaryKey(CorpsInfo.class, vo.getMarinesIdB());
						if (corpsB != null) {
							vo.setMarinesIdB(corpsB.getName());
						}
					} else {
						vo.setMarinesIdB("");
					}
				}
				voList.add(vo);
			}
		}
		return voList;
	}

	/**
	 * 
	 * <><赛事统计>
	 * 
	 * @param pageable
	 * @param searchParams
	 * @return
	 * @author liangsh
	 * @date 2015年12月24日 下午5:11:33
	 */
	public Page<Games> eventStatisticsList(PageRequest pageable, Map<String, Object> searchParams, boolean isPage) {
		List<Games> list = new ArrayList<Games>();
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		GamesCriteria c = new GamesCriteria();
		GamesCriteria.Criteria cri = c.createCriteria();
		if (isPage) {
			c.setMysqlLength(pageable.getPageSize());
			c.setMysqlOffset(pageable.getPageNumber());
		}
		c.setOrderByClause(" ct desc");
		if (searchParams != null) {
			Criterias.bySearchFilter(cri, filters.values());
		}
		int total = gamesMapper.countByExample(c);
		list = gamesMapper.selectByExample(c);
		if (CollectionUtils.isEmpty(list)) {
			list = new ArrayList<Games>();
		}
		return new PageImpl<>(list, pageable, total);
	}
}
