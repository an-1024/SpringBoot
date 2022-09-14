package com.azh.springpostprocessor.service;

import org.springframework.stereotype.Component;

@Component
public class SpringComponentScan {
	// 测试使用 component-scan 标签是否可以可以识别注解，并将注解类注入到 Spring IOC 容器中
	public void testComponentScan(){
		System.out.println("已经扫描到注解类，并使调用相应的方法输出");
	}
}
