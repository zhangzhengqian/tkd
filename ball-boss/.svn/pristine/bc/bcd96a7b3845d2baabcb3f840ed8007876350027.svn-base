package com.lc.zy.ball.boss.framework.prize.controller;


import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.reflect.TypeToken;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.prize.service.PrizeService;
import com.lc.zy.ball.boss.framework.prize.vo.PrizeLaveVo;
import com.lc.zy.ball.boss.framework.prize.vo.StatiumPrizeVo;
import com.lc.zy.ball.boss.framework.prize.vo.UserPrizeByStatiumVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.PrizeConfig;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.web.WebUtils;

@Controller
@RequestMapping(value = "prize")
public class PrizeController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(PrizeController.class);

    @Autowired
    private PrizeService prizeService;
    
    @RequestMapping(value = { "", "/" })
    public String index(HttpServletRequest request, Model model) throws Exception {
    	Date now = DateUtils.now();
    	String year = DateUtils.formatDate(now, "yyyy");
    	String month = DateUtils.formatDate(now, "M");
    	List<PrizeConfig> configs = prizeService.getConfig(year, month);
    	request.setAttribute("configs", configs);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
        return "prize/list";
    }
    @RequestMapping(value = "getConfig/{year}/{month}")
    public String getConfig(HttpServletRequest request,@PathVariable String year,@PathVariable String month, Model model) throws Exception {
    	request.setAttribute("year", year);
		request.setAttribute("month", month);
    	List<PrizeConfig> configs = prizeService.getConfig(year, month);
    	request.setAttribute("configs", configs);
    	return "prize/list";
    }
    
    @RequestMapping(value = "config/{year}/{month}")
    public String config(HttpServletRequest request,@PathVariable String year,@PathVariable String month, Model model) throws Exception {
    	request.setAttribute("year", year);
		request.setAttribute("month", month);
    	List<PrizeConfig> configs = prizeService.getConfig(year, month);
    	request.setAttribute("configs", MyGson.getInstance().toJson(configs));
    	return "prize/config";
    }
    
    @RequestMapping(value="setConfig")
	@ResponseBody
	public String setConfig( String paramArray,String year,String month, HttpServletRequest request ) throws Exception {
		logger.debug("paramArray={}",paramArray);
		try{
			prizeService.deleteConfig(year+month);
			Type type = new TypeToken<List<PrizeConfig>>() {}.getType();
			List<PrizeConfig> configs = MyGson.getInstance().fromJson(paramArray, type);
			for(PrizeConfig config:configs){
				config.setId(UUID.get());
				config.setLotteryMonth(year+month);
				config.setPrizeName(URLDecoder.decode(config.getPrizeName(), "utf-8"));
				prizeService.saveConfig(config);
			}
			return "success";
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return "fail";
		}
	}
    
    @RequestMapping(value="delConfig")
	@ResponseBody
	public String delConfig(String year,String month, HttpServletRequest request ) throws Exception {
		try{
			prizeService.deleteConfig(year+month);
			return "success";
		}catch(Exception e){
			return "fail";
		}
	}
    /**
     * 分场馆显示所有抽奖奖品数量列表
     * @Title: prizeListByStatium
     * @author：wpengcheng
     * @date： 2016年10月13日 上午9:43:22  
     * @param request
     * @param model
     * @param year
     * @param month
     * @return
     * @throws Exception
     * @return: String
     * @throws:
     */
    @RequestMapping(value ="getUserPrizeList")
    public String prizeList(HttpServletRequest request, Model model) throws Exception {
    	Date now = DateUtils.now();
    	int page = WebUtils.getPage(request);
        int size = WebUtils.getPageSize(request);
    	String year = DateUtils.formatDate(now, "yyyy");
    	String month = DateUtils.formatDate(now, "M");
    	String lotteryMonth=year+month;
        Page<UserPrizeByStatiumVo> onePage = prizeService.getUserPrizeList(new PageRequest(page, size), lotteryMonth);
        
        List<PrizeLaveVo> prizeLaveVos = prizeService.getPrizeLave(year, month);
    	request.setAttribute("prizeLaves", prizeLaveVos);
    	
    	Set<String> set=prizeService.getPrizeType(lotteryMonth);
    	request.setAttribute("prizeName", set);
        
        request.setAttribute("data", onePage);
        request.setAttribute("year", year);
        request.setAttribute("month", month);
 		return "prize/userPrizeList";
    }
    @RequestMapping(value="getUserPrizeList/{year}/{month}")
	public String prizeListByStatium(HttpServletRequest request,Model model,@PathVariable String year,@PathVariable String month) throws Exception{
    	int page = WebUtils.getPage(request);
        int size = WebUtils.getPageSize(request);
        String lotteryMonth=year+month;
        Page<UserPrizeByStatiumVo> onePage = prizeService.getUserPrizeList(new PageRequest(page, size), lotteryMonth);
        
        List<PrizeLaveVo> prizeLaveVos = prizeService.getPrizeLave(year, month);
    	request.setAttribute("prizeLaves", prizeLaveVos);
        
    	Set<String> set=prizeService.getPrizeType(lotteryMonth);
    	request.setAttribute("prizeName", set);
        request.setAttribute("data", onePage);
        request.setAttribute("year", year);
        request.setAttribute("month", month);
		return "prize/userPrizeList";
	}
    /**
     * 场馆所选月份抽奖详情
     * @Title: statiumPrizeList
     * @author：wpengcheng
     * @date： 2016年10月14日 下午3:18:26  
     * @param request
     * @param model
     * @param statiumId
     * @param year
     * @param month
     * @return
     * @throws Exception
     * @return: String
     * @throws:
     */
    @RequestMapping(value="getStatiumPrizeList/{statiumId}/{year}/{month}")
    public String statiumPrizeList(HttpServletRequest request,Model model,@PathVariable String statiumId,@PathVariable String year,@PathVariable String month)throws Exception{
    	int page = WebUtils.getPage(request);
        int size = WebUtils.getPageSize(request);
        String lotteryMonth=year+month;
        Page<StatiumPrizeVo> onePage = prizeService.getStatiumPrizeList(new PageRequest(page, size),statiumId,lotteryMonth);
        request.setAttribute("data", onePage);
		return "prize/statiumPrizeList";
    }
}