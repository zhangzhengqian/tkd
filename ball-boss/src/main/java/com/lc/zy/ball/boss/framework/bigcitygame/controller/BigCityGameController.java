package com.lc.zy.ball.boss.framework.bigcitygame.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.bigcitygame.service.BigCityGameService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.domain.oa.po.BigCityGame;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.web.WebUtils;

/**
 * 大城市赛Controller
 * 
 *
 */
@Controller
@RequestMapping(value = "/bigcitygame")
public class BigCityGameController extends AbstractController {

    private static Logger logger = LoggerFactory.getLogger(BigCityGameController.class);
    
    private static final String SUCCESS_MSG = "尊敬的用户您好，您通过球友圈报名的“2016中国大城市业余网球团体赛”审核已通过！更多赛事信息请看竞赛规程。";
    private static final String FAIL_MSG = "尊敬的用户您好，您在球友圈报名的“2016中国大城市业余网球团体赛”，审核未通过，详情请咨询：400-898-1081.";
    
    @Autowired
    private BigCityGameService bigCityGameService;

    /**
     * 
     * <审核列表><功能具体实现>
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = { "", "/" })
    public String index(HttpServletRequest request, Model model) {
    	Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		CommonOAUtils.paramesTrim(searchParams);
		Page<BigCityGame> onePage = null;
		try {
			onePage = bigCityGameService.games(searchParams, page, size);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}finally{
			model.addAttribute("data", onePage);
			model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		}
        return "bigcitygame/list";
    }
    
    @RequestMapping(value="audit/{id}/{type}")
	public String audit(@PathVariable String id,@PathVariable String type) throws Exception {
    	int type_ = Integer.parseInt(type);
    	//审核不通过
    	String msg = "";
    	if(type_==0){
    		msg = FAIL_MSG;
    	}else{
    		msg = SUCCESS_MSG;
    	}
    	bigCityGameService.audit(id, type_, msg);
		return "redirect:/bigcitygame";
	}
    
    @RequestMapping(value="delete/{id}")
	public String delete(@PathVariable String id) throws Exception {
    	bigCityGameService.deleteByPrimaryKey(BigCityGame.class, id);
		return "redirect:/bigcitygame";
	}
    
    @RequestMapping(value="update")
	public String audit(BigCityGame game) throws Exception {
    	bigCityGameService.update(game);
		return "redirect:";
	}
    
    @RequestMapping(value="view/{id}")
   	public String view(@PathVariable String id,Model model) throws Exception {
    	BigCityGame game = bigCityGameService.getDetail(id);
    	model.addAttribute("data",game);
   		return "bigcitygame/view";
   	}
    
}
