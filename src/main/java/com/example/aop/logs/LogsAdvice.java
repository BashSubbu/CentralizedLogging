package com.example.aop.logs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogsAdvice{
    Logger logger = LoggerFactory.getLogger(LogsAdvice.class);

    /**
     * to specify where the packages where this log should work
     */
    @Pointcut(value = "execution(* com.example.aop.*.*.*(..))")
    public void myPointCut(){
        // to specify the level of logger needs to implement
    }

    /**
     * applicationLogger is used to get method class,method,argument and response details
     * log them
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("myPointCut()")
    public Object applicationLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = null;
        String className = null;
        Object[] arguments = null;
        ObjectMapper objectMapper = null;
        Object response = null;
        String argumentsString = null;
        String responseString = null;

        objectMapper = new ObjectMapper();
        methodName = proceedingJoinPoint.getSignature().getName();
        className = proceedingJoinPoint.getTarget().getClass().toString();
        arguments = proceedingJoinPoint.getArgs();
        argumentsString = objectMapper.writeValueAsString(arguments);
        logger.info("class Invoked {} ", className);
        logger.info("method Invoked {} ", methodName);
        logger.info("arguments {} " ,argumentsString);
        // before
        response = proceedingJoinPoint.proceed();
        //after
        responseString = objectMapper.writeValueAsString(response);
        logger.info("class {} ", className);
        logger.info("method {} ", methodName);
        logger.info("response {} " ,responseString);
        return response;
    }
}
