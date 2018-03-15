package com.home.demo.service;

import com.home.demo.bean.Vacation;
import com.home.demo.util.Result;

public interface HolidayService {

	Result findAll();

	Result findById(String taskId);

	Result deployProcess();

	Result save(Vacation vacation);

	Result modify(String taskId, boolean isApproved, String comments);

	Result findByGroupName(String groupName);

	Result findByAssignee(String assignee);

}
