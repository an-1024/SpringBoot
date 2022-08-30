package com.azh.springpostprocessor.service;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MySpringFactoryBeanContext extends ClassPathXmlApplicationContext {
	public MySpringFactoryBeanContext(String ...val) {
		super(val);
	}

	@Override
	protected void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {
		// 可以不同过配置 XML 的方式实现添加实现 BeanFactoryPostProcessor 的接口
		super.addBeanFactoryPostProcessor(new MyBeanFactoryPostProcessor());
		super.addBeanFactoryPostProcessor(new MyBeanDefinitionRegistryPostProcessor());
		super.setAllowBeanDefinitionOverriding(false);
		super.setAllowCircularReferences(true);
		super.customizeBeanFactory(beanFactory);
	}
}
