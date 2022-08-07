package com.azh.springpostprocessor.dto;

import org.springframework.beans.factory.InitializingBean;

public class MyJavaBeanFactoryPostProcessorDto implements InitializingBean {
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
		System.out.println("MyJavaBeanFactoryPostProcessorDto.MyJavaBeanFactoryPostProcessorDtoMethod 执行指定的初始化方法");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("MyJavaBeanFactoryPostProcessorDto 初始化后执行");
	}
}
