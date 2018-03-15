package com.home.demo.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.demo.bean.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByUsername(String username);
}
