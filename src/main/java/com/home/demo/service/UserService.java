package com.home.demo.service;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import com.home.demo.bean.User;
import com.home.demo.util.Result;

public interface UserService {
	//@Secured({ "ROLE_USER", "ROLE_ADMIN" })   //同时拥有ADMIN & USER 。但是仅仅通过使用 @Secured注解是无法实现的。不支持Spring EL表达式
	@PreAuthorize("hasRole('ROLE_ADMIN') AND hasRole('ROLE_USER')") 
	Result findAll();
    
     //限制只能查询Id小于10的用户
     @PreAuthorize("#id==355001")
	Result findById(int id);
	
	//@PreAuthorize可以用来控制一个方法是否能够被调用,支持Spring EL
	//可以将登录用户的roles/permissions参数传到方法中
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	Result save(User user);
	
	//@Secured("ROLE_ADMIN")  //@Secured只有那些角色/权限的用户才可以调用该方法,不具备将会抛出AccessDenied 异常。
	@PreAuthorize("hasRole('ROLE_ADMIN')")  
	Result modify(User user);
	
	//@Secured("ROLE_ADMIN")  //@Secured只有那些角色/权限的用户才可以调用该方法,不具备将会抛出AccessDenied 异常。
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	Result deleteById(int id);
	
	//@PostAuthorize 注解使用并不多，在方法执行后再进行权限验证,适合验证带有返回值的权限
	//Spring EL 提供 返回对象能够在表达式语言中获取返回的对象returnObject 
	@PostAuthorize ("returnObject.type == authentication.name") 
	public Result findByUserName(String username);
}
