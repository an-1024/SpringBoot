package com.azh.configannotation;

import net.bytebuddy.implementation.bytecode.constant.ClassConstant;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConfigAnnotationApplication {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-configannotation.xml");
	}
}
