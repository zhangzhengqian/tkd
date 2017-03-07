package com.lc.zy.ball.boss.framework.activity.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.lc.zy.ball.boss.common.BarcodeFactory;
import com.lc.zy.ball.boss.common.FlashAttributeUtil;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.activity.service.ActivityService;
import com.lc.zy.ball.boss.framework.activity.vo.ActivityItemVo;
import com.lc.zy.ball.boss.framework.activity.vo.ActivityMemberVo;
import com.lc.zy.ball.boss.framework.activity.vo.ActivityVo;
import com.lc.zy.ball.boss.framework.push.service.PushService;
import com.lc.zy.ball.boss.framework.ssouser.service.SsoUserService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.domain.oa.po.Activity;
import com.lc.zy.ball.domain.oa.po.ActivityItem;
import com.lc.zy.ball.domain.oa.po.MemberList;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.common.Constants;
import com.lc.zy.common.cache.RedisService;
import com.lc.zy.common.emsgclient.EmsgPushClient;
import com.lc.zy.common.mq.QueueNames;
import com.lc.zy.common.mq.QueueProducer;
import com.lc.zy.common.push.PushPacket;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.ExportExcelUtil;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.web.WebUtils;

/**
 * <活动管理>
 * 
 * @author yankefei
 * @date 2015年10月14日 上午11:55:55
 */
@Controller
@RequestMapping(value = "/activity")
public class ActivityController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private ActivityService activityService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private SsoUserService ssoUserService;

    @Autowired
    private QueueProducer queueProducer;
    
    @Autowired
    private PushService pushService;
    
    @Autowired
    private PushPacket pushPacket;
    
    @Autowired
	private EmsgPushClient client;

    /**
     * <活动列表><功能具体实现>
     * 
     * @param model
     * @param request
     * @param redirectAttributes
     * @return
     * @throws Exception
     * @author yankefei
     * @date 2015年10月15日 上午11:30:28
     */
    @RequestMapping(value = "list")
    public String ActivityList(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes)
            throws Exception {
        logger.debug("ActivityController ActivityList method execute!");
        // 根据查询条件查询数据
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        // parseDate(searchParams, "GTE_startTime");
        // parseDate(searchParams, "LTE_startTime");
        int page = WebUtils.getPage(request);
        int size = WebUtils.getPageSize(request);
        CommonOAUtils.paramesTrim(searchParams);
        Page<ActivityVo> onePage = activityService.find(searchParams, page, size, true, true);
        int maxStick = activityService.getMaxStick();

        model.addAttribute("maxStick", maxStick);
        model.addAttribute("data", onePage);
        // 将搜索条件编码成字符串，用于排序，分页的URL
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "activity/activityList";
    }

    /**
     * <活动删除><功能具体实现>
     * 
     * @param id
     * @return
     * @throws Exception
     * @author yankefei
     * @date 2015年10月15日 上午10:34:01
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@PathVariable String id) throws Exception {
        Gson gson = new Gson();
        Map<String, String> result = new HashMap<String, String>();
        result.put("result", "error");
        try {
            activityService.deleteActivity(id);
            result.put("result", "success");
        } catch (Exception e) {
            logger.error("删除活动失败！", e);
        }
        return gson.toJson(result);
    }

    /**
     * <创建活动Form>><功能具体实现>
     * 
     * @param model
     * @return
     * @throws Exception
     * @author yankefei
     * @date 2015年10月15日 上午13:26:07
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) throws Exception {
        model.addAttribute("action", "create");
        return "activity/activityForm";
    }

    /**
     * <创建活动><功能具体实现>
     * 
     * @param activityVo
     * @param redirectAttributes
     * @return
     * @throws Exception
     * @author yankefei
     * @date 2015年10月15日 上午15:02:17
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(ActivityVo activityVo, RedirectAttributes redirectAttributes) {
        try {
            logger.debug("发布活动={}",SessionUtil.currentUser().getUserId());
            activityVo.setRemainCount(activityVo.getTotalNumber());
            String activityId = activityService.createOrUpdateActivity(activityVo);
            // 同步solr
            queueProducer.push(QueueNames.SYNC_ACTIVITY_QUEUE, activityId);
            redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "创建活动成功!"));
            
            // 发送活动推送 add by sl 2015-12-14
			if (activityVo.getIsPush() == 0) {
				String areaCode = activityVo.getAreaCode();
				pushPacket.sendPush(areaCode, Constants.pushMsgType.PUSH_TYPE_ACTIVITY, activityId,
						activityVo.getName(), SessionUtil.currentUserId(), Constants.isPush.PUSH_RYPE_TIME, "");
			}
        } catch (Exception e) {
            logger.error("创建活动失败: {}", e.getMessage());
            logger.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "创建活动失败！"));
        }
        return "redirect:/activity/list";
    }

    /**
     * <查看活动><功能具体实现>
     * 
     * @param activityId
     * @param model
     * @return
     * @author yankefei
     * @date 2015年10月16日 下午6:01:11
     */
    @RequestMapping(value = "view/{activityId}")
    public String view(@PathVariable String activityId, Model model) {
        try {
            ActivityVo activityVo = activityService.getActivityVo(activityId);
            List<ActivityItemVo> list = activityService.getActivityItemList(activityId);
            if (!CollectionUtils.isEmpty(list)) {
                ActivityItemVo itemVo = list.get(0);
                model.addAttribute("firstItem", itemVo);
            }
            model.addAttribute("activity", activityVo);
        } catch (Exception e) {
            logger.error("查看活动失败！", e);
        }
        return "/activity/activityView";
    }

    /**
     * <活动用户审核列表><功能具体实现>
     * 
     * @create：2015年10月13日 下午3:43:38
     * @author： yankefei
     * @param activityItemId
     * @param model
     * @return
     */
    @RequestMapping(value = "userList/{activityItemId}")
    public String userList(@PathVariable String activityItemId, Model model) {
        try {
            ActivityItem item = activityService.getActivityItem(activityItemId);
            String activityId = item.getActivityId();
            ActivityItemVo aiVo = new ActivityItemVo();
            aiVo.setId(item.getId());
            aiVo.setDate(DateUtils.formatDate(item.getStartDate()));

            // 获取活动item的用户列表
            List<ActivityMemberVo> userList = activityService.itemUserList(activityItemId);
            // 获取活动的item列表
            List<ActivityItemVo> activityItemList = activityService.getActivityItemList(activityId);

            model.addAttribute("userList", userList);
            model.addAttribute("activityItem", aiVo);
            model.addAttribute("activityItemList", activityItemList);
        } catch (Exception e) {
            logger.error("查看用户审核列表失败", e);
        }
        return "/activity/userList";
    }

    /**
     * <活动成员审核><功能具体实现>
     * 
     * @param userId
     * @param activityId
     * @param state
     * @param model
     * @return
     * @author yankefei
     * @date 2015年10月16日 上午16:49:37
     */
    @RequestMapping(value = "updateState/{userId}/{activityItemId}/{state}")
    public String updateState(@PathVariable String userId, @PathVariable String activityItemId,
            @PathVariable Integer state, Model model) {
        try {
            activityService.updateState(userId, activityItemId, state);
        } catch (Exception e) {
            logger.error("查看用户审核列表异常={}", e.getMessage());
        }
        return "redirect:/activity/userList/" + activityItemId;
    }

    /**
     * <查看用户信息><功能具体实现>
     * 
     * @param userId
     * @param activityItemId
     * @param model
     * @return
     * @author yankefei
     * @date 2015年10月15日 上午11:11:52
     */
    @RequestMapping(value = "findUserById/{userId}/{activityItemId}")
    public String findUserById(@PathVariable String userId, @PathVariable String activityItemId, Model model) {
        try {
            MemberList member = activityService.findActivityMember(userId, activityItemId);

            SsoUser ssoUser = ssoUserService.viewSsoUser(userId);
            model.addAttribute("ssoUser", ssoUser);
            model.addAttribute("activityItemId", activityItemId);
            // 该用户报名的审核状态
            model.addAttribute("auditState", member.getState());
        } catch (Exception e) {
            logger.error("查看用户信息失败！", e);
        }
        return "/activity/userView";
    }

    /**
     * <活动item列表><功能具体实现>
     * 
     * @param activityId
     * @param model
     * @return
     * @throws Exception
     * @author yankefei
     * @date 2015年10月16日 下午6:48:03
     */
    @RequestMapping(value = "itemList/{activityId}")
    public String activityItemList(@PathVariable String activityId, Model model) throws Exception {
        List<ActivityItem> list = activityService.getAllActivityItemList(activityId);
        model.addAttribute("itemList", list);
        return "activity/activityItemList";
    }

    /**
     * <活动修改Form><功能具体实现>
     * 
     * @param activityId
     * @param model
     * @return
     * @author yankefei
     * @date 2015年10月16日 下午8:11:49
     */
    @RequestMapping(value = "update/{activityId}")
    public String updateActivity(@PathVariable String activityId, Model model) {
        ActivityVo activityVo = activityService.getActivityVo(activityId);
        model.addAttribute("activity", activityVo);
        model.addAttribute("action", "update");
        return "/activity/activityForm";
    }

    /**
     * <活动修改><功能具体实现>
     * 
     * @param activityVo
     * @param redirectAttributes
     * @return
     * @author yankefei
     * @date 2015年10月21日 上午11:50:09
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(ActivityVo activityVo, RedirectAttributes redirectAttributes) {
        try {
            String activityId = activityService.createOrUpdateActivity(activityVo);
            queueProducer.push(QueueNames.SYNC_ACTIVITY_QUEUE, activityId);
            // 发送活动推送 add by sl 2015-12-14
            if (activityVo.getIsPush() == 0) {
            	String areaCode = activityVo.getAreaCode();
				pushPacket.sendPush(areaCode, Constants.pushMsgType.PUSH_TYPE_ACTIVITY, activityId,
						activityVo.getName(), SessionUtil.currentUserId(), Constants.isPush.PUSH_RYPE_TIME, "");
			}
            redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "修改活动信息成功!"));
        } catch (Exception e) {
            logger.debug("修改活动信息失败: {}", e.getMessage());
            logger.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute("message",
                    FlashAttributeUtil.build(false, "修改活动信息失败:" + e.getMessage()));
        }
        return "redirect:/activity/list";
    }

    /**
     * <置顶><功能具体实现>
     * 
     * @param redirectAttributes
     * @param activityId
     * @return
     * @author yankefei
     * @date 2015年10月16日 下午4:03:14
     */
    @RequestMapping(value = "stick/{activityId}")
    public String ActivityStick(RedirectAttributes redirectAttributes, @PathVariable String activityId) {
        if (CommonUtils.isNotEmpty(activityId)) {
            try {
                activityService.stick(activityId);
            } catch (Exception e) {
                logger.debug("置顶活动失败: {}", e.getMessage());
                logger.error(e.getMessage(), e);
                redirectAttributes.addFlashAttribute("message",
                        FlashAttributeUtil.build(false, "置顶活动失败:" + e.getMessage()));
            }
        }
        redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "活动置顶成功"));
        return "redirect:/activity/list";
    }

    /**
     * <置底><功能具体实现>
     * 
     * @param redirectAttributes
     * @param activityId
     * @return
     * @author yankefei
     * @date 2015年10月16日 下午2:01:40
     */
    @RequestMapping(value = "unstick/{activityId}")
    public String ActivityUnStick(RedirectAttributes redirectAttributes, @PathVariable String activityId) {
        if (CommonUtils.isNotEmpty(activityId)) {
            try {
                activityService.unStick(activityId);
            } catch (Exception e) {
                logger.debug("取消置顶活动失败: {}", e.getMessage());
                logger.error(e.getMessage(), e);
                redirectAttributes.addFlashAttribute("message",
                        FlashAttributeUtil.build(false, "取消置顶活动失败:" + e.getMessage()));
            }
        }
        redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "取消活动置顶成功"));
        return "redirect:/activity/list";
    }

    /**
     * <上移><功能具体实现>
     * 
     * @param redirectAttributes
     * @param activityId
     * @return
     * @author yankefei
     * @date 2015年10月16日 下午4:04:32
     */
    @RequestMapping(value = "moveUp/{activityId}")
    public String activityMoveUp(RedirectAttributes redirectAttributes, @PathVariable String activityId) {
        activityService.moveUp(activityId);
        return "redirect:/activity/list";
    }

    /**
     * <下移><功能具体实现>
     * 
     * @param redirectAttributes
     * @param activityId
     * @return
     * @author yankefei
     * @date 2015年10月16日 下午5:27:27
     */
    @RequestMapping(value = "moveDown/{activityId}")
    public String activityMoveDown(RedirectAttributes redirectAttributes, @PathVariable String activityId) {
        activityService.moveDown(activityId);
        return "redirect:/activity/list";
    }

    /**
     * <更改活动item状态><功能具体实现>
     * 
     * @param redirectAttributes
     * @param activityItemId
     * @return
     * @author yankefei
     * @date 2015年10月16日 下午7:21:03
     */
    @RequestMapping(value = "itemState/{activityItemId}", method = RequestMethod.POST)
    @ResponseBody
    public String updateItemState(@PathVariable String activityItemId) {
        Map<String, String> map = new HashMap<String, String>();
        if (activityService.updateItemState(activityItemId)) {
            map.put("result", "0");
            map.put("desc", "更新活动详情状态成功");
        } else {
            map.put("result", "1");
            map.put("desc", "更新活动详情状态失败");
        }
        return MyGson.getInstance().toJson(map);
    }

    /**
     * 
     * <活动统计><功能具体实现>
     *
     * @create：2015年12月7日 下午3:39:58
     * @author： CYY
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "statisticsList")
    public String activityStatisticsList(Model model, HttpServletRequest request) throws Exception {
        logger.debug("ActivityController activityStatisticsList method execute!");
        // 根据查询条件查询数据
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        int page = WebUtils.getPage(request);
        int size = WebUtils.getPageSize(request);
        CommonOAUtils.paramesTrim(searchParams);
        Page<ActivityVo> onePage = activityService.activityStatisticsList(searchParams, page, size, true);
        model.addAttribute("data", onePage);
        // 将搜索条件编码成字符串，用于排序，分页的URL
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "activity/activityStatisticsList";
    }

    /**
     * 
     * <修改统计><功能具体实现>
     *
     * @create：2015年12月7日 下午3:39:58
     * @author： CYY
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "updateActivityStatistics")
    public String updateActivityStatistics(Model model, ActivityVo vo) throws Exception {
        logger.debug("ActivityController updateActivityStatistics method execute!");
        activityService.updateActivityStatistics(vo);
        return "redirect:/activity/statisticsList";
    }

    /**
     * 
     * <修改统计页面><功能具体实现>
     *
     * @create：2015年12月7日 下午4:40:23
     * @author： CYY
     * @param activityId
     * @param model
     * @return
     */
    @RequestMapping(value = "updateForm/{activityId}")
    public String updateForm(@PathVariable String activityId, Model model) {
        try {
            ActivityVo activityVo = activityService.getActivityVo(activityId);
            model.addAttribute("activity", activityVo);
        } catch (Exception e) {
            logger.error("修改统计页面", e);
        }
        return "/activity/activityStatisticsForm";
    }

    /**
     * 
     * <导出exce><功能具体实现>
     *
     * @create：2015年12月7日 下午6:13:48
     * @author： CYY
     * @param response
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "export")
    public void export(HttpServletResponse response, HttpServletRequest request) throws Exception {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        try {
            // 根据查询条件查询数据
            int page = WebUtils.getPage(request);
            int size = WebUtils.getPageSize(request);
            CommonOAUtils.paramesTrim(searchParams);
            Page<ActivityVo> onePage = activityService.activityStatisticsList(searchParams, page, size, false);
            if (onePage != null && onePage.getSize() > 0) {
                writeExcel(response, onePage);
            }
        } catch (Exception e) {
            logger.error("导出exce出错", e);
        }
    }

    /**
     * 定义导出Excel的样式
     * 
     * @param response
     * @param voList
     * @throws Exception
     */
    public Map<String, Object> writeExcel(HttpServletResponse response, Page<ActivityVo> onePage) throws Exception {
        String uuid = UUID.get();
        String fileName = uuid + ".xls";
        fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
        response.reset();
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);// 指定下载的文件名
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        OutputStream output = response.getOutputStream();
        BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);

        // 定义单元格报头
        String worksheetTitle = "活动统计(" + DateUtil.formatDate(new Date(), "yyyy-MM-dd") + ")";

        HSSFWorkbook wb = new HSSFWorkbook();

        // 创建单元格样式
        HSSFCellStyle cellStyleTitle = wb.createCellStyle();
        // 指定单元格居中对齐
        cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 指定单元格垂直居中对齐
        cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 指定当单元格内容显示不下时自动换行
        cellStyleTitle.setWrapText(true);
        // ------------------------------------------------------------------
        HSSFCellStyle cellStyle = wb.createCellStyle();
        // 指定单元格居中对齐
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 指定单元格垂直居中对齐
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 指定当单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);
        // ------------------------------------------------------------------
        // 设置单元格字体
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        cellStyleTitle.setFont(font);

        HSSFSheet sheet = wb.createSheet();
        ExportExcelUtil exportExcel = new ExportExcelUtil(wb, sheet);
        // 创建报表头部
        exportExcel.createNormalHead(worksheetTitle, 8);
        // 定义第一行
        HSSFRow row1 = sheet.createRow(1);
        HSSFCell cell1 = row1.createCell(0);

        // 第一行第1列
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("活动名称"));

        // 第一行第2列
        cell1 = row1.createCell(1);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("运营成本（总）"));

        // 第一行第3列
        cell1 = row1.createCell(2);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("报名费"));

        // 第一行第4列
        cell1 = row1.createCell(3);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("报名人数"));

        // 第一行第5列
        cell1 = row1.createCell(4);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("场地支出"));

        // 第一行第6列
        cell1 = row1.createCell(5);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("盈利"));

        // 第一行第7列
        cell1 = row1.createCell(6);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("发布人"));

        // 第一行第8列
        cell1 = row1.createCell(7);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("发布时间"));

        // 第一行第9列
        cell1 = row1.createCell(8);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("备注"));

        // 定义第二行
        HSSFRow row = sheet.createRow(2);
        HSSFCell cell = row.createCell(1);
        int i = 1;
        HSSFCellStyle dateCellStyle = wb.createCellStyle();
        short df = wb.createDataFormat().getFormat("yyyy-mm-dd HH:mm:ss");
        dateCellStyle.setDataFormat(df);
        HSSFCellStyle dateCellStyle_ = wb.createCellStyle();
        short df_ = wb.createDataFormat().getFormat("yyyy-mm-dd");
        dateCellStyle_.setDataFormat(df_);
        for (ActivityVo activityVo : onePage.getContent()) {
            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(new HSSFRichTextString(activityVo.getName()));
            i += 1;
            cell.setCellStyle(cellStyle);

            cell = row.createCell(1);
            cell.setCellStyle(dateCellStyle);
            if (activityVo.getCostPrice() != null) {
                cell.setCellValue(new HSSFRichTextString((activityVo.getCostPrice() / 100) + "元"));
            } else {
                cell.setCellValue(new HSSFRichTextString(""));
            }

            cell = row.createCell(2);
            cell.setCellStyle(dateCellStyle);
            if (activityVo.getMoney() != null) {
                cell.setCellValue(new HSSFRichTextString((activityVo.getMoney() / 100) + "元"));
            } else {
                cell.setCellValue(new HSSFRichTextString(""));
            }

            cell = row.createCell(3);
            cell.setCellStyle(dateCellStyle);
            if (activityVo.getRegistrationNumber() != null) {
                cell.setCellValue(activityVo.getRegistrationNumber());
            } else {
                cell.setCellValue(new HSSFRichTextString(""));
            }

            cell = row.createCell(4);
            cell.setCellStyle(cellStyle);
            if (activityVo.getExpenditure() != null) {
                cell.setCellValue(new HSSFRichTextString((activityVo.getExpenditure() / 100) + "元"));
            } else {
                cell.setCellValue(new HSSFRichTextString(""));
            }

            cell = row.createCell(5);
            cell.setCellStyle(cellStyle);
            if (activityVo.getProfit() != null) {
                cell.setCellValue(new HSSFRichTextString((activityVo.getProfit() / 100) + "元"));
            } else {
                cell.setCellValue(new HSSFRichTextString(""));
            }

            cell = row.createCell(6);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(activityVo.getCreateUserName()));

            cell = row.createCell(7);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(DateUtils.formatDate(activityVo.getCreateTime(), "yyyy-MM-dd"));

            cell = row.createCell(8);
            cell.setCellStyle(dateCellStyle_);
            cell.setCellValue(new HSSFRichTextString(activityVo.getRemark()));
        }
        try {
            bufferedOutPut.flush();
            wb.write(bufferedOutPut);
            return null;
        } catch (IOException e) {
            logger.error("导出exce表格失败", e);
            throw new RuntimeException(e);
        } finally {
            bufferedOutPut.close();
        }
    }
    
    /**
     * 生成二维码图片(带logo)
     * @param content
     * @param barUri
     * @return
     */
    @RequestMapping(value = "generateQR/{content}/{barUri}", method = RequestMethod.POST)
    @ResponseBody
    public String generateQr(@PathVariable String content,@PathVariable String barUri) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
			String url = BarcodeFactory.encode(content,300, 300, barUri);
			result.put("result", true);
			result.put("qrUrl", url);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.put("result", false);
		}
        return MyGson.getInstance().toJson(result);
    }
    
    /**
     * 生成二维码图片(不带logo)
     * @param content
     * @param barUri
     * @return
     */
    @RequestMapping(value = "generateQR/{content}", method = RequestMethod.POST)
    @ResponseBody
    public String generateQrWithOut(@PathVariable String content) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
			String url = BarcodeFactory.encode(content,300, 300);
			result.put("result", true);
			result.put("qrUrl", url);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.put("result", false);
		}
        return MyGson.getInstance().toJson(result);
    }
}
