package com.azh.configannotation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;

@Configuration
@ComponentScan("com.azh.configannotation.beanclass")
@PropertySource(value = "classpath:db.properties", encoding = "UTF-8")
@Order(1)
public class MyComponentScan {

	@Value("${mysql.name}")
	private String name;

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
