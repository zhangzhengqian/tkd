package com.lc.zy.ball.boss.framework.order.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.order.vo.BillPriceVo;
import com.lc.zy.ball.boss.framework.order.vo.BillTermVo;
import com.lc.zy.ball.boss.framework.order.vo.OrderBillVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.BillTermMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderBillItemMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderBillMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumInfosMapper;
import com.lc.zy.ball.domain.oa.po.BillTerm;
import com.lc.zy.ball.domain.oa.po.BillTermCriteria;
import com.lc.zy.ball.domain.oa.po.OrderBill;
import com.lc.zy.ball.domain.oa.po.OrderBillCriteria;
import com.lc.zy.ball.domain.oa.po.OrderBillItem;
import com.lc.zy.ball.domain.oa.po.OrderBillItemCriteria;
import com.lc.zy.ball.domain.oa.po.StatiumInfos;
import com.lc.zy.ball.domain.oa.po.StatiumInfosCriteria;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.ExcelUtil;
import com.lc.zy.common.util.FreeMarkerUtils;
import com.lc.zy.common.util.UUID;
@Service
@Transactional(readOnly=false)
public class BillService extends AbstractCacheService{
	private final static String SPLIT = "\01";
	@Autowired
	private BillTermMapper billTermMapper;
	
	@Autowired
	private OrderBillMapper orderBillMapper;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private StatiumInfosMapper statiumInfosMapper;
	
	@Autowired
	private OrderBillItemMapper orderBillItemMapper;
	
	private	static Logger logger = LoggerFactory.getLogger(BillService.class);
	
	/**
	 * 
	 * <道馆结账列表展示><功能具体实现>
	 *
	 * @create：2016年9月19日 下午5:19:53
	 * @author：zzq
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page<BillTermVo> find(int page,int pageSize){
		PageRequest pageable = new PageRequest(page, pageSize);
		BillTermCriteria billTermCriteria = new BillTermCriteria();
		billTermCriteria.setMysqlLength(pageable.getPageSize());
		billTermCriteria.setMysqlOffset(pageable.getOffset());
		billTermCriteria.setOrderByClause("ct desc");
		int total = billTermMapper.countByExample(billTermCriteria);
		List<BillTerm> billTerms = billTermMapper.selectByExample(billTermCriteria);
		List<BillTermVo> vos = new ArrayList<BillTermVo>();
		for(BillTerm billTerm:billTerms){
			BillTermVo vo=new BillTermVo();
			BeanUtils.copyProperties(billTerm, vo);
			String id = billTerm.getId();
			//id前8位是开始时间 后8位是结束时间 getTime返回以毫秒单位
			String startTime = id.substring(0, 8);
			String endTime   = id.substring(8, 16);
			//回头看看*100000L是怎么处理的 
			logger.debug("看看"+Long.parseLong(startTime)*100000L);
			String startTime1= DateUtils.formatDate(new Date(Long.parseLong(startTime)*100000L), "yyyy-MM-dd");
			String endTime2= DateUtils.formatDate(new Date(Long.parseLong(endTime)*100000L), "yyyy-MM-dd");
			boolean flag = true;
			if(billTerm.getOrderNum()==0){
				vo.setAvaliable(true);
			}
			else{
				flag = this.isAvaliable(id);
			}
			vo.setStartTime(startTime1);
			vo.setEndTime(endTime2);
			vo.setAvaliable(flag);
			vo.setTotalAmount(new BigDecimal(vo.getTotalAmount()).divide(new BigDecimal(100)).intValue());
			vos.add(vo);
		}
		
		return new PageImpl<>(vos,pageable,total);
		
	}
	
	/**
	 * 
	 * <判断是否可删除><功能具体实现>
	 *
	 * @create：2016年9月19日 下午6:24:37
	 * @author：zzq
	 * @param id
	 * @return
	 */
	public boolean isAvaliable(String id){
		//判断是否可删除，对于已结算的账单 不能删除 0新账单 1已确认 2已结账
		OrderBillCriteria orderBillCriteria = new OrderBillCriteria();
		orderBillCriteria.createCriteria().andBillIdEqualTo(id).andStatusEqualTo(2);
		int total = orderBillMapper.countByExample(orderBillCriteria);
		if(total>0){
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * 
	 * <生成一段时间内的账单信息><功能具体实现>
	 *
	 * @create：2016年9月21日 下午4:24:33
	 * @author：zzq
	 * @param startDate
	 * @param endDate
	 * @param ordersType
	 */
	@Transactional(readOnly=false)
	public void billBuild(String startDate,String endDate,Integer ordersType){
		try {
			Map<String,Object> paras = new HashMap<String, Object>();
			List<OrderBillVo> vos = new ArrayList<OrderBillVo>();
			String sql="";
			paras.put("GTE_editTime", startDate);
			paras.put("LTE_editTime", endDate);
			//课程和活动用一个账单结账  ordersType 0
			//会员卡订单单独一个账单结账  ordersType 1
			if(ordersType==0){
				paras.put("EQ_ordersTypeIn", new Integer[]{0,1});
				sql = FreeMarkerUtils.format("/template/order/bill_orders.ftl", paras);
			}
			if(ordersType==1){
				paras.put("EQ_ordersType", 2);
				sql = FreeMarkerUtils.format("/template/order/bill_recharge_orders.ftl", paras);
			}
			logger.debug("sql"+sql);
			List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
			for(Map<String,Object> map:list){
				OrderBillVo vo = new OrderBillVo();
				//订单id
				String id = (String)map.get("id");
				//开场时间
				Date signDate = (Date)map.get("signDate");
				String signDate1 = DateUtils.formatDate(signDate, "yyyy-MM-dd");
				//道馆id
				Integer dgId = (Integer)map.get("statiumId");
				//实付金额
				Integer finalFee = (Integer)map.get("finalFee");
				//查询道馆 
				StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
				statiumInfosCriteria.createCriteria().andDgIdEqualTo(dgId);
				List<StatiumInfos> dgList = statiumInfosMapper.selectByExample(statiumInfosCriteria);
				vo.setId(id);
				if(dgList!=null){
					StatiumInfos statiumInfos = dgList.get(0);
					vo.setStatiumName(statiumInfos.getDgName());
					vo.setArea(statiumInfos.getProvince()+statiumInfos.getCity()+statiumInfos.getArea());
					vo.setAddress(statiumInfos.getAddress());
					//道馆联系人
					vo.setUserName(statiumInfos.getContact());
					//联系电话
					vo.setPhone(statiumInfos.getTel());
					//银行账户
					vo.setBankAccountName(statiumInfos.getBankAccountName());
					vo.setBankAccountNo(statiumInfos.getBankAccountNo());
				}
				//实付费用
				vo.setCostPrice(finalFee);
				//预定日期
				vo.setOrderDate(signDate1);
				//道馆id
				vo.setStatiumId(dgId.toString());
				vos.add(vo);
			}
			
			//封装一个map对象 key是String value是统计道馆每日订单数量和金额总数的billPriceVo
			 //保存数据库 生成三个表 1按日期所有道馆的结算 2按道馆分 这些道馆这些天的订单统计3某个道馆每天的订单统计
			Map<String,BillPriceVo> billPrice = this.addMap(vos);
			 this.insertSelective(billPrice,startDate,endDate,ordersType);
			 
		}catch (Exception e) {
			logger.debug("封装OrderBillVo"+e.getMessage());
		}
	}
	
	/**
	 * 
	 * <道馆每日的订单详细统计><功能具体实现>
	 *
	 * @create：2016年9月21日 下午6:16:48
	 * @author：zzq
	 * @return
	 */
	public Map<String, BillPriceVo>	addMap(List<OrderBillVo> vos){
		Map<String,BillPriceVo> billPrice = new HashMap<String, BillPriceVo>();
		for(OrderBillVo orderBillVo:vos){
			String address = orderBillVo.getAddress();
			String area = orderBillVo.getArea();
			String bankAccountName = orderBillVo.getBankAccountNo();
			String bankAccountNo = orderBillVo.getBankAccountNo();
			String statiumName = orderBillVo.getStatiumName();
			String phone = orderBillVo.getPhone();
			String orderDate = orderBillVo.getOrderDate();
			String userName = orderBillVo.getUserName();
			//道馆id
			String statiumId = orderBillVo.getStatiumId();
			//实付金额
			BigDecimal bigDecimal = new BigDecimal(orderBillVo.getCostPrice());
			String key = statiumName+SPLIT+address+SPLIT+area+SPLIT+bankAccountName+SPLIT+bankAccountNo+SPLIT+phone+SPLIT+userName+SPLIT+orderDate+SPLIT+statiumId;
			if(billPrice.containsKey(key)){
				BillPriceVo vo = billPrice.get(key);
				int costPrice = (new BigDecimal(vo.getCostPrice()).add(bigDecimal)).intValue();
				int orderCount = vo.getOrderCount();
				vo.setCostPrice(costPrice);
				vo.setOrderCount(++orderCount);
			}else{
				BillPriceVo billPriceVo = new BillPriceVo();
				billPriceVo.setCostPrice(orderBillVo.getCostPrice());
				billPriceVo.setOrderCount(1);
				billPrice.put(key, billPriceVo);
				
			}
		}
		return billPrice;
	}
	
	/**
	 * 
	 * <保存结账数据><功能具体实现>
	 *
	 * @create：2016年9月22日 上午9:46:40
	 * @author：zzq
	 * @param billPrice
	 */
	public void insertSelective(Map<String,BillPriceVo> results,String startDate,String endDate,Integer ordersType){
		Map<String,BillPriceVo> orderBillMap = new HashMap<String,BillPriceVo>();
		logger.debug("道馆结账"+results);
		Date start = DateUtil.parse(startDate.substring(0, 10),null);
		Date end = DateUtil.parse(endDate.substring(0, 10),null);
		logger.debug("getTime"+start.getTime());
		int st = new BigDecimal(start.getTime()).divide(new BigDecimal(100000)).intValue();
		int ed = new BigDecimal(end.getTime()).divide(new BigDecimal(100000)).intValue();
		String newKey = "";
		//保存道馆当天的数据
		for(Map.Entry<String, BillPriceVo> map:results.entrySet()){
			
			OrderBillItem orderBillIterm = new OrderBillItem();
			String key = map.getKey();
			BillPriceVo billPrice = map.getValue();
			String[] keys = key.split(SPLIT);
			String orderDate = keys[7];
			String statiumId = keys[8];
			
			String id = UUID.get();
			//开始日期＋结束日期＋账单类型  0 课程和活动 1 充值
			String typeStr = ordersType.toString();
			orderBillIterm.setOrderBillId(st+ed+statiumId+typeStr);
			orderBillIterm.setId(id);
			orderBillIterm.setStartDate(orderDate);
			orderBillIterm.setOrderCount(billPrice.getOrderCount());
			orderBillIterm.setTotalFee(billPrice.getCostPrice());
			try {
				this.insertSelective(orderBillIterm,id);
			} catch (Exception e) {
				logger.error("保存道馆每天的数据"+e.getMessage());
			}
			newKey = keys[0]+SPLIT+keys[1]+SPLIT+keys[2]+SPLIT+keys[3]+SPLIT+keys[4]+SPLIT+keys[5]+SPLIT+keys[6]+SPLIT+keys[8];
			//保存道馆这几天的数据,先封装一个map对象
			if(orderBillMap.containsKey(newKey)){
				BillPriceVo billPrice2 = orderBillMap.get(newKey);
				int orderCount = billPrice2.getOrderCount();
				billPrice2.setOrderCount(billPrice.getOrderCount()+orderCount);
				int costPrice = billPrice2.getCostPrice();
				billPrice2.setCostPrice(new BigDecimal(costPrice).add(new BigDecimal(billPrice.getCostPrice())).intValue());
			}
			else{
				BillPriceVo billPrice1 = new BillPriceVo();
				billPrice1.setOrderCount(billPrice.getOrderCount());
				billPrice1.setCostPrice(billPrice.getCostPrice());
				orderBillMap.put(newKey, billPrice1);
			}
		}
		//统计所有道馆这一段时间内的订单
		//订单数量
		BigDecimal orderCount = new BigDecimal(0);
		//道馆数量
		int statiumCount = 0;
		//订单总金额
		BigDecimal costPrice = new BigDecimal(0);
		for(Map.Entry<String, BillPriceVo> map:orderBillMap.entrySet()){
			OrderBill orderBill = new OrderBill();
			statiumCount++;
			String id = UUID.get();
			logger.debug("map"+map);
			String newKey1 = map.getKey();
			BillPriceVo billPrice = map.getValue();
			String[] keys = newKey1.split(SPLIT);
			String statiumName = keys[0];
			String address = keys[1];
			String area = keys[2];
			String bankAccountName = keys[3];
			String bankAccountNo = keys[4];
			String phone = keys[5];
			String userName = keys[6];
			String statiumId = keys[7];
			orderBill.setId(id);
			orderBill.setAddress(address);
			orderBill.setArea(area);
			orderBill.setBankAccountName(bankAccountName);
			orderBill.setBankaccountno(bankAccountNo);
			orderBill.setBeginDate(st);
			orderBill.setEndDate(ed);
			orderBill.setBeginDate(st);
			orderBill.setEndDate(ed);
			orderBill.setCb(SessionUtil.currentUserId());
			orderBill.setCt(new Date());
			orderBill.setStatiumName(statiumName);
			orderBill.setPhone(phone);
			orderBill.setUserName(userName);
			orderBill.setOrderNum(billPrice.getOrderCount());
			orderBill.setFee(billPrice.getCostPrice());
			//未确认
			orderBill.setStatus(0);
			//道馆id
			orderBill.setStatiumId(statiumId);
			//开始日期＋结束日期＋账单类型  0 课程和活动 1 充值
			String typeStr = ordersType.toString();
			orderBill.setBillId(String.valueOf(st)+String.valueOf(ed)+typeStr);
			try {
				this.insertSelective(orderBill, id);
			} catch (Exception e) {
				logger.error("保存道馆几天的账单");
			}
			costPrice = costPrice.add(new BigDecimal(billPrice.getCostPrice()));
			orderCount = orderCount.add(new BigDecimal(billPrice.getOrderCount()));
		}
		//保存所有道馆这几天的订单统计
		//主键为开始日期＋结束日期＋账单类型  0 课程和活动 1 充值
		String typeStr = ordersType.toString(); 
		BillTerm billTerm = new BillTerm();
		billTerm.setId(String.valueOf(st)+String.valueOf(ed)+typeStr);
		billTerm.setCb(SessionUtil.currentUserId());
		billTerm.setCt(new Date());
		billTerm.setOrderNum(orderCount.intValue());
		billTerm.setStatiumNum(statiumCount);
		billTerm.setTotalAmount(costPrice.intValue());
		//账单种类 1课程和活动的订单 存0 2充值订单 存1
		billTerm.setType(ordersType);
		try {
			this.insertSelective(billTerm,String.valueOf(st)+String.valueOf(ed));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("保存所有数据"+e.getMessage());
		}
		
		
	}
	
	/**
	 * 
	 * <开始时间不能小于目前最大的结束时间><功能具体实现>
	 *
	 * @create：2016年9月22日 下午6:35:59
	 * @author：zzq
	 * @param startDate
	 * @return
	 * @throws ParseException 
	 */
	public boolean checkAdd(String startDate,Integer ordersType) throws ParseException{
		String start = startDate.substring(0,10);
		Date date = DateUtil.parse(start, null);
		int startDate1 =new BigDecimal(date.getTime()).divide(new BigDecimal(100000)).intValue();
		boolean flag = false;
		BillTermCriteria billTermCriteria = new BillTermCriteria();
		billTermCriteria.setOrderByClause("id desc");
		billTermCriteria.createCriteria().andTypeEqualTo(ordersType);
		List<BillTerm> termList = billTermMapper.selectByExample(billTermCriteria);
		if(termList.size()==0){
			flag = true;
		}
		else{
			String id = termList.get(0).getId();
			String endDate1 = id.substring(8, 16);
			//上一个结束时间
			int endDate = Integer.valueOf(endDate1);
			if(startDate1>endDate){
				flag = true;
			}
			else{
				flag = false;
			}
		}
		
		return flag;
	}
	
	/**
	 * 
	 * <删除结账数据><功能具体实现>
	 *
	 * @create：2016年9月23日 下午12:14:09
	 * @author：zzq
	 * @param billTermId
	 */
	@Transactional(readOnly=false)
	public void deleteBill(String billTermId){
		try {
			logger.debug(billTermId);
			this.deleteByPrimaryKey(BillTerm.class, billTermId);
			OrderBillCriteria orderBillCriteria = new OrderBillCriteria();
			orderBillCriteria.createCriteria().andBillIdEqualTo(billTermId);
			List<OrderBill> orderBillList = orderBillMapper.selectByExample(orderBillCriteria);
			String startTime = billTermId.substring(0, 8);
			String endTime = billTermId.substring(8, 16);
			String orderType = billTermId.substring(16, 17);
			int st = Integer.parseInt(startTime);
			int et = Integer.parseInt(endTime);
			int billItermId = st+et;
			String statiumId = "";
			for(OrderBill orderBill:orderBillList){
				String id = orderBill.getId();
				this.deleteByPrimaryKey(OrderBill.class, id);
				//开始时间+结束时间 道馆id 类型拼接
				if(orderBillList!=null){
					 statiumId= orderBill.getStatiumId();
				}
				String orderBillId = String.valueOf(billItermId)+statiumId+orderType;
				OrderBillItemCriteria orderBillItemCriteria = new OrderBillItemCriteria();
				orderBillItemCriteria.createCriteria().andOrderBillIdEqualTo(orderBillId);
				List<OrderBillItem> itermList = orderBillItemMapper.selectByExample(orderBillItemCriteria);
				if(itermList!=null){
					for(OrderBillItem item:itermList){
						orderBillItemMapper.deleteByPrimaryKey(item);
					}
				}
			}
		} catch (NumberFormatException e) {
			e.getMessage();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	/**
	 * 
	 * <结账详情><功能具体实现>
	 *
	 * @create：2016年9月23日 下午7:06:00
	 * @author：zzq
	 * @param page
	 * @param pageSize
	 * @param id
	 * @return
	 */
	public Page<OrderBillVo> findDetail(int page,int pageSize,String id,String queryCondition,String type){
		PageRequest pageResquest = new PageRequest(page, pageSize);
		OrderBillCriteria orderBillCriteria = new OrderBillCriteria();
		orderBillCriteria.setMysqlLength(pageResquest.getPageSize());
		orderBillCriteria.setMysqlOffset(pageResquest.getOffset());
		if(!"".equals(queryCondition)){
			String[] condition = queryCondition.split("_",-1);
			String statiumName = condition[0];
			String area = condition[1];
			if(!"".equals(statiumName)){
				orderBillCriteria.createCriteria().andStatiumNameLike("%"+statiumName+"%").andBillIdEqualTo(id);
			}
			if(!"".equals(area)){
				orderBillCriteria.createCriteria().andAreaLike("%"+area+"%").andBillIdEqualTo(id);
			}
			orderBillCriteria.createCriteria().andBillIdEqualTo(id);
		}
		if(!"".equals(type)){
			orderBillCriteria.createCriteria().andStatusEqualTo(Integer.valueOf(type)).andBillIdEqualTo(id);
		}
		if("".equals(queryCondition)){
			orderBillCriteria.createCriteria().andBillIdEqualTo(id);
		}
		int total = orderBillMapper.countByExample(orderBillCriteria);
		List<OrderBill> vos = orderBillMapper.selectByExample(orderBillCriteria);
		List<OrderBillVo> billList = new ArrayList<OrderBillVo>();
		for(OrderBill orderBill:vos){
			OrderBillVo vo = new OrderBillVo();
			BeanUtils.copyProperties(orderBill, vo);
			String billId = vo.getBillId();
			int st = Integer.parseInt(billId.substring(0, 8));
			int dt = Integer.parseInt(billId.substring(8, 16));
			String orderType = billId.substring(16, 17);
			vo.setBillId(String.valueOf(st+dt)+vo.getStatiumId()+orderType);
			vo.setCostPrice(new BigDecimal(vo.getFee()).divide(new BigDecimal(100)).intValue());
			billList.add(vo);
		}
		//
		return new PageImpl<OrderBillVo>(billList, pageResquest, total);
	}
	
	/**
	 * 
	 * <确认或部分确认账单><功能具体实现>
	 *
	 * @create：2016年9月27日 上午10:26:41
	 * @author：zzq
	 * @param ids
	 */
	public void verifyBill(String[] ids){
		try {
			for(String id:ids){
				OrderBill orderBill = this.selectByPrimaryKey(OrderBill.class, id);
				if(orderBill!=null){
					orderBill.setStatus(1);
					this.updateByPrimaryKeySelective(orderBill, id);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("修改状态为已确认"+e.getMessage());
		}
	}
	
	/**
	 * 
	 * <结算或者批量结算><功能具体实现>
	 *
	 * @create：2016年9月27日 下午3:12:16
	 * @author：zzq
	 * @param ids
	 */
	public void balanceBill(String[] ids){
		try {
			for(String id:ids){
				OrderBill orderBill = this.selectByPrimaryKey(OrderBill.class, id);
				if(orderBill!=null){
					orderBill.setStatus(2);
					this.updateByPrimaryKeySelective(orderBill, id);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("修改状态为已结算"+e.getMessage());
		}
	}
	
	/**
	 * 
	 * <全部确认><功能具体实现>
	 *
	 * @create：2016年9月27日 下午4:15:35
	 * @author：zzq
	 * @param billId
	 */
	public void verifyBillAll(String billId){
		try {
			OrderBillCriteria orderBillCriteria = new OrderBillCriteria();
			orderBillCriteria.createCriteria().andBillIdEqualTo(billId);
			List<OrderBill> billList = orderBillMapper.selectByExample(orderBillCriteria);
			for(OrderBill orderBill:billList){
				if(orderBill.getStatus()==0){
					orderBill.setStatus(1);
				}
				this.updateByPrimaryKeySelective(orderBill, orderBill.getId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("全部修改状态已确认"+e.getMessage());
		}
	}
	
	/**
	 * 
	 * <全部结算><功能具体实现>
	 *
	 * @create：2016年9月27日 下午5:20:24
	 * @author：zzq
	 * @param billId
	 */
	public void balanceBillAll(String billId){
		try {
			OrderBillCriteria orderBillCriteria = new OrderBillCriteria();
			orderBillCriteria.createCriteria().andBillIdEqualTo(billId);
			List<OrderBill> billList = orderBillMapper.selectByExample(orderBillCriteria);
			for(OrderBill orderBill:billList){
				if(orderBill.getStatus()==1){
					orderBill.setStatus(2);
				}
				this.updateByPrimaryKeySelective(orderBill, orderBill.getId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("全部修改状态已结算"+e.getMessage());
		}
	
	}
	
	/**
	 * 
	 * <数据处理><功能具体实现>
	 *
	 * @create：2016年9月27日 下午5:38:51
	 * @author：zzq
	 * @param vo
	 */
	public void handle(OrderBillVo vo,Integer sequence){
		if(vo.getStatus()==0){
			vo.setStatusStr("新账单");
		}
		else if(vo.getStatus()==1){
			vo.setStatusStr("已确认");
		}
		else if(vo.getStatus()==2){
			vo.setStatusStr("已结算");
		}
		vo.setSequence(sequence);
		
	}
	
	/**
	 * 
	 * <导出各种excel><功能具体实现>
	 *
	 * @create：2016年9月28日 上午10:32:55
	 * @author：zzq
	 * @param response
	 * @param billList
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	public void writeExcel(HttpServletResponse response,List<OrderBill> billList) throws IOException{
		List newBillList = new ArrayList<OrderBill>();
		Integer sequence = 0;
		for(OrderBill orderBill:billList){
			orderBill.setFee(orderBill.getFee()/100);
			sequence+=1;
			OrderBillVo orderBillVo = new OrderBillVo();
			BeanUtils.copyProperties(orderBill, orderBillVo);
			//状态处理 序号处理
			this.handle(orderBillVo,sequence);
			newBillList.add(orderBillVo);
		}
		ExcelUtil excelUtil = new ExcelUtil();
		String fileName = "";
		if(billList.get(0).getStatus()==0){
			fileName = excelUtil.createtFileName("导出新账单");
		}
		if(billList.get(0).getStatus()==1){
			fileName = excelUtil.createtFileName("导出待结算账单");
		}
		if(billList.get(0).getStatus()==2){
			fileName = excelUtil.createtFileName("导出已结算账单");
		}
		try {
			fileName = new String(fileName.getBytes("GBK"),"iso8859-1");
			response.reset();
			//指定下载的文件名
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expires", 0);
	        
	        String title="新账单列表";
	        String[] headers = {
	        		"序号::sequence","道馆名称名::statiumName","地区::area","地址::address","电话::phone","订单总数::orderNum",
	        		"总金额::fee","状态::statusStr","户主::userName","账号::bankaccountno",
	        };
	        excelUtil.exportExcel(title, headers, newBillList, response.getOutputStream(), "yyyy-MM-dd");
		
	}
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error("导出excel"+e.getMessage());
		}
	}
	
	/**
	 * 
	 * <是否能够导出><功能具体实现>
	 *
	 * @create：2016年11月4日 下午6:56:18
	 * @author：zzq
	 * @param type
	 * @param billId
	 * @return
	 */
	public boolean isExportOk(String type,String billId){
		Boolean flag = false;
		OrderBillCriteria orderBillCriteria = new OrderBillCriteria();
		orderBillCriteria.createCriteria().andBillIdEqualTo(billId).andStatusEqualTo(Integer.parseInt(type));
		List<OrderBill> billList = orderBillMapper.selectByExample(orderBillCriteria);
		if(billList.size()>0){
			flag = true;
		}
		else{
			return flag;
		}
		return flag;
	}
}
