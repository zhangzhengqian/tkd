package com.lc.zy.ball.boss.framework.statistic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.statistic.service.StatisticUalogService;
import com.lc.zy.ball.boss.framework.statistic.vo.DatasetForPie;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.ex.StatisticUalogEx;
import com.lc.zy.common.cache.RedisPool;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.FreeMarkerUtils;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.wxap.util.MD5Util;

/**
 * 接口访问分析
 * 
 * @author bhg
 */
@Controller
@RequestMapping(value = "/statisticUalog")
public class StatisticUalogController extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(StatisticUalogController.class);
	
	@Resource(name = "oaJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private RedisPool redisPool = null;
	
	
	@Autowired
	private StatisticUalogService statisticUalogService;

	//bhg 定义10组颜色，用于曲线图里图例
	private String[] colorList = {"#90EE90",
								  "#8B0000",
								  "#008B8B",
								  "#FFAEB9",
								  "#00008B",
								  "#FFFF00",
								  "#7FFF00",
								  "#FF00FF",
								  "#87CEFF",
								  "#FFA500",
								  "#CCCCCC",
								  "#90EE90",
								  "#8B0000",
								  "#008B8B",
								  "#FFAEB9",
								  "#00008B",
								  "#FFFF00",
								  "#7FFF00",
								  "#FF00FF"
								  };
	
	// 图用RGB
	private	Map<String,String> channelColor = new HashMap<String,String>();
	/**
	 * 
	 * 表格数据
	 *
	 * @create：09-17
	 * @author： bhg
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "ualogList")
	public String ualogList(HttpServletRequest request,Model model)
			throws Exception {
		// 根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

		//bhg 时间参数直接传递回去，不做类型转变
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

		// 分页
//		int page = WebUtils.getPage(request);
//		int size = WebUtils.getPageSize(request);
		
		
		//首页查询昨日的数据
		if(searchParams.get("EQ_ct")==null){
			searchParams.put("EQ_ct", DateUtils.formatDate(DateUtils.minusDaysToday(1)));
		}

		logger.debug(searchParams.toString());
		Page<StatisticUalogEx> statisticUalogPage = null;
		try {
			statisticUalogPage = statisticUalogService.findUalogList(new PageRequest(1, 100), searchParams, false, true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ualogList error:" + e.getMessage());
		}
		
		model.addAttribute("data", statisticUalogPage);
		// 页面用16#
		String sql = FreeMarkerUtils.format("/template/statistic/ualogChartDaySql.ftl", null);
		List<Map<String,Object>> serviceMethodList = jdbcTemplate.queryForList(sql);
		Map<String,String> channelColorPage = new LinkedHashMap<String,String>();
		int i = 0;
		for(Map sm :serviceMethodList){
			
			channelColor.put(sm.get("service").toString()+sm.get("method"), colorList[i]);
			channelColorPage.put(sm.get("service").toString()+":"+sm.get("method")+sm.get("total"), colorList[i]);
			i++;
		}
		//other数据
		channelColor.put("other", colorList[i]);
		channelColorPage.put("other", colorList[i]);
		model.addAttribute("channelColorPage", channelColorPage);
		return "/statistic/ualogStatistic";
	}
	
	
	/**
	 * 从当前时间往前推，比如取7天的数据，则参数 days=7
	 * 
	 * @param days
	 * @return
	 */
	@RequestMapping(value = "uglogForChart", method = RequestMethod.POST)
	@ResponseBody
	public String uglogForChart() {
		String sql = FreeMarkerUtils.format("/template/statistic/ualogChartDaySql.ftl", null);
		logger.debug(sql);
		String key = MD5Util.MD5Encode(sql, "utf-8");
		logger.debug("key={}",key);
		String rtn = redisPool.getStr(key);
		if(rtn!=null&&!"".equals(rtn)){
			logger.debug("data from cache.");
			return rtn;
		}
		List<Map<String,Object>> xysList = jdbcTemplate.queryForList(sql);
		
		List<DatasetForPie> datasets = new ArrayList<DatasetForPie>();
		int i = 0;
		for( Map<String,Object> xys : xysList ){
			String channel = xys.get("total").toString();
			DatasetForPie dp = new DatasetForPie();
			dp.setValue(channel);
			dp.setColor(channelColor.get(xys.get("service").toString()+xys.get("method")));
			datasets.add(dp);
			
		}
		//other数据
		int otherTotal = statisticUalogService.otherTotal();
		DatasetForPie dp = new DatasetForPie();
		dp.setValue(otherTotal+"");
		dp.setColor(channelColor.get("other"));
		datasets.add(dp);
		String j = MyGson.getInstance().toJson(datasets).toString();
		logger.debug(j);
		redisPool.setStr(key, j);
		return j;
	}

	@RequestMapping(value = "queryByChannel/{channel}", method = RequestMethod.GET)
	public String queryByChannel(@PathVariable String channel,HttpServletRequest request,Model model) {
				// 根据查询条件查询数据
				Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
				searchParams.put("EQ_channel", channel);
				//bhg 时间参数直接传递回去，不做类型转变
				model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

				// 分页
//				int page = WebUtils.getPage(request);
//				int size = 50;
				//按日查询 只查一个月的
				int  flagType = 2;
//				if(searchParams.get("flagType")!=null){
//					flagType = Integer.parseInt(searchParams.get("flagType").toString());
//					searchParams.remove("flagType");
//				}
				//按日和按月查询不需要+时分秒数据
//				if(flagType == 1){
//					// 日期String+时分秒
//					try {
//						parseStringForReg(searchParams, "GTE_ct");
//						parseStringForReg(searchParams, "LTE_ct");
//					} catch (ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//				}
				// 增加参数 当前月份  用大于小于两个参数，并且把当前月份组装成1和31日
				String nowMonth = DateUtils.formatDate(DateUtils.minusDaysToday(2)).substring(0,7);
				if(searchParams.get("nowMonth")!=null){
					nowMonth = searchParams.get("nowMonth").toString();
					searchParams.remove("nowMonth");
			    }
//				searchParams里进两个参数 GTE_ct LTE_ct
				searchParams.put("GTE_ct", nowMonth+"-01");
				searchParams.put("LTE_ct", nowMonth+"-31");
				
				logger.debug(searchParams.toString());
				Page<StatisticUalogEx> statisticUalogPage = null;
				try {
					statisticUalogPage = statisticUalogService.findUalogListByChannel(new PageRequest(1, 31), searchParams, false, true,flagType);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("ualogListForChannel error:" + e.getMessage());
				}
				model.addAttribute("data", statisticUalogPage);
				model.addAttribute("channel", channel);
				
				return "/statistic/ualogForChannel";
	}
	
}
