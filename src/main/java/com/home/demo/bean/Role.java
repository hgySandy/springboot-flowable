package com.home.demo.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class Role implements Serializable{

	private static final long serialVersionUID = 3812467431210971600L;
	private String id;
    private String name;

}
