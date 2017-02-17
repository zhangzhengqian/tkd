package com.lc.zy.common.util;

import java.io.File;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 图片操作
 * @author liangc
 */
public class ImageUtils {
	
	private static Logger logger = LoggerFactory.getLogger(ImageUtils.class);
	
	/**
	 * 默认的图片压缩质量是 80
	 */
	public static final String def_quality = "80";
	
	/**
	 * 图片压缩
	 * @param file 要压缩的图片
	 * @param width 压缩后最大边长
	 * @return
	 * @throws Exception
	 */
	public static String createThumbnail(String file, int width) throws Exception {
		return createThumbnail(file,width,"1",def_quality);
	}
	
	/**
	 * 图片压缩
	 * @param file 要压缩的图片
	 * @param width 压缩后最大边长
	 * @param quality 1~100 表示图片质量，默认80,超范围则取默认值
	 * @return
	 * @throws Exception
	 */
	public static String createThumbnail(String file, int width,int quality) throws Exception {
		String q = def_quality;
		if(quality>100 || quality<1){
			logger.warn("image thumbnail quality in range  [1-100] , but input={},so use default {}",quality,def_quality);
		}else{
			q = String.valueOf(quality);
		}
		return createThumbnail(file,width,"1",q);
	}
	
	/*
	 * 根据尺寸缩放图片
	 * 
	 * @author liangc
	 * @param path 源图路径
	 * @param width 压缩后宽度
	 * @param type 1为像素，2为百分比处理，如（像素大小：1024x1024,百分比：50%x50%）
	 * @param quality 1~100 图片质量
	 * @return
	 * @throws Exception
	 */
	public static String createThumbnail(String file, int width, String type,String quality) throws Exception {
		IMOperation op = new IMOperation();
		ConvertCmd cmd = new ConvertCmd(true);
		String newFileName = null;
		// 文件名前缀
		String prevFileName = null;
		String newfile = null;
		try {
			op.addImage();
			String raw = "";
			int height = width;
			if ("1".equals(type)) {
				// 按像素
				raw = width + "x" + height ;
				prevFileName = width + "x" + height + "_";
			} else {
				// 按百分比
				raw = width + "%x" + height + "%";
				prevFileName = width + "%x" + height + "%_";
			}
			// 压缩
			op.addRawArgs("-thumbnail", raw);
			// 图片质量
			op.addRawArgs("-quality", "80");
			op.addImage();
			// 系统类型
			String osName = System.getProperty("os.name").toLowerCase();
			if (osName.indexOf("win") != -1) {
				//如果是windows 就不压缩了
				return file;
			}
			// 读取配置文件：工程路径
			String filePath =  file;
			String fpath[] = filePath.split("/");
			// 原图名称
			String oldFileName = fpath[fpath.length - 1];
			// 压缩后的新文件名
			newFileName = prevFileName + oldFileName;
			// 新文件路径
			newfile = filePath.replace(oldFileName, newFileName);
			// 压缩
			cmd.run(op, filePath, newfile);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return newfile;
	}
	
	/**
	 * 清除传入的文件
	 * @param files
	 */
	public void clean(String ... files){
		for(String file : files){
			try{
				File f = new File(file);
				f.deleteOnExit();
			}catch(Exception e){
				logger.warn("clean error :{}",e.getMessage());
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(createThumbnail("/app/a.png",1920));
	}
}
