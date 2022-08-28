package com.azh.custombeanproperties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCustomBeanPropertiesApplication {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-customproperties.xml");
		MyCustomBean myCustomBean = (MyCustomBean) applicationContext.getBean("myCustomBean");
		System.out.println("out-------" + myCustomBean.toString());
	}
}
