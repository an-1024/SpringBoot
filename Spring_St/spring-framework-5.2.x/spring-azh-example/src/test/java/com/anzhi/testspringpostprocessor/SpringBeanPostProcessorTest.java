package com.anzhi.testspringpostprocessor;

import com.azh.springpostprocessor.dto.MyJavaBeanFactoryPostProcessorDto;
import com.azh.springpostprocessor.dto.MyJavaBeanPostProcessorDto;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanPostProcessorTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
		MyJavaBeanPostProcessorDto myJavaBeanPostProcessorDto = (MyJavaBeanPostProcessorDto) context.getBean("myJavaBeanPostProcessorDto");
		System.out.println("==================输出结果========================");
		System.out.println("SpringBeanPostProcessorTest.main 名称" + myJavaBeanPostProcessorDto.getName());
		System.out.println("SpringBeanPostProcessorTest.main 备注" + myJavaBeanPostProcessorDto.getRemark());
	}
}
