package com.azh.springannotationprocessor;

import com.alibaba.fastjson.JSON;
import com.azh.SpringBootConfigFileApplication;
import com.azh.config.BeanPostProcessorConfig;
import com.azh.dto.CarAnnotationDto;
import com.azh.dto.MyBeanSpringProcessorDto;
import com.azh.dto.UserAnnotationDto;
import com.azh.dto.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootConfigFileApplication.class})
public class SpringAnnotationPostProcessor {

    @Autowired
    @Lazy
    private UserVO user;

    @Autowired
    private CarAnnotationDto carAnnotationDto;

    @Test
    public void SpringAnnotationPostProcessorTest(){
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);
        UserAnnotationDto userAnnotation = context.getBean(UserAnnotationDto.class);
        userAnnotation.aspectAop();

        MyBeanSpringProcessorDto myBeanSpringProcessor = context.getBean(MyBeanSpringProcessorDto.class);
        myBeanSpringProcessor.aspectAop();


        CarAnnotationDto carAnnotation = context.getBean(CarAnnotationDto.class);
        carAnnotation.aspectAop();

        log.info("-------------------{}", JSON.toJSONString(user));
        user.aspectAop();

        carAnnotationDto.aspectAop();
    }
}
