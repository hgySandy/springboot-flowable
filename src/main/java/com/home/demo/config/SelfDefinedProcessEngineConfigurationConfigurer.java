package com.home.demo.config;

import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.stereotype.Component;

/**
 * 自定义配置器修改流程引擎配置类
 * @author hgy
 */
@Component
public class SelfDefinedProcessEngineConfigurationConfigurer 
				implements ProcessEngineConfigurationConfigurer{

	@Override
	public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {
		processEngineConfiguration.setActivityFontName("宋体");
		processEngineConfiguration.setLabelFontName("宋体");
		processEngineConfiguration.setAnnotationFontName("宋体");
		System.out.println("#############SelfDefinedProcessEngineConfigurationConfigurer#############");
		System.out.println(processEngineConfiguration.getActivityFontName());
		
	}

}
