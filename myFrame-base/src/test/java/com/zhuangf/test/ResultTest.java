package com.zhuangf.test;

import com.zhuangf.frame.base.enums.ResultStatus;
import com.zhuangf.frame.base.model.Result;

/**
 * recode测试
 * @Author: zhuangf
 * @Date: 2018/11/30
 */
public class ResultTest {
    public static void main(String[] args) {
        Result result = Result.successWithCode();
        System.out.println(result);
    }
}
