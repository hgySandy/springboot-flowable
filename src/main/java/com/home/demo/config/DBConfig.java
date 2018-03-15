package com.home.demo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
//配置类千万不要忘了这个注解
@Configuration
//扫描 Mapper 接口并容器管理
@MapperScan(basePackages = DBConfig.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory")
public class DBConfig {

	// 精确到 spring 目录，以便跟其他数据源隔离
	static final String PACKAGE = "com.home.demo.dao";
	static final String MAPPER_LOCATION = "classpath:mybatis/*.xml";
	
	//方案二  通过@Value注入
	@Value("${mysql.flowable.url}")
	private String url;
	@Value("${mysql.flowable.driverClassName}")
	private String driverClassName;
	@Value("${mysql.flowable.username}")
	private String username;
	@Value("${mysql.flowable.password}")
	private String password;
	
	
	@Bean(name = "dataSource")
	//@Primary //用@Primary 告诉spring 在犹豫的时候优先选择哪一个具体的实现(有多个实现的时候)
	public DataSource dataSource() {
		System.out.println(driverClassName);
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	
	@Bean(name = "transactionManager")
	@Primary
	public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean(name = "sqlSessionFactory")
	@Primary
	public SqlSessionFactory springSqlSessionFactory(
			@Qualifier("dataSource") DataSource dataSource,ApplicationContext applicationContext)
			throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
					  .getResources(DBConfig.MAPPER_LOCATION));
		//sessionFactory.setMapperLocations(applicationContext.getResources(DBConfig.MAPPER_LOCATION));
				
		return sessionFactory.getObject();
	}
	
	
    
}
