package com.home.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.home.demo.bean.Permission;
import com.home.demo.dao.PermissionDao;
import com.home.demo.dao.UserDao;

@Service
public class UserDetailsServiceImpl implements UserDetailsService { //自定义UserDetailsService 接口

    @Autowired
    UserDao userDao;
    @Autowired
    PermissionDao permissionDao;

//    public CustomUserService(UserDao userDao, PermissionDao permissionDao) {
//    		this.userDao = userDao;
//    		this.permissionDao = permissionDao;
//	}
    
    @Override
	public UserDetails loadUserByUsername(String username) {
	    	com.home.demo.bean.User user = userDao.findByUserName(username);
	        if (user != null) {
	            List<Permission> permissions = permissionDao.findByAdminUserId(user.getId());
	            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
	            for (Permission permission : permissions) {
	                if (permission != null && permission.getName()!=null) {
		                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
		                //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
		                grantedAuthorities.add(grantedAuthority);
	                }
	            }
	            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
	        } else {
	            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
	        }
    }

}
