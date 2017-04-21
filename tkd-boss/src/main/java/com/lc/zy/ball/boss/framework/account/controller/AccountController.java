package com.lc.zy.ball.boss.framework.account.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.web.Servlets;

import com.google.gson.JsonObject;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.Zonemap;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.account.AccountVo;
import com.lc.zy.ball.boss.framework.account.service.AccountService;
import com.lc.zy.ball.boss.framework.system.service.OrgService;
import com.lc.zy.ball.boss.framework.system.service.RoleService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.Area;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.common.web.WebUtils;

@Controller
@RequestMapping(value="/account")
public class AccountController extends AbstractController {
	
	private static Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private AccountService accountService;
	/**
	 * 
	 * <账户列表><功能具体实现>
	 *
	 * @create：2017年4月12日 下午5:54:21
	 * @author：ywl
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"","/","/list"})
	public String list(HttpServletRequest request,Model model){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<User> user = null;
		try {
			user = accountService.list(new PageRequest(page, size), searchParams);
			model.addAttribute("data", user);
			String searchParamsStr = Servlets.encodeParameterStringWithPrefix(searchParams, "search_");
			model.addAttribute("searchParams", searchParamsStr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("账户列表：{}"+e.getMessage());
		}
		return "/accountMagger/accountList";
	}
	/**
	 * 
	 * <><功能具体实现>
	 *
	 * @create：2017年4月12日 下午4:35:43
	 * @author：ywl
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="sign" ,method=RequestMethod.GET)
	public String sign(Model model) throws Exception{
		String roleId = SessionUtil.currentUserRole();
		model.addAttribute("user", SessionUtil.currentUser());
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("action", "create");
		model.addAttribute("orgTree", orgService.getOrgTree(null));
		model.addAttribute("roleId", roleId);
		return "/accountMagger/accountForm";
	}
	/**
	 * 
	 * <保存账户><功能具体实现>
	 *
	 * @create：2017年4月13日 下午2:09:13
	 * @author：ywl
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(AccountVo vo){
		try {
			Map<String, String> areaMap = Zonemap.split(vo.getAreacode());
			if (vo.getRoleIds().equals("1004") || vo.getRoleIds().equals("1003") || vo.getRoleIds().equals("1002")){
				vo.setProvince(areaMap.get("province"));
				if (vo.getRoleIds().equals("1004") || vo.getRoleIds().equals("1003")){
					if ("市辖区".equals(areaMap.get("city"))
							|| areaMap.get("city") == null) {
						vo.setCity(areaMap.get("province"));
					} else {
						vo.setCity(areaMap.get("city"));
					}
					if (vo.getRoleIds().equals("1004")){
						vo.setArea(areaMap.get("area"));
					}
				}
			}
			accountService.save(vo);
		} catch (Exception e) {
			logger.debug("保存账户：{}"+e.getMessage());
		}
		return "redirect:/account/list";
		
	}
	/**
	 * 
	 * <删除账号><功能具体实现>
	 *
	 * @create：2017年4月13日 下午2:12:04
	 * @author：ywl
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delAccount/{userId}")
	public String delAccount(@PathVariable String userId){
		try {
			accountService.delAccount(userId);
		} catch (Exception e) {
			logger.debug("删除账号:{}"+e.getMessage());
		}
		return "redirect:/account/list";
	}
	/**
	 * 
	 * <获取省市县><功能具体实现>
	 *
	 * @create：2017年4月18日 下午3:15:31
	 * @author：ywl
	 * @param parentId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/resetPwd/{userId}")
	public String resetPwd(@PathVariable String userId){
		try {
			accountService.resetPwd(userId);
		} catch (Exception e) {
			logger.debug("初始化密码:{}"+e.getMessage());
		}
		return "redirect:/account/list";
	}
	
	@RequestMapping(value="/area")
	@ResponseBody
	public String getAreaList(String parentId,Model model){
		List<Area> list = null;
		JSONObject json = new JSONObject();
		try {
			list = accountService.getArea(parentId);
			model.addAttribute("data", list);
		} catch (Exception e) {
			logger.debug("地区列表:{}"+e.getMessage());
		}
		json.put("result", list);
		return json.toString();
	}
}
