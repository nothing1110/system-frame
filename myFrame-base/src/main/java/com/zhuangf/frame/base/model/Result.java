package com.zhuangf.frame.base.model;

import com.zhuangf.frame.base.enums.ResultCode;
import com.zhuangf.frame.base.enums.ResultStatus;

import java.io.Serializable;

/**
 * 结果集
 * @Author: zhuangf
 * @Date: 2018/11/29
 */
public class Result<T> implements Serializable {
    private int status;//返回状态
    private int code;//返回值
    private String message;//返回信息
    private T data;//返回数据

    private Result() {//构造函数私有化
    }

    /*创建实例*/
    public static class ResultBuilder<T> {
        public Result build() {
            return new Result();
        }
    }

    /**
     * 创建成功实体类
     * @param code
     * @return
     */
    public static Result success(int code) {
        return new ResultBuilder().build().setStatus(ResultStatus.SUCCESS.getValue()).setCode(code);
    }

    public static Result success(ResultCode resultCode) {
        return new ResultBuilder().build().setStatus(ResultStatus.SUCCESS.getValue()).setCode(resultCode.getCode()).setMessage(resultCode.getCodeMsg());
    }

    /**
     * 创建失败实体类
     * @param code
     * @return
     */
    public static Result fail(int code) {
        return new ResultBuilder().build().setStatus(ResultStatus.FAIL.getValue()).setCode(code);
    }

    public static Result fail(ResultCode resultCode) {
        return new ResultBuilder().build().setStatus(ResultStatus.FAIL.getValue()).setCode(resultCode.getCode()).setMessage(resultCode.getCodeMsg());
    }

    /**
     * 创建默认实体类
     * @param
     * @return
     */
    public static Result successWithCode() {
        return new ResultBuilder().build().setStatus(ResultStatus.SUCCESS.getValue()).setCode(ResultCode.SUCCESS.getCode()).setMessage(ResultCode.SUCCESS.getCodeMsg());
    }

    /**
     * 创建默认失败
     * @param
     * @return
     */
    public static Result failWithCode() {
        return new ResultBuilder().build().setStatus(ResultStatus.FAIL.getValue()).setCode(ResultCode.FAIL.getCode()).setMessage(ResultCode.FAIL.getCodeMsg());
    }

    public int getStatus() {
        return status;
    }

    public Result setStatus(int status) {
        this.status = status;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
