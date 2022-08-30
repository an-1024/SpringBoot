package com.azh.springpostprocessor.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.Order;

public class MyBeanDefinitionRegistryPostProcessorOrdered implements BeanDefinitionRegistryPostProcessor, Ordered {

	private String userName;

	public String getName() {
		return userName;
	}

	public void setName(String name) {
		this.userName = name;
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanDefinitionRegistryPostProcessorOrdered.postProcessBeanFactory 调用 MyBeanDefinitionRegistryPostProcessorOrdered 的 postProcessBeanFactory ");
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("MyBeanDefinitionRegistryPostProcessorOrdered.postProcessBeanDefinitionRegistry 调用 MyBeanDefinitionRegistryPostProcessorOrdered 的 postProcessBeanDefinitionRegistry ");
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(MyBeanDefinitionRegistryPostProcessorPriorityOrdered.class);
		beanDefinitionBuilder.addPropertyValue("name", "MyBeanDefinitionRegistryPostProcessorOrdered");
		registry.registerBeanDefinition("myBeanDefinitionRegistryPostProcessorPriorityOrdered", beanDefinitionBuilder.getBeanDefinition());
	}

	@Override
	public int getOrder() {
		return 1;
	}
}
