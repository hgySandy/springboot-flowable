package com.home.demo.util;

/**
* 响应结果生成工具
*/
public class ResultGenerator {
  private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

  public static Result getSuccessResult() {
      return new Result()
              .setCode(ResultCode.SUCCESS)
              .setMessage(DEFAULT_SUCCESS_MESSAGE);
  }

  public static Result getSuccessResult(Object data) {
      return new Result()
              .setCode(ResultCode.SUCCESS)
              .setMessage(DEFAULT_SUCCESS_MESSAGE)
              .setData(data);
  }

  public static Result getFailResult(String message) {
      return new Result()
              .setCode(ResultCode.FAIL)
              .setMessage(message);
  }
}


