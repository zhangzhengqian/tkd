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
import com.lc.zy.ball.domain.oa.po.Function;
import com.lc.zy.ball.domain.oa.po.ex.FunctionEx;
import com.lc.zy.ball.boss.framework.system.service.FunctionService;
import com.lc.zy.common.util.MessageBean;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.TreeNode;
import com.lc.zy.common.web.Token;
import com.lc.zy.common.web.Token.Type;

@Controller
@RequestMapping(value = "/admin/func")
public class FunctionController {
	
	@Autowired
	private FunctionService functionService;
	
	@RequestMapping(value={"", "/"})
	public String main() {
		return "admin/function";
	}
	
	@RequestMapping("/tree")
	@ResponseBody
	public String tree(String pid, HttpServletResponse response) {
		
		Servlets.setNoCacheHeader(response);
		
		TreeNode root = null;
		if (StringUtils.isBlank(pid)) {
			root = new TreeNode("0", "功能树", null);
			root.setIsParent(true);
			root.setOpen(false);
			
			//因为zTree需要数组形式的json，所以这里转换一下
			return MyGson.getInstance().toJson(new TreeNode[]{root});
		}
		
		List<FunctionEx> funcs = functionService.findExByPId(pid);
		List<TreeNode> nodes = bind2TreeNode(funcs);
		
		return MyGson.getInstance().toJson(nodes);
	}

	
	private TreeNode newTreeNode(FunctionEx func) {
		String id = func.getFuncId();
		String name = func.getFuncName();
		TreeNode node = new TreeNode(id, name, null);
		if (StringUtils.isNotBlank(func.getParentId())) {
			node.setpId(func.getParentId());
		}
		if (func.getChildren() > 0) {
			node.setIsParent(true);
		}
		
		node.getData().put("seqNum", func.getSeqNum());
		
		return node;
	}

	private List<TreeNode> bind2TreeNode(List<FunctionEx> funcs) {
		List<TreeNode> list = Lists.newArrayList();
		for (FunctionEx func : funcs) {
			list.add(newTreeNode(func));
		}
		
		return list;
	}
	
	@Token
	@RequestMapping("form")
	public String loadForm(String id, String pId,  Model model, HttpServletResponse response) {
		Servlets.setNoCacheHeader(response);
		
		Function func = null;
		if (StringUtils.isNotBlank(id)) {
			func = functionService.getFunction(id);
		}
		func = func == null ? new Function() : func;
		
		if (func.getParentId() == null && StringUtils.isNotBlank(pId)) {
			func.setParentId(pId);
		}
		
		model.addAttribute("func", func);
		
		return "admin/functionForm";
	}
	
	@Token(Type.REMOVE)
	@RequestMapping("save")
	public String save(@ModelAttribute("preload") Function func, RedirectAttributes redirectAttr) {
		
		functionService.saveFunction(func);
		
		redirectAttr.addFlashAttribute("message", "保存成功！");
		
		return "redirect:/admin/func";
	}
	
	@RequestMapping("delete/{id}")
	public String delete(@PathVariable String id, RedirectAttributes redirectAttr) throws Exception {
		
		int n = functionService.countByPId(id);
		if (n > 0) {
			redirectAttr.addFlashAttribute("message", 
					MyGson.getInstance().toJson(new MessageBean(0, "无法删除父功能!")));
			
		} else {
			functionService.delFunction(id);
			
			redirectAttr.addFlashAttribute("message", "操作成功！");
		}
		return "redirect:/admin/func";
	}
	
	@ModelAttribute("preload")
	public Function preload(String id) {
		Function func = null;
		if (StringUtils.isNotBlank(id))
			func = functionService.getFunction(id);
		
		return func == null ? new Function() : func;
	}
	
	@RequestMapping(value = "checkName")
	@ResponseBody
	public boolean checkName(String oldName, String funcName, String pId) {
		if (oldName.equals(funcName)) {
			return true;
		} else {
			return functionService.getByNameAndPId(funcName, pId) == null;
		}
	}
	
	
	@RequestMapping(value = "change")
	@ResponseBody
	public boolean change(String id, String pId, @RequestParam(required=false)Integer seqNum) {
		Function f = new Function();
		f.setFuncId(id);
		f.setParentId(pId);
		if (seqNum != null)
			f.setSeqNum(seqNum);
		
		functionService.saveFunction(f);
		
		return true;
	}

}
