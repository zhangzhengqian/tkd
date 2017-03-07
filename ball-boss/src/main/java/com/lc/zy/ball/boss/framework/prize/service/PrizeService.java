package com.lc.zy.ball.boss.framework.prize.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.prize.vo.PrizeLaveVo;
import com.lc.zy.ball.boss.framework.prize.vo.StatiumPrizeVo;
import com.lc.zy.ball.boss.framework.prize.vo.UserPrizeByStatiumVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.Pageable;
import com.lc.zy.ball.domain.oa.mapper.PrizeConfigMapper;
import com.lc.zy.ball.domain.oa.mapper.PrizePoolMapper;
import com.lc.zy.ball.domain.oa.mapper.UserPrizeMapper;
import com.lc.zy.ball.domain.oa.po.PrizeConfig;
import com.lc.zy.ball.domain.oa.po.PrizeConfigCriteria;
import com.lc.zy.ball.domain.oa.po.StatiumDetail;
import com.lc.zy.ball.domain.oa.po.UserPrize;
import com.lc.zy.ball.domain.oa.po.UserPrizeCriteria;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.FreeMarkerUtils;

@Service
@Transactional(readOnly = true)
public class PrizeService extends AbstractCacheService {

    private static Logger logger = LoggerFactory.getLogger(PrizeService.class);
    
    @Autowired
    private PrizePoolMapper poolMapper;
    
    @Autowired
    private PrizeConfigMapper configMapper;
    
    @Autowired
    private UserPrizeMapper userPrizeMapper;
    
    @Resource(name = "oaJdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    
    public List<PrizeConfig> getConfig(String year,String month) throws Exception {
    	String date = year+month;
    	PrizeConfigCriteria criteria = new PrizeConfigCriteria();
    	PrizeConfigCriteria.Criteria cri = criteria.createCriteria();
    	cri.andLotteryMonthEqualTo(date);
    	List<PrizeConfig> configs = configMapper.selectByExample(criteria);
    	return configs;
    }
    
    @Transactional(readOnly = false)
    public void saveConfig(PrizeConfig config) throws Exception {
    	configMapper.insert(config);
    }
    
    @Transactional(readOnly = false)
    public void deleteConfig(String date) throws Exception {
    	PrizeConfigCriteria criteria = new PrizeConfigCriteria();
    	PrizeConfigCriteria.Criteria cri = criteria.createCriteria();
    	cri.andLotteryMonthEqualTo(date);
    	configMapper.deleteByExample(criteria);
    }
    
    public Set<String> getPrizeType(String lotteryMonth)throws Exception{
    	Set<String> set=new LinkedHashSet<>();
    	PrizeConfigCriteria c=new PrizeConfigCriteria();
    	PrizeConfigCriteria.Criteria cri=c.createCriteria();
    	cri.andLotteryMonthEqualTo(lotteryMonth);
    	List<PrizeConfig> list=configMapper.selectByExample(c);
    	for (PrizeConfig prizeConfig : list) {
			set.add(prizeConfig.getPrizeName());
		}
    	return set;
    }
    /**
     * 抽奖列表
     * @Title: getUserPrizeList
     * @author：wpengcheng
     * @param pageable
     * @param lotteryMonth
     * @return
     * @throws Exception
     * @return: Page<UserPrizeByStatiumVo>
     * @throws:
     */
	public Page<UserPrizeByStatiumVo> getUserPrizeList(Pageable pageable, String lotteryMonth) throws Exception {
		List<UserPrizeByStatiumVo> list=new ArrayList<UserPrizeByStatiumVo>();
		//筛选所选月份所有抽奖记录
		UserPrizeCriteria c=new UserPrizeCriteria();
		UserPrizeCriteria.Criteria cri=c.createCriteria();
		cri.andLotteryMonthEqualTo(StringUtils.isNotEmpty(lotteryMonth)?lotteryMonth:DateUtil.formatDate(new Date(), "yyyyMM"));
		List<UserPrize> prizeList=userPrizeMapper.selectByExample(c);
		//筛选所选月份所有参与抽奖的场馆id
		Set<String> statiumSet=new HashSet<>();
		for (UserPrize userPrize : prizeList) {
			statiumSet.add(userPrize.getStatiumId());
		}
		//根据场馆id分别统计
		for (String statiumId : statiumSet) {
			StatiumDetail statiumDetail=this.selectByPrimaryKey(StatiumDetail.class, statiumId);
			UserPrizeByStatiumVo userPrizeByStatiumVo=new UserPrizeByStatiumVo();
			List<Integer> numList=new ArrayList<>();
			Map<String, String> params=new HashMap<>();
			params.put("statiumId", statiumId);
			String sql=FreeMarkerUtils.format("/template/crm_statium/crm_statium_lotteryPrize.ftl", params);
			List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
			Set<String> set=new LinkedHashSet<>();
			PrizeConfigCriteria cf=new PrizeConfigCriteria();
	    	PrizeConfigCriteria.Criteria crif=cf.createCriteria();
	    	crif.andLotteryMonthEqualTo(lotteryMonth);
	    	List<PrizeConfig> configs=configMapper.selectByExample(cf);
	    	for (PrizeConfig prizeConfig : configs) {
				set.add(prizeConfig.getPrizeType());
			}
			for (String type : set) {
				int i=0;
				if (StringUtils.isNotEmpty(type)) {
					for (Map<String, Object> map : mapList) {
						if (map!=null&&map.get("prizeType")!=null&&type.equals(map.get("prizeType").toString())) {
							i++;
							numList.add(Integer.parseInt(map.get("amount").toString()));
						}
					}
					if (i==0) {
						numList.add(0);
					}
				}
			}
			userPrizeByStatiumVo.setPrizeNum(numList);
			userPrizeByStatiumVo.setStatiumId(statiumId);
			if (statiumDetail!=null) {
				userPrizeByStatiumVo.setStatiumName(statiumDetail.getName());
			}
			list.add(userPrizeByStatiumVo);
		}
		List<UserPrizeByStatiumVo> listNew=new ArrayList<>();
		for (int i=pageable.getPageNumber()*pageable.getPageSize();i<(pageable.getPageNumber()+1)*pageable.getPageSize()&&i<list.size();i++) {
			listNew.add(list.get(i));
		}
		return new PageImpl<>(listNew,pageable,statiumSet.size());
	}
	/**
	 * 场馆奖品详情
	 * @Title: getStatiumPrizeList
	 * @author：wpengcheng
	 * @param pageable
	 * @param statiumId
	 * @param lotteryMonth
	 * @return
	 * @throws Exception
	 * @return: Page<StatiumPrizeVo>
	 * @throws:
	 */
	public Page<StatiumPrizeVo> getStatiumPrizeList(Pageable pageable,String statiumId,String lotteryMonth)throws Exception {
		UserPrizeCriteria c=new UserPrizeCriteria();
		UserPrizeCriteria.Criteria cri=c.createCriteria();
		cri.andStatiumIdEqualTo(statiumId).andLotteryMonthEqualTo(lotteryMonth);
		c.setMysqlLength(pageable.getPageSize());
        c.setMysqlOffset(pageable.getOffset());
		c.setOrderByClause("ct desc");
		List<UserPrize> prizeList=userPrizeMapper.selectByExample(c);
		int total=userPrizeMapper.countByExample(c);
		List<StatiumPrizeVo> voList=new ArrayList<>();
		for (UserPrize userPrize : prizeList) {
			StatiumPrizeVo vo=new StatiumPrizeVo();
			vo.setTime(DateUtil.formatDate(userPrize.getCt(), "yyyy.MM.dd HH:mm"));
			if ("hongbao".equals(userPrize.getPrizeType())) {
				vo.setPrize(userPrize.getPrizeName()+userPrize.getAmount()+"元");
			}else{
				vo.setPrize(userPrize.getPrizeName());
			}
			voList.add(vo);
		}
		return new PageImpl<>(voList,pageable,total);
	}
	/**
	 * 奖品剩余
	 * @Title: getPrizeLave
	 * @author：wpengcheng
	 * @param year
	 * @param month
	 * @return
	 * @return: List<PrizeLaveVo>
	 * @throws:
	 */
	public List<PrizeLaveVo> getPrizeLave(String year, String month) {
    	List<PrizeLaveVo> voList=new ArrayList<>();
		String lotteryMonth=year+month;
		Map<String, String> params=new HashMap<>();
		params.put("lotteryMonth", lotteryMonth);
		String sql=FreeMarkerUtils.format("/template/crm_statium/crm_statium_prizeLave.ftl", params);
		List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> map : mapList) {
			if (map!=null&&StringUtils.isNotEmpty(map.get("prize_type").toString())) {
				PrizeLaveVo vo=new PrizeLaveVo();
				vo.setPrizeType(map.get("prize_type").toString());
				vo.setPrizeName(map.get("prize_name").toString()+("0".equals(map.get("money").toString())?"":map.get("money").toString()));
				if ("hongbao".equals(map.get("prize_type").toString())) {
					PrizeConfigCriteria c=new PrizeConfigCriteria();
					PrizeConfigCriteria.Criteria cri=c.createCriteria();
					cri.andPrizeTypeEqualTo(map.get("prize_type").toString());
					cri.andMoneyEqualTo(Integer.parseInt(map.get("money").toString()));
					if (configMapper.selectByExample(c)!=null&&configMapper.selectByExample(c).size()!=0) {
						vo.setAmount(configMapper.selectByExample(c).get(0).getAmount());
					}
				}else {
					PrizeConfigCriteria c=new PrizeConfigCriteria();
					PrizeConfigCriteria.Criteria cri=c.createCriteria();
					cri.andPrizeTypeEqualTo(map.get("prize_type").toString());
					if (configMapper.selectByExample(c)!=null&&configMapper.selectByExample(c).size()!=0) {
						vo.setAmount(configMapper.selectByExample(c).get(0).getAmount());
					}
				}
				int lottery=0;
				if (map.get("lottery")!=null) {
					lottery=Integer.parseInt(map.get("lottery").toString());
				}
				vo.setLave(vo.getAmount()-lottery);
				voList.add(vo);
			}
		}
		return voList;
	}
}
