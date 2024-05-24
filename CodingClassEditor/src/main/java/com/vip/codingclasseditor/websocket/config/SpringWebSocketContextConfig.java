package com.vip.codingclasseditor.websocket.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringWebSocketContextConfig implements ApplicationContextAware {

	private static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringWebSocketContextConfig.context = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return context;
	}
	    
	public static <T> T getBean(Class<T> beanClass) {
		return context.getBean(beanClass);
	}

}
