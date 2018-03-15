package com.home.demo.test;

import java.util.logging.Logger;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class SendRejectionMail implements JavaDelegate{

	private final Logger log = Logger.getLogger(CallExternalSystemDelegate.class.getName());  
	@Override
	public void execute(DelegateExecution execution) {
		System.out.println("Sorry,you have been rejected!");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  
        log.info("variavles=" + execution.getVariables());  
        log.info("Calling the external system for employee: "
				+execution.getVariable("employee")+"您好，您的假期未获批准！");
	}
	
}
