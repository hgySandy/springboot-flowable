package com.home.demo.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;


@Entity
@Data
public class Person implements Serializable{

	private static final long serialVersionUID = 3748319103456028815L;

	@Id
    @GeneratedValue
    public Long id;

    private String username;

    private String firstName;

    private String lastName;

    private Date birthDate;

        
}
