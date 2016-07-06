package it.carmelolagamba.esmarket.controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AOPLogger {

	private static final Logger logger = LoggerFactory.getLogger(AOPLogger.class);

	@Pointcut("execution(* it.carmelolagamba.esmarket.service.*.*(..))")
	public void beforeAnyMethod() {

	}

	@Before(value = "beforeAnyMethod()", argNames = "joinPoint")
	public void writeOnLogger(JoinPoint joinPoint) {
		logger.info("called {}", joinPoint.getSignature().toShortString());
	}

}

