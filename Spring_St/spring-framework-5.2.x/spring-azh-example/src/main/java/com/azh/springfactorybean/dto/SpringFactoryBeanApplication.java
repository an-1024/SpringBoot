package com.azh.springfactorybean.dto;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringFactoryBeanApplication extends ClassPathXmlApplicationContext {
	public static void main(String[] args) {
		MySpringFactoryBeanContext mySpringFactoryBeanContext = new MySpringFactoryBeanContext("classpath:spring-customproperties.xml");
	}
}
