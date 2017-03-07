package com.lc.zy.ball.boss.framework.qiuyouzone.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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

import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.qiuyouzone.service.QiuyouzoneLabelService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.QiuyouzoneLabel;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.web.WebUtils;

@Controller
@RequestMapping(value = "/qiuyouzoneLabel")
public class QiuyouzoneLabelController extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(QiuyouzoneLabelController.class);

	@Autowired
	private QiuyouzoneLabelService qiuyouzoneLabelService;

	/**
	 * 
	 * <球友圈标签列表><功能具体实现>
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年1月20日 下午6:06:15
	 */
	@RequestMapping(value = "list")
	public String labelList(Model model, HttpServletRequest request) throws Exception {
		logger.info("LabelController labelList method execute!");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		CommonOAUtils.paramesTrim(searchParams);// 参数去空
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<QiuyouzoneLabel> onPage = qiuyouzoneLabelService.find(searchParams, new PageRequest(page, size));
		model.addAttribute("data", onPage);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "/qiuyouzone/label/list";
	}

	/**
	 * 
	 * <创建标签><功能具体实现>
	 * 
	 * @param model
	 * @return
	 * @author liangsh
	 * @date 2016年1月20日 下午6:06:43
	 */
	@RequestMapping(value = "createForm_dlg")
	public String createForm(Model model) {
		return "/qiuyouzone/label/labelForm";
	}

	/**
	 * 
	 * <保存标签><功能具体实现>
	 * @param model
	 * @param label
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年1月21日 上午10:25:59
	 */
	@RequestMapping(value = "save")
	public String save(Model model, QiuyouzoneLabel label) throws Exception {
		if(label != null){
			label.setEt(new Date());
			label.setEb(SessionUtil.currentUserId());
			if(StringUtils.isNotBlank(label.getId())){
				label.setCt(new Date());
				label.setCb(SessionUtil.currentUserId());
				qiuyouzoneLabelService.updateByPrimaryKeySelective(label, label.getId());
			}else {
				label.setCt(new Date());
				label.setId(UUID.get());
				label.setType(0);
				label.setCb(SessionUtil.currentUserId());
				qiuyouzoneLabelService.insertSelective(label,label.getId());
			}
		}
		
		return "redirect:/qiuyouzoneLabel/list";
	}
	
	/**
	 * 
	 * <修改标签><功能具体实现>
	 * @param id
	 * @param model
	 * @return
	 * @author liangsh
	 * @date 2016年1月21日 上午10:29:44
	 */
	@RequestMapping(value = "detail/{id}")
	public String detailForm(@PathVariable String id,Model model) throws Exception{
		if(StringUtils.isNotBlank(id)){
			QiuyouzoneLabel label = qiuyouzoneLabelService.selectByPrimaryKey(QiuyouzoneLabel.class, id);
			model.addAttribute("label", label);
		}
		return "/qiuyouzone/label/labelForm";
	}
	
	/**
	 * 
	 * <删除标签逻辑删除><功能具体实现>
	 * @param id
	 * @return
	 * @author liangsh
	 * @date 2016年1月21日 上午10:45:41
	 */
	@RequestMapping(value = "delete",method = RequestMethod.POST)
	@ResponseBody
	public String delete(String id){
		Map<String,Object> result = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(id)){
			try {
				QiuyouzoneLabel label = qiuyouzoneLabelService.selectByPrimaryKey(QiuyouzoneLabel.class, id);
				if(label != null){
					label.setStatus(2);
					label.setEt(new Date());
					label.setEb(SessionUtil.currentUserId());
					qiuyouzoneLabelService.updateByPrimaryKeySelective(label, label.getId());
					result.put(Constants.Result.RESULT, true);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, e.getMessage());
			}
		}else{
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON,"id不能为空");
		}
		return MyGson.getInstance().toJson(result);
	}
	
	/**
	 * 
	 * <停用启用><功能具体实现>
	 * @param id
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年1月21日 下午2:28:44
	 */
	@RequestMapping(value="status/{id}/{status}")
	public String status(@PathVariable String id,@PathVariable Integer status) throws Exception{
		QiuyouzoneLabel label = qiuyouzoneLabelService.selectByPrimaryKey(QiuyouzoneLabel.class, id);
		if(label != null){
			label.setStatus(status);
			label.setEt(new Date());
			label.setEb(SessionUtil.currentUserId());
			qiuyouzoneLabelService.updateByPrimaryKeySelective(label, label.getId());
		}
		return "redirect:/qiuyouzoneLabel/list";
	}
	
	/**
	 * 
	 * <取消置顶><功能具体实现>
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年1月21日 下午2:32:50
	 */
	@RequestMapping(value="unstick/{id}")
	public String unstick(@PathVariable String id) throws Exception{
		QiuyouzoneLabel label = qiuyouzoneLabelService.selectByPrimaryKey(QiuyouzoneLabel.class, id);
		if(label != null){
			label.setTopTime(null);
			label.setEt(new Date());
			label.setEb(SessionUtil.currentUserId());
			qiuyouzoneLabelService.updateByPrimaryKey(label, label.getId());
		}
		return "redirect:/qiuyouzoneLabel/list";
	}
	
	/**
	 * 
	 * <置顶><功能具体实现>
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年1月21日 下午2:32:50
	 */
	@RequestMapping(value="stick/{id}")
	public String  stick(@PathVariable String id) throws Exception{
		QiuyouzoneLabel label = qiuyouzoneLabelService.selectByPrimaryKey(QiuyouzoneLabel.class, id);
		if(label != null){
			label.setTopTime(new Date());
			label.setEt(new Date());
			label.setEb(SessionUtil.currentUserId());
			qiuyouzoneLabelService.updateByPrimaryKey(label, label.getId());
		}
		return "redirect:/qiuyouzoneLabel/list";
	}
}
