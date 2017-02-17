package com.lc.zy.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils implements ApplicationContextAware {

	private Logger logger = LoggerFactory.getLogger(SpringUtils.class);
	
	public static ApplicationContext context = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		logger.debug("set application context ===> "+applicationContext);
		context = applicationContext;
	}

	public static Object getBean(String beanName) throws BeansException {
		return context.getBean(beanName);
	}

	public static <T> T getBean(Class<T> clazz) throws BeansException  {
		return (T)context.getBean(clazz);
	}

}