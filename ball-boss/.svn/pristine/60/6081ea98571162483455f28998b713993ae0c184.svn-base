package com.lc.zy.ball.boss.framework.vote.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.google.gson.reflect.TypeToken;
import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.FlashAttributeUtil;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.framework.vote.service.VoteService;
import com.lc.zy.ball.boss.framework.vote.vo.VoteCandidateVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.VoteCandidate;
import com.lc.zy.ball.domain.oa.po.VoteParticipants;
import com.lc.zy.ball.domain.oa.po.VoteTheme;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.PostRequest;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.util.wxap.util.MD5Util;
import com.lc.zy.common.web.WebUtils;
import com.lc.zy.common.zoo.SEQGenerate;

/**
 * User: liangsh
 * Date: 2016/7/8
 * Time: 16:12
 */
@Controller
@RequestMapping(value = "/vote")
public class VoteController {

    private static final Logger logger = LoggerFactory.getLogger(VoteController.class);

    @Autowired
    private VoteService voteService;

    @Resource(name="configs")
    private Map<String,Object> configs = null;
    
    @Resource(name = "oaJdbcTemplate")
	private JdbcTemplate jdbcTemplate; 
    
    @Autowired
    private SEQGenerate seqGenerate;

    /**
     *<投票主题列表></>
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "list")
    public String list(Model model, HttpServletRequest request) throws Exception {
        String weixinDomain = (String)configs.get("weixinDomain");
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        int page = WebUtils.getPage(request);
        int size = WebUtils.getPageSize(request);
        Page<VoteTheme> onePage = voteService.list(new PageRequest(page, size), searchParams);
        model.addAttribute("data", onePage);
        // 将搜索条件编码成字符串，用于排序，分页的URL
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "vote/themeList";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) throws Exception {
        model.addAttribute("active", "create");
        return "vote/form";
    }
    
    /**
     *<投票主题列表></>
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "downLoadNewUser")
    public String downLoadNewUser(Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
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
    	List<String> dates = new ArrayList<String>();
    	dates.add("2016-08-31");
    	dates.add("2016-09-01");
    	dates.add("2016-09-02");
    	dates.add("2016-09-03");
    	int index = 0;
    	for(String date : dates){
    		if(index==0){
    			HSSFRow row = sheet.createRow(0);  
    			logger.debug("row is null?,{}",row==null);
    	        HSSFCell cell = row.createCell(index);
    	        cell.setCellValue(new HSSFRichTextString(date));
    		}else{
    			HSSFRow row = sheet.getRow(0);
    			logger.debug("row is null?,{}",row==null);
    			HSSFCell cell = row.createCell(index);
    			cell.setCellValue(new HSSFRichTextString(date));
    		}
    		String sql = "select s.phone phone from vote_participants p left join sso_user s on p.user_id=s.id where p.theme_id in ('d1b1cc051c77451bba47c185f22b7cf1','ae7e4f74a070444fa295e8a3e877ba07','35da8c9bd7544b4fa6b4c1e4c6a7dfa9') and s.create_time like '"+date+"%'";
    		List<Map<String,Object>> phones = jdbcTemplate.queryForList(sql);
    		int j=1;
    		for(Map<String,Object> result:phones){
    			String phone = (String)result.get("phone");
    			if(index==0){
    				HSSFRow row = sheet.createRow(j);
    				logger.debug("row is null?,{}",row==null);
    				HSSFCell cell = row.createCell(index);
    				cell.setCellValue(new HSSFRichTextString(phone));
    			}else{
    				HSSFRow row = sheet.getRow(j);
    				logger.debug("row is null?,{}",row==null);
    				HSSFCell cell = row.createCell(index);
    				cell.setCellValue(new HSSFRichTextString(phone));
    			}
    			j++;
    		}
    		index++;
    	}
    	try {  
            bufferedOutPut.flush();  
            wb.write(bufferedOutPut);
            return null;
        }
        catch (IOException e) { 
        	e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        } finally {
        	bufferedOutPut.close();
        }
    }
    
    
    /**
     *<投票主题列表></>
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "remainPrize")
    public String remainPrize(Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String fileName = "奖品数量.xls";  
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
    	List<String> dates = new ArrayList<String>();
    	dates.add("2016-08-31");
    	dates.add("2016-09-01");
    	dates.add("2016-09-02");
    	dates.add("2016-09-03");
    	String ip = request.getHeader("X-Real-IP");
    	logger.debug("url={}","http://"+configs.get("data.server")+":83/game4/api/sel_prize_cnt/");
		String rn = PostRequest.postTextWithIp(configs.get("data.server")+":83/game4/api/sel_prize_cnt/","REMOTE_ADDR_APP","192.168.0.4", "gameId", "game4","status","0");
		Type type = new TypeToken<List<Map<String,String>>>() {}.getType();
		List<Map<String,String>> prize_cnts = MyGson.getInstance().fromJson(rn, type);
		HSSFRow row = sheet.createRow(0);
		int index = 0;
		for(Map<String,String> prize_cnt:prize_cnts){
			String prz = prize_cnt.get("priz");
			String cnt = prize_cnt.get("cnt");
			HSSFCell cell = row.createCell(index++);
	        cell.setCellValue(new HSSFRichTextString(prz));
	        
	        cell = row.createCell(index++);
	        cell.setCellValue(new HSSFRichTextString(cnt));
		}
		row = sheet.createRow(1);
		index = 0;
		rn = PostRequest.postTextWithIp(configs.get("data.server")+":83/game4/api/sel_prize_cnt/","REMOTE_ADDR_APP","192.168.0.4", "gameId", "game4","status","1");
		prize_cnts = MyGson.getInstance().fromJson(rn, type);
		logger.debug("rn={}", rn);
		for(Map<String,String> prize_cnt:prize_cnts){
			String prz = prize_cnt.get("priz");
			String cnt = prize_cnt.get("cnt");
			HSSFCell cell = row.createCell(index++);
	        cell.setCellValue(new HSSFRichTextString(prz));
	        
	        cell = row.createCell(index++);
	        cell.setCellValue(new HSSFRichTextString(cnt));
		}
    	try {  
            bufferedOutPut.flush();  
            wb.write(bufferedOutPut);
            return null;
        }
        catch (IOException e) { 
        	e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        } finally {
        	bufferedOutPut.close();
        }
    }

    /**
     * <保存投票主题></>
     * @param info
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "save")
    public String save(VoteTheme info, RedirectAttributes redirectAttributes) {
        try {
            if(info != null){
                if(org.apache.commons.lang.StringUtils.isEmpty(info.getId())){
                    info.setId(UUID.get());
                    info.setCt(new Date());
                    info.setCb(SessionUtil.currentUserId());
                    voteService.insertSelective(info,info.getId());
                }else {
                    voteService.updateByPrimaryKeySelective(info,info.getId());
                }
                redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "保存成功!"));
            }
        } catch (Exception e) {
            logger.debug("保存失败: {}", e.getMessage());
            logger.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "保存失败！"));
        }
        return "redirect:/vote/list";
    }

    @RequestMapping(value = "detail/{id}")
    public String detail(@PathVariable String id, Model model) {
        try {
            VoteTheme theme = voteService.selectByPrimaryKey(VoteTheme.class,id);
            model.addAttribute("info",theme);
        } catch (Exception e) {
            logger.error("查看失败！", e);
        }
        return "vote/form";
    }

    /**
     *<投票候选人列表></>
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "memberList/{id}")
    public String memberlist(@PathVariable String id, Model model, HttpServletRequest request) throws Exception {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        int page = WebUtils.getPage(request);
        int size = 50;
        if (WebUtils.getPageSize(request)!=0) {
			size = WebUtils.getPageSize(request);
		}
        Map<String,Object> map = voteService.memberlist(new PageRequest(page, size), searchParams,id);
        Page<VoteCandidateVo> onePage =(Page<VoteCandidateVo>)map.get("data");
        model.addAttribute("data", onePage);
        model.addAttribute("themeId",id);
        model.addAttribute("totalParticipants",Integer.valueOf(map.get("totalParticipants").toString()));
        // 将搜索条件编码成字符串，用于排序，分页的URL
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "vote/memberList";
    }
    /**
     * 投票人列表
     * @param themeId
     * @param candidateId
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="participantList/{themeId}/{candidateId}")
    public String participantList(@PathVariable String themeId,@PathVariable String candidateId,Model model,HttpServletRequest request)throws Exception{
    	System.out.println(themeId);
    	System.out.println(candidateId);
    	Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        int page = WebUtils.getPage(request);
        int size = 50;
        Map<String,Object> map = voteService.participantList(new PageRequest(page, size), searchParams,themeId,candidateId);
        Page<VoteCandidateVo> onePage =(Page<VoteCandidateVo>)map.get("data");
        model.addAttribute("data", onePage);
        model.addAttribute("totalParticipants",Integer.valueOf(map.get("totalParticipants").toString()));
        model.addAttribute("themeId",themeId);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        System.out.println(onePage);
        return "vote/participantList";
    }
    
    /**
     * 更改候选人状态
     * @param themeId
     * @param ids
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="updateUserState/{themeId}/{ids}")
    public String updateUserState(@PathVariable String themeId,@PathVariable String ids)throws Exception{
    	if (StringUtils.isNotEmpty(ids)) {
    		voteService.insertUserState(themeId,ids);
		}
    	return "redirect:/vote/memberList/"+themeId;
    }
    
    /**
     *<投票候选人列表></>
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addVotes/{themeId}/{voteId}/{voteNum}")
    public String addVotes(@PathVariable String themeId,@PathVariable String voteId,@PathVariable int voteNum, Model model, HttpServletRequest request) throws Exception {
    	String username = SessionUtil.currentUsername();
    	if(!"buyanping".equals(username)&&!"liuhankai".equals(username)&&!"huangwenbo".equals(username)){
    		return "redirect:/vote/memberList/"+themeId;
    	}
    	logger.debug("operation people={}",username);
    	for(int i=0;i<voteNum;i++){
    		String phone = seqGenerate.genTradeNo();
        	String id = MD5Util.MD5Encode(themeId+phone, "utf-8");
        	VoteParticipants participants = new VoteParticipants();
        	participants.setCandidateId(voteId);
        	participants.setThemeId(themeId);
        	participants.setId(id);
        	participants.setCt(new Date());
        	participants.setUserName("自动投票");
        	participants.setUserId(phone);
        	participants.setUserPhoto("");
        	voteService.insertVote(participants);
    	}
        return "redirect:/vote/memberList/"+themeId;
    }
    
    /**
     *<投票候选人列表></>
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addVotes/{themeId}")
    public String addVotes(@PathVariable String themeId,Integer votesNumber,String ids,Model model, HttpServletRequest request) throws Exception {
    	String username = SessionUtil.currentUsername();
    	if(!"buyanping".equals(username)){
    		return "redirect:/vote/memberList/"+themeId;
    	}
    	String idArray[] = ids.split(",");
    	for(String id_:idArray){
    		for(int i=0;i<votesNumber;i++){
        		String phone = seqGenerate.genTradeNo();
            	String id = MD5Util.MD5Encode(themeId+phone, "utf-8");
            	VoteParticipants participants = new VoteParticipants();
            	participants.setCandidateId(id_);
            	participants.setThemeId(themeId);
            	participants.setId(id);
            	participants.setUserName("自动投票");
            	participants.setUserId(phone);
            	participants.setUserPhoto("");
            	voteService.insertVote(participants);
        	}
    	}
        return "redirect:/vote/memberList/"+themeId;
    }

    @RequestMapping(value = "createMember/{id}", method = RequestMethod.GET)
    public String createMember(@PathVariable String id, Model model) throws Exception {
        model.addAttribute("active", "create");
        VoteCandidate info = new VoteCandidateVo();
        info.setThemeId(id);
        model.addAttribute("info",info);
        return "vote/memberForm";
    }

    /**
     * <保存投票候选人></>
     * @param info
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "saveMember")
    public String saveMember(VoteCandidate info, RedirectAttributes redirectAttributes) {
        try {
            if(info != null){
                if(org.apache.commons.lang.StringUtils.isEmpty(info.getId())){
                    info.setId(UUID.get());
                    info.setCt(new Date());
                    info.setCb(SessionUtil.currentUserId());
                    voteService.insertSelective(info,info.getId());
                }else {
                    voteService.updateByPrimaryKeySelective(info,info.getId());
                }
                redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "保存成功!"));
            }
        } catch (Exception e) {
            logger.debug("保存失败: {}", e.getMessage());
            logger.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "保存失败！"));
        }
        return "redirect:/vote/memberList/"+info.getThemeId();
    }

    @RequestMapping(value = "memberDetail/{id}")
    public String memberDetail(@PathVariable String id, Model model) {
        try {
            VoteCandidateVo vo = new VoteCandidateVo();
            VoteCandidate member = voteService.selectByPrimaryKey(VoteCandidate.class,id);
            if(member != null){
                BeanUtils.copyProperties(member,vo);
               VoteTheme theme = voteService.selectByPrimaryKey(VoteTheme.class, vo.getThemeId());
                if(theme != null){
                    vo.setTheme(theme.getTitle());
                }
            }
            model.addAttribute("info",vo);
        } catch (Exception e) {
            logger.error("查看失败！", e);
        }
        return "vote/memberForm";
    }

    @RequestMapping(value = "delTheme", method = RequestMethod.POST)
    @ResponseBody
    public String delTheme(String id) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (StringUtils.isBlank(id)) {
                result.put(Constants.Result.RESULT, false);
                result.put(Constants.Result.REASON, "id不能为空");
            } else {
                VoteTheme theme = voteService.selectByPrimaryKey(VoteTheme.class, id);
                if (theme != null) {
                    voteService.deleteByPrimaryKey(VoteTheme.class, id);
                }
                result.put(Constants.Result.RESULT, true);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            result.put(Constants.Result.RESULT, false);
            result.put(Constants.Result.REASON, e.getMessage());
        }
        return MyGson.getInstance().toJson(result);
    }

    @RequestMapping(value = "delMember", method = RequestMethod.POST)
    @ResponseBody
    public String delMember(String id) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (StringUtils.isBlank(id)) {
                result.put(Constants.Result.RESULT, false);
                result.put(Constants.Result.REASON, "id不能为空");
            } else {
                VoteCandidate theme = voteService.selectByPrimaryKey(VoteCandidate.class, id);
                if (theme != null) {
                    voteService.deleteByPrimaryKey(VoteCandidate.class, id);
                }
                result.put(Constants.Result.RESULT, true);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            result.put(Constants.Result.RESULT, false);
            result.put(Constants.Result.REASON, e.getMessage());
        }
        return MyGson.getInstance().toJson(result);
    }



}
