package com.home.demo.controller;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.home.demo.bean.Msg;
import com.home.demo.util.UserInfo;


@Controller
public class HomeController {
	
    @RequestMapping("/toApi")
    public String toApi(){
        return "redirect:swagger-ui.html";
    }
	    
    @RequestMapping("/")
    public String index(Model model){
	    	Collection<? extends GrantedAuthority> authorities = UserInfo.getAuthorities();
	    	StringBuilder sb = new StringBuilder();
	    	for(GrantedAuthority authority : authorities) {
	    		sb.append(authority.getAuthority()+ " ||  ");
	    	}
        Msg msg =  new Msg("测试标题","您好: "+UserInfo.getPrincipal()+", 您拥有的权限有："+ sb.toString(),"欢迎来到HOME页面");
        model.addAttribute("msg", msg);
        return "home";
    }
    
    @RequestMapping("/admin")
    @ResponseBody
    public String hello(){
        return "hello admin";
    }
    
}
