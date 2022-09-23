package com.azh.springpostprocessor;

import com.azh.springpostprocessor.service.MySpringFactoryBeanContext;
import com.azh.springpostprocessor.service.SpringComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringFactoryPostProcessorBeanApplication extends ClassPathXmlApplicationContext {
	public static void main(String[] args) {
		MySpringFactoryBeanContext mySpringFactoryBeanContext = new MySpringFactoryBeanContext("spring-beanFactoryPostProcessor.xml");

		// 测试在 XML 使用 component-scan 是否可以扫描到注解类，debug 解析流程
		SpringComponentScan springComponentScan = (SpringComponentScan) mySpringFactoryBeanContext.getBean("springComponentScan");
		springComponentScan.testComponentScan();
	}
}
