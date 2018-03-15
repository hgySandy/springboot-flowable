package com.home.demo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.flowable.engine.ProcessEngines;

public class ProcessEnginesServletContextListener  implements ServletContextListener {
		@Override
	  public void contextInitialized(ServletContextEvent servletContextEvent) {
	    ProcessEngines.init();
	  }
		@Override
	  public void contextDestroyed(ServletContextEvent servletContextEvent) {
	    ProcessEngines.destroy();
	  }
}
