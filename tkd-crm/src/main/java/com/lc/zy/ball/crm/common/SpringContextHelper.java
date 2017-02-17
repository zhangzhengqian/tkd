package com.lc.zy.ball.crm.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHelper implements ApplicationContextAware {

	private static ApplicationContext context = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}

	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

	public static <T> T getBean(Class<T> clazz) {
		return (T)context.getBean(clazz);
	}

}
