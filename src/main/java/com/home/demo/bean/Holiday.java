package com.home.demo.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class Holiday implements Serializable{
	
	private static final long serialVersionUID = 8780571747316603529L;
	private String taskId;
	private boolean approved;
	private String employee;
	private Integer nrOfHolidays;
	private String description;
	
	public Holiday() {
		super();
	}
	
	public Holiday(String taskId, String employee, Integer nrOfHolidays, String description) {
		this.taskId = taskId;
		this.employee = employee;
		this.nrOfHolidays = nrOfHolidays;
		this.description = description;
	}
	
	public Holiday(String taskId, boolean approved, String employee, Integer nrOfHolidays, String description) {
		this(taskId,employee, nrOfHolidays, description);
		this.approved = approved;
	}
	
}
