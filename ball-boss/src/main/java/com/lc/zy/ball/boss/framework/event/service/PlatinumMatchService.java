package com.lc.zy.ball.boss.framework.event.service;

import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.Zonemap;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.event.vo.EnjoyGameVo;
import com.lc.zy.ball.boss.framework.event.vo.EnjoyMemberVo;
import com.lc.zy.ball.boss.framework.event.vo.PlatinumMatchVo;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.Pageable;
import com.lc.zy.ball.domain.oa.mapper.*;
import com.lc.zy.ball.domain.oa.po.*;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.*;
import com.lc.zy.common.util.UUID;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;
import java.util.*;

/**
 * User: liangsh
 * Date: 2016/6/13
 * Time: 17:55
 */
@Service
@Transactional(readOnly = true)
public class PlatinumMatchService extends AbstractCacheService {
    private static final Logger logger = LoggerFactory.getLogger(PlatinumMatchService.class);

    @Autowired
    private PlatinumMatchMapper platinumMatchMapper;

    @Autowired
    private PlatinumMatchMemberMapper platinumMatchMemberMapper;

    @Autowired
    private QiuyouCardLogMapper qiuyouCardLogMapper;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    public PageImpl<PlatinumMatchVo> list(Pageable pageable, Map<String, Object> searchParams) throws Exception {
        logger.debug(searchParams.toString());
        List<PlatinumMatchVo> voList = new ArrayList<PlatinumMatchVo>();
        PlatinumMatchCriteria criteria = new PlatinumMatchCriteria();
        PlatinumMatchCriteria.Criteria cri = criteria.createCriteria();
        cri.andDeleteFlagEqualTo(0);
        cri.andGidIsNull(); //多个项目的赛事只查询一条
        String city  = searchParams.get("LIKE_city")!=null?searchParams.get("LIKE_city").toString():null;
        if(searchParams.get("LIKE_city")!=null&&(city.contains("上海") || city.contains("北京") || city.contains("天津") || city.contains("重庆"))){
            searchParams.put("LIKE_province", city);
            searchParams.remove("LIKE_city");
        }
        if (searchParams != null) {
            Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
            Criterias.bySearchFilter(cri, filters.values());
        }
        criteria.setMysqlLength(pageable.getPageSize());
        criteria.setMysqlOffset(pageable.getOffset());
        criteria.setOrderByClause("ct desc");
        List<PlatinumMatch> list = platinumMatchMapper.selectByExample(criteria);
        int total = platinumMatchMapper.countByExample(criteria);
        if (CollectionUtils.isEmpty(list)) {
            list = new ArrayList<PlatinumMatch>();
        }else{
            for (PlatinumMatch platinumMatch : list) {
                PlatinumMatchVo vo = new PlatinumMatchVo();
                BeanUtils.copyProperties(platinumMatch,vo);
                vo.setGamesLength(0);
                PlatinumMatchCriteria c2 = new PlatinumMatchCriteria();
                PlatinumMatchCriteria.Criteria cri2 = c2.createCriteria();
                cri2.andDeleteFlagEqualTo(0);
                cri2.andGidEqualTo(vo.getId());
                c2.setOrderByClause("type");
                int total2 = platinumMatchMapper.countByExample(c2);
                List<PlatinumMatch> list2 = new ArrayList<PlatinumMatch>();
                if(total2 > 0){
                    list2 = platinumMatchMapper.selectByExample(c2);
                    vo.setGames(list2);
                    vo.setGamesLength(total2);
                }
                //设置赛事状态
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
                    PlatinumMatchMemberCriteria memberCriteria = new PlatinumMatchMemberCriteria();
                    PlatinumMatchMemberCriteria.Criteria memberCri = memberCriteria.createCriteria();
                    if(CollectionUtils.isNotEmpty(list2)){
                        StringBuilder idBd = new StringBuilder("");
                        for (PlatinumMatch g: list2 ) {
                            idBd.append(g.getId()).append(",");
                        }
                        memberCri.andPlatinumMatchIdIn(Arrays.asList(idBd.append(vo.getId()).toString()));
                    }else{
                        memberCri.andPlatinumMatchIdEqualTo(vo.getId());
                    }
                    if(platinumMatchMemberMapper.countByExample(memberCriteria) > 0){
                        vo.setStateValue("报名中");
                        vo.setStatus(2);
                    }else {
                        vo.setStateValue("");
                        vo.setStatus(-1);
                    }
                } else if (now.compareTo(expiryDate) >= 0) {
                    vo.setStateValue("报名截止");
                    vo.setStatus(3);
                }
                voList.add(vo);
            }
        }
        return new PageImpl<>(voList, pageable, total);
    }

    @Transactional(readOnly = false)
    public String createOrUpdate(PlatinumMatch game,String eventId) throws Exception {
        if (game != null) {
            if (StringUtils.isNotEmpty(game.getAreaCode())) {
                Map<String, String> areaMap = Zonemap.split(game.getAreaCode());
                game.setProvince(areaMap.get("province"));
                game.setCity(areaMap.get("city"));
                game.setArea(areaMap.get("area"));
            }
            if (StringUtils.isBlank(game.getId())) {
                game.setId(com.lc.zy.common.util.UUID.get());
                game.setCt(new Date());
                game.setCb(SessionUtil.currentUserId());
                if(StringUtils.isNotBlank(eventId)){
                    game.setGid(eventId);
                }
                this.insertSelective(game, game.getId());
            } else {
                game.setEt(new Date());
                game.setEb(SessionUtil.currentUserId());
                this.updateByPrimaryKeySelective(game, game.getId());
            }
        }
        return game.getId();
    }

    public PageImpl<EnjoyMemberVo> findMemberList(Pageable pageable, Map<String, Object> searchParams, String gameId)
            throws Exception {
        PlatinumMatchMemberCriteria criteria = new PlatinumMatchMemberCriteria();
        PlatinumMatchMemberCriteria.Criteria cri = criteria.createCriteria();
        if (searchParams != null) {
            Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
            Criterias.bySearchFilter(cri, filters.values());
        }
        cri.andUserIdIsNotNull();
        cri.andStateIn(Arrays.asList(1,2));  //审核通过的和审核不通过的
        cri.andPlatinumMatchIdEqualTo(gameId);
        criteria.setMysqlOffset(pageable.getOffset());
        criteria.setMysqlLength(pageable.getPageSize());
        criteria.setOrderByClause("ct asc");
        List<PlatinumMatchMember> memberList = platinumMatchMemberMapper.selectByExample(criteria);
        List<EnjoyMemberVo> list = new ArrayList<EnjoyMemberVo>();
        int total = platinumMatchMemberMapper.countByExample(criteria);
        if (CollectionUtils.isNotEmpty(memberList)) {
            for (PlatinumMatchMember member : memberList) {
                EnjoyMemberVo vo = new EnjoyMemberVo();
                vo.setIdA(member.getId());
                vo.setNameA(member.getName());
                vo.setCardNoA(member.getCardNo());
                vo.setSexAstr(member.getSex());
                vo.setReason(member.getRemark());
                SsoUser userA = this.selectByPrimaryKey(SsoUser.class,member.getUserId());
                if(userA != null){
                    vo.setPhoneA(userA.getPhone());
                }
                vo.setDoubleFlag(member.getDoubleFlag());
                vo.setState(member.getState());
                vo.setCt(DateUtils.forDatetime(member.getCt()));
                if (member.getDoubleFlag() == 1) {
                    PlatinumMatchMember memberB =  this.selectByPrimaryKey(PlatinumMatchMember.class,member.getTeammate());
                    vo.setIdB(memberB.getId());
                    vo.setNameB(memberB.getName());
                    vo.setCardNoB(memberB.getCardNo());
                    vo.setSexBstr(memberB.getSex());
//                    SsoUser userB = this.selectByPrimaryKey(SsoUser.class,memberB.getUserId());
//                    if(userB != null){
//                        vo.setPhoneB(userB.getPhone());
//                    }
                }
                list.add(vo);
            }
        }
        return new PageImpl<>(list, pageable, total);
    }

    @Transactional(readOnly = false)
    public String updateMember(String memberId, Integer status,String reason) throws Exception {
        PlatinumMatchMember member = this.selectByPrimaryKey(PlatinumMatchMember.class, memberId);
        PlatinumMatchMember memberB = null;
        Order order = this.selectByPrimaryKey(Order.class,
                member.getOrderid());
        if (member != null) {
            member.setState(status);
            member.setEt(new Date());
            member.setEb(SessionUtil.currentUser().getUserId());
            // 当审核通过时，并且订单已支付时 修改订单状态为已审核
            if (status == 1 &&
                    order.getStatus().equals(Constants.OrderStatus.PAIED)) { //
                // 审核通过，已支付的订单
                order.setStatus(Constants.OrderStatus.VERIFY); // 更成已确认订单
                order.setEt(new Date());
                order.setEb(SessionUtil.currentUser().getUserId());
                this.updateByPrimaryKeySelective(order, member.getOrderid());
                this.updateByPrimaryKeySelective(member, memberId);
            } else if (status == 1 &&
                    order.getStatus().equals(Constants.OrderStatus.NEW)) {//
                // 审核通过，未支付的订单，客服通知用户取消订单
                return "";
            } else if (status == 2
                    && (order.getStatus().equals(Constants.OrderStatus.PAIED) ||
                    order.getStatus().equals(
                            Constants.OrderStatus.VERIFY))) {// 审核不通过，已支付的订单
                // 审核不通过的已支付的订单，需要先退款
                order.setStatus(Constants.OrderStatus.REFUNDING);
                order.setEt(new Date());
                order.setEb(SessionUtil.currentUser().getUserId());
                this.updateByPrimaryKeySelective(order, member.getOrderid());
                member.setRemark(reason);
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
                if(order.getQiuyouFee()!=null&&order.getQiuyouFee()>0){
                    QiuyouCardUser account = this.selectByPrimaryKey(QiuyouCardUser.class, order.getCustomerId());
                    Integer balanceInit = account.getBalance();
                    Integer balanceResult = balanceInit+order.getQiuyouFee();
                    //余额更新
                    account.setBalance(balanceResult);
                    account.setEt(new Date());
                    account.setEb("1");
                    this.updateByPrimaryKeySelective(account, order.getCustomerId());
                    //创建球友卡消费日志
                    QiuyouCardLog qycl = new QiuyouCardLog();
                    qycl.setId(UUID.get());
                    qycl.setType(2);	//0充值1消费
                    qycl.setUserId(order.getCustomerId());
                    qycl.setAmount(order.getQiuyouFee());
                    qycl.setBalance(balanceResult);
                    qycl.setOrderId(order.getId());
                    qycl.setDescription("退回");
                    qycl.setCt(new Date());
                    qiuyouCardLogMapper.insert(qycl);
                }
                PlatinumMatch enjoyGame = this.selectByPrimaryKey(PlatinumMatch.class,order.getStatiumId());
                if(StringUtils.isNotBlank(enjoyGame.getGid())){
                    PlatinumMatch tmp = this.selectByPrimaryKey(PlatinumMatch.class,enjoyGame.getGid());
                    enjoyGame.setName(tmp.getName());
                }
                //场馆名称 +赛事名称+报名项目
                String gameType = "";
                switch (enjoyGame.getType()) {
                    case 1:
                        gameType = "女子单打";
                        break;
                    case 2:
                        gameType = "青年组双打";
                        break;
                    case 3:
                        gameType = "常青组双打";
                        break;
                    case 4:
                        gameType = "青年组混打";
                        break;
                    case 5:
                        gameType = "常青组混打";
                        break;
                }
                String gameName = enjoyGame.getName()+gameType;
                Map<String, Object> root = new HashMap<String, Object>();
                //您“北京水立方网球馆 “cta网球乐享赛(北京站)” 男子单打 的订单（订单号：1111）已被取消，请修改后重新下单。
                root.put("gameName",gameName);
                root.put("orderNo",order.getId());
                String msg = FreeMarkerUtils.format("/template/sms/orderEventCancel.ftl", root);
                SsoUser user = this.selectByPrimaryKey(SsoUser.class,member.getUserId());
                if(user != null){
                    messageUtil.sendSms(user.getPhone(),msg);
                }
//                if(member.getDoubleFlag() == 1){
//                    if(StringUtils.isNotBlank(member.getTeammate())){
//                        GamesPlayer player2 = this.selectByPrimaryKey(GamesPlayer.class,member.getTeammate());
//                        if(player2 != null){
//                            SsoUser user2 = this.selectByPrimaryKey(SsoUser.class, player2.getUserId());
//                            if(user2 != null){
//                                messageUtil.sendSms(user2.getPhone(),msg);
//                            }
//                        }
//                    }
//                }

                //给客服发送消息提醒
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
                    messageUtil.sendLoc("1", userid, "女子白金赛报名(订单号:" + order.getId() + ")被拒绝", "女子白金赛报名(订单号:" + order.getId() + ")被拒绝");
                }

            } else {// 审核不通过，未支付的订单
                member.setRemark(reason);
                this.updateByPrimaryKeySelective(member, memberId);
            }
        }
        return member.getPlatinumMatchId();
    }

    public Map<Integer,PlatinumMatch> gameVo(PlatinumMatch game)throws Exception{
        Map<Integer,PlatinumMatch> map = new HashMap<Integer, PlatinumMatch>();
        EnjoyGameVo vo = new EnjoyGameVo();
        BeanUtils.copyProperties(game,vo);
        map.put(game.getType(),game);
        PlatinumMatchCriteria c2 = new PlatinumMatchCriteria();
        PlatinumMatchCriteria.Criteria cri2 = c2.createCriteria();
        cri2.andDeleteFlagEqualTo(0);
        cri2.andGidEqualTo(game.getId());
        c2.setOrderByClause("type");
        int total2 = platinumMatchMapper.countByExample(c2);
        if(total2 > 0){
            List<PlatinumMatch> list2 = platinumMatchMapper.selectByExample(c2);
            vo.setGamesLength(total2);
            for (PlatinumMatch enjoyGame : list2) {
                map.put(enjoyGame.getType(),enjoyGame);
            }
        }
        return map;
    }

}





