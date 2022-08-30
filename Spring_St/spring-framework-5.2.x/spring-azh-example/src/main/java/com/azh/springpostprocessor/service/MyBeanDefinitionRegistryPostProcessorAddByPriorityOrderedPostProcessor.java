package com.azh.springpostprocessor.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

public class MyBeanDefinitionRegistryPostProcessorAddByPriorityOrderedPostProcessor implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanDefinitionRegistryPostProcessor.postProcessBeanFactory 调用 MyBeanDefinitionRegistryPostProcessor 的 postProcessBeanFactory ");
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("MyBeanDefinitionRegistryPostProcessorAddByOrderedPostProcessor.postProcessBeanDefinitionRegistry 调用 MyBeanDefinitionRegistryPostProcessorAddByOrderedPostProcessor 的 postProcessBeanDefinitionRegistry ");
	}
}
