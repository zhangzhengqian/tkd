package com.lc.zy.ball.boss.common.web;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;

import com.lc.zy.ball.domain.oa.mapper.*;
import com.lc.zy.ball.domain.oa.po.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lc.zy.ball.boss.common.Zonemap;
import com.lc.zy.ball.boss.common.service.CommonService;
import com.lc.zy.ball.boss.framework.orders.vo.OrderBillItemVo;
import com.lc.zy.common.Constants;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.MyGson;
/**
 * 公共方法
 * @author liangc
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController extends AbstractController {
	
	private static Logger logger = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	private CommonService commonService = null;
	@Autowired 
	private StatiumDetailMapper statiumDetailMapper = null;
	@Autowired
	private CompanyInfoMapper companyInfoMapper;
	@Autowired
	private StatiumSpaceMapper statiumSpaceMapper;
	@Autowired
	private OrderMapper orderMapper = null;
	@Resource(name="oaJdbcTemplate")
	private JdbcTemplate jdbc = null;
	@Autowired
	private OrderBillItemMapper orderBillItemMapper;
	@Autowired
	private OrderBillMapper orderBillMapper;
	@Autowired
	private StatiumPriceTmplMapper statiumPriceTmplMapper;
	@Autowired
	private SpacePriceMapper spacePriceMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private SsoUserMapper ssoUserMapper;
	@Autowired
	private EnjoyGameSiteMapper enjoyGameSiteMapper;
	@Autowired
	private VoteThemeMapper voteThemeMapper;
	
	@RequestMapping(value="search_org_dlg",method=RequestMethod.GET)
	public String search_org_dlg(Model model, ServletRequest request) throws Exception {
		String orgTree = commonService.getOrgTreeOfCurrentUser();
		logger.debug("search_org_dlg : {}",orgTree);	
		model.addAttribute("orgTree",orgTree);
		return "common/search_org_dlg";
	}
	
	@RequestMapping(value="search_statium_by_name")
	@ResponseBody
	public String search_statium_by_name(boolean flag,String q,int limit, ServletRequest request) throws Exception {
		logger.debug(q);
		StatiumDetailCriteria statiumDetailCriteria = new StatiumDetailCriteria();
		statiumDetailCriteria.setMysqlOffset(0);
		statiumDetailCriteria.setMysqlOffset(limit);
		statiumDetailCriteria.setOrderByClause("name asc");
		StatiumDetailCriteria.Criteria cri = statiumDetailCriteria.createCriteria();
		cri.andNameLike("%" + q + "%");
		if(flag){
			cri.andDeleteFlagIsNull();	
		}
		List<StatiumDetail> l = statiumDetailMapper.selectByExample(statiumDetailCriteria);
		List<StatiumDetail> rl = new ArrayList<StatiumDetail>();
		for(StatiumDetail po : l){
			StatiumDetail vo = new StatiumDetail();
			vo.setName(po.getName());
			vo.setAddress(po.getAddress());
			vo.setId(po.getId());
			if(StringUtils.isNotBlank(po.getAreaCode())){
				Map<String,String> zoneMap = Zonemap.split(po.getAreaCode());
				vo.setArea(zoneMap.get("province")+zoneMap.get("city"));
			}
			rl.add(vo);
		}
		if(rl!=null){
			String r = MyGson.getInstance().toJson(rl);
			logger.debug(r);
			return r;
		}
		return null;
	}
	
	@RequestMapping(value="search_company_by_name")
	@ResponseBody
	public String search_company_by_name(String q,int limit, ServletRequest request) throws Exception {
		logger.debug(q);
		CompanyInfoCriteria criteria = new CompanyInfoCriteria();
		criteria.setMysqlOffset(0);
		criteria.setMysqlOffset(limit);
		criteria.setOrderByClause("name asc");
		CompanyInfoCriteria.Criteria cri = criteria.createCriteria();
		cri.andNameLike("%" + q + "%");
		List<CompanyInfo> l = companyInfoMapper.selectByExample(criteria);
		List<CompanyInfo> rl = new ArrayList<CompanyInfo>();
		for(CompanyInfo po : l){
			CompanyInfo vo = new CompanyInfo();
			vo.setName(po.getName());
			vo.setId(po.getId());
			rl.add(vo);
		}
		if(rl!=null){
			String r = MyGson.getInstance().toJson(rl);
			logger.debug(r);
			return r;
		}
		return null;
	}
	
	@RequestMapping(value="search_statium_dlg")
	public String search_statium_dlg(Model model,String name,ServletRequest request){
		logger.debug("search_statium_dlg : {}","");
		return "common/search_statium_dlg";
	}
	
	@RequestMapping(value="init_resume__dlg")
	public String initResume(Model model,String name,ServletRequest request){
		logger.debug("search_statium_dlg : {}","");
		return "common/init_resume__dlg";
	}
	
	@RequestMapping(value="dialog_statium_by_name")
	@ResponseBody
	public String dialog_statium_by_name(String name, ServletRequest request) throws Exception {
		StatiumDetailCriteria statiumDetailCriteria = new StatiumDetailCriteria();
		statiumDetailCriteria.setMysqlOffset(0);
		statiumDetailCriteria.setMysqlLength(30);
		statiumDetailCriteria.setOrderByClause("name asc");
		StatiumDetailCriteria.Criteria cri = statiumDetailCriteria.createCriteria();
		cri.andNameLike("%" + name + "%");
		List<StatiumDetail> l = statiumDetailMapper.selectByExample(statiumDetailCriteria);
		List<StatiumDetail> rl = new ArrayList<StatiumDetail>();
		for(StatiumDetail po : l){
			StatiumDetail vo = new StatiumDetail();
			vo.setId(po.getId());
			vo.setName(po.getName());
			vo.setAddress(po.getAddress());
			vo.setArea(Zonemap.toString(po.getAreaCode()));
			rl.add(vo);
		}
		if(rl.size()>0){
			String r = MyGson.getInstance().toJson(rl);
			logger.debug(r);
			return r;
		}
		return null;
	}
	@RequestMapping(value="search_order_dlg")
	public String getRegionOrders(Model model, String billId, ServletRequest request) throws Exception{
		OrderBillItemCriteria billCriteria = new OrderBillItemCriteria();
		OrderBillItemCriteria.Criteria criteria = billCriteria.createCriteria();
		criteria.andOrderBillIdEqualTo(billId);
		billCriteria.setOrderByClause("start_date");
		List<OrderBillItem> items = orderBillItemMapper.selectByExample(billCriteria);
		BigDecimal totalFee = new BigDecimal(0);
		BigDecimal totalSubsidy = new BigDecimal(0);
		BigDecimal totalOrder = new BigDecimal(0);
		for(OrderBillItem item :items){
			totalFee = totalFee.add(new BigDecimal(item.getTotalFee()));
			totalOrder = totalOrder.add(new BigDecimal(item.getOrderCount()));
			totalSubsidy = totalSubsidy.add(new BigDecimal(item.getSubsidyAmount()));
		}
		model.addAttribute("totalFee",totalFee.toString());
		model.addAttribute("totalSubsidy",totalSubsidy.toString());
		model.addAttribute("totalOrder",totalOrder.toString());
		model.addAttribute("items",items);
		
		return "common/init_orderBill_dlg";
	}
	
	@RequestMapping(value="getOrders")
	@ResponseBody
	public String getRegionOrder(Model model, int subsidy,String startDate,String endDate,String statiumId, ServletRequest request) throws Exception{
		OrderBillCriteria billCriteria = new OrderBillCriteria();
		OrderBillCriteria.Criteria criteria = billCriteria.createCriteria();
		criteria.andStatiumIdEqualTo(statiumId);
		billCriteria.setOrderByClause("end_date desc");
		List<OrderBill> bills = orderBillMapper.selectByExample(billCriteria);
		Map<String,String> result = new HashMap<String, String>();
		if(CollectionUtils.isNotEmpty(bills)){
			int totalEnd = bills.get(0).getEndDate();
			try {
				Date start = org.apache.commons.lang3.time.DateUtils.parseDate(startDate.substring(0,10), "yyyy-MM-dd");
				int startNew = new BigDecimal(start.getTime()).divide(new BigDecimal(100000)).intValue();
				if(startNew<=totalEnd){
					result.put("error", "");
					result.put("message", "结账日期不连续");
					return MyGson.getInstance().toJson(result);
				}
			} catch (ParseException e) {
				logger.error(e.getMessage(), e);
			}
			
		}
		
		StatiumDetail statium = commonService.selectByPrimaryKey(StatiumDetail.class, statiumId);
		String sportType = statium.getSportType();
		String sportTypes[] = sportType.split(";;");
		Map<String,Integer> subsidieMap = new HashMap<String, Integer>();
		Map<String,List<Integer>> costMap_work = new HashMap<String, List<Integer>>();
		Map<String,List<Integer>> costMap_noWork = new HashMap<String, List<Integer>>();
		//场馆按类型的补贴价
		for(String type:sportTypes){
			StatiumPriceTmplCriteria statiumPriceTmplCriteria = new StatiumPriceTmplCriteria();
        	StatiumPriceTmplCriteria.Criteria statiumPriceTmplCri = statiumPriceTmplCriteria.createCriteria();
        	statiumPriceTmplCri.andStatiumIdEqualTo(statiumId);
        	statiumPriceTmplCri.andSportTypeEqualTo(type);
        	List<StatiumPriceTmpl> statiumPriceTmpls = statiumPriceTmplMapper.selectByExample(statiumPriceTmplCriteria);
        	int subsidies = statiumPriceTmpls.get(0).getSubsidies();
        	subsidieMap.put(type, subsidies);
		}
		//场馆按场地的成本价 工作日
		SpacePriceCriteria spacePriceCriteria = new SpacePriceCriteria();
		SpacePriceCriteria.Criteria spacePriceCri = spacePriceCriteria.createCriteria();
		spacePriceCri.andStatiumIdEqualTo(statiumId);
		spacePriceCri.andIsWorkdayEqualTo(1);
		List<SpacePrice> prices_work = spacePriceMapper.selectByExample(spacePriceCriteria);
		for(SpacePrice sp:prices_work){
			if(StringUtils.isEmpty(sp.getCostPrice())){
				continue;
			}
			String[] prices = sp.getCostPrice().split(",",-1);
            List<Integer> newPrice = new ArrayList<Integer>();
            for (int i = 0; i < prices.length; i++) {
            	int price = 0;
                if (!"".equals(prices[i])&&!"0".equals(prices[i])) {
                	price = Integer.parseInt(prices[i]) / 100;
                }
                newPrice.add(price);
            }
            costMap_work.put(sp.getSpaceId(), newPrice);
		}
		//场馆按场地的成本价 非工作日
		spacePriceCriteria = new SpacePriceCriteria();
		SpacePriceCriteria.Criteria spacePriceCri_ = spacePriceCriteria.createCriteria();
		spacePriceCri_.andIsWorkdayEqualTo(0);
		List<SpacePrice> prices_noWork = spacePriceMapper.selectByExample(spacePriceCriteria);
		for(SpacePrice sp:prices_noWork){
			if(StringUtils.isEmpty(sp.getCostPrice())){
				continue;
			}
			String[] prices = sp.getCostPrice().split(",",-1);
            List<Integer> newPrice = new ArrayList<Integer>();
            for (int i = 0; i < prices.length; i++) {
            	int price = 0;
                if (!"".equals(prices[i])&&!"0".equals(prices[i])) {
                	price = Integer.parseInt(prices[i]) / 100;
                }
                newPrice.add(price);
            }
            costMap_noWork.put(sp.getSpaceId(), newPrice);
		}
		
		OrderCriteria orderCriteria = new OrderCriteria();
		OrderCriteria.Criteria orderCri = orderCriteria.createCriteria();
		orderCri.andStatiumIdEqualTo(statiumId);
		orderCri.andEtBetween(DateUtil.parse(startDate, "yyyy-MM-dd HH:mm:ss", null), DateUtil.parse(endDate, "yyyy-MM-dd HH:mm:ss", null));
		orderCri.andStatusEqualTo(Constants.OrderStatus.PLAYING);
		List<Order> orders = orderMapper.selectByExample(orderCriteria);
		
		Map<String,OrderBillItemVo> results = new HashMap<String, OrderBillItemVo>();
		OrderBillItemVo vo = null;
		BigDecimal totalOrders = new BigDecimal(orders.size());
		for(Order order:orders){
			Date et = order.getEt();
			String perDate = DateUtil.formatDate(et, "yyyy-MM-dd");
			OrderItemCriteria orderItemCriteria = new OrderItemCriteria();
			OrderItemCriteria.Criteria orderItemCri = orderItemCriteria.createCriteria();
			orderItemCri.andOrderIdEqualTo(order.getId());
			List<OrderItem> orderItems = orderItemMapper.selectByExample(orderItemCriteria);
			OrderItem item = orderItems.get(0);
			//补贴
			int sub = subsidieMap.get(item.getSportType());
			//成本价
			int workDay = this.checkWorkday(DateUtil.formatDate(item.getStartDate(), "yyyy-MM-dd"));
			BigDecimal cost = new BigDecimal(0);
			for(OrderItem item_:orderItems){
				Date startTime = new Date(item_.getStartTime() * 1000L);
				Long startHour = DateUtils.getFragmentInHours(startTime, Calendar.DATE);
				List<Integer> costs = null;
				if(workDay==1){
					costs = costMap_work.get(item_.getSpaceId());
				}else{
					costs = costMap_noWork.get(item_.getSpaceId());
				}
				if(CollectionUtils.isNotEmpty(costs)){
					cost = cost.add(new BigDecimal(costs.get(startHour.intValue())));
				}else{
					cost = cost.add(new BigDecimal(0));
				}
			}
			
			if(results.containsKey(perDate)){
				vo = results.get(perDate);
				vo.setOrderCount(vo.getOrderCount()+1);
				int totalFee = vo.getTotalFee();
				totalFee = ((new BigDecimal(order.getFee()).divide(new BigDecimal(100))).add(new BigDecimal(totalFee))).intValue();
				int totalFinalFee = vo.getTotalFinalFee();
				totalFinalFee = ((new BigDecimal(order.getFinalFee()).divide(new BigDecimal(100))).add(new BigDecimal(totalFinalFee))).intValue();
				vo.setTotalFee(totalFee);
				vo.setTotalFinalFee(totalFinalFee);
				
				int subsidyNum = vo.getSubsidy();
				subsidyNum = ((new BigDecimal(subsidyNum)).add(new BigDecimal(sub))).intValue();
				vo.setSubsidy(subsidyNum);
				
				int costFee = vo.getCostFee();
				costFee = ((new BigDecimal(costFee)).add(cost)).intValue();
				vo.setCostFee(costFee);
				
			}else{
				vo = new OrderBillItemVo();
				vo.setOrderCount(1);
				vo.setTotalFee((new BigDecimal(order.getFee()).divide(new BigDecimal(100))).intValue());
				vo.setTotalFinalFee((new BigDecimal(order.getFinalFee()).divide(new BigDecimal(100))).intValue());
				vo.setPerDate(perDate);
				vo.setCostFee(cost.intValue());
				vo.setSubsidy(sub);
				results.put(perDate, vo);
			}
		}
		Date date0 = DateUtil.parse(startDate, null);
		Date date1 = DateUtil.parse(endDate, null);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date0);
		List<OrderBillItemVo> resultView = new ArrayList<OrderBillItemVo>();
		BigDecimal orderCount = totalOrders;
		BigDecimal totalFinalFee = new BigDecimal(0);
		BigDecimal totalFee = new BigDecimal(0);
		BigDecimal subsidyTotal = new BigDecimal(0);
		BigDecimal costFee = new BigDecimal(0); 
		while(cal.getTime().compareTo(date1)<=0){
			String currentDate = DateUtil.formatDate(cal.getTime(), "yyyy-MM-dd");
			if(results.containsKey(currentDate)){
				vo = results.get(currentDate);
				totalFinalFee = totalFinalFee.add(new BigDecimal(vo.getTotalFinalFee()));
				totalFee = totalFee.add(new BigDecimal(vo.getTotalFee()));
				subsidyTotal = subsidyTotal.add(new BigDecimal(vo.getSubsidy()));
				costFee = costFee.add(new BigDecimal(vo.getCostFee()));
				resultView.add(results.get(currentDate));
			}else{
				vo = new OrderBillItemVo();
				vo.setOrderCount(0);
				vo.setTotalFee(0);
				vo.setTotalFinalFee(0);
				vo.setPerDate(currentDate);
				vo.setSubsidy(0);
				vo.setCostFee(0);
				resultView.add(vo);
			}
			cal.add(Calendar.DAY_OF_MONTH,1);
		}
		Collections.sort(resultView, new Comparator<OrderBillItemVo>() {
			@Override
			public int compare(OrderBillItemVo o1, OrderBillItemVo o2) {
				return o1.getPerDate().compareTo(o2.getPerDate());
			}
		});
		vo = new OrderBillItemVo();
		vo.setPerDate("总计");
		vo.setOrderCount(orderCount.intValue());
		vo.setTotalFinalFee(totalFinalFee.intValue());
		vo.setTotalFee(totalFee.intValue());
		vo.setSubsidy(subsidyTotal.intValue());
		vo.setCostFee(costFee.intValue());
		resultView.add(vo);
		return MyGson.getInstance().toJson(resultView);
	}
	
	@RequestMapping(value="init_balanceOrder_dlg")
	public String initBalanceOrder(Model model,String name,ServletRequest request)throws Exception{
		
		return "common/init_balanceOrder_dlg";
	}
	@RequestMapping(value="batch_query_dlg")
	public String batchQuerySpace(Model model,String statiumId,ServletRequest request)throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<StatiumSpace> spaces = new ArrayList<StatiumSpace>();
			StatiumSpaceCriteria spaceCriteria = new StatiumSpaceCriteria();
			StatiumSpaceCriteria.Criteria cri = spaceCriteria.createCriteria();
			cri.andStatiumIdEqualTo(statiumId);
			spaces = statiumSpaceMapper.selectByExample(spaceCriteria);
			model.addAttribute("spaces", spaces);
			model.addAttribute("statiumId", statiumId);
			model.addAttribute("action", "detail");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查看场地失败: {}", e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "查看场地失败");
		}
		return "common/batch_search_dlg";
	}
	
	@RequestMapping(value="qiuyou_query_dlg")
	public String queryQiuyou(Model model,ServletRequest request)throws Exception{
		try {
			SsoUserCriteria criteria = new SsoUserCriteria();
			criteria.setMysqlOffset(0);
			criteria.setMysqlLength(20);
			List<SsoUser> users = ssoUserMapper.selectByExample(criteria);
			model.addAttribute("users", users);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		}
		return "corps/qiuyou_query_dlg";
	}
	
	@RequestMapping(value="ajax_qiuyou_query_dlg")
	@ResponseBody
	public String ajaxQiuyouQuery(Model model,String username,String phone,ServletRequest request)throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			SsoUserCriteria criteria = new SsoUserCriteria();
			SsoUserCriteria.Criteria cri = criteria.createCriteria();
			
			if(StringUtils.isNotBlank(username)){
				cri.andNickNameLike(username);
			}
			if(StringUtils.isNotBlank(phone)){
				cri.andPhoneEqualTo(phone);
			}
			if(StringUtils.isBlank(username)&&StringUtils.isBlank(phone)){
				criteria.setMysqlOffset(0);
				criteria.setMysqlLength(20);
			}
			List<SsoUser> users = ssoUserMapper.selectByExample(criteria);
			result.put(Constants.Result.RESULT, true);
			result.put(Constants.Result.DATA, users);
			return MyGson.getInstance().toJson(result);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "查询球友圈用户失败！");
			return MyGson.getInstance().toJson(result);
		}
	}
	
	private Integer checkWorkday(String startDate) throws Exception {
        // 是否是工作日0：否，1： 是
        Integer isWorkday = 1;
        // holiday为null是工作日，不为null节假日
        Holiday holiday = commonService.selectByPrimaryKey(Holiday.class, startDate);
        if (holiday != null) {// 节假日
            isWorkday = 0;
        }
        return isWorkday;
    }
	
	@RequestMapping(value="search_statium_by_name_from_site")
	@ResponseBody
	public String search_statium_by_name_from_site(boolean flag,String q,int limit, ServletRequest request) throws Exception {
		logger.debug(q);
		EnjoyGameSiteCriteria enjoyGameSiteCriteria = new EnjoyGameSiteCriteria();
		EnjoyGameSiteCriteria.Criteria siteCri = enjoyGameSiteCriteria.createCriteria();
		Calendar a=Calendar.getInstance();
		siteCri.andYearEqualTo(String.valueOf(a.get(Calendar.YEAR)));
		List<String> statiumList = new ArrayList<String>();
        if(enjoyGameSiteMapper.countByExample(enjoyGameSiteCriteria) > 0){
        	List<EnjoyGameSite> siteList = enjoyGameSiteMapper.selectByExample(enjoyGameSiteCriteria);
        	for (EnjoyGameSite enjoyGameSite : siteList) {
        		statiumList.add(enjoyGameSite.getStatiumId());
			}
        }else{
        	return null;
        }
		StatiumDetailCriteria statiumDetailCriteria = new StatiumDetailCriteria();
		statiumDetailCriteria.setMysqlOffset(0);
		statiumDetailCriteria.setMysqlOffset(limit);
		statiumDetailCriteria.setOrderByClause("name asc");
		StatiumDetailCriteria.Criteria cri = statiumDetailCriteria.createCriteria();
		cri.andIdIn(statiumList);
		cri.andNameLike("%" + q + "%");
		if(flag){
			cri.andDeleteFlagIsNull();	
		}
		List<StatiumDetail> l = statiumDetailMapper.selectByExample(statiumDetailCriteria);
		List<StatiumDetail> rl = new ArrayList<StatiumDetail>();
		for(StatiumDetail po : l){
			StatiumDetail vo = new StatiumDetail();
			vo.setName(po.getName());
			vo.setAddress(po.getAddress());
			EnjoyGameSiteCriteria enjoyGameSiteCriteria2 = new EnjoyGameSiteCriteria();
			EnjoyGameSiteCriteria.Criteria siteCri2 = enjoyGameSiteCriteria2.createCriteria();
			siteCri2.andYearEqualTo(String.valueOf(a.get(Calendar.YEAR)));
			siteCri2.andStatiumIdEqualTo(po.getId());
			if(enjoyGameSiteMapper.countByExample(enjoyGameSiteCriteria2) > 0){
				vo.setId(po.getId()+"_"+enjoyGameSiteMapper.selectByExample(enjoyGameSiteCriteria2).get(0).getId()+"_"+po.getAddress());
				if(StringUtils.isNotBlank(po.getAreaCode())){
					Map<String,String> zoneMap = Zonemap.split(po.getAreaCode());
					vo.setArea(zoneMap.get("province")+zoneMap.get("city"));
				}
				rl.add(vo);
			}
		}
		if(rl!=null){
			String r = MyGson.getInstance().toJson(rl);
			logger.debug(r);
			return r;
		}
		return null;
	}

	@RequestMapping(value="search_voteTheme_by_name")
	@ResponseBody
	public String search_voteTheme_by_name(boolean flag,String q,int limit, ServletRequest request) throws Exception {
		logger.debug(q);
		VoteThemeCriteria voteThemeC = new VoteThemeCriteria();
		voteThemeC.setMysqlOffset(0);
		voteThemeC.setMysqlOffset(limit);
		voteThemeC.setOrderByClause("name asc");
		VoteThemeCriteria.Criteria cri = voteThemeC.createCriteria();
		cri.andTitleLike("%" + q + "%");
		if(voteThemeMapper.countByExample(voteThemeC) > 0){
	     	List<VoteTheme> l = voteThemeMapper.selectByExample(voteThemeC);
			String r = MyGson.getInstance().toJson(l);
			logger.debug(r);
			return r;
		}
		return null;
	}
}
