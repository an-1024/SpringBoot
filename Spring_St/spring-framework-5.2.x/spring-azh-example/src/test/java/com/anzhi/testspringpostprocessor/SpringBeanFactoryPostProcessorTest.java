package com.anzhi.testspringpostprocessor;

import com.azh.springpostprocessor.dto.MyJavaBeanFactoryPostProcessorDto;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanFactoryPostProcessorTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
		MyJavaBeanFactoryPostProcessorDto myJavaBeanFactoryPostProcessorDto = (MyJavaBeanFactoryPostProcessorDto) context.getBean("myBeanFactoryPostProcessor");
		System.out.println("==================输出结果========================");
		System.out.println("SpringBeanFactoryPostProcessorTest.main 名称" + myJavaBeanFactoryPostProcessorDto.getName());
		System.out.println("SpringBeanFactoryPostProcessorTest.main 备注" + myJavaBeanFactoryPostProcessorDto.getRemark());
	}
}
