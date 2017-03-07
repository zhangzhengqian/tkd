package com.lc.zy.ball.boss.framework.cbsc;


import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lc.zy.ball.boss.common.web.AbstractController;

@Controller
@RequestMapping(value = "/cbsc")
public class CbscController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(CbscController.class);

	


	@RequestMapping("/uploader")
	@ResponseBody
	public String uploadFile(HttpServletRequest request,HttpServletResponse response)throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		JSONObject json = new JSONObject();
		json.put("success", true);
		try{
			for(Iterator<String> it = multipartRequest.getFileNames(); it.hasNext();){
				String fileName = it.next();
				logger.debug("-------------->{}",fileName);
				MultipartFile file = multipartRequest.getFile(fileName);
				logger.debug("-----------------------------------");
				logger.debug("file_key : "+fileName);
				logger.debug("name : "+file.getName());
				logger.debug("size : "+file.getSize());
				logger.debug("contentType : "+file.getContentType());
				logger.debug("originalFilename : "+file.getOriginalFilename());
				logger.debug("-----------------------------------");
				String filePath = "/tmp/cbsc/";
				filePath=filePath+file.getOriginalFilename();
				OutputStream os = new FileOutputStream(filePath);
				os.write(file.getBytes());
				os.flush();
				os.close();
			}
		}catch(Exception e){
			logger.error("",e);
			json.put("success", false);
		}
		String res = json.toString();
		logger.debug(res);
		return res;
	}
	

}
