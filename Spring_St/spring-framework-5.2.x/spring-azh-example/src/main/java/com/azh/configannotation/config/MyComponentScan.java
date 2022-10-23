package com.azh.configannotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@ComponentScan("com.azh.configannotation.beanclass")
@Order(1)
public class MyComponentScan {

	private String name = "测试";

	@ComponentScan("com.azh.configannotation.beanclass")
	@Configuration
	class InnerClass{}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
