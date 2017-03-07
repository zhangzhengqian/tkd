package com.lc.zy.ball.boss.framework.orders.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.FlashAttributeUtil;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.orders.service.BillService;
import com.lc.zy.ball.boss.framework.orders.service.OrderService;
import com.lc.zy.ball.boss.framework.orders.service.PayLogService;
import com.lc.zy.ball.boss.framework.orders.vo.BillTermVo;
import com.lc.zy.ball.boss.framework.orders.vo.OrderBillItemVo;
import com.lc.zy.ball.boss.framework.orders.vo.PayLogVo;
import com.lc.zy.ball.boss.framework.orders.vo.StatiumOrderBill;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.OrderBillItemMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderBillMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumDetailMapper;
import com.lc.zy.ball.domain.oa.po.OrderBill;
import com.lc.zy.ball.domain.oa.po.OrderBillCriteria;
import com.lc.zy.ball.domain.oa.po.OrderBillItem;
import com.lc.zy.ball.domain.oa.po.StatiumDetail;
import com.lc.zy.ball.domain.oa.po.StatiumDetailCriteria;
import com.lc.zy.common.mq.bean.OrdeNotifyrMessage;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.ExcelUtil;
import com.lc.zy.common.util.ExportExcelUtil;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.OrderNotifyUtil;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.web.WebUtils;

/**
 * 账单管理
 * @author chenglong
 */
@Controller
@RequestMapping(value="/orders/bill")
public class BillController extends AbstractController{
	
	private static final Logger logger = LoggerFactory.getLogger(BillController.class);
	@Autowired
	private PayLogService payLogService;
	@Autowired
	private OrderBillMapper orderBillMapper;
	@Autowired
	private OrderBillItemMapper orderBillItemMapper;
	@Autowired
	private StatiumDetailMapper statiumDetailMapper;
	@Autowired
	private BillService billService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderNotifyUtil orderNotifyUtil;
	private Gson gson = new Gson();
	public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	 /**
     * 账单分页展示
     * @param model
     * @param request
     * @return
     */
	@RequestMapping(value="")
	public String OrdersList(Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
		logger.debug("BillController OrdersList method execute!");
		//根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		parseDate(searchParams, "GTE_createTime");
		parseDate(searchParams, "LTE_createTime");
		
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		CommonOAUtils.paramesTrim(searchParams);//参数去空
		
		Map<String,Object> res = payLogService.find(searchParams, page, size,true,true);
	
		model.addAttribute("data", res.get("data"));
		model.addAttribute("sum", res.get("sum"));
		// 将搜索条件编码成字符串，用于排序，分页的URL
		String as[] = {"GTE_createTime","LTE_createTime"};
		for(String s :as ){
			if(null != searchParams.get(s) && searchParams.get(s) instanceof Date){
				searchParams.put(s, sdf.format(searchParams.get(s)));
			}
		}
		
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		
		return "orders/billList";
	}
	
	/**
     * excel导出数据
     * @param model
     * @param request
     * @return
     */
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	@RequestMapping(value={"export"})
	public void exportData(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		logger.debug("BillController exportData method execute!");
		List<PayLogVo> payLogVoList = new ArrayList<PayLogVo>();
		String title = "账单";
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		String beginStr = "";
		String endStr = "";
		if(CommonUtils.isNotEmpty((String)searchParams.get("GTE_createTime"))){
			beginStr = (String)searchParams.get("GTE_createTime");
		}
		if(CommonUtils.isNotEmpty((String)searchParams.get("LTE_createTime"))){
			endStr = (String)searchParams.get("LTE_createTime");
		}
		if(CommonUtils.isNotEmpty(beginStr) && CommonUtils.isNotEmpty(endStr) ){
			if(beginStr.equals(endStr)){
				title = title+"("+beginStr+")";
			}else{
				title = title+"("+beginStr+"~"+endStr+")";
			}
		}
		
		parseDate(searchParams, "GTE_createTime");
		parseDate(searchParams, "LTE_createTime");
		
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		CommonOAUtils.paramesTrim(searchParams);//参数去空
		
		Map<String,Object> res = payLogService.find(searchParams, page, size,false,false);
		
		if(res != null && res.get("data")!= null){
			payLogVoList = ((Page<PayLogVo>)res.get("data")).getContent();
		}
		
		ExcelUtil excelUtil = new ExcelUtil();
		String fileName = excelUtil.createtFileName("导出账单数据");
		fileName = new String(fileName.getBytes("GBK"), "iso8859-1");  
		response.reset();
		//指定下载的文件名  
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		response.setContentType("application/vnd.ms-excel");  
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);  
		
		String[] headers = {"订单号::orderId","流水号::tradeNoStr","用户名称::userNickName",
							"应收金额（元）::feeStr","实收金额（元）::finalFeeStr","日期::createTime",
							"类型::statusStr","支付类型::payTypeStr"
				           };
		
		excelUtil.exportExcel(title, headers, payLogVoList, response.getOutputStream(), "yyyy-MM-dd HH:mm:ss");
	}
	
	@RequestMapping(value="billingint")
	public String billing(Model model,HttpServletRequest request){
		String areaCode = request.getParameter("areaCode");
		String statiumName = request.getParameter("statiumName");
		StatiumDetailCriteria criteria = new StatiumDetailCriteria();
		StatiumDetailCriteria.Criteria cri = criteria.createCriteria();
		if(StringUtils.isNotEmpty(areaCode)){
    		if(areaCode.endsWith("0000")){
    			cri.andAreaCodeLike(areaCode.substring(0,2)+"%");
    		}else if (areaCode.endsWith("00")){
    			cri.andAreaCodeLike(areaCode.substring(0,4)+"%");
    		}else{
    			cri.andAreaCodeEqualTo(areaCode);
    		}
    	}else{
    		//cri.andAreaCodeLike("11%");
    	}
		//cri.andStatusEqualTo(1);
		cri.andDeleteFlagIsNull();
		cri.andIsSignedEqualTo(1);
		if(StringUtils.isNotEmpty(statiumName)){
			cri.andNameEqualTo(statiumName);
		}else{
			
		}
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		int total = statiumDetailMapper.countByExample(criteria);
		criteria.setOrderByClause("bill_date desc");
		PageRequest pageable = new PageRequest(page, size);
		criteria.setMysqlLength(size);
		criteria.setMysqlOffset(page);
		List<StatiumDetail> details =  statiumDetailMapper.selectByExample(criteria);
		Page<StatiumDetail> pageVo = new PageImpl<>(details, pageable, total);
		model.addAttribute("data", pageVo);
		return "/orders/orderBill";
	}
	
	@RequestMapping(value="billList")
	public String billing(Model model,HttpServletRequest request,RedirectAttributes redirectAttributes){
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		try {
			//billService.initPrice();
			Page<BillTermVo> onePage = billService.find(page, size);
			model.addAttribute("data", onePage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/orders/orderBillList";
	}
	
	@RequestMapping(value="billBuild")
	public String billBuild(Model model,String startDate,String endDate,int ordersType,HttpServletRequest request,RedirectAttributes redirectAttributes){
		
		try {
			if(billService.checkBillTerm(startDate, endDate,ordersType)){
				long bg = System.currentTimeMillis();
				billService.billBuild(startDate, endDate,ordersType);
				long time = System.currentTimeMillis()-bg;
				logger.debug(time/1000+"----------------------------");
			}else{
				redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "账单日期不连续！"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return "redirect:/orders/bill/billList";
	}
	
	@RequestMapping(value="delBill")
	public String DelBillTerm(Model model,String id,HttpServletRequest request,RedirectAttributes redirectAttributes){
		
		try {
			billService.delBill(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/orders/bill/billList";
	}
	
	@RequestMapping(value="verifyBillSel")
	public String verifyBillSel(Model model,String id,String billId,HttpServletRequest request,RedirectAttributes redirectAttributes){
		
		try {
			String ids[] = id.split(",",-1);
			billService.verifyBillSel(ids);
			model.addAttribute("billId", billId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/orders/bill/statiumBill/"+billId;
	}
	
	@RequestMapping(value="verifyBillAll")
	public String verifyBillAll(Model model,String billId,HttpServletRequest request,RedirectAttributes redirectAttributes){
		
		try {
			billService.verifyBillAll(billId);
			model.addAttribute("billId", billId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/orders/bill/statiumBill/"+billId;
	}
	
	@RequestMapping(value="balanceBillAll")
	public String balanceBillAll(Model model,String billId,HttpServletRequest request,RedirectAttributes redirectAttributes){
		
		try {
			billService.balanceBillAll(billId);
			model.addAttribute("billId", billId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/orders/bill/statiumBill/"+billId;
	}
	
	@RequestMapping(value="balanceBillSel")
	public String balanceBillSel(Model model,String id,String billId,HttpServletRequest request,RedirectAttributes redirectAttributes){
		
		try {
			String ids[] = id.split(",",-1);
			billService.balanceBillSel(ids);
			model.addAttribute("billId", billId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/orders/bill/statiumBill/"+billId;
	}
	
	@RequestMapping(value="statiumBill/{id}")
	public String statiumBill(Model model,@PathVariable String id,HttpServletRequest request,RedirectAttributes redirectAttributes){
		
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		try {
			Page<StatiumOrderBill> onePage = billService.statiumBill(id,page, size,null,null,true);
			model.addAttribute("data", onePage);
			model.addAttribute("billId", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/orders/statiumBillList";
	}
	
	@RequestMapping(value="statiumBillSearch/{id}/{type}")
	public String statiumBillSearch(Model model,@PathVariable String id,@PathVariable String type,HttpServletRequest request,RedirectAttributes redirectAttributes){
		
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		try {
			Page<StatiumOrderBill> onePage = billService.statiumBill(id,page, size,type,null,true);
			model.addAttribute("data", onePage);
			model.addAttribute("billId", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/orders/statiumBillList";
	}
	
	@RequestMapping(value="statiumBillExport/{id}/{type}")
	public void statiumBillExport(Model model,@PathVariable String id,@PathVariable String type,HttpServletRequest request,RedirectAttributes redirectAttributes,HttpServletResponse response){
		
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		try {
			Page<StatiumOrderBill> onePage = billService.statiumBill(id,page, size,type,null,false);
			List<StatiumOrderBill> bills = onePage.getContent();
			if(CollectionUtils.isNotEmpty(bills)){
				writeExcel(response,bills);
	    	}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	
	@RequestMapping(value="statiumBillSearchByStatiumName/{id}/{name}")
	public String statiumBillSearchByStatiumName(Model model,@PathVariable String id,@PathVariable String name,HttpServletRequest request,RedirectAttributes redirectAttributes){
		
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		try {
			Page<StatiumOrderBill> onePage = billService.statiumBill(id,page, size,null,name,true);
			model.addAttribute("data", onePage);
			model.addAttribute("billId", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/orders/statiumBillList";
	}
	
	@RequestMapping(value="billHistory")
	@ResponseBody
	public String getHistoryBills(String statiumId,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		OrderBillCriteria billCriteria = new OrderBillCriteria();
		OrderBillCriteria.Criteria criteria = billCriteria.createCriteria();
		criteria.andStatiumIdEqualTo(statiumId);
		billCriteria.setOrderByClause("ct desc");
		try {
			List<OrderBill> bills = orderBillMapper.selectByExample(billCriteria);
			result.put(Constants.Result.RESULT, true);
			result.put(Constants.Result.DATA, bills);
			return MyGson.getInstance().toJson(result);
		} catch (Exception e) {
			logger.error("查询往期账单失败！",e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "查询往期账单失败！");
			return gson.toJson(result);
		}
	}
	@RequestMapping(value="bill/do")
	public String bill(String statiumId,String startDate,String endDate,String resultView,HttpServletRequest request,RedirectAttributes redirectAttributes){
		OrderBillCriteria billCriteria = new OrderBillCriteria();
		OrderBillCriteria.Criteria criteria = billCriteria.createCriteria();
		criteria.andStatiumIdEqualTo(statiumId);
		billCriteria.setOrderByClause("end_date desc");
		List<OrderBill> bills = orderBillMapper.selectByExample(billCriteria);
		if(CollectionUtils.isNotEmpty(bills)){
			int totalEnd = bills.get(0).getEndDate();
			try {
				Date start = org.apache.commons.lang3.time.DateUtils.parseDate(startDate.substring(0,10), "yyyy-MM-dd");
				int startNew = new BigDecimal(start.getTime()).divide(new BigDecimal(100000)).intValue();
				if(startNew<=totalEnd){
					redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "结账日期不连续"));
					return "redirect:/orders/billingint";
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		Type type = new TypeToken<List<OrderBillItemVo>>() {
		}.getType();
		List<OrderBillItemVo> vos = MyGson.getInstance().fromJson(resultView, type);
		String billId = UUID.get();
		OrderBillItem item = null;
		for(OrderBillItemVo vo :vos){
			item = new OrderBillItem();
			item.setFinalFee(vo.getTotalFinalFee());
			item.setOrderBillId(billId);
			item.setStartDate(vo.getPerDate());
			item.setSubAmount(vo.getCostFee());
			item.setId(UUID.get());
			item.setOrderCount(vo.getOrderCount());
			item.setSubsidyAmount(vo.getSubsidy());
			item.setTotalFee(vo.getTotalFee());
			orderBillItemMapper.insertSelective(item);
		}
		OrderBillItemVo vo = vos.get(vos.size()-1);
		OrderBill bill = new OrderBill();
		bill.setCb(SessionUtil.currentUserId());
		bill.setId(billId);
		bill.setCt(new Date());
		bill.setFee(vo.getCostFee());
		bill.setSubsidy(vo.getSubsidy());
		try {
			Date start = org.apache.commons.lang3.time.DateUtils.parseDate(startDate.substring(0,10), "yyyy-MM-dd");
			bill.setBeginDate(new BigDecimal(start.getTime()).divide(new BigDecimal(100000)).intValue());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			Date end = org.apache.commons.lang3.time.DateUtils.parseDate(endDate.substring(0,10), "yyyy-MM-dd");
			bill.setEndDate(new BigDecimal(end.getTime()).divide(new BigDecimal(100000)).intValue());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		bill.setStatiumId(statiumId);
		try {
			orderService.insertSelective(bill, bill.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		StatiumDetail statium = new StatiumDetail();
		statium.setId(statiumId);
		statium.setBillDate(endDate.substring(0,10));
		try {
			orderService.updateByPrimaryKeySelective(statium, statiumId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		OrdeNotifyrMessage notifyMessage = new OrdeNotifyrMessage();
		notifyMessage.setType("bill");
		notifyMessage.setStatiumId(statiumId);
		notifyMessage.setBillTitle(startDate.substring(0,10)+"到"+endDate.substring(0,10)+"的账单");
		orderNotifyUtil.notifyOrder(MyGson.getInstance().toJson(notifyMessage));
		return "redirect:/orders/billingint";
	} 
	
	/**
     * 定义导出Excel的样式
     * @param response
     * @param bills
     * @throws Exception
     */
	public Map<String,Object> writeExcel(HttpServletResponse response, List<StatiumOrderBill> bills) throws Exception{
		Map<String, Object> map = new HashMap<String,Object>();
		String uuid = UUID.get();
		String fileName = uuid+".xls";  
        fileName = new String(fileName.getBytes("GBK"), "iso8859-1");  
        response.reset();  
        response.setHeader("Content-Disposition", "attachment;filename="  
                + fileName);// 指定下载的文件名  
        response.setContentType("application/vnd.ms-excel");  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        OutputStream output = response.getOutputStream();  
        BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);  
  
        // 定义单元格报头  
        String worksheetTitle = "结账(" + DateUtil.formatDate(new Date(), "yyyy-MM-dd") + ")";  
  
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
  
        // 工作表名  
        String sequence = "序号"; 
        String statiumName = "场馆名称";
        String areaStr= "地区";
        String address = "地址";
        String phone = "电话";  
        String orderNum = "订单总数";  
        String costPrice = "成本金额";
        String subsidies = "补贴金额";
        String status = "状态";
        String fee = "总额";
        String sportType = "品类";
        String userName = "户主";
        String bankName = "银行";
        String bankNo = "账号";
        HSSFSheet sheet = wb.createSheet();  
        ExportExcelUtil exportExcel = new ExportExcelUtil(wb, sheet);  
        // 创建报表头部  
        exportExcel.createNormalHead(worksheetTitle, 8);  
        // 定义第一行  
        HSSFRow row1 = sheet.createRow(1);  
        HSSFCell cell1 = row1.createCell(0);  
  
        //第一行第1列  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(sequence));  
        
        //第一行第2列  
        cell1 = row1.createCell(1);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(statiumName));
        
        //第一行第2列  
        cell1 = row1.createCell(2);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(areaStr));  
  
        //第一行第3列  
        cell1 = row1.createCell(3);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(address));  
        
        //第一行第4列  
        cell1 = row1.createCell(4);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(phone)); 
        
        //第一行第5列  
        cell1 = row1.createCell(5);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(orderNum));
        
        //第一行第6列  
        cell1 = row1.createCell(6);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(costPrice));  
  
        //第一行第7列  
        cell1 = row1.createCell(7);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(subsidies));  
  
        //第一行第9列  
        cell1 = row1.createCell(8);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(fee));  

        //第一行第8列  
        cell1 = row1.createCell(9);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(status));  
        
      //第一行第8列  
        cell1 = row1.createCell(10);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(sportType));  
        
      //第一行第8列  
        cell1 = row1.createCell(11);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(userName));
        
      //第一行第8列  
        cell1 = row1.createCell(12);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(bankName));  

      //第一行第8列  
        cell1 = row1.createCell(13);  
        cell1.setCellStyle(cellStyleTitle);  
        cell1.setCellValue(new HSSFRichTextString(bankNo));  
        
        
        
          
        //定义第二行  
        HSSFRow row = sheet.createRow(2);  
        HSSFCell cell = row.createCell(1);
        int i = 1;
        for(StatiumOrderBill bill:bills){
            row = sheet.createRow(i+1);
            cell = row.createCell(0);  
            cell.setCellValue(new HSSFRichTextString(i+""));  
            i += 1;
            cell.setCellStyle(cellStyle);
            
            cell = row.createCell(1);  
            cell.setCellStyle(cellStyle);
        	cell.setCellValue(new HSSFRichTextString(bill.getStatiumName()));
        	
        	cell = row.createCell(2);  
            cell.setCellStyle(cellStyle);
        	cell.setCellValue(new HSSFRichTextString(bill.getArea()));
              
            cell = row.createCell(3);  
            cell.setCellStyle(cellStyle);  
        	cell.setCellValue(new HSSFRichTextString(bill.getAddress())); 
        	
            cell = row.createCell(4);  
            cell.setCellStyle(cellStyle);  
            cell.setCellValue(new HSSFRichTextString(bill.getPhone()));  
            
            cell = row.createCell(5);  
            cell.setCellStyle(cellStyle);  
            cell.setCellValue(bill.getOrderNum()); 
            
            cell = row.createCell(6);  
            cell.setCellStyle(cellStyle);
            cell.setCellValue(bill.getFee()); 
            
            cell = row.createCell(7);  
            cell.setCellStyle(cellStyle);
            cell.setCellValue(bill.getSubsidy());  
            
            cell = row.createCell(8);  
            cell.setCellStyle(cellStyle);
            int subsidy = bill.getSubsidy();
            int fees = bill.getFee();
            cell.setCellValue((new BigDecimal(fees).add(new BigDecimal(subsidy))).intValue());
            
            cell = row.createCell(9);  
            cell.setCellStyle(cellStyle);
            int statusInt = bill.getStatus();
            String status_ = "";
            if(statusInt==0){
            	status_="新账单";
            }else if(statusInt==1){
            	status_="待结算";
            }else if(statusInt==2){
            	status_="已结算";
            }
            cell.setCellValue(new HSSFRichTextString(status_));
            
            cell = row.createCell(10);  
            cell.setCellStyle(cellStyle);  
            cell.setCellValue(new HSSFRichTextString(bill.getSportType()));  
            
            cell = row.createCell(11);  
            cell.setCellStyle(cellStyle);  
            cell.setCellValue(new HSSFRichTextString(bill.getUserName()));  
            
            cell = row.createCell(12);  
            cell.setCellStyle(cellStyle);  
            cell.setCellValue(new HSSFRichTextString(bill.getBankAccountName()));  
            
            cell = row.createCell(13);  
            cell.setCellStyle(cellStyle);  
            cell.setCellValue(new HSSFRichTextString(bill.getBankaccountno()+""));  
      
        }
        try {  
            bufferedOutPut.flush();  
            wb.write(bufferedOutPut);
            return null;
        }
        catch (IOException e) { 
        	e.printStackTrace();
            logger.error(e.getMessage());
            map.put(Constants.Result.RESULT, "false");
            map.put(Constants.Result.REASON, "导出Excel失败,请再试一次");
            return map;
        } finally {
        	bufferedOutPut.close();
        }
	}
}
