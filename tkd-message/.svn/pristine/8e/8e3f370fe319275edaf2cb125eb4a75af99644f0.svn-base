package com.lc.zy.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lc.zy.common.util.SpringUtils;


public class MessageMain {
	private static Logger logger = LoggerFactory.getLogger(MessageMain.class);
	
	private static String paths[] = { "classpath:spring/applicationContext.xml" };
	public static ApplicationContext ctx = null;
	
	public static void main(String[] args) {
		ctx = new ClassPathXmlApplicationContext(paths);
		logger.debug("message_started...");
		System.out.println(SpringUtils.getBean("brokerUrl"));
	}
}
