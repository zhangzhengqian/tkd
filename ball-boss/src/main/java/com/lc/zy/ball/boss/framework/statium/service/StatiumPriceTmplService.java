package com.lc.zy.ball.boss.framework.statium.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.common.web.FunctionTag;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumPriceTmplVo;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumSpacePriceVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.OrderItemMapper;
import com.lc.zy.ball.domain.oa.mapper.SpacePriceMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumPriceTmplMapper;
import com.lc.zy.ball.domain.oa.po.Holiday;
import com.lc.zy.ball.domain.oa.po.Order;
import com.lc.zy.ball.domain.oa.po.OrderItem;
import com.lc.zy.ball.domain.oa.po.OrderItemCriteria;
import com.lc.zy.ball.domain.oa.po.StatiumPriceTmpl;
import com.lc.zy.ball.domain.oa.po.StatiumPriceTmplCriteria;
import com.lc.zy.ball.domain.oa.po.StatiumPriceTmplCriteria.Criteria;
import com.lc.zy.ball.domain.oa.po.StatiumSpace;
import com.lc.zy.ball.domain.oa.po.StatiumSpacePrice;
import com.lc.zy.ball.domain.oa.po.StatiumSpacePriceCriteria;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.MyGson;

@Service
@Transactional(readOnly = true)
public class StatiumPriceTmplService extends AbstractCacheService {

    @Autowired
    private StatiumPriceTmplMapper statiumPriceTmplMapper;

    @Autowired
    private StatiumSpacePriceService statiumSpacePriceService;
    
    @Autowired
    private OrderItemMapper orderItemMapper;
    
    @Autowired
    private SpacePriceMapper spacePriceMapper;

    private static Logger logger = LoggerFactory.getLogger(StatiumPriceTmplService.class);

    /**
     * 
     * <获取价格列表><功能具体实现>
     *
     * @create：2015年8月7日 下午8:57:26
     * @author： CYY
     * @param pageable
     * @param searchParams
     * @return
     */
    public Page<StatiumPriceTmpl> find(PageRequest pageable, Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        StatiumPriceTmplCriteria statiumPriceTmplCriteria = new StatiumPriceTmplCriteria();
        statiumPriceTmplCriteria.setMysqlLength(pageable.getPageSize());
        statiumPriceTmplCriteria.setMysqlOffset(pageable.getOffset());
        StatiumPriceTmplCriteria.Criteria cri = statiumPriceTmplCriteria.createCriteria();
        Criterias.bySearchFilter(cri, filters.values());
        statiumPriceTmplCriteria.setOrderByClause("ct desc");
        int total = statiumPriceTmplMapper.countByExample(statiumPriceTmplCriteria);
        List<StatiumPriceTmpl> list = statiumPriceTmplMapper.selectByExample(statiumPriceTmplCriteria);
        return new PageImpl<>(list, pageable, total);
    }

    /**
     * 
     * <保存价格><功能具体实现>
     *
     * @create：2015年8月4日 下午12:00:28
     * @author： CYY
     * @param po
     * @param userId
     * @return
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void save(StatiumPriceTmpl statiumPriceTmpl) throws Exception {
        this.insertSelective(statiumPriceTmpl, statiumPriceTmpl.getId());
    }

    /**
     * 
     * <返回场地价格模版的json><功能具体实现>
     *
     * @create：2015年8月11日 下午8:57:26
     * @author： CYY
     * @param statiumId
     * @param sportType
     * @return
     */
    public String spacePriceTmplToJson(String statiumId, String sportType) {
        try {
            String[] sportTypes = sportType.split(";;");
            List<String> sportList = Arrays.asList(sportTypes);
            StatiumPriceTmplCriteria sptc = new StatiumPriceTmplCriteria();
            Criteria cri = sptc.createCriteria();
            cri.andStatiumIdEqualTo(statiumId);
            cri.andSportTypeIn(sportList);
            sptc.setOrderByClause(" sport_type asc,is_workday asc, seq asc");
            List<StatiumPriceTmpl> statiumPriceTmplList = this.selectByExample(sptc);
            if (CollectionUtils.isNotEmpty(statiumPriceTmplList)) {
                List<StatiumPriceTmplVo> statiumPriceTmplVoList = new ArrayList<StatiumPriceTmplVo>();
                List<StatiumPriceTmpl> workingDaysList = null;
                List<StatiumPriceTmpl> noWorkingDaysList = null;
                StatiumPriceTmplVo statiumPriceTmplVo = null;
                for (String sport : sportTypes) {
                    workingDaysList = new ArrayList<StatiumPriceTmpl>();
                    noWorkingDaysList = new ArrayList<StatiumPriceTmpl>();
                    int subsidies = 0;
                    for (StatiumPriceTmpl tmpl : statiumPriceTmplList) {
                        if (sport.equals(tmpl.getSportType())) {
                            if (tmpl.getIsWorkday() == 1) {
                                workingDaysList.add(tmpl);
                            } else {
                                noWorkingDaysList.add(tmpl);
                            }
                            subsidies = tmpl.getSubsidies();
                        }
                    }
                    statiumPriceTmplVo = new StatiumPriceTmplVo();
                    statiumPriceTmplVo.setBallType(sport);
                    statiumPriceTmplVo.setBallName(FunctionTag.getDicItem(sport).getItemName());
                    statiumPriceTmplVo.setNoWorkingDays(noWorkingDaysList);
                    statiumPriceTmplVo.setWorkingDays(workingDaysList);
                    statiumPriceTmplVo.setSubsidies(subsidies);
                    statiumPriceTmplVoList.add(statiumPriceTmplVo);
                    logger.debug("spacePrice====>"+MyGson.getInstance().toJson(statiumPriceTmplVoList));
                }
                return MyGson.getInstance().toJson(statiumPriceTmplVoList);
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * <返回场地价格的json><功能具体实现>
     *
     * @create：2015年8月11日 下午8:57:26
     * @author： CYY
     * @param statiumId
     * @param spaceId
     * @param sportType
     * @return
     */
    @Transactional(readOnly = false)
    public String spacePriceToJson(String statiumId, String spaceId, String sportType) {
        try {
            StatiumSpacePriceCriteria spc = new StatiumSpacePriceCriteria();
            StatiumSpacePriceCriteria.Criteria cri = spc.createCriteria();
            cri.andSpaceIdEqualTo(spaceId);
            spc.setOrderByClause(" is_workday asc, seq asc");
            List<StatiumSpacePrice> statiumSpacePriceList = statiumSpacePriceService.selectByExample(spc);
            if (CollectionUtils.isNotEmpty(statiumSpacePriceList)) {
                return buildPriceJson(statiumSpacePriceList, sportType);
            } else {
                StatiumPriceTmplCriteria spt = new StatiumPriceTmplCriteria();
                StatiumPriceTmplCriteria.Criteria c = spt.createCriteria();
                c.andStatiumIdEqualTo(statiumId);
                c.andSportTypeEqualTo(sportType);
                spt.setOrderByClause(" sport_type asc,is_workday asc, seq asc");
                List<StatiumPriceTmpl> statiumPriceTmplList = this.selectByExample(spt);
                if (CollectionUtils.isNotEmpty(statiumPriceTmplList)) {
                    List<StatiumPriceTmpl> noWorkingDays = new ArrayList<StatiumPriceTmpl>();
                    List<StatiumPriceTmpl> workingDays = new ArrayList<StatiumPriceTmpl>();
                    for (StatiumPriceTmpl tmpl : statiumPriceTmplList) {
                        if (tmpl.getIsWorkday() == 1) {
                            workingDays.add(tmpl);
                        } else {
                            noWorkingDays.add(tmpl);
                        }
                    }
                    statiumSpacePriceService.saveStatiumPrice(workingDays, 1, spaceId, sportType);
                    statiumSpacePriceService.saveStatiumPrice(noWorkingDays, 0, spaceId, sportType);
                    return spacePriceToJson(statiumId, spaceId, sportType);
                } else {
                    return "";
                }
                // TODO : add by liangc : 如果是旧数据 即便保存了场馆价格，并生成了模板，那么也不会有场地价格，所以
                // statiumSpacePriceList 一定是空
                // 因为场地是在价格模板初始化之前创建的，所以，这里有两种情况
                // 第一种情况：场馆价格模板不存在，那么应该直接抛出异常，页面提醒操作员，先去保存并初始化价格模板，然后再来修改场地信息；
                // 第二种情况：场馆价格模板已经初始化，但由于场地是先于价格模板创建的，所以在这里要根据价格模板，初始化场地价格，然后再执行
                // 下面语句
                // TODO
                // statiumSpacePriceList =
                // statiumSpacePriceService.selectByExample(spc);
                // return buildPriceJson(statiumSpacePriceList,sportType);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    // add by liangc
    private String buildPriceJson(List<StatiumSpacePrice> statiumSpacePriceList, String sportType)throws Exception {
        List<StatiumSpacePriceVo> statiumSpacePriceVoList = new ArrayList<StatiumSpacePriceVo>();
        List<StatiumSpacePrice> workingDaysList = null;
        List<StatiumSpacePrice> noWorkingDaysList = null;
        StatiumSpacePriceVo statiumPriceTmplVo = null;
        workingDaysList = new ArrayList<StatiumSpacePrice>();
        noWorkingDaysList = new ArrayList<StatiumSpacePrice>();
        int subsidies = 0;
        for (StatiumSpacePrice price : statiumSpacePriceList) {
        	subsidies = findSubsidies(price.getSpaceId());
            if (price.getIsWorkday() == 1) {
                workingDaysList.add(price);
            } else {
                noWorkingDaysList.add(price);
            }
        }
        statiumPriceTmplVo = new StatiumSpacePriceVo();
        statiumPriceTmplVo.setBallType(sportType);
        statiumPriceTmplVo.setBallName(FunctionTag.getDicItem(sportType).getItemName());
        statiumPriceTmplVo.setNoWorkingDays(noWorkingDaysList);
        statiumPriceTmplVo.setWorkingDays(workingDaysList);
        statiumPriceTmplVo.setSubsidies(subsidies);
        statiumSpacePriceVoList.add(statiumPriceTmplVo);
        
        return MyGson.getInstance().toJson(statiumSpacePriceVoList);
    }
    
    /**
     * 
     * <根据场地Id获取补贴价><功能具体实现>
     *
     * @create：2015年10月22日 下午2:13:05
     * @author： liangsh
     * @param spaceId
     * @return
     * @throws Exception
     */
    private Integer findSubsidies(String spaceId)throws Exception{
    	StatiumSpace space = this.selectByPrimaryKey(StatiumSpace.class,spaceId);
    	if(space != null){
    		StatiumPriceTmplCriteria c = new StatiumPriceTmplCriteria();
        	StatiumPriceTmplCriteria.Criteria cri = c.createCriteria();
        	cri.andStatiumIdEqualTo(space.getStatiumId());
        	cri.andSportTypeEqualTo(space.getSportType());
        	List<StatiumPriceTmpl> list = this.selectByExample(c);
        	if(CollectionUtils.isNotEmpty(list)){
        		return list.get(0).getSubsidies();
        	}else{
        		return 0;
        	}
    	}else{
    		return 0;
    	}
    	
    }
    
    /**
     * 
     * <根据订单Id获取订单总补贴价><功能具体实现>
     *
     * @create：2015年10月22日 下午3:19:15
     * @author： liangsh
     * @param orderId
     * @return
     * @throws Exception
     */
	public Integer getSubsidiesByOrderId(String orderId)throws Exception{
    	Order order = this.selectByPrimaryKey(Order.class, orderId);
    	if(order.getSubsidies()==0){
    		return 0;
    	}
    	return (new BigDecimal(order.getSubsidies()).divide(new BigDecimal(100))).intValue();
    }
	
	/**
	 * 
	 * <更具订单id获取订单总成本><功能具体实现>
	 *
	 * @create：2015年10月22日 下午4:47:29
	 * @author： liangsh
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public Integer getCostPriceByOrderId(String orderId)throws Exception{
    	OrderItemCriteria c = new OrderItemCriteria();
    	OrderItemCriteria.Criteria  cri = c.createCriteria();
    	cri.andOrderIdEqualTo(orderId);
    	List<OrderItem> items = orderItemMapper.selectByExample(c);
    	BigDecimal totalCostPrice = new BigDecimal(0);
    	logger.debug("订单行，{}",items.size());
    	if(CollectionUtils.isNotEmpty(items)){
    		for (OrderItem orderItem : items) {
				int costPrice = orderItem.getCostPrice();
				logger.debug("成本价，{}",costPrice);
				totalCostPrice = totalCostPrice.add(new BigDecimal(costPrice));
			}
    	}
    	logger.debug("成本总价，{}",totalCostPrice);
    	if(totalCostPrice.intValue()!=0){
    		return (totalCostPrice.divide(new BigDecimal(100))).intValue();
    	}
    	return 0;
    }

    public Integer checkWorkday(String startDate) throws Exception {
        // 是否是工作日0：否，1： 是
        Integer isWorkday = 1;
        // holiday为null是工作日，不为null节假日
        Holiday holiday = this.selectByPrimaryKey(Holiday.class, startDate);
        if (holiday != null) {// 节假日
            isWorkday = 0;
        }
        return isWorkday;
    }
	
    /**
     * 
     * <获取场地价格模版列表><功能具体实现>
     *
     * @create：2015年8月11日 下午8:57:26
     * @author： CYY
     * @param statiumPriceTmplCriteria
     * @return
     */
    public List<StatiumPriceTmpl> selectByExample(StatiumPriceTmplCriteria statiumPriceTmplCriteria) {
        try {
            return statiumPriceTmplMapper.selectByExample(statiumPriceTmplCriteria);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * <删除场地价格模版列表><功能具体实现>
     *
     * @create：2015年8月11日 下午8:57:26
     * @author： CYY
     * @param statiumPriceTmplCriteria
     * @return
     */
    public int deleteByExample(StatiumPriceTmplCriteria statiumPriceTmplCriteria) {
        try {
            return statiumPriceTmplMapper.deleteByExample(statiumPriceTmplCriteria);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
