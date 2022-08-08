package com.azh.config;

import com.azh.dto.CarAnnotationDto;
import com.azh.dto.UserAnnotationDto;
import com.azh.service.MyBeanSpringProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class BeanPostProcessorConfig {
	@Bean
	public UserAnnotationDto userAnnotationDto(){
		return new UserAnnotationDto("test", 20);
	}

	@Bean
	public CarAnnotationDto carAnnotationDto(){
		return new CarAnnotationDto(12, new BigDecimal("25000.00"));
	}

	@Bean
	public MyBeanSpringProcessor myBeanSpringProcessor(){
		return new MyBeanSpringProcessor();
	}
}
