package com.home.demo.bean;

import java.io.Serializable;

import lombok.Data;
@Data
public class TaskRepresentation implements Serializable{
	
	private static final long serialVersionUID = -6758602444720110075L;
	private String id;
    private String name;
    private String processInstanceId;

    public TaskRepresentation() {
        super();
    }

    public TaskRepresentation(String id, String name, String processInstanceId) {
        this.id = id;
        this.name = name;
        this.processInstanceId = processInstanceId;
    }


    

   
}
