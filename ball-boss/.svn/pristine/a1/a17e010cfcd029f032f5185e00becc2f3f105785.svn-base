package com.lc.zy.ball.boss.framework.statium.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;
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

import com.google.gson.reflect.TypeToken;
import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.framework.statium.service.StatiumDetailService;
import com.lc.zy.ball.boss.framework.statium.service.StatiumPriceTmplService;
import com.lc.zy.ball.boss.framework.statium.service.StatiumSpaceService;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumPriceTmplVo;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumSpaceBatch;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumSpaceVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.StatiumDetail;
import com.lc.zy.ball.domain.oa.po.StatiumSpace;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.web.WebUtils;

/**
 * 场地信息管理
 * 
 * @author wang.haibin
 *
 */
@Controller
@RequestMapping(value = "/statium/space")
public class StatiumSpaceController {

	private static Logger logger = LoggerFactory.getLogger(StatiumSpaceController.class);

	@Autowired
	private StatiumSpaceService statiumSpaceService;

	@Autowired
	private StatiumDetailService statiumDetailService;

	@Autowired
	private StatiumPriceTmplService statiumPriceTmplService;

	/**
	 * 场地信息首页
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "", "/" })
	public String index(Model model, String statiumId, ServletRequest request) {
		try {
			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
			int page = WebUtils.getPage(request);
			int size = WebUtils.getPageSize(request);
			Object id = searchParams.get("EQ_statiumId");
			if (id == null) {
				if (StringUtils.isNotEmpty(statiumId)) {
					searchParams.put("EQ_statiumId", statiumId);
				}
			} else {
				statiumId = id.toString();
			}
			// if (StringUtils.isNotEmpty(statiumId)) {
			// searchParams.put("EQ_statiumId", statiumId);
			// } else {
			// model.addAttribute("msg", "场馆id不能为空");
			// return "redirect:statium";
			// }
			Page<StatiumSpace> data = statiumSpaceService.find(new PageRequest(page, size), searchParams);

			// StatiumDetailCriteria sdc = new StatiumDetailCriteria();
			// StatiumDetailCriteria.Criteria cri = sdc.createCriteria();
			// cri.andIdEqualTo(statiumId);
			// cri.andDeleteFlagEqualTo(0);
			// List<StatiumDetail> statiumDetailList =
			// statiumDetailService.find(sdc);
			StatiumDetail statium = statiumDetailService.selectByPrimaryKey(StatiumDetail.class, statiumId);
			if (statium != null) {
				model.addAttribute("sportTypes", statium.getSportType());
			}
			model.addAttribute("data", data);
			model.addAttribute("statiumId", statiumId);
			// 将搜索条件编码成字符串，用于排序，分页的URL
			model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return "statium/space/index";
	}

	/**
	 * 
	 * <预约情况><功能具体实现>
	 *
	 * @create：2015年8月13日 下午7:41:01
	 * @author： CYY
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "usage", method = RequestMethod.GET)
	public String usage(Model model, String statiumId) throws Exception {
		model.addAttribute("statiumId", statiumId);
		// StatiumDetailCriteria sdc = new StatiumDetailCriteria();
		// StatiumDetailCriteria.Criteria cri = sdc.createCriteria();
		// cri.andIdEqualTo(statiumId);
		// cri.andDeleteFlagEqualTo(0);
		// List<StatiumDetail> statiumDetailList =
		// statiumDetailService.find(sdc);
		StatiumDetail statium = statiumDetailService.selectByPrimaryKey(StatiumDetail.class, statiumId);
		if (statium != null) {
			String sportTypes = statium.getSportType();
			String sportType = (sportTypes.split(";;"))[0];
			model.addAttribute("sportType", sportType);
			model.addAttribute("sportTypes", sportTypes);
		}
		return "statium/space/usage";
	}

	/**
	 * 
	 * <预约情况列表><功能具体实现>
	 *
	 * @create：2015年8月14日 下午7:41:01
	 * @author： CYY
	 * @param model
	 * @param statiumId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "usageList_dlg")
	public String usageList(Model model, String statiumId, String sportType, String startDate, ServletRequest request) {
		try {
			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
			int page = 0;
			int size = 10000;
			if (StringUtils.isNotEmpty(statiumId)) {
				searchParams.put("EQ_statiumId", statiumId);
			} else {
				model.addAttribute("msg", "场馆id不能为空");
				throw new RuntimeException("场馆id不能为空");
			}
			searchParams.put("EQ_sportType", sportType);

			// StatiumDetailCriteria sdc = new StatiumDetailCriteria();
			// StatiumDetailCriteria.Criteria cri = sdc.createCriteria();
			// cri.andIdEqualTo(statiumId);
			// cri.andDeleteFlagEqualTo(0);
			// List<StatiumDetail> statiumDetailList =
			// statiumDetailService.find(sdc);
			StatiumDetail statium = statiumDetailService.selectByPrimaryKey(StatiumDetail.class, statiumId);
			if (statium != null) {
				String sportTypes = statium.getSportType();
				if (StringUtils.isEmpty(sportType)) {
					sportType = (sportTypes.split(";;"))[0];
					searchParams.put("EQ_sportType", sportType);
				}
			}
			Page<StatiumSpace> data = statiumSpaceService.find(new PageRequest(page, size), searchParams);
			model.addAttribute("data", data);
			model.addAttribute("statiumId", statiumId);
			model.addAttribute("startDate", startDate);
			// 将搜索条件编码成字符串，用于排序，分页的URL
			model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return "statium/space/usageList";
	}

	/**
	 * 
	 * <创建场地><功能具体实现>
	 *
	 * @create：2015年8月12日 下午3:22:36
	 * @author： CYY
	 * @param model
	 * @param statiumId
	 * @return
	 */
	@RequestMapping(value = "createForm", method = RequestMethod.GET)
	public String createForm(Model model, String statiumId) {
		try {
			// StatiumDetailCriteria statiumDetailCriteria = new
			// StatiumDetailCriteria();
			// StatiumDetailCriteria.Criteria cri =
			// statiumDetailCriteria.createCriteria();
			// cri.andIdEqualTo(statiumId);
			// cri.andDeleteFlagEqualTo(0);
			// List<StatiumDetail> statiumDetailList =
			// statiumDetailService.find(statiumDetailCriteria);
			StatiumDetail statium = statiumDetailService.selectByPrimaryKey(StatiumDetail.class, statiumId);
			if (statium != null) {
				model.addAttribute("sportTypes", statium.getSportType());
			}
			StatiumSpace space = new StatiumSpace();
			space.setStatiumId(statiumId);
			model.addAttribute("space", space);
			model.addAttribute("action", "create");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return "statium/space/createForm";
	}

	/**
	 * 
	 * <修改场地表单><功能具体实现>
	 *
	 * @create：2015年8月10日 下午5:01:35
	 * @author： CYY
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "updateForm", method = RequestMethod.GET)
	public String updateForm(Model model, String id) {
		try {
			StatiumSpace space = statiumSpaceService.findStatiumSpaceById(id);
			model.addAttribute("space", space);
			model.addAttribute("action", "edit");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return "statium/space/form";
	}

	/**
	 * 
	 * <场地详细表单><功能具体实现>
	 *
	 * @create：2015年8月11日 下午5:01:35
	 * @author： CYY
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "detailForm", method = RequestMethod.GET)
	public String detailForm(Model model, String id) {
		try {
			StatiumSpace space = statiumSpaceService.findStatiumSpaceById(id);
			model.addAttribute("space", space);
			model.addAttribute("action", "detail");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return "statium/space/form";
	}

	/**
	 * 
	 * <创建场地><功能具体实现>
	 *
	 * @create：2015年8月10日 上午11:35:43
	 * @author： CYY
	 * @param statium
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(StatiumSpaceVo statiumSpace, String priceTemps) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			// 场馆价格模板操作
			if (StringUtils.isNotEmpty(priceTemps)) {
				Type type = new TypeToken<List<StatiumPriceTmplVo>>() {
				}.getType();
				List<StatiumPriceTmplVo> list = MyGson.getInstance().fromJson(priceTemps, type);
				statiumSpaceService.create(statiumSpace, list);
				result.put(Constants.Result.RESULT, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("创建场地失败: {}", e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "创建场地失败");
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 * 
	 * <更新场地信息><功能具体实现>
	 *
	 * @create：2015年8月10日 上午11:35:43
	 * @author： CYY
	 * @param statium
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(StatiumSpace statiumSpace, String priceTemps) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.isNotEmpty(statiumSpace.getId())) {
				StatiumSpace space = statiumSpaceService.findStatiumSpaceById(statiumSpace.getId());
				statiumSpace.setSportType(space.getSportType());
				statiumSpace.setSpaceCode(space.getSpaceCode());
				if (StringUtils.isNotEmpty(priceTemps)) {
					// 场馆价格模板操作
					Type type = new TypeToken<List<StatiumPriceTmplVo>>() {
					}.getType();
					List<StatiumPriceTmplVo> list = MyGson.getInstance().fromJson(priceTemps, type);
					statiumSpaceService.update(statiumSpace, list,"simple");
					result.put(Constants.Result.RESULT, true);
				} else {
					result.put(Constants.Result.RESULT, false);
					result.put(Constants.Result.REASON, "价格不能为空");
				}
			} else {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "id不能为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改场地失败: {}", e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "修改场地失败");
		}
		return MyGson.getInstance().toJson(result);
	}
	
	
	@RequestMapping(value = "batchModify", method = RequestMethod.POST)
	@ResponseBody
	public String batchModify(StatiumSpaceBatch statiumSpaces, String priceTemps) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String ids[] = statiumSpaces.getId();
			Type type = new TypeToken<List<StatiumPriceTmplVo>>() {
			}.getType();
			logger.debug("批量{}",priceTemps);
			List<StatiumPriceTmplVo> temps = MyGson.getInstance().fromJson(priceTemps, type);
			StatiumSpace save_ = new StatiumSpace();
			for(int i=0;i<ids.length;i++){
				StatiumSpace space = statiumSpaceService.findStatiumSpaceById(ids[i]);
				save_.setSportType(space.getSportType());
				save_.setSpaceCode(space.getSpaceCode());
				save_.setId(ids[i]);
				save_.setSpaceName(statiumSpaces.getSpaceName()[i]);
				save_.setStatiumId(space.getStatiumId());
				if (StringUtils.isNotEmpty(priceTemps)) {
					// 场馆价格模板操作
					List<StatiumPriceTmplVo> temp = new ArrayList<StatiumPriceTmplVo>();
					temp.add(temps.get(i));
					if(i==ids.length-1){
						statiumSpaceService.update(save_, temp,"simple");
					}else{
						statiumSpaceService.update(save_, temp,"batch");
					}
					result.put(Constants.Result.RESULT, true);
				} else {
					result.put(Constants.Result.RESULT, false);
					result.put(Constants.Result.REASON, "价格不能为空");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改场地失败: {}", e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "修改场地失败");
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 * 
	 * <删除场地><功能具体实现>
	 *
	 * @create：2015年8月10日 下午16:35:43
	 * @author： CYY
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(String id,String statiumId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			boolean isSuccess = statiumSpaceService.delete(id,statiumId);
			result.put(Constants.Result.RESULT, isSuccess);
			result.put(Constants.Result.REASON, "有未完成订单不可删除场地");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除场地失败: {}", e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "删除场地失败");
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 * 
	 * <批量删除场地><功能具体实现>
	 *
	 * @create：2015年10月29日 上午11:45:06
	 * @author： liangsh
	 * @param ids
	 * @param statiumId
	 * @return
	 */
	@RequestMapping(value = "deleteAll", method = RequestMethod.POST)
	@ResponseBody
	public String deleteAll(String ids,String statiumId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String[] idArray = ids.split(";");
			statiumSpaceService.deleteAll(idArray,statiumId);
			result.put(Constants.Result.RESULT, true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除场地失败: {}", e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "删除场地失败");
		}
		return MyGson.getInstance().toJson(result);
	}
	
	@RequestMapping(value = "batchUpdate/{ids}")
	public String batchUpdate(@PathVariable String ids,Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			logger.debug("ids,{}",ids);
			String id[] = ids.split(",");
			String statiumId = "";
			List<StatiumSpace> spaces = new ArrayList<StatiumSpace>();
			for(String i:id){
				StatiumSpace space = statiumSpaceService.findStatiumSpaceById(i);
				statiumId = space.getStatiumId();
				spaces.add(space);
			}
			model.addAttribute("spaces", spaces);
			model.addAttribute("statiumId", statiumId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除场地失败: {}", e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "删除场地失败");
		}
		return "statium/space/forms";
	}
	
	@RequestMapping(value = "batchSearch/{ids}")
	public String batchSearch(@PathVariable String ids,Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			logger.debug("ids,{}",ids);
			String id[] = ids.split(",");
			String statiumId = "";
			List<StatiumSpace> spaces = new ArrayList<StatiumSpace>();
			for(String i:id){
				StatiumSpace space = statiumSpaceService.findStatiumSpaceById(i);
				statiumId = space.getStatiumId();
				spaces.add(space);
			}
			model.addAttribute("spaces", spaces);
			model.addAttribute("statiumId", statiumId);
			model.addAttribute("action", "detail");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除场地失败: {}", e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "删除场地失败");
		}
		return "statium/space/forms";
	}

	/**
	 * 
	 * <根据场地类型获取场地价格模板><功能具体实现>
	 *
	 * @create：2015年8月10日 下午16:35:43
	 * @author： CYY
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "spacePriceTmpl", method = RequestMethod.POST)
	@ResponseBody
	public String spacePriceTmplBySportType(String statiumId, String sportType) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.isNotEmpty(statiumId) && StringUtils.isNotEmpty(sportType)) {
				String json = statiumPriceTmplService.spacePriceTmplToJson(statiumId, sportType);
				result.put(Constants.Result.DATA, json);
				result.put(Constants.Result.RESULT, true);
			} else {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "缺少参数");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取场地价格失败: {}", e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "获取场地价格失败");
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 * 
	 * <根据场地类型获取场地价格><功能具体实现>
	 *
	 * @create：2015年8月10日 下午16:35:43
	 * @author： CYY
	 * @param statiumId
	 *            场馆id
	 * @param spaceId
	 *            场地id
	 * @param sportType
	 *            运动类型
	 * @return
	 */
	@RequestMapping(value = "spacePrice", method = RequestMethod.POST)
	@ResponseBody
	public String spacePriceBySportType(String statiumId, String spaceId, String sportType) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.isNotEmpty(spaceId)) {
				String json = statiumPriceTmplService.spacePriceToJson(statiumId, spaceId, sportType);
				if (StringUtils.isNotEmpty(json)) {
					result.put(Constants.Result.DATA, json);
					result.put(Constants.Result.RESULT, true);
				} else {
					result.put(Constants.Result.RESULT, false);
					result.put(Constants.Result.REASON, "价格模板为空，先去保存并初始化价格模板，然后再来修改场地信息");
				}
			} else {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "缺少参数");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取场地价格失败: {}", e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "获取场地价格失败");
		}
		return MyGson.getInstance().toJson(result);
	}

}
