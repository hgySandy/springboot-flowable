package com.home.demo.dao;

import java.util.List;


import com.home.demo.bean.User;

public interface UserDao {
	
	List<User> findAll();
	
	User findById(int id);
	
	void save(User user);
	
	void modify(User user);
	
	void deleteById(int id);
	
	public User findByUserName(String username);
}
