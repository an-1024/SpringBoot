package com.azh.springpostprocessor.service;

import com.azh.springpostprocessor.dto.MyJavaBeanPostProcessorDto;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof MyJavaBeanPostProcessorDto) {
			System.out.println("1. BeanPostProcessor，对象" + beanName + "调用初始化方法之前的数据： " + bean.toString());
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof MyJavaBeanPostProcessorDto) {
			System.out.println("4. BeanPostProcessor，对象" + beanName + "调用初始化方法之后的数据：" + bean.toString());
		}
		return bean;
	}
}
