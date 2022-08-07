package com.azh.springpostprocessor.service;

import com.alibaba.fastjson.JSON;
import com.azh.springpostprocessor.dto.MyJavaBeanPostProcessorDto;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof  MyJavaBeanPostProcessorDto) {
			System.out.println("1.MyBeanPostProcessor.postProcessBeforeInitialization 初始化前 myJavaBeanPostProcessorDto 的值：" + JSON.toJSONString(bean));
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof MyJavaBeanPostProcessorDto) {
			System.out.println("3.MyBeanPostProcessor.postProcessBeforeInitialization 初始化后 myJavaBeanPostProcessorDto 的值：" + JSON.toJSONString(bean));
		}
		return bean;
	}
}
