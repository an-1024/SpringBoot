package com.azh.springbeanfactorypostprocessor.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanFactoryPostProcessor.postProcessBeanFactory 调用 MyBeanFactoryPostProcessor 的 postProcessBeanFactory ");
		BeanDefinition bd = beanFactory.getBeanDefinition("myJavaBeanDto");
		System.out.println("MyBeanFactoryPostProcessor.postProcessBeanFactory 属性值==========" + bd.getPropertyValues());
	}
}
