package com.lc.zy.ball.boss.framework.statistic.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.orders.service.OrderService;
import com.lc.zy.ball.boss.framework.orders.vo.OrderBillItemVo;
import com.lc.zy.ball.boss.framework.orders.vo.OrderVo;
import com.lc.zy.ball.boss.framework.statistic.vo.ChartBean;
import com.lc.zy.ball.boss.framework.statistic.vo.Dataset;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.OrderMapper;
import com.lc.zy.ball.domain.oa.po.OrderCriteria;
import com.lc.zy.common.cache.RedisPool;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.ExportExcelUtil;
import com.lc.zy.common.util.FreeMarkerUtils;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.util.wxap.util.MD5Util;
import com.lc.zy.common.web.WebUtils;

/**
 * 员工管理
 * 
 * @author liangc
 */
@Controller
@RequestMapping(value = "/statistic")
public class StatisticController extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(StatisticController.class);
	
	@Resource(name = "oaJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private RedisPool redisPool = null;
	
	@Autowired
	private OrderMapper orderMapper = null;
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 当天的订单总数
	 * @return
	 */
	@RequestMapping(value = "orderCounterToday", method = RequestMethod.POST)
	@ResponseBody
	public String orderCounterToday() {
		Date now = DateUtils.now();
		String ymd = DateUtils.formatDate(now, "yyyy-MM-dd");
		Date f = DateUtils.getDate(ymd+" 00:00:00");
		OrderCriteria orderCriteria = new OrderCriteria();
		orderCriteria.createCriteria().andCtGreaterThanOrEqualTo(f).andCtLessThan(DateUtils.plusDays(f,1));
		String counter = orderMapper.countByExample(orderCriteria) + "";
		logger.debug(counter);
		return counter;
	}
	
	/**
	 * 从当前时间往前推，比如取7天的数据，则参数 days=7
	 * 
	 * @param days
	 * @return
	 */
	@RequestMapping(value = "ordersChartDay", method = RequestMethod.POST)
	@ResponseBody
	public String ordersChartOfDay(Integer days) {
		Date from = DateUtils.now();
		Date to = DateUtils.minusDays(from, days);
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("from", from);
		root.put("to", to);
		String sql = FreeMarkerUtils.format("/template/statistic/ordersChartDaySql.ftl", root);
		logger.debug(sql);
		String key = MD5Util.MD5Encode(sql, "utf-8");
		logger.debug("key={}",key);
		String rtn = redisPool.getStr(key);
		if(rtn!=null&&!"".equals(rtn)){
			logger.debug("data from cache.");
			return rtn;
		}
		List<Map<String,Object>> xysList = jdbcTemplate.queryForList(sql);
		// 运动类型 与 xy 的映射，这样可以取出每个运动类型的列表
		Map<String,List<Map<String,Object>>> xyMap = new HashMap<String,List<Map<String,Object>>>();
		for( Map<String,Object> xys : xysList ){
			String sport_type = xys.get("sport_type").toString();
			List<Map<String,Object>> xyList = null;
			if( xyMap.get(sport_type) != null ){
				xyList = xyMap.get(sport_type);
			} else {
				xyList = new ArrayList<Map<String,Object>>();
			}
			xyList.add(xys);
			xyMap.put(sport_type, xyList);
		}
		
		List<String> xList = new ArrayList<String>();
		//10 天 填满 x 轴
		for( int i=days ; i>0 ; i--  ){
			Date x = DateUtils.minusDays(from, i);
			String s = DateUtils.formatDate(x);
			xList.add(s);
		}
		
		List<Dataset> datasets = new ArrayList<Dataset>();
		Set<String> xySet = xyMap.keySet();
		for( String k : xySet ){
			String color = sportTypeColor.get(k);
			List<Map<String,Object>> xyList = xyMap.get(k);
			List<Integer> yList = new ArrayList<Integer>();
			// 库中的时间数量映射
			Map<String,Integer> xMap = new HashMap<String,Integer>();
			for(Map<String,Object> xy : xyList){
				logger.debug(color+" ; "+xy.toString());
				String x = xy.get("x").toString();
				String y = xy.get("y").toString();
				xMap.put(x, Integer.parseInt(y));
			}
			// 填满 y 轴，库中没有的填 0
			for( String x : xList ){
				if(xMap.get(x)==null){
					yList.add(0);
				}else{
					yList.add(xMap.get(x));
				}
			}
/*			
			fillColor : "rgba(220,220,220,0.5)",
			strokeColor : "rgba(220,220,220,0.8)",
			highlightFill: "rgba(220,220,220,0.75)",
			highlightStroke: "rgba(220,220,220,1)",
*/
			Dataset d = new Dataset(yList);
			d.setLabel(k);
			
			d.setFillColor(color);
			d.setStrokeColor(color);
			d.setHighlightFill(color);
			d.setHighlightStroke(color);
			
			d.setPointColor(null);
			d.setPointHighlightStroke(null);
			datasets.add(d);
		}
		ChartBean data = new ChartBean(xList, datasets);
		String j = data.toString();
		logger.debug(j);
		redisPool.setStr(key, j);
		return j;
	}

	@RequestMapping(value = "ordersChartHour", method = RequestMethod.POST)
	@ResponseBody
	public String ordersChartOfHour(String date) {
		Date now = DateUtils.now();
		if(StringUtils.isNotEmpty(date)){
			now = DateUtils.getDate(date);
		}
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("now", now);
		String sql = FreeMarkerUtils.format("/template/statistic/ordersChartHourSql.ftl", root);
		logger.debug(sql);
		String h = DateUtils.formatDate(DateUtils.now(),"yyyyMMddHH");
		String key = MD5Util.MD5Encode(sql+h, "utf-8");//每小时一个key
		logger.debug("key={}",key);
		String rtn = redisPool.getStr(key);
		if(rtn!=null&&!"".equals(rtn)){
			logger.debug("data from cache.");
			return rtn;
		}
		List<Map<String,Object>> xysList = jdbcTemplate.queryForList(sql);
		// 运动类型 与 xy 的映射，这样可以取出每个运动类型的列表
		Map<String,List<Map<String,Object>>> xyMap = new HashMap<String,List<Map<String,Object>>>();
		for( Map<String,Object> xys : xysList ){
			String sport_type = xys.get("sport_type").toString();
			List<Map<String,Object>> xyList = null;
			if( xyMap.get(sport_type) != null ){
				xyList = xyMap.get(sport_type);
			} else {
				xyList = new ArrayList<Map<String,Object>>();
			}
			xyList.add(xys);
			xyMap.put(sport_type, xyList);
		}
		
		List<String> xList = new ArrayList<String>();
		//24 个小时 填满 x 轴
		for( int i=0 ; i<=24 ; i++  ){
			String s = ""+i;
			if( i<10 ){
				s = "0"+i;
			}
			xList.add(s);
		}
		List<Dataset> datasets = new ArrayList<Dataset>();
		Set<String> xySet = xyMap.keySet();
		for( String k : xySet ){
			String color = sportTypeColor.get(k);
			List<Map<String,Object>> xyList = xyMap.get(k);
			List<Integer> yList = new ArrayList<Integer>();
			// 库中的时间数量映射
			Map<String,Integer> xMap = new HashMap<String,Integer>();
			for(Map<String,Object> xy : xyList){
				logger.debug(color+" ; "+xy.toString());
				String x = xy.get("x").toString();
				String y = xy.get("y").toString();
				xMap.put(x, Integer.parseInt(y));
			}
			// 填满 y 轴，库中没有的填 0
			for( String x : xList ){
				if(xMap.get(x)==null){
					yList.add(0);
				}else{
					yList.add(xMap.get(x));
				}
			}
			Dataset d = new Dataset(yList);
			d.setLabel(k);
			d.setStrokeColor(color);
			d.setPointColor(color);
			d.setPointHighlightStroke(color);
			datasets.add(d);
		}
		ChartBean data = new ChartBean(xList, datasets);
		String j = data.toString();
		logger.debug(j);
		redisPool.setStr(key, j);
		return j;
	}
	
	
	
	private Map<String,String> sportTypeColor = new HashMap<String,String>();
	{
		/*
		<span class="label " style="background-color: #3CB371;" >网球</span>
		<span class="label " style="background-color: #A52A2A;" >羽毛球</span>
		<span class="label " style="background-color: #27408B;" >乒乓球</span>
		<span class="label " style="background-color: #4682B4;" >台球</span>
		<span class="label " style="background-color: #CDAD00;" >篮球</span>
		<span class="label " style="background-color: #006400;" >足球</span>
		<span class="label " style="background-color: #8B4726;" >保龄球</span>
		<span class="label " style="background-color: #707070;" >高尔夫</span>
		
		篮球(BASKETBALL)
		足球(FOOTBALL)
		羽毛球(BADMINTON)
		网球(TENNIS)
		乒乓球(TABLE_TENNIS)
		台球(BILLIARDS)
		保龄球(BOWLING)
		高尔夫(GOLF)
		*/
		sportTypeColor.put("BADMINTON", "rgba(165,42,42,0.6)");
		sportTypeColor.put("TENNIS", "rgba(60,179,113,0.6)");
		sportTypeColor.put("TABLE_TENNIS", "rgba(39,64,139,0.6)");
		sportTypeColor.put("BILLIARDS", "rgba(70,130,180,0.6)");
		sportTypeColor.put("BOWLING", "rgba(139,71,38,0.6)");
		sportTypeColor.put("GOLF", "rgba(112,112,112,0.6)");
		sportTypeColor.put("FOOTBALL", "rgba(0,100,0,0.6)");
		sportTypeColor.put("BASKETBALL", "rgba(205,173,0,0.6)");
	}
	
	
	@RequestMapping(value = "statisticNumChartDay", method = RequestMethod.POST)
	@ResponseBody
	public String statisticNumChartDay(String start) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		String[] date = DateUtils.getTodayStr().split("-");
		//获取当前月份的最后一天
		String 	startNew = start; 
		String 	end = date[0]+"-"+date[1]+"-"+DateUtils.getMonthFirstLastDay(new Date())[1];
		if(StringUtils.isBlank(start)){
			//获取当前月份第一天和最后一天
			startNew = date[0]+"-"+date[1]+"-01";
		}else{
			startNew = start +"-01";
			end = start+"-"+DateUtils.getMonthFirstLastDay(DateUtils.getDate(startNew))[1];
		}
		c1.setTime(DateUtils.getDate(startNew));
		c2.setTime(DateUtils.getDate(end));
		
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("start", startNew);
		root.put("end", end);
		String sql1 = FreeMarkerUtils.format("/template/statistic/activity_num_day.ftl", root);
		logger.debug(sql1);
		String sql2 = FreeMarkerUtils.format("/template/statistic/order_num_day.ftl", root);
		logger.debug(sql2);
		String sql3 = FreeMarkerUtils.format("/template/statistic/register_num_day.ftl", root);
		logger.debug(sql3);
		String sql4 = FreeMarkerUtils.format("/template/statistic/rh_num_day.ftl", root);
		logger.debug(sql4);
//		String h = DateUtils.formatDate(DateUtils.now(),"yyyyMMdd");
//		String key = MD5Util.MD5Encode(sql1+h, "utf-8");//每小时一个key
//		logger.debug("key={}",key);
//		String rtn = redisPool.getStr(key);
//		if(rtn!=null&&!"".equals(rtn)){
//			logger.debug("data from cache.");
//			return rtn;
//		}
		List<Map<String,Object>> xysList1 = jdbcTemplate.queryForList(sql1);
		List<Map<String,Object>> xysList2 = jdbcTemplate.queryForList(sql2);
		List<Map<String,Object>> xysList3 = jdbcTemplate.queryForList(sql3);
		List<Map<String,Object>> xysList4 = jdbcTemplate.queryForList(sql4);
		Map<String,List<Map<String,Object>>> xyMap = new HashMap<String,List<Map<String,Object>>>();
		xyMap.put("activity", xysList1);
		xyMap.put("register", xysList3);
		xyMap.put("rh", xysList4);
		xyMap.put("os", xysList2);
		
		// 运动类型 与 xy 的映射，这样可以取出每个运动类型的列表
		
		List<String> xList = new ArrayList<String>();
		
		while(c1.getTime().compareTo(c2.getTime())<=0){
			String currentDate = DateUtil.formatDate(c1.getTime(), "yyyy-MM-dd");
			xList.add(currentDate);
			c1.add(Calendar.DATE,1);
		}
		
		List<Dataset> datasets = new ArrayList<Dataset>();
		Set<String> xySet = xyMap.keySet();
		for( String k : xySet ){
			String color = statisticColor.get(k);
			List<Map<String,Object>> xyList = xyMap.get(k);
			List<Integer> yList = new ArrayList<Integer>();
			// 库中的时间数量映射
			Map<String,Integer> xMap = new HashMap<String,Integer>();
			for(Map<String,Object> xy : xyList){
				logger.debug(color+" ; "+xy.toString());
				String x = xy.get("title").toString();
				String y = xy.get("num").toString();
				xMap.put(x, Integer.parseInt(y));
			}
			// 填满 y 轴，库中没有的填 0
			for( String x : xList ){
				if(xMap.get(x)==null){
					yList.add(0);
				}else{
					yList.add(xMap.get(x));
				}
			}
			Dataset d = new Dataset(yList);
			d.setLabel(k);
			d.setStrokeColor(color);
			d.setPointColor(color);
			d.setPointHighlightStroke(color);
			datasets.add(d);
		}
		ChartBean data = new ChartBean(xList, datasets);
		String j = data.toString();
		logger.debug(j);
//		redisPool.setStr(key, j);
		return j;
	}
	
	@RequestMapping(value = "orderNumChartDay", method = RequestMethod.POST)
	@ResponseBody
	public String orderNumChartDay(String start) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		String[] date = DateUtils.getTodayStr().split("-");
		//获取当前月份的最后一天
		String 	startNew = start; 
		String 	end = date[0]+"-"+date[1]+"-"+DateUtils.getMonthFirstLastDay(new Date())[1];
		if(StringUtils.isBlank(start)){
			//获取当前月份第一天和最后一天
			startNew = date[0]+"-"+date[1]+"-01";
		}else{
			startNew = start +"-01";
			end = start+"-"+DateUtils.getMonthFirstLastDay(DateUtils.getDate(startNew))[1];
		}
		c1.setTime(DateUtils.getDate(startNew));
		c2.setTime(DateUtils.getDate(end));
		
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("start", startNew);
		root.put("end", end);
		String sql = FreeMarkerUtils.format("/template/statistic/order_sum_day.ftl", root);
		logger.debug(sql);
//		String h = DateUtils.formatDate(DateUtils.now(),"yyyyMMdd");
//		String key = MD5Util.MD5Encode(sql+h, "utf-8");//每小时一个key
//		logger.debug("orderNumChartDay_key={}",key);
//		String rtn = redisPool.getStr(key);
//		if(rtn!=null&&!"".equals(rtn)){
//			logger.debug("data from cache.");
//			return rtn;
//		}
		List<Map<String,Object>> xysList = jdbcTemplate.queryForList(sql);
		
		List<String> xList = new ArrayList<String>();
		
		while(c1.getTime().compareTo(c2.getTime())<=0){
			String currentDate = DateUtil.formatDate(c1.getTime(), "yyyy-MM-dd");
			xList.add(currentDate);
			c1.add(Calendar.DATE,1);
		}
		
		
		Map<String,List<Map<String,Object>>> colMap = new HashMap<String, List<Map<String,Object>>>();
		for(Map<String,Object> map : xysList){
			Date ct = (Date)map.get("title");
			String title = DateUtils.formatDate(ct, "yyyy-MM-dd");
			//订单总额
			int oz = map.get("num1")!=null?(((BigDecimal)map.get("num1")).divide(new BigDecimal(100))).intValue():0;
			if(colMap.containsKey("oz")){
				List<Map<String,Object>> yList = colMap.get("oz");
				Map<String,Object> temp = new HashMap<String, Object>();
				temp.put("title", title);
				temp.put("num", oz);
				yList.add(temp);
				colMap.put("oz", yList);
			}else{
				List<Map<String,Object>> yList = new ArrayList<Map<String,Object>>();
				Map<String,Object> temp = new HashMap<String, Object>();
				temp.put("title", title);
				temp.put("num", oz);
				yList.add(temp);
				colMap.put("oz", yList);
			}
			//订单毛利润
			int om = map.get("num2")!=null?(((BigDecimal)map.get("num2")).divide(new BigDecimal(100))).intValue():0;
			if(colMap.containsKey("om")){
				List<Map<String,Object>> yList = colMap.get("om");
				Map<String,Object> temp = new HashMap<String, Object>();
				temp.put("title", title);
				temp.put("num", om);
				yList.add(temp);
				colMap.put("om", yList);
			}else{
				List<Map<String,Object>> yList = new ArrayList<Map<String,Object>>();
				Map<String,Object> temp = new HashMap<String, Object>();
				temp.put("title", title);
				temp.put("num", om);
				yList.add(temp);
				colMap.put("om", yList);
			}
			//订单补贴
			int ob = map.get("num3")!=null?(((BigDecimal)map.get("num3")).divide(new BigDecimal(100))).intValue():0;
			if(colMap.containsKey("ob")){
				List<Map<String,Object>> yList = colMap.get("ob");
				Map<String,Object> temp = new HashMap<String, Object>();
				temp.put("title", title);
				temp.put("num", ob);
				yList.add(temp);
				colMap.put("ob", yList);
			}else{
				List<Map<String,Object>> yList = new ArrayList<Map<String,Object>>();
				Map<String,Object> temp = new HashMap<String, Object>();
				temp.put("title", title);
				temp.put("num", ob);
				yList.add(temp);
				colMap.put("ob", yList);
			}
			//订单优惠券
			int oy = map.get("num4")!=null?(((BigDecimal)map.get("num4")).divide(new BigDecimal(100))).intValue():0;
			if(colMap.containsKey("oy")){
				List<Map<String,Object>> yList = colMap.get("oy");
				Map<String,Object> temp = new HashMap<String, Object>();
				temp.put("title", title);
				temp.put("num", oy);
				yList.add(temp);
				colMap.put("oy", yList);
			}else{
				List<Map<String,Object>> yList = new ArrayList<Map<String,Object>>();
				Map<String,Object> temp = new HashMap<String, Object>();
				temp.put("title", title);
				temp.put("num", oy);
				yList.add(temp);
				colMap.put("oy", yList);
			}

		}
		
		List<Dataset> datasets = new ArrayList<Dataset>();
		Set<String> xySet = colMap.keySet();
		for( String k : xySet ){
			String color = statisticColor.get(k);
			List<Map<String,Object>> xyList = colMap.get(k);
			List<Integer> yList = new ArrayList<Integer>();
			// 库中的时间数量映射
			Map<String,Integer> xMap = new HashMap<String,Integer>();
			for(Map<String,Object> xy : xyList){
				logger.debug(color+" ; "+xy.toString());
				String x = xy.get("title").toString();
				String y = xy.get("num").toString();
				xMap.put(x, Integer.parseInt(y));
			}
			// 填满 y 轴，库中没有的填 0
			for( String x : xList ){
				if(xMap.get(x)==null){
					yList.add(0);
				}else{
					yList.add(xMap.get(x));
				}
			}
			Dataset d = new Dataset(yList);
			d.setLabel(k);
			d.setStrokeColor(color);
			d.setPointColor(color);
			d.setPointHighlightStroke(color);
			datasets.add(d);
		}
		
		ChartBean data = new ChartBean(xList, datasets);
		String j = data.toString();
		logger.debug(j);
//		redisPool.setStr(key, j);
		return j;
	}
	
	@RequestMapping(value = "orderNumChartDay2", method = RequestMethod.POST)
	@ResponseBody
	public String orderNumChartDay2(String start) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		String[] date = DateUtils.getTodayStr().split("-");
		//获取当前月份的最后一天
		String 	startNew = start; 
		String 	end = date[0]+"-"+date[1]+"-"+DateUtils.getMonthFirstLastDay(new Date())[1];
		if(StringUtils.isBlank(start)){
			//获取当前月份第一天和最后一天
			startNew = date[0]+"-"+date[1]+"-01";
		}else{
			startNew = start +"-01";
			end = start+"-"+DateUtils.getMonthFirstLastDay(DateUtils.getDate(startNew))[1];
		}
		c1.setTime(DateUtils.getDate(startNew));
		c2.setTime(DateUtils.getDate(end));
		
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("start", startNew);
		root.put("end", end);
		String sql = FreeMarkerUtils.format("/template/statistic/order_sum_day.ftl", root);
		logger.debug(sql);
//		String h = DateUtils.formatDate(DateUtils.now(),"yyyyMMdd");
//		String key = MD5Util.MD5Encode(sql+h, "utf-8");//每小时一个key
//		logger.debug("orderNumChartDay_key={}",key);
//		String rtn = redisPool.getStr(key);
//		if(rtn!=null&&!"".equals(rtn)){
//			logger.debug("data from cache.");
//			return rtn;
//		}
		List<Map<String,Object>> xysList = jdbcTemplate.queryForList(sql);
		
		List<String> xList = new ArrayList<String>();
		
		while(c1.getTime().compareTo(c2.getTime())<=0){
			String currentDate = DateUtil.formatDate(c1.getTime(), "yyyy-MM-dd");
			xList.add(currentDate);
			c1.add(Calendar.DATE,1);
		}
		
		
		Map<String,List<Map<String,Object>>> colMap = new HashMap<String, List<Map<String,Object>>>();
		for(Map<String,Object> map : xysList){
			Date ct = (Date)map.get("title");
			String title = DateUtils.formatDate(ct, "yyyy-MM-dd");
			//订单毛利润
			int om = map.get("num2")!=null?(((BigDecimal)map.get("num2")).divide(new BigDecimal(100))).intValue():0;
			if(colMap.containsKey("om")){
				List<Map<String,Object>> yList = colMap.get("om");
				Map<String,Object> temp = new HashMap<String, Object>();
				temp.put("title", title);
				temp.put("num", om);
				yList.add(temp);
				colMap.put("om", yList);
			}else{
				List<Map<String,Object>> yList = new ArrayList<Map<String,Object>>();
				Map<String,Object> temp = new HashMap<String, Object>();
				temp.put("title", title);
				temp.put("num", om);
				yList.add(temp);
				colMap.put("om", yList);
			}
			//订单补贴
			int ob = map.get("num3")!=null?(((BigDecimal)map.get("num3")).divide(new BigDecimal(100))).intValue():0;
			if(colMap.containsKey("ob")){
				List<Map<String,Object>> yList = colMap.get("ob");
				Map<String,Object> temp = new HashMap<String, Object>();
				temp.put("title", title);
				temp.put("num", ob);
				yList.add(temp);
				colMap.put("ob", yList);
			}else{
				List<Map<String,Object>> yList = new ArrayList<Map<String,Object>>();
				Map<String,Object> temp = new HashMap<String, Object>();
				temp.put("title", title);
				temp.put("num", ob);
				yList.add(temp);
				colMap.put("ob", yList);
			}
		}
		
		List<Dataset> datasets = new ArrayList<Dataset>();
		Set<String> xySet = colMap.keySet();
		for( String k : xySet ){
			String color = statisticColor.get(k);
			List<Map<String,Object>> xyList = colMap.get(k);
			List<Integer> yList = new ArrayList<Integer>();
			// 库中的时间数量映射
			Map<String,Integer> xMap = new HashMap<String,Integer>();
			for(Map<String,Object> xy : xyList){
				logger.debug(color+" ; "+xy.toString());
				String x = xy.get("title").toString();
				String y = xy.get("num").toString();
				xMap.put(x, Integer.parseInt(y));
			}
			// 填满 y 轴，库中没有的填 0
			for( String x : xList ){
				if(xMap.get(x)==null){
					yList.add(0);
				}else{
					yList.add(xMap.get(x));
				}
			}
			Dataset d = new Dataset(yList);
			d.setLabel(k);
			d.setStrokeColor(color);
			d.setPointColor(color);
			d.setPointHighlightStroke(color);
			datasets.add(d);
		}
		
		ChartBean data = new ChartBean(xList, datasets);
		String j = data.toString();
		logger.debug(j);
//		redisPool.setStr(key, j);
		return j;
	}
	
	private Map<String,String> statisticColor = new HashMap<String,String>();
	{
		/*
		<span class="label " style="background-color: #3CB371;" >网球</span>
		<span class="label " style="background-color: #A52A2A;" >羽毛球</span>
		<span class="label " style="background-color: #27408B;" >乒乓球</span>
		<span class="label " style="background-color: #4682B4;" >台球</span>
		<span class="label " style="background-color: #CDAD00;" >篮球</span>
		<span class="label " style="background-color: #006400;" >足球</span>
		<span class="label " style="background-color: #8B4726;" >保龄球</span>
		<span class="label " style="background-color: #707070;" >高尔夫</span>
		
		篮球(BASKETBALL)
		足球(FOOTBALL)
		羽毛球(BADMINTON)
		网球(TENNIS)
		乒乓球(TABLE_TENNIS)
		台球(BILLIARDS)
		保龄球(BOWLING)
		高尔夫(GOLF)
		*/
		statisticColor.put("activity", "rgba(165,42,42,0.6)");
		statisticColor.put("register", "rgba(60,179,113,0.6)");
		statisticColor.put("rh", "rgba(39,64,139,0.6)");
		statisticColor.put("os", "rgba(70,130,180,0.6)");
		statisticColor.put("oz", "rgba(139,71,38,0.6)");
		statisticColor.put("om", "rgba(112,112,112,0.6)");
		statisticColor.put("ob", "rgba(0,100,0,0.6)");
		statisticColor.put("oy", "rgba(205,173,0,0.6)");
	}
	
	@RequestMapping(value="orderStatistic")
	public ModelAndView statisticsCharts(String type,HttpServletRequest request) throws Exception{
		ModelAndView model = new ModelAndView("statistic/orderStatisticCharts");
		return model;
	}
	
	@RequestMapping(value="order")
	public ModelAndView statistics(String type,HttpServletRequest request) throws Exception{
		ModelAndView model = new ModelAndView("statistic/orderStatistic");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		int page = WebUtils.getPage(request);
        int size = WebUtils.getPageSize(request);
        Page<OrderVo> data = orderService.statistics(new PageRequest(page, size),searchParams);
        model.addObject("data", data);
        model.addObject("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return model;
	}
	
	@RequestMapping(value="orderExport")
	public void export(HttpServletResponse response,HttpServletRequest request) throws Exception{
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		try{
			List<OrderVo> orders = orderService.exports(searchParams);
	    	if(orders != null && orders.size()>0){
				writeExcel(response,orders);
	    	}
    	}catch (Exception e){
    		e.printStackTrace();
    		logger.error(e.getMessage());
		}
	}
	
	/**
     * 定义导出Excel的样式
     * @param response
     * @param voList
     * @throws Exception
     */
	public Map<String,Object> writeExcel(HttpServletResponse response, List<OrderVo> voList) throws Exception{
		Map<String, Object> map = new HashMap<String,Object>();
		String uuid = UUID.get();
		String fileName = uuid+".xls";  
        fileName = new String(fileName.getBytes("GBK"), "iso8859-1");  
        response.reset();  
        response.setHeader("Content-Disposition", "attachment;filename="  
                + fileName);// 指定下载的文件名  
        response.setContentType("application/vnd.ms-excel");  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        OutputStream output = response.getOutputStream();  
        BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);  
  
        // 定义单元格报头  
        String worksheetTitle = "订单统计(" + DateUtil.formatDate(new Date(), "yyyy-MM-dd") + ")";  
  
        HSSFWorkbook wb = new HSSFWorkbook();  
  
        // 创建单元格样式  
        HSSFCellStyle cellStyleTitle = wb.createCellStyle();  
        // 指定单元格居中对齐  
        cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        // 指定单元格垂直居中对齐  
        cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        // 指定当单元格内容显示不下时自动换行  
        cellStyleTitle.setWrapText(true);  
        // ------------------------------------------------------------------  
        HSSFCellStyle cellStyle = wb.createCellStyle();  
        // 指定单元格居中对齐  
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        // 指定单元格垂直居中对齐  
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        // 指定当单元格内容显示不下时自动换行  
        cellStyle.setWrapText(true);  
        // ------------------------------------------------------------------  
        // 设置单元格字体  
        HSSFFont font = wb.createFont();  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        font.setFontName("宋体");  
        font.setFontHeight((short) 200);
        cellStyleTitle.setFont(font);  
  
        // 工作表名  
        String sequence = "序号"; 
        String orderTime = "下单日期";
        String editTime = "修改日期";
        String orderNum = "订单号";  
        String statiumName = "球馆名称";
        String cooperNum = "合作次数";
        String username ="用户名";
        String userPhone = "用户电话";  
        String orderDate = "预约日期";
        String orderTimes = "预约时间";
        String sportType = "品类";
        String orderstatium = "所订场次";
        String fee = "消费金额(元)";  
        String qiuyouFee = "球友金额(元)"; 
        String couponFee = "优惠券金额";
		String acountFee = "账户支付金额(元)";
		String bounsAccountFee = "奖金账户支付金额(元)";
        String costPrice = "成本金额";
        String subsidies = "补贴金额";
        String paidType = "支付方式";  
        String status = "状态";
        String areaStr = "球馆地区"; 
        String address = "球馆地址";
        String ordersType = "订单类型";
        String reason = "原因/备注";
        String makesure = "是否解决";
  
        HSSFSheet sheet = wb.createSheet();  
        ExportExcelUtil exportExcel = new ExportExcelUtil(wb, sheet);  
        // 创建报表头部  
        exportExcel.createNormalHead(worksheetTitle, 8);  
        // 定义第一行  
        HSSFRow row1 = sheet.createRow(1);  
        HSSFCell cell1 = row1.createCell(0);  
  
        //第一行第1列  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(sequence));  
        
        //第一行第2列  
        cell1 = row1.createCell(1);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(orderTime));
        
        //第一行第2列  
        cell1 = row1.createCell(2);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(editTime));  
  
        //第一行第3列  
        cell1 = row1.createCell(3);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(orderNum));  
        
        //第一行第4列  
        cell1 = row1.createCell(4);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(statiumName)); 
        
        //第一行第5列  
        cell1 = row1.createCell(5);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(cooperNum));
        
        //第一行第6列  
        cell1 = row1.createCell(6);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(username));  
  
        //第一行第7列  
        cell1 = row1.createCell(7);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(userPhone));  
  
        //第一行第8列  
        cell1 = row1.createCell(8);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(orderDate));  
        
        //第一行第9列  
        cell1 = row1.createCell(9);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(orderTimes));  
        
        //第一行第10列  
        cell1 = row1.createCell(10);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(sportType));  
        
      //第一行第10列  
        cell1 = row1.createCell(11);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(orderstatium));  
        
        //第一行第11列  
        cell1 = row1.createCell(12);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(fee));  
        
        //第一行第11列  
        cell1 = row1.createCell(13);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(qiuyouFee));  

        //第一行第11列  
        cell1 = row1.createCell(14);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(couponFee));

		//第一行第11列
		cell1 = row1.createCell(15);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(acountFee));

		//第一行第11列
		cell1 = row1.createCell(16);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(bounsAccountFee));
        
      //第一行第11列  
        cell1 = row1.createCell(17);
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(costPrice));
        
      //第一行第11列  
        cell1 = row1.createCell(18);
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(subsidies));
        
        //第一行第11列  
        cell1 = row1.createCell(19);
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(paidType));  
        
        
        //第一行第11列  
        cell1 = row1.createCell(20);
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(status));  
        
        //第一行第4列  
        cell1 = row1.createCell(21);
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(areaStr));
        
      //第一行第4列  
        cell1 = row1.createCell(22);
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(address));
        
        //第一行第11列  
        cell1 = row1.createCell(23);
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(ordersType));  
      
        //第一行第11列  
        cell1 = row1.createCell(24);
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(reason));  
        
        
        //第一行第11列  
        cell1 = row1.createCell(25);
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(makesure));  
        
          
        //定义第二行  
        HSSFRow row = sheet.createRow(2);  
        HSSFCell cell = row.createCell(1);
        int i = 1;
        HSSFCellStyle dateCellStyle=wb.createCellStyle();
        short df=wb.createDataFormat().getFormat("yyyy-mm-dd HH:mm:ss"); 
        dateCellStyle.setDataFormat(df);
        HSSFCellStyle dateCellStyle_=wb.createCellStyle();
        short df_=wb.createDataFormat().getFormat("yyyy-mm-dd"); 
        dateCellStyle_.setDataFormat(df_);
        for(OrderVo orderVo:voList){
            row = sheet.createRow(i+1);
            cell = row.createCell(0);  
            cell.setCellValue(new HSSFRichTextString(i+""));  
            i += 1;
            cell.setCellStyle(cellStyle);
            
            cell = row.createCell(1);  
            cell.setCellStyle(dateCellStyle);
        	cell.setCellValue(orderVo.getCt());
        	
        	
        	cell = row.createCell(2);  
            cell.setCellStyle(dateCellStyle);
        	cell.setCellValue(orderVo.getEt());
              
            cell = row.createCell(3);  
            cell.setCellStyle(cellStyle);  
        	cell.setCellValue(new HSSFRichTextString(orderVo.getId())); 
        	
            cell = row.createCell(4);  
            cell.setCellStyle(cellStyle);  
            cell.setCellValue(new HSSFRichTextString(orderVo.getName() + ""));  
            
            cell = row.createCell(5);  
            cell.setCellStyle(cellStyle);  
            cell.setCellValue(new HSSFRichTextString(orderVo.getCooperateNum() + "")); 
            
            cell = row.createCell(6);  
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(orderVo.getUsername())); 
            
            cell = row.createCell(7);  
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(orderVo.getPhone()));  
            
            cell = row.createCell(8);  
            cell.setCellStyle(dateCellStyle_);
            cell.setCellValue(DateUtil.parse(orderVo.getOrderDate(),"yyyy-MM-dd",null));
            
            cell = row.createCell(9);  
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(orderVo.getOrderTime()));
            
            cell = row.createCell(10);  
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(orderVo.getSportType()));
            
            cell = row.createCell(11);  
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(orderVo.getPeriodNum()+""));
            
            cell = row.createCell(12);  
            cell.setCellStyle(cellStyle);
            if(orderVo.getFinalFee() != null){
            	cell.setCellValue(orderVo.getFinalFee());  
            }else{
            	cell.setCellValue(0);
            }
            
            cell = row.createCell(13);  
            cell.setCellStyle(cellStyle);
            if(orderVo.getQiuyouFee() != null){
            	cell.setCellValue(orderVo.getQiuyouFee());  
            }else{
            	cell.setCellValue(0);
            }

			cell = row.createCell(14);
			cell.setCellStyle(cellStyle);
			if(orderVo.getSubAmount() != null){
				cell.setCellValue(orderVo.getSubAmount());
			}else{
				cell.setCellValue(0);
			}


			cell = row.createCell(15);
			cell.setCellStyle(cellStyle);
			if(orderVo.getAcountFee() != null){
				cell.setCellValue(orderVo.getAcountFee());
			}else{
				cell.setCellValue(0);
			}
            
            cell = row.createCell(16);
            cell.setCellStyle(cellStyle);
            if(orderVo.getBounsAccountFee() != null){
            	cell.setCellValue(orderVo.getBounsAccountFee());
            }else{
            	cell.setCellValue(0);
            }
            
            cell = row.createCell(17);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(orderVo.getCostPrice());
            
            cell = row.createCell(18);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(orderVo.getSubsidies());
            
            cell = row.createCell(19);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(orderVo.getPayTypeStr()));
            
            cell = row.createCell(20);
            cell.setCellStyle(cellStyle);
            String orderStatus = orderVo.getStatus();
			if(orderStatus.equals(Constants.OrderStatus.PAIED)){
				cell.setCellValue(new HSSFRichTextString("已支付"));
			}else if(orderStatus.equals(Constants.OrderStatus.PLAYING)){
				cell.setCellValue(new HSSFRichTextString("交易完成"));
			}else if(orderStatus.equals(Constants.OrderStatus.VERIFY)){
				cell.setCellValue(new HSSFRichTextString("已确认"));
			}
			
			cell = row.createCell(21);
            cell.setCellStyle(cellStyle);  
            cell.setCellValue(new HSSFRichTextString(orderVo.getAreaStr() + "")); 
            
            cell = row.createCell(22);
            cell.setCellStyle(cellStyle);  
            cell.setCellValue(new HSSFRichTextString(orderVo.getAddress() + ""));  
            
            cell = row.createCell(23);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(orderVo.getOrdersTypeStr()));
            
            cell = row.createCell(24);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(orderVo.getReason()));
            
            cell = row.createCell(25);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(""));
      
        }
        try {  
            bufferedOutPut.flush();  
            wb.write(bufferedOutPut);
            return null;
        }
        catch (IOException e) { 
        	e.printStackTrace();
            logger.error(e.getMessage());
            map.put(Constants.Result.RESULT, "false");
            map.put(Constants.Result.REASON, "导出Excel失败,请再试一次");
            return map;
        } finally {
        	bufferedOutPut.close();
        }
	}
	
	public static void main(String[] args) {
		Date now = DateUtils.now();
		Calendar c1 = Calendar.getInstance();
		c1.setTime(now);
		c1.add(Calendar.DATE, -1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(now);
		c2.add(Calendar.DATE, -7);
		
		while(c2.getTime().compareTo(c1.getTime())<=0){
			String currentDate = DateUtil.formatDate(c2.getTime(), "yyyy-MM-dd");
			System.out.println(currentDate);
			c2.add(Calendar.DATE,1);
		}
	}
}
