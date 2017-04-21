package com.lc.zy.ball.boss.common.web;

import com.lc.zy.common.Constants;
import com.lc.zy.common.qrCode.BarcodeFactory;
import com.lc.zy.common.util.PropertyUtils;
import com.lc.zy.common.util.UUID;
import com.qiniu.common.QiniuException;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author sl
 *
 */
@Controller
public class UpImageController {

	private static Logger logger = LoggerFactory.getLogger(UpImageController.class);

	@Resource(name="configs")
	private Map<String,Object> configs = null;

	// 创建上传对象
	UploadManager uploadManager = new UploadManager();

	// 简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public String getUpToken() {
		// 密钥配置
		Auth auth = Auth.create((String)configs.get("fileserver.access_key"), (String)configs.get("fileserver.secret_key"));
		return auth.uploadToken((String)configs.get("fileserver.bucketname"));
	}

	/**
	 *
	 * <图片上传><功能具体实现>
	 *
	 * @create：2016年4月22日 上午9:17:15
	 * @author： sl
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/uploadImage")
	@ResponseBody
	public String uploadImage(HttpServletRequest request, HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		JSONObject json = new JSONObject();
		json.put("success", true);
		try {
			for (Iterator<String> it = multipartRequest.getFileNames(); it.hasNext();) {
				String fileName = it.next();
				logger.debug("-------------->{}", fileName);
				MultipartFile file = multipartRequest.getFile(fileName);
				// 图片名称
				String id = UUID.get();
				String key = id + ".png";
				uploadManager.put(file.getBytes(), key, getUpToken());
				// 获取url
				json.put("url", getFileAsUrl(key));
			}
		} catch (IOException e) {
			logger.error("",e);
			json.put("success", false);
		}
		String res = json.toString();
		logger.debug(res);
		return res;
	}
	/**
	 * 
	 * <文件上传><功能具体实现>
	 *
	 * @create：2017年4月7日 下午1:50:45
	 * @author：ywl
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/uploadFile")
	@ResponseBody
	public String uploadFile(HttpServletRequest request, HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		JSONObject json = new JSONObject();
		json.put("success", true);
		try {
			for (Iterator<String> it = multipartRequest.getFileNames(); it.hasNext();) {
				String fileName = it.next();
				logger.debug("-------------->{}", fileName);
				MultipartFile file = multipartRequest.getFile(fileName);
				long size = (file.getSize());
				String fileSize = getFileSize(size);
				//response.setContentLength(size);
				logger.debug("文件大小：{}"+fileSize);
				//原始文件名
				String realName = file.getOriginalFilename();
				String suffixName = realName.substring(realName.lastIndexOf(".")+1);
				logger.debug("后缀名:{}"+suffixName);
				// 图片名称
				String id = UUID.get();
				String key = id + realName;
				uploadManager.put(file.getBytes(), key, getUpToken());
				// 获取url  +"?attname=<"+key+">"
				json.put("url", getFileAsUrl(key));
				json.put("fileName", realName);
				json.put("fileSize", fileSize);
				json.put("suffixName", suffixName);
			}
		} catch (IOException e) {
			logger.error("",e);
			json.put("success", false);
		}
		String res = json.toString();
		logger.debug(res);
		return res;
	}

	/**
	 *
	 * <功能描述><功能具体实现>
	 *
	 * @create：2016年4月22日 上午10:10:40
	 * @author： sl
	 * @param fid
	 * @return
	 */
	public static String getFileAsUrl(String fid) {
		String url = getProperty("fileserver.path", "http://7xt8bo.com2.z0.glb.qiniucdn.com/") + fid;
		return url;
	}

	/**
	 *
	 * <功能描述><功能具体实现>
	 *
	 * @create：2016年4月22日 上午10:10:47
	 * @author： sl
	 * @param key
	 * @param def
	 * @return
	 */
	private static String getProperty(String key, String def) {
		try {
			return PropertyUtils.getProperty(key, def);
		} catch (Exception e) {
			return def;
		}
	}

	/**
	 *
	 * <二维码图片上传><功能具体实现>
	 *
	 * @create：2016-10-15 10:07:51
	 * @author：sl
	 * @param statiumId
	 * @return java.lang.String
	 */
	public String uploadQRCode(String statiumId) throws Exception {
		// 二维码地址
		String url = "";
		try {
			// 获取二维码
			BarcodeFactory barcodeFactory = new BarcodeFactory();
			byte[] bytes = null;

			bytes = barcodeFactory.encode(statiumId, Constants.QRCode.WIDTH, Constants.QRCode.HEIGHT);

			// 上传文件
			String id = UUID.get();
			String key = id + ".png";
			try {
				uploadManager.put(bytes, key, getUpToken());
			} catch (QiniuException e) {
				logger.debug("上传七牛服务器失败 {}", e.getMessage());
			}
			// 获取url
			url = getFileAsUrl(key);
		} catch (Exception e) {
			logger.debug("生成道馆二维码 {}", e.getMessage());
			e.printStackTrace();
		}
		return url;
	}
	
	/*@RequestMapping(value="/downloadFile/{url}",method = RequestMethod.POST)
	public void downFile(@PathVariable String url){
		Auth auth = Auth.create((String)configs.get("fileserver.access_key"), (String)configs.get("fileserver.secret_key"));
		String downloadUrl = auth.privateDownloadUrl(url);
		System.out.println("downloadUrl============="+downloadUrl);
	}*/
	
	public String getFileSize(long size){
		//如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义  
        if (size < 1024) {  
            return String.valueOf(size) + "B";  
        } else {  
            size = size / 1024;  
        }  
        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位  
        //因为还没有到达要使用另一个单位的时候  
        //接下去以此类推  
        if (size < 1024) {  
            return String.valueOf(size) + "KB";  
        } else {  
            size = size / 1024;  
        }  
        if (size < 1024) {  
            //因为如果以MB为单位的话，要保留最后1位小数，  
            //因此，把此数乘以100之后再取余  
            size = size * 100;  
            return String.valueOf((size / 100)) + "."  
                    + String.valueOf((size % 100)) + "MB";  
        } else {  
            //否则如果要以GB为单位的，先除于1024再作同样的处理  
            size = size * 100 / 1024;  
            return String.valueOf((size / 100)) + "."  
                    + String.valueOf((size % 100)) + "GB";  
        }  
	}
}
