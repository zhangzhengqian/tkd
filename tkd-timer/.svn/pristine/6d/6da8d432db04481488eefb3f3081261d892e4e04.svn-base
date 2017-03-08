package com.lc.zy.ball.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TimerMain {
	private static Logger logger = LoggerFactory.getLogger(TimerMain.class);
	
	private static String paths[] = { "classpath:spring/applicationContext.xml" };
	public static ApplicationContext ctx = null;
	
	public static void main(String[] args) {
		ctx = new ClassPathXmlApplicationContext(paths);
		logger.debug("timmer_started...");
	}
}
