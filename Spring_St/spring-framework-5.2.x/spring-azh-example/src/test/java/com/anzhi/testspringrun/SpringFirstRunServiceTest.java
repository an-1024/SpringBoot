package com.anzhi.testspringrun;

import com.azh.testspringrun.service.SpringFirstRunService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringFirstRunServiceTest {
	@Test
	public void testSpringFirstService(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.refresh();
		context.register(SpringFirstRunService.class);

		SpringFirstRunService springFirstRunService = (SpringFirstRunService) context.getBean("springFirstRunService");
		springFirstRunService.HelloMethod();
		System.out.println("SpringFirstRunServiceTest.testSpringFirstService run end");
	}
}
