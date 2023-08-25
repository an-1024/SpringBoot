package com.azh.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class BeanPostProcessorAspect {
    @Before("execution(* com.azh.dto.MyBeanSpringProcessorDto.aspectAop(..))")
    public void beforeBeanPostProcessorConfig(JoinPoint joinPoint){
       log.info("beforeBeanPostProcessorConfig----------------{}", JSON.toJSONString(joinPoint.getThis()));
    }


    @Before("execution(* com.azh.dto.UserAnnotationDto.aspectAop(..))")
    public void beforeUserAnnotationDto(JoinPoint joinPoint){
        log.info("beforeUserAnnotationDto----------------{}", JSON.toJSONString(joinPoint.getThis()));
    }

    @Before("execution(* com.azh.dto.User.aspectAop(..))")
    public void beforeUser(JoinPoint joinPoint){
        log.info("beforeUser----------------{}", JSON.toJSONString(joinPoint.getThis()));
    }


    @Before("execution(* com.azh.dto.CarAnnotationDto.aspectAop(..))")
    public void beforeCarAnnotationDto(JoinPoint joinPoint){
        log.info("beforeCarAnnotationDto----------------{}", JSON.toJSONString(joinPoint.getThis()));
    }
}
