package com.azh.springinitPropertySources;

public class SpringApplication {
	public static void main(String[] args) {
		SpringInitPropertySourcesApplicationContext context = new SpringInitPropertySourcesApplicationContext("spring-config.xml");
	}
}
