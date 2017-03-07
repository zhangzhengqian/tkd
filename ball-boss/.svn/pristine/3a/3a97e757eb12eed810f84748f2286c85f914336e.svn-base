package com.lc.zy.ball.boss.framework.accounts.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import com.lc.zy.ball.domain.oa.mapper.*;
import com.lc.zy.ball.domain.oa.po.*;
import com.lc.zy.common.util.FreeMarkerUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.accounts.vo.UserAccountDetailVo;
import com.lc.zy.ball.boss.framework.accounts.vo.UserAccountLogVo;
import com.lc.zy.ball.boss.framework.accounts.vo.UserAccountVo;
import com.lc.zy.ball.boss.framework.ssouser.vo.AccountVo;
import com.lc.zy.ball.boss.framework.ssouser.vo.UserIntegralVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.UUID;

import javax.annotation.Resource;

@Component
@Transactional
public class AccountService extends AbstractCacheService {

    private static Logger logger = LoggerFactory.getLogger(AccountService.class);
    
    private static BigDecimal baseDecimal = new BigDecimal(100);
    
    @Autowired
    private UserAccountMapper userAccountMapper;
    
    @Autowired
    private UserAccountLogMapper userAccountLogMapper;
    
    @Autowired
    private UserAccountRecordMapper userAccountRecordMapper;
    
    @Autowired
    private ThreeOpenMapper threeOpenMapper;
    
    @Autowired
    private PayLogMapper payLogMapper;
    
    @Autowired
    private SsoUserMapper ssoUserMapper;
    
    @Autowired
    private StatiumCommentReplyMapper statiumCommentReplyMapper;
    
    @Autowired
    private UserIntegralLogMapper userIntegralLogMapper;

	@Autowired
	private SsoUserBonusAccountLogMapper ssoUserBonusAccountLogMapper;

	@Autowired
	private SsoUserBonusAccountMapper ssoUserBonusAccountMapper;

	@Resource(name="oaJdbcTemplate")
	JdbcTemplate jdbcTemplate;
    
    /**
     * <分页查询账户列表><功能具体实现>
     * @param searchParams
     * @param page
     * @param size
     * @param isPage
     * @param isHasCount
     * @return
     * @throws Exception
     * @author yankefei
     * @date 2015年11月26日 下午5:55:18
     */
    public Page<UserAccountVo> find(Map<String, Object> searchParams, int page, int size,
    		boolean isPage, boolean isHasCount) throws Exception {
        PageRequest pageable = new PageRequest(page, size);
		List<UserAccountVo> vos = new ArrayList<UserAccountVo>();
		int total = 0;
		Map<String, SearchFilter> filters=null;
		try {
            for(String key : searchParams.keySet()){
                if(StringUtils.isEmpty(searchParams.get(key).toString())){
                    searchParams.remove(key);
                }
            }
		    if(!searchParams.isEmpty()){
		        SsoUserCriteria ssoUserCriteria = new SsoUserCriteria();
	            // 查询条件
	            filters = SearchFilter.parse(searchParams);
	            // 分页
	            if (isPage) {
	                ssoUserCriteria.setMysqlLength(pageable.getPageSize());
	                ssoUserCriteria.setMysqlOffset(pageable.getOffset());
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
	            List<SsoUser> list = new ArrayList<SsoUser>();
	            // list
	            list = ssoUserMapper.selectByExample(ssoUserCriteria);
	            if(CollectionUtils.isNotEmpty(list)){
	                StringBuilder sb = new StringBuilder();
	                for(SsoUser u : list){
	                    sb.append(u.getId());
	                    sb.append(";");
	                }
	                String userId = sb.toString();
	                userId = userId.substring(0,userId.length() - 1);
	                searchParams.put("IN_userId", userId);
	                
	            }
		    }
		    UserAccountCriteria cc = new UserAccountCriteria();
            if (isPage) {
                cc.setMysqlOffset(pageable.getOffset());
                cc.setMysqlLength(pageable.getPageSize());
            }
            
            UserAccountCriteria.Criteria cri1 = cc.createCriteria();
            if (searchParams != null) {
                filters = SearchFilter.parse(searchParams);
                Criterias.bySearchFilter(cri1, filters.values());
            }
            total = userAccountMapper.countByExample(cc);
            List<UserAccount> list1 = userAccountMapper.selectByExample(cc);
            if (list1 != null && list1.size()>0) {
                for (UserAccount account: list1) {
                    UserAccountVo vo = new UserAccountVo();
                    SsoUser user = this.selectByPrimaryKey(SsoUser.class, account.getUserId());
                    if(user==null)continue;
                    
                    Integer frozen = account.getFrozen();
                    Integer balance = account.getBalance();
                    Integer accountTotal = frozen+balance;
                    BigDecimal bigDecimal = new BigDecimal(accountTotal);
                    vo.setTotal(bigDecimal.divide(baseDecimal, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    vo.setUserId(account.getUserId());
                    vo.setQiuyouNo(user.getQiuyouno());
                    vo.setUserType(user.getProperty());
                    vo.setPhone(user.getPhone());
                    vo.setNickName(user.getNickName());
                    vo.setStatus(account.getIsfreeze()==0?"正常":"冻结");
                    vos.add(vo);
                }
            }
		} catch (Exception e) {
			logger.error("查询账户列表异常");
			throw new RuntimeException(e);
		}
        return new PageImpl<>(vos, pageable, total);
    }

	/**
	 * <奖金账户列表><>
	 * @param searchParams
	 * @param page
	 * @param size
	 * @param isPage
	 * @param isHasCount
	 * @return
     * @throws Exception
     */
	public Map<String,Object> findBonus(Map<String, Object> searchParams, int page, int size,
									boolean isPage, boolean isHasCount) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,Object> res = new HashMap<String, Object>();
		Map<String, Object> root = new HashMap<String, Object>();

		PageRequest pageable = new PageRequest(page, size);
		List<UserAccountVo> vos = new ArrayList<UserAccountVo>();
		int total = 0;
		Map<String, SearchFilter> filters=null;
		String searchUserId = "";
		try {
			if(!searchParams.isEmpty() &&  searchParams.get("LIKE_phone") != ""){
				SsoUserCriteria ssoUserCriteria = new SsoUserCriteria();
				// 查询条件

				SsoUserCriteria.Criteria cri = ssoUserCriteria.createCriteria();
				// 用户状态(正常:1、删除:2、冻结:3)
				cri.andStateNotEqualTo(2);
				cri.andPhoneLike(String.valueOf(searchParams.get("LIKE_phone")));
				// 排序
				ssoUserCriteria.setOrderByClause("update_time desc");
				List<SsoUser> list = new ArrayList<SsoUser>();
				// list
				list = ssoUserMapper.selectByExample(ssoUserCriteria);
				if(CollectionUtils.isNotEmpty(list)){
					StringBuilder sb = new StringBuilder();
					for(SsoUser u : list){
						sb.append(u.getId());
						sb.append(";");
					}
					String userId = sb.toString();
					userId = userId.substring(0,userId.length() - 1);
					searchParams.put("IN_userId", userId);
					searchUserId = userId;

				}
			}
			searchParams.remove("LIKE_phone");
			SsoUserBonusAccountCriteria cc = new SsoUserBonusAccountCriteria();
			SsoUserBonusAccountCriteria.Criteria cri1 = cc.createCriteria();
			if(searchParams.get("GTE_ct") != null &&  !"".equals(String.valueOf(searchParams.get("GTE_ct")).trim())){
					root.put("GTE_ct",searchParams.get("GTE_ct"));
				    logger.debug("GTE_ct={}",String.valueOf(searchParams.get("GTE_ct")));
					searchParams.put("GTE_ct",DateUtil.parse(String.valueOf(searchParams.get("GTE_ct")),"yyyy-MM-dd HH:mm:ss",new Date()));
			}else{
				searchParams.remove("GTE_ct");
			}
			if(searchParams.get("LTE_ct") != null && !"".equals(String.valueOf(searchParams.get("LTE_ct")).trim() )){
					root.put("LTE_ct",searchParams.get("LTE_ct"));
					searchParams.put("LTE_ct",DateUtil.parse(String.valueOf(searchParams.get("LTE_ct")),"yyyy-MM-dd HH:mm:ss",new Date()));
			}else{
				searchParams.remove("LTE_ct");
			}
			filters = SearchFilter.parse(searchParams);
			logger.debug("searchParams==="+searchParams.toString());
			Criterias.bySearchFilter(cri1, filters.values());
			if (isPage) {
				cc.setMysqlOffset(pageable.getOffset());
				cc.setMysqlLength(pageable.getPageSize());
			}
			total = ssoUserBonusAccountMapper.countByExample(cc);
			if(StringUtils.isNotEmpty(searchUserId)){
				root.put("userId",searchUserId);
			}
			String sql = FreeMarkerUtils.format("/template/bonus_list_sum_money.ftl", root);
			logger.debug("sql={}",sql.toString());
			List<Map<String,Object>> mapList = jdbcTemplate.queryForList(sql);
			if(CollectionUtils.isNotEmpty(mapList)){
				for (Map<String,Object> map: mapList) {
					res.put(String.valueOf(map.get("type")),map.get("amount"));
				}
			}
			List<SsoUserBonusAccount> list1 = ssoUserBonusAccountMapper.selectByExample(cc);
			if (list1 != null && list1.size()>0) {
				for (SsoUserBonusAccount account: list1) {
					UserAccountVo vo = new UserAccountVo();
					SsoUser user = this.selectByPrimaryKey(SsoUser.class, account.getUserId());
					if(user==null)continue;
					Integer frozen = account.getFrozen();
					Integer balance = account.getBalance();
					Integer accountTotal = frozen+balance;
					BigDecimal bigDecimal = new BigDecimal(accountTotal);
					vo.setTotal(bigDecimal.divide(baseDecimal, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
					vo.setUserId(account.getUserId());
					vo.setQiuyouNo(user.getQiuyouno());
					vo.setUserType(user.getProperty());
					vo.setPhone(user.getPhone());
					vo.setNickName(user.getNickName());
					vo.setStatus(account.getIsfreeze()==0?"正常":"冻结");
					vos.add(vo);
				}
			}
			res.put("data", new PageImpl<>(vos, pageable, total));
		} catch (Exception e) {
			logger.error("查询奖金账户列表异常");
			throw new RuntimeException(e);
		}
		return res;
	}

	/**
	 * <奖金流水><>
	 * @param userId
	 * @param begin
	 * @param size
     * @return
     */
	public Page<SsoUserBonusAccountLog> bonusAccountLogs(String userId, int begin, int size) {
		PageRequest pageable = new PageRequest(begin, size);
		List<SsoUserBonusAccountLog> list = new ArrayList<SsoUserBonusAccountLog>();
		int total = 0;
		try {
			SsoUserBonusAccountLogCriteria bonusAccountLogCriteria = new SsoUserBonusAccountLogCriteria();
			SsoUserBonusAccountLogCriteria.Criteria criteria = bonusAccountLogCriteria.createCriteria();
			criteria.andUserIdEqualTo(userId);
			bonusAccountLogCriteria.setMysqlOffset(begin);
			bonusAccountLogCriteria.setMysqlLength(size);
			total = ssoUserBonusAccountLogMapper.countByExample(bonusAccountLogCriteria);
			list = ssoUserBonusAccountLogMapper.selectByExample(bonusAccountLogCriteria);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new PageImpl<SsoUserBonusAccountLog>(list, pageable, total);
	}

    /**
     * <冻结账户><功能具体实现>
     * @param userId
     * @return
     * @author yankefei
     * @date 2015年11月27日 下午4:15:27
     */
	public boolean freeze(String userId) {
		try {
			UserAccount account = this.selectByPrimaryKey(UserAccount.class, userId);
			if(account!=null){
				account.setIsfreeze(1);
				this.updateByPrimaryKey(account, userId);
				return true;
			}else{
				logger.error("冻结账户未找到userId={}", userId);
				return false;
			}
		} catch (Exception e) {
			logger.error("冻结账户异常userId={}", userId);
			throw new RuntimeException(e);
		}
	}

    /**
     * <解冻账户><功能具体实现>
     * @param userId
     * @return
     * @author yankefei
     * @date 2015年11月27日 下午4:53:20
     */
	public boolean unfreeze(String userId) {
		try {
			UserAccount account = this.selectByPrimaryKey(UserAccount.class, userId);
			if(account!=null){
				account.setIsfreeze(0);
				this.updateByPrimaryKey(account, userId);
				return true;
			}else{
				logger.error("解冻账户未找到userId={}", userId);
				return false;
			}
		} catch (Exception e) {
			logger.error("解冻账户异常userId={}", userId);
			throw new RuntimeException(e);
		}
	}
    
	/**
	 * <提现列表><功能具体实现>
	 * @param searchParams
	 * @param page
	 * @param size
	 * @param isPage
	 * @param isHasCount
	 * @return
	 * @author yankefei
	 * @date 2015年11月30日 下午12:04:40
	 */
	public Page<UserAccountLogVo> findWithdrawCash(Map<String, Object> searchParams, int page, int size,
    		boolean isPage, boolean isHasCount) {
        PageRequest pageable = new PageRequest(page, size);
		List<UserAccountLogVo> vos = new ArrayList<UserAccountLogVo>();
		int total = 0;
		Map<String, SearchFilter> filters = null;
		try {
		    for(String key : searchParams.keySet()){
                if(StringUtils.isEmpty(searchParams.get(key).toString())){
                    searchParams.remove(key);
                }
            }
            if(!searchParams.isEmpty()){
                SsoUserCriteria ssoUserCriteria = new SsoUserCriteria();
                // 查询条件
                filters = SearchFilter.parse(searchParams);
                // 分页
                if (isPage) {
                    ssoUserCriteria.setMysqlLength(pageable.getPageSize());
                    ssoUserCriteria.setMysqlOffset(pageable.getOffset());
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
                List<SsoUser> list = new ArrayList<SsoUser>();
                // list
                list = ssoUserMapper.selectByExample(ssoUserCriteria);
                if(CollectionUtils.isNotEmpty(list)){
                    StringBuilder sb = new StringBuilder();
                    for(SsoUser u : list){
                        sb.append(u.getId());
                        sb.append(";");
                    }
                    String userId = sb.toString();
                    userId = userId.substring(0,userId.length() - 1);
                    searchParams.put("IN_userId", userId);
                }
            }
		    
			UserAccountLogCriteria cc = new UserAccountLogCriteria();
			UserAccountLogCriteria.Criteria cri = cc.createCriteria();
			//提现类型
			cri.andTypeEqualTo(2);
			cc.setOrderByClause("ct desc");
			if (isPage) {
			    cc.setMysqlOffset(pageable.getOffset());
			    cc.setMysqlLength(pageable.getPageSize());
			}
			if (searchParams != null) {
			    filters = SearchFilter.parse(searchParams);
			    Criterias.bySearchFilter(cri, filters.values());
			}
			total = userAccountLogMapper.countByExample(cc);
			List<UserAccountLog> list = userAccountLogMapper.selectByExample(cc);
			if (list != null && list.size()>0) {
			    for (UserAccountLog log: list) {
			        UserAccountLogVo vo = new UserAccountLogVo();
			        SsoUser user = this.selectByPrimaryKey(SsoUser.class, log.getUserId());
			        if(user==null)continue;
			        
			        vo.setLogId(log.getId());
			        vo.setUserId(log.getUserId());
			        vo.setQiuyouNo(user.getQiuyouno());
			        vo.setPhone(user.getPhone());
			        vo.setNickName(user.getNickName());
			        vo.setTime(DateUtils.forDatetime(log.getCt()));
			        vo.setTradetype(log.getTradetype());
			        vo.setAttrInfo(log.getAttrinfo());
			        
			        //设置提现金额
			        Integer amount = log.getAmount();
			    	BigDecimal bigDecimal = new BigDecimal(amount);
			    	double amount_final = bigDecimal.divide(baseDecimal, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
			    	vo.setAmount(amount_final);
			    	
			    	//设置提现状态
			    	UserAccountRecordCriteria userAccountRecordCriteria = new UserAccountRecordCriteria();
			    	UserAccountRecordCriteria.Criteria criteria = userAccountRecordCriteria.createCriteria();
			    	criteria.andLogIdEqualTo(log.getId());
			    	userAccountRecordCriteria.setOrderByClause("ct desc");
			    	List<UserAccountRecord> records = userAccountRecordMapper.selectByExample(userAccountRecordCriteria);
			    	if(records!=null && records.size()>0){
			    		vo.setStatus(records.get(0).getStatus());
			    	}
			    	vos.add(vo);
			    }
			}
		} catch (Exception e) {
			logger.error("查询提现记录列表异常");
			throw new RuntimeException(e);
		}
        return new PageImpl<>(vos, pageable, total);
	}

	/**
	 * <提现审核><功能具体实现>
	 * @param action
	 * @param logId
	 * @return
	 * @author yankefei
	 * @date 2015年12月1日 下午3:24:58
	 */
	public Map<String, String> audit(Integer action, String logId,String reason) {
		Map<String, String> map = new HashMap<String, String>();
		//action为0代表审核通过，1代表审核不通过
		//status:审核中、处理中、未通过、已完成
		try {
			if(action!=null){
				UserAccountRecordCriteria userAccountRecordCriteria = new UserAccountRecordCriteria();
				UserAccountRecordCriteria.Criteria criteria = userAccountRecordCriteria.createCriteria();
				criteria.andLogIdEqualTo(logId);
				userAccountRecordCriteria.setOrderByClause("ct desc");
				List<UserAccountRecord> list = userAccountRecordMapper.selectByExample(userAccountRecordCriteria);
				if(list!=null && list.size()>0){
					UserAccountRecord uar = list.get(0);
					if(action.equals(0)){
						if("审核中".equals(uar.getStatus()) || "未通过".equals(uar.getStatus())){
							UserAccountRecord uar2 = new UserAccountRecord();
							uar2.setId(UUID.get());
							uar2.setLogId(logId);
							uar2.setStatus("处理中");
							uar2.setCb(SessionUtil.currentUserId());
							uar2.setCt(new Date());
							userAccountRecordMapper.insert(uar2);
							map.put("result", "0");
						}else{
							map.put("result", "3");
							map.put("reason", "审核失败");
							logger.warn("审核失败status={}", uar.getStatus());
						}
					}else{
						if("审核中".equals(uar.getStatus())){
							UserAccountRecord uar2 = new UserAccountRecord();
							uar2.setId(UUID.get());
							uar2.setLogId(logId);
							uar2.setStatus("未通过");
							uar2.setCb(SessionUtil.currentUserId());
							uar2.setCt(new Date());
							uar2.setReason(reason);
							userAccountRecordMapper.insert(uar2);
							map.put("result", "0");
						}else{
							map.put("result", "3");
							map.put("reason", "审核失败");
							logger.warn("审核失败status={}", uar.getStatus());
						}
					}
				}else{
					map.put("result", "2");
					map.put("reason", "未查询到提现状态更新日志!");
					logger.warn("未查询到提现状态更新日志logId={}",logId);
				}
			}else{
				map.put("result", "1");
				map.put("reason", "action不能为空!");
				logger.warn("action为空");
			}
		} catch (Exception e) {
			logger.error("提现审核异常");
			throw new RuntimeException(e);
		}
		return map;
	}
	
	/**
	 * <财务提现处理列表><功能具体实现>
	 * @param searchParams
	 * @param page
	 * @param size
	 * @param isPage
	 * @param isHasCount
	 * @return
	 * @author yankefei
	 * @date 2015年12月2日 上午10:56:25
	 */
	public Page<UserAccountLogVo> findWithdrawCashFinance(Map<String, Object> searchParams, int page, int size,
    		boolean isPage, boolean isHasCount) {
        PageRequest pageable = new PageRequest(page, size);
		List<UserAccountLogVo> vos = new ArrayList<UserAccountLogVo>();
		int total = 0;
		Map<String, SearchFilter> filters = null;
		try {
		    for(String key : searchParams.keySet()){
                if(StringUtils.isEmpty(searchParams.get(key).toString())){
                    searchParams.remove(key);
                }
            }
            if(!searchParams.isEmpty()){
                SsoUserCriteria ssoUserCriteria = new SsoUserCriteria();
                // 查询条件
                filters = SearchFilter.parse(searchParams);
                // 分页
                if (isPage) {
                    ssoUserCriteria.setMysqlLength(pageable.getPageSize());
                    ssoUserCriteria.setMysqlOffset(pageable.getOffset());
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
                List<SsoUser> list = new ArrayList<SsoUser>();
                // list
                list = ssoUserMapper.selectByExample(ssoUserCriteria);
                if(CollectionUtils.isNotEmpty(list)){
                    StringBuilder sb = new StringBuilder();
                    for(SsoUser u : list){
                        sb.append(u.getId());
                        sb.append(";");
                    }
                    String userId = sb.toString();
                    userId = userId.substring(0,userId.length() - 1);
                    searchParams.put("IN_userId", userId);
                }
            }
            
			UserAccountLogCriteria cc = new UserAccountLogCriteria();
			UserAccountLogCriteria.Criteria cri = cc.createCriteria();
			//提现类型
			cri.andTypeEqualTo(2);
			//这个total数据可能不准，因为下面要根据status过滤一部分
			cc.setOrderByClause("ct desc");
			if (isPage) {
			    cc.setMysqlOffset(pageable.getOffset());
			    cc.setMysqlLength(pageable.getPageSize());
			}
			if (searchParams != null) {
			    filters = SearchFilter.parse(searchParams);
			    Criterias.bySearchFilter(cri, filters.values());
			}
			total = userAccountLogMapper.countByExample(cc);
			List<UserAccountLog> list = userAccountLogMapper.selectByExample(cc);
			if (list != null && list.size()>0) {
			    for (UserAccountLog log: list) {
			        UserAccountLogVo vo = new UserAccountLogVo();
			        SsoUser user = this.selectByPrimaryKey(SsoUser.class, log.getUserId());
			        
			        vo.setLogId(log.getId());
			        vo.setUserId(log.getUserId());
			        vo.setQiuyouNo(user==null?"":user.getQiuyouno());
			        vo.setPhone(user==null?"":user.getPhone());
			        vo.setNickName(user==null?"":user.getNickName());
			        vo.setTime(DateUtils.forDatetime(log.getCt()));
			        vo.setTradetype(log.getTradetype());
			        //设置提现账号
			        if(log.getAttrinfo()!=null && !"".equals(log.getAttrinfo())){
			        	//支付宝账号
			        	vo.setAttrInfo(log.getAttrinfo());
			        }else{
			        	//微信账号
			        	ThreeOpenCriteria threeOpenCriteria = new ThreeOpenCriteria();
			        	ThreeOpenCriteria.Criteria criteria = threeOpenCriteria.createCriteria();
			        	criteria.andUserIdEqualTo(log.getUserId());
			        	List<ThreeOpen> list2 = threeOpenMapper.selectByExample(threeOpenCriteria);
			        	if(list2!=null && list2.size()>0){
			        		vo.setAttrInfo(list2.get(0).getOpenid());
			        	}else{
			        		vo.setAttrInfo("");
			        	}
			        }
			        
			        //设置提现金额
			        Integer amount = log.getAmount();
			    	BigDecimal bigDecimal = new BigDecimal(amount);
			    	double amount_final = bigDecimal.divide(baseDecimal, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
			    	vo.setAmount(amount_final);
			    	
			    	//设置提现状态
			    	UserAccountRecordCriteria userAccountRecordCriteria = new UserAccountRecordCriteria();
			    	UserAccountRecordCriteria.Criteria criteria = userAccountRecordCriteria.createCriteria();
			    	criteria.andLogIdEqualTo(log.getId());
			    	userAccountRecordCriteria.setOrderByClause("ct desc");
			    	List<UserAccountRecord> records = userAccountRecordMapper.selectByExample(userAccountRecordCriteria);
			    	if(records!=null && records.size()>0){
			    		vo.setStatus(records.get(0).getStatus());
			    	}
			    	vos.add(vo);
			    }
			}
			total = userAccountLogMapper.countByExample(cc);
		} catch (Exception e) {
			logger.error("查询需要确认转账的提现记录异常");
			throw new RuntimeException(e);
		}
        return new PageImpl<>(vos, pageable, total);
	}

	/**
	 * <提现财务处理><功能具体实现>
	 * @param logId
	 * @return
	 * @author yankefei
	 * @date 2015年12月7日 上午10:09:52
	 */
	public Map<String, String> process(String logId) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			UserAccountRecordCriteria userAccountRecordCriteria = new UserAccountRecordCriteria();
			UserAccountRecordCriteria.Criteria criteria = userAccountRecordCriteria.createCriteria();
			criteria.andLogIdEqualTo(logId);
			userAccountRecordCriteria.setOrderByClause("ct desc");
			List<UserAccountRecord> list = userAccountRecordMapper.selectByExample(userAccountRecordCriteria);
			if(list!=null && list.size()>0){
				UserAccountRecord record = list.get(0);
				if("处理中".equals(record.getStatus())){
					UserAccountRecord uar2 = new UserAccountRecord();
					uar2.setId(UUID.get());
					uar2.setLogId(logId);
					uar2.setStatus("已完成");
					uar2.setCb(SessionUtil.currentUserId());
					uar2.setCt(new Date());
					userAccountRecordMapper.insert(uar2);
					
					UserAccountLog ual = userAccountLogMapper.selectByPrimaryKey(logId);
					ual.setStatus(1);
					userAccountLogMapper.updateByPrimaryKey(ual);
					String userId = ual.getUserId();
					
					//状态更新之后，记录支付日志
					PayLog log = new PayLog();
					log.setId(UUID.get());
					log.setCreateTime(new Date());
					log.setUserId(userId);
					log.setFee(ual.getAmount());
					log.setFinalFee(ual.getAmount());
					log.setPayType(ual.getTradetype());
					log.setStatus(Constants.PayStatus.WITH_DRAW_SUCCESS);
					//TODO 表中字段不为空，故先设置为空字符串
					log.setTradeNo("");
					log.setOrderId("");
					payLogMapper.insert(log);
					
					map.put("result", "0");
				}else{
					map.put("result", "2");
					map.put("reason", "提现状态异常");
					logger.warn("提现状态异常status={}", record.getStatus());
				}
			}else{
				map.put("result", "1");
				map.put("reason", "未查询到提现状态更新日志!");
				logger.warn("未查询到提现状态更新日志logId={}", logId);
			}
		} catch (Exception e) {
			logger.error("提现财务处理异常");
			throw new RuntimeException(e);
		}
		return map;
	}

	/**
	 * <账户详情查看><功能具体实现>
	 * @param userId
	 * @return
	 * @author yankefei
	 * @date 2015年12月7日 下午3:29:36
	 */
	public UserAccountDetailVo accountDetail(String userId) {
		UserAccountDetailVo vo = new UserAccountDetailVo();
		try {
			SsoUser user = this.selectByPrimaryKey(SsoUser.class, userId);
			UserAccount ua = this.selectByPrimaryKey(UserAccount.class, userId);
			Integer balance = ua.getBalance();
			Integer frozen = ua.getFrozen();
			Integer total = balance+frozen;
			BigDecimal bigDecimal = new BigDecimal(total);
			BigDecimal bigDecimal2 = new BigDecimal(frozen);
			vo.setUserId(user.getId());
			vo.setQiuyouNo(user.getQiuyouno());
			vo.setNickName(user.getNickName());
			vo.setPhone(user.getPhone());
			vo.setStatus(ua.getIsfreeze().equals(1)?"冻结":"正常");
			vo.setFrozen(bigDecimal2.divide(baseDecimal, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
			vo.setTotal(bigDecimal.divide(baseDecimal, 2, BigDecimal.ROUND_HALF_UP).doubleValue());

			SsoUserBonusAccount bua = this.selectByPrimaryKey(SsoUserBonusAccount.class,userId);
			if(bua != null){
				Integer bonusBalance = bua.getBalance();
				Integer bonusFrozen = bua.getFrozen();
				BigDecimal bigDecimal3 = new BigDecimal(bonusBalance + bonusFrozen);
				BigDecimal bigDecimal4 = new BigDecimal(bonusFrozen);
				vo.setBonus(bigDecimal3.divide(baseDecimal, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
				vo.setFrozenBonus(bigDecimal4.divide(baseDecimal, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
				vo.setFrozen(new BigDecimal(frozen + bonusFrozen).divide(baseDecimal, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
				vo.setTotal(new BigDecimal(total + bonusBalance + bonusFrozen).divide(baseDecimal, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return vo;
	}

	/**
	 * <账户消费记录><功能具体实现>
	 * @param userId
	 * @param begin
	 * @param size
	 * @return
	 * @author yankefei
	 * @date 2015年12月9日 下午12:10:05
	 */
	public Page<UserAccountLog> accountLogs(String userId, int begin, int size) {
		PageRequest pageable = new PageRequest(begin, size);
		List<UserAccountLog> list = new ArrayList<UserAccountLog>();
		int total=0;
		try {
			UserAccountLogCriteria userAccountLogCriteria = new UserAccountLogCriteria();
			UserAccountLogCriteria.Criteria criteria = userAccountLogCriteria.createCriteria();
			criteria.andUserIdEqualTo(userId);
			total = userAccountLogMapper.countByExample(userAccountLogCriteria);
			userAccountLogCriteria.setMysqlOffset(begin);
			userAccountLogCriteria.setMysqlLength(size);
			list = userAccountLogMapper.selectByExample(userAccountLogCriteria);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new PageImpl<UserAccountLog>(list, pageable, total);
	}
	
	public Double totalAmountQiuyou(String userId){
		try {
			QiuyouCardUser account = this.selectByPrimaryKey(QiuyouCardUser.class, userId);
			if(account==null)return 0.00d;
			BigDecimal decimal = new BigDecimal(100);
			BigDecimal bigDecimal = new BigDecimal(account.getBalance());
			return bigDecimal.divide(decimal, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
		} catch (Exception e) {
			logger.error("球友卡账户总额查询异常userId={},{}", userId,e);
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
	public Double totalAmountAccount(String userId){
		Double amount = 0.00d;
		try {
			UserAccount account = this.selectByPrimaryKey(UserAccount.class, userId);
			if(account==null)return amount;
			BigDecimal decimal = new BigDecimal(100);
			BigDecimal bigDecimal = new BigDecimal(account.getBalance()+account.getFrozen());
			amount = bigDecimal.divide(decimal, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("账户总额查询异常userId={}", userId);
		}
		return amount;
	}

	public Double totalAmountBonus(String userId){
		Double amount = 0.00d;
		try {
			SsoUserBonusAccount account = this.selectByPrimaryKey(SsoUserBonusAccount.class, userId);
			if(account==null)return amount;
			BigDecimal decimal = new BigDecimal(100);
			BigDecimal bigDecimal = new BigDecimal(account.getBalance()+account.getFrozen());
			amount = bigDecimal.divide(decimal, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("奖金账户总额查询异常userId={}", userId);
		}
		return amount;
	}
	
	/**
     * 
     * <获取用户积分><功能具体实现>
     *
     * @create：2015年11月24日 下午7:21:03
     * @author： CYY
     * @param userId
     */
    public Integer getUserIntegral(String userId) {
        try {
            UserIntegral userIntegral = this.selectByPrimaryKey(UserIntegral.class, userId);
            Date now = new Date();
            if (userIntegral == null) { // 开通积分账户
                userIntegral = new UserIntegral();
                userIntegral.setUserId(userId);
                userIntegral.setCt(now);
                userIntegral.setEt(now);
                userIntegral.setIntegral(0);
                this.insertSelective(userIntegral, userId);
            }
            return userIntegral.getIntegral();
        } catch (Exception e) {
            logger.error("IntegralRepository getUserIntegral: " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 
     * <用户积分日志列表><功能具体实现>
     *
     * @create：2015年11月24日 下午2:50:11
     * @author： CYY
     * @param userId
     * @param date
     * @param begin
     * @param size
     * @return
     */
    public List<UserIntegralVo> getUserIntegralLog(String userId, String date, int begin, int size) {
        try {
            UserIntegralLogCriteria userIntegralLogCriteria = new UserIntegralLogCriteria();
            UserIntegralLogCriteria.Criteria cri = userIntegralLogCriteria.createCriteria();
            cri.andUserIdEqualTo(userId);
            /*userIntegralLogCriteria.setMysqlLength(size);
            userIntegralLogCriteria.setMysqlOffset(begin);*/
            userIntegralLogCriteria.setOrderByClause(" ct desc ");
            if (StringUtils.isNotEmpty(date)) {
                Date startDate = DateUtils.getDate(date + "01 00:00:00", "yyyy-MM-dd HH:mm:ss");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startDate);
                int maxMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                Date endDate = DateUtils.getDate(date + maxMonth + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
                cri.andCtBetween(startDate, endDate);
            }
            List<UserIntegralLog> userIntegralLogList = userIntegralLogMapper.selectByExample(userIntegralLogCriteria);
            UserIntegralVo vo = null;
            List<UserIntegralVo> userIntegralList = new ArrayList<UserIntegralVo>();
            for (UserIntegralLog log : userIntegralLogList) {
                vo = new UserIntegralVo();
                vo.setCt(DateUtils.formatDate(log.getCt()));
                vo.setDescription(log.getDescription());
                vo.setId(log.getId());
                vo.setIntegral(log.getIntegral());
                vo.setIntegralType(log.getIntegralType());
                userIntegralList.add(vo);
            }
            return userIntegralList;
        } catch (Exception e) {
            logger.error("IntegralRepository getUserIntegralLog: " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 查看账户详情
     * @param userId
     * @return
     */
	public AccountVo viewAccount(String userId,String start,String end,String type){
		AccountVo account = new AccountVo();
		try {
			UserAccount ua = this.selectByPrimaryKey(UserAccount.class, userId);
			SsoUser user = this.selectByPrimaryKey(SsoUser.class, userId);
			if(ua==null){
				return account;
			}
			Integer balance = ua.getBalance();
			Integer frozen = ua.getFrozen();
			//奖金
			Integer bonus = 0;
			SsoUserBonusAccount uba = this.selectByPrimaryKey(SsoUserBonusAccount.class,userId);
			if(uba != null){
				bonus = uba.getBalance();
			}
			Integer total = balance+frozen +bonus;
			BigDecimal bigDecimal = new BigDecimal(total);
			BigDecimal bigDecimal2 = new BigDecimal(frozen);
			BigDecimal bigDecimal3 = new BigDecimal(bonus);
			account.setUserId(userId);
			account.setQiuyouNo(user.getQiuyouno());
			account.setName(user.getNickName());
			account.setPhone(user.getPhone());
			account.setState(ua.getIsfreeze().equals(1)?"冻结":"正常");
			account.setFreez(bigDecimal2.divide(baseDecimal, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
			account.setTotal(bigDecimal.divide(baseDecimal, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
			account.setBonus(bigDecimal3.divide(baseDecimal,2,BigDecimal.ROUND_CEILING).doubleValue());

			//账户流水日子
			UserAccountLogCriteria accountLogCriteria = new UserAccountLogCriteria();
			UserAccountLogCriteria.Criteria accountLogCri = accountLogCriteria.createCriteria();
//			//奖金进出流水
//			SsoUserBonusAccountLogCriteria bonusAccountLogCriteria = new SsoUserBonusAccountLogCriteria();
//			SsoUserBonusAccountLogCriteria.Criteria bonusLogCri = bonusAccountLogCriteria.createCriteria();
			accountLogCri.andUserIdEqualTo(userId);
//			bonusLogCri.andUserIdEqualTo(userId);
//			bonusLogCri.andTypeEqualTo(0); //发奖金
			if(StringUtils.isNotBlank(start)){
				accountLogCri.andCtGreaterThanOrEqualTo(DateUtil.parse(start+" 00:00:00","yyyy-MM-dd HH:mm:ss",null));
//				bonusLogCri.andCtGreaterThanOrEqualTo(DateUtil.parse(start+" 00:00:00","yyyy-MM-dd HH:mm:ss",null));
			}
			if(StringUtils.isNotBlank(end)){
				accountLogCri.andCtLessThanOrEqualTo(DateUtil.parse(end+" 23:59:59","yyyy-MM-dd HH:mm:ss",null));
//				bonusLogCri.andCtLessThanOrEqualTo(DateUtil.parse(end+" 23:59:59","yyyy-MM-dd HH:mm:ss",null));
			}
			if(StringUtils.isNotBlank(type)){
				accountLogCri.andTypeEqualTo(Integer.parseInt(type));
//				bonusLogCri.andTypeEqualTo(Integer.parseInt(type));
			}
			accountLogCriteria.setOrderByClause("ct desc");
			List<UserAccountLog> logs = userAccountLogMapper.selectByExample(accountLogCriteria);
//			List<SsoUserBonusAccountLog> bonusLogs = ssoUserBonusAccountLogMapper.selectByExample(bonusAccountLogCriteria);
//			for (SsoUserBonusAccountLog bonusLog: bonusLogs ) {
//				UserAccountLog tmp = new UserAccountLog();
//				tmp.setId(bonusLog.getId());
//				tmp.setTradeno(bonusLog.getTradeno());
//				tmp.setOrderId(bonusLog.getOrderId());
//				tmp.setAmount(bonusLog.getAmount());
//				tmp.setCt(bonusLog.getCt());
//				tmp.setType(bonusLog.getType());
//				logs.add(tmp);
//			}
			Collections.sort(logs,
				Collections.reverseOrder(new Comparator<UserAccountLog>() {
					public int compare(UserAccountLog u1, UserAccountLog u2) {
						return u2.getCt().compareTo(u1.getCt());
					}}));
			account.setLogs(logs);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("账户总额查询异常userId={}", userId);
		}
		return account;
	}
	
	/**
	 * <申请提现><功能具体实现>
	 * @param userId
	 * @param attrInfo
	 * @param amount
	 * @author yankefei
	 * @date 2015年11月3日 下午4:19:02
	 */
	public Map<String, String> withdrawCash(String userId, String amount, String attrInfo) {
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
			/**
			 * 增加每天只能提现一次验证 by caiyy 151230
			 */
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
			ual.setTradetype(1);
			int tradeType = 1;
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
			uar.setStatus("审核中");
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
	}
	
}
