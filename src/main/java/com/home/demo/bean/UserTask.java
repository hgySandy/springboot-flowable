package com.home.demo.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserTask implements Serializable{
	
		private static final long serialVersionUID = 5035085957635203218L;
		private String taskId;
		private String taskName;
		private String taskFrom;
		private String taskTo;
		
		public UserTask() {
			super();
		}
		
		public UserTask(String taskId, String taskName, String taskFrom, String taskTo) {
			super();
			this.taskId = taskId;
			this.taskName = taskName;
			this.taskFrom = taskFrom;
			this.taskTo = taskTo;
		}
				
}
