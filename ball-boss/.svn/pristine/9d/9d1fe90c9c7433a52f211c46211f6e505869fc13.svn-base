package com.lc.zy.ball.boss.framework.system.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.domain.oa.po.Role;
import com.lc.zy.ball.domain.oa.po.RoleFuncKey;
import com.lc.zy.ball.boss.framework.system.service.RoleService;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.web.Token;
import com.lc.zy.common.web.Token.Type;
import com.lc.zy.common.web.WebUtils;

@Controller
@RequestMapping(value = "/admin/role")
public class RoleController {

	private static final Logger log = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "")
	public String list(Model model, ServletRequest request) throws Exception {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		
		Page<Role> onePage = roleService.find(searchParams, page, size);
		
		model.addAttribute("data", onePage);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		
		return "admin/roleList";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) throws Exception {
		if (isSysRole(id)) {
			redirectAttributes.addFlashAttribute("message", "{\"result\":false, \"content\": \"无法修改系统角色！\"}");
			return "redirect:/admin/role";
		}
		model.addAttribute("role", roleService.getRole(id));
		model.addAttribute("action", "update");
		return "admin/roleForm";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute("preloadRole") Role role,
			RedirectAttributes redirectAttributes) {
		if (isSysRole(role.getRoleId())) {
			redirectAttributes.addFlashAttribute("message", "{\"result\":false, \"content\": \"无法修改系统角色！\"}");
		} else {
			roleService.saveRole(role);
			redirectAttributes.addFlashAttribute("message", "保存角色成功");
		}
		return "redirect:/admin/role";
	}

	@RequestMapping(value = "checkRoleName")
	@ResponseBody
	public String checkRoleName(@RequestParam("oldName") String oldName,
			@RequestParam("roleName") String roleName) {
		if (roleName.equals(oldName)) {
			return "true";
		} else if (roleService.findByRoleName(roleName) == null) {
			return "true";
		}

		return "false";
	}
	
	@RequestMapping(value = "checkRoleCode")
	@ResponseBody
	public String checkRoleCode(@RequestParam("oldCode") String oldCode,
			@RequestParam("roleCode") String roleCode) {
		if (roleCode.equals(oldCode)) {
			return "true";
		} else if (roleService.findByRoleCode(roleCode) == null) {
			return "true";
		}
		
		return "false";
	}

	/**
	 * 使用@ModelAttribute, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出Role对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此本方法在该方法中执行.
	 * @throws Exception 
	 */
	@ModelAttribute("preloadRole")
	public Role getRole(@RequestParam(value = "roleId", required = false) String roleId) throws Exception {
		if (roleId != null) {
			return roleService.getRole(roleId);
		}
		return null;
	}
	
	@Token
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("role", new Role());
		model.addAttribute("action", "create");
		return "admin/roleForm";
	}

	@Token(Type.REMOVE)
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(Role newRole, RedirectAttributes redirectAttributes) {
		log.debug("******************** " + roleService + " is proxy? " + AopUtils.isAopProxy(roleService));
		roleService.saveRole(newRole);
		redirectAttributes.addFlashAttribute("message", "{\"result\":true, \"content\": \"创建角色成功\"}");
		return "redirect:/admin/role";
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String deleteAll(@RequestParam("ids") String roleIds, RedirectAttributes redirectAttributes) throws Exception {
		String[] ids = roleIds.split(";");
		
		if (isSysRole(ids)) {
			redirectAttributes.addFlashAttribute("message", "{\"result\":false, \"content\": \"无法删除系统角色！\"}");
		} else {
			roleService.delete(ids);
			redirectAttributes.addFlashAttribute("message", "删除角色成功");
		}
		return "redirect:/admin/role";
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") String roleId, RedirectAttributes redirectAttributes) throws Exception {
		if (isSysRole(roleId)) {
			redirectAttributes.addFlashAttribute("message", "{\"result\":false, \"content\": \"无法删除系统角色！\"}");
		} else {
			roleService.delete(roleId);
			redirectAttributes.addFlashAttribute("message", "删除角色成功");
		}
		return "redirect:/admin/role";
	}
	
	@RequestMapping(value="perms/{id}", method=RequestMethod.GET)
	public String loadPerms(@PathVariable String id, Model model) throws Exception {
		model.addAttribute("role", roleService.getRole(id));
		
		return "admin/permission";
	}
	
	@RequestMapping(value="perms/{id}", method=RequestMethod.POST)
	public String savePerms(@PathVariable String id, String[] fids, RedirectAttributes redirectAttributes) throws Exception {
		roleService.saveRoleFunc(id, Arrays.asList(fids));
		
		redirectAttributes.addFlashAttribute("message", "保存成功");
		
		return "redirect:/admin/role";
	}
	
	@RequestMapping(value="perms/{id}/json")
	@ResponseBody
	public String getRolePerms(@PathVariable String id) throws Exception {
		List<RoleFuncKey> list = roleService.findRoleFunc(id);
		String[] fids = new String[list.size()];
		for(int i = 0; i < list.size(); i++) {
			fids[i] = list.get(i).getFuncId();
		}
		
		return MyGson.getInstance().toJson(fids);
	}
	
	public boolean isSysRole(String id) {
		Assert.notNull(id);
		return id.startsWith("10");
	}
	
	public boolean isSysRole(String[] ids) {
		Assert.notNull(ids);
		for (String id : ids) {
			if (id.startsWith("10"))
				return true;
		}
		return false;
	}
	
}
