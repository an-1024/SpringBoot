package com.azh.config;

import com.azh.dto.UserAnnotationDto;
import com.azh.dto.CarAnnotationDto;
import com.azh.dto.MyBeanSpringProcessorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@Slf4j
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
	public MyBeanSpringProcessorDto myBeanSpringProcessor(){
		return new MyBeanSpringProcessorDto();
	}
}
