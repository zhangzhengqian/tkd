package com.lc.zy.ball.boss.framework.activationCode.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.framework.activationCode.service.ActivationCodeService;
import com.lc.zy.ball.boss.framework.activationCode.vo.ActivationCodeVo;
import com.lc.zy.ball.boss.framework.activationCode.vo.CodeUseVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.ActivationCode;
import com.lc.zy.ball.domain.oa.po.ActivationCodeInfo;
import com.lc.zy.ball.domain.oa.po.StatiumDetail;
import com.lc.zy.common.Constants;
import com.lc.zy.common.util.ExcelUtil;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.web.WebUtils;

/**
 * 活动码
 * 
 * @author sl
 *
 */

@Controller
@RequestMapping(value = "code")
public class ActivationCodeController {
	private static final Logger logger = LoggerFactory.getLogger(ActivationCodeController.class);

	@Autowired
	ActivationCodeService activationCodeService = null;

	/**
	 * 
	 * <获取活动码list><功能具体实现>
	 *
	 * @create：2016年2月23日 下午2:24:48 
	 * @author： sl
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "codeList")
	public String codeList(Model model, HttpServletRequest request) {
		try {
			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
			int page = WebUtils.getPage(request);
			int size = WebUtils.getPageSize(request);
			Page<ActivationCode> codePage = activationCodeService.codeList(new PageRequest(page, size), searchParams);
			model.addAttribute("data", codePage);
			// 将搜索条件编码成字符串，用于排序，分页的RUL
			model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		} catch (Exception e) {
			logger.debug("获取活动码list", e.getMessage());
		}
		return "activationCode/codeList";
	}
	
	/**
	 * 
	 * <新增页面初始化><功能具体实现>
	 *
	 * @create：2016年2月23日 下午7:19:17
	 * @author： sl
	 * @return
	 */
	@RequestMapping(value = "codeForm")
	public String codeForm(){
		return "activationCode/codeForm";
	}

	/**
	 * 
	 * <保存活动码><功能具体实现>
	 *
	 * @create：2016年2月23日 下午3:38:15 
	 * @author： sl
	 * @param activationCode
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "codeSave")
	public String codeSave(ActivationCodeVo activationCodeVo, RedirectAttributes redirectAttributes) {
		try {
			ActivationCode activationCode = new ActivationCode();
			// id
			String id = UUID.get();
			activationCode.setId(id);
			// ct
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date time = sdf.parse(sdf.format(new Date()));
			activationCode.setCt(time);
			// cb
			String uid = SessionUtil.currentUserId();
			activationCode.setCb(uid);
			// 激活码数量
			activationCode.setNumber(activationCodeVo.getNumber());
			// 可使用场馆id
			activationCode.setStatiums(activationCodeVo.getStatiums());
			// 开始日期
			activationCode.setStartDate(sdf.parse(activationCodeVo.getsDate()));
			// 结束日期
			activationCode.setEndDate(sdf.parse(activationCodeVo.geteDate()));
			// 渠道
			activationCode.setChannel(activationCodeVo.getChannel());
			// 简介
			activationCode.setDescription(activationCodeVo.getDescription());
			// 保存
			activationCodeService.saveCode(activationCode);
			// 活动码生成数量
			int codeNum = activationCode.getNumber();
			// 生成活动码info
			codeInfo(id, codeNum);
		} catch (Exception e) {
			logger.debug("保存活动码", e.getMessage());
		}
		return "redirect:/code/codeList";
	}

	/**
	 * 
	 * <保存code详情><功能具体实现>
	 *
	 * @create：2016年2月23日 下午6:34:28
	 * @author： sl
	 * @param id
	 * @param codeNum
	 */
	public void codeInfo(String id, int codeNum) {
		try {
			for (int i = 0; i < codeNum; i++) {
				ActivationCodeInfo activationCodeInfo = new ActivationCodeInfo();
				// id
				activationCodeInfo.setId(UUID.get());
				// codeId
				activationCodeInfo.setCodeId(id);
				// statue
				activationCodeInfo.setStatue(0);
				// code
				String code = codeRandom(8);
				activationCodeInfo.setCode(code);
				// 保存code详情
				activationCodeService.saveCodeInfo(activationCodeInfo);
			}
		} catch (Exception e) {
			logger.debug("保存code详情", e.getMessage());
		}
	}

	/**
	 * 
	 * <获取随机数><功能具体实现>
	 *
	 * @create：2016年2月23日 下午5:23:49 
	 * @author： sl
	 * @param length
	 * @return
	 */
	public String codeRandom(int length) {
		String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				"q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
				"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };
		String code = "";
		try {
			StringBuffer shortBuffer = new StringBuffer();
			String uuid = UUID.get();
			for (int i = 0; i < length; i++) {
				String str = uuid.substring(i * 4, i * 4 + 4);
				int x = Integer.parseInt(str, 16);
				shortBuffer.append(chars[x % 0x3E]);
			}
			Random random = new Random();
			code = shortBuffer.toString().toUpperCase().substring(1) + String.valueOf(random.nextInt(10));
		} catch (Exception e) {
			logger.debug("获取随机数", e.getMessage());
		}
		return code;
	}

	/**
	 * 
	 * <根据活动码id获取活动码详情><功能具体实现>
	 *
	 * @create：2016年2月23日 下午6:25:44
	 * @author： sl
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "codeInfo/{id}")
	public String codeInfo(Model model, HttpServletRequest request,@PathVariable String id){
		List<CodeUseVo> codeUseVos = new ArrayList<CodeUseVo>();
		try {
			// 根据codeId获取激活码信息
			ActivationCode activationCode = activationCodeService.codeById(id);
			// 场馆id
			String [] statiums = activationCode.getStatiums().split(",");
			for (String sid : statiums){
				CodeUseVo codeUseVo = new CodeUseVo();
				// 根据场馆id获取场馆信息
				StatiumDetail statiumDetail = activationCodeService.statiumInfoById(sid);
				// 场馆id
				codeUseVo.setStatiumId(sid);
				// 场馆名称
				codeUseVo.setStatiumName(statiumDetail.getName());
				// 根据场馆id和活动码id获取场馆活动码谁用次数
				int useNum = activationCodeService.countCodeUseNum(sid, id);
				codeUseVo.setUseNum(useNum);
				codeUseVos.add(codeUseVo);
			}
			// 活动码场馆使用信息
			model.addAttribute("useInfo", codeUseVos);
			
			// 活动码详情
			int page = WebUtils.getPage(request);
			int size = WebUtils.getPageSize(request);
			Page<ActivationCodeInfo> infoPage = activationCodeService.infoList(new PageRequest(page, size), id);
			model.addAttribute("data", infoPage);
			
			// 活动码id
			model.addAttribute("codeId", id);
		} catch (Exception e) {
			logger.debug("根据活动码id获取活动码详情", e.getMessage());
		}
		return "/activationCode/codeInfoList";
	}
	
	/**
	 * 
	 * <导出活动码><功能具体实现>
	 *
	 * @create：2016年2月23日 下午7:14:23
	 * @author： sl
	 * @param model
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @param id
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	@RequestMapping(value = "exportExcel/{id}")
	public void exportExcel(Model model, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes,@PathVariable String id) {
		try {
			// 获取活动码信息
			ActivationCode activationCode = activationCodeService.codeById(id);
			// 获取活动码详情
			List<ActivationCodeInfo> activationCodeInfos = activationCodeService.codeInfos(id);
			
			ExcelUtil excelUtil = new ExcelUtil();
			String fileName = excelUtil.createtFileName(activationCode.getChannel() + "活动码");
			fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
			response.reset();
			// 指定下载的文件名
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			
			// 活动码可用于场馆
			String statiumName = "";
			String[] sids = activationCode.getStatiums().split(",");
			for (String sid : sids) {
				statiumName = statiumName + activationCodeService.statiumInfoById(sid).getName() + "、";
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String title = activationCode.getChannel() + "活动码列表";
			String excelName = "活动码有效期为：" + 
					sdf.format(activationCode.getStartDate()) + "-" + sdf.format(activationCode.getEndDate()) + 
					"，可用于场馆：" + statiumName.substring(0, statiumName.length()-1) + "，活动码简介：" + activationCode.getDescription();
			String[] headers = {excelName + "::code" };
			
			excelUtil.exportExcel(title, headers, activationCodeInfos, response.getOutputStream(), "yyyy-MM-dd");
		} catch (Exception e) {
		logger.debug("导出活动码", e.getMessage());
		}
	}
	
	/**
	 * 
	 * <删除活动码><功能具体实现>
	 *
	 * @create：2016年2月23日 下午7:44:54
	 * @author： sl
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "deleteCode/{id}")
	public String deleteCode(@PathVariable String id, Model model){
		try {
			activationCodeService.deleteCodeById(id);
		} catch (Exception e) {
			logger.debug("删除活动码", e.getMessage());
		}
		return "redirect:/code/codeList";
	}
	
	/**
	 * 
	 * <获取场馆信息><功能具体实现>
	 *
	 * @create：2016年2月24日 下午2:35:36
	 * @author： sl
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "statium_dlg")
	public String statiumDlg(Model model){
		try {
			// 获取场馆信息
			List<StatiumDetail> statiumDetails = activationCodeService.statiumDetails();
			model.addAttribute("statiums", statiumDetails);
		} catch (Exception e) {
			logger.debug("获取场馆信息", e.getMessage());
		}
		return "/activationCode/statium_dlg";
	}
	
	/**
	 * 
	 * <查询场馆><功能具体实现>
	 *
	 * @create：2016年2月24日 下午3:24:09
	 * @author： sl
	 * @param model
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "statium_query_dlg")
	@ResponseBody
	public String statiumQueryDlg(Model model, String name, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			// 获取场馆信息
			List<StatiumDetail> statiumDetails = activationCodeService.statiumDetailsByName(name);
			result.put(Constants.Result.RESULT, true);
			result.put(Constants.Result.DATA, statiumDetails);
			return MyGson.getInstance().toJson(result);
		} catch (Exception e) {
			logger.debug("查询场馆失败", e.getMessage());
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "查询场馆失败！");
			return MyGson.getInstance().toJson(result);
		}
	}
}
