package com.lc.zy.common.util;

import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
/**
 * 简单封装 freemarker
 * @author liangc
 *
 */
public class FreeMarkerUtils {

	private Configuration freemarkerCfg = null;
	private String text = null;
	
	public FreeMarkerUtils(){
		freemarkerCfg = new Configuration();
		freemarkerCfg.setClassForTemplateLoading(this.getClass(), "/");
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
	}
	/**
	 * @param templateName 
	 * 	<p>摸板路径
	 * 	<p>以classpath为根,例如 /src/main/resources/template/shop/template.ftl 
	 * 	<p>则填  /template/shop/template.ftl 即可
	 * @param root 实例化摸板所用的数据
	 */
	public FreeMarkerUtils(String templateName,Map<String, Object> root){
		this();
		text = fromFtl(templateName,root);
	}
	/**
	 * 方法说明.
	 *	用传入对象实例化指定摸板
	 * @param templateName 
	 * 	<p>摸板路径
	 * 	<p>以classpath为根,例如 /src/main/resources/dev/template/shop/template.ftl 
	 * 	<p>则填  /template/shop/template.ftl 即可
	 * @param root 实例化摸板所用的数据
	 * @return 返回 摸板实例化结果 属性
	 * @author <a href="mailto:liangchuan@knet.cn">liangchuan</a>
	 */
	public String fromFtl(String templateName,Map<String, Object> root){
		try{
			Template template = freemarkerCfg.getTemplate(templateName);
			template.setEncoding("UTF-8");
			StringWriter writer = new StringWriter();
			template.process(root, writer);
			text = writer.toString();
			return text;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 方法说明.
	 *	获取摸板实例化后的结果
	 * @return
	 * @author <a href="mailto:liangchuan@knet.cn">liangchuan</a>
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * 静态方法
	 * 
	 * @param templateName
	 * @param data
	 * @return
	 */
	public static String format(String templateName, Object data) {
		Configuration freemarkerCfg = new Configuration();
		freemarkerCfg.setClassForTemplateLoading(FreeMarkerUtils.class, "/");
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
		Template template;
		try {
			template = freemarkerCfg.getTemplate(templateName);
			template.setEncoding("UTF-8");
			StringWriter writer = new StringWriter();
			template.process(data, writer);
			return writer.toString();
		} catch (Exception e) {
			return "";
		}
	}
}
