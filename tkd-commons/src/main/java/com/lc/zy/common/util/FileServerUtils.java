package com.lc.zy.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.ByteArrayPartSource;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.FilePartSource;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.PartSource;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName:UploadFile.java
 * @Author:liu.ou
 * @Description:
 * @CreateDate:2015年1月19日
 */
public class FileServerUtils {

	private static Logger logger = LoggerFactory.getLogger(FileServerUtils.class);

	/**
	 * @MethodName upload
	 * @Description 上传到图片服务器
	 * @Author liu.ou
	 * @param fileId 新增时为空,更新需指明
	 * @param fileName
	 * @param b
	 * @param watermark 是否加水印:true/false
	 * @param file_type 附件类型
	 * @param contentType 
	 * @param multipartFile
	 * @return
	 * @throws Exception
	 * @CreatDate 2015年1月19日
	 */
	public static String upload(String fileId, String fileName, byte[] b, boolean watermark, String file_type) throws Exception{
		return upload(fileId,fileName,b,watermark,file_type,false);
	}

	public static String upload(String fileId, String fileName, String filePath, boolean watermark, String file_type) throws Exception{
		InputStream is = null;
		ByteArrayOutputStream bos = null;
		try{
			is = new FileInputStream(filePath);
			bos = new ByteArrayOutputStream();
			byte[] buff = new byte[1024];
			int t=0;
			while((t=is.read(buff))!=-1){
				bos.write(buff,0,t);
			}
			byte[] b = bos.toByteArray();
			return upload(fileId,fileName,b,watermark,file_type,false);
		}catch(Exception e){
			throw e;
		}finally{
			if (is!=null){
				is.close();
			}
			if(bos!=null){
				bos.close();
			}
		}
	}
	/**
	 * 获取附件url
	 * @param fid
	 * @return
	 */
	public static String getFileAsUrl(String fid){
		String url = getProperty("fileserver.path","http://fileserver.qiuyouzone.com/fileserver/")+"get/"+fid;
		return url;
	}
	public static String upload(String fileId, String fileName, byte[] b, boolean watermark, String file_type,boolean auth) throws Exception{
		String url = getProperty("fileserver.path","http://fileserver.qiuyouzone.com/fileserver/")+"upload/";
		PostMethod method = new PostMethod(url);
		try {
			if(StringUtils.isBlank(fileId)){
				fileId = "";
			}
			StringPart _id = new StringPart("id", fileId);
			StringPart _appid = new StringPart("appid", getProperty("appid","ball"));
			StringPart _appkey = new StringPart("appkey", getProperty("appkey","qwertyuiop"));
			StringPart _file_type = new StringPart("file_type", file_type);
			StringPart _file_name = new StringPart("file_name", fileName, "utf8");
			StringPart _auth = new StringPart("auth",Boolean.toString(auth));
			String mark = String.valueOf(watermark);

			StringPart _watermark = new StringPart("watermark", mark);
			PartSource part = new ByteArrayPartSource(fileName,b);
			FilePart filePart = new FilePart("file",part,null,"utf8");
			method.setRequestEntity(new MultipartRequestEntity(new Part[]{
				_id,_appid,_appkey,_file_type,_file_name,_auth,_watermark,filePart},method.getParams()));
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
			client.executeMethod(method);
			String res = method.getResponseBodyAsString();
			JSONObject json = new JSONObject(res);
			if (json.getBoolean("success")) {
				return json.getJSONObject("entity").getString("id");
			}
			throw new Exception("上传失败!");
		} finally {
			method.releaseConnection();
		}
	}
	
	public static String token() throws Exception{
		String url = getProperty("fileserver.path","http://fileserver.lczybj.com/fileserver/")+"token/";
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		try{
			method.addParameter("appid", getProperty("appid","sara"));
			method.addParameter("appkey", getProperty("appkey","sara"));
			client.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
			client.executeMethod(method);
			String res = method.getResponseBodyAsString();
			JSONObject json = new JSONObject(res);
			if(json.getBoolean("success")){
				return json.getJSONObject("entity").getString("token");
			}else{
				throw new Exception(res);
			}
		}catch(Exception e){
			logger.error("",e);
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 删除文件
	 * 
	 * @param fileId
	 * @throws Exception
	 */
	public static void delete(String fileId) throws Exception{
		delete(fileId,null);
	}
	public static void delete(String fileId,String token) throws Exception{
//		删除地址 http://fileserver.lczybj.com/fileserver/del/ 只接收post请求，参数 id、appid、appkey ，如果是需要鉴权的资源则必须填写token参数并忽略appid、appkey
//			不需要鉴权时，post 传递参数 id、appid、appkey 完成删除
//			需要鉴权时，post 传递参数 id、token 完成删除
		String url = getProperty("fileserver.path","http://fileserver.lczybj.com/fileserver/")+"del/";
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		try {
			method.addParameter("id", fileId);
			if(token!=null){
				method.addParameter("token", token);
			}else{
				method.addParameter("appid", getProperty("appid","sara"));
				method.addParameter("appkey", getProperty("appkey","sara"));
			}
			client.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
			client.executeMethod(method);
			String res = method.getResponseBodyAsString();
			logger.debug("delete__res == "+res);
			JSONObject json = new JSONObject(res);
			if (!json.getBoolean("success")) {
				throw new Exception("删除失败!");
			}
		} finally {
			method.releaseConnection();
		}
	}
	
	/**
	 * 将word文档转换为html
	 * 
	 * @param fileId
	 * @return
	 * @throws Exception
	 */
	public static String toHtml(String fileId) throws Exception{
		String url = getProperty("fileserver.path","http://fileserver.lczybj.com/fileserver/")+"doc2html/";
		GetMethod method = new GetMethod(url + fileId);
		try {
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
			client.executeMethod(method);
			String res = method.getResponseBodyAsString();
			JSONObject json = new JSONObject(res);
			if (json.getBoolean("success")) {
				return json.getJSONObject("entity").getString("html");
			}
			
			throw new Exception("转换失败!");
		} finally {
			method.releaseConnection();
		}
	}
	
	private static String getProperty(String key,String def){
		try{
			return PropertyUtils.getProperty(key,def);
		}catch(Exception e){
			return def;
		}
	}
	
	
	/**
	 * UEditor上传图片文件
	 * 
	 * @param fileName
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static String uploadImage(String fileName, File file) throws Exception{
		String path = getProperty("fileserver.path","http://fileserver.lczybj.com/fileserver/");
		String url = path+"upload/";
		PostMethod method = new PostMethod(url);
		try {
			StringPart _appid = new StringPart("appid", getProperty("appid","sara"));
			StringPart _appkey = new StringPart("appkey", getProperty("appkey","sara"));
			StringPart _watermark = new StringPart("watermark", "false");
			StringPart _file_type = new StringPart("file_type", "image");

			FilePartSource partSource = new FilePartSource(fileName, file);
			FilePart filePart = new FilePart("file",partSource);
			method.setRequestEntity(new MultipartRequestEntity(new Part[]{
				_appid, _appkey, _watermark, _file_type, filePart}, method.getParams()));
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
			client.executeMethod(method);
			String res = method.getResponseBodyAsString();
			JSONObject json = new JSONObject(res);
			if (json.getBoolean("success")) {
				return path+"get/"+json.getJSONObject("entity").getString("id");
			}
			throw new Exception("上传失败!");
		} finally {
			method.releaseConnection();
		}
	}
	
	
	public static void main(String[] args) throws Exception {
//		String success = uploadImage("cart.png", new File("/home/tiger/images/cart.png"));
//		System.out.println(success);
		System.out.println(token());
	}
}
