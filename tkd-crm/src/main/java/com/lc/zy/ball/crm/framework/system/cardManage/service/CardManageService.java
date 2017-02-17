package com.lc.zy.ball.crm.framework.system.cardManage.service;

import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.crm.common.Constants;
import com.lc.zy.ball.crm.common.SessionUtil;
import com.lc.zy.ball.crm.common.service.AbstractCacheService;
import com.lc.zy.ball.crm.framework.system.cardManage.vo.*;
import com.lc.zy.ball.domain.oa.mapper.*;
import com.lc.zy.ball.domain.oa.po.*;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.zoo.SEQGenerate;

import org.apache.commons.lang.StringUtils;
import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class CardManageService extends AbstractCacheService {
	private static final Logger logger = LoggerFactory.getLogger(CardManageService.class);

    @Autowired
    private CrmCardMapper crmCardMapper = null;

    @Autowired
    private SsoUserMapper ssoUserMapper = null;

    @Autowired
    private CrmUserCardAccountMapper crmUserCardAccountMapper = null;

    @Autowired
    private SEQGenerate seqGenerate = null;

    @Autowired
    private CrmUserCardLogMapper crmUserCardLogMapper = null;

    @Autowired
    private CrmTurnCardLogMapper crmTurnCardLogMapper = null;

    @Autowired
    private CrmCardLogMapper crmCardLogMapper = null;

    /**
     *
     * <会员卡list><功能具体实现>
     *
     * @create：2016/11/29 上午9:56
     * @author：sl
     * @param pageRequest
     * @param searchParams
     * @return com.lc.zy.ball.common.data.pageable.Page<com.lc.zy.ball.domain.oa.po.CrmCard>
     */
    public Page<CrmCard> findCard(PageRequest pageRequest, Map<String, Object> searchParams) {
        int total = 0;
        List<CrmCard> list = new ArrayList<CrmCard>();
        try {
            Map<String, SearchFilter> filters = SearchFilter
                    .parse(searchParams);
            CrmCardCriteria criteria = new CrmCardCriteria();
            criteria.setMysqlLength(pageRequest.getPageSize());
            criteria.setMysqlOffset(pageRequest.getOffset());
            CrmCardCriteria.Criteria cri = criteria.createCriteria();
            Criterias.bySearchFilter(cri, filters.values());
            criteria.setOrderByClause("et desc");
            total = crmCardMapper.countByExample(criteria);
            list =crmCardMapper.selectByExample(criteria);
        } catch (Exception e) {
            logger.error("会员卡list {}", e.getMessage());
        }
        return new PageImpl<>(list, pageRequest, total);
    }

    /**
     *
     * <卡片信息><功能具体实现>
     *
     * @create：2016/11/29 上午10:30
     * @author：sl
     * @param cardId
     * @return com.lc.zy.ball.domain.oa.po.CrmCard
     */
    public CrmCard getCardFormById(String cardId) {
        CrmCard crmCard = new CrmCard();
        try {
            crmCard = this.selectByPrimaryKey(CrmCard.class, cardId);
            crmCard.setCardAmount(crmCard.getCardAmount() / 100);
            crmCard.setCardGift(crmCard.getCardGift() / 100);
        } catch (Exception e) {
            logger.debug("卡片信息 {}", e.getMessage());
        }
        return crmCard;
    }

    /**
     *
     * <保存卡片信息><功能具体实现>
     *
     * @create：2016/11/29 上午10:39
     * @author：sl
     * @param myForm
     * @return void
     */
    @Transactional(readOnly = false)
    public void saveCard(CrmCard myForm) {
        try {
            // uuid
            String userid = SessionUtil.currentUserId();
            Date now = new Date();
            // et
            myForm.setEt(now);
            // eb
            myForm.setEb(userid);
            // cardAmount
            myForm.setCardAmount(myForm.getCardAmount() * 100);
            // cardGift
            if (myForm.getCardGift() == null) {
                myForm.setCardGift(0);
            }
            myForm.setCardGift(myForm.getCardGift() * 100);
            if (StringUtils.isBlank(myForm.getId())) {
                // id
                myForm.setId(UUID.get());
                // ct
                myForm.setCt(now);
                // cb
                myForm.setCb(userid);
                // status
                myForm.setStatus(0);
                // statiumid
                myForm.setStatiumId(Integer.valueOf(SessionUtil.currentStatium()));
                this.insertSelective(myForm, myForm.getId());
            } else {
                this.updateByPrimaryKeySelective(myForm, myForm.getId());
            }

        } catch (Exception e) {
            logger.debug("保存卡片信息 {}", e.getMessage());
            throw new RuntimeException("保存卡片失败");
        }
    }

    /**
     *
     * <激活卡片><功能具体实现>
     *
     * @create：2016/11/29 上午11:25
     * @author：sl
     * @param cardId
     * @return void
     */
    @Transactional(readOnly = false)
    public void activeCard(String cardId) throws Exception{
        try {
            CrmCard crmCard = new CrmCard();
            crmCard.setId(cardId);
            // 激活状态：0、未激活 1、激活
            crmCard.setStatus(1);
            crmCard.setEt(new Date());
            crmCard.setEb(SessionUtil.currentStatium());
            this.updateByPrimaryKeySelective(crmCard, cardId);
        } catch (Exception e) {
            logger.debug("激活卡片 {}", e.getMessage());
            throw new RuntimeException("服务器异常！");
        }
    }

    /**
     *
     * <冻结卡片><功能具体实现>
     *
     * @create：2016/11/29 上午11:25
     * @author：sl
     * @param cardId
     * @return void
     */
    @Transactional(readOnly = false)
    public void freezeCard(String cardId) throws Exception{
        try {
            CrmCard crmCard = new CrmCard();
            crmCard.setId(cardId);
            // 冻结状态：0、未激活 1、激活
            crmCard.setStatus(0);
            crmCard.setEt(new Date());
            crmCard.setEb(SessionUtil.currentStatium());
            this.updateByPrimaryKeySelective(crmCard, cardId);
        } catch (Exception e) {
            logger.debug("冻结卡片 {}", e.getMessage());
            throw new RuntimeException("服务器异常！");
        }
    }

    /**
     *
     * <会员卡list><功能具体实现>
     *
     * @create：2016/11/29 下午2:30
     * @author：sl
     * @param pageRequest
     * @param searchParams
     * @return com.lc.zy.ball.common.data.pageable.Page<com.lc.zy.ball.crm.framework.system.cardManage.vo.UserCardVo>
     */
    public Page<UserCardVo> findUserCard(PageRequest pageRequest, Map<String, Object> searchParams) {
        int total = 0;
        List<UserCardVo> list = new ArrayList<UserCardVo>();
        try {
            // 用户id
            List<String> userIds = new ArrayList<String>();
            // 用户手机号查询
            if (searchParams.get("LIKE_phone") != null && CommonUtils.isNotEmpty((String) searchParams.get("LIKE_phone"))) {
                String phone = (String) searchParams.get("LIKE_phone");
                // 根据手机号获取用户信息
                SsoUserCriteria criteria = new SsoUserCriteria();
                SsoUserCriteria.Criteria c = criteria.createCriteria();
                c.andPhoneLike("%" + phone + "%");
                List<SsoUser> users = ssoUserMapper.selectByExample(criteria);
                if (users.size() == 0) {
                    return new PageImpl<>(new ArrayList<UserCardVo>(), pageRequest, 0);
                } else {
                    for (SsoUser ssoUser : users) {
                        userIds.add(ssoUser.getId());
                    }
                }
            }

            // 道馆id
            int statiumId = Integer.valueOf(SessionUtil.currentStatium());
            // 购卡日期
            Date gteDate = null;
            Date lteDate = null;
            if (searchParams.get("EQ_et") != null && CommonUtils.isNotEmpty((String) searchParams.get("EQ_et"))) {
                gteDate = DateUtils.getDateTime(((String)searchParams.get("EQ_et")+" 00:00:00"));
                lteDate = DateUtils.getDateTime(((String)searchParams.get("EQ_et")+" 23:59:59"));
            }
            // 获取储值卡
            CrmUserCardAccountCriteria crmUserCardAccountCriteria = new CrmUserCardAccountCriteria();
            CrmUserCardAccountCriteria.Criteria criteria1 = crmUserCardAccountCriteria.createCriteria();
            crmUserCardAccountCriteria.setMysqlLength(pageRequest.getPageSize());
            crmUserCardAccountCriteria.setMysqlOffset(pageRequest.getOffset());
            // 道馆id
            criteria1.andStatiumIdEqualTo(statiumId);
            if (!userIds.isEmpty()) {
                // 用户ID
                criteria1.andUserIdIn(userIds);
            }
            if (searchParams.get("EQ_et") != null && CommonUtils.isNotEmpty((String) searchParams.get("EQ_et"))) {
                criteria1.andEtGreaterThan(gteDate);
                criteria1.andEtLessThan(lteDate);
            }
            //modify by zzq 同一个手机号在两家道馆购买会员卡 此时需对道馆id进行校验
            criteria1.andStatiumIdEqualTo(Integer.parseInt(SessionUtil.currentStatium()));
            crmUserCardAccountCriteria.setOrderByClause("et desc");
            List<CrmUserCardAccount> crmUserCardAccounts = crmUserCardAccountMapper.selectByExample(crmUserCardAccountCriteria);
            total = crmUserCardAccountMapper.countByExample(crmUserCardAccountCriteria);
            for (CrmUserCardAccount crmUserCardAccount : crmUserCardAccounts) {
                // 用户id
                String userId = crmUserCardAccount.getUserId();
                UserCardVo userCardVo = new UserCardVo();
                // 获取用户信息
                SsoUser ssoUser = this.selectByPrimaryKey(SsoUser.class, userId);
                if (ssoUser != null) {
                    // userId
                    userCardVo.setUserId(userId);
                    //
                    if (StringUtils.isNotBlank(ssoUser.getUsername())) {
                        userCardVo.setName(ssoUser.getUsername());
                    } else {
                        userCardVo.setName(ssoUser.getNickName());
                    }
                    // 手机号
                    userCardVo.setPhone(ssoUser.getPhone());
                    // 卡片类型
                    userCardVo.setCardType(crmUserCardAccount.getCardType());
                    if (crmUserCardAccount.getCardType() == Constants.cardType.CARD) {
                        // 卡片余额
                        userCardVo.setBalance(crmUserCardAccount.getBalance());
                    } else {
                        String sTime = DateUtils.formatDate(crmUserCardAccount.getStartDate(), "yyyy/MM/dd");
                        String eTime = DateUtils.formatDate(crmUserCardAccount.getEndDate(), "yyyy/MM/dd");
                        userCardVo.setCardTime(sTime + " ~ " + eTime);
                    }
                    // 卡片id
                    if (crmUserCardAccount.getCardType() == 2) {
                        userCardVo.setCardId(crmUserCardAccount.getCardId());
                    }
                    // account
                    userCardVo.setAccountId(crmUserCardAccount.getId());

                    list.add(userCardVo);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("会员卡list {}", e.getMessage());
        }
        return new PageImpl<>(list, pageRequest, total);
    }

    /**
     *
     * <获取会员卡><功能具体实现>
     *
     * @create：2016/12/1 上午10:35
     * @author：sl
     * @param
     * @return java.util.List<com.lc.zy.ball.domain.oa.po.CrmCard>
     */
    public List<CrmCard> getCards() {
        List<CrmCard> cards = new ArrayList<CrmCard>();
        try {
            CrmCardCriteria crmCardCriteria = new CrmCardCriteria();
            CrmCardCriteria.Criteria criteria = crmCardCriteria.createCriteria();
            criteria.andStatiumIdEqualTo(Integer.valueOf(SessionUtil.currentStatium()));
            // 激活状态
            criteria.andStatusEqualTo(1);
            crmCardCriteria.setMysqlOffset(0);
            crmCardCriteria.setMysqlLength(20);
            crmCardCriteria.setOrderByClause("et desc");
            cards = crmCardMapper.selectByExample(crmCardCriteria);
        } catch (Exception e) {
            logger.debug("获取会员卡：{}", e.getMessage());
        }
        return cards;
    }

    /**
     *
     * <购买会员卡><功能具体实现>
     *
     * @create：2016/12/2 下午4:20
     * @author：sl
     * @param myForm
     * @return void
     */
    @Transactional(readOnly = false)
    public void saveCardUser(UserCardVo myForm) {
        try {
            if (StringUtils.isBlank(myForm.getUserId())) {
                SsoUser user = checkUserByPhone(myForm.getPhone());
                if (user != null) {
                    myForm.setUserId(user.getId());
                } else {
                    // 创建ssoUser
                    String userId = createUserbyPhone(myForm.getName(), myForm.getPhone());
                    myForm.setUserId(userId);
                }
            }
            // 判断卡片类型 1、储值卡 2、期限卡
            if (myForm.getCardType() == Constants.cardType.CARD) {
                // 判断用户账户
                CrmUserCardAccount account = checkByUserId(myForm.getUserId());
                if (account != null) {
                    myForm.setAccountId(account.getId());
                    // 充值（续费）
                    int balance = account.getBalance();
                    account.setBalance(balance + (myForm.getCardAmount() + myForm.getCardGift()) * 100);
                    // et
                    account.setEt(new Date());
                    // eb
                    account.setEb(SessionUtil.currentUserId());

                    this.updateByPrimaryKeySelective(account, account.getId());
                } else {
                    // 创建crm用户账
                    CrmUserCardAccount crmAccount = new CrmUserCardAccount();
                    // uuid
                    crmAccount.setUserId(myForm.getUserId());
                    // statiumId
                    crmAccount.setStatiumId(Integer.valueOf(SessionUtil.currentStatium()));
                    // 获取卡值
                    CrmCard crmCard = getCrmCard(myForm.getCardId());
                    // blance
                    crmAccount.setBalance(crmCard.getCardAmount() + crmCard.getCardGift());
                    // cardType
                    crmAccount.setCardType(myForm.getCardType());
                    String accountId = newCrmUserAccount(crmAccount);
                    myForm.setAccountId(accountId);
                }
            } else {
                // 期限卡
                CrmUserCardAccount crmAccount = new CrmUserCardAccount();
                // uuid
                crmAccount.setUserId(myForm.getUserId());
                // 期限
                crmAccount.setStartDate(DateUtils.getDate(myForm.getStartDate()));
                crmAccount.setEndDate(DateUtils.getDate(myForm.getEndDate()));
                // cardId
                crmAccount.setCardId(myForm.getCardId());
                // cardType
                crmAccount.setCardType(myForm.getCardType());
                // statiumId
                crmAccount.setStatiumId(Integer.valueOf(SessionUtil.currentStatium()));
                String accountId = userDateCard(crmAccount);
                myForm.setAccountId(accountId);
            }
            // 创建订单
            Order order = new Order();
            // userId
            order.setUserId(myForm.getUserId());
            // fee
            order.setFee(myForm.getCardAmount() * 100);
            // final_fee
            order.setFinalFee(myForm.getCardAmount() * 100);
            logger.debug("订单"+order);
            String orderId = creatOrder(order, myForm.getCardType(), myForm.getCardId());
            myForm.setOrderId(orderId);

            // log
            cardLog(myForm);

            // log
            userCardLog(myForm);

        } catch (Exception e) {
            logger.debug("购买会员卡 {}", e.getMessage());
            throw new RuntimeException("购买会员卡失败");
        }
    }

    /**
     *
     * <期限卡购买><功能具体实现>
     *
     * @create：2016/12/5 下午3:36
     * @author：sl
     * @param crmAccount
     * @return void
     */
    @Transactional(readOnly = false)
    private String userDateCard(CrmUserCardAccount crmAccount) {
        String crmUserAccountId = UUID.get();
        String opId = SessionUtil.currentUserId();
        try {
            // id
            crmAccount.setId(crmUserAccountId);
            // statiumId
            crmAccount.setStatiumId(Integer.valueOf(SessionUtil.currentStatium()));
            Date now = new Date();
            // ct
            crmAccount.setCt(now);
            // et
            crmAccount.setEt(now);
            // cb
            crmAccount.setCb(opId);
            // eb
            crmAccount.setEb(opId);
            // status
            crmAccount.setStatus(Constants.cardStatus.ACTIVE);
            this.insertSelective(crmAccount, crmAccount.getId());
        } catch (Exception e) {
            logger.debug("期限卡购买 {}", e.getMessage());
            throw new RuntimeException("期限卡购买失败");
        }
        return crmUserAccountId;
    }

    /**
     *
     * <根据手机号获取用户信息><功能具体实现>
     *
     * @create：2016/12/1 下午4:42
     * @author：sl
     * @param phone
     * @return com.lc.zy.ball.domain.oa.po.SsoUser
     */
    public SsoUser checkUserByPhone(String phone) {
        SsoUser user = null;
        try {
            // 判断此用户是否存在
            SsoUserCriteria ssoUserCriteria = new SsoUserCriteria();
            SsoUserCriteria.Criteria criteria = ssoUserCriteria.createCriteria();
            criteria.andPhoneEqualTo(phone);
            List<SsoUser> users = ssoUserMapper.selectByExample(ssoUserCriteria);
            if (!users.isEmpty()) {
                user =  users.get(0);
            }
        } catch (Exception e) {
            logger.debug("根据手机号获取用户信息 {}", e.getMessage());
        }
        return user;
    }

    /**
     *
     * <根据用户id获取用户会员卡账户><功能具体实现>
     *
     * @create：2016/12/1 下午4:47
     * @author：sl
     * @param userId
     * @return com.lc.zy.ball.domain.oa.po.CrmUserCardAccount
     */
    public CrmUserCardAccount checkByUserId(String userId){
        CrmUserCardAccount account = null;
        try {
            CrmUserCardAccountCriteria criteria = new CrmUserCardAccountCriteria();
            CrmUserCardAccountCriteria.Criteria cri = criteria.createCriteria();
            cri.andUserIdEqualTo(userId);
            cri.andCardTypeEqualTo(Constants.cardType.CARD);
            //modify by zzq 同一个手机号在两家道馆购买会员卡 此时需对道馆id进行校验
            cri.andStatiumIdEqualTo(Integer.parseInt(SessionUtil.currentStatium()));
            List<CrmUserCardAccount> accounts = crmUserCardAccountMapper.selectByExample(criteria);
            if (!accounts.isEmpty()) {
                account = accounts.get(0);
            }
        } catch (Exception e) {
            logger.debug("根据用户id获取用户会员卡账户 {}", e.getMessage());
        }
        return account;
    }

    /**
     *
     * <创建用户><功能具体实现>
     *
     * @create：2016/12/1 下午5:04
     * @author：sl
     * @param userName
     * @param phone
     * @return java.lang.String
     */
    @Transactional(readOnly = false)
    public String createUserbyPhone (String userName, String phone) {
        // userId
        String userId = UUID.get();
        try {
            SsoUser user = new SsoUser();
            user.setId(userId);
            // nikeName
            user.setNickName(userName);
            // name
            user.setUsername(userName);
            // phone
            user.setPhone(phone);
            // 用户app号
            String qn = seqGenerate.genQiuyouNumber();
            user.setTkdNo(qn);
            // cb
            user.setCreateUser(userId);
            // eb
            user.setUpdateUser(userId);
            Date now = new Date();
            // ct
            user.setCreateTime(now);
            // eb
            user.setUpdateTime(now);
            user.setActiveState(Constants.userStatus.ACTIVE);// 1 激活, 0 未激活
            user.setRegistTime(now);
            user.setState(Constants.userStatus.ACTIVE);// 用户状态：1为正常用户，2删除，3屏蔽用户
            user.setLevel(Constants.userLevel.GENERAL);// 用户等级
            // statiumId
            user.setStatiumId(Integer.valueOf(SessionUtil.currentStatium()));
            // db and cache
            super.insertSelective(user, userId);
        } catch (Exception e) {
            logger.debug("创建用户 {}", e.getMessage());
            throw new RuntimeException("创建用户失败");
        }
        return userId;
    }

    /**
     *
     * <new账户充值><功能具体实现>
     *
     * @create：2016/12/1 下午5:18
     * @author：sl
     * @param crmAccount
     * @return void
     */
    @Transactional(readOnly = false)
    public String newCrmUserAccount (CrmUserCardAccount crmAccount) {
        String crmUserAccountId = UUID.get();
        String opId = SessionUtil.currentUserId();
        try {
            // id
            crmAccount.setId(crmUserAccountId);
            // statiumId
            crmAccount.setStatiumId(Integer.valueOf(SessionUtil.currentStatium()));
            Date now = new Date();
            // ct
            crmAccount.setCt(now);
            // et
            crmAccount.setEt(now);
            // cb
            crmAccount.setCb(opId);
            // eb
            crmAccount.setEb(opId);
            // status 0：未激活、冻结 1：激活 2：注销
            crmAccount.setStatus(Constants.cardStatus.ACTIVE);

            this.insertSelective(crmAccount, crmAccount.getId());
        } catch (Exception e) {
            logger.debug("new账户充值 {}", e.getMessage());
            throw new RuntimeException("创建账户失败");
        }
        return crmUserAccountId;
    }

    /**
     *
     * <道馆会员卡log><功能具体实现>
     *
     * @create：2016/12/2 下午3:07
     * @author：sl
     * @param vo
     * @return void
     */
    @Transactional(readOnly = false)
    public void cardLog (UserCardVo vo) {
        try {
            CrmCardLog crmCardLog = new CrmCardLog();
            // id
            crmCardLog.setId(UUID.get());
            // userId
            crmCardLog.setUserId(vo.getUserId());
            // statiumId
            crmCardLog.setStatiumId(Integer.valueOf(SessionUtil.currentStatium()));
            // cardId
            crmCardLog.setCardId(vo.getCardId());
            // cardType
            crmCardLog.setCardType(vo.getCardType());
            // amount
            crmCardLog.setAmount(vo.getCardAmount() * 100);
            // gift
            crmCardLog.setGiftAmount(vo.getCardGift() * 100);
            // buyType 0:app 1:线下
            crmCardLog.setBuyType(Constants.orderType.ONLINE);
            // ct
            crmCardLog.setCt(new Date());
            // cb
            crmCardLog.setCb(SessionUtil.currentUserId());
            // type 0、办卡 1、激活 2、注销 3、转卡 4、充值, 5、退款
            crmCardLog.setType(Constants.cardOpType.BUY);
            this.insertSelective(crmCardLog, crmCardLog.getId());
        } catch (Exception e) {
            logger.debug("道馆会员卡log {}", e.getMessage());
            throw new RuntimeException("记录道馆会员卡失败");
        }
    }

    /**
     *
     * <用户cardLog--买卡><功能具体实现>
     *
     * @create：2016/12/5 下午3:51
     * @author：sl
     * @param vo
     * @return void
     */
    @Transactional(readOnly = false)
    public void userCardLog (UserCardVo vo)  {
        try {
            CrmUserCardLog crmUserCardLog = new CrmUserCardLog();
            // id
            crmUserCardLog.setId(UUID.get());
            // userId
            crmUserCardLog.setUserId(vo.getUserId());
            // cardId
            crmUserCardLog.setCardId(vo.getCardId());
            // type 0、办卡 1、激活 2、注销 3、转卡 4、充值,5、退款
            crmUserCardLog.setType(Constants.cardOpType.BUY);
            if (vo.getCardType() == Constants.cardType.CARD) {
                // amount
                int amount = (vo.getCardAmount() + vo.getCardGift()) * 100;
                crmUserCardLog.setAmount(amount);
                // balance
                int balance = checkByUserId(vo.getUserId()).getBalance();
                crmUserCardLog.setBalance(balance);
            } else {
                crmUserCardLog.setStartDate(DateUtils.getDate(vo.getStartDate(), "yyyy-MM-dd"));
                crmUserCardLog.setEndDate(DateUtils.getDate(vo.getEndDate(), "yyyy-MM-dd"));
            }
            // order_type
            crmUserCardLog.setOrderType(Constants.orderType.ONLINE);
            // ct
            crmUserCardLog.setCt(new Date());
            // description
            crmUserCardLog.setDescription(Constants.cardHandel.BUYCARD);
            // orderId
            crmUserCardLog.setOrderId(vo.getOrderId());
            // accountId
            crmUserCardLog.setAccountId(vo.getAccountId());
            // cardType
            crmUserCardLog.setCardType(vo.getCardType());


            this.insertSelective(crmUserCardLog, crmUserCardLog.getId());
        } catch (Exception e) {
            logger.debug("用户cardLog {}", e.getMessage());
            throw new RuntimeException("用户卡片log失败");
        }
    }

    /**
     *
     * <道馆办卡order><功能具体实现>
     *
     * @create：2016/12/2 下午4:13
     * @author：sl
     * @param order
     * @param cardType
     *@param cardId @return java.lang.String
     */
    @Transactional(readOnly = false)
    public String creatOrder(Order order, Integer cardType, String cardId) {
        String orderId = "";
        try {
            // 获取订单号
            orderId = seqGenerate.genLiveOrderId();
            order.setId(orderId);

            // statiumId
            order.setStatiumId(Integer.valueOf(SessionUtil.currentStatium()));
            // status
            order.setStatus(Constants.OrderStatus.BILLED);
            // orderType
            order.setOrderType(Constants.BookType.LIVE);
            Date now = new Date();
            // ct
            order.setCt(now);
            // et
            order.setEt(now);
            String userId = SessionUtil.currentUserId();
            // cb
            order.setCb(userId);
            // eb
            order.setEb(userId);
            // ordersType
            order.setOrdersType(Constants.OrdersType.CARD);

            this.insertSelective(order, orderId);

            // orderItem
            createOrderItem(order.getId(), cardType, cardId);

        } catch (Exception e) {
            logger.debug("道馆办卡order {}", e.getMessage());
            throw new RuntimeException("创建办卡订单失败");
        }
        return orderId;
    }

    /**
     *
     * <道馆办卡orderItem><功能具体实现>
     *
     * @create：2016/12/2 下午4:11
     * @author：sl
     * @param cardType
     * @param cardId
     * @param orderId
     * @return void
     */
    @Transactional(readOnly = false)
    private void createOrderItem(String orderId, Integer cardType, String cardId) {
        try {
            OrderItem item = new OrderItem();
            // id
            item.setId(UUID.get());
            // orderId
            item.setOrderId(orderId);
            // cardType
            item.setCardType(cardType);
            // cardId
            if (StringUtils.isNotEmpty(cardId)) {
                item.setCardId(cardId);
            }
            Date now = new Date();
            // activeDate
            item.setActiveDate(now);
            // cardStatus
            item.setCardStatus(Constants.cardStatus.ACTIVE);
            // buyDate
            item.setCardBuyDate(now);
            // ct
            item.setCt(now);
            // et
            item.setEt(now);
            // cb
            item.setCb(SessionUtil.currentUserId());
            // eb
            item.setEb(SessionUtil.currentUserId());

            this.insertSelective(item, item.getId());

        } catch (Exception e) {
            logger.debug("道馆办卡orderItem {}", e.getMessage());
            throw new RuntimeException("创建办卡订单详情失败");
        }
    }

    /**
     *
     * <根据账户id获取卡片行为记录><功能具体实现>
     *
     * @create：2016/12/6 下午2:25
     * @author：sl
     * @param pageRequest
     * @param accountId
     * @return com.lc.zy.ball.common.data.pageable.Page<com.lc.zy.ball.crm.framework.system.cardManage.vo.UserCardVo>
     */
    public Page<CrmUserCardLog> cardUserLog(PageRequest pageRequest, String accountId) {
        int total = 0;
        List<CrmUserCardLog> list = new ArrayList<CrmUserCardLog>();
        try {
            CrmUserCardLogCriteria criteria = new CrmUserCardLogCriteria();
            CrmUserCardLogCriteria.Criteria cri = criteria.createCriteria();
            cri.andAccountIdEqualTo(accountId);
            criteria.setOrderByClause("ct desc");
            criteria.setMysqlOffset(pageRequest.getOffset());
            criteria.setMysqlLength(pageRequest.getPageSize());
            total = crmUserCardLogMapper.countByExample(criteria);
            list = crmUserCardLogMapper.selectByExample(criteria);
        } catch (Exception e) {
            logger.debug("根据账户id获取卡片行为记录 {}", e.getMessage());
        }
        return new PageImpl<>(list, pageRequest, total);
    }

    /**
     *
     * <根据账户id获取账户信息><功能具体实现>
     *
     * @create：2016/12/6 下午2:11
     * @author：sl
     * @param accountId
     * @return com.lc.zy.ball.crm.framework.system.cardManage.vo.UserCardVo
     */
    public UserCardVo cardUser(String accountId) {
        UserCardVo vo = new UserCardVo();
        try {
            CrmUserCardAccount account = this.selectByPrimaryKey(CrmUserCardAccount.class, accountId);
            // 获取用户信息
            SsoUser user = this.selectByPrimaryKey(SsoUser.class, account.getUserId());
            // 名称
            if (user.getUsername().isEmpty()) {
                vo.setName(user.getNickName());
            } else {
                vo.setName(user.getUsername());
            }
            // userId
            vo.setUserId(user.getId());
            // accoutId
            vo.setAccountId(accountId);
            // balance
            vo.setBalance(account.getBalance());
            // 手机号
            vo.setPhone(user.getPhone());
            // 卡片类型
            vo.setCardType(account.getCardType());
            if (vo.getCardType() == Constants.cardType.CARD) {
                // 卡片余额
                vo.setBalance(account.getBalance());
            } else {
                vo.setOldEndDate(DateUtils.formatDate(account.getEndDate(), "yyyy-MM-dd"));
                vo.setOldStartDate(DateUtils.formatDate(account.getStartDate(), "yyyy-MM-dd"));
                String sTime = DateUtils.formatDate(account.getStartDate(), "yyyy/MM/dd");
                String eTime = DateUtils.formatDate(account.getEndDate(), "yyyy/MM/dd");
                vo.setCardTime(sTime + " ~ " + eTime);
            }
        } catch (Exception e) {
            logger.debug("根据账户id获取账户信息 {}", e.getMessage());
        }
        return vo;
    }

    /**
     *
     * <保存转卡信息><功能具体实现>
     *
     * @create：2016/12/13 上午9:31
     * @author：sl
     * @param myForm
     * @return void
     */
    @Transactional(readOnly = false)
    public void saveTurnCard(TurnCardVo myForm) {
        try {
            // 判断新用户是否有注册
            SsoUser user = checkUserByPhone(myForm.getNewPhone());
            if (user != null) {
                myForm.setNewUserId(user.getId());
            } else {
                // 创建用户
                String userId = createUserbyPhone(myForm.getNewName(), myForm.getNewPhone());
                myForm.setNewUserId(userId);
            }
            // 判断卡片类型
            if (myForm.getCardType() == Constants.cardType.CARD) {
                // 判断用户账户
                CrmUserCardAccount account = checkByUserId(myForm.getNewUserId());
                if (account != null) {
                    myForm.setNewAccountId(account.getId());
                    // 充值（续费）
                    int balance = account.getBalance();
                    account.setBalance(balance + myForm.getOldBalance());
                    // et
                    account.setEt(new Date());
                    // eb
                    account.setEb(SessionUtil.currentUserId());

                    try {
						this.updateByPrimaryKeySelective(account, account.getId());
					} catch (Exception e) {
						e.printStackTrace();
					}
                } else {
                    // 创建crm用户账
                    CrmUserCardAccount crmAccount = new CrmUserCardAccount();
                    // uuid
                    crmAccount.setUserId(myForm.getNewUserId());
                    // statiumId
                    crmAccount.setStatiumId(Integer.valueOf(SessionUtil.currentStatium()));
                    // blance
                    crmAccount.setBalance(myForm.getOldBalance());
                    // cardType
                    crmAccount.setCardType(myForm.getCardType());
                    String accountId = newCrmUserAccount(crmAccount);
                    myForm.setNewAccountId(accountId);
                }
            } else {
                // 期限卡
                CrmUserCardAccount crmAccount = new CrmUserCardAccount();
                // uuid
                //modify by zzq 转卡后新卡用户是新的userId
                crmAccount.setUserId(myForm.getNewUserId());
                // 期限
                crmAccount.setStartDate(DateUtils.getDate(myForm.getNewStartDate()));
                crmAccount.setEndDate(DateUtils.getDate(myForm.getNewEndDate()));
                // cardId
                crmAccount.setCardId(myForm.getCardId());
                // cardType
                crmAccount.setCardType(myForm.getCardType());
                // statiumId
                crmAccount.setStatiumId(Integer.valueOf(SessionUtil.currentStatium()));
                String accountId = userDateCard(crmAccount);
                myForm.setNewAccountId(accountId);
            }
            // 创建订单
            Order order = new Order();
            // userId
            order.setUserId(myForm.getNewUserId());
            if (myForm.getCardType() == Constants.cardType.DATECARD) {
                myForm.setOldBalance(0);
            }
            // fee
            order.setFee(myForm.getOldBalance());
            // final_fee
            order.setFinalFee(myForm.getOldBalance());
            String orderId = creatOrder(order, myForm.getCardType(), myForm.getCardId());
            myForm.setOrderId(orderId);

            // 道馆log
            saveRurnCardLog(myForm);
            // 用户log
            saveCardUserLog(myForm);

            // 转卡记录
            saveTurnLog(myForm);

            // 更新被转卡用户信息
            updateTurnAccount(myForm.getOldAccountId(), myForm.getCardType());

            // 被转卡用户记录
            updateCardLog(myForm);
        } catch (Exception e) {
            logger.debug("保存转卡信息 {}", e.getMessage());
            throw new RuntimeException("保存转卡信息失败");
        }
    }

    /**
     *
     * <被转卡用户记录cardLog--被转卡><功能具体实现>
     *
     * @create：2016/12/13 下午3:45
     * @author：sl
     * @param vo
     * @return void
     */
    private void updateCardLog(TurnCardVo vo) {
        try {
            CrmUserCardLog crmUserCardLog = new CrmUserCardLog();
            // id
            crmUserCardLog.setId(UUID.get());
            // userId
            crmUserCardLog.setUserId(vo.getOldUserId());
            // cardId
            crmUserCardLog.setCardId(vo.getCardId());
            // type 0、办卡 1、激活 2、注销 3、转卡 4、充值,5、退款
            crmUserCardLog.setType(Constants.cardOpType.RECHARGE);
            if (vo.getCardType() == Constants.cardType.CARD) {
                // amount
                crmUserCardLog.setAmount(0);
                // balance
                crmUserCardLog.setBalance(0);
            } else {
                crmUserCardLog.setStartDate(DateUtils.getDate(vo.getNewStartDate(), "yyyy-MM-dd"));
                crmUserCardLog.setEndDate(DateUtils.getDate(vo.getNewEndDate(), "yyyy-MM-dd"));
            }
            // order_type
            crmUserCardLog.setOrderType(Constants.orderType.ONLINE);
            // ct
            crmUserCardLog.setCt(new Date());
            // description
            crmUserCardLog.setDescription(Constants.cardHandel.TURNCARD);
            // orderId
            crmUserCardLog.setOrderId(vo.getOrderId());
            // accountId
            crmUserCardLog.setAccountId(vo.getOldAccountId());

            this.insertSelective(crmUserCardLog, crmUserCardLog.getId());
        } catch (Exception e) {
            logger.debug("被转卡用户记录cardLog {}", e.getMessage());
        }
    }

    /**
     *
     * <更改转卡原用户信息><功能具体实现>
     *
     * @create：2016/12/13 下午2:50
     * @author：sl
     * @param oldAccountId
     * @param cardType
     * @return void
     */
    @Transactional(readOnly = false)
    private void updateTurnAccount(String oldAccountId, int cardType) {
        try {
            CrmUserCardAccount account = new CrmUserCardAccount();
            account.setId(oldAccountId);
            if (cardType == Constants.cardType.CARD) {
                account.setBalance(0);
            } else {
                account.setEndDate(DateUtils.getDate(DateUtils.formatDate(new Date()), "yyyy-MM-dd"));
                account.setStatus(Constants.cardStatus.FROZEN);
            }
            // et
            account.setEt(new Date());
            // eb
            account.setEb(SessionUtil.currentUserId());
            this.updateByPrimaryKeySelective(account, oldAccountId);
        } catch (Exception e) {
            logger.debug("更改转卡原用户信息 {}", e.getMessage());
            throw new RuntimeException("更新转卡用户信息失败");
        }
    }

    /**
     *
     * <转卡记录><功能具体实现>
     *
     * @create：2016/12/13 下午2:03
     * @author：sl
     * @param myForm
     * @return void
     */
    @Transactional(readOnly = false)
    private void saveTurnLog(TurnCardVo myForm) {
        try {
            CrmTurnCardLog turnLog = new CrmTurnCardLog();
            // id
            turnLog.setId(UUID.get());
            // old
            turnLog.setOldUserId(myForm.getOldUserId());
            turnLog.setOldAccountId(myForm.getOldAccountId());
            // new
            turnLog.setNewUserId(myForm.getNewUserId());
            turnLog.setNewAccountId(myForm.getNewAccountId());
            // balance
            turnLog.setCardBalance(myForm.getOldBalance());
            // reason
            turnLog.setReason(myForm.getTurnReason());
            // cost
            turnLog.setCardCost(myForm.getCardCost() * 100);
            // cardType
            turnLog.setCardType(myForm.getCardType());
            if (myForm.getCardType() == Constants.cardType.DATECARD) {
                turnLog.setStartDate(DateUtils.getDate(myForm.getNewStartDate()));
                turnLog.setEndDate(DateUtils.getDate(myForm.getNewEndDate()));
            }
            // statiumid
            turnLog.setStatiumId(Integer.valueOf(SessionUtil.currentStatium()));
            // ct
            turnLog.setCt(new Date());

            this.insertSelective(turnLog, turnLog.getId());
        } catch (Exception e) {
            logger.debug("转卡记录 {}", e.getMessage());
            throw new RuntimeException("保存转卡记录失败");
        }
    }

    /**
     *
     * <用户转卡log--转卡><功能具体实现>
     *
     * @create：2016/12/13 上午10:59
     * @author：sl
     * @param vo
     * @return void
     */
    @Transactional(readOnly = false)
    private void saveCardUserLog(TurnCardVo vo) {
        try {
            CrmUserCardLog crmUserCardLog = new CrmUserCardLog();
            // id
            crmUserCardLog.setId(UUID.get());
            // userId
            crmUserCardLog.setUserId(vo.getNewUserId());
            // cardId
            crmUserCardLog.setCardId(vo.getCardId());
            // type 0、办卡 1、激活 2、注销 3、转卡 4、充值,5、退款
            crmUserCardLog.setType(Constants.cardOpType.RECHARGE);
            if (vo.getCardType() == Constants.cardType.CARD) {
                // amount
                int amount = vo.getOldBalance();
                crmUserCardLog.setAmount(amount);
                // balance
                int balance = 0;
              //modify by zzq 2016-02-09 用户转卡日志余额 显示新账户余额 之前已经处理 直接取balance;
                if (checkByUserId(vo.getNewUserId()) != null) {
                    balance = checkByUserId(vo.getNewUserId()).getBalance();
                }
                crmUserCardLog.setBalance(balance);
            } else {
                crmUserCardLog.setStartDate(DateUtils.getDate(vo.getNewStartDate(), "yyyy-MM-dd"));
                crmUserCardLog.setEndDate(DateUtils.getDate(vo.getNewEndDate(), "yyyy-MM-dd"));
            }
            // order_type
            crmUserCardLog.setOrderType(Constants.orderType.ONLINE);
            // ct
            crmUserCardLog.setCt(new Date());
            // description
            crmUserCardLog.setDescription(Constants.cardHandel.TURNCARD);
            // orderId
            crmUserCardLog.setOrderId(vo.getOrderId());
            // accountId
            crmUserCardLog.setAccountId(vo.getNewAccountId());
            // cardType
            crmUserCardLog.setCardType(vo.getCardType());

            this.insertSelective(crmUserCardLog, crmUserCardLog.getId());
        } catch (Exception e) {
            logger.debug("用户cardLog {}", e.getMessage());
            throw new RuntimeException("记录用户转卡log失败");
        }
    }

    /**
     *
     * <道馆会员卡转卡log><功能具体实现>
     *
     * @create：2016/12/13 上午10:35
     * @author：sl
     * @param vo
     * @return void
     */
    @Transactional(readOnly = false)
    private void saveRurnCardLog(TurnCardVo vo) {
        try {
            CrmCardLog crmCardLog = new CrmCardLog();
            // id
            crmCardLog.setId(UUID.get());
            // userId
            crmCardLog.setUserId(vo.getNewUserId());
            // statiumId
            crmCardLog.setStatiumId(Integer.valueOf(SessionUtil.currentStatium()));
            // cardId
            crmCardLog.setCardId(vo.getCardId());
            // cardType
            crmCardLog.setCardType(vo.getCardType());
            // amount
            crmCardLog.setAmount(vo.getOldBalance());
            // gift
            crmCardLog.setGiftAmount(0);
            // buyType 0:app 1:线下
            crmCardLog.setBuyType(Constants.orderType.ONLINE);
            // ct
            crmCardLog.setCt(new Date());
            // cb
            crmCardLog.setCb(SessionUtil.currentUserId());
            // type 0、办卡 1、激活 2、注销 3、转卡 4、充值, 5、退款
            crmCardLog.setType(Constants.cardOpType.RECHARGE);
            this.insertSelective(crmCardLog, crmCardLog.getId());
        } catch (Exception e) {
            logger.debug("道馆会员卡转卡log {}", e.getMessage());
            throw new RuntimeException("道馆会员卡转卡日志失败");
        }
    }

    /**
     *
     * <根据卡片id获取卡片信息><功能具体实现>
     *
     * @create：2016/12/7 下午2:52
     * @author：sl
     * @param cardId
     * @return com.lc.zy.ball.domain.oa.po.CrmCard
     */
    public CrmCard getCrmCard (String cardId) {
        CrmCard crmCard = null;
        try {
            crmCard = this.selectByPrimaryKey(CrmCard.class, cardId);
        } catch (Exception e) {
            logger.debug("根据卡片id获取卡片信息 {}", e.getMessage());
        }
        return crmCard;
    }

    /**
     *
     * <会员卡list><功能具体实现>
     *
     * @create：2016/12/13 上午10:26
     * @author：sl
     * @param pageRequest
     * @param searchParams
     * @return com.lc.zy.ball.common.data.pageable.Page<com.lc.zy.ball.crm.framework.system.cardManage.vo.CardTurnListVo>
     */
    public Page<CardTurnListVo> findCardTurn(PageRequest pageRequest, Map<String, Object> searchParams) {
        int total = 0;
        List<CardTurnListVo> list = new ArrayList<CardTurnListVo>();
        try {
            // 用户id
            List<String> userIds = new ArrayList<String>();
            // 用户手机号查询
            if (searchParams.get("LIKE_phone") != null && CommonUtils.isNotEmpty((String) searchParams.get("LIKE_phone"))) {
                String phone = (String) searchParams.get("LIKE_phone");
                // 根据手机号获取用户信息
                SsoUserCriteria criteria = new SsoUserCriteria();
                SsoUserCriteria.Criteria c = criteria.createCriteria();
                c.andPhoneLike("%" + phone + "%");
                List<SsoUser> users = ssoUserMapper.selectByExample(criteria);
                if (users.size() == 0) {
                    return new PageImpl<>(new ArrayList<CardTurnListVo>(), pageRequest, 0);
                } else {
                    for (SsoUser ssoUser : users) {
                        userIds.add(ssoUser.getId());
                    }
                }
            }
            CrmTurnCardLogCriteria criteria = new CrmTurnCardLogCriteria();
            CrmTurnCardLogCriteria.Criteria cri1 = criteria.createCriteria();

            CrmTurnCardLogCriteria.Criteria cri2 = criteria.createCriteria();
            // 购卡日期
            if (searchParams.get("EQ_ct") != null && CommonUtils.isNotEmpty((String) searchParams.get("EQ_ct"))) {
                Date gteDate = null;
                Date lteDate = null;
                gteDate = DateUtils.getDateTime(((String)searchParams.get("EQ_ct")+" 00:00:00"));
                lteDate = DateUtils.getDateTime(((String)searchParams.get("EQ_ct")+" 23:59:59"));
                cri1.andCtGreaterThanOrEqualTo(gteDate);
                cri1.andCtLessThanOrEqualTo(lteDate);

                cri2.andCtGreaterThanOrEqualTo(gteDate);
                cri2.andCtLessThanOrEqualTo(lteDate);
            }
            if (!userIds.isEmpty()) {
                cri1.andOldUserIdIn(userIds);
                criteria.or(cri2.andNewUserIdIn(userIds));
            }
            cri1.andStatiumIdEqualTo(Integer.valueOf(SessionUtil.currentStatium()));
            cri2.andStatiumIdEqualTo(Integer.valueOf(SessionUtil.currentStatium()));
            criteria.setMysqlLength(pageRequest.getPageSize());
            criteria.setMysqlOffset(pageRequest.getOffset());
            criteria.setOrderByClause("ct desc");

            criteria.setMysqlLength(pageRequest.getPageSize());
            criteria.setMysqlOffset(pageRequest.getOffset());
            criteria.setOrderByClause("ct desc");
            List<CrmTurnCardLog> ls = crmTurnCardLogMapper.selectByExample(criteria);
            total = crmTurnCardLogMapper.countByExample(criteria);
            for (CrmTurnCardLog turnLog : ls) {
                // 获取用户信息
                CardTurnListVo vo = new CardTurnListVo();
                BeanUtils.copyProperties(turnLog, vo);
                // 获取new用户信息
                SsoUser newUser = getUserById(turnLog.getNewUserId());
                if (StringUtils.isEmpty(newUser.getUsername())) {
                    vo.setNewName(newUser.getNickName());
                } else {
                    vo.setNewName(newUser.getUsername());
                }
                // new phone
                vo.setNewPhone(newUser.getPhone());
                // 获取old用户信息
                SsoUser oldUser = getUserById(turnLog.getOldUserId());
                if (StringUtils.isEmpty(oldUser.getUsername())) {
                    vo.setOldName(oldUser.getNickName());
                } else {
                    vo.setOldName(oldUser.getUsername());
                }
                // old phone
                vo.setOldPhone(oldUser.getPhone());
                list.add(vo);
            }
        } catch (Exception e) {
            logger.error("会员卡list {}", e.getMessage());
        }
        return new PageImpl<>(list, pageRequest, total);
    }

    /**
     *
     * <根据用户id获取用户信息><功能具体实现>
     *
     * @create：2016/12/13 上午10:17
     * @author：sl
     * @param userId
     * @return com.lc.zy.ball.domain.oa.po.SsoUser
     */
    public SsoUser getUserById (String userId) {
        SsoUser user = null;
        try {
            user = this.selectByPrimaryKey(SsoUser.class, userId);
        } catch (Exception e) {
            logger.debug("根据用户id获取用户信息 {}", e.getMessage());
        }
        return user;
    }

    /**
     *
     * <账户充值／续费><功能具体实现>
     *
     * @create：2016/12/14 下午3:18
     * @author：sl
     * @param myForm
     * @return void
     */
    @Transactional(readOnly = false)
    public void saveRechargeCard(RechargeCardVo myForm) {
        try {
            // 更新用户账户信息
            updateUserAccount(myForm);
            // 创建订单
            Order order = new Order();
            // userId
            order.setUserId(myForm.getUserId());
            if (myForm.getPayType() == Constants.payType.RANDOM) {
                // fee
                order.setFee(myForm.getAmount() * 100);
                // final_fee
                order.setFinalFee(myForm.getAmount() * 100);
            } else if (myForm.getPayType() == Constants.payType.CARDPAC) {
                // fee
                order.setFee(myForm.getCardAmount() * 100);
                // final_fee
                order.setFinalFee(myForm.getCardAmount() * 100);
            }
            String orderId = creatOrder(order, myForm.getCardType(), myForm.getCardId());
            myForm.setOrderId(orderId);
            // 更新道馆log(套餐)
            if (myForm.getPayType() == Constants.payType.CARDPAC) {
                rechargeCardLog(myForm);
            }
            // 更新用户log
            rechargeUserCardLog(myForm);

        } catch (Exception e) {
            logger.debug("账户充值／续费 {}", e.getMessage());
            throw new RuntimeException("账户充值、续费失败");
        }
    }

    /**
     *
     * <更新用户账户信息><功能具体实现>
     *
     * @create：2016/12/14 下午3:01
     * @author：sl
     * @param vo
     * @return void
     */
    @Transactional(readOnly = false)
    private void updateUserAccount(RechargeCardVo vo) {
        try {
            CrmUserCardAccount account = new CrmUserCardAccount();
            // id
            account.setId(vo.getAccountId());
            // 判断卡片类型(区分充值or延期)
            if (vo.getCardType() == Constants.cardType.CARD) {
                // 充值类型判断（随机or套餐）
                if (vo.getPayType() == Constants.payType.RANDOM) {
                    account.setBalance(vo.getBalance() + vo.getAmount() * 100);
                } else {
                    account.setBalance(vo.getBalance() + (vo.getCardAmount() + vo.getCardGift())* 100);
                }
            } else {
                account.setEndDate(DateUtils.getDate(vo.getEndDate(), "yyyy-MM-dd"));
            }
            // et
            account.setEt(new Date());
            // eb
            account.setEb(SessionUtil.currentUserId());

            this.updateByPrimaryKeySelective(account, vo.getAccountId());
        } catch (Exception e) {
            logger.debug("更新用户账户信息 {}", e.getMessage());
            throw new RuntimeException("更新用户账户信息失败");
        }
    }

    /**
     *
     * <充值购买套餐log><功能具体实现>
     *
     * @create：2016/12/14 下午3:14
     * @author：sl
     * @param vo
     * @return void
     */
    @Transactional(readOnly = false)
    public void rechargeCardLog (RechargeCardVo vo) {
        try {
            CrmCardLog crmCardLog = new CrmCardLog();
            // id
            crmCardLog.setId(UUID.get());
            // userId
            crmCardLog.setUserId(vo.getUserId());
            // statiumId
            crmCardLog.setStatiumId(Integer.valueOf(SessionUtil.currentStatium()));
            // cardId
            crmCardLog.setCardId(vo.getCardId());
            // cardType
            crmCardLog.setCardType(vo.getCardType());
            // amount
            crmCardLog.setAmount(vo.getCardAmount() * 100);
            // gift
            crmCardLog.setGiftAmount(vo.getCardGift() * 100);
            // buyType 0:app 1:线下
            crmCardLog.setBuyType(Constants.orderType.ONLINE);
            // ct
            crmCardLog.setCt(new Date());
            // cb
            crmCardLog.setCb(SessionUtil.currentUserId());
            // type 0、办卡 1、激活 2、注销 3、转卡 4、充值, 5、退款
            crmCardLog.setType(Constants.cardOpType.RECHARGE);
            this.insertSelective(crmCardLog, crmCardLog.getId());
        } catch (Exception e) {
            logger.debug("充值购买套餐log {}", e.getMessage());
            throw new RuntimeException("充值购买套餐log失败");
        }
    }

    /**
     *
     * <用户cardLog--充值／续费><功能具体实现>
     *
     * @create：2016/12/5 下午3:51
     * @author：sl
     * @param vo
     * @return void
     */
    @Transactional(readOnly = false)
    public void rechargeUserCardLog (RechargeCardVo vo)  {
        try {
            CrmUserCardLog crmUserCardLog = new CrmUserCardLog();
            // id
            crmUserCardLog.setId(UUID.get());
            // userId
            crmUserCardLog.setUserId(vo.getUserId());
            // cardId
            crmUserCardLog.setCardId(vo.getCardId());
            // type 0、办卡 1、激活 2、注销 3、转卡 4、充值,5、退款
            crmUserCardLog.setType(Constants.cardOpType.RECHARGE);
            if (vo.getPayType() == Constants.payType.RANDOM) {
                // amount
                int amount = vo.getAmount() * 100;
                crmUserCardLog.setAmount(amount);
                // balance
                crmUserCardLog.setBalance(amount + vo.getBalance());
            } else if (vo.getPayType() == Constants.payType.CARDPAC) {
                if (vo.getCardType() == Constants.cardType.CARD) {
                    // amount
                    int amount = (vo.getCardAmount() + vo.getCardGift()) * 100;
                    crmUserCardLog.setAmount(amount);
                    // balance
                    crmUserCardLog.setBalance(amount + vo.getBalance());
                } else {
                    crmUserCardLog.setStartDate(DateUtils.getDate(vo.getOldStartDate(), "yyyy-MM-dd"));
                    crmUserCardLog.setEndDate(DateUtils.getDate(vo.getEndDate(), "yyyy-MM-dd"));
                }
            }
            // order_type
            crmUserCardLog.setOrderType(Constants.orderType.ONLINE);
            // ct
            crmUserCardLog.setCt(new Date());
            // description
            crmUserCardLog.setDescription(Constants.cardHandel.RECHANGRCARD);
            // orderId
            crmUserCardLog.setOrderId(vo.getOrderId());
            // accountId
            crmUserCardLog.setAccountId(vo.getAccountId());
            // cardType
            crmUserCardLog.setCardType(vo.getCardType());

            this.insertSelective(crmUserCardLog, crmUserCardLog.getId());
        } catch (Exception e) {
            logger.debug("用户cardLog {}", e.getMessage());
            throw new RuntimeException("用户充值、续费log失败");
        }
    }

    /**
     *
     * <会员卡购买><功能具体实现>
     *
     * @create：2016/12/15 下午4:17
     * @author：sl
     * @param pageRequest
     * @param searchParams
     * @return com.lc.zy.ball.common.data.pageable.Page<com.lc.zy.ball.crm.framework.system.cardManage.vo.CardHistoryVo>
     */
    public Page<CardHistoryVo> findCardHistoty(PageRequest pageRequest, Map<String, Object> searchParams) {
        int total = 0;
        List<CardHistoryVo> list = new ArrayList<CardHistoryVo>();
        try {
            Map<String, SearchFilter> filters = SearchFilter
                    .parse(searchParams);
            CrmCardLogCriteria criteria = new CrmCardLogCriteria();
            criteria.setMysqlLength(pageRequest.getPageSize());
            criteria.setMysqlOffset(pageRequest.getOffset());
            CrmCardLogCriteria.Criteria cri = criteria.createCriteria();
            Criterias.bySearchFilter(cri, filters.values());
            criteria.setOrderByClause("ct desc");
            total = crmCardLogMapper.countByExample(criteria);
            List<CrmCardLog> ls =crmCardLogMapper.selectByExample(criteria);
            for (CrmCardLog log : ls) {
                CardHistoryVo vo = new CardHistoryVo();
                BeanUtils.copyProperties(log, vo);
                // 根据用户id获取用户信息
                SsoUser user = getUserById(log.getUserId());
                if (StringUtils.isNotEmpty(user.getUsername())) {
                    vo.setUserName(user.getUsername());
                } else {
                    vo.setUserName(user.getNickName());
                }

                list.add(vo);
            }
        } catch (Exception e) {
            logger.error("会员卡购买list {}", e.getMessage());
        }
        return new PageImpl<>(list, pageRequest, total);
    }
}
