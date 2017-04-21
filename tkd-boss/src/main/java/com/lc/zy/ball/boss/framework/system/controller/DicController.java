package com.lc.zy.ball.boss.framework.system.controller;

import java.util.List;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.web.Servlets;

import com.google.common.collect.Lists;
import com.lc.zy.ball.boss.common.service.DicService;
import com.lc.zy.ball.domain.oa.po.Dic;
import com.lc.zy.ball.domain.oa.po.DicItem;
import com.lc.zy.common.util.TreeNode;
import com.lc.zy.common.web.Token;
import com.lc.zy.common.web.Token.Type;


@Controller
@RequestMapping(value = "/admin/dic")
public class DicController {


	@Autowired
	private DicService dicService;
	
	private static JsonMapper mapper = JsonMapper.nonEmptyMapper();
	
	@RequestMapping(value = "")
	public String main() {
		return "admin/dic";
	}
	
	@ModelAttribute("preloadDic")
	public Dic getDic(@RequestParam(value = "dicId", required = false) String dicId) throws Exception {
		if (StringUtils.isNotBlank(dicId)) {
			return dicService.getDic(dicId);
		} else {
			return new Dic();
		}
	}
	
	@ModelAttribute("preloadDicItem")
	public DicItem getDicItem(@RequestParam(value = "itemId", required = false) String itemId) throws Exception {
		if (StringUtils.isNotBlank(itemId)) {
			return dicService.getItem(itemId);
		} else {
			return new DicItem();
		}
	}
	
	@RequestMapping(value = "checkDicName", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkDicName(@RequestParam("oldName") String oldName, @RequestParam("dicName") String dicName) {
		if (dicName.equals(oldName)) {
			return true;
		} else if (dicService.findDicByName(dicName) == null) {
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "checkDicCode", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkDicCode(@RequestParam("oldCode") String oldCode, @RequestParam("dicCode") String dicCode) {
		if (dicCode.equals(oldCode)) {
			return true;
		} else if (dicService.findDicByCode(dicCode) == null) {
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	@Token
	public String createDic(Model model) {
		model.addAttribute("dic", new Dic());
		return "admin/dicForm";
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	@Token
	public String updateDic(@PathVariable("id") String dicId, Model model) throws Exception {
		Dic dic = dicService.getDic(dicId);
		model.addAttribute("dic", dic);
		return "admin/dicForm";
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	@Token(Type.REMOVE)
	public boolean saveDic(@ModelAttribute("preloadDic") Dic newDic) throws Exception {
		dicService.saveDic(newDic);
		return true;
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteDic(@PathVariable("id")  String dicId) throws Exception {
		if (isReserved(dicId)) {
			return false;
		}
		
		dicService.deleteDic(dicId);
		return true;
	}
	
	private boolean isReserved(String dicId) {
		return dicId.startsWith("10");
	}

	@RequestMapping(value = "tree", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getdicTreeNodes(ServletResponse response) {
		Servlets.setNoCacheHeader((HttpServletResponse)response);
		TreeNode root = new TreeNode("dic-root", "字典类别", "0");
		root.setOpen(true);
		List<Dic> dics = dicService.findDicByPId(null);
		if (dics != null)
			root.setChildren(bindDic2TreeNode(dics));
		
		//因为zTree需要数组形式的json，所以这里转换一下
		TreeNode[] returnVal = new TreeNode[]{root};
		
		return mapper.toJson(returnVal);
	}
	
	@RequestMapping(value = "tree/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getDicTreeNodesByPid(@PathVariable("id") String pId, ServletResponse response) {
		Servlets.setNoCacheHeader((HttpServletResponse)response);
		List<Dic> dics = dicService.findDicByPId(pId);
		return mapper.toJson(bindDic2TreeNode(dics));
	}

	private List<TreeNode> bindDic2TreeNode(List<Dic> dics) {
		List<TreeNode> list = Lists.newArrayList();
		for (Dic dic : dics) {
			String id = dic.getDicId().toString();
			String name = String.format("%s(%s)", dic.getDicName(), dic.getDicCode());
			TreeNode node = new TreeNode(id, name, "dic-root");
			node.getData().put("code", dic.getDicCode());
			
			list.add(node);
		}
		
		return list;
	}
	
	@RequestMapping(value = "tree/{id}/item", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getDicItemTreeNodes(@PathVariable(value="id") String dicId, ServletResponse response) throws Exception {
		Servlets.setNoCacheHeader((HttpServletResponse)response);
		if ("null".equals(dicId)) {
			return null;
		}
		Dic dic = dicService.getDic(dicId);
		TreeNode root = new TreeNode(dic.getDicId().toString(), dic.getDicName(), "0");
		root.setOpen(true);
		root.setIsParent(true);
		
		List<DicItem> items = dicService.findItemByDicId(dic.getDicId());
		root.setChildren(bindDicItem2TreeNode(items));
		return mapper.toJson(new TreeNode[]{root});
	}

	private List<TreeNode> bindDicItem2TreeNode(List<DicItem> items) {
		List<TreeNode> list = Lists.newArrayList();
		for (DicItem item : items) {
			String id = item.getItemId();
			String name = String.format("%s(%s)", item.getItemName(), item.getItemCode());
			String pid = item.getDicId();
			TreeNode node = new TreeNode(id, name, pid);
			node.getData().put("code", item.getItemCode());
			list.add(node);
		}
		
		return list;
	}
	
	@RequestMapping(value = "checkDicItemName", method = RequestMethod.GET)
	@ResponseBody
	public String checkDicItemName(@RequestParam("oldName") String oldName
			, @RequestParam("itemName") String itemName
			, @RequestParam(value="dicId") String dicId) {
		if (itemName.equals(oldName)) {
			return "true";
		} else if (dicService.findItemByNameAndDicId(itemName, dicId) == null) {
			return "true";
		}
		return "false";
	}
	
	@RequestMapping(value = "checkDicItemCode", method = RequestMethod.GET)
	@ResponseBody
	public String checkDicItemCode(@RequestParam("oldCode") String oldCode
			, @RequestParam("itemCode") String itemCode) {
		if (itemCode.equals(oldCode)) {
			return "true";
		} else if (dicService.findItemByCode(itemCode)== null) {
			return "true";
		}
		return "false";//表示存在相同的code
	}
	
	@RequestMapping(value = "item/create", method = RequestMethod.GET)
	@Token
	public String createItem(@RequestParam("dicId")String dicId, Model model) throws Exception {
		DicItem item = new DicItem();
		item.setDicId(dicId);
		item.setSeqNum(10);
		model.addAttribute("item", item);
		return "admin/dicItemForm";
	}
	
	@RequestMapping(value = "item/update/{id}", method = RequestMethod.GET)
	@Token
	public String updateItem(@PathVariable("id") String itemId, Model model) throws Exception {
		DicItem item = dicService.getItem(itemId);
		model.addAttribute("item", item);
		return "admin/dicItemForm";
	}
	
	@RequestMapping(value = "item/save", method = RequestMethod.POST)
	@ResponseBody
	@Token(Type.REMOVE)
	public boolean saveItem(@ModelAttribute("preloadDicItem")DicItem newItem) throws Exception {
		dicService.saveDicItem(newItem);
		return true;
	}
	
	@RequestMapping(value = "item/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteItem(@PathVariable("id") String itemId) throws Exception {
		if (isReserved(itemId)) {
			return false;
		}
		dicService.deleteDicItem(itemId);
		return true;
	}

}
