package com.azh.springinitPropertySources;

public class SpringApplication {
	public static void main(String[] args) {
		// 需要配合设置 JVM 参数使用 -Denv=dev  -D 后面添加参数，指定变量名称
		SpringInitPropertySourcesApplicationContext context = new SpringInitPropertySourcesApplicationContext("spring-config-${env}.xml");
	}
}
