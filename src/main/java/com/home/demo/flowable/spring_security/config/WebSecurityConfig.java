package com.home.demo.flowable.spring_security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.home.demo.service.impl.UserDetailsServiceImpl;
import com.home.demo.service.impl.MyFilterSecurityInterceptor;


@Configuration
@EnableWebSecurity
/**
 *  prePostEnabled :决定Spring Security的前注解是否可用 [@PreAuthorize,@PostAuthorize,..] 
	secureEnabled : 决定是否Spring Security的保障注解 [@Secured] 是否可用
	jsr250Enabled ：决定 JSR-250 annotations 注解[@RolesAllowed..] 是否可用.
 * */
@EnableGlobalMethodSecurity(prePostEnabled = true)  //开启Spring方法级安全
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private DataSource dataSource;
    

    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Bean
    UserDetailsService customUserService(){ //注册UserDetailsService 的bean
        return new UserDetailsServiceImpl();
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()); //user Details Service验证

    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsManager());
    }
    
    @Bean
    public JdbcUserDetailsManager userDetailsManager() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setDataSource(dataSource);
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
	    	http.authorizeRequests()
	        .anyRequest().authenticated() //任何请求,登录后可以访问
	        .and()
	        .formLogin()
	        .loginPage("/login")
	        .failureUrl("/login?error")
	        .permitAll() //登录页面用户任意访问
	        .and()
	        .logout().permitAll(); //注销行为任意访问
	    	http.csrf().disable();//在原本的配置文件下添加这行代码，禁用security的csrf 
	    	http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
        
    }
    
    
    

}