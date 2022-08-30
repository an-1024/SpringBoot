package com.azh.springfactorybean;

import org.springframework.beans.factory.FactoryBean;

public class FactoryBeanDto {
	private String desc;
	private String remark;

	public FactoryBeanDto() {
	}

	public FactoryBeanDto(String desc, String remark) {
		this.desc = desc;
		this.remark = remark;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
