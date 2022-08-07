package com.azh.springpostprocessor.dto;

public class MyJavaBeanPostProcessorDto {
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
	 * bean 类的初始化方法
	 */
	public void MyJavaBeanPostProcessorDtoMethod (){
		System.out.println("2.MyJavaBeanPostProcessorDto.MyJavaBeanPostProcessorDtoMethod 执行指定的初始化方法");
	}
}
