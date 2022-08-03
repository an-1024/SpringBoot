package com.azh.springbeanfactorypostprocessor.dto;

import org.springframework.beans.factory.InitializingBean;

public class MyJavaBeanDto implements InitializingBean {
	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 备注
	 */
	private String remark;

	public MyJavaBeanDto() {
	}

	public MyJavaBeanDto(String name, String remark) {
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

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("MyJavaBeanDto.afterPropertiesSet 实例化后执行");
	}

	public void MyJavaBeanDtoInitMethod (){
		System.out.println("MyJavaBeanDto.MyJavaBeanDtoInitMethod 执行指定的初始化方法");
	}
}
