package com.home.demo.config;

import javax.sql.DataSource;

import org.flowable.engine.HistoryService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ManagementService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.idm.engine.IdmEngineConfiguration;
import org.flowable.idm.engine.impl.cfg.StandaloneIdmEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

@Configuration
public class ProcessEngineConfig {
	
    //方案三  注入environment  通过environment获取  
    @Autowired  
    private Environment environment;  
    
    public void init(){  
        //RelaxedPropertyResolver propertyResolver = 
        		new RelaxedPropertyResolver(environment, "mysql.flowable.");  
        //String url = environment.getProperty("url");  
    } 
	
	@Bean(name = "idmEngineConfiguration")
	@Primary
	public IdmEngineConfiguration idmEngineConfiguration(@Qualifier("dataSource") DataSource dataSource) {
		IdmEngineConfiguration idmEngineConfiguration = new StandaloneIdmEngineConfiguration();
		idmEngineConfiguration.setDataSource(dataSource);
		return idmEngineConfiguration;
	}
	

	
	@Bean(name = "processEngineConfiguration")
	@Primary
	public ProcessEngineConfiguration processEngineConfiguration(@Qualifier("dataSource") DataSource dataSource) {
		ProcessEngineConfiguration cfg = 
				new StandaloneProcessEngineConfiguration()
					.setDataSource(dataSource)              
	                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
//                .setAsyncExecutorActivate( Boolean.valueOf(environment.getProperty("asyncExecutorActivate")))
//                .setMailServerHost( environment.getProperty("mailServerHost"))
//                .setMailServerPort( Integer.valueOf(environment.getProperty("mailServerPort")));
		
		return cfg;
	}
	
	@Bean(name = "processEngine")
	@Primary
	public ProcessEngine processEngine(
			@Qualifier("processEngineConfiguration") ProcessEngineConfiguration processEngineConfiguration)
			throws Exception {
		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
		return processEngine;
	}
	
	@Bean(name = "repositoryService")
	@Primary
	public RepositoryService repositoryService(
			@Qualifier("processEngine") ProcessEngine processEngine)
					throws Exception {
		return processEngine.getRepositoryService();
	}
	
	@Bean(name = "runtimeService")
	@Primary
	public RuntimeService runtimeService(
			@Qualifier("processEngine") ProcessEngine processEngine)
					throws Exception {
		return processEngine.getRuntimeService();
	}
	
	
	@Bean(name = "taskService")
	@Primary
	public TaskService taskService(
			@Qualifier("processEngine") ProcessEngine processEngine)
			throws Exception {
		return processEngine.getTaskService();
	}
	
	@Bean(name = "historyService")
	@Primary
	public HistoryService historyService(
			@Qualifier("processEngine") ProcessEngine processEngine)
					throws Exception {
		return processEngine.getHistoryService();
	}
	
	@Bean(name = "managementService")
	@Primary
	public ManagementService managementService(
			@Qualifier("processEngine") ProcessEngine processEngine)
					throws Exception {
		return processEngine.getManagementService();
	}
	
	@Bean(name = "identityService")
	@Primary
	public IdentityService identityService(
			@Qualifier("processEngine") ProcessEngine processEngine)
					throws Exception {
		return processEngine.getIdentityService();
	}
	
}
