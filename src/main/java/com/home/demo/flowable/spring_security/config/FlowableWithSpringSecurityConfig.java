package com.home.demo.flowable.spring_security.config;

import org.flowable.idm.engine.IdmEngineConfiguration;
import org.flowable.idm.engine.impl.persistence.entity.data.impl.MybatisGroupDataManager;
import org.flowable.idm.engine.impl.persistence.entity.data.impl.MybatisUserDataManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

//@Configuration
public class FlowableWithSpringSecurityConfig {
    @Autowired
    private IdmEngineConfiguration idmEngineConfiguration;

    @Autowired
    private JdbcUserDetailsManager userManager;

    @Bean
    InitializingBean processEngineInitializer() {
        return new InitializingBean() {
        		@Override
            public void afterPropertiesSet() throws Exception {
	            	idmEngineConfiguration.setUserEntityManager(new SpringSecurityUserManager(idmEngineConfiguration, new MybatisUserDataManager(idmEngineConfiguration), userManager));
	            	idmEngineConfiguration.setGroupEntityManager(new SpringSecurityGroupManager(idmEngineConfiguration, new MybatisGroupDataManager(idmEngineConfiguration)));
            }
        };
    }
}
