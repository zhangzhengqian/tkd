package com.lc.zy.ball.boss.framework.corps.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.corps.service.CorpsService;
import com.lc.zy.ball.boss.framework.corps.vo.CorpsVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.domain.oa.po.CorpsInfo;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.web.WebUtils;

/**
 * 战队Controller
 * 
 *
 */
@Controller
@RequestMapping(value = "/corps")
public class CorpsController extends AbstractController {

    private static Logger logger = LoggerFactory.getLogger(CorpsController.class);
    
    @Autowired
    private CorpsService corpsService;

    /**
     * 
     * <战队列表><功能具体实现>
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
		Page<CorpsVo> onePage = null;
		try {
			onePage = corpsService.listCorps(searchParams, page, size);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}finally{
			model.addAttribute("data", onePage);
			model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		}
        return "corps/corpsList";
    }
    /**
     * 初始化增加战队页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value="add")
	public String add() throws Exception {
		return "corps/add";
	}

    /**
     * 保存战队
     * @param info
     * @param memberList
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="save")
	public String save(CorpsInfo info,String memberList,Model model) throws Exception {
    	if(StringUtils.isNotBlank(info.getId())){
    		corpsService.update(info, memberList);
    	}else{
    		corpsService.save(info, memberList);
    	}
		return "redirect:/corps/";
	}
    
    /**
     * 解散战队
     * @param corpsId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="disband/{corpsId}")
    public String disband(@PathVariable String corpsId,Model model) throws Exception {
    	corpsService.disband(corpsId);
    	return "redirect:/corps/";
    }
    
    /**
     * 查看战队
     * @param corpsId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="view/{corpsId}")
    public String view(@PathVariable String corpsId,Model model) throws Exception {
    	Map<String,Object> result = corpsService.view(corpsId);
    	model.addAttribute("result", result);
    	return "corps/view";
    }
    
    /**
     * 修改战队
     * @param corpsId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="update/{corpsId}")
    public String update(@PathVariable String corpsId,Model model) throws Exception {
    	Map<String,Object> result = corpsService.get(corpsId);
    	model.addAttribute("result", result);
    	return "corps/update";
    }
    
    /**
     * 查看赛程
     * @param corpsId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="games_dlg/{corpsId}")
    public String games(@PathVariable String corpsId,Model model) throws Exception {
    	Map<String,List<Map<String,Object>>> result = corpsService.getVs(corpsId);
    	model.addAttribute("result", result);
    	return "corps/games_query_dlg";
    }
    
    public static void main(String[] args) {
		System.out.println(UUID.get());
	}
}
