package com.home.demo.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class Permission implements Serializable{
	
	private static final long serialVersionUID = -4094427471622023406L;
	private String id;
    /**权限名称*/
    private String name;
    //权限描述
    private String descritpion;
    //授权链接
    private String url;
    //父节点id
    private String pid;

    
}
