package com.example.demo.logger;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class MyLogger {

	
	private static final Logger log = LoggerFactory.getLogger(MyLogger.class);

	
	@Before("execution (* *.save(..))")
	public void avant()
	{
		log.info("avant l appel a une methode");
	}
	
	@After("execution (* *.save(..))")
	public void apres()
	{
		log.info("apes l appel a une methode");
	}
	
	
	@Before("execution (* com.example.demo.metier.ICatalogue.*(..))")
	public void avantx(JoinPoint jp)
	{
		
		System.out.println( jp.getSignature().getName());
		System.out.println( jp.getSignature());
		
		log.info("avant l appel a une methode de icatalogue");
	}
	
	@AfterReturning(pointcut = "execution (* com.example.demo.metier.ICatalogue.*(..))", returning = "res")
	public void aprerreturn(Object res)
	{
		log.info(res.toString());
	}
	
	@AfterThrowing(pointcut = "execution (* com.example.demo.metier.ICatalogue.*(..))", throwing =  "e")
	public void aprerreturn(Exception e)
	{
		log.info(e.getMessage());
	}
}
