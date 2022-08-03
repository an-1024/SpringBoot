package com.anzhi.testspringbeanfactorypostprocessor;

import com.azh.springbeanfactorypostprocessor.dto.MyJavaBeanDto;
import com.azh.springbeanfactorypostprocessor.service.MyBeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanFactoryPostProcessorTest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
		MyJavaBeanDto myJavaBeanDto = (MyJavaBeanDto) context.getBean("myJavaBeanDto");
		System.out.println("==================输出结果========================");
		System.out.println("SpringBeanFactoryPostProcessorTest.main 名称" + myJavaBeanDto.getName());
		System.out.println("SpringBeanFactoryPostProcessorTest.main 备注" + myJavaBeanDto.getRemark());
	}
}
