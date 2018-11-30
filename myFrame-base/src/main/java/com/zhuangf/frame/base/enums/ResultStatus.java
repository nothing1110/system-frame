package com.zhuangf.frame.base.enums;

/**
 * 返回状态
 * @Author: zhuangf
 * @Date: 2018/11/30
 */
public enum ResultStatus {
    SUCCESS(1), FAIL(0);

    private int value;

    private ResultStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
