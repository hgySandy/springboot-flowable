package com.home.demo.util;

/**
* 统一API响应结果封装
*/
public class Result {
  private int code;
  private String message;
  private Object data;
  
  public Result setCode(ResultCode resultCode) {
      this.code = resultCode.code;
      return this;
  }
  
  public Result setMessage(String message) {
	  this.message = message;
	  return this;
  }

	public Result setData(Object data) {
		this.data = data;
		return this;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Object getData() {
		return data;
	}
  
}

