package com.home.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.home.demo.bean.User;
import com.home.demo.service.UserService;
import com.home.demo.util.Result;
import com.home.demo.util.UserInfo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/User")
public class UserController {
	@Autowired
	private UserService userService;
	
	    /**
	     * 默认显示员工列表
	     */
		@ApiOperation(value = "获取员工列表", notes = "获取员工列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	    @RequestMapping(value = "",method=RequestMethod.GET)
	    public Result index(HttpServletRequest request){
			System.out.println("当前用户为: " + UserInfo.getPrincipal()); 
	        return userService.findAll();
	    }
		
	    /**
	     * 查看员工详细信息
	     */
		@ApiOperation(value = "获取指定id的员工", notes = "获取指定id的员工", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	    @RequestMapping(value = "/id/{id}",method=RequestMethod.GET)
	    public Result viewUser(@PathVariable("id")int id){
	    		return userService.findById(id);
	    }
		
		
	    /**
	     * 查看员工详细信息
	     */
		@ApiOperation(value = "获取指定id的员工", notes = "获取指定id的员工", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	    @RequestMapping(value = "/username/{username}",method=RequestMethod.GET)
	    public Result findByUserName(@PathVariable("username")String username){
	    		return userService.findByUserName(username);
	    }
		
	    /**
	     * 新增员工
	     */
		@ApiOperation(value = "创建一个新的员工", notes = "创建一个新的员工", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	    @RequestMapping(value = "",method=RequestMethod.POST)
	    public Result addUser(User user){
	    		return userService.save(user);
	    }
	    /**
	     * 更新员工
	     */
		@ApiOperation(value = "更新指定id的员工", notes = "更新指定id的员工", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
	    @RequestMapping(value = "/{id}",method=RequestMethod.PUT)
	    public Result modifyUser(@PathVariable("id")int id,User user){
	    		return userService.modify(user);
	    }
	    /**
	     * 删除员工
	     */
		@ApiOperation(value = "删除指定id的员工", notes = "删除指定id的员工", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
	    @RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
	    public Result deleteUser(@PathVariable("id")int id){
			return userService.deleteById(id);
	    }
	     
}
