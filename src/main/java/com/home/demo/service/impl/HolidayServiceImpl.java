package com.home.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.engine.IdentityService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.demo.bean.UserTask;
import com.home.demo.bean.Vacation;
import com.home.demo.service.HolidayService;
import com.home.demo.util.Result;
import com.home.demo.util.ResultGenerator;

@Service
public class HolidayServiceImpl implements HolidayService{
	@Autowired
	private TaskService taskService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private IdentityService identityService;
	
	//获取所有任务
	@Override
	public Result findAll() {
		try {
	    		//获取管理员要处理的任务
	    		List<Task> tasks = taskService.createTaskQuery().list();
	    		System.out.println("You have " + tasks.size() + " tasks:");
	    		List<UserTask> userTasks = new ArrayList<UserTask>();
	    		for (int i=0; i<tasks.size(); i++) {
	    			Map<String, Object> processVariables = taskService.getVariables(tasks.get(i).getId());
	    			String taskFrom = (String)processVariables.get("employeeName");
	    			userTasks.add(new UserTask(tasks.get(i).getId(),tasks.get(i).getName(),taskFrom,"the management group"));
	    		    System.out.println((i+1) + ") " + tasks.get(i).getName());
	    		}
			return ResultGenerator.getSuccessResult(userTasks);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.getFailResult("获取请假列表失败！");
		}
	}
	
	//获取管理员想要处理的任务
	@Override
	public Result findById(String taskId) {
		try {
	    		//询问管理员处理哪一个任务
	    		System.out.println("Which task would you like to complete?");
	    		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management")
	    							.taskId(taskId).list();
	    		if(tasks.size() == 0) {
	    			return ResultGenerator.getSuccessResult("No tasks!");
	    		}
	    		Task task = tasks.get(0);
	    		Map<String, Object> processVariables = taskService.getVariables(taskId);
	    		processVariables.put("taskId", taskId);
	    		processVariables.put("taskName", task.getName());
			return ResultGenerator.getSuccessResult(processVariables);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.getFailResult("获取请假信息失败！");
		}
		
	}
	//管理员处理任务
	@Override
	public Result modify(String taskId, boolean isApproved,String comments) {
		try {
			Map<String, Object> variables = new HashMap<String, Object>();
	    		variables.put("vacationApproved",isApproved);
	    		variables.put("comments",comments);
	    		taskService.complete(taskId,variables);
	    		variables.put("taskId",taskId);
			return ResultGenerator.getSuccessResult(variables);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.getFailResult("处理请假信息失败！");
		}
		
	}
	//创建请假申请
	@Override
	public Result save(Vacation vacation) {
		try {
	    		//传入实际参数
	    		Map<String, Object> variables = new HashMap<String, Object>();
	    		identityService.setAuthenticatedUserId(vacation.getEmployeeName());
	    		variables.put("employeeName", vacation.getEmployeeName());
	    		variables.put("startDate", vacation.getStartDate());
	    		variables.put("numberOfDays", vacation.getNumberOfDays());
	    		variables.put("reason", vacation.getReason());
	    		runtimeService.startProcessInstanceByKey("vacationRequest", variables);
			return ResultGenerator.getSuccessResult(variables);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.getFailResult("假期申请提交失败！");
		}
	}

	//部署请假流程
	@Override
	public Result deployProcess() {
	    Deployment deployment = repositoryService.createDeployment()
											      .addClasspathResource("processes/vacation-request.bpmn20.xml")
											      .deploy();
	    //查询流程定义
	    ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
												    		  .deploymentId(deployment.getId())
												    		  .singleResult();
	    Map<String, Object> result = new HashMap<String, Object>();
	    result.put("deploymentId", deployment.getId());
	    result.put("deploymentName", deployment.getName());
	    result.put("processDefinitionId", processDefinition.getId());
	    result.put("processDefinitionName", processDefinition.getName());
    		return ResultGenerator.getSuccessResult(result);
	}

	//根据用户所在权限组获取任务
	@Override
	public Result findByGroupName(String groupName) {
		try {
	    		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(groupName).list();
	    		System.out.println("You have " + tasks.size() + " tasks:");
	    		List<UserTask> userTasks = new ArrayList<UserTask>();
	    		for (int i=0; i<tasks.size(); i++) {
	    			Map<String, Object> processVariables = taskService.getVariables(tasks.get(i).getId());
	    			String taskFrom = (String)processVariables.get("employeeName");
	    			userTasks.add(new UserTask(tasks.get(i).getId(),tasks.get(i).getName(),taskFrom,"the management group"));
	    		    System.out.println((i+1) + ") " + tasks.get(i).getName());
	    		}
			return ResultGenerator.getSuccessResult(userTasks);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.getFailResult("获取请假信息失败！");
		}
	}

	@Override
	public Result findByAssignee(String assignee) {
		try {
	    		List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).
	    				taskName("Modify vacation request").list();
	    		System.out.println("You have " + tasks.size() + " tasks:");
	    		List<UserTask> userTasks = new ArrayList<UserTask>();
	    		for (int i=0; i<tasks.size(); i++) {
	    			Map<String, Object> processVariables = taskService.getVariables(tasks.get(i).getId());
	    			String taskFrom = (String)processVariables.get("employeeName");
	    			userTasks.add(new UserTask(tasks.get(i).getId(),tasks.get(i).getName(),taskFrom,"the management group"));
	    		    System.out.println((i+1) + ") " + tasks.get(i).getName());
	    		}
			return ResultGenerator.getSuccessResult(userTasks);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.getFailResult("获取请假信息失败！");
		}
	}
	
	

}
