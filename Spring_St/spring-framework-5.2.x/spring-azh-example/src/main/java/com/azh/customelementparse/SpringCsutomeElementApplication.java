package com.azh.customelementparse;

import com.azh.customelementparse.dto.CustomElementAzh;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCsutomeElementApplication {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-customeele-dev.xml");
		CustomElementAzh bean = (CustomElementAzh) applicationContext.getBean("myCustomElementAzh");
		System.out.println("custom-------------getName() = " + bean.toString());
	}
}
