package com.mycompany.app.service;

import java.util.logging.Logger;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationService {
	Logger logger=Logger.getLogger(ValidationService.class.getName());
	
	@Before("execution (* com.mycompany.app.service.SavingsAccountServiceImpl.createNewAccount(..))")
	public void createAccountBefore() {
		logger.info("Create Account");
	}
	
	@After("execution (* com.mycompany.app.service.SavingsAccountServiceImpl.createNewAccount(..))")
	public void createAccountAfter() {
		logger.info("Congrats!!! Account opened successfully");
	}
	


	
	
}
