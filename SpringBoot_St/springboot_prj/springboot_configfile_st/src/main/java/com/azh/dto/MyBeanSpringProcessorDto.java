package com.azh.dto;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Slf4j
public class MyBeanSpringProcessorDto implements BeanPostProcessor {


	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if((bean instanceof UserAnnotationDto) || (bean instanceof CarAnnotationDto)) {
			log.info("before----------------------{}", JSON.toJSONString(bean));
			log.info("before----------------------{}", beanName);
		}
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if((bean instanceof UserAnnotationDto) || (bean instanceof CarAnnotationDto)) {
			log.info("after----------------------{}", JSON.toJSONString(bean));
			log.info("after----------------------{}", beanName);
		}
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}

	public void aspectAop(){
		log.info("MyBeanSpringProcessorDto log");
	}
}
