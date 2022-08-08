package com.anzhi.testspringpostprocessor;

import com.azh.springpostprocessor.config.BeanConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanAnnotationPostProcessorTest {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
	}
}
