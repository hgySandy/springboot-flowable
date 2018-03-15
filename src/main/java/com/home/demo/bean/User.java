package com.home.demo.bean;

import java.util.List;

import lombok.Data;
@Data
public class User {
	
    private String id;
    private String username;
    private String password;

    private List<Role> roles;


}