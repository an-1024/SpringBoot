package com.azh.springpostprocessor.dto;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyJavaBeanFactoryPostProcessorDto implements BeanFactoryPostProcessor {
	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 备注
	 */
	private String remark;

	public MyJavaBeanFactoryPostProcessorDto() {
	}

	public MyJavaBeanFactoryPostProcessorDto(String name, String remark) {
		this.name = name;
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * bean 类的初始化方法
	 */
	public void MyJavaBeanFactoryPostProcessorDtoMethod (){
		System.out.println("MyJavaBeanDto.MyJavaBeanDtoInitMethod 执行指定的初始化方法");
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyJavaBeanDto.afterPropertiesSet 实例化后，在bean的属性初始化后执行");
		this.remark = "MyJavaBeanDto.afterPropertiesSet 实例化后，在bean的属性初始化后执行";

	}
}
