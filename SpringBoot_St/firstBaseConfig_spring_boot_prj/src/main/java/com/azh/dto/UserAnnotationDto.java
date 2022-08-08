package com.azh.dto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserAnnotationDto {
	private String name;

	private int age;

	public UserAnnotationDto() {
	}

	public UserAnnotationDto(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}


	public void aspectAop(){
		log.info("UserAnnotationDto log");
	}
}
