package com.azh.springpostprocessor.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.PriorityOrdered;

public class MyBeanDefinitionRegistryPostProcessorPriorityOrdered implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanDefinitionRegistryPostProcessorPriorityOrdered.postProcessBeanFactory 调用 MyBeanDefinitionRegistryPostProcessorPriorityOrdered 的 postProcessBeanFactory ");
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("MyBeanDefinitionRegistryPostProcessorPriorityOrdered.postProcessBeanDefinitionRegistry 调用 MyBeanDefinitionRegistryPostProcessorPriorityOrdered 的 postProcessBeanDefinitionRegistry ");
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(MyBeanDefinitionRegistryPostProcessorAddByPriorityOrderedPostProcessor.class);
		beanDefinitionBuilder.addPropertyValue("name", "MyBeanDefinitionRegistryPostProcessorAddByPriorityOrderedPostProcessor");
		registry.registerBeanDefinition("myBeanDefinitionRegistryPostProcessorAddByPriorityOrderedPostProcessor", beanDefinitionBuilder.getBeanDefinition());
	}

	@Override
	public int getOrder() {
		return 0;
	}
}
