package com.lc.zy.ball.boss.framework.orders.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.orders.vo.BillPrice;
import com.lc.zy.ball.boss.framework.orders.vo.BillTermVo;
import com.lc.zy.ball.boss.framework.orders.vo.OrderBillVo;
import com.lc.zy.ball.boss.framework.orders.vo.StatiumOrderBill;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.BillTermMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderBillItemMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderBillMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderItemMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumPriceTmplMapper;
import com.lc.zy.ball.domain.oa.po.BillTerm;
import com.lc.zy.ball.domain.oa.po.BillTermCriteria;
import com.lc.zy.ball.domain.oa.po.Holiday;
import com.lc.zy.ball.domain.oa.po.Order;
import com.lc.zy.ball.domain.oa.po.OrderBill;
import com.lc.zy.ball.domain.oa.po.OrderBillCriteria;
import com.lc.zy.ball.domain.oa.po.OrderBillItem;
import com.lc.zy.ball.domain.oa.po.OrderBillItemCriteria;
import com.lc.zy.ball.domain.oa.po.OrderCriteria;
import com.lc.zy.ball.domain.oa.po.OrderItem;
import com.lc.zy.ball.domain.oa.po.OrderItemCriteria;
import com.lc.zy.ball.domain.oa.po.StatiumDetail;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.common.mq.bean.OrdeNotifyrMessage;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.FreeMarkerUtils;
import com.lc.zy.common.util.MessageUtil;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.OrderNotifyUtil;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.util.Zonemap;

@Service
public class BillService  extends AbstractCacheService {
	
	private final static String SPLIT = "\01";
	@Autowired
	private BillTermMapper billTermMapper;
	@Autowired
	private OrderBillMapper billMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private StatiumPriceTmplMapper statiumPriceTmplMapper;
	@Resource(name = "oaJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private OrderBillItemMapper billItemMapper;
	@Autowired
	private OrderNotifyUtil orderNotifyUtil;
	@Autowired
	private MessageUtil messageUtil;
	
	private static Logger logger = LoggerFactory.getLogger(BillService.class);
	
	public Page<BillTermVo> find(int page, int size) throws Exception {
		PageRequest pageable = new PageRequest(page, size);
		BillTermCriteria billTermCriteria = new BillTermCriteria();
		int total = billTermMapper.countByExample(billTermCriteria);
		billTermCriteria.setMysqlLength(pageable.getPageSize());
		billTermCriteria.setMysqlOffset(pageable.getOffset());
		billTermCriteria.setOrderByClause("id desc");
		List<BillTerm> billTerms = billTermMapper.selectByExample(billTermCriteria);
		List<BillTermVo> vos = new ArrayList<BillTermVo>();
		BillTermVo vo = null;
		for(BillTerm term : billTerms){
			vo = new BillTermVo();
			BeanUtils.copyProperties(term, vo);
			String id = term.getId();
			String start = id.substring(0,8);
			String end = id.substring(8,16);
			vo.setStartTime(DateUtil.formatDate(new Date(Long.parseLong(start)*100000L), "yyyy-MM-dd"));
			vo.setEndTime(DateUtil.formatDate(new Date(Long.parseLong(end)*100000L), "yyyy-MM-dd"));
			boolean flag = this.availableDel(id);
			vo.setDel(flag);
			vos.add(vo);
		}
		return new PageImpl<BillTermVo>(vos, pageable, total);
	}
	
	private boolean availableDel(String id){
		OrderBillCriteria billCriteria = new OrderBillCriteria();
		OrderBillCriteria.Criteria billCri = billCriteria.createCriteria();
		billCri.andBillIdEqualTo(id);
		billCri.andStatusNotEqualTo(0);
		int cont = billMapper.countByExample(billCriteria);
		if(cont>0){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean checkBillTerm(String startDate,String endDate,int ordersType) throws Exception{
		BillTermCriteria billCriteria = new BillTermCriteria();
		BillTermCriteria.Criteria billCri = billCriteria.createCriteria();
		billCri.andTypeEqualTo(ordersType);
		Date start = org.apache.commons.lang3.time.DateUtils.parseDate(startDate.substring(0,10), "yyyy-MM-dd");
		int startStamp = new BigDecimal(start.getTime()).divide(new BigDecimal(100000)).intValue();
		billCriteria.setOrderByClause("id desc");
		List<BillTerm> billTerms = billTermMapper.selectByExample(billCriteria);
		if(CollectionUtils.isEmpty(billTerms)){
			return true;
		}
		BillTerm term = billTerms.get(0);
		String id = term.getId();
		int checkEndTime = Integer.parseInt(id.substring(8,16));
		
		if(startStamp>checkEndTime){
			return true;
		}else{
			return false;
		}
	}
	
	public void billBuild(String startDate,String endDate,int ordersType) throws Exception{
		Map<String, Object> paras = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(startDate)) {
			paras.put("GTE_editTime", startDate);
		}
		
		if (StringUtils.isNotBlank(endDate)) {
			paras.put("LTE_editTime", endDate);
		}
		String sqlList = "";
		if(ordersType==0){
			sqlList = FreeMarkerUtils.format("/template/order/bill_statium_orders.ftl", paras);
		}else if(ordersType==1){
			sqlList = FreeMarkerUtils.format("/template/order/bill_enjoy_orders.ftl", paras);
		}else{
			//......
		}
		
        List<Map<String,Object>> orders = jdbcTemplate.queryForList(sqlList);
		List<OrderBillVo> vos = new ArrayList<OrderBillVo>();
		OrderBillVo vo = null;
		for(Map<String, Object> order:orders){
			vo = new OrderBillVo();
			String id = (String)order.get("id");
			vo.setId(id);
			Date et = (Date)order.get("et");
			String statiumId = (String)order.get("statiumId");
			int subsidies = 0;
			if(order.get("subsidies")!=null){
				subsidies = (Integer)order.get("subsidies");
			}
			BigDecimal costPrice = new BigDecimal(0);
			if(order.get("costPrice")!=null){
				costPrice = (BigDecimal)order.get("costPrice");
			}
			vo.setStatiumId(statiumId);
			vo.setSubsidies(subsidies);
			vo.setCostPrice(costPrice.intValue());
			StatiumDetail detail = this.selectByPrimaryKey(StatiumDetail.class,statiumId);
			vo.setBankAccountName(detail.getBankAccountBranchName());
			vo.setBankaccountno(detail.getBankAccountNo());
			String sportType = (String)order.get("sportType");
			vo.setSportType(CommonOAUtils.sportsEToC(sportType));
			vo.setAddress(detail.getAddress());
			vo.setUserName(detail.getBankAccountName());
			Map<String,String> zoneMap = Zonemap.split(detail.getAreaCode());
			vo.setArea(zoneMap.get("province")+zoneMap.get("city"));
			vo.setStatiumName(detail.getName());
			vo.setPhone(detail.getMasterTel());
			vo.setOrderDate(DateUtil.formatDate(et,"yyyy-MM-dd"));
			vos.add(vo);
		}
/*		Collections.sort(vos, new Comparator<OrderBillVo>() {
            public int compare(OrderBillVo o1, OrderBillVo o2) {
                return (o1.getOrderDate()).compareTo(o2.getOrderDate());
            }
        });*/
		Map<String,BillPrice> results = this.buildMap(vos);
		this.insertStatiumBill(results,startDate,endDate,ordersType);
	}
	
	public void delBill(String id) throws Exception{
		OrderBillItemCriteria billItemCriteria = new OrderBillItemCriteria();
		OrderBillItemCriteria.Criteria billItemCri = billItemCriteria.createCriteria();
		billItemCri.andOrderBillIdLike(id+"%");
		billItemMapper.deleteByExample(billItemCriteria);
		
		OrderBillCriteria billCriteria = new OrderBillCriteria();
		OrderBillCriteria.Criteria billCri = billCriteria.createCriteria();
		billCri.andBillIdEqualTo(id);
		billMapper.deleteByExample(billCriteria);
		
		billTermMapper.deleteByPrimaryKey(id);
	}
	
	public Page<StatiumOrderBill> statiumBill(String id,int page, int size,String type,String name,boolean isPage) throws Exception{
		PageRequest pageable = new PageRequest(page, size);
		OrderBillCriteria billTermCriteria = new OrderBillCriteria();
		OrderBillCriteria.Criteria criteria = billTermCriteria.createCriteria();
		criteria.andBillIdEqualTo(id);
		/*//客服
		if (SecurityUtils.getSubject().hasRole("customer_manager")) {
			List<Integer> statuss = new ArrayList<Integer>();
			statuss.add(0);
			statuss.add(2);
			criteria.andStatusIn(statuss);
        }
		//财务
		if (SecurityUtils.getSubject().hasRole("finance")) {
			List<Integer> statuss = new ArrayList<Integer>();
			statuss.add(1);
			statuss.add(2);
			criteria.andStatusIn(statuss);
        }*/
		if(StringUtils.isNotEmpty(type)){
			criteria.andStatusEqualTo(Integer.parseInt(type));
		}
		if(StringUtils.isNotEmpty(name)){
			String [] name_area = name.split("_",-1);
			if(StringUtils.isNotBlank(name_area[0])){
				criteria.andStatiumNameLike("%"+name_area[0]+"%");
			}
			if(StringUtils.isNotBlank(name_area[1])){
				criteria.andAreaLike(name_area[1]+"%");
			}
			if(StringUtils.isNotBlank(name_area[2])){
				criteria.andSportTypeLike(name_area[2]+"%");
			}
		}
		int total = billMapper.countByExample(billTermCriteria);
		if(isPage){
			billTermCriteria.setMysqlLength(pageable.getPageSize());
			billTermCriteria.setMysqlOffset(pageable.getOffset());
		}
		billTermCriteria.setOrderByClause("status");
		List<OrderBill> billTerms = billMapper.selectByExample(billTermCriteria);
		List<StatiumOrderBill> vos = new ArrayList<StatiumOrderBill>();
		StatiumOrderBill vo = null;
		for(OrderBill term : billTerms){
			vo = new StatiumOrderBill();
			BeanUtils.copyProperties(term, vo);
			String start = id.substring(0,8);
			String end = id.substring(8,16);
			vo.setStartTime(DateUtil.formatDate(new Date(Long.parseLong(start)*100000L), "yyyy-MM-dd"));
			vo.setEndTime(DateUtil.formatDate(new Date(Long.parseLong(end)*100000L), "yyyy-MM-dd"));
			vos.add(vo);
		}
		return new PageImpl<StatiumOrderBill>(vos, pageable, total);
	}
	
	public void verifyBillSel(String [] ids) throws Exception{
		OrderBill orderBill = null;
		for(String id:ids){
			orderBill = new OrderBill();
			orderBill.setId(id);
			orderBill.setStatus(1);
			billMapper.updateByPrimaryKeySelective(orderBill);
		}
	}
	
	public void verifyBillAll(String billId) throws Exception{
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("status", 1);
		paras.put("billId", billId);
		String sqlCount = FreeMarkerUtils.format("/template/order/batch_update_bill.ftl", paras);
		jdbcTemplate.update(sqlCount);
	}
	
	public void balanceBillSel(String [] ids) throws Exception{
		OrderBill orderBill = null;
		String sms = "";
		for(String id:ids){
			orderBill = new OrderBill();
			orderBill.setId(id);
			orderBill.setStatus(2);
			billMapper.updateByPrimaryKeySelective(orderBill);
			orderBill = billMapper.selectByPrimaryKey(id);
			OrdeNotifyrMessage notifyMessage = new OrdeNotifyrMessage();
			notifyMessage.setType("bill");
			notifyMessage.setStatiumId(orderBill.getStatiumId());
			notifyMessage.setBillTitle(DateUtil.formatDate(new Date((long)orderBill.getBeginDate()*100000L), "yyyy-MM-dd")+"到"+DateUtil.formatDate(new Date((long)orderBill.getEndDate()*100000L), "yyyy-MM-dd")+"的账单");
			//orderNotifyUtil.notifyOrder(MyGson.getInstance().toJson(notifyMessage));
			
			//场馆负责人发送短信
			Map<String, Object> paras = new HashMap<String, Object>();
			logger.debug("账单信息,{}", MyGson.getInstance().toJson(orderBill));
			paras.put("masterName", orderBill.getUserName());
			paras.put("begin", DateUtil.formatDate(new Date((long)orderBill.getBeginDate()*100000L), "yy年M月d日"));
			paras.put("end", DateUtil.formatDate(new Date((long)orderBill.getEndDate()*100000L), "yy年M月d日"));
			paras.put("orderCont", orderBill.getOrderNum());
			paras.put("totalMoney", orderBill.getFee()+orderBill.getSubsidy());
			//打款日为4个工作日
			Date endDate = new Date((long)orderBill.getEndDate()*100000L);
			Calendar cal = Calendar.getInstance();
			cal.setTime(endDate);
			cal.add(Calendar.DATE, 2);
			//判断为非工作日 +1天
			while(this.checkWorkday(DateUtil.formatDate(cal.getTime(), "yyyy-MM-dd"))!=1){
				cal.add(Calendar.DATE, 1);
			}		
			String endStr = DateUtil.formatDate(cal.getTime(), "M月d日");
			paras.put("billDate", endStr);
			sms = FreeMarkerUtils.format("/template/sms/billSuccess.ftl", paras);
			String statiumId = orderBill.getStatiumId();
			StatiumDetail detail = this.selectByPrimaryKey(StatiumDetail.class, statiumId);
			messageUtil.sendSms(orderBill.getPhone(), sms);
		}
	}
	
	public void balanceBillAll(String billId) throws Exception{
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("status", 2);
		paras.put("billId", billId);
		String sqlCount = FreeMarkerUtils.format("/template/order/batch_update_bill.ftl", paras);
		jdbcTemplate.update(sqlCount);
		OrderBillCriteria orderBillCriteria = new OrderBillCriteria();
		OrderBillCriteria.Criteria OrderBillCri = orderBillCriteria.createCriteria();
		OrderBillCri.andBillIdEqualTo(billId);
		List<OrderBill> bills = billMapper.selectByExample(orderBillCriteria);
		String sms = "";
		for(OrderBill bill:bills){
			OrdeNotifyrMessage notifyMessage = new OrdeNotifyrMessage();
			notifyMessage.setType("bill");
			notifyMessage.setStatiumId(bill.getStatiumId());
			notifyMessage.setBillTitle(DateUtil.formatDate(new Date((long)bill.getBeginDate()*100000L), "yyyy-MM-dd")+"到"+DateUtil.formatDate(new Date((long)bill.getEndDate()*100000L), "yyyy-MM-dd")+"的账单");
			//orderNotifyUtil.notifyOrder(MyGson.getInstance().toJson(notifyMessage));
			
			//场馆负责人发送短信
			Map<String, Object> paras_ = new HashMap<String, Object>();
			logger.debug("账单信息,{}", MyGson.getInstance().toJson(bill));
			paras_.put("masterName", bill.getUserName());
			paras_.put("begin", DateUtil.formatDate(new Date((long)bill.getBeginDate()*100000L), "yy年M月d日"));
			paras_.put("end", DateUtil.formatDate(new Date((long)bill.getEndDate()*100000L), "yy年M月d日"));
			paras_.put("orderCont", bill.getOrderNum());
			paras_.put("totalMoney", bill.getFee()+bill.getSubsidy());
			//打款日为4个工作日
			Date endDate = new Date((long)bill.getEndDate()*100000L);
			Calendar cal = Calendar.getInstance();
			cal.setTime(endDate);
			cal.add(Calendar.DATE, 2);
			//判断为非工作日 +1天
			while(this.checkWorkday(DateUtil.formatDate(cal.getTime(), "yyyy-MM-dd"))!=1){
				cal.add(Calendar.DATE, 1);
			}		
			String endStr = DateUtil.formatDate(cal.getTime(), "M月d日");
			paras.put("billDate", endStr);
			sms = FreeMarkerUtils.format("/template/sms/billSuccess.ftl", paras_);
			String statiumId = bill.getStatiumId();
			StatiumDetail detail = this.selectByPrimaryKey(StatiumDetail.class, statiumId);
			messageUtil.sendSms(bill.getPhone(), sms);
		}
	}
	@Transactional(readOnly = false)
	private void insertStatiumBill(Map<String,BillPrice> results,String startDate,String endDate,int ordersType) throws Exception{
		User user = SessionUtil.currentUser();
		Date start = DateUtil.parse(startDate.substring(0,10), null);
		int st = new BigDecimal(start.getTime()).divide(new BigDecimal(100000)).intValue();
		Date end = DateUtil.parse(endDate.substring(0,10), null);
		int ed = new BigDecimal(end.getTime()).divide(new BigDecimal(100000)).intValue();
		OrderBillItem billItem = null;
		Map<String,BillPrice> totalBillMap = new HashMap<String, BillPrice>();
		BillPrice totalBillPrice = null;
		for (Map.Entry<String, BillPrice> entry : results.entrySet()) {
			billItem = new OrderBillItem();
			billItem.setId(UUID.get());
            String key = entry.getKey();
            BillPrice billPrice = entry.getValue();
            //vo.getStatiumId()+SPLIT+vo.getArea()+SPLIT+vo.getStatiumName()+SPLIT+vo.getAddress()+SPLIT+vo.getPhone()+SPLIT+vo.getSportType()+SPLIT+vo.getBankAccountName()+SPLIT+vo.getBankaccountno()+SPLIT+vo.getOrderDate();
            String[] keys = key.split(SPLIT,-1);
            String statiumId = keys[0];
            String orderDate = keys[9];
            billItem.setOrderBillId(String.valueOf(st)+String.valueOf(ed)+statiumId);
            billItem.setStartDate(orderDate);
            billItem.setTotalFee(billPrice.getCostPrice());
            billItem.setSubsidyAmount(billPrice.getSubsidy());
            billItem.setOrderCount(billPrice.getOrderCount());
            billItemMapper.insert(billItem);
            String newKey = keys[0]+SPLIT+keys[1]+SPLIT+keys[2]+SPLIT+keys[3]+SPLIT+keys[4]+SPLIT+keys[5]+SPLIT+keys[6]+SPLIT+keys[7]+SPLIT+keys[8];
            if(totalBillMap.containsKey(newKey)){
            	totalBillPrice = totalBillMap.get(newKey);
            	totalBillPrice.setOrderCount(totalBillPrice.getOrderCount()+billPrice.getOrderCount());
				int costPrice = totalBillPrice.getCostPrice();
				costPrice = ((new BigDecimal(billPrice.getCostPrice())).add(new BigDecimal(costPrice))).intValue();
				int subsidy = totalBillPrice.getSubsidy();
				subsidy = ((new BigDecimal(billPrice.getSubsidy())).add(new BigDecimal(subsidy))).intValue();
				totalBillPrice.setCostPrice(costPrice);
				totalBillPrice.setSubsidy(subsidy);
            }else{
            	totalBillMap.put(newKey, billPrice);
            }
        }
		OrderBill bill = null;
		BillTerm billTerm = new BillTerm();
		billTerm.setId(String.valueOf(st)+String.valueOf(ed));
		BigDecimal orderCount = new BigDecimal(0);
		BigDecimal costPrice = new BigDecimal(0);
		BigDecimal subsidy = new BigDecimal(0);
		int statiumNum=0;
		for (Map.Entry<String, BillPrice> entry : totalBillMap.entrySet()) {
			statiumNum++;
			String key = entry.getKey();
            BillPrice billPrice = entry.getValue();
            //vo.getStatiumId()+"_"+vo.getArea()+"_"+vo.getStatiumName()+"_"+vo.getAddress()+"_"+vo.getPhone()+"_"+vo.getOrderDate();
            String keys[] = key.split(SPLIT,-1);
            String statiumId = keys[0];
			String area = keys[1];
            String statiumName = keys[2];
            String address = keys[3];
            String phone = keys[4];
            String sportType = keys[5];
            String bankName = keys[6];
            String bankNo = keys[7];
            String userName = keys[8];
            bill = new OrderBill();
            bill.setId(UUID.get());
            bill.setAddress(address);
            bill.setPhone(phone);
            bill.setArea(area);
            bill.setBeginDate(st);
            bill.setEndDate(ed);
            bill.setUserName(userName);
            bill.setBankAccountName(bankName);
            bill.setBankaccountno(bankNo);
            bill.setSportType(sportType);
            bill.setBillId(String.valueOf(st)+String.valueOf(ed));
            bill.setCb(user.getUserId());
            bill.setCt(new Date());
            bill.setStatiumId(statiumId);
            bill.setStatiumName(statiumName);
            bill.setStatus(0);
            bill.setFee(billPrice.getCostPrice());
            bill.setSubsidy(billPrice.getSubsidy());
            bill.setOrderNum(billPrice.getOrderCount());
            orderCount = orderCount.add(new BigDecimal(billPrice.getOrderCount()));
            costPrice = costPrice.add(new BigDecimal(billPrice.getCostPrice()));
            subsidy = subsidy.add(new BigDecimal(billPrice.getSubsidy()));
            billMapper.insert(bill);
		}
		billTerm.setOrderNum(orderCount.intValue());
		billTerm.setTotalAmount((costPrice.add(subsidy)).intValue());
		billTerm.setStatiumNum(statiumNum);
		billTerm.setCt(new Date());
		billTerm.setCb(user.getUserId());
		billTerm.setType(ordersType);
		billTermMapper.insert(billTerm);
	}
	
	/**
	 * 构建场馆每天的结算
	 * @param vos
	 * @return
	 * @throws Exception
	 */
	private Map<String,BillPrice> buildMap(List<OrderBillVo> vos) throws Exception{
		Map<String,BillPrice> results = new HashMap<String, BillPrice>();
		for(OrderBillVo vo : vos){
			//补贴
			BigDecimal sub = new BigDecimal(vo.getSubsidies());
			sub = sub.divide(new BigDecimal(100));
			//成本价
			BigDecimal cost = new BigDecimal(vo.getCostPrice());
			cost = cost.divide(new BigDecimal(100));
			String key = vo.getStatiumId()+SPLIT+vo.getArea()+SPLIT+vo.getStatiumName()+SPLIT+vo.getAddress()+SPLIT+vo.getPhone()+SPLIT+vo.getSportType()+SPLIT+vo.getBankAccountName()+SPLIT+vo.getBankaccountno()+SPLIT+vo.getUserName()+SPLIT+vo.getOrderDate();
			if(results.containsKey(key)){
				BillPrice billPrice = results.get(key);
				int orderCount = billPrice.getOrderCount();
				billPrice.setOrderCount(++orderCount);
				int costPrice = billPrice.getCostPrice();
				costPrice = ((new BigDecimal(billPrice.getCostPrice())).add(cost)).intValue();
				int subsidy = billPrice.getSubsidy();
				subsidy = ((new BigDecimal(subsidy)).add(sub)).intValue();
				billPrice.setCostPrice(costPrice);
				billPrice.setSubsidy(subsidy);
			}else{
				BillPrice billPrice = new BillPrice();
				billPrice.setOrderCount(1);
				billPrice.setCostPrice(cost.intValue());
				billPrice.setSubsidy(sub.intValue());
				results.put(key, billPrice);
			}
			
		}
		return results;
		
	}
	
	/**
	 * 获取补贴价格映射statiumId_isWorkDay_sportType,subsidies
	 * @return
	 */
	private Map<String,Integer> getStatiumSubsidiesMap(){
		Map<String, Object> paras = new HashMap<String, Object>();
		String sql = FreeMarkerUtils.format("/template/order/statium_subsidies.ftl", paras);
		List<Map<String,Object>> statiumSubsidiesMaps = jdbcTemplate.queryForList(sql);
		Map<String,Integer> statiumSubsidiesMap = new HashMap<String, Integer>();
		for(Map<String, Object> map:statiumSubsidiesMaps){
			String key = (String)map.get("k");
			Integer value = (Integer)map.get("v");
			statiumSubsidiesMap.put(key, (new BigDecimal(value).multiply(new BigDecimal(100))).intValue());
		}
		return statiumSubsidiesMap;
	}
	
	private Map<String,List<Integer>> getSpaceCostMap(){
		Map<String, Object> paras = new HashMap<String, Object>();
		String sql = FreeMarkerUtils.format("/template/order/space_costvalue.ftl", paras);
		List<Map<String,Object>> spaceCostMaps = jdbcTemplate.queryForList(sql);
		Map<String,List<Integer>> spaceCostMap = new HashMap<String, List<Integer>>();
		for(Map<String, Object> map:spaceCostMaps){
			String key = (String)map.get("k");
			String value = (String)map.get("v");
			String[] prices = value.split(",",-1);
            List<Integer> newPrice = new ArrayList<Integer>();
            for (int i = 0; i < prices.length; i++) {
            	int price = 0;
                if (!"".equals(prices[i])&&!"0".equals(prices[i])) {
                	price = Integer.parseInt(prices[i]);
                }
                newPrice.add(price);
            }
			spaceCostMap.put(key, newPrice);
		}
		logger.debug(spaceCostMap.toString());
		return spaceCostMap;
	}
	
	private Integer checkWorkday(String startDate) throws Exception {
        // 是否是工作日0：否，1： 是
        Integer isWorkday = 1;
        // holiday为null是工作日，不为null节假日
        Holiday holiday = this.selectByPrimaryKey(Holiday.class, startDate);
        if (holiday != null) {// 节假日
            isWorkday = 0;
        }
        return isWorkday;
    }
	public void initPrice() throws Exception{
		Map<String, Integer> subMap = getStatiumSubsidiesMap();
		Map<String, List<Integer>> costMap = getSpaceCostMap();
		OrderCriteria criteria = new OrderCriteria();
		OrderCriteria.Criteria cri = criteria.createCriteria();
		cri.andOrdersTypeEqualTo(0);
		cri.andCtBetween(DateUtil.parse("2015-10-01 00:00:00", "yyyy-MM-dd HH:mm:ss", null),DateUtil.parse("2015-11-05 23:59:59", "yyyy-MM-dd HH:mm:ss", null));
		List<Order> orders = orderMapper.selectByExample(criteria);
		for (Order order : orders) {
			OrderItemCriteria orderItemCriteria = new OrderItemCriteria();
			OrderItemCriteria.Criteria orderItemCri = orderItemCriteria.createCriteria();
			orderItemCri.andOrderIdEqualTo(order.getId());
			List<OrderItem> orderItems = orderItemMapper.selectByExample(orderItemCriteria);
			if(CollectionUtils.isEmpty(orderItems)){
				continue;
			}
			int workDay = checkWorkday(DateUtil.formatDate(orderItems.get(0).getStartDate(), "yyyy-MM-dd"));
			int cost = 0;
			for(OrderItem item_:orderItems){
				Date startTime = new Date(item_.getStartTime() * 1000L);
				Long startHour = DateUtils.getFragmentInHours(startTime, Calendar.DATE);
				logger.debug("key值为,{}",item_.getSpaceId()+String.valueOf(workDay));
				List<Integer> costs = costMap.get(item_.getSpaceId()+"_"+String.valueOf(workDay));
				if(CollectionUtils.isNotEmpty(costs)){
					cost = costs.get(startHour.intValue());
					OrderItem newItem = new OrderItem();
					newItem.setId(item_.getId());
					newItem.setCostPrice(cost);
					this.updateByPrimaryKeySelective(newItem, newItem.getId());
				}else{
					
				}
			}
			
			int sub = 0;
			if(cost!=0){
				if(subMap.containsKey(order.getStatiumId()+"_"+orderItems.get(0).getSportType())){
					sub = subMap.get(order.getStatiumId()+"_"+orderItems.get(0).getSportType());
					Order newOrder = new Order();
					newOrder.setId(order.getId());
					newOrder.setSubsidies((new BigDecimal(sub).multiply(new BigDecimal(orderItems.size()))).intValue());
					this.updateByPrimaryKeySelective(newOrder, order.getId());
				}
			}
		}
	}
	public static void main(String[] args) {
		Map<String, Object> paras_ = new HashMap<String, Object>();
		/*String bill = {"id":"07fdec43cef541738943c80c5d471e93","billId":"1
			465574414663520","statiumName":"金鼎新概念台球馆","area":"辽宁省大连市","address":"大连市甘井子区中华西路21号（安盛沃特时尚广场地下一层）","phone":"15040580908","
			sportType":"台球","userName":"李平平","bankAccountName":"民生银行","bankaccountno":"6226220784114844","status":2,"orderNum":7,"beginDate":14655744,"endDate":14663
			520,"fee":98,"subsidy":0,"statiumId":"704d22f640c141119a9ae99df61c1d44","ct":"2016-06-21 05:58:12","cb":"663c837deefb4401819b263efb7d3c1b"};*/
		paras_.put("masterName", "李平平");
		paras_.put("begin", "15年7月1日");
		paras_.put("end", "15年7月10日");
		paras_.put("orderCont", "15");
		paras_.put("totalMoney", "430");
		String sms = FreeMarkerUtils.format("/template/sms/billSuccess.ftl", paras_);
	}
}
