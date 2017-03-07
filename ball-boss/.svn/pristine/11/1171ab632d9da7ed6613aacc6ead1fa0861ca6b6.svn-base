package com.lc.zy.ball.boss.framework.system.controller;


import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import org.springside.modules.web.Servlets;
import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.system.service.BadWordsService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.BadWords;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.SynBadWordsUtil;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.web.WebUtils;

@Controller
@RequestMapping(value = "/badWords")
public class BadWordsController extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(BadWordsController.class);

	@Autowired
	private BadWordsService badWordsService;

	@Autowired
	private SynBadWordsUtil synBadWordsUtil;

	/**
	 * 
	 * <禁词列表><功能具体实现>
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年1月12日 下午7:12:37
	 */
	@RequestMapping(value = { "list" })
	public String list(Model model, ServletRequest request) throws Exception {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request, 50);
		logger.debug("badwords_list_search_params={}", searchParams);
		Page<BadWords> onePage = badWordsService.find(new PageRequest(page, size), searchParams);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		model.addAttribute("data", onePage);
		return "admin/badWordsList";
	}

	/**
	 * 
	 * <新增禁词一次增加一个或多个><功能具体实现>
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年1月12日 下午7:15:10
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model) throws Exception {
		return "admin/badWordsForm";
	}

	/**
	 * 
	 * <新增禁词一次增加一个或多个><功能具体实现>
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年1月12日 下午7:15:19
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(BadWords info, Model model) throws Exception {
		badWordsService.add(info);
		return "redirect:/badWords/list";
	}

	/**
	 * 
	 * <删除禁词><功能具体实现>
	 * 
	 * @param id
	 * @return
	 * @author liangsh
	 * @date 2016年1月12日 下午7:12:08
	 */
	@RequestMapping(value = "del/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String del(@PathVariable String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.isBlank(id)) {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "id不能为空");
			} else {
				badWordsService.deleteByPrimaryKey(BadWords.class, id);
				synBadWordsUtil.synBadWords("");
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

	@RequestMapping(value = "uploadWords", method = RequestMethod.GET)
	public String uploadForm(Model model) throws Exception {
		return "admin/uploadWords";
	}

	@RequestMapping(value = "uploadWords", method = RequestMethod.POST)
	public String uploadWord(@RequestParam("myfile") MultipartFile myfile, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!myfile.isEmpty()) {
			logger.info(myfile.getName());
//			 CommonsMultipartFile cf = (CommonsMultipartFile) myfile; //
//			 DiskFileItem fi = (DiskFileItem) cf.getFileItem();
//			 File file = fi.getStoreLocation();
//			 logger.info(new String(file.getPath()));
//			 try {
//			 // 创建存储word文档的对象
//			 HWPFDocument doc = new HWPFDocument(new
//			 FileInputStream(file.getPath()));
//			 // 用来获得word文档内容
//			 Range range = doc.getRange();
//			 // 文档段落数目
//			 int paragraphCount = range.numParagraphs();
//			 // 遍历段落读取数据
//			 for (int i = 0; i < paragraphCount; i++) {
//			 Paragraph pph = range.getParagraph(i);
//			 if (StringUtils.isNotBlank(pph.text())) {
//			 BadWords info = new BadWords();
//			 info.setLevel(4);
//			 info.setBadWord(pph.text().trim());
//			 info.setCt(new Date());
//			 info.setCb(SessionUtil.currentUserId());
//			 info.setId(UUID.get());
//			 badWordsService.add(info);
//			 System.out.println(pph.text().trim());
//			 }
//			 }
//			 } catch (FileNotFoundException e) {
//			 e.printStackTrace();
//			 } catch (IOException e) {
//			 e.printStackTrace();
//			 }

			InputStream is;
			try {
				is = myfile.getInputStream();
				InputStreamReader isr = new InputStreamReader(is,"UTF-8");
				BufferedReader br = new BufferedReader(isr);
				String s;
				while ((s = br.readLine()) != null)
					if (StringUtils.isNotBlank(s)) {
						BadWords info = new BadWords();
						info.setLevel(4);
						info.setBadWord(s.trim() );
						info.setCt(new Date());
						info.setCb(SessionUtil.currentUserId());
						info.setId(UUID.get());
						badWordsService.add(info);
						logger.info(s.trim());
					}
				br.close();
				isr.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "redirect:/badWords/list";
	}

}
