package com.azh.configannotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.azh.configannotation.beanclass")
public class MyComponentScan {

	@ComponentScan("com.azh.configannotation.beanclass")
	@Configuration
	class InnerClass{}
}
