package com.mycompany.app.aspectvalidation;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.mycompany.app.account.SavingsAccount;

@Aspect
@Component
public class ServiceValidationAspect {
	 Logger logger=Logger.getLogger(ServiceValidationAspect.class.getName());
	 
	 @Around("execution(* com.mycompany.app.service.SavingsAccountServiceImpl.withdraw(..))")
	 public void withDraw(ProceedingJoinPoint pjp)throws Throwable{
		 Object[] params=pjp.getArgs();
		 SavingsAccount account=(SavingsAccount)params[0];
		double currentBalance=account.getBankAccount().getAccountBalance();
		 if ((Double)params[1]> 0 && currentBalance>(Double)params[1]) {
			 logger.info("amount is withdrawn");
			 pjp.proceed();
		 }
		 else {
			 logger.info("Invalid amount or Insufficient funds");
			 }
	 }
	 
	 
	 @Around("execution(* com.mycompany.app.service.SavingsAccountServiceImpl.deposit(..))")
	 public void deposit(ProceedingJoinPoint pjp)throws Throwable{
		 Object[] params=pjp.getArgs();
		 System.out.println(params[1]);
		 if((Double)params[1]>0)
		 {
			 logger.info("amount is deposited");
			 pjp.proceed();
		 }
		 else
		 {
			 logger.info("Invalid input amount");
		 }
	}
}
