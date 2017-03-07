package com.lc.zy.ball.boss.framework.company.service;

import java.lang.reflect.Type;
import java.util.*;

import com.google.common.net.InetAddresses;
import com.lc.zy.ball.boss.framework.company.vo.*;
import com.lc.zy.ball.domain.oa.mapper.*;
import com.lc.zy.ball.domain.oa.po.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.emsg.client.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.Pageable;
import com.lc.zy.common.bean.ClientRequest;
import com.lc.zy.common.cache.RedisPool;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.zoo.SEQGenerate;

@Service
@Transactional(readOnly = true)
public class CompanyService extends AbstractCacheService {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);
	
	@Autowired
	private CompanyInfoMapper companyInfoMapper;
	
    @Autowired
    private SEQGenerate seqGenerate;
    
    @Autowired
    private SsoUserMapper ssoUserMapper;
    
    @Autowired
    private EmsgGroupsMapper emsgGroupsMapper;
    
    @Autowired
    private EmsgGroupUserMapper emsgGroupUserMapper;
    
    @Autowired
    private CompanyServicesMapper companyServicesMapper;
    
    @Autowired
    private CompanyServiceLogMapper companyServiceLogMapper;
    
    @Autowired
    private StaffAccountLogMapper staffAccountLogMapper;
    
    @Autowired
    private CompanyAccountLogMapper companyAccountLogMapper;
    
    @Autowired
    private CompanyServiceFlowMapper companyServiceFlowMapper;
    
    @Autowired
    private CompanyServiceItemMapper companyServiceItemMapper;
    
    @Autowired
    private RedisPool redisPool;
    
    @Autowired
	private EmsgServerMapper emsgServerMapper;
    
    @Autowired
    private PayLogMapper payLogMapper;
    
    @Autowired
    private CompanyBookItemMapper bookItemMapper;

	@Autowired
	private CompanyMonthIntegralMapper companyMonthIntegralMapper;

	@Autowired
	private CompanyGameMapper companyGameMapper;

	@Autowired
	private CompanyActivityMapper companyActivityMapper;

	@Autowired
	private CompanyActivityStatisticMapper companyActivityStatisticMapper;

	/**
	 * 
	 * <获取企业用户列表><功能具体实现>
	 * @param pageable
	 * @param searchParams
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年3月2日 下午4:52:02
	 */
	public PageImpl<CompanyInfoVo> list(Pageable pageable, Map<String, Object> searchParams) throws Exception {
		logger.debug(searchParams.toString());
		List<CompanyInfoVo> volist = new ArrayList<CompanyInfoVo>();
		CompanyInfoCriteria criteria = new CompanyInfoCriteria();
		CompanyInfoCriteria.Criteria cri = criteria.createCriteria();
		if (searchParams != null) {
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			Criterias.bySearchFilter(cri, filters.values());
		}
		criteria.setMysqlLength(pageable.getPageSize());
		criteria.setMysqlOffset(pageable.getOffset());
		criteria.setOrderByClause("ct desc");
		List<CompanyInfo>  list = companyInfoMapper.selectByExample(criteria);
		int total = companyInfoMapper.countByExample(criteria);
		if (CollectionUtils.isEmpty(list)) {
			list = new ArrayList<CompanyInfo>();
		}else{
			for (CompanyInfo companyInfo : list) {
				CompanyInfoVo vo = new CompanyInfoVo();
				BeanUtils.copyProperties(vo,companyInfo);
				//根据企业id获取企业账户
				CompanyAccount account = this.selectByPrimaryKey(CompanyAccount.class,companyInfo.getId());
				if(account != null){
					if(account.getBalance() != null){
						//账户可用余额
						vo.setBalance(String.valueOf(account.getBalance()));
					}
				}
				if(StringUtils.isNotBlank(vo.getGroupsId())){
					vo.setEmployees(this.getGroupUsers(vo).size());
				}
				volist.add(vo);
			}
		}
		return new PageImpl<>(volist, pageable, total);
	}

	/**
	 *<企业积分列表></>
	 * @param pageable
	 * @param searchParams
	 * @return
	 * @throws Exception
     */
	public PageImpl<CompanyMonthIntegralVo> integraLlist(Pageable pageable, Map<String, Object> searchParams) throws Exception {
		logger.debug(searchParams.toString());
		List<CompanyMonthIntegralVo> volist = new ArrayList<CompanyMonthIntegralVo>();
		CompanyMonthIntegralCriteria c = new CompanyMonthIntegralCriteria();
		CompanyMonthIntegralCriteria.Criteria cri = c.createCriteria();
		if (searchParams != null) {
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			Criterias.bySearchFilter(cri, filters.values());
		}
		c.setMysqlLength(pageable.getPageSize());
		c.setMysqlOffset(pageable.getOffset());
		c.setOrderByClause("integral desc");
		List<CompanyMonthIntegral>  list = companyMonthIntegralMapper.selectByExample(c);
		int total = companyMonthIntegralMapper.countByExample(c);
		if (CollectionUtils.isEmpty(list)) {
			list = new ArrayList<CompanyMonthIntegral>();
		}else{
			for (CompanyMonthIntegral companyMonthIntegral : list) {
				CompanyMonthIntegralVo vo = new CompanyMonthIntegralVo();
				BeanUtils.copyProperties(vo,companyMonthIntegral);
				CompanyInfo companyInfo = this.selectByPrimaryKey(CompanyInfo.class,vo.getCompanyId());
				if(companyInfo != null){
					vo.setName(companyInfo.getName());
					vo.setLinkMan(companyInfo.getLinkMan());
					vo.setLinkPhone(companyInfo.getLinkPhone());
				}
				volist.add(vo);
			}
		}
		return new PageImpl<>(volist, pageable, total);
	}

	/**
	 * <企业赛事列表></>
	 * @param pageable
	 * @param searchParams
	 * @return
	 * @throws Exception
     */
	public PageImpl<CompanyGameVo> companyGameList(Pageable pageable, Map<String, Object> searchParams) throws Exception {
		logger.debug(searchParams.toString());
		List<CompanyGameVo> volist = new ArrayList<CompanyGameVo>();
		CompanyGameCriteria c = new CompanyGameCriteria();
		CompanyGameCriteria.Criteria cri = c.createCriteria();
		if (searchParams != null) {
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			Criterias.bySearchFilter(cri, filters.values());
		}
		cri.andStatusNotEqualTo(0);
		c.setMysqlLength(pageable.getPageSize());
		c.setMysqlOffset(pageable.getOffset());
		c.setOrderByClause("ct desc");
		List<CompanyGame>  list = companyGameMapper.selectByExample(c);
		int total = companyGameMapper.countByExample(c);
		if (CollectionUtils.isEmpty(list)) {
			list = new ArrayList<CompanyGame>();
		}else{
			for (CompanyGame game : list) {
				CompanyGameVo vo = new CompanyGameVo();
				BeanUtils.copyProperties(vo,game);
				CompanyInfo companyInfo = this.selectByPrimaryKey(CompanyInfo.class,vo.getCompanyId());
				if(companyInfo != null){
					vo.setCompanyName(companyInfo.getName());
					vo.setLinkPhone(companyInfo.getLinkPhone());
					vo.setLinkMan(companyInfo.getLinkMan());
				}
				volist.add(vo);
			}
		}
		return new PageImpl<>(volist, pageable, total);
	}

	/**
	 * <企业活动列表></>
	 * @param pageable
	 * @param searchParams2
	 * @return
	 * @throws Exception
     */
	public PageImpl<CompanyActivityVo> companyActivityList(Pageable pageable, Map<String, Object> searchParams2) throws Exception {
		logger.debug(searchParams2.toString());
		List<CompanyActivityVo> volist = new ArrayList<CompanyActivityVo>();
		CompanyActivityCriteria c = new CompanyActivityCriteria();
		CompanyActivityCriteria.Criteria cri = c.createCriteria();
		if (searchParams2 != null) {
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams2);
			Criterias.bySearchFilter(cri, filters.values());
		}
		cri.andStatusNotEqualTo(0);
		c.setMysqlLength(pageable.getPageSize());
		c.setMysqlOffset(pageable.getOffset());
		c.setOrderByClause("ct desc");
		List<CompanyActivity>  list = companyActivityMapper.selectByExample(c);
		int total = companyActivityMapper.countByExample(c);
		if (CollectionUtils.isEmpty(list)) {
			list = new ArrayList<CompanyActivity>();
		}else{
			for (CompanyActivity activity : list) {
				CompanyActivityVo vo = new CompanyActivityVo();
				BeanUtils.copyProperties(vo,activity);
				CompanyInfo companyInfo = this.selectByPrimaryKey(CompanyInfo.class,vo.getCompanyId());
				if(companyInfo != null){
					vo.setCompanyName(companyInfo.getName());
					vo.setLinkPhone(companyInfo.getLinkPhone());
					vo.setLinkMan(companyInfo.getLinkMan());
				}
				volist.add(vo);
			}
		}
		return new PageImpl<>(volist, pageable, total);
	}

	/**
	 * 获取企业活动、赛事月统计数
	 * @param companyId
	 * @return
     */
	public CompanyActivityStatistic searchCompanyActivityStatistic(String companyId,Integer type){
		CompanyActivityStatisticCriteria c = new CompanyActivityStatisticCriteria();
		CompanyActivityStatisticCriteria.Criteria cri = c.createCriteria();
		cri.andCompanyIdEqualTo(companyId);
		cri.andTypeEqualTo(type);
		cri.andMonthEqualTo(DateUtil.formatDate(new Date(),"yyyy-MM"));
		if(companyActivityStatisticMapper.countByExample(c) > 0){
			return  companyActivityStatisticMapper.selectByExample(c).get(0);
		}else {
			return  null;
		}
	}

	/**
	 * 
	 * <企业员工列表><功能具体实现>
	 * @param pageable
	 * @param info
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年3月4日 下午5:46:19
	 */
	public PageImpl<EmsgGroupUserVo> GroupUsers(Pageable pageable,CompanyInfo info) throws Exception {
		List<EmsgGroupUserVo> voList = new ArrayList<EmsgGroupUserVo>();
		//从群组获取企业成员 获取管理员是联系人的企业群成员
		//1、获取企业群管理员ssouser账户id
		SsoUserCriteria userC = new SsoUserCriteria();
		SsoUserCriteria.Criteria uCri = userC.createCriteria();
		uCri.andCompanyIdEqualTo(info.getId());
		userC.setMysqlLength(pageable.getPageSize());
		userC.setMysqlOffset(pageable.getOffset());
		List<SsoUser> users =  ssoUserMapper.selectByExample(userC);
		int total = ssoUserMapper.countByExample(userC);
		if(CollectionUtils.isNotEmpty(users)){
			for (SsoUser ssoUser : users) {
				EmsgGroupUserVo vo = new EmsgGroupUserVo();
				vo.setUsername(ssoUser.getUsername());
				vo.setCompanyName(info.getName());
				vo.setCreateTime(ssoUser.getCreateTime());
				vo.setId(ssoUser.getId());
				vo.setPhone(ssoUser.getPhone());
				vo.setQiuyouno(ssoUser.getQiuyouno());
				vo.setState(ssoUser.getState());
				vo.setAddr(info.getAddr());
				//获取员额度，消费信息
				StaffAccount account = this.selectByPrimaryKey(StaffAccount.class,ssoUser.getId());
				if(account != null){
					vo.setLimitAmount(account.getLimitAmount());
					vo.setUseAmout(account.getUseAmout());
				}
				if(StringUtils.isNotBlank(info.getGroupsId())){
					//是否是群主
					EmsgGroups groups = this.selectByPrimaryKey(EmsgGroups.class,info.getGroupsId());
					if(groups != null){
						if(groups.getUserId().equals(ssoUser.getId())){
							vo.setType(1);
						}else{
							vo.setType(0);
						}
					}
				}
				voList.add(vo);
			}
		}
		return new PageImpl<>(voList, pageable, total);
	}
	
	
	/**
	 * 
	 * <根据企业获取企业员工><功能具体实现>
	 * @param info
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年3月4日 上午11:58:10
	 */
	public 	List<SsoUser>  getGroupUsers(CompanyInfo info)throws Exception{
		List<SsoUser>  users = new ArrayList<SsoUser>();
		//从群组获取企业成员 获取管理员是联系人的企业群成员
		//1、获取企业群管理员ssouser账户id
		if(StringUtils.isNotBlank(info.getGroupsId())){
			SsoUserCriteria userC = new SsoUserCriteria();
			SsoUserCriteria.Criteria uCri = userC.createCriteria();
			uCri.andCompanyIdEqualTo(info.getId());
			users =  ssoUserMapper.selectByExample(userC);
		}
		return users;
	}
	
	/**
	 * 
	 * <获取企业已消费总额><功能具体实现>
	 * @param companyId
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年3月8日 下午6:58:19
	 */
	public Integer useAmout(String companyId)throws Exception{
		//除了线下 固定包场、 1活动赛事；再加上线上固定包场、 线上活动赛事、 4运动基金消费, 6 包场取消  7 活动取消 8 运动基金退款
		CompanyAccountLogCriteria criteria = new CompanyAccountLogCriteria();
		CompanyAccountLogCriteria.Criteria cri = criteria.createCriteria();
		cri.andCompanyIdEqualTo(companyId);
		List<Integer> notIn = new ArrayList<Integer>();
		notIn.add(2);
		notIn.add(3);
		notIn.add(5);
		cri.andTransTypeNotIn(notIn);
		List<CompanyAccountLog> logs = companyAccountLogMapper.selectByExample(criteria);
		Integer userAmount = 0;
		for (CompanyAccountLog log : logs) {
			userAmount += log.getAmount();
		}
		//线下包场、活动
		CompanyServicesCriteria c = new CompanyServicesCriteria();
		CompanyServicesCriteria.Criteria  ci = c.createCriteria();
		ci.andCompanyIdEqualTo(companyId);
		List<Integer> in = new ArrayList<Integer>();
		in.add(0);
		in.add(1);
		ci.andServiceTypeIn(in);
		ci.andPayTypeEqualTo(1);
		ci.andStatusEqualTo(4);
		List<CompanyServices> list = companyServicesMapper.selectByExample(c);
		for (CompanyServices service : list) {
			userAmount += -1*service.getFee();
		}
		return userAmount;
	}
	
	/**
	 * 
	 * <获取员工消费记录列表><功能具体实现>
	 * @param companyId
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年3月6日 下午1:47:13
	 */
	public List<StaffAccountVo> staffAccountVoList(String companyId)throws Exception{
		List<StaffAccountVo>  voList = new ArrayList<StaffAccountVo>();
		CompanyAccountLogCriteria criteria = new CompanyAccountLogCriteria();
		CompanyAccountLogCriteria.Criteria cri = criteria.createCriteria();
		cri.andCompanyIdEqualTo(companyId);
		cri.andTransTypeEqualTo(4);
		List<CompanyAccountLog> logs = companyAccountLogMapper.selectByExample(criteria);
		for(CompanyAccountLog log : logs){
			Order order = this.selectByPrimaryKey(Order.class,log.getServiceId());
			if(order.getStatus().equals(Constants.OrderStatus.PLAYING) || order.getStatus().equals(Constants.OrderStatus.PAIED)
					|| order.getStatus().equals(Constants.OrderStatus.VERIFY)){
				StaffAccountVo vo = new StaffAccountVo();
				SsoUser ssoUser = this.selectByPrimaryKey(SsoUser.class, order.getCustomerId());
				vo.setUserId(ssoUser.getId());
				vo.setUserName(ssoUser.getUsername());
				vo.setPhone(ssoUser.getPhone());
				vo.setOrderId(order.getId());
				vo.setUseAmout(order.getFinalFee());
				vo.setCt(order.getCt());
				switch (order.getOrdersType()){
				case 0:
					vo.setOrdersType("订场");
					break;
				case 1:
					vo.setOrdersType("教/陪练");
					break;
				case 2:
					vo.setOrdersType("活动");
					break;
				case 3:
					vo.setOrdersType("赛事");
					break;
				}
				voList.add(vo);
			}
		}
		return voList;
	} 
	
	public List<StaffAccountVo> userStaffAccountVoList(String id,SsoUser user)throws Exception{
		List<StaffAccountVo>  voList = new ArrayList<StaffAccountVo>();
		StaffAccountLogCriteria c = new StaffAccountLogCriteria();
		StaffAccountLogCriteria.Criteria cri = c.createCriteria();
		cri.andUserIdEqualTo(id);
		c.setOrderByClause("ct desc");
		List<StaffAccountLog> list = staffAccountLogMapper.selectByExample(c);
		if(CollectionUtils.isNotEmpty(list)){
			for (StaffAccountLog staffAccountLog : list) {
				StaffAccountVo vo = new StaffAccountVo();
				BeanUtils.copyProperties(vo,staffAccountLog);
				vo.setUserId(user.getId());
				vo.setUserName(user.getUsername());
				vo.setPhone(user.getPhone());
				vo.setOrderId(staffAccountLog.getOrderId());
				vo.setUseAmout(staffAccountLog.getFee());
				Order order = this.selectByPrimaryKey(Order.class,staffAccountLog.getOrderId());
				switch (order.getOrdersType()){
				case 0:
					vo.setOrdersType("订场");
					break;
				case 1:
					vo.setOrdersType("教/陪练");
					break;
				case 2:
					vo.setOrdersType("活动");
					break;
				case 3:
					vo.setOrdersType("赛事");
					break;
				}
				voList.add(vo);
			}
		}
		return voList;
	} 
	
	
	/**
	 * 
	 * <获取已购买的套餐><功能具体实现>
	 * @return
	 * @author liangsh
	 * @date 2016年3月4日 下午4:48:13
	 */
	public List<CompanyServices> servicesList(String companyId){
		CompanyServicesCriteria c = new CompanyServicesCriteria();
		CompanyServicesCriteria.Criteria  cri = c.createCriteria();
		cri.andCompanyIdEqualTo(companyId);
		List<Integer> in = new ArrayList<Integer>();
		in.add(0);
		in.add(1);
		cri.andServiceTypeIn(in);
		List<CompanyServices> list = companyServicesMapper.selectByExample(c);
		List<CompanyServices> list_ = new ArrayList<CompanyServices>();
		for(CompanyServices service:list){
			if(service.getPayType()==0){
				if(service.getStatus()!=2&&service.getStatus()!=3){
					list_.add(service);
				}
			}else{
				if(service.getStatus()==4){
					list_.add(service);
				}
			}
		}
		return list_;
	}
	
	/**
	 * 
	 * <获取已购买的套餐><功能具体实现>
	 * @return
	 * @author liangsh
	 * @date 2016年3月4日 下午4:48:13
	 */
	public List<CompanyServices> withDrawAndChargeList(String companyId){
		CompanyServicesCriteria c = new CompanyServicesCriteria();
		CompanyServicesCriteria.Criteria  cri = c.createCriteria();
		cri.andCompanyIdEqualTo(companyId);
		List<Integer> notIn = new ArrayList<Integer>();
		notIn.add(2);
		notIn.add(3);
		cri.andStatusNotIn(notIn);
		List<Integer> in = new ArrayList<Integer>();
		in.add(2);
		in.add(3);
		cri.andServiceTypeIn(in);
		List<CompanyServices> list = companyServicesMapper.selectByExample(c);
		if(CollectionUtils.isEmpty(list)){
			list = new ArrayList<CompanyServices>();
		}
		return list;
	}
	
	/**
	 * 
	 * <运营服务企业备忘列表><功能具体实现>
	 * @param companyId
	 * @return
	 * @author liangsh
	 * @date 2016年3月4日 下午5:32:40
	 */
	public List<CompanyServiceLog> serviceLogs(String companyId) {
		CompanyServiceLogCriteria c = new CompanyServiceLogCriteria();
		CompanyServiceLogCriteria.Criteria cri = c.createCriteria();
		cri.andCompanyIdEqualTo(companyId);
		List<CompanyServiceLog> list = companyServiceLogMapper.selectByExample(c);
		if(CollectionUtils.isEmpty(list)){
			list = new ArrayList<CompanyServiceLog>();
		}
		return list;
	}
	
	/**
	 * 
	 * <保存企业用户><功能具体实现>
	 * @param info
	 * @return    
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年3月2日 下午4:52:18
	 */
	@Transactional(readOnly = false)
	public Map<String, Object> createOrUpdate(CompanyInfo info) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if (info != null) {
			CompanyInfo old = this.selectByPrimaryKey(CompanyInfo.class,info.getId());
			if (StringUtils.isBlank(info.getId())) {
				info.setId(UUID.get());
				createCompanyAccount(info.getId());// 注册完企业马上注册公司账户
				info.setCt(new Date());
				info.setCb(SessionUtil.currentUserId());
				info.setEt(new Date());
				info.setEb(SessionUtil.currentUserId());
				info.setState(0);
				String userId = "";
				//根据手机号查注册的账号Id
				SsoUserCriteria c = new SsoUserCriteria();
				SsoUserCriteria.Criteria cri = c.createCriteria();
				cri.andPhoneEqualTo(info.getLinkPhone());
				List<SsoUser> users = ssoUserMapper.selectByExample(c);
				if(CollectionUtils.isNotEmpty(users)){
					for (SsoUser ssoUser : users) {
						if(StringUtils.isNotBlank(ssoUser.getCompanyId())){
							result.put(Constants.Result.RESULT, false);
							result.put(Constants.Result.REASON, "保存失败，联系人手机已绑定其他企业账号！");
							return result;
						}else{
							ssoUser.setState(1);
							ssoUser.setUpdateTime(new Date());
							ssoUser.setCompanyId(info.getId());
							this.updateByPrimaryKeySelective(ssoUser,ssoUser.getId());
							userId = ssoUser.getId();
							break;
						}
					}
				}
				//创建ssouser账号
				if(StringUtils.isEmpty(userId)){
					userId = this.createSsoUser(info);
				}
				//用户创建成功创建群组
				if(StringUtils.isNotBlank(userId)){
					//创建企业群组
					EmsgGroups group = new EmsgGroups();
					group.setId(UUID.get());
					group.setCt(new Date());
					group.setCb(SessionUtil.currentUserId());
					group.setEt(new Date());
					group.setEb(SessionUtil.currentUserId());
					group.setName(info.getName());
					group.setPhoto(info.getLogo());
					group.setType(1);
					group.setGroupId(seqGenerate.genGroupId());
					group.setUserId(userId);
				    this.insertSelective(group,group.getId());
				    this.reload(group.getId());
					info.setGroupsId(group.getId());
				    //设置群组管理员
				    EmsgGroupUser groupUser = new EmsgGroupUser();
				    groupUser.setId(UUID.get());
				    groupUser.setUserId(userId);
				    groupUser.setCt(new Date());
				    groupUser.setEt(new Date());
				    groupUser.setGroupId(group.getId());
				    groupUser.setType(1);	//用户类型：0普通用户1群主2企业用户
				    groupUser.setAction(2); //动作：1申请入群2邀请入群
				    this.insertSelective(groupUser,groupUser.getId());
				}
				int infoCount = this.insertSelective(info, info.getId());
				if(infoCount > 0){
					this.createStaffAccount(userId, info.getId());
					result.put(Constants.Result.RESULT, true);
					result.put(Constants.Result.DATA,info.getId());
					return result;
				}else{
					result.put(Constants.Result.RESULT, false);
					result.put(Constants.Result.REASON, "保存失败");
					return result;
				}
			} else {
				info.setEt(new Date());
				info.setEb(SessionUtil.currentUserId());
				String userId = "";
				//查看是否更换了联系人
				if(!info.getLinkPhone().equals(old.getLinkPhone())){
					//根据新联系人手机号查注册的账号Id
					SsoUserCriteria c = new SsoUserCriteria();
					SsoUserCriteria.Criteria cri = c.createCriteria();
					cri.andPhoneEqualTo(info.getLinkPhone());
					List<SsoUser> users = ssoUserMapper.selectByExample(c);
					if(CollectionUtils.isNotEmpty(users)){
						for (SsoUser ssoUser : users) {
							if(StringUtils.isNotBlank(ssoUser.getCompanyId())){
								result.put(Constants.Result.RESULT, false);
								result.put(Constants.Result.REASON, "保存失败，联系人手机已绑定其他企业账号！");
								return result;
							}else{
								ssoUser.setState(1);
								ssoUser.setUpdateTime(new Date());
								//ssoUser.setUsername(SessionUtil.currentUserId());
								ssoUser.setCompanyId(info.getId());
								this.updateByPrimaryKeySelective(ssoUser,ssoUser.getId());
								userId = ssoUser.getId();
								break;
							}
						}
					}
					//创建ssouser账号
					if(StringUtils.isEmpty(userId)){
						userId = this.createSsoUser(info);
					}
					//修改群组管理员
					EmsgGroupsCriteria g = new EmsgGroupsCriteria();
					EmsgGroupsCriteria.Criteria gCriteria = g.createCriteria();
					gCriteria.andUserIdEqualTo(old.getLinkPhone()); 
					gCriteria.andTypeEqualTo(1); //0 ：普通群，1：企业群
					List<EmsgGroups> gList = emsgGroupsMapper.selectByExample(g);
					if(CollectionUtils.isNotEmpty(gList)){
						if(!info.getLinkPhone().equals(old.getLinkPhone())){
							//群组信息更新
							EmsgGroups oldGroups  = gList.get(0);
							oldGroups.setUserId(userId);
							oldGroups.setEt(new Date());
							oldGroups.setEb(SessionUtil.currentUserId());
							this.updateByPrimaryKeySelective(oldGroups,oldGroups.getId());
							//更换管理员
							EmsgGroupUserCriteria gUserC = new EmsgGroupUserCriteria();
							EmsgGroupUserCriteria.Criteria guCriteria = gUserC.createCriteria();
							guCriteria.andUserIdEqualTo(old.getLinkPhone());
							guCriteria.andGroupIdEqualTo(oldGroups.getId());
							guCriteria.andTypeEqualTo(1);  //0 ：普通群，1：企业群
							List<EmsgGroupUser> gUsers = emsgGroupUserMapper.selectByExample(gUserC);
							for (EmsgGroupUser emsgGroupUser : gUsers) {
								emsgGroupUser.setUserId(userId);
								emsgGroupUser.setEt(new Date());
								this.updateByPrimaryKeySelective(emsgGroupUser,emsgGroupUser.getId());
							}
						}
					}else{
						//创建企业群组
						EmsgGroups group = new EmsgGroups();
						group.setId(UUID.get());
						group.setCt(new Date());
						group.setCb(SessionUtil.currentUserId());
						group.setEt(new Date());
						group.setEb(SessionUtil.currentUserId());
						group.setName(info.getName());
						group.setPhoto(info.getLogo());
						group.setType(1);
						group.setGroupId(seqGenerate.genGroupId());
						group.setUserId(userId);
					    this.insertSelective(group,group.getId());
						info.setGroupsId(group.getId());
					    this.reload(group.getId());
					    //设置群组管理员
					    EmsgGroupUser groupUser = new EmsgGroupUser();
					    groupUser.setId(UUID.get());
					    groupUser.setUserId(userId);
					    groupUser.setCt(new Date());
					    groupUser.setEt(new Date());
					    groupUser.setGroupId(group.getId());
					    groupUser.setType(1);	//用户类型：0普通用户1群主2企业用户
					    groupUser.setAction(2); //动作：1申请入群2邀请入群
					    this.insertSelective(groupUser,groupUser.getId());
					}
					this.createStaffAccount(userId, info.getId());
				}
				int infoCount = this.updateByPrimaryKeySelective(info, info.getId());
				if(infoCount > 0){
					result.put(Constants.Result.RESULT, true);
					result.put(Constants.Result.DATA,info.getId());
					return result;
				}else{
					result.put(Constants.Result.RESULT, false);
					result.put(Constants.Result.REASON, "保存失败");
					return result;
				}
			}
		   
		}
		result.put(Constants.Result.RESULT, false);
		result.put(Constants.Result.REASON, "保存失败");
		return result;
	}
	
	/**
	 * 
	 * <创建企业员工账户><功能具体实现>
	 * @param userId
	 * @author liangsh
	 * @date 2016年3月14日 下午4:59:59
	 */
	@Transactional(readOnly = false)
	public void createStaffAccount(String userId,String companyId)throws Exception{
		//查看此用户是否已有企业账户
		StaffAccount account = this.selectByPrimaryKey(StaffAccount.class,companyId);
		if(account != null){ //存在更新企业id
			account.setCompanyAccountId(companyId);
			this.updateByPrimaryKeySelective(account,userId);
		}else{//不存在创建账户
			account = new StaffAccount();
			account.setUserId(userId);
			account.setCompanyAccountId(companyId);
			account.setCb(SessionUtil.currentUserId());
			account.setCt(new Date());
			account.setEt(new Date());
			account.setEb(SessionUtil.currentUserId());
			account.setLimitAmount(0);
			account.setUseAmout(0);
			this.insertSelective(account,userId);
		}
		
	}
	
	 /**
     * 
     * <重新加载群成员><功能具体实现>
     *
     * @create：2015年9月25日 下午6:40:47
     * @author： CYY
     * @param gid
     */
    public void reload(String gid) {
        try {
            Gson gson = MyGson.getInstance();
			EmsgServer emsgServer = this.getEmsgServer();
            ClientRequest request = new ClientRequest();
            request.setSn(UUID.get());
            request.setService("emsg_group");
            request.setMethod("reload");
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("domain", emsgServer.getDomain());
            params.put("license", emsgServer.getLicense());
            params.put("gid", gid);
            request.setParams(params);
            HashMap<String, String> requestaram = new HashMap<String, String>();
            String body = gson.toJson(request);
            logger.debug("body = {} " + body);
            requestaram.put("body", body);
            String url = emsgServer.getHost();
            String port = "4280"; // 端口
            url = "http://" + url + ":" + port;
            logger.debug("url = {} " + url);
            HttpUtils.http(url, requestaram);
        } catch (Exception e) {
            logger.error("EmsgGroupRepository reload: " + e.getMessage(), e);
        }
    }
    
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
	 * <创建SsoUser账户><功能具体实现>
	 * @param info
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年3月3日 下午5:05:51
	 */
	@Transactional(readOnly = false)
	private String createSsoUser(CompanyInfo info)throws Exception{
		SsoUser user = new SsoUser();
		user.setId(UUID.get());
		user.setCompanyId(info.getId());
		user.setName(info.getLinkMan());
		user.setPhone(info.getLinkPhone());
		user.setCompanyId(info.getId());
		user.setCreateTime(new Date());
		user.setCreateUser(SessionUtil.currentUserId());
		user.setUpdateTime(new Date());
		user.setUpdateUser(SessionUtil.currentUserId());
		user.setUsername(info.getLinkPhone());
		user.setQiuyouno(seqGenerate.genQiuyouNumber());
		user.setState(1);
		user.setRegistTime(new Date());
		user.setNickName("球友"+user.getQiuyouno());
		user.setActiveState(1);
		int count = this.insertSelective(user,user.getId());
		if(count > 0){
			return user.getId();
		}
		return "";
	}
	/**
	 * 
	 * <获取企业服务列表><功能具体实现>
	 * @param pageable
	 * @param searchParams
	 * @return
	 * @throws Exception
	 * @date 2016年3月2日 下午4:52:02
	 */
	public PageImpl<CompanyServicesVo> services(Pageable pageable, Map<String, Object> searchParams,boolean isPage) throws Exception {
		logger.debug(searchParams.toString());
		List<CompanyServices> services = new ArrayList<CompanyServices>();
		List<CompanyServicesVo> results = new ArrayList<CompanyServicesVo>();
		CompanyServicesCriteria criteria = new CompanyServicesCriteria();
		CompanyServicesCriteria.Criteria cri = criteria.createCriteria();
		if (searchParams != null&&searchParams.containsKey("LIKE_name")) {
			String name = (String)searchParams.get("LIKE_name");
			CompanyInfoCriteria ciCriteria = new CompanyInfoCriteria();
			CompanyInfoCriteria.Criteria ciCri = ciCriteria.createCriteria();
//			ciCri.andNameEqualTo(name);
			ciCri.andNameLike("%"+name+"%");
			List<CompanyInfo> companys = companyInfoMapper.selectByExample(ciCriteria);
			if(CollectionUtils.isEmpty(companys)){
				return new PageImpl<>(results, pageable, 0);
			}else{
//				String companyId = companys.get(0).getId();
//				searchParams.put("EQ_companyId", companyId);
				List<String> companyIds  =  new ArrayList<String>();
				for (CompanyInfo company: companys ) {
					companyIds.add(company.getId());
				}
				cri.andCompanyIdIn(companyIds);
			}
//			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
//			Criterias.bySearchFilter(cri, filters.values());
		}
		int total = companyServicesMapper.countByExample(criteria);
		if (total==0) {
			return new PageImpl<>(results, pageable, total);
		}else{
			if(isPage) {
				criteria.setMysqlLength(pageable.getPageSize());
				criteria.setMysqlOffset(pageable.getOffset());
			}
			criteria.setOrderByClause("ct desc");
		}
		services = companyServicesMapper.selectByExample(criteria);
		CompanyServicesVo result = null;
		for(CompanyServices service:services){
			result = new CompanyServicesVo();
			org.springframework.beans.BeanUtils.copyProperties(service, result);
			String companyId = service.getCompanyId();
			CompanyInfo company = this.selectByPrimaryKey(CompanyInfo.class, companyId);
			if(company==null){
				continue;
			}
			String companyName = company.getName();
			result.setName(companyName);
			String cb = service.getCb();
			User user = this.selectByPrimaryKey(User.class, cb);
			result.setCb(user==null?null:user.getNickname());
			int serviceType = service.getServiceType();
			switch (serviceType){
				case 0:
					result.setServiceType_("固定包场");
					break;
				case 1:
					result.setServiceType_("活动赛事");
					break;
				case 2:
					result.setServiceType_("充值");
					break;
				case 3:
					result.setServiceType_("提现");
					break;
			}
			results.add(result);
		}
		return new PageImpl<>(results, pageable, total);
	}
	/**
	 * 保存服务
	 * @param service
	 * @param items
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public void saveService(CompanyServices service,List<CompanyServiceItem> items) throws Exception{
		this.insertSelective(service, service.getId());
		for(CompanyServiceItem item:items){
			this.insertSelective(item, item.getId());
		}
		//活动、包场
		if(((service.getServiceType()==0||service.getServiceType()==1)&&service.getPayType()==0)||service.getServiceType()==3){
			CompanyAccount companyAccount = this.selectByPrimaryKey(CompanyAccount.class,service.getCompanyId());
	        Integer balanceAmount = companyAccount.getBalance() - service.getFee();
	        companyAccount.setBalance(balanceAmount);
	        companyAccount.setEt(new Date());
	        companyAccount.setEb(service.getCb());
	        this.updateByPrimaryKeySelective(companyAccount, companyAccount.getCompanyId());
	        CompanyAccountLog companyAccountLog = new CompanyAccountLog();
	        companyAccountLog.setId(UUID.get());
	        companyAccountLog.setCompanyId(companyAccount.getCompanyId());
	        companyAccountLog.setAmount(service.getFee()*-1);
	        companyAccountLog.setBalance(balanceAmount);
	        companyAccountLog.setServiceId(service.getId());
	        companyAccountLog.setTransType(service.getServiceType());// 消费
	        companyAccountLog.setCt(new Date());
	        companyAccountLogMapper.insertSelective(companyAccountLog);
		}
	}
	
	/**
	 * 
	 * <根据登录人角色获取待审核列表><功能具体实现>
	 * @param pageable
	 * @param searchParams
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年3月7日 下午3:10:23
	 */
	public PageImpl<CompanyServicesVo> servicesOrders(Pageable pageable, Map<String, Object> searchParams) throws Exception {
		List<CompanyServicesVo> voList = new ArrayList<CompanyServicesVo>();
		CompanyServicesCriteria c = new CompanyServicesCriteria();
		CompanyServicesCriteria.Criteria cri = c.createCriteria();
		if (searchParams != null) {
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			Criterias.bySearchFilter(cri, filters.values());
		}
	
		c.setMysqlLength(pageable.getPageSize());
		c.setMysqlOffset(pageable.getOffset());
		c.setOrderByClause("status asc,ct desc");
		int total = companyServicesMapper.countByExample(c);
		List<CompanyServices> list = companyServicesMapper.selectByExample(c);
		if(CollectionUtils.isNotEmpty(list)){
			for (CompanyServices companyServices : list) {
					CompanyServicesVo vo = new CompanyServicesVo();
					org.springframework.beans.BeanUtils.copyProperties(companyServices,vo);
					if(StringUtils.isNotBlank(companyServices.getCompanyId())){
						CompanyInfo company = this.selectByPrimaryKey(CompanyInfo.class, companyServices.getCompanyId());
						if(company != null){
							vo.setName(company.getName());
						}
						int serviceType = companyServices.getServiceType();
						switch (serviceType){
							case 0:
								vo.setServiceType_("固定包场");
								vo.setType("收款");
								break;
							case 1:
								vo.setServiceType_("活动赛事");
								vo.setType("收款");
								break;
							case 2:
								vo.setServiceType_("充值");
								vo.setType("收款 ");
								vo.setPayType(1);
								break;
							case 3:
								vo.setServiceType_("提现");
								vo.setType("付款");
								vo.setPayType(1);
								break;
						}
					}
					voList.add(vo);
			}
		}else{
			list = new ArrayList<CompanyServices>();
		}
		return new PageImpl<>(voList, pageable, total);
	}
	
	/**
	 * 
	 * <财务确认服务订单><功能具体实现>
	 * @param id 0提现1收款
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年3月8日 下午3:02:48
	 */
	@Transactional(readOnly = false)
	public void financeAudit(String id,Integer type)throws Exception{
		CompanyServices service = this.selectByPrimaryKey(CompanyServices.class, id);
		CompanyServices service_ = new CompanyServices();
		CompanyServiceFlow flow = new CompanyServiceFlow();
		flow.setId(UUID.get());
		flow.setServiceId(service.getId());
		flow.setHandler(SessionUtil.currentUserId());
		flow.setHandleTime(new Date());
		service_.setId(service.getId());
		service_.setStatus(4);
		PayLog payLog = new PayLog();
		payLog.setId(UUID.get());
		payLog.setCreateTime(new Date());
		payLog.setOrderId(service.getId());
		payLog.setPayType(4);
		payLog.setUserId(SessionUtil.currentUserId());
		if(type==0){//提现
			flow.setOperation("财务确认提现");
			payLog.setFee(service.getFee()*-1);
			payLog.setFinalFee(service.getFee()*-1);
			payLog.setStatus(Constants.PayStatus.WITH_DRAW_SUCCESS);
			payLogMapper.insert(payLog);
		}else{
			
			if(service.getServiceType()==0||service.getServiceType()==1){
				
			}else{
				CompanyAccount companyAccount = this.selectByPrimaryKey(CompanyAccount.class,service.getCompanyId());
		        Integer balanceAmount = companyAccount.getBalance() + service.getFee();
		        companyAccount.setBalance(balanceAmount);
		        companyAccount.setEt(new Date());
		        companyAccount.setEb(service.getCb());
		        this.updateByPrimaryKeySelective(companyAccount, companyAccount.getCompanyId());
		        
		        CompanyAccountLog companyAccountLog = new CompanyAccountLog();
		        companyAccountLog.setId(UUID.get());
		        companyAccountLog.setCompanyId(companyAccount.getCompanyId());
		        companyAccountLog.setAmount(service.getFee());
		        companyAccountLog.setBalance(balanceAmount);
		        companyAccountLog.setServiceId(service.getId());
		        companyAccountLog.setTransType(service.getServiceType());
		        companyAccountLog.setCt(new Date());
		        companyAccountLogMapper.insertSelective(companyAccountLog);
				this.updateByPrimaryKeySelective(service_, service_.getId());
				payLog.setFee(service.getFee());
				payLog.setFinalFee(service.getFee());
				payLog.setStatus(Constants.PayStatus.RECHARGE_SUCCESS);
				payLogMapper.insert(payLog);
			}
			flow.setOperation("财务确认收款");
			if(service.getServiceType() == 1 && service.getPayType() == 1){
					//企业月赛事总数加1
					CompanyActivityStatistic statistic = this.searchCompanyActivityStatistic(service.getCompanyId(),1);
					if(statistic != null){
						statistic.setHoldTimes(statistic.getHoldTimes() +1);
						this.updateByPrimaryKeySelective(statistic,statistic.getId());
					}else{
						statistic = new CompanyActivityStatistic();
						statistic.setId(UUID.get());
						statistic.setCt(new Date());
						statistic.setCb(SessionUtil.currentUserId());
						statistic.setCompanyId(service.getCompanyId());
						statistic.setMonth(DateUtil.formatDate(new Date(),"yyyy-MM"));
						statistic.setType(1);
						statistic.setHoldTimes(1);
						statistic.setEt(new Date());
						this.insertSelective(statistic,statistic.getId());
					}
					CompanyInfo companyInfo = this.selectByPrimaryKey(CompanyInfo.class,service.getCompanyId());
					companyInfo.setActivityNum(statistic.getHoldTimes());
				this.updateByPrimaryKeySelective(companyInfo,companyInfo.getId());
			}
		}
		companyServiceFlowMapper.insertSelective(flow);
		this.updateByPrimaryKeySelective(service_, service_.getId());
		if(service.getServiceType() == 1 || service.getServiceType() == 0){
			this.createOrderItems(id, service.getServiceType(), service.getCompanyId());
		}
	}
	/**
	 * 
	 * <财务确认服务订单><功能具体实现>
	 * @param id
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年3月8日 下午3:02:48
	 */
	@Transactional(readOnly = false)
	public void customerAudit(String id,Integer type)throws Exception{
		CompanyServices service = this.selectByPrimaryKey(CompanyServices.class, id);
		CompanyServices service_ = new CompanyServices();
		CompanyServiceFlow flow = new CompanyServiceFlow();
		flow.setId(UUID.get());
		flow.setServiceId(service.getId());
		flow.setHandler(SessionUtil.currentUserId());
		flow.setHandleTime(new Date());
		service_.setId(service.getId());
		if(type==0){//拒绝
			service_.setStatus(2);
			CompanyAccount companyAccount = this.selectByPrimaryKey(CompanyAccount.class,service.getCompanyId());
	        Integer balanceAmount = companyAccount.getBalance() + service.getFee();
	        companyAccount.setBalance(balanceAmount);
	        companyAccount.setEt(new Date());
	        companyAccount.setEb(service.getCb());
	        this.updateByPrimaryKeySelective(companyAccount, companyAccount.getCompanyId());
	        CompanyAccountLog companyAccountLog = new CompanyAccountLog();
	        companyAccountLog.setId(UUID.get());
	        companyAccountLog.setCompanyId(companyAccount.getCompanyId());
	        companyAccountLog.setAmount(service.getFee());
	        companyAccountLog.setBalance(balanceAmount);
	        companyAccountLog.setServiceId(service.getId());
	        companyAccountLog.setTransType(5);// 提现拒绝
	        companyAccountLog.setCt(new Date());
	        companyAccountLogMapper.insertSelective(companyAccountLog);
			this.updateByPrimaryKeySelective(service_, service_.getId());
			flow.setOperation("客服审核拒绝");
			companyServiceFlowMapper.insertSelective(flow);
			return;
		}
		service_.setStatus(0);
		service_.setFlowState(3);
		flow.setOperation("客服审核通过");
		companyServiceFlowMapper.insertSelective(flow);
		this.updateByPrimaryKeySelective(service_, service_.getId());
	}
	
	public void createOrderItems(String serviceId,Integer serviceType,String companyId){
		CompanyServiceItemCriteria c = new CompanyServiceItemCriteria();
		CompanyServiceItemCriteria.Criteria cri = c.createCriteria();
		cri.andManFlagEqualTo(0);
		cri.andServiceIdEqualTo(serviceId);
		List<CompanyServiceItem> itemsList = companyServiceItemMapper.selectByExample(c);
		Map<Integer, List<Integer>> activityDate = MyGson.getInstance().fromJson(itemsList.get(0).getActivityTime(), new TypeToken<Map<Integer, List<Integer>>>(){}.getType());
			List<CompanyBookItem> books = new ArrayList<CompanyBookItem>();
			String begin = "";
			String end = "";
			if(activityDate!=null&&!activityDate.isEmpty()){
				if(serviceType == 1){
					begin = DateUtils.forDatetime(itemsList.get(0).getBegin());
					end = DateUtils.forDatetime(itemsList.get(0).getEnd());
				}else{
					begin = DateUtils.formatDate(itemsList.get(0).getBegin(), "yyyy-MM-dd");
					end = DateUtils.formatDate(itemsList.get(0).getEnd(), "yyyy-MM-dd");
				}
				 Date b = DateUtil.parse(begin, null);
				 Date e = DateUtil.parse(end, null);
				 Calendar c1 = Calendar.getInstance();
				c1.setTime(b);
				
				Calendar c2 = Calendar.getInstance();
				c2.setTime(e);
				
				CompanyBookItem book = null;
				while (c1.compareTo(c2)<=0){
					int weekend = c1.get(Calendar.DAY_OF_WEEK)-1==0?7:c1.get(Calendar.DAY_OF_WEEK)-1;
					if(activityDate.containsKey(weekend)){
						book = new CompanyBookItem();
						book.setId(UUID.get());
						book.setActivitydate(DateUtil.formatDate(c1.getTime(), "yyyy-MM-dd"));
						book.setCompanyId(companyId);
						book.setCompanyServiceId(serviceId);
						book.setStartTime(activityDate.get(weekend).get(0));
						book.setEndTime(activityDate.get(weekend).get(1));
						books.add(book);
					}
					c1.add(Calendar.DAY_OF_MONTH,1);
				}
			}
			if(serviceType==1){
				CompanyBookItem book = new CompanyBookItem();
				book.setId(UUID.get());
				book.setActivitydate(DateUtil.formatDate(itemsList.get(0).getBegin(), "yyyy-MM-dd"));
				book.setCompanyId(companyId);
				book.setCompanyServiceId(serviceId);
				Calendar c3 = Calendar.getInstance();
				c3.setTime(itemsList.get(0).getBegin());
				book.setStartTime(c3.get(Calendar.HOUR_OF_DAY));
				books.add(book);
			}
			for(CompanyBookItem book:books){
				bookItemMapper.insert(book);
			}
			
	}
	
	
	/**
	 * 审核
	 * @param service
	 * @throws Exception 
	 */
	@Transactional(readOnly = false)
	public void auditServcie(CompanyServices service,int type) throws Exception{
		int serviceType = service.getServiceType();
		CompanyServices service_ = new CompanyServices();
		CompanyServiceFlow flow = new CompanyServiceFlow();
		flow.setId(UUID.get());
		flow.setServiceId(service.getId());
		flow.setHandler(SessionUtil.currentUserId());
		flow.setHandleTime(new Date());
		service_.setId(service.getId());
		if(type==0){//拒绝
			service_.setStatus(2);
			if(service.getServiceType()==3){
				CompanyAccount companyAccount = this.selectByPrimaryKey(CompanyAccount.class,service.getCompanyId());
		        Integer balanceAmount = companyAccount.getBalance() + service.getFee();
		        companyAccount.setBalance(balanceAmount);
		        companyAccount.setEt(new Date());
		        companyAccount.setEb(service.getCb());
		        this.updateByPrimaryKeySelective(companyAccount, companyAccount.getCompanyId());
		        CompanyAccountLog companyAccountLog = new CompanyAccountLog();
		        companyAccountLog.setId(UUID.get());
		        companyAccountLog.setCompanyId(companyAccount.getCompanyId());
		        companyAccountLog.setAmount(service.getFee());
		        companyAccountLog.setBalance(balanceAmount);
		        companyAccountLog.setServiceId(service.getId());
		        companyAccountLog.setTransType(5);// 提现拒绝
		        companyAccountLog.setCt(new Date());
		        companyAccountLogMapper.insertSelective(companyAccountLog);
			} else if(service.getServiceType()==0||service.getServiceType()==1){
				if(service.getPayType()==0){
					CompanyAccount companyAccount = this.selectByPrimaryKey(CompanyAccount.class,service.getCompanyId());
			        Integer balanceAmount = companyAccount.getBalance() + service.getFee();
			        companyAccount.setBalance(balanceAmount);
			        companyAccount.setEt(new Date());
			        companyAccount.setEb(service.getCb());
			        this.updateByPrimaryKeySelective(companyAccount, companyAccount.getCompanyId());
			        CompanyAccountLog companyAccountLog = new CompanyAccountLog();
			        companyAccountLog.setId(UUID.get());
			        companyAccountLog.setCompanyId(companyAccount.getCompanyId());
			        companyAccountLog.setAmount(service.getFee());
			        companyAccountLog.setBalance(balanceAmount);
			        companyAccountLog.setServiceId(service.getId());
			        companyAccountLog.setTransType(service.getServiceType()==0?6:7);// 包场、活动
			        companyAccountLog.setCt(new Date());
			        companyAccountLogMapper.insertSelective(companyAccountLog);
				}
			}
			this.updateByPrimaryKeySelective(service_, service_.getId());
			flow.setOperation("销售经理审核拒绝");
			companyServiceFlowMapper.insertSelective(flow);
			return;
		}
		if(type==2){//取消
			service_.setStatus(3);
			flow.setOperation("销售经理取消订单");
			companyServiceFlowMapper.insertSelective(flow);
			this.updateByPrimaryKeySelective(service_, service_.getId());
			if(service.getServiceType()==3){
				CompanyAccount companyAccount = this.selectByPrimaryKey(CompanyAccount.class,service.getCompanyId());
		        Integer balanceAmount = companyAccount.getBalance() + service.getFee();
		        companyAccount.setBalance(balanceAmount);
		        companyAccount.setEt(new Date());
		        companyAccount.setEb(service.getCb());
		        this.updateByPrimaryKeySelective(companyAccount, companyAccount.getCompanyId());
		        CompanyAccountLog companyAccountLog = new CompanyAccountLog();
		        companyAccountLog.setId(UUID.get());
		        companyAccountLog.setCompanyId(companyAccount.getCompanyId());
		        companyAccountLog.setAmount(service.getFee());
		        companyAccountLog.setBalance(balanceAmount);
		        companyAccountLog.setServiceId(service.getId());
		        companyAccountLog.setTransType(5);// 提现拒绝
		        companyAccountLog.setCt(new Date());
		        companyAccountLogMapper.insertSelective(companyAccountLog);
			} else if(service.getServiceType()==0||service.getServiceType()==1){
				if(service.getPayType()==0){
					CompanyAccount companyAccount = this.selectByPrimaryKey(CompanyAccount.class,service.getCompanyId());
			        Integer balanceAmount = companyAccount.getBalance() + service.getFee();
			        companyAccount.setBalance(balanceAmount);
			        companyAccount.setEt(new Date());
			        companyAccount.setEb(service.getCb());
			        this.updateByPrimaryKeySelective(companyAccount, companyAccount.getCompanyId());
			        CompanyAccountLog companyAccountLog = new CompanyAccountLog();
			        companyAccountLog.setId(UUID.get());
			        companyAccountLog.setCompanyId(companyAccount.getCompanyId());
			        companyAccountLog.setAmount(service.getFee());
			        companyAccountLog.setBalance(balanceAmount);
			        companyAccountLog.setServiceId(service.getId());
			        companyAccountLog.setTransType(service.getServiceType()==0?6:7);// 包场、活动
			        companyAccountLog.setCt(new Date());
			        companyAccountLogMapper.insertSelective(companyAccountLog);
				}
			}
			this.updateByPrimaryKeySelective(service_, service_.getId());
			return;
		}
		//包场、活动
		service_.setStatus(0);
		if(serviceType==0||serviceType==1){
			if(service.getPayType()==0){
				service_.setStatus(4);
				this.createOrderItems(service.getId(), service.getServiceType(), service.getCompanyId());
			}else{
				service_.setFlowState(3);
			}
			//线下
			
		}else if(serviceType==3){//提现
			service_.setFlowState(2);
		}
		flow.setOperation("销售经理审核通过");
		companyServiceFlowMapper.insertSelective(flow);
		this.updateByPrimaryKeySelective(service_, service_.getId());
		if(service.getServiceType() == 1 && service.getPayType() == 0){
			//企业月赛事总数加1
			CompanyActivityStatistic statistic = this.searchCompanyActivityStatistic(service.getCompanyId(),1);
			if(statistic != null){
				statistic.setHoldTimes(statistic.getHoldTimes() +1);
				this.updateByPrimaryKeySelective(statistic,statistic.getId());
			}else{
				statistic = new CompanyActivityStatistic();
				statistic.setId(UUID.get());
				statistic.setCt(new Date());
				statistic.setCb(SessionUtil.currentUserId());
				statistic.setCompanyId(service.getCompanyId());
				statistic.setMonth(DateUtil.formatDate(new Date(),"yyyy-MM"));
				statistic.setType(1);
				statistic.setHoldTimes(1);
				statistic.setEt(new Date());
				this.insertSelective(statistic,statistic.getId());
			}
			CompanyInfo companyInfo = this.selectByPrimaryKey(CompanyInfo.class,service.getCompanyId());
			companyInfo.setActivityNum(statistic.getHoldTimes());
			this.updateByPrimaryKeySelective(companyInfo,companyInfo.getId());
		}
	}
	
	public List<CompanyServiceItem> getServiceItems(String serviceId){
		CompanyServiceItemCriteria criteria = new CompanyServiceItemCriteria();
		CompanyServiceItemCriteria.Criteria cri = criteria.createCriteria();
		cri.andServiceIdEqualTo(serviceId);
		criteria.setOrderByClause("man_flag desc");
		List<CompanyServiceItem> items = companyServiceItemMapper.selectByExample(criteria);
		return items;
	}
	
	public List<CompanyServiceFlow> getServiceFlows(String serviceId){
		CompanyServiceFlowCriteria criteria = new CompanyServiceFlowCriteria();
		CompanyServiceFlowCriteria.Criteria cri = criteria.createCriteria();
		cri.andServiceIdEqualTo(serviceId);
		criteria.setOrderByClause("handle_time");
		List<CompanyServiceFlow> items = companyServiceFlowMapper.selectByExample(criteria);
		return items;
	}
	
	public List<CompanyServicesVo> getServicesStatistic() throws Exception{
		CompanyServicesCriteria criteria = new CompanyServicesCriteria();
		CompanyServicesCriteria.Criteria cri = criteria.createCriteria();
		cri.andStatusEqualTo(4);
		List<Integer> types = new ArrayList<Integer>();
		types.add(0);
		types.add(1);
		cri.andServiceTypeIn(types);
		List<CompanyServices> services = companyServicesMapper.selectByExample(criteria);
		List<CompanyServicesVo> vos = new ArrayList<CompanyServicesVo>();
		CompanyServicesVo vo = null;
		for(CompanyServices service : services){
			vo = new CompanyServicesVo();
			org.springframework.beans.BeanUtils.copyProperties(service, vo);
			String companyId = service.getCompanyId();
			CompanyInfo info = this.selectByPrimaryKey(CompanyInfo.class, companyId);
			vo.setName(info.getName());
			vos.add(vo);
		}
		return vos;
	}
	
	/**
	 * 
	 * <创建公司账户><功能具体实现>
	 *
	 * @create：2016年3月9日 下午7:02:10
	 * @author： CYY
	 * @param companyId 企业id
	 */
	public void createCompanyAccount(String companyId)throws Exception{
	    CompanyAccount account = new CompanyAccount();
	    account.setCompanyId(companyId);
	    account.setCb(SessionUtil.currentUserId());
	    account.setEb(SessionUtil.currentUserId());
	    Date now = new Date();
	    account.setCt(now);
	    account.setEt(now);
	    account.setDefaultAmount(0);
	    account.setSetType(1);
	    this.insertSelective(account, account.getCompanyId());
	}
	
	public static void main(String[] args) {
	    MyGson.getInstance().fromJson("{\"1\":[10,12],\"5\":[17,19]}", new TypeToken<Map<Integer, List<Integer>>>(){}.getType());
    }
}
