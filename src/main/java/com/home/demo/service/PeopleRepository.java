package com.home.demo.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.home.demo.bean.People;

public interface PeopleRepository extends JpaRepository<People, Long> {
	
	@RestResource(path = "nameStartsWith", rel = "nameStartsWith")
    People findByNameStartsWith(@Param("name")String name);
    
}