package com.azh.springinitPropertySources;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringInitPropertySourcesApplicationContext extends ClassPathXmlApplicationContext {

	public SpringInitPropertySourcesApplicationContext(String ...config){
		super(config);
	}

	@Override
	protected void initPropertySources() {
		System.out.println("子类扩展 initPropertySources");
		getEnvironment().setRequiredProperties("user");
	}

	@Override
	protected void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {
		super.setAllowBeanDefinitionOverriding(true);
		super.setAllowCircularReferences(false);
		super.customizeBeanFactory(beanFactory);
	}

	@Override
	public void setId(String id) {
		super.setId(id);
	}


}
