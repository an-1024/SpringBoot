package com.anzhi.springfactorybean;

import com.azh.springfactorybean.dto.FactoryBeanDto;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringFactoryBeanTest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
		Object factoryBeanDto = context.getBean("springFactoryBeanDto");
		if (factoryBeanDto instanceof  FactoryBeanDto){
			System.out.println("使用 beanFactory 创建对象");
		}
	}
}
