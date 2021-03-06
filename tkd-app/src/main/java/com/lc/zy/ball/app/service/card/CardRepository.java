package com.lc.zy.ball.app.service.card;

import com.lc.zy.ball.app.common.AbstractCacheService;
import com.lc.zy.ball.app.common.CommonService;
import com.lc.zy.ball.app.common.Constants;
import com.lc.zy.ball.app.service.card.bean.CrmCardTradeVo;
import com.lc.zy.ball.app.service.card.bean.CrmCardVo;
import com.lc.zy.ball.app.service.card.bean.CrmUserCardAccountVo;
import com.lc.zy.ball.app.service.crmUser.CrmUserRepository;
import com.lc.zy.ball.domain.oa.mapper.*;
import com.lc.zy.ball.domain.oa.po.*;
import com.lc.zy.common.bean.CacheKeys;
import com.lc.zy.common.bean.KeyValueEntity;
import com.lc.zy.common.bean.Reason;
import com.lc.zy.common.bean.Success;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.Encrypts;
import com.lc.zy.common.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.geronimo.mail.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class CardRepository extends AbstractCacheService implements CacheKeys{
	
	@Autowired
	private CrmCardMapper crmCardMapper;
	
	@Autowired
	private CrmUserCardAccountMapper crmUserCardAccountMapper;
	
	@Autowired
	private SsoUserMapper ssoUserMapper;
	
	@Autowired
	private CrmUserCardLogMapper crmUserCardLogMapper;
	
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Autowired
	private StatiumInfosMapper statiumInfosMapper;
	
    @Autowired
    private CommonService commonService;
    
	
	private static Logger logger = LoggerFactory.getLogger(CrmUserRepository.class);
	
	/**
	 * 
	 * <卡片列表><功能具体实现>
	 *
	 * @create：2017年1月16日 下午4:30:15
	 * @author：zzq
	 * @param ssoUser
	 * @return
	 */
	public List<CrmCardVo> getCardList(SsoUser ssoUser){
		 Integer dgId = ssoUser.getStatiumId();
		 CrmCardCriteria crmCardCriteria = new CrmCardCriteria();
		 crmCardCriteria.createCriteria().andStatiumIdEqualTo(dgId).andCardTypeEqualTo(Constants.CardType.PAIEDCARD).andStatusEqualTo(Constants.CardStatus.ACTIVE);
		 List<CrmCard> cards = crmCardMapper.selectByExample(crmCardCriteria);
		 List<CrmCardVo> vos = new ArrayList<CrmCardVo>();
		 if(cards.size()!=0){
			 for(CrmCard card:cards){
				 CrmCardVo cardVo = new CrmCardVo();
				 //卡片id
				 cardVo.setCardId(card.getId());
				 //实付费用
				 cardVo.setFinalFee(new BigDecimal(card.getCardAmount()).divide(new BigDecimal(100)).intValue());
				 //赠送费用
				 cardVo.setGiftFee(new BigDecimal(card.getCardGift()).divide(new BigDecimal(100)).intValue());
				 vos.add(cardVo);
			 }
		 }
		 return vos;
	}
	
	/**
	 * 
	 * <账户余额详情><功能具体实现>
	 *
	 * @create：2016年11月25日 下午3:47:05
	 * @author：zzq
	 * @param ssoUser
	 * @return
	 */
	public CrmUserCardAccountVo getCrmUserAccount(SsoUser ssoUser){
		String userId = ssoUser.getId();
		//账户余额
		CrmUserCardAccountCriteria cardAccountCriteria = new CrmUserCardAccountCriteria();
		cardAccountCriteria.createCriteria().andUserIdEqualTo(userId).andCardTypeEqualTo(Constants.CardType.PAIEDCARD);
		cardAccountCriteria.createCriteria().andStatiumIdEqualTo(ssoUser.getStatiumId());
		
		List<CrmUserCardAccount> list = crmUserCardAccountMapper.selectByExample(cardAccountCriteria);
		CrmUserCardAccountVo vo =  new CrmUserCardAccountVo();
		CrmUserCardAccount account = null;
		if(list.size()>0){
			account = list.get(0);
		}else{
			//创建用户账户
			account = createAccount(userId);
		}
			//绑定的道馆id
			vo.setStatiumId(account.getStatiumId());
			//账户余额  显示除以100的形式
			vo.setBalance(new BigDecimal(account.getBalance()).divide(new BigDecimal(100)).intValue());
			//是否设置密码
			if(StringUtils.isEmpty(account.getPasswd())){
				vo.setIsPwd(0);
			}else{
				vo.setIsPwd(1);
			}
			//用户id
			vo.setUserId(userId);
		return vo;
		
	}
	
	/**
	 * 
	 * <根据用户id创建账户><功能具体实现>
	 *
	 * @create：2016年11月25日 下午4:12:17
	 * @author：zzq
	 * @param userId
	 * @return
	 */
	public CrmUserCardAccount createAccount(String userId){
		CrmUserCardAccount account = new CrmUserCardAccount();
		account.setId(UUID.get());
		account.setUserId(userId);
		//没有进行办卡和充值操作的会员卡状态设置为未激活
		account.setStatus(Constants.cardUserStatus.ACTIVE);
		account.setFrozen(0);
		account.setCb(userId);
		account.setCt(new Date());
		account.setEt(new Date());
		account.setEb(userId);
		account.setBalance(0);
		//储值卡
		account.setCardType(Constants.CardType.PAIEDCARD);
		//绑定道馆id
		SsoUser ssoUser;
		try {
			ssoUser = this.selectByPrimaryKey(SsoUser.class, userId);
			account.setStatiumId(ssoUser.getStatiumId());
		} catch (Exception e1) {
			logger.error("查询用户"+e1.getMessage());
		}
		try {
			this.insertSelective(account, account.getId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存用户表"+e.getMessage());
		}
		return account;
	}
	
	/**
	 * 
	 * <获取会员卡的交易列表><功能具体实现>
	 *
	 * @create：2016年11月28日 上午9:52:37
	 * @author：zzq
	 * @param ssoUser
	 * @return
	 */
	public	List<CrmCardTradeVo> getTradeList(SsoUser ssoUser,Integer begin,Integer size){
		List<CrmCardTradeVo> list = new ArrayList<CrmCardTradeVo>();
		String userId = ssoUser.getId();
		CrmUserCardLogCriteria crmUserCardLogCriteria = new CrmUserCardLogCriteria();
		crmUserCardLogCriteria.createCriteria().andUserIdEqualTo(userId).andCardTypeEqualTo(Constants.CardType.PAIEDCARD);
		crmUserCardLogCriteria.setMysqlLength(size);
		crmUserCardLogCriteria.setMysqlOffset(begin);
		crmUserCardLogCriteria.setOrderByClause("ct desc");
		List<CrmUserCardLog> cardList = crmUserCardLogMapper.selectByExample(crmUserCardLogCriteria);
		if(cardList!=null){
			for(CrmUserCardLog cardLog : cardList){
				CrmCardTradeVo vo = new CrmCardTradeVo();
				vo.setLogId(cardLog.getId());
				vo.setType(cardLog.getType());
				Date date = cardLog.getCt();
				String dateStr = DateUtils.formatDate(date, "yyyy-MM-dd");
				vo.setDate(dateStr);
				Integer type = cardLog.getType();
				//0 办卡 1 消费， 3转卡，4 充值，5退款,
				String amount = "";
				if(type==1){
					amount = new BigDecimal(cardLog.getAmount()).divide(new BigDecimal(100)).toString();
				}else{
					amount = new BigDecimal(cardLog.getAmount()).divide(new BigDecimal(100)).toString();
					amount = "+" + amount;
				}
				vo.setAmount(amount);
				vo.setDescribe(cardLog.getDescription());
				list.add(vo);
			}
		}
		return list;
	}
	
	/**
	 * 
	 * <会员卡交易记录详情><功能具体实现>
	 *
	 * @create：2016年11月28日 上午10:40:34
	 * @author：zzq
	 * @param logId
	 * @return
	 */
	public CrmCardTradeVo tradeLogDetail(String logId){

		CrmCardTradeVo vo = null;
		try {
			vo = new CrmCardTradeVo();
			//List<WithdrawCashLogDetailVo> voList = new ArrayList<WithdrawCashLogDetailVo>();
			CrmUserCardLog crmUserCardLog =	this.selectByPrimaryKey(CrmUserCardLog.class, logId);
			
			//交易金额
			Integer amount = crmUserCardLog.getAmount();
			BigDecimal base = new BigDecimal(100);
			BigDecimal bigDecimal = new BigDecimal(amount);
			String finalAmount = bigDecimal.divide(base, 2, BigDecimal.ROUND_HALF_UP).toString();
			
			vo.setAmount(finalAmount);
			//账户余额
			Integer balance = crmUserCardLog.getBalance();
			BigDecimal bigDecimal2 = new BigDecimal(balance);
			String finalBalance = bigDecimal2.divide(base, 2, BigDecimal.ROUND_HALF_UP).toString();
			vo.setBalance(finalBalance);
			
			//交易类型  ：：0 办卡，1 消费，2 转卡，3 卡种变更，4 、续费
			int type = crmUserCardLog.getType();
			vo.setType(type);
			
			
			
			//充值支付类型
//			if(userAccountLog.getTradetype().equals(1)){
//				vo.setTradeType("支付宝");
//			}else{
//				vo.setTradeType("微信");
//			}
			//交易时间
			vo.setTime(DateUtils.forDatetime(crmUserCardLog.getCt()));
			//设置交易描述
			vo.setDescribe(crmUserCardLog.getDescription());
			
			//订单id
			if(crmUserCardLog.getOrderId()!=null){
				vo.setOrderId(crmUserCardLog.getOrderId());
				Order order = this.selectByPrimaryKey(Order.class, crmUserCardLog.getOrderId());

                // 获取订单详情
                OrderItemCriteria orderItemCriteria = new OrderItemCriteria();
                OrderItemCriteria.Criteria criteria = orderItemCriteria.createCriteria();
                criteria.andOrderIdEqualTo(crmUserCardLog.getOrderId());
                List<OrderItem> orderItems = orderItemMapper.selectByExample(orderItemCriteria);

                // 订单类型 0课程 1活动 2充值
				int ordersType = order.getOrdersType();
                vo.setOrdersType(ordersType);
                //道馆名称
                String statiumName = "";
                StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
                StatiumInfosCriteria.Criteria criteria1 = statiumInfosCriteria.createCriteria();
                criteria1.andDgIdEqualTo(order.getStatiumId());
                List<StatiumInfos> statiumInfoses = statiumInfosMapper.selectByExample(statiumInfosCriteria);
                if(statiumInfoses!=null){
                	statiumName = statiumInfoses.get(0).getDgName();
                	vo.setStatiumName(statiumName);
                }
				switch (ordersType) {
				case 0:
					// 课程
                    vo.setClassName(orderItems.get(0).getClassName());
                    // 教练
                    StatiumClassInfo statiumClassInfo = this.selectByPrimaryKey(StatiumClassInfo.class, orderItems.get(0).getClassInfoId());
                    vo.setCoachName(statiumClassInfo.getCoachName());
					break;
				case 1:
					// 活动
                    vo.setActivityName(orderItems.get(0).getActivityName());
					break;
				case 2:
					//充值
                	//会员卡名称
                	/*String cardId = orderItems.get(0).getCardId();
                	CrmCard crmCard = this.selectByPrimaryKey(CrmCard.class, cardId);
                	if(crmCard!=null){
                		String cardName = crmCard.getCardName();
                		vo.setCardName(cardName);
                	}*/
                	//会员卡类型
                	vo.setCardType(Constants.CardType.PAIEDCARD);
                	break;
				default:
					break;
				}
			}
			
			//vo.setList(voList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("提现记录详情查询异常userAccountLogId={}", logId);
			throw new RuntimeException(e);
		}
		return vo;
	}
	
	
	/**
     * 
     * <设置支付密码><功能具体实现>
     *
     * @create：2015年11月20日 上午11:41:03
     * @author： CYY
     * @param user
     * @param password
     * @return
     */
    public Success setPwd(SsoUser user, String password) {
        try {
            CrmUserCardAccount account = new CrmUserCardAccount();
			CrmUserCardAccountCriteria crmUserCardAccountCriteria = new CrmUserCardAccountCriteria();
	        crmUserCardAccountCriteria.createCriteria().andUserIdEqualTo(user.getId()).andCardTypeEqualTo(Constants.CardType.PAIEDCARD);
	        
	        List<CrmUserCardAccount> cardAccounts = crmUserCardAccountMapper.selectByExample(crmUserCardAccountCriteria);
	        if(cardAccounts==null){
	        	return new Success("", false, new Reason(Constants.SYSTEM_KEY, "账户不存在，请联系客服"));
	        }
	        account = cardAccounts.get(0);
            account.setPasswd(Encrypts.encodePassword(password));
            account.setEt(new Date());
            account.setEb(user.getId());
			// modify by sl 2016-01-16
//            this.updateByPrimaryKeySelective(account, account.getUserId());
            this.updateByPrimaryKeySelective(account, account.getId());
            String key = Constants.ACCOUNT_NUMBER + DateUtils.formatDate(new Date());
            commonService.hset(key, user.getId(), "0");
            commonService.expire(key, 24 * 60 * 60);
            return new Success("", true, new KeyValueEntity("isSetPayPwd", 1));
        } catch (Exception e) {
            logger.error("AccountRepository setPwd={}", e);
            throw new RuntimeException(e);
        }
    }
    
    
    /**
     * 
     * <验证支付密码><功能具体实现>
     *
     * @create：2015年11月21日 下午2:23:24
     * @author： CYY
     * @param user
     * @param password 支付密码
     * @return
     */
    public Success validatePayPwd(SsoUser user, String password,String token) {
        try {
        	CrmUserCardAccount account = new CrmUserCardAccount();
			CrmUserCardAccountCriteria crmUserCardAccountCriteria = new CrmUserCardAccountCriteria();
	        crmUserCardAccountCriteria.createCriteria().andUserIdEqualTo(user.getId()).andCardTypeEqualTo(Constants.CardType.PAIEDCARD);
	        crmUserCardAccountCriteria.createCriteria().andStatiumIdEqualTo(user.getStatiumId());
	        List<CrmUserCardAccount> cardAccounts = crmUserCardAccountMapper.selectByExample(crmUserCardAccountCriteria);
	        if(cardAccounts==null){
	        	return new Success("", false, new Reason(Constants.SYSTEM_KEY, "账户不存在，请联系客服"));
	        }
	        account = cardAccounts.get(0);
            //password = Encrypts.encodePassword(password1);
            password = Encrypts.encodePassword(password);
            String key = Constants.ACCOUNT_NUMBER + DateUtils.formatDate(new Date());
            if (!password.equals(account.getPasswd())) {
                String strNum = commonService.hget(key, user.getId());
                strNum = strNum == null ? "0" : strNum;
                Integer number = Integer.parseInt(strNum) + 1;
                if (Constants.PAY_NUMBER <= number) {
                    return new Success("", false, new Reason("numberOut", "支付密码输错已超过次数，请找回支付密码"));
                }
                commonService.hincrBy(key, user.getId(), 1L);
                commonService.expire(key, 24 * 60 * 60);
                return new Success("", false, new Reason(Constants.SYSTEM_KEY, "支付密码不正确，你还可以输入" + (Constants.PAY_NUMBER - number) + "次"));
            }
            Success success = isPayPwd(user.getId());
            if(success.isSuccess()){
                commonService.hset(key, user.getId(), "0");
                commonService.expire(key, 24 * 60 * 60);
                String checkToken = UUID.get();
                commonService.setStr("checkToken_"+token, checkToken,5*60);
                return new Success("", true,new KeyValueEntity("checkToken", checkToken));
            }else{
                return success;
            }
        } catch (Exception e) {
            logger.error("AccountRepository validatePayPwd={}", e);
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 
     * <支付密码是否可用><功能具体实现>
     *
     * @create：2015年11月21日 下午2:23:24
     * @author： CYY
     * @param userId
     * @return
     */
    public Success isPayPwd(String userId) {
        try {
            String key = Constants.ACCOUNT_NUMBER + DateUtils.formatDate(new Date());
            String strNum = commonService.hget(key, userId);
            strNum = strNum == null ? "0" : strNum;
            Integer number = Integer.parseInt(strNum) + 1;
            if (Constants.PAY_NUMBER <= number) {
                return new Success("", false, new Reason("numberOut", "支付密码输错已超过次数，请找回支付密码"));
            }
            return new Success("", true);
        } catch (Exception e) {
            logger.error("cardRepository isPayPwd={}", e);
            throw new RuntimeException(e);
        }
    }
    
    /**
	 * 
	 * <余额是否够支付><功能具体实现>
	 *
	 * @create：2016年3月2日 下午6:56:29
	 * @author： CYY
	 * @param userId
	 * @param finalFee
	 * @return
	 */
    public boolean isAmountFull(String userId,Double finalFee) {
        try {
            //此处不查缓存
        	CrmUserCardAccount account = new CrmUserCardAccount();
			CrmUserCardAccountCriteria crmUserCardAccountCriteria = new CrmUserCardAccountCriteria();
	        crmUserCardAccountCriteria.createCriteria().andUserIdEqualTo(userId).andCardTypeEqualTo(Constants.CardType.PAIEDCARD);
	        List<CrmUserCardAccount> cardAccounts = crmUserCardAccountMapper.selectByExample(crmUserCardAccountCriteria);
	        if(cardAccounts!=null){
	        	account = cardAccounts.get(0);
	            BigDecimal base = new BigDecimal(100);
	            BigDecimal decimal1 = new BigDecimal(account.getBalance());
	            Double balance = decimal1.divide(base, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
	            if(balance.compareTo(finalFee) < 0){
	                return false;
	            }
	        }else{
	        	createAccount(userId);
	        	return false;
	        }
	        
        } catch (Exception e) {
            logger.error("AccountRepository isAmountPay_exception={}", e);
            throw new RuntimeException(e);
        }
        return true;
    }
    
    /**
     * 
     * <会员卡金额修改 消费课程或活动订单><功能具体实现>
     *
     * @create：2017年1月16日 下午2:23:03
     * @author：zzq
     * @param userId
     * @param money
     * @param tradeNo
     * @param orderId
     * @param customerId
     * @param statiumInfos
     * @param number
     * @return
     * @throws Exception
     */
	public void accountUpdate(String userId, Integer money,String tradeNo,String orderId,String customerId,StatiumInfos statiumInfos,String number) throws Exception{
		try {
			//type 0 可用金额，1 冻结金额
			//money 充值/退款为正数*100，消费/提现为负数*100
			//查找此用户的会员卡账户
			CrmUserCardAccountCriteria crmUserCardAccountCriteria = new CrmUserCardAccountCriteria();
        	crmUserCardAccountCriteria.createCriteria().andUserIdEqualTo(userId).andCardTypeEqualTo(Constants.CardType.PAIEDCARD).andStatusEqualTo(1);
        	List<CrmUserCardAccount> cardAccounts = crmUserCardAccountMapper.selectByExample(crmUserCardAccountCriteria);
        	CrmUserCardAccount account = null;
        	if(cardAccounts.size()!=0){
        		account = cardAccounts.get(0);
        	}
			
        	//会员卡更新
			Integer balance = account.getBalance();
			account.setBalance(balance+money);
			account.setEt(new Date());
			account.setEb(userId);
			this.updateByPrimaryKeySelective(account, account.getId());
			
			//会员卡日志表记录
			CrmUserCardLog ual = new CrmUserCardLog();
			
			ual.setId(UUID.get());
			ual.setAmount(money);
			ual.setBalance(account.getBalance());
			ual.setDescription("消费");
			ual.setTradeno(tradeNo);
			ual.setOrderId(orderId);
			ual.setStatus(1);
			ual.setType(Constants.CrmUserCardStatus.CONSUME);
			ual.setUserId(customerId);
			ual.setCt(new Date());
			ual.setCardId(account.getId());
			ual.setNumber(number);
			//渠道
			ual.setOrderType(Constants.CHANNEL.APP);
			ual.setAccountId(account.getId());
			ual.setCardType(Constants.CardType.PAIEDCARD);
			this.insertAccountLog(ual);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("账户金额更新异常userId={}", userId);
			throw new RuntimeException(e);
		}
	}
	

  	/**
	 * <插入日志记录><功能具体实现>
	 * @param crmUserCardLog
	 * @author yankefei
	 * @date 2015年12月4日 下午2:42:54
	 */
	public void insertAccountLog(CrmUserCardLog crmUserCardLog){
		try {
			this.insert(crmUserCardLog, crmUserCardLog.getId());
		} catch (Exception e) {
			logger.error("插入会员卡日志记录异常");
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * <验证支付的时候判断是不是激活用户><功能具体实现>
	 *
	 * @create：2016年12月8日 下午6:03:28
	 * @author：zzq
	 * @param userId
	 * @return
	 */
	public boolean isActiveAccount(String userId){
		Boolean flag = true;
		CrmUserCardAccountCriteria crmUserCardAccountCriteria = new CrmUserCardAccountCriteria();
		//抽储值卡类型 状态是激活的指定用户
		crmUserCardAccountCriteria.createCriteria().andUserIdEqualTo(userId).andCardTypeEqualTo(Constants.CardType.PAIEDCARD);
		List<CrmUserCardAccount> accountList = crmUserCardAccountMapper.selectByExample(crmUserCardAccountCriteria);
		if(accountList!=null){
			Integer status = accountList.get(0).getStatus();
			if(status == 0){
				flag = false;
			}
		}
		return flag;
	}
}
