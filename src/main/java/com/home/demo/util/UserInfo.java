package com.home.demo.util;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfo {
	   //获取用户名
	   public static String getPrincipal(){  
	        String userName = null;  
	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();  
	   
	        if (principal instanceof UserDetails) {  
	            userName = ((UserDetails)principal).getUsername();  
	        } else {  
	            userName = principal.toString();  
	        }  
	        return userName;  
	    }
	   //获取用户的权限
	   public static Collection<? extends GrantedAuthority> getAuthorities(){  
		    Collection<? extends GrantedAuthority> authorities = null;
		    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();  
	        if (principal instanceof UserDetails) {  
	        	UserDetails userDetails = (UserDetails)principal;
	            authorities = userDetails.getAuthorities();
	        } 
	        return authorities;  
	    }
	   
}
