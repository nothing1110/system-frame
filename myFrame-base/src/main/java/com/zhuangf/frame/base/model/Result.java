package com.zhuangf.frame.base.model;

/**
 * 结果集
 * @Author: zhuangf
 * @Date: 2018/11/29
 */
public class Result<T> {
    private int code;//返回值
    private String message;//返回信息
    private T data;//返回数据

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
