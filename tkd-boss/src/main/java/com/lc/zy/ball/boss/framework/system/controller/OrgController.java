package com.lc.zy.ball.boss.framework.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.google.common.collect.Lists;
import com.lc.zy.ball.boss.framework.system.service.OrgService;
import com.lc.zy.ball.domain.oa.po.Organization;
import com.lc.zy.ball.domain.oa.po.ex.OrganizationEx;
import com.lc.zy.common.util.MessageBean;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.TreeNode;
import com.lc.zy.common.web.Token;
import com.lc.zy.common.web.Token.Type;

/**
 * 组织管理
 * @author liangc
 */
@Controller
@RequestMapping(value = "/admin/org")
public class OrgController {
	
	@Autowired
	private OrgService orgService;
	
	@RequestMapping(value={"", "/"})
	public String main() {
		return "admin/org";
	}
	
	@RequestMapping("/tree")
	@ResponseBody
	public String tree(String pid, HttpServletResponse response) {
		
		Servlets.setNoCacheHeader(response);
		
		TreeNode root = null;
		if (StringUtils.isBlank(pid)) {
			root = new TreeNode("0", "组织树", null);
			root.setIsParent(true);
			root.setOpen(false);
			
			//因为zTree需要数组形式的json，所以这里转换一下
			return MyGson.getInstance().toJson(new TreeNode[]{root});
		}
		
		List<OrganizationEx> orgs = orgService.findExByPId(pid);
		List<TreeNode> nodes = bind2TreeNode(orgs);
		
		return MyGson.getInstance().toJson(nodes);
	}

	
	private TreeNode newTreeNode(OrganizationEx org) {
		String id = org.getId();
		String name = org.getOrgName();
		TreeNode node = new TreeNode(id, name, null);
		if (StringUtils.isNotBlank(org.getPid())) {
			node.setpId(org.getPid());
		}
		if (org.getChildren() > 0) {
			node.setIsParent(true);
			System.out.println("=========="+org.getOrgName());
		}
		node.getData().put("seqNum", org.getSeq());
		return node;
	}

	private List<TreeNode> bind2TreeNode(List<OrganizationEx> orgs) {
		List<TreeNode> list = Lists.newArrayList();
		for (OrganizationEx org : orgs) {
			list.add(newTreeNode(org));
		}
		
		return list;
	}
	
	@Token
	@RequestMapping("form")
	public String loadForm(String id, String pid,  Model model, HttpServletResponse response) {
		Servlets.setNoCacheHeader(response);
		
		Organization org = null;
		if (StringUtils.isNotBlank(id)) {
			org = orgService.getOrganization(id);
		}
		org = org == null ? new Organization() : org;
		
		if (org.getPid() == null && StringUtils.isNotBlank(pid)) {
			org.setPid(pid);
		}
		model.addAttribute("org", org);
		return "admin/orgForm";
	}
	
	@Token(Type.REMOVE)
	@RequestMapping("save")
	public String save(@ModelAttribute("preload") Organization org, RedirectAttributes redirectAttr) {
		
		orgService.saveOrganization(org);
		
		redirectAttr.addFlashAttribute("message", "保存成功！");
		
		return "redirect:/admin/org";
	}
	
	@RequestMapping("delete/{id}")
	public String delete(@PathVariable String id, RedirectAttributes redirectAttr) {
		
		int n = orgService.countByPId(id);
		if (n > 0) {
			redirectAttr.addFlashAttribute("message", 
					MyGson.getInstance().toJson(new MessageBean(0, "无法删除父功能!")));
			
		} else {
			orgService.delOrganization(id);
			
			redirectAttr.addFlashAttribute("message", "操作成功！");
		}
		return "redirect:/admin/org";
	}
	
	@ModelAttribute("preload")
	public Organization preload(String id) {
		Organization org = null;
		if (StringUtils.isNotBlank(id))
			org = orgService.getOrganization(id);
		
		return org == null ? new Organization() : org;
	}
	
	@RequestMapping(value = "checkName")
	@ResponseBody
	public boolean checkName(String oldName, String orgName, String pid) {
		if (oldName.equals(orgName)) {
			return true;
		} else {
			return orgService.getByNameAndPId(orgName, pid) == null;
		}
	}
	
	
	@RequestMapping(value = "change")
	@ResponseBody
	public boolean change(String id, String pid, @RequestParam(required=false)Integer seq) {
		Organization f = new Organization();
		f.setId(id);
		f.setPid(pid);
		if (seq != null)
			f.setSeq(seq);
		orgService.saveOrganization(f);
		return true;
	}

}
