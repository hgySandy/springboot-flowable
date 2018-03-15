package com.home.demo.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket createRestApi() {
        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                Class<?> declaringClass = input.declaringClass();
                // 排除
                if (declaringClass == BasicErrorController.class) {
                		return false;
                }
                // 被注解的类
                if(declaringClass.isAnnotationPresent(RestController.class)) {
                		return true;
                }
                // 被注解的方法
                if(input.isAnnotatedWith(ResponseBody.class)) {
                		return true;
                }
                return false;
            }
        };
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(predicate)
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("flowable-springboot-swagger-restful")//大标题
            .version("1.0")//版本
            .build();
    }
	

    /**
     * 可以定义多个组，比如本类中定义把test和demo区分开了
     * （访问页面就可以看到效果了） 
     */
    @Bean
    public Docket testApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("test")
                .genericModelSubstitutes(DeferredResult.class)
//              .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(or(regex("/api/.*")))//过滤的接口
                .build()
                .apiInfo(testApiInfo());
    }

    @Bean
    public Docket demoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("demo")
                .genericModelSubstitutes(DeferredResult.class)
//              .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .paths(or(regex("/demo/.*")))//过滤的接口
                .build()
                .apiInfo(demoApiInfo());
    }

    private ApiInfo testApiInfo() {
        return new ApiInfoBuilder()
            .title("Electronic Health Record(EHR) Platform API")//大标题
            .description("EHR Platform's REST API, all the applications could access the Object model data via JSON.")//详细描述
            .version("1.0")//版本
            .termsOfServiceUrl("NO terms of service")
           //.contact(new Contact("小单", "http://blog.csdn.net/catoop", "365384722@qq.com"))//作者
            .license("The Apache License, Version 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .build();
    }

    private ApiInfo demoApiInfo() {
        return new ApiInfoBuilder()
            .title("Electronic Health Record(EHR) Platform API")//大标题
            .description("EHR Platform's REST API, all the applications could access the Object model data via JSON.")//详细描述
            .version("1.0")//版本
            .termsOfServiceUrl("NO terms of service")
            .contact(new Contact("小单", "http://blog.csdn.net/catoop", "365384722@qq.com"))//作者
            //.license("The Apache License, Version 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .build();
    }
    
//    private SpringSwaggerConfig springSwaggerConfig;
//
//    @Autowired
//    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
//        this.springSwaggerConfig = springSwaggerConfig;
//    }
//
//    /**
//     * 链式编程 来定制API样式 后续会加上分组信息
//     * 
//     * @return
//     */
//    @Bean
//    public SwaggerSpringMvcPlugin customImplementation(){
//           return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
//                    .apiInfo(apiInfo())
//                    .includePatterns(".*?");
//    }
//
//    private ApiInfo apiInfo() {
//        ApiInfo apiInfo = new ApiInfo("API接口测试平台",
//                "提供后台所有Restful接口", "www.flyeast.top",
//                "shexd1001@gmail.com", "β客栈", "www.flyeast.top");
//        return apiInfo;
//    }
//
//    @Override
//    public void configureDefaultServletHandling(
//            DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }


}
