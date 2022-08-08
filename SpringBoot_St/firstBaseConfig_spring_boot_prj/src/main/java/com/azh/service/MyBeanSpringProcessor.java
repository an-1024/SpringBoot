package com.azh.service;

import com.alibaba.fastjson.JSON;
import com.azh.dto.CarAnnotationDto;
import com.azh.dto.UserAnnotationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Slf4j
public class MyBeanSpringProcessor implements BeanPostProcessor {

	@Autowired
	private UserAnnotationDto userAnnotationDto;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if((bean instanceof UserAnnotationDto) || (bean instanceof CarAnnotationDto)) {
			log.info("before----------------------{}", JSON.toJSONString(userAnnotationDto));
			log.info("before----------------------{}", JSON.toJSONString(bean));
			log.info("before----------------------{}", beanName);
		}
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if((bean instanceof UserAnnotationDto) || (bean instanceof CarAnnotationDto)) {
			log.info("after----------------------{}", JSON.toJSONString(userAnnotationDto));
			log.info("after----------------------{}", JSON.toJSONString(bean));
			log.info("after----------------------{}", beanName);
		}
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
}
