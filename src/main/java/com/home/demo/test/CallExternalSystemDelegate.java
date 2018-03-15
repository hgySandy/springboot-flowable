package com.home.demo.test;

import java.util.logging.Logger;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class CallExternalSystemDelegate implements JavaDelegate{
	private final Logger log = Logger.getLogger(CallExternalSystemDelegate.class.getName());  
	@Override
	public void execute(DelegateExecution execution) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  
        log.info("variavles=" + execution.getVariables());  
        log.info("Calling the external system for employee: "
				+execution.getVariable("employee")+"您好，您的假期已经批准！");   
		
	}

}
