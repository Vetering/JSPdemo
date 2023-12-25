package com.ct.common;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/4 20:54
 **/
public class Result {
    public static final Integer CODE_SUCCESS = 200;
    public static final Integer CODE_ERROR =500 ;
    public static final Integer CODE_AUTH_ERROR = 401;

    private Integer code;
    private String message;
    private Object data;
    public Result() {
    }

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success() {
        return new Result(CODE_SUCCESS, "请求成功！", null);
    }

    public static Result success(Object data) {
        return new Result(CODE_SUCCESS, "请求成功！", data);
    }

    public static Result error() {
        return new Result(CODE_ERROR, "系统错误！", null);
    }

    public static Result error(Integer code, String message) {
        return new Result(code, message, null);
    }

    // Getters and setters for code, message, and data

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

