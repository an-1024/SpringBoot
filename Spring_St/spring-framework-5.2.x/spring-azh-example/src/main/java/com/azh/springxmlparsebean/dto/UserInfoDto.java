package com.azh.springxmlparsebean.dto;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class UserInfoDto extends BaseInfoDto implements BeanPostProcessor {

	private String userRemark;

	private String userDesc;

	public UserInfoDto() {
	}

	public String getUserRemark() {
		return userRemark;
	}

	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}


	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("UserInfoDto.postProcessBeforeInitialization start execute");
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("UserInfoDto.postProcessAfterInitialization start execute");
		return bean;
	}

	public void initMethod(){
		System.out.println("UserInfoDto.initMethod start run");
	}
}
