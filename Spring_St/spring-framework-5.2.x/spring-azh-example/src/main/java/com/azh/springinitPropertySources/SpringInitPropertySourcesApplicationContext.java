package com.azh.springinitPropertySources;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringInitPropertySourcesApplicationContext extends ClassPathXmlApplicationContext {

	public SpringInitPropertySourcesApplicationContext(String ...config){
		super(config);
	}

	@Override
	protected void initPropertySources() {
		System.out.println("子类扩展 initPropertySources");
		getEnvironment().setRequiredProperties("username");
	}
}
