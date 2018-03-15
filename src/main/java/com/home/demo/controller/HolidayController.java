package com.home.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.home.demo.bean.Vacation;
import com.home.demo.service.HolidayService;
import com.home.demo.util.Result;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/process/vacation-request")
public class HolidayController {
		@Autowired
		private HolidayService holidayService;
		
	    /**
	     * 部署请假流程
	     */
	    @ApiOperation(value = "部署请假流程", notes = "部署请假流程", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	    @RequestMapping(value = "/repository/deployments",method=RequestMethod.POST)
	    public Result deployProcess(){
	    		return holidayService.deployProcess();
	    }
	    
	    /**
	     * 创建假期申请
	     */
	    @ApiOperation(value = "创建假期申请", notes = "创建假期申请", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	    @RequestMapping(value = "/runtime/tasks",method=RequestMethod.POST)
	    public Result addHoliday(@RequestBody Vacation vacation){
	    		return holidayService.save(vacation);
	    }
	    
	    /**
	     * 显示假期列表
	     */
	    @ApiOperation(value = "获取假期申请列表", notes = "获取假期申请列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	    @RequestMapping(value = "/runtime/tasks",method=RequestMethod.GET)
	    public Result findAllHolidays(){
	        return holidayService.findAll();
	    }
	    
	    /**
	     * 查看假期详细信息
	     */
	    @ApiOperation(value = "获取指定id的假期申请", notes = "获取指定id的假期申请", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	    @RequestMapping(value = "/runtime/tasks/id/{taskId}",method=RequestMethod.GET)
	    public Result findTaskByTaskId(@PathVariable("taskId")String taskId){
	    		return holidayService.findById(taskId);
	    }
	    
	    /**
	     * 查看假期详细信息
	     */
	    @ApiOperation(value = "获取指定候选组的待处理任务", notes = "获取指定候选组的待处理任务", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	    @RequestMapping(value = "/runtime/tasks/group/{groupName}",method=RequestMethod.GET)
	    public Result findTaskByGroupName(@PathVariable("groupName")String groupName){
	    		return holidayService.findByGroupName(groupName);
	    }
	    
	    /**
	     * 查看假期详细信息
	     */
	    @ApiOperation(value = "获取指定执行者的待处理任务", notes = "获取指定执行者的待处理任务", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	    @RequestMapping(value = "/runtime/tasks/assignee/{assignee}",method=RequestMethod.GET)
	    public Result findTaskByAssignee(@PathVariable("assignee")String assignee){
	    	return holidayService.findByAssignee(assignee);
	    }
	    
	    /**
	     * 审批假期申请
	     */
	    @ApiOperation(value = "审批(更新)假期申请", notes = "审批(更新)假期申请", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
	    @RequestMapping(value = "/runtime/tasks/id/{taskId}",method=RequestMethod.PUT)
	    public Result modifyHoliday(@PathVariable("taskId")String taskId,boolean isApproved,String comments){
	    	return holidayService.modify(taskId,isApproved,comments);
	    }

	     
}
