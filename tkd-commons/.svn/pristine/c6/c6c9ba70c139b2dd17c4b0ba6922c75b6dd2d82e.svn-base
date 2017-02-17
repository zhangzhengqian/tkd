package com.lc.zy.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取文档中的文本信息
 * 支持 doc 和 docx 格式
 * @author liangc
 *
 */
public class Docx2Text {
	
	private static Logger logger = LoggerFactory.getLogger(Docx2Text.class);	
	
	public static String process(byte[] buff) throws Exception {
		logger.debug("doc to text : 1");
		ByteArrayInputStream bis = null;
		try{
			logger.debug("doc to text : 2.1");
			bis = new ByteArrayInputStream(buff);
			logger.debug("doc to text : 2.2");
			XWPFDocument doc = new XWPFDocument(bis);
			logger.debug("doc to text : 2.3");
			XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
			logger.debug("doc to text : 2.4");
			String text = extractor.getText();
			logger.debug("doc to text : 2.5");
			//extractor.close();
			//logger.debug("doc to text : 2.6");
			return text;
		}catch(Exception e){
			try{
				logger.debug("doc to text : 3.1");
				bis = new ByteArrayInputStream(buff);
				logger.debug("doc to text : 3.2");
				HWPFDocument doc = new HWPFDocument(bis); 
				logger.debug("doc to text : 3.3");
				String text = doc.getDocumentText();
				logger.debug("doc to text : 3.4");
				return text;
			}catch(Exception e2){
				logger.debug("doc to text error 3 : "+e2.getMessage());
				logger.error("",e2);
				e2.printStackTrace();
				throw e2;
			}
		}finally{
			logger.debug("doc to text : 4.1");
			bis.close();
		}
	}
	public static String process(InputStream is) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buff = new byte[1024];
		int t = 0;
		while((t=is.read(buff))!=-1){
			bos.write(buff,0,t);
		}
		byte[] data = bos.toByteArray();
		is.close();
		bos.close();
		return process(data);
	}

}
