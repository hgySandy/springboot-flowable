package com.home.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.demo.bean.User;
import com.home.demo.dao.UserDao;
import com.home.demo.service.UserService;
import com.home.demo.util.Result;
import com.home.demo.util.ResultGenerator;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;

   
	@Override
	public Result findAll() {
		try {
			return ResultGenerator.getSuccessResult(userDao.findAll());
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.getFailResult("获取员工列表失败！");
		}
	}

	
	@Override
	public Result findById(int id) {
		try {
			return ResultGenerator.getSuccessResult(userDao.findById(id));
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.getFailResult("获取员工信息失败！");
		}
		
	}


	@Override
	public Result save(User user) {
		try {
			userDao.save(user);
			return ResultGenerator.getSuccessResult();
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.getFailResult("保存员工信息失败！");
		}
		
	}

	@Override
	public Result modify(User user) {
		try {
			userDao.modify(user);
			return ResultGenerator.getSuccessResult();
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.getFailResult("修改员工信息失败！");
		}
		
	}

	@Override
	public Result deleteById(int id) {
		try {
			userDao.deleteById(id);
			return ResultGenerator.getSuccessResult();
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.getFailResult("删除员工信息失败！");
		}
	}

	@Override
	public Result findByUserName(String username) {
		try {
			return ResultGenerator.getSuccessResult(userDao.findByUserName(username));
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.getFailResult("获取员工信息失败！");
		}
	}

}
