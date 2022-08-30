package com.azh.springpostprocessor;

import com.azh.springpostprocessor.service.MySpringFactoryBeanContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringFactoryBeanApplication extends ClassPathXmlApplicationContext {
	public static void main(String[] args) {
		MySpringFactoryBeanContext mySpringFactoryBeanContext = new MySpringFactoryBeanContext("spring-beanFactoryPostProcessor.xml");
	}
}
