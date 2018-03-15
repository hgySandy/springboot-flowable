package com.home.demo.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class Vacation implements Serializable{
	
	private static final long serialVersionUID = 162254020969518856L;
	private String employeeName;
	private String numberOfDays;
	private String startDate;
	private String reason;
	private String approver;
	private String comments;
	private boolean vacationApproved;
	private boolean resendRequest;
	

}
