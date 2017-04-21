package com.lc.zy.ball.boss.framework.system.controller;

import java.util.Map;
import javax.servlet.ServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;
import com.lc.zy.ball.boss.common.FlashAttributeUtil;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.system.service.OrgService;
import com.lc.zy.ball.boss.framework.system.service.RoleService;
import com.lc.zy.ball.boss.framework.system.service.UserService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.ball.domain.oa.po.ex.UserEx;
import com.lc.zy.common.web.Token;
import com.lc.zy.common.web.Token.Type;
import com.lc.zy.common.web.WebUtils;

/**
 * 员工管理
 * @author liangc
 */
@Controller
@RequestMapping(value = "/admin/user")
public class UserController extends AbstractController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	private UserService userService;
	private RoleService roleService;
	private OrgService orgService;

	@Autowired
	public UserController(OrgService orgService,UserService userService, RoleService roleService) {
		super();
		this.userService = userService;
		this.roleService = roleService;
		this.orgService = orgService;
	}

	@RequestMapping(value={"", "/"})
	public String list(Model model, ServletRequest request) throws Exception {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//XXX 将日期字符串转换成对象
		parseDate(searchParams, "GTE_createTime");
		parseDate(searchParams, "LTE_createTime");
		
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request,50);//默认50条
		logger.debug("user_list_search_params={}",searchParams);
		Page<User> onePage = userService.find(new PageRequest(page, size), searchParams,0);
		
		//为了按角色查询
		model.addAttribute("roleList",roleService.findAll());
		model.addAttribute("data", onePage);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		
		return "admin/userList";
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) throws Exception {
		model.addAttribute("user", userService.getUser(id));
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("user_roles", roleService.findByUserId(id));
		model.addAttribute("action", "update");
		model.addAttribute("orgTree", orgService.getOrgTree(id));
		return "admin/userForm";
	}
	
	@ModelAttribute("preloadUser")
	public UserEx getUser(@RequestParam(required = false) String userId) throws Exception {
		if (StringUtils.isNotBlank(userId)) {
			return userService.getUserEx(userId);
		}
		
		return new UserEx();
	}
	

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute("preloadUser") UserEx user, RedirectAttributes redirectAttributes) {
		try {
			userService.updateSupporter(user);
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "保存用户信息成功!"));
		} catch (Exception e) {
			logger.debug("保存用户信息成功失败: {}", e.getMessage());
			logger.error(e.getMessage(), e);
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "保存用户信息失败:" + e.getMessage()));
		}
		
		return "redirect:/admin/user";
	}

	@RequestMapping(value = "checkLoginName")
	@ResponseBody
	public boolean checkLoginName(String oldName, String loginName) {
		if (loginName.equals(oldName)) {
			return true;
		} else {
			return userService.getByLoginName(loginName) == null;
		}
	}
	
	@RequestMapping(value = "checkNickname")
	@ResponseBody
	public boolean checkNickname(String oldName, String nickname) {
		if (oldName.equals(nickname)) {
			return true;
		} else {
			return userService.getByNickname(nickname) == null;
		}
	}
	
	@Token
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) throws Exception {
		model.addAttribute("user", new User());
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("action", "create");
		model.addAttribute("orgTree", orgService.getOrgTree(null));
		return "admin/userForm";
	}

	@Token(Type.REMOVE)
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@ModelAttribute("preloadUser") UserEx user, RedirectAttributes redirectAttributes) {
		try {
			logger.debug("XXXXXXXXXXXX");
			logger.debug(user.toString());
			userService.createSupporter(user);
			//modify by liangc 150731 : 角色与用户改成 多对多 关系，此处逻辑不成立了
			//if(type == 0){
			//	userService.createSupporter(user);
			//}
			//if(type == 1){
			//	userService.createCustomer(user);
			//}
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "创建用户成功!"));
		} catch (Exception e) {
			logger.debug("创建失败: {}", e.getMessage());
			logger.error(e.getMessage(), e);
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "创建用户失败:" + e.getMessage()));
		}
		return "redirect:/admin/user";
	}
	
	@RequestMapping(value = "delete/{userId}", method = RequestMethod.POST)
	public String delete(@PathVariable String userId, RedirectAttributes redirectAttributes) throws Exception {
		
		userService.deleteUser(userId);
		
		redirectAttributes.addFlashAttribute("message", "删除用户成功");
		return "redirect:/admin/user";
	}
	
	@RequestMapping(value = "resetPwd/{userId}")
	public String resetPwd(@PathVariable String userId, RedirectAttributes redirectAttributes) throws Exception {
		
		userService.resetPassword(userId);
		
		redirectAttributes.addFlashAttribute("message", "\"用户密码\"已经重置为与\"登录账号\"相同！");
		return "redirect:/admin/user";
	}
}
