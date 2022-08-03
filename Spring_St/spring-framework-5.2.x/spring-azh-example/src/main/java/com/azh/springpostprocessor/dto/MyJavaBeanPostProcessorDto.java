package com.azh.springpostprocessor.dto;

import org.springframework.beans.factory.InitializingBean;

public class MyJavaBeanPostProcessorDto implements InitializingBean {
	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 备注
	 */
	private String remark;

	public MyJavaBeanPostProcessorDto() {
	}

	public MyJavaBeanPostProcessorDto(String name, String remark) {
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
     * 实例化后，在bean的属性初始化后执行
	 * @throws Exception
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("2. MyJavaBeanPostProcessorDto.afterPropertiesSet 实例化后，在bean的属性初始化后执行");
		this.remark = "MyJavaBeanPostProcessorDto.afterPropertiesSet 实例化后，在bean的属性初始化后执行";
	}

	/**
	 * bean 类的初始化方法
	 */
	public void MyJavaBeanPostProcessorDtoMethod (){
		System.out.println("3. MyJavaBeanPostProcessorDto.MyJavaBeanPostProcessorDtoMethod 执行指定的初始化方法");
	}
}
