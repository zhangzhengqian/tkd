package com.lc.zy.ball.boss.common.web;

import java.io.FileNotFoundException;
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

import com.lc.zy.common.util.FileServerUtils;
import com.lc.zy.common.util.ImageUtils;
import com.lc.zy.common.util.UUID;

/**
 * 图片上传功能
 * @author liangc
 *
 */
@Controller
public class UploaderController {

	private static Logger logger = LoggerFactory.getLogger(UploaderController.class);
	public static void main(String[] args) {
		JSONObject j = new JSONObject();
		System.out.println(j.get("foo"));
	}
	@RequestMapping("/uploader")
	@ResponseBody
	public String uploadFile(HttpServletRequest request,HttpServletResponse response)throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		JSONObject json = new JSONObject(); 
		json.put("success", true);
		try{
			for(Iterator<String> it = multipartRequest.getFileNames();it.hasNext();){
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
				String fid = thumbnailAndUpload(fileName, file.getBytes());
				json.put("url", FileServerUtils.getFileAsUrl(fid));
			}
		}catch(Exception e){
			logger.error("",e);
			json.put("success", false);
		}
		String res = json.toString();
		logger.debug(res);
		return res;
	}
	
	/**
	 * 压缩并上传
	 * @param buff
	 * @throws FileNotFoundException 
	 */
	private String thumbnailAndUpload(String fileName ,byte[] buff) throws Exception{
		String id = UUID.get();
		String file = "/tmp/"+id;
		try{
			OutputStream os = new FileOutputStream(file);
			os.write(buff);
			os.flush();
			os.close();
			String f1 = ImageUtils.createThumbnail(file, 150 );
			String f2 = ImageUtils.createThumbnail(file, 750 );
			FileServerUtils.upload(id, fileName, f1  , false, "image");
			FileServerUtils.upload(id+"bigPicture", fileName, f2  , false, "image");
		}catch(FileNotFoundException e){
			FileServerUtils.upload(id, fileName, buff,false,"image");
			FileServerUtils.upload(id+"bigPicture", fileName, buff,false,"image");
		}
		logger.debug("upFile.id={}",id);
		return id;
	}
	
	/**
	 * 
	 * <轮播图上传图片><无压缩 640*156 png格式>
	 *
	 * @create：2015年9月16日 下午4:03:47
	 * @author： sl
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/uploaderCarousel")
	@ResponseBody
	public String uploadCarouselFile(HttpServletRequest request,HttpServletResponse response)throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		JSONObject json = new JSONObject(); 
		json.put("success", true);
		try{
			for(Iterator<String> it = multipartRequest.getFileNames();it.hasNext();){
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
				String id = UUID.get();
				String fid = FileServerUtils.upload(id, fileName, file.getBytes() , false, "image");
				json.put("url", FileServerUtils.getFileAsUrl(fid));
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
