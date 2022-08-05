package com.azh.springxmlparsebean;

import com.azh.springxmlparsebean.dto.UserInfoDto;
import com.azh.springxmlparsebean.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringXmlParseApplication {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
		UserService service = (UserService) context.getBean("userService");
		UserInfoDto userInfoDto = (UserInfoDto) context.getBean("userInfoDto");

		System.out.println(service.getName("An", "ZH"));
	}
}
