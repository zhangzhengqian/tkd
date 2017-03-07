package com.lc.zy.ball.boss.framework.ssouser.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.google.gson.Gson;
import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.FlashAttributeUtil;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.ssouser.service.CoachService;
import com.lc.zy.ball.boss.framework.ssouser.service.SsoUserService;
import com.lc.zy.ball.boss.framework.ssouser.vo.CoachVo;
import com.lc.zy.ball.boss.framework.statium.service.StatiumDetailService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.domain.oa.po.CoachResume;
import com.lc.zy.ball.domain.oa.po.Coacher;
import com.lc.zy.common.mq.QueueNames;
import com.lc.zy.common.mq.QueueProducer;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.ExcelUtil;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.web.Token;
import com.lc.zy.common.web.Token.Type;
import com.lc.zy.common.web.WebUtils;

/**
 * 用户管理 - 教陪管理
 * 
 * @author chenglong
 */
@Controller
@RequestMapping(value = "/ssouser/coach")
public class CoachController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(CoachController.class);

    @Autowired
    private CoachService coachService;
    
    @Autowired
    private StatiumDetailService statiumDetailService;
    
    @Autowired
    private QueueProducer queueProducer;
    
	@Autowired
	private SsoUserService ssoUserService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 教陪 分页展示
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "")
    public String CoachList(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes)
            throws Exception {
        logger.debug("CoachController CoachList method execute!");
        // 根据查询条件查询数据
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        parseDate(searchParams, "GTE_submitTime");
        parseDate(searchParams, "LTE_submitTime");
        parseDate(searchParams, "GTE_passTime");
        parseDate(searchParams, "LTE_passTime");

        if (searchParams.get("EQ_areaCode")!=null) {
        	String areaCode = (String)searchParams.get("EQ_areaCode");
        	if(areaCode.endsWith("0000")){
        		searchParams.put("LIKE_area", areaCode.substring(0,2)+"%");
    		}else if(areaCode.endsWith("00")){
    			searchParams.put("LIKE_area", areaCode.substring(0,4)+"%");
    		}else{
    			searchParams.put("EQ_area", areaCode);
    		}
        }

        int page = WebUtils.getPage(request);
        int size = WebUtils.getPageSize(request);
        CommonOAUtils.paramesTrim(searchParams);// 参数去空

        Page<CoachVo> onePage = coachService.find(searchParams, page, size, true, true);

        int unCheckNum = coachService.getUnCheckNum();
        model.addAttribute("data", onePage);
        model.addAttribute("unCheckNum", unCheckNum);
        // 将搜索条件编码成字符串，用于排序，分页的URL
        String as[] = { "GTE_submitTime", "LTE_submitTime", "GTE_passTime", "LTE_passTime" };
        for (String s : as) {
            if (null != searchParams.get(s) && searchParams.get(s) instanceof Date) {
                searchParams.put(s, sdf.format(searchParams.get(s)));
            }
        }
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

        return "ssouser/coachList";
    }

    /**
     * 删除教练 某个ID
     * 
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "delete/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@PathVariable String userId) throws Exception {
        Gson gson = new Gson();
        Map<String, String> result = new HashMap<String, String>();
        result.put("result", "error");
        try {
            coachService.deleteCoach(userId);
            result.put("result", "success");
        } catch (Exception e) {
            logger.error("批量删除教练失败！", e);
        }
        return gson.toJson(result);
    }

    /**
     * 批量删除教练 某个ID
     * 
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "batchDelete", method = RequestMethod.POST)
    @ResponseBody
    public String batchDelete(String ids) throws Exception {
        Gson gson = new Gson();
        Map<String, String> result = new HashMap<String, String>();
        result.put("result", "error");
        try {
            coachService.deleteBatchCoach(ids);
            result.put("result", "success");
        } catch (Exception e) {
            logger.error("删除教练失败！", e);
        }
        return gson.toJson(result);
    }

    /**
     * 修改教陪练冻结和解冻状态
     * 
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "updateStatus/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public String updateStatus(@PathVariable String userId, String status) throws Exception {
        Gson gson = new Gson();
        Map<String, String> result = new HashMap<String, String>();
        result.put("result", "error");
        try {
            coachService.updateStatus(userId, status);
            Coacher info = coachService.selectByPrimaryKey(Coacher.class, userId);
            //同时冻结或解冻用户
            if(status.equals("1")){
            	ssoUserService.freezeSsoUser(info.getSsoUserId());
            }else{
            	ssoUserService.unfreezeSsoUser(info.getSsoUserId());
            }
            //更新教陪信息到solr
            queueProducer.push(QueueNames.SYNC_COACH_QUEUE,info.getSsoUserId() );
            result.put("result", "success");
        } catch (Exception e) {
            logger.error("冻结和解冻教练失败！", e);
        }
        return gson.toJson(result);
    }

    /**
     * excel导出数据
     * 
     * @param model
     * @param request
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
    @RequestMapping(value = { "export" })
    public void exportData(Model model, HttpServletRequest request, HttpServletResponse response,
            RedirectAttributes redirectAttributes) throws Exception {
        logger.debug("CoachController exportData method execute!");
        List<CoachVo> coachList = new ArrayList<CoachVo>();
        // 根据查询条件查询数据
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        
        parseDate(searchParams, "GTE_submitTime");
        parseDate(searchParams, "LTE_submitTime");
        parseDate(searchParams, "GTE_passTime");
        parseDate(searchParams, "LTE_passTime");

        if (searchParams.get("EQ_areaCode")!=null) {
        	String areaCode = (String)searchParams.get("EQ_areaCode");
        	if(areaCode.endsWith("0000")){
        		searchParams.put("LIKE_area", areaCode.substring(0,2)+"%");
    		}else if(areaCode.endsWith("00")){
    			searchParams.put("LIKE_area", areaCode.substring(0,4)+"%");
    		}else{
    			searchParams.put("EQ_area", areaCode);
    		}
        }
        int page = WebUtils.getPage(request);
        int size = WebUtils.getPageSize(request);

        Page<CoachVo> onePage = coachService.find(searchParams, page, size, false, false);
        coachList = onePage.getContent();

        ExcelUtil excelUtil = new ExcelUtil();
        String fileName = excelUtil.createtFileName("导出教陪练数据");
        fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
        response.reset();
        // 指定下载的文件名
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        String title = "教陪练列表";
        String[] headers = { "球友号::qiuyouno", "姓名::name", "用户类型::userType","价格::price", "性别::sex", "注册手机::phone", "所在省::province","所在市::city","所在区::area",
                "运动类型::sprotTypeZWStr", "职业类型::occType", "教练资质::coachQual",
                "注册来源::registSourceStr", "签约人::signUserName","用户状态::userStateStr"};

        excelUtil.exportExcel(title, headers, coachList, response.getOutputStream(), "yyyy-MM-dd");
    }

    @Token
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) throws Exception {
        model.addAttribute("action", "create");
        CoachVo coach = new CoachVo();
        model.addAttribute("signUser", statiumDetailService.getSignUser());
        model.addAttribute("coach", coach);
        return "ssouser/coachForm";
    }

    /**
     * 教陪练 添加
     * 
     * @param coachVo
     * @param redirectAttributes
     * @return
     */
    @Token(Type.REMOVE)
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(CoachVo coachVo, RedirectAttributes redirectAttributes) {
        try {
        	logger.debug("======================");
            Map<String, String> result = coachService.createOrUpdateCoach(coachVo);
            logger.debug(result.toString()+"======================");
            //更新教陪信息到solr
            if(result.containsKey("new")){
            	queueProducer.push(QueueNames.SYNC_COACH_QUEUE, result.get("new"));
            }
            logger.debug("======================");
            if(result.containsKey("old")){
            	String oldId = result.get("old");
            	queueProducer.push(QueueNames.SYNC_COACH_QUEUE, "del:"+oldId);
            }
            logger.debug("======================");
            redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "创建教陪练成功!"));
        } catch (Exception e) {
        	logger.debug("======================");
            logger.debug("创建教陪练失败: {}", e.getMessage());
            logger.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "创建教陪练失败！"));
        }
        return "redirect:/ssouser/coach";
    }
    
    /**
     * 检测手机号是否已经注册
     * 
     * @param id
     * @param name
     * @return
     */
    @RequestMapping(value = "checkPhone", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkPhone(String oldPhone, String phone) {
        try {
            oldPhone = oldPhone == null ? "" : oldPhone;
            if (oldPhone.equals(phone)) {
                return true;
            } else {
                if (CommonUtils.isNotEmpty(phone)) {
                    return !coachService.checkPhone(phone);
                } else {
                    return false;
                }
            }

        } catch (Exception e) {
            logger.error("检测教陪练手机号是否已经注册失败！", e);
        }
        return false;
    }

    /**
     * 查看教陪练
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "view/{id}")
    public String view(@PathVariable String id, Model model) {
        CoachVo coach;
        try {
            coach = coachService.getCoachVo(id);
            model.addAttribute("coach", coach);
            model.addAttribute("signUser", statiumDetailService.getSignUser());
        } catch (Exception e) {
            logger.error("查看教陪练失败！", e);
        }
        return "/ssouser/coachView";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable String id, Model model, RedirectAttributes redirectAttributes)
            throws Exception {
        model.addAttribute("coach", coachService.getCoachVo(id));
        model.addAttribute("signUser", statiumDetailService.getSignUser());
        model.addAttribute("action", "update");
        return "ssouser/coachForm";
    }

    /**
     * 修改
     * 
     * @param id
     * @param model
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(CoachVo coachVo, RedirectAttributes redirectAttributes) {
        try {
        	logger.debug("======================");
            Map<String, String> result = coachService.createOrUpdateCoach(coachVo);
            logger.debug(result.toString()+"======================");
            //更新教陪信息到solr
            if(result.containsKey("new")){
            	queueProducer.push(QueueNames.SYNC_COACH_QUEUE, result.get("new"));
            }
            logger.debug("======================");
            if(result.containsKey("old")){
            	String oldId = result.get("old");
            	queueProducer.push(QueueNames.SYNC_COACH_QUEUE, "del:"+oldId);
            }
            logger.debug("======================");
            redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "保存教陪练信息成功!"));
        } catch (Exception e) {
            logger.debug("保存教陪练信息失败: {}", e.getMessage());
            logger.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute("message",
                    FlashAttributeUtil.build(false, "保存教陪练信息失败:" + e.getMessage()));
        }

        return "redirect:/ssouser/coach";
    }

    /**
     * 
     * <保存执教经历><功能具体实现>
     *
     * @create：2015年9月03日 下午11:35:43
     * @author： CYY
     * @param statium
     * @return
     */
    @RequestMapping(value = "saveResume", method = RequestMethod.POST)
    @ResponseBody
    public String saveResume(CoachResume coachResume) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            coachService.saveResume(coachResume);
            result.put(Constants.Result.RESULT, true);
        } catch (Exception e) {
            logger.error("保存执教经历失败: {}", e);
            result.put(Constants.Result.RESULT, false);
            result.put(Constants.Result.REASON, "保存执教经历失败");
        }
        return MyGson.getInstance().toJson(result);
    }
    
    /**
     * 
     * <教陪练审核接口><功能具体实现>
     *
     * @create：2015年9月03日 下午11:35:43
     * @author： yankefei
     * @param coachId
     * @return
     */
    @RequestMapping(value = "audit/{coachId}/{level}", method = RequestMethod.POST)
    @ResponseBody
    public String auditCoach(@PathVariable String coachId,@PathVariable Integer level) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            String ssoUserId = coachService.audit(coachId,level);
            if(ssoUserId==null || "".equals(ssoUserId)){
            	result.put(Constants.Result.RESULT, false);
            	result.put(Constants.Result.REASON, "教陪练审核失败");
            }else{
            	result.put(Constants.Result.RESULT, true);
            	//审核通过后，同步教陪数据到solr
            	queueProducer.push(QueueNames.SYNC_COACH_QUEUE,ssoUserId);
            }
        } catch (Exception e) {
            logger.error("教陪练审核失败: {}", e);
            result.put(Constants.Result.RESULT, false);
            result.put(Constants.Result.REASON, "教陪练审核失败");
        }
        return MyGson.getInstance().toJson(result);
    }
    
    /**
     * 
     * <置顶球馆><功能具体实现>
     *
     * @create：2015年10月10日 下午2:34:09
     * @author： CYY
     * @param id 场馆id
     * @return
     */
    @RequestMapping(value = "top")
    @ResponseBody
    public String top(String id, Integer type) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (StringUtils.isNotEmpty(id)) {
                result = coachService.top(id, result, type);
                Coacher coach = coachService.selectByPrimaryKey(Coacher.class, id);
                queueProducer.push(QueueNames.SYNC_COACH_QUEUE,coach.getSsoUserId());
            } else {
                result.put(Constants.Result.RESULT, false);
                result.put(Constants.Result.REASON, "id不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            result.put(Constants.Result.RESULT, false);
            result.put(Constants.Result.REASON, "服务器繁忙");
        }
        return MyGson.getInstance().toJson(result);
    }
    public static void main(String[] args) {
    	Map<String, String> zone = com.lc.zy.common.util.Zonemap.split("370113");
    	System.out.println(zone.get("province"));
    	System.out.println(zone.get("city"));
    	System.out.println(zone.get("area"));
	}
}
