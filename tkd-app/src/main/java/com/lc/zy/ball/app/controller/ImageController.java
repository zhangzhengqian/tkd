package com.lc.zy.ball.app.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.cxf.common.util.Base64Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lc.zy.common.util.FileServerUtils;
import com.lc.zy.common.util.ImageUtils;
import com.lc.zy.common.util.UUID;

@Controller
@RequestMapping(value = "/image")
public class ImageController {

    private static Logger logger = LoggerFactory.getLogger(ImageController.class);
    
    /**
     * 
     * <图片上传接口><功能具体实现>
     *
     * @create：2015年9月23日 上午10:56:40
     * @author： yankefei
     * @param request
     * @param response
     * @throws Exception
     */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String imageUpload(@RequestParam String image) throws Exception {
		String fid = null;
		try {
			//转换base64字符串为字节数组
			byte[] img = Base64Utility.decode(image);
			fid = thumbnailAndUpload("coacher_"+UUID.get(), img);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("image upload failed: {}", e);
		}
		return fid;
    }
	
	/**
	 * 压缩并上传
	 * @param buff
	 * @throws FileNotFoundException 
	 */
	private String thumbnailAndUpload(String fileName ,byte[] buff) throws Exception{
		String id = UUID.get();
		String file = "/tmp/"+id;
		OutputStream os = new FileOutputStream(file);
		os.write(buff);
		os.flush();
		os.close();
		String f1 = ImageUtils.createThumbnail(file, 150 );
		String f2 = ImageUtils.createThumbnail(file, 750 );
		FileServerUtils.upload(id, fileName, f1  , false, "image");
		FileServerUtils.upload(id+"bigPicture", fileName, f2  , false, "image");
		logger.debug("image upload success id:{}",id);
		return id;
	}
	
}
