package com.lc.zy.ball.boss.framework.vip.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.enums.Roles;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.vip.service.VipCardService;
import com.lc.zy.ball.boss.framework.vip.vo.VipCardLogVo;
import com.lc.zy.ball.boss.framework.vip.vo.VipCardVo;
import com.lc.zy.ball.boss.framework.vip.vo.VipLogVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.ExportExcelUtil;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.web.WebUtils;

@Controller
@RequestMapping(value = "vip")
public class VipCardController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(VipCardController.class);

    @Autowired
    private VipCardService vipCardService;

    /**
     * 
     * <球馆会员卡列表><功能具体实现>
     *
     * @create：2015年12月8日 上午11:00:26
     * @author： CYY
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = { "", "/" })
    public String index(HttpServletRequest request, Model model) {
        try {
            Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
            // 设置默认的组织，如果是 【 客服、客服经理、运营经理、root】，则忽略默认组织，可以看所有组织的数据
            User currentUser = SessionUtil.currentUser();
            if (!SecurityUtils.getSubject().hasRole(Roles.CUSTOMER.getCode())
                    && !SecurityUtils.getSubject().hasRole(Roles.CUSTOMER_MANAGER.getCode())
                    && !SecurityUtils.getSubject().hasRole(Roles.SUPPORT_MANAGER.getCode())
                    && !SecurityUtils.getSubject().hasRole(Roles.ADMIN.getCode())
                    && !"root".equals(currentUser.getLoginName())) {
                // 设置默认组织条件
                setDefaultOrgCode(searchParams);
            }
            int page = WebUtils.getPage(request);
            int size = WebUtils.getPageSize(request);
            logger.debug("user_list_search_params={}", searchParams);
            Page<VipCardVo> onePage = vipCardService.find(new PageRequest(page, size), searchParams, true);
            model.addAttribute("data", onePage);
            // 将搜索条件编码成字符串，用于排序，分页的URL
            model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "vip/vipCardList";
    }

    /**
     * 
     * <导出会员卡列表exce><功能具体实现>
     *
     * @create：2015年12月7日 下午6:13:48
     * @author： CYY
     * @param response
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "exportVipCardList")
    public void exportVipCardList(HttpServletResponse response, HttpServletRequest request) throws Exception {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        try {
            // 根据查询条件查询数据
            int page = WebUtils.getPage(request);
            int size = WebUtils.getPageSize(request);
            Page<VipCardVo> onePage = vipCardService.find(new PageRequest(page, size), searchParams, false);
            if (onePage != null && onePage.getSize() > 0) {
                writeVipCardExcel(response, onePage);
            }
        } catch (Exception e) {
            logger.error("导出会员卡列表exce出错", e);
        }
    }

    /**
     * 
     * <添加会员卡页面><功能具体实现>
     *
     * @create：2015年12月8日 下午2:40:21
     * @author： CYY
     * @param model
     * @return
     */
    @RequestMapping(value = "createForm")
    public String createForm(Model model) {
        return "vip/vipCardForm";
    }

    /**
     * 
     * <保存会员卡信息><功能具体实现>
     *
     * @create：2015年12月8日 下午2:40:21
     * @author： CYY
     * @param model
     * @param vipCardVo
     * @return
     */
    @RequestMapping(value = "save")
    public String save(Model model, VipCardVo vipCardVo) {
        try {
            vipCardService.save(vipCardVo);
        } catch (Exception e) {
            logger.error("保存会员卡信息出错", e);
        }
        return "redirect:";
    }

    /**
     * 
     * <会员卡详情信息><功能具体实现>
     *
     * @create：2015年12月8日 下午2:42:00
     * @author： CYY
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "view/{id}")
    public String view(HttpServletRequest request, Model model, @PathVariable("id") String id) {
        try {
            Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
            VipCardVo vipCardVo = vipCardService.detail(id);
            model.addAttribute("vipCard", vipCardVo);
            int page = WebUtils.getPage(request);
            int size = WebUtils.getPageSize(request);
            searchParams.put("EQ_statiumId", vipCardVo.getStatiumId());
            logger.debug("user_list_search_params={}", searchParams);
            Page<VipCardLogVo> onePage = vipCardService.findVipCardLogList(new PageRequest(page, size), searchParams,
                    vipCardVo, true);
            model.addAttribute("data", onePage);
            // 将搜索条件编码成字符串，用于排序，分页的URL
            model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        } catch (Exception e) {
            logger.error("获取会员卡详情信息出错", e);
        }
        return "vip/view";
    }

    /**
     * 
     * <到导出会员卡详情信息><功能具体实现>
     *
     * @create：2015年12月8日 下午2:42:00
     * @author： CYY
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "exportVipCardLog/{id}")
    public void exportVipCardLog(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
        try {
            Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
            VipCardVo vipCardVo = vipCardService.detail(id);
            int page = WebUtils.getPage(request);
            int size = WebUtils.getPageSize(request);
            searchParams.put("EQ_statiumId", vipCardVo.getStatiumId());
            logger.debug("user_list_search_params={}", searchParams);
            Page<VipCardLogVo> onePage = vipCardService.findVipCardLogList(new PageRequest(page, size), searchParams,
                    vipCardVo, false);
            if (onePage != null && onePage.getSize() > 0) {
                writeVipCardLogExcel(response, onePage);
            }
        } catch (Exception e) {
            logger.error("获取会员卡详情信息出错", e);
        }
    }

    /**
     * 
     * <充值记录><功能具体实现>
     *
     * @create：2015年12月8日 下午2:42:00
     * @author： CYY
     * @param model
     * @param request
     * @param vipLogVo
     * @return
     */
    @RequestMapping(value = "saveRecharge")
    public String saveRecharge(HttpServletRequest request, Model model, VipLogVo vipLogVo) {
        try {
            vipCardService.saveRecharge(vipLogVo);
        } catch (Exception e) {
            logger.error("充值记录出错", e);
        }
        return "redirect:view/" + vipLogVo.getVipCardId();
    }

    /**
     * 
     * <充值记录列表><功能具体实现>
     *
     * @create：2015年12月8日 下午2:42:00
     * @author： CYY
     * @param request
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "findViplogList/{id}")
    public String findViplogList(HttpServletRequest request, Model model, @PathVariable("id") String id) {
        try {
            Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
            int page = WebUtils.getPage(request);
            int size = WebUtils.getPageSize(request);
            searchParams.put("EQ_vipCardId", id);
            logger.debug("user_list_search_params={}", searchParams);
            Page<VipLogVo> onePage = vipCardService.findViplogList(new PageRequest(page, size), searchParams, true);
            model.addAttribute("data", onePage);
            model.addAttribute("vipCardId", id);
            // 将搜索条件编码成字符串，用于排序，分页的URL
            model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        } catch (Exception e) {
            logger.error("充值记录列表出错", e);
        }
        return "vip/vipLogList";
    }

    /**
     * 
     * <导出充值记录列表><功能具体实现>
     *
     * @create：2015年12月8日 下午2:42:00
     * @author： CYY
     * @param request
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "exportVipCardRechargeLog/{id}")
    public void exportVipCardRechargeLog(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("id") String id) {
        try {
            Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
            int page = WebUtils.getPage(request);
            int size = WebUtils.getPageSize(request);
            searchParams.put("EQ_vipCardId", id);
            logger.debug("user_list_search_params={}", searchParams);
            Page<VipLogVo> onePage = vipCardService.findViplogList(new PageRequest(page, size), searchParams, false);
            if (onePage != null && onePage.getSize() > 0) {
                writeVipCardRechargeLogExcel(response, onePage);
            }
        } catch (Exception e) {
            logger.error("导出充值记录列表出错", e);
        }
    }

    /**
     * 导出球馆会员卡Excel
     * 
     * @param response
     * @param onePage
     * @throws Exception
     */
    public Map<String, Object> writeVipCardExcel(HttpServletResponse response, Page<VipCardVo> onePage)
            throws Exception {
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
        String worksheetTitle = "球馆会员卡统计(" + DateUtil.formatDate(new Date(), "yyyy-MM-dd") + ")";

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
        cell1.setCellValue(new HSSFRichTextString("场馆名称"));

        // 第一行第2列
        cell1 = row1.createCell(1);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("会员卡号"));

        // 第一行第3列
        cell1 = row1.createCell(2);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("卡号"));

        // 第一行第4列
        cell1 = row1.createCell(3);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("面额（元）"));

        // 第一行第5列
        cell1 = row1.createCell(4);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("余额（元）"));

        // 第一行第6列
        cell1 = row1.createCell(5);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("支出"));

        // 第一行第7列
        cell1 = row1.createCell(6);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("收入"));

        // 第一行第8列
        cell1 = row1.createCell(7);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("盈利"));

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
        for (VipCardVo vipCardVo : onePage.getContent()) {
            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(new HSSFRichTextString(vipCardVo.getStatiumName()));
            i += 1;
            cell.setCellStyle(cellStyle);

            cell = row.createCell(1);
            cell.setCellStyle(dateCellStyle);
            cell.setCellValue(new HSSFRichTextString(vipCardVo.getCardNumber()));

            cell = row.createCell(2);
            cell.setCellStyle(dateCellStyle);
            cell.setCellValue(new HSSFRichTextString(vipCardVo.getName()));

            cell = row.createCell(3);
            cell.setCellStyle(dateCellStyle);
            if (vipCardVo.getAmount() != null) {
                cell.setCellValue(new HSSFRichTextString((vipCardVo.getAmount() / 100) + "元"));
            } else {
                cell.setCellValue(new HSSFRichTextString(""));
            }

            cell = row.createCell(4);
            cell.setCellStyle(cellStyle);
            if (vipCardVo.getBalance() != null) {
                cell.setCellValue(new HSSFRichTextString((vipCardVo.getBalance() / 100) + "元"));
            } else {
                cell.setCellValue(new HSSFRichTextString(""));
            }

            cell = row.createCell(5);
            cell.setCellStyle(cellStyle);
            if (vipCardVo.getExpenditure() != null) {
                cell.setCellValue(new HSSFRichTextString((vipCardVo.getExpenditure() / 100) + "元"));
            } else {
                cell.setCellValue(new HSSFRichTextString(""));
            }

            cell = row.createCell(6);
            cell.setCellStyle(cellStyle);
            if (vipCardVo.getRevenue() != null) {
                cell.setCellValue(new HSSFRichTextString((vipCardVo.getRevenue() / 100) + "元"));
            } else {
                cell.setCellValue(new HSSFRichTextString(""));
            }

            cell = row.createCell(7);
            cell.setCellStyle(cellStyle);
            if (vipCardVo.getProfit() != null) {
                cell.setCellValue(new HSSFRichTextString((vipCardVo.getProfit() / 100) + "元"));
            } else {
                cell.setCellValue(new HSSFRichTextString(""));
            }
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
     * 导出球馆会员卡消费记录Excel
     * 
     * @param response
     * @param onePage
     * @throws Exception
     */
    public Map<String, Object> writeVipCardLogExcel(HttpServletResponse response, Page<VipCardLogVo> onePage)
            throws Exception {
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
        String worksheetTitle = "订单流水统计(" + DateUtil.formatDate(new Date(), "yyyy-MM-dd") + ")";

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
        cell1.setCellValue(new HSSFRichTextString("会员卡号"));

        // 第一行第2列
        cell1 = row1.createCell(1);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("会员卡名称"));

        // 第一行第3列
        cell1 = row1.createCell(2);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("场馆名称"));

        // 第一行第4列
        cell1 = row1.createCell(3);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("会员卡余额"));

        // 第一行第5列
        cell1 = row1.createCell(4);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("支出（元）"));

        // 第一行第6列
        cell1 = row1.createCell(5);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("收入（元）"));

        // 第一行第7列
        cell1 = row1.createCell(6);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("事件"));

        // 第一行第8列
        cell1 = row1.createCell(7);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("盈利（元）"));

        // 第一行第9列
        cell1 = row1.createCell(8);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("订场时间"));

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
        for (VipCardLogVo log : onePage.getContent()) {
            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(new HSSFRichTextString(log.getCardNumber()));
            i += 1;
            cell.setCellStyle(cellStyle);

            cell = row.createCell(1);
            cell.setCellStyle(dateCellStyle);
            cell.setCellValue(new HSSFRichTextString(log.getName()));

            cell = row.createCell(2);
            cell.setCellStyle(dateCellStyle);
            cell.setCellValue(new HSSFRichTextString(log.getStatiumName()));

            cell = row.createCell(3);
            cell.setCellStyle(dateCellStyle);
            if (log.getBalance() != null) {
                cell.setCellValue(new HSSFRichTextString((log.getBalance() / 100) + "元"));
            } else {
                cell.setCellValue(new HSSFRichTextString(""));
            }

            cell = row.createCell(4);
            cell.setCellStyle(cellStyle);
            if (log.getExpenditure() != null) {
                cell.setCellValue(new HSSFRichTextString((log.getExpenditure() / 100) + "元"));
            } else {
                cell.setCellValue(new HSSFRichTextString(""));
            }

            cell = row.createCell(5);
            cell.setCellStyle(cellStyle);
            if (log.getRevenue() != null) {
                cell.setCellValue(new HSSFRichTextString((log.getRevenue() / 100) + "元"));
            } else {
                cell.setCellValue(new HSSFRichTextString(""));
            }

            cell = row.createCell(6);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(log.getEvent()));

            cell = row.createCell(7);
            cell.setCellStyle(cellStyle);
            if (log.getProfit() != null) {
                cell.setCellValue(new HSSFRichTextString((log.getProfit() / 100) + "元"));
            } else {
                cell.setCellValue(new HSSFRichTextString(""));
            }

            cell = row.createCell(8);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(DateUtils.formatDate(log.getCt())));
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
     * 导出球馆会员卡消费记录Excel
     * 
     * @param response
     * @param onePage
     * @throws Exception
     */
    public Map<String, Object> writeVipCardRechargeLogExcel(HttpServletResponse response, Page<VipLogVo> onePage)
            throws Exception {
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
        String worksheetTitle = "充值记录(" + DateUtil.formatDate(new Date(), "yyyy-MM-dd") + ")";

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
        cell1.setCellValue(new HSSFRichTextString("会员卡号"));

        // 第一行第2列
        cell1 = row1.createCell(1);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("会员卡名称"));

        // 第一行第3列
        cell1 = row1.createCell(2);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("场馆名称"));

        // 第一行第4列
        cell1 = row1.createCell(3);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("会员卡余额"));

        // 第一行第5列
        cell1 = row1.createCell(4);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("充值金额"));

        // 第一行第6列
        cell1 = row1.createCell(5);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("充值时间"));

        // 第一行第7列
        cell1 = row1.createCell(6);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString("充值人员"));

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
        for (VipLogVo log : onePage.getContent()) {
            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(new HSSFRichTextString(log.getCardNumber()));
            i += 1;
            cell.setCellStyle(cellStyle);

            cell = row.createCell(1);
            cell.setCellStyle(dateCellStyle);
            cell.setCellValue(new HSSFRichTextString(log.getCardName()));

            cell = row.createCell(2);
            cell.setCellStyle(dateCellStyle);
            cell.setCellValue(new HSSFRichTextString(log.getStatiumName()));

            cell = row.createCell(3);
            cell.setCellStyle(dateCellStyle);
            if (log.getBalance() != null) {
                cell.setCellValue(new HSSFRichTextString((log.getBalance() / 100) + "元"));
            } else {
                cell.setCellValue(new HSSFRichTextString(""));
            }

            cell = row.createCell(4);
            cell.setCellStyle(cellStyle);
            if (log.getRechargeAmount() != null) {
                cell.setCellValue(new HSSFRichTextString((log.getRechargeAmount() / 100) + "元"));
            } else {
                cell.setCellValue(new HSSFRichTextString(""));
            }

            cell = row.createCell(5);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(DateUtils.formatDate(log.getCt())));

            cell = row.createCell(6);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(log.getRechargePerson()));
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
}