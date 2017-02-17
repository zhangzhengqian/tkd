package com.lc.zy.ball.app.service.account;

import com.lc.zy.ball.app.common.AbstractCacheService;
import com.lc.zy.ball.app.common.CommonService;
import com.lc.zy.ball.app.common.Constants;
import com.lc.zy.ball.app.service.account.bean.UserAccountLogDetailVo;
import com.lc.zy.ball.app.service.account.bean.UserAccountLogVo;
import com.lc.zy.ball.app.service.account.bean.UserAccountVo;
import com.lc.zy.ball.app.service.account.bean.WithdrawCashLogDetailVo;
import com.lc.zy.ball.domain.oa.mapper.*;
import com.lc.zy.ball.domain.oa.po.*;
import com.lc.zy.common.bean.CacheKeys;
import com.lc.zy.common.bean.KeyValueEntity;
import com.lc.zy.common.bean.Reason;
import com.lc.zy.common.bean.Success;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.Encrypts;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.util.alipay.config.AlipayConfig;
import com.lc.zy.common.util.alipay.util.SignUtils;
import com.lc.zy.common.util.payUtils.PayParameter;
import com.lc.zy.common.util.wxap.util.MD5Util;
import com.lc.zy.common.zoo.SEQGenerate;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

@Repository
@Transactional
public class AccountRepository extends AbstractCacheService implements CacheKeys {

    private static Logger logger = LoggerFactory.getLogger(AccountRepository.class);
    
    private static String NOTIFY_URL_NEW_ALIPAY = "alipay/recharge_notify_new";
    
	private static String NOTIFY_URL_NEW_TENPAY = "tenpay/recharge_notify_new";
	
	private static String PREPAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    
	@Resource(name="configs")
	private Map<String,String> configs;
	
    @Autowired
    private SEQGenerate seqGenerate;
    
    @Autowired
    private PayParameter payParameter;
    
    @Autowired
    private SsoUserAccountMapper userAccountMapper;
    
    @Autowired
    private SsoUserAccountLogMapper userAccountLogMapper;
    
    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private CommonService commonService;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private StatiumInfosMapper statiumInfosMapper;

    /**
     * <创建账户><功能具体实现>
     * @param userId
     * @author yankefei
     * @date 2015年11月20日 上午11:53:39
     */
    public SsoUserAccount createAccount(String userId){
		try {
			SsoUserAccount ua = new SsoUserAccount();
			ua.setUserId(userId);
			ua.setBalance(0);
			ua.setFrozen(0);	//冻结金额为不能消费且不能体现
			ua.setIsfreeze(0);	//0为正常账户，1为冻结账户
			ua.setCt(new Date());
			ua.setEt(new Date());
			ua.setCb(userId);
			ua.setEb(userId);
			
			this.insertSelective(ua, userId);
			return ua;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("创建个人账户失败userId={}", userId);
			throw new RuntimeException(e);
		}
    }
    
    /**
     * <查询账户余额><功能具体实现>
     * @param userId
     * @return
     * @author yankefei
     * @date 2015年10月22日 下午7:16:19
     */
	public UserAccountVo accountDetail(String userId) {
		UserAccountVo userAccountVo = null;
		try {
			SsoUserAccount ua = this.selectByPrimaryKey(SsoUserAccount.class, userId);
			userAccountVo = new UserAccountVo();
			
			BigDecimal decimal = new BigDecimal(100);
			BigDecimal bigDecimal1 = new BigDecimal(ua.getBalance());
			BigDecimal bigDecimal2 = new BigDecimal(ua.getFrozen());
			BigDecimal bigDecimal3 = new BigDecimal(ua.getBalance()+ua.getFrozen());
			String freeMoney = bigDecimal1.divide(decimal, 2, BigDecimal.ROUND_HALF_UP).toString();
			String frozenTotal = bigDecimal2.divide(decimal, 2, BigDecimal.ROUND_HALF_UP).toString();
			String total = bigDecimal3.divide(decimal, 2, BigDecimal.ROUND_HALF_UP).toString();
			//冻结金额
			userAccountVo.setFrozen(frozenTotal);
			//可提现金额
			userAccountVo.setFree(freeMoney);
			//总金额
			userAccountVo.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询账户信息异常={}", e.getMessage());
			throw new RuntimeException(e);
		}
		return userAccountVo;
	}
	
	/**
	 * <充值><功能具体实现>
	 * @param userId
	 * @param type
	 * @param amount
	 * @param ip
	 * @return
	 * @author yankefei
	 * @date 2015年10月26日 下午6:52:28
	 */
	public KeyValueEntity recharge(String userId, int type, String amount, String ip) {
		KeyValueEntity entity = null;
		try {
			//总余额
//			Double total = totalAmount(userId);
			Double total = 0.00;
			//交易流水号
			String tradeNo = seqGenerate.genTradeNo();
			//总公司orgCode=master
	        Organization organization = payParameter.payKeys("master");
			if(type==1){
				//支付宝签名
				String partner = organization.getPartner();
				String SELLER = organization.getSellerId();
				String privateKey = organization.getPrivateKey();
		        // 签约合作者身份ID
		        String info = "partner=" + "\"" + partner + "\"";
		        // 签约卖家支付宝账号
		        info += "&seller_id=" + "\"" + SELLER + "\"";
		        // 商户网站唯一订单号
		        info += "&out_trade_no=" + "\"" + tradeNo + "\""; // 自己的订单号
		        // 商品名称
		        info += "&subject=" + "\"充值\""; // 场馆名称
		        // 商品详情
		        info += "&body=" + "\"充值\""; // 场馆地址
		        // 商品金额
		        info += "&total_fee=" + "\"" + amount + "\"";
		        //服务器异步通知页面路径 orderInfo += "&notify_url=" + "\"" +
		        //"http://notify.msp.hk/notify.htm" + "\"";
		        info += "&notify_url=" + "\"" + configs.get("callbackServer")+ NOTIFY_URL_NEW_ALIPAY + "\"";
		        // 服务接口名称， 固定值
		        info += "&service=\"mobile.securitypay.pay\"";
		        // 支付类型， 固定值
		        info += "&payment_type=\"1\"";
		        // 参数编码， 固定值
		        info += "&_input_charset=\"" + AlipayConfig.input_charset + "\"";
		        // 设置未付款交易的超时时间
		        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		        // 取值范围：1m～15d。
		        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		        // 该参数数值不接受小数点，如1.5h，可转换为90m。
		        info += "&it_b_pay=\"30m\"";
		        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";
		        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		        info += "&show_url=\"m.alipay.com\"";
		        
	        	logger.debug("pay_sign : params={} ; privateKey={}", info, privateKey); 
	        	String sign = SignUtils.sign(info, privateKey);
	        	 //签完名转换编码
	        	sign = URLEncoder.encode(sign, "UTF-8");
	        	
	        	entity = new KeyValueEntity();
	            entity.append("sign", sign);
	            entity.append("info", info);
			}else if(type==2){
				//微信支付签名
		        String appid = organization.getAppid();
		        String mchId = organization.getMchId();
		        String appKey = organization.getAppKey();
		        
		        //微信需要把金额*100
		        BigDecimal decimal = new BigDecimal(100);
		        BigDecimal decimal2 = new BigDecimal(amount);
		        String wxAmount = decimal2.multiply(decimal).toString();
		        
		        SortedMap<String, String> signParams = new TreeMap<String, String>();
		        signParams.put("appid", appid);
		        signParams.put("mch_id", mchId);
		        signParams.put("nonce_str", UUID.get());
		        signParams.put("body", "充值");
		        logger.debug("ip new = {}", ip);
		        signParams.put("spbill_create_ip", ip);
		        signParams.put("out_trade_no", tradeNo);
		        signParams.put("trade_type", "APP");
		        signParams.put("total_fee", wxAmount);	//正常情况下，微信最少充值1元钱
//		        signParams.put("total_fee", amount);	//测试充值1分钱使用
		        signParams.put("notify_url", configs.get("callbackServer")+NOTIFY_URL_NEW_TENPAY);
		        
		        StringBuffer sb = new StringBuffer();
		        for (String key : signParams.keySet()) {
		            sb.append(key).append("=").append(signParams.get(key)).append("&");
		        }
		        sb.append("key=").append(appKey);
		        logger.debug("params : {}", sb);
		        String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8");
		        signParams.put("sign", sign.toUpperCase());
		        logger.debug("sign : {}", sign);
		        
		        String requestBody = CommonOAUtils.converteToXml(signParams);
		        logger.debug("xml : {}", requestBody);
		        
		        //微信预支付
		        Map<String, String> responseMap = null;
		        try {
		        	PostMethod method = new PostMethod(PREPAY_URL);
		        	method.addRequestHeader("Content-Type", "text/plain; charset=utf-8");
		        	RequestEntity req = new StringRequestEntity(requestBody, "text/xml", "UTF-8");
		        	method.setRequestEntity(req);
		        	HttpClient client = new HttpClient();
		        	client.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
					client.executeMethod(method);
					String responseData = method.getResponseBodyAsString();
					responseData = new String(responseData.getBytes("ISO-8859-1"), "UTF-8");
					logger.debug("responseData=" + responseData);
					responseMap = CommonOAUtils.converteToMap(responseData);
				} catch (Exception e) {
					logger.error("微信预支付异常:" + e.getMessage());
					throw new RuntimeException(e);
				}
		        
		        //解析微信预支付返回
		        if ("SUCCESS".equals(responseMap.get("return_code"))) {
		            if ("SUCCESS".equals(responseMap.get("result_code"))) {
		                // 把客户端需要的参数MD5加密
		                Long timestamp = System.currentTimeMillis() / 1000;
		                String noncestr = signParams.get("nonce_str");
		                StringBuilder build = new StringBuilder();
		                build.append("appid");
		                build.append('=');
		                build.append(appid);
		                build.append('&');
		                build.append("noncestr");
		                build.append('=');
		                build.append(noncestr);
		                build.append('&');
		                build.append("package");
		                build.append('=');
		                build.append("Sign=WXpay");
		                build.append('&');
		                build.append("partnerid");
		                build.append('=');
		                build.append(mchId);
		                build.append('&');
		                build.append("prepayid");
		                build.append('=');
		                build.append(responseMap.get("prepay_id"));
		                build.append('&');
		                build.append("timestamp");
		                build.append('=');
		                build.append(timestamp);
		                build.append('&');
		                build.append("key=");
		                build.append(appKey);
		                logger.debug("client sign=" + build.toString());
		                //返回客户端加密串
		                String returnSign = MD5Util.MD5Encode(build.toString(), "UTF-8");
		                
		                entity = new KeyValueEntity();
		                entity.append("prepay_id", responseMap.get("prepay_id"));
		                entity.append("sign", returnSign);
		                entity.append("timestamp", timestamp);
		                entity.append("noncestr", noncestr);
		            } else {
		            	logger.error("微信充值签名失败={}",responseMap.get("err_code_des"));
		            	throw new RuntimeException("微信充值签名失败");
		            }
		        }
			}
			//添加充值记录日志
			if(entity!=null){
				SsoUserAccountLog ual = new SsoUserAccountLog();
				String ualId = UUID.get();
				ual.setId(ualId);
				//设置充值金额*100
				BigDecimal bigDecimal = new BigDecimal(amount);
				BigDecimal decimal = new BigDecimal(100);
				Integer money = bigDecimal.multiply(decimal).intValue();
				ual.setAmount(money);
				
				//设置充值之后账户余额
				BigDecimal bigDecimal2 = new BigDecimal(total);
				Integer totalInteger = bigDecimal2.multiply(decimal).intValue();
				
				//0 充值，1 消费，2 提现，3 退款，4 其他
				ual.setType(Constants.AccountUseType.RECHARGE);
				ual.setUserId(userId);
				ual.setTradetype(type);
				ual.setTradeno(tradeNo);
				ual.setBalance(money+totalInteger);
				//0未完成1完成
				ual.setStatus(0);
				ual.setDescription("充值");
				ual.setCt(new Date());
				
				this.insertSelective(ual, ualId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("充值生成签名记录日志失败userId={}", userId);
			throw new RuntimeException(e);
		}
		return entity;
	}
	
	/**
	 * <获取账户绑定的银行或第三方支付账号信息><功能具体实现>
	 * @param userId
	 * @return
	 * @author yankefei
	 * @date 2015年10月28日 上午11:11:07
	 */
//	public List<UserAccountAttrVo> getAccountAttrs(String userId) {
//		//目前只支持支付宝微信
//		List<UserAccountAttrVo> list = new ArrayList<UserAccountAttrVo>();
//		try {
//			UserAccountAttrsCriteria criteria = new UserAccountAttrsCriteria();
//			UserAccountAttrsCriteria.Criteria cri = criteria.createCriteria();
//			UserAccountAttrsCriteria.Criteria cri2 = criteria.createCriteria();
//			criteria.or(cri2);
//			cri.andUserIdEqualTo(userId);
//			cri.andBankNameEqualTo("支付宝");
//			cri.andStatusEqualTo(0);
//			
//			cri.andUserIdEqualTo(userId);
//			cri.andBankNameEqualTo("微信");
//			cri.andStatusEqualTo(0);
//			List<UserAccountAttrs> accountAttrs = userAccountAttrsMapper.selectByExample(criteria);
//			
//			for(UserAccountAttrs attr:accountAttrs){
//				UserAccountAttrVo vo = new UserAccountAttrVo();
//				vo.setAttrId(attr.getId());
//				vo.setType(attr.getBankName());
//				list.add(vo);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("查询用户账户绑定银行及第三方支付信息时异常userId={}", userId);
//			throw new RuntimeException(e);
//		}
//		return list;
//	}
	
	/**
	 * <根据交易流水号查询记录><功能具体实现>
	 * @param tradeNo
	 * @return
	 * @author yankefei
	 * @date 2015年10月28日 下午6:58:40
	 */
	public SsoUserAccountLog getLogByTradeNo(String tradeNo, Integer payType){
		try {
			SsoUserAccountLogCriteria userAccountLogCriteria = new SsoUserAccountLogCriteria();
			SsoUserAccountLogCriteria.Criteria criteria = userAccountLogCriteria.createCriteria();
			criteria.andTradenoEqualTo(tradeNo);
			criteria.andTradetypeEqualTo(payType);
			List<SsoUserAccountLog> list = userAccountLogMapper.selectByExample(userAccountLogCriteria);
			if(list!=null && list.size()>=1){
				return list.get(0);	
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据交易流水号查询记录异常={}", e.getMessage());
		}
		return null;
	}
	
	/**
	 * <账户金额修改，包括充值、消费、提现、退款><功能具体实现>
	 * @param userId
	 * @param money
	 * @param type
	 * @author yankefei
	 * @date 2015年10月29日 下午2:08:10
	 */
	public Integer accountUpdate(String userId, Integer money, Integer type,String tradeNo,String orderId,String customerId) throws Exception{
		try {
			Integer result = 0;
			//type 0 可用金额，1 冻结金额
			//money 充值/退款为正数*100，消费/提现为负数*100
			SsoUserAccount account = this.selectByPrimaryKey(SsoUserAccount.class, userId);
			if(account == null){
                account = createAccount(userId);
            }
			Integer balance = account.getBalance();
			Integer frozen = account.getFrozen();
			result = balance+frozen;
			SsoUserAccountLog ual = new SsoUserAccountLog();

			switch (type) {
			case 0:
				//可用金额操作
				account.setBalance(balance+money);
				break;
			case 1:
				//冻结金额操作
				account.setFrozen(frozen+money);
				break;
			default:
				logger.warn("type值非法type={}", type);
				break;
			}
			account.setEt(new Date());
			account.setEb(userId);
			this.updateByPrimaryKeySelective(account, userId);
			ual.setId(UUID.get());
			ual.setAmount(money);
			ual.setBalance(account.getBalance());
			ual.setDescription("消费");
			ual.setTradeno(tradeNo);
			ual.setOrderId(orderId);
			ual.setStatus(1);
			ual.setType(Constants.AccountUseType.CONSUME);
			ual.setUserId(customerId);
			ual.setCt(new Date());
			this.insertAccountLog(ual);
			return result+money;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("账户金额更新异常userId={}", userId);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <更新日志记录><功能具体实现>
	 * @throws Exception
	 * @author yankefei
	 * @date 2015年10月29日 下午2:51:43
	 */
	public void updateAccountLog(SsoUserAccountLog accountLog){
		try {
			this.updateByPrimaryKey(accountLog, accountLog.getId());
		} catch (Exception e) {
			logger.error("更新账户日志记录异常");
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <插入日志记录><功能具体实现>
	 * @param accountLog
	 * @author yankefei
	 * @date 2015年12月4日 下午2:42:54
	 */
	public void insertAccountLog(SsoUserAccountLog accountLog){
		try {
			this.insert(accountLog, accountLog.getId());
		} catch (Exception e) {
			logger.error("插入账户日志记录异常");
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <申请提现><功能具体实现>
	 * @param userId
	 * @param tradeType
	 * @param amount
	 * @author yankefei
	 * @date 2015年11月3日 下午4:19:02
	 */
	/*public Map<String, String> withdrawCash(String userId, int tradeType, String amount, String attrInfo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			UserAccount uAccount = this.selectByPrimaryKey(UserAccount.class, userId);
			//账户可用余额
			Integer balance_old = uAccount.getBalance();
			Integer total = balance_old+uAccount.getFrozen();
			
			BigDecimal decimal = new BigDecimal(100);
			BigDecimal decimal2 = new BigDecimal(amount);
			//提现金额的相反数
			Integer amount2 = decimal2.multiply(decimal).negate().intValue();
			if(amount2 >= 0){
				map.put("success", "3");
				map.put("reason", "提现金额非法");
				return map;
			}else if(-amount2 > balance_old){
				map.put("success", "4");
				map.put("reason", "提现金额不能大于可用余额");
				return map;
			}
			*//**
			 * 增加每天只能提现一次验证 by caiyy 151230
			 *//*
			UserAccountLogCriteria userAccountLogCriteria = new UserAccountLogCriteria();
			userAccountLogCriteria.createCriteria().andUserIdEqualTo(userId).andCtEqualTo(new Date());
			List<UserAccountLog> userAccountLogList = userAccountLogMapper.selectByExample(userAccountLogCriteria);
			if(CollectionUtils.isNotEmpty(userAccountLogList)){
			    map.put("success", "5");
                map.put("reason", "每天只能提现一次");
                return map;
			}
			
			UserAccountLog ual = new  UserAccountLog();
			String ualId = UUID.get();
			ual.setId(ualId);
			ual.setAmount(amount2);
			ual.setBalance(total+amount2);
			ual.setType(2);	//提现
			ual.setUserId(userId);
			ual.setDescription("提现");
			ual.setStatus(0);
			ual.setCt(new Date());
			//1支付宝，2微信
			ual.setTradetype(tradeType);
			switch (tradeType) {
			case 1:
				//支付宝
				ual.setAttrinfo(attrInfo);
				break;
			case 2:
				//微信
				ThreeOpenCriteria threeOpenCriteria = new ThreeOpenCriteria();
				ThreeOpenCriteria.Criteria criteria = threeOpenCriteria.createCriteria();
				criteria.andUserIdEqualTo(userId);
				criteria.andTypeEqualTo(0);
				List<ThreeOpen> list = threeOpenMapper.selectByExample(threeOpenCriteria);
				if(list!=null && list.size()>0){
					ual.setAttrinfo(list.get(0).getOpenid());
				}else{
					map.put("success", "1");
					map.put("reason", "未绑定微信账户");
					return map;
				}
				break;
			default:
				map.put("success", "2");
				map.put("reason", "类型值非法");
				return map;
			}
			this.insertSelective(ual, ualId);
			
			//更新账户余额=余额-提现金额
			uAccount.setBalance(balance_old+amount2);
			uAccount.setEb(userId);
			uAccount.setEt(new Date());
			this.updateByPrimaryKeySelective(uAccount, uAccount.getUserId());
			//提现状态
			UserAccountRecord uar = new UserAccountRecord();
			uar.setId(UUID.get());
			uar.setLogId(ualId);
			uar.setCb(userId);
			//状态：审核中
			uar.setStatus(Constants.withdrawCashStatus.AUDITING);
			uar.setCt(new Date());
			//此处不需要缓存
			userAccountRecordMapper.insert(uar);
			
			map.put("success", "0");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("申请提现处理异常={}", e.getMessage());
			throw new RuntimeException(e);
		}
	}*/
	
	/**
	 * <校验用户是否被冻结><功能具体实现>
	 * @param userId
	 * @return
	 * @author yankefei
	 * @date 2015年11月4日 下午6:23:35
	 */
	public boolean isFreeze(String userId) {
		try {
			//此处不查缓存
			SsoUserAccount ua = userAccountMapper.selectByPrimaryKey(userId);
			if(ua==null)ua=createAccount(userId);
			//0未冻结1冻结
			if(ua.getIsfreeze()==0)return false;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询账户冻结状态异常userId={}", userId);
			throw new RuntimeException(e);
		}
		return true;
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
            SsoUserAccount ua = userAccountMapper.selectByPrimaryKey(userId);
            BigDecimal base = new BigDecimal(100);
            BigDecimal decimal1 = new BigDecimal(ua.getBalance());
            Double balance = decimal1.divide(base, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
            if(balance.compareTo(finalFee) < 0){
                return false;
            }
        } catch (Exception e) {
            logger.error("AccountRepository isAmountPay_exception={}", e);
            throw new RuntimeException(e);
        }
        return true;
    }
	
	/**
	 * <绑定支付宝/微信账号><功能具体实现>
	 * @param userId
	 * @param type
	 * @param attr
	 * @return
	 * @author yankefei
	 * @date 2015年11月18日 下午12:06:07
	 */
//	public String bindAttr(String userId, String type, String attr) {
//		String attrId = null;
//		try {
//			UserAccountAttrsCriteria criteria = new UserAccountAttrsCriteria();
//			UserAccountAttrsCriteria.Criteria cri = criteria.createCriteria();
//			cri.andUserIdEqualTo(userId);
//			cri.andBankNameEqualTo(type);
//			cri.andStatusEqualTo(0);
//			List<UserAccountAttrs> list = userAccountAttrsMapper.selectByExample(criteria);
//			if(list!=null && list.size()>0){
//				logger.warn("用户已绑定{}账户userId={}", type, userId);
//			}else{
//				UserAccountAttrs uaa = new UserAccountAttrs();
//				attrId = UUID.get();
//				uaa.setId(attrId);
//				uaa.setUserId(userId);
//				uaa.setBankNum(attr);
//				uaa.setBankName(type);
//				//0绑定1解绑
//				uaa.setStatus(0);
//				uaa.setCreateTime(new Date());
//				uaa.setUpdateTime(new Date());
//				userAccountAttrsMapper.insert(uaa);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("绑定支付宝/微信账号异常userId={}", userId);
//			throw new RuntimeException(e);
//		}
//		return attrId;
//	}
	
	/**
	 * <账户交易明细><功能具体实现>
	 * @param userId
	 * @param begin
	 * @param size
	 * @return
	 * @author yankefei
	 * @date 2015年11月18日 下午4:55:33
	 */
	public List<UserAccountLogVo> accountLog(String userId, Integer begin, Integer size) {
		List<UserAccountLogVo> list = new ArrayList<UserAccountLogVo>();
		try {
			if(begin==null)begin = 0;
			if(size==null)size = 10;
			SsoUserAccountLogCriteria userAccountLogCriteria = new SsoUserAccountLogCriteria();
			SsoUserAccountLogCriteria.Criteria criteria = userAccountLogCriteria.createCriteria();
			criteria.andUserIdEqualTo(userId);
			criteria.andStatusEqualTo(1);
			userAccountLogCriteria.setMysqlOffset(begin);
			userAccountLogCriteria.setMysqlLength(size);
			userAccountLogCriteria.setOrderByClause("ct desc");
			
			List<SsoUserAccountLog> list2 = userAccountLogMapper.selectByExample(userAccountLogCriteria);
			for(SsoUserAccountLog log:list2){
				UserAccountLogVo vo = new UserAccountLogVo();
				vo.setId(log.getId());
				vo.setDate(DateUtils.formatDate(log.getCt(), "yyyy-MM-dd HH:mm"));
				vo.setType(log.getType());
				vo.setDescribe(log.getDescription());
				
				BigDecimal base = new BigDecimal(100);
				BigDecimal decimal1 = new BigDecimal(log.getAmount());
				BigDecimal decimal2 = new BigDecimal(log.getBalance());
				//操作金额收支类型：0 充值，1 消费，2 提现，3 退款，4 其他
                String amount = decimal1.divide(base, 2, BigDecimal.ROUND_HALF_UP).toString();
                if (log.getType() == 3){
                    amount = "+" + amount;
                }
				vo.setAmount(amount);
				//账户剩余金额
				vo.setBalance(decimal2.divide(base, 2, BigDecimal.ROUND_HALF_UP).toString());
				
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询账户交易明细异常userId={}", userId);
			throw new RuntimeException(e);
		}
		return list;
	}

	/**
	 * <提现记录><功能具体实现>
	 * @param userId
	 * @param begin
	 * @param size
	 * @return
	 * @author yankefei
	 * @date 2015年11月19日 下午6:22:44
	 */
	/*public List<UserAccountLogVo> withdrawCashLog(String userId, Integer begin, Integer size) {
		List<UserAccountLogVo> list = new ArrayList<UserAccountLogVo>();
		try {
			UserAccountLogCriteria userAccountLogCriteria = new UserAccountLogCriteria();
			UserAccountLogCriteria.Criteria criteria = userAccountLogCriteria.createCriteria();
			criteria.andUserIdEqualTo(userId);
			criteria.andTypeEqualTo(Constants.AccountUseType.WITHDRAWCASH);
			if(begin!=null && size!=null){
				userAccountLogCriteria.setMysqlOffset(begin);
				userAccountLogCriteria.setMysqlLength(size);
			}
			userAccountLogCriteria.setOrderByClause("ct desc");
			
			List<UserAccountLog> list2 = userAccountLogMapper.selectByExample(userAccountLogCriteria);
			for(UserAccountLog log:list2){
				UserAccountLogVo vo = new UserAccountLogVo();
				vo.setId(log.getId());
				
				BigDecimal base = new BigDecimal(100);
				BigDecimal decimal1 = new BigDecimal(log.getAmount());
				BigDecimal decimal2 = new BigDecimal(log.getBalance());
				//操作金额
				vo.setAmount(decimal1.divide(base, 2, BigDecimal.ROUND_HALF_UP).toString());
				//账户剩余金额
				vo.setBalance(decimal2.divide(base, 2, BigDecimal.ROUND_HALF_UP).toString());
				
				vo.setDescribe(log.getDescription());
				vo.setType(log.getType());
				vo.setDate(DateUtils.formatDate(log.getCt()));
				
				//设置提现状态
				UserAccountRecordCriteria userAccountRecordCriteria = new UserAccountRecordCriteria();
				UserAccountRecordCriteria.Criteria criteria2 = userAccountRecordCriteria.createCriteria();
				criteria2.andLogIdEqualTo(log.getId());
				criteria2.andStatusNotEqualTo("未通过");
				userAccountRecordCriteria.setOrderByClause("ct desc");
				List<UserAccountRecord> records = userAccountRecordMapper.selectByExample(userAccountRecordCriteria);
				if(records!=null && records.size()>0){
					vo.setState(records.get(0).getStatus());
				}
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("提现记录查询异常userId={}",userId);
			throw new RuntimeException(e);
		}
		return list;
	}*/

	/**
	 * <交易记录详情><功能具体实现>
	 * @param logId
	 * @return
	 * @author yankefei
	 * @date 2015年11月19日 下午6:56:51
	 */
	public UserAccountLogDetailVo accountLogDetail(String logId) {
		UserAccountLogDetailVo vo = null;
		try {
			vo = new UserAccountLogDetailVo();
			List<WithdrawCashLogDetailVo> voList = new ArrayList<WithdrawCashLogDetailVo>();
			SsoUserAccountLog userAccountLog =	this.selectByPrimaryKey(SsoUserAccountLog.class, logId);
			
			//交易金额
			Integer amount = userAccountLog.getAmount();
			BigDecimal base = new BigDecimal(100);
			BigDecimal bigDecimal = new BigDecimal(amount);
			String finalAmount = bigDecimal.divide(base, 2, BigDecimal.ROUND_HALF_UP).toString();
			vo.setAmount(finalAmount);
			
			//账户余额
			Integer balance = userAccountLog.getBalance();
			BigDecimal bigDecimal2 = new BigDecimal(balance);
			String finalBalance = bigDecimal2.divide(base, 2, BigDecimal.ROUND_HALF_UP).toString();
			vo.setBalance(finalBalance);
			
			//交易类型
			int type = userAccountLog.getType();
			vo.setType(type);
			
			//充值支付类型
//			if(userAccountLog.getTradetype().equals(1)){
//				vo.setTradeType("支付宝");
//			}else{
//				vo.setTradeType("微信");
//			}
			//交易时间
			vo.setTime(DateUtils.forDatetime(userAccountLog.getCt()));
			//设置交易描述
			vo.setTypeDesc(userAccountLog.getDescription());
			
			//订单id
			if(userAccountLog.getOrderId()!=null){
				vo.setOrderId(userAccountLog.getOrderId());
				Order order = this.selectByPrimaryKey(Order.class, userAccountLog.getOrderId());

                // 获取订单详情
                OrderItemCriteria orderItemCriteria = new OrderItemCriteria();
                OrderItemCriteria.Criteria criteria = orderItemCriteria.createCriteria();
                criteria.andOrderIdEqualTo(userAccountLog.getOrderId());
                List<OrderItem> orderItems = orderItemMapper.selectByExample(orderItemCriteria);

                // 订单类型
				int orderType = order.getOrdersType();
                vo.setOrderType(orderType);
				switch (orderType) {
				case 0:
					// 课程
                    vo.setClassName(orderItems.get(0).getClassName());
                    // 教练
                    StatiumClassInfo statiumClassInfo = this.selectByPrimaryKey(StatiumClassInfo.class, orderItems.get(0).getClassInfoId());
                    vo.setCoachName(statiumClassInfo.getCoachName());
                    // 道馆名称
                    StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
                    StatiumInfosCriteria.Criteria criteria1 = statiumInfosCriteria.createCriteria();
                    criteria1.andDgIdEqualTo(order.getStatiumId());
                    List<StatiumInfos> statiumInfoses = statiumInfosMapper.selectByExample(statiumInfosCriteria);
                    vo.setStatiumName(statiumInfoses.get(0).getDgName());
					break;
				case 1:
					// 活动
                    vo.setActivityName(orderItems.get(0).getActivityName());
					break;
				default:
					break;
				}
			}
			
			vo.setList(voList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("提现记录详情查询异常userAccountLogId={}", logId);
			throw new RuntimeException(e);
		}
		return vo;
	}
	
	/**
	 * <获取账户总余额><功能具体实现>
	 * @param userId
	 * @return
	 * @author yankefei
	 * @date 2015年11月20日 上午11:22:32
	 */
	public UserAccountVo totalAmount(String userId){
        UserAccountVo userAccountVo = new UserAccountVo();
		Double amount = 0.00d;
        Integer isPwd = 0;
		try {
			SsoUserAccount account = this.selectByPrimaryKey(SsoUserAccount.class, userId);
			if(account == null){
                account = createAccount(userId);
            }
			BigDecimal decimal = new BigDecimal(100);
            Integer frozen = 0;
            if (account.getFrozen() != null){
                frozen = account.getFrozen();
            }
			BigDecimal bigDecimal = new BigDecimal(account.getBalance()+frozen);
			amount = bigDecimal.divide(decimal, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
            userAccountVo.setTotal(String.valueOf(amount));
            if (account.getPasswd() != null) {
                isPwd = 1;
            }
            userAccountVo.setIsPwd(isPwd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("账户总额查询异常{}", e.getMessage());
		}
		return userAccountVo;
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
            SsoUserAccount account = this.selectByPrimaryKey(SsoUserAccount.class, user.getId());
            if (account == null) {
                return new Success("", false, new Reason(Constants.SYSTEM_KEY, "账户不存在，请联系客服"));
            }
            account.setPasswd(Encrypts.encodePassword(password));
            account.setEt(new Date());
            account.setEb(user.getId());
            this.updateByPrimaryKeySelective(account, account.getUserId());
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
     * <修改支付密码><功能具体实现>
     *
     * @create：2015年11月20日 上午11:41:03
     * @author： CYY
     * @param user
     * @param oldPwd 旧密码
     * @param password 新密码
     * @return
     */
    public Success modifyPwd(SsoUser user, String oldPwd, String password) {
        try {
            SsoUserAccount account = this.selectByPrimaryKey(SsoUserAccount.class, user.getId());
            if (account == null) {
                return new Success("", false, new Reason(Constants.SYSTEM_KEY, "账户不存在，请联系客服"));
            }
            Success success = validatePayPwd(user,account.getPasswd(),oldPwd);
            if(success.isSuccess()){
                account.setPasswd(Encrypts.encodePassword(password));
                this.updateByPrimaryKeySelective(account, account.getUserId());
                return new Success("", true);
            }else{
                return success;
            }
        } catch (Exception e) {
            logger.error("AccountRepository modifyPayPwd={}", e);
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
            SsoUserAccount account = this.selectByPrimaryKey(SsoUserAccount.class, user.getId());
            if (account == null) {
                return new Success("", false, new Reason(Constants.SYSTEM_KEY, "账户不存在，请联系客服"));
            }
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
            logger.error("AccountRepository isPayPwd={}", e);
            throw new RuntimeException(e);
        }
    }
    
    /**
	 * <获取账户总余额><功能具体实现>
	 * @param userId
	 * @return
	 * @author yankefei
	 * @date 2015年11月20日 上午11:22:32
	 */
	public BigDecimal totalAmount_(String userId){
		BigDecimal amount = new BigDecimal(0.00d);
		try {
			SsoUserAccount account = this.selectByPrimaryKey(SsoUserAccount.class, userId);
			if(account == null){
			    account = createAccount(userId);
			}

			BigDecimal decimal = new BigDecimal(100);
			BigDecimal bigDecimal = new BigDecimal(account.getBalance()+account.getFrozen());
			amount = bigDecimal.divide(decimal, 2, BigDecimal.ROUND_HALF_UP);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("账户总额查询异常userId={}", userId);
		}
		return amount;
	}
}
