package com.azh.springpostprocessor.service;

import com.azh.springpostprocessor.dto.MyJavaBeanFactoryPostProcessorDto;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanDefinitionRegistryPostProcessor.postProcessBeanFactory 调用 MyBeanDefinitionRegistryPostProcessor 的 postProcessBeanFactory ");
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("MyBeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry 调用 MyBeanDefinitionRegistryPostProcessor 的 postProcessBeanDefinitionRegistry ");
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(MyBeanDefinitionRegistryPostProcessorOrdered.class);
		beanDefinitionBuilder.addPropertyValue("name", "MyBeanDefinitionRegistryPostProcessorOrdered");
		registry.registerBeanDefinition("myBeanDefinitionRegistryPostProcessorOrdered", beanDefinitionBuilder.getBeanDefinition());
	}
}
