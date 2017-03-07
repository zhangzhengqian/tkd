package com.lc.zy.ball.boss.framework.publishhtml;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.cxf.common.util.Base64Utility;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.publishhtml.vo.FileInfo;
import com.lc.zy.common.util.MyGson;
@Controller
@RequestMapping(value="/html5")
public class PublishHtmlController extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger(PublishHtmlController.class);
	
	@RequestMapping(value="list")
	public String listFile(Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
		FileInfo file = new FileInfo();
		file.setPath("/home/appusr/www/WXGZPT");
		file.setName("WXGZPT");
		findSubs(file);
		String fileJson = MyGson.getInstance().toJson(file);
		model.addAttribute("files", fileJson);
		return "html5/list";
	}
	private void findSubs(FileInfo file) throws UnsupportedEncodingException{
		File file_ = new File(file.getPath());
		if(file_.isDirectory()){
			String subs[] = file_.list();
			List<FileInfo> subs_ = new ArrayList<FileInfo>();
			file.setChildren(subs_);
			for(String sub:subs){
				FileInfo file__ = new FileInfo();
				file__.setPath(file_.getAbsolutePath()+"/"+sub);
				file__.setName(sub);
				logger.debug(sub);
				subs_.add(file__);
				File newFile = new File(file_.getAbsolutePath()+"/"+sub);
				if(newFile.isDirectory()){
					findSubs(file__);
				}
			}
		}
	}
	
	@RequestMapping(value="get")
	@ResponseBody
	public String getFile(String path,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
		String fileName = FilenameUtils.getName(path);
		//文本文件
		StringBuilder result = new StringBuilder();
		if(fileName.indexOf(".html")!=-1||fileName.indexOf(".js")!=-1||fileName.indexOf(".css")!=-1){
			BufferedReader reader = null;
			try {
				//File file = new File("E://WXGZPT//activity//activityDetail.html");
	        	File file = new File(path);
	        	String tempString = null;
	        	reader = new BufferedReader(new FileReader(file));
	        	int line = 1;
				while ((tempString = reader.readLine()) != null) {
					result.append("line " + line + ": " + tempString+"\n");
					line++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			//String img = getImageStr("E:\\WXGZPT\\activity\\images\\active_1.jpg");
			String img = getImageStr(path);
			result.append(img);
		}
		return result.toString();
	}
	
	@RequestMapping("/uploader")
	@ResponseBody
	public String uploadFile(String path,boolean pathFlag,HttpServletRequest request,HttpServletResponse response)throws Exception {
		logger.debug("修改人：,{}",SessionUtil.currentUsername());
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
				String name = file.getOriginalFilename();
				if(pathFlag){
					path = path+"/";
				}else{
					name = FilenameUtils.getName(path);
					path = FilenameUtils.getFullPath(path);
				}
				OutputStream os = new FileOutputStream(path+name);
				os.write(file.getBytes());
				os.flush();
				os.close();
				if(name.indexOf(".zip")!=-1){
					logger.debug("unzip "+path+name);
					ZipFile zip = new ZipFile(new File(path+name));  
					for(Enumeration entries = zip.entries();entries.hasMoreElements();){
			        	 ZipEntry entry = (ZipEntry)entries.nextElement();  
			             String zipEntryName = entry.getName();
			             if(entry.isDirectory()){
			            	 File tmp = new File(path+zipEntryName);
			            	 if(!tmp.exists()){
			            		 tmp.mkdir();
			            	 }
			            	 continue;
			             }
			             InputStream in = zip.getInputStream(entry);
			             OutputStream out = new FileOutputStream(path+zipEntryName);  
			             byte[] buf1 = new byte[1024];  
			             int len;  
			             while((len=in.read(buf1))>0){  
			                 out.write(buf1,0,len);  
			             }  
			             in.close();  
			             out.close();
			        }
					logger.debug("unzip "+path+name+" complete!");
					zip.close();
					File zipFile = new File(path+name);
					zipFile.delete();
				}
			}
		}catch(Exception e){
			logger.error("",e);
			json.put("success", false);
		}
		String res = json.toString();
		logger.debug(res);
		return res;
	}
	
	@RequestMapping(value="importSchedule")
	public String importSchedule(Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
		return "html5/importSchedule";
	}
	
	@RequestMapping("/uploadSchedule")
	@ResponseBody
	public String uploadSchedule(HttpServletRequest request,HttpServletResponse response)throws Exception {
		logger.debug("上传人：,{}",SessionUtil.currentUsername());
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
				String name = file.getOriginalFilename();
				OutputStream os = new FileOutputStream("/home/appusr/www/WXGZPT/womenGold/saicheng/"+name);
				os.write(file.getBytes());
				os.flush();
				os.close();
				if(name.indexOf(".zip")!=-1){
					logger.debug("unzip "+"/home/appusr/www/WXGZPT/womenGold/saicheng/"+name);
					ZipFile zip = new ZipFile(new File("/home/appusr/www/WXGZPT/womenGold/saicheng/"+name));  
					for(Enumeration entries = zip.entries();entries.hasMoreElements();){
			        	 ZipEntry entry = (ZipEntry)entries.nextElement();  
			             String zipEntryName = entry.getName();
			             if(entry.isDirectory()){
			            	 File tmp = new File("/home/appusr/www/WXGZPT/womenGold/saicheng/"+zipEntryName);
			            	 if(!tmp.exists()){
			            		 tmp.mkdir();
			            	 }
			            	 continue;
			             }
			             InputStream in = zip.getInputStream(entry);
			             OutputStream out = new FileOutputStream("/home/appusr/www/WXGZPT/womenGold/saicheng/"+zipEntryName);  
			             byte[] buf1 = new byte[1024];  
			             int len;  
			             while((len=in.read(buf1))>0){  
			                 out.write(buf1,0,len);  
			             }  
			             in.close();  
			             out.close();
			        }
					logger.debug("unzip "+"/home/appusr/www/WXGZPT/womenGold/saicheng/"+name+" complete!");
					zip.close();
					File zipFile = new File("/home/appusr/www/WXGZPT/womenGold/saicheng/"+name);
					zipFile.delete();
				}
			}
		}catch(Exception e){
			logger.error("",e);
			json.put("success", false);
		}
		String res = json.toString();
		logger.debug(res);
		return res;
	}
	
	
	
	 private String getImageStr(String imagePath)  
	    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
	        InputStream in = null;  
	        byte[] data = null;  
	        //读取图片字节数组  
	        try   
	        {  
	            in = new FileInputStream(imagePath);          
	            data = new byte[in.available()];  
	            in.read(data);  
	            in.close();  
	        }   
	        catch (IOException e)   
	        {  
	            e.printStackTrace();  
	        }  
	        //对字节数组Base64编码 
	        return Base64Utility.encode(data);  
	    }  
	public static void main(String[] args) throws IOException {
		ZipFile zip = new ZipFile(new File("E:\\WXGZPT\\123.zip"));  
        for(Enumeration entries = zip.entries();entries.hasMoreElements();){
        	 ZipEntry entry = (ZipEntry)entries.nextElement();  
             String zipEntryName = entry.getName();
             if(entry.isDirectory()){
            	 File tmp = new File("E:\\WXGZPT\\"+zipEntryName);
            	 if(!tmp.exists()){
            		 tmp.mkdir();
            	 }
            	 continue;
             }
             InputStream in = zip.getInputStream(entry);
             OutputStream out = new FileOutputStream("E:\\WXGZPT\\"+zipEntryName);  
             byte[] buf1 = new byte[1024];  
             int len;  
             while((len=in.read(buf1))>0){  
                 out.write(buf1,0,len);  
             }  
             in.close();  
             out.close();
        }
        File zipFile = new File("E:\\WXGZPT\\123.zip");
        zip.close();
        zipFile.delete();
	}
}
