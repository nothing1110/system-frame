package com.zhuangf.frame.base.enums;

/**
 * 返回值
 * @Author: zhuangf
 * @Date: 2018/11/30
 */
public enum ResultCode {
    /**成功**/
    SUCCESS(200,"操作成功"),
    /**失败**/
    FAIL(400,"操作失败"),
    /**未认证（签名错误）**/
    UNAUTHORIZED(401,"认证失败"),
    /**接口不存在**/
    NOT_FOUND(404,"接口不存在"),
    /**服务器内部错误**/
    INTERNAL_SERVER_ERROR(500,"服务器内部错误"),
    /**重定向至登录界面**/
    REDIRECT_LOGIN(10999,"重定向至登录界面"),
    /**提示权限不足**/
    PROMPT_AUTH(10998,"权限不足"),
    /**没有参数**/
    NO_PARAM(10901,"参数缺失"),
    /**解析失败**/
    PARSE_ERROR(10914,"解析失败"),
    /**验证成功**/
    CHECK_OK(10996),
    /**验证失败**/
    CHECK_FAIL(10995),

    /*
     * 登录返回信息
     * */
    E_411(411, "请求处理异常，请稍后再试"),
    E_10008(10008, "尚无用户属于此角色"),
    E_10009(10009, "账户已存在"),
    E_10010(10010, "用户名错误"),
    E_10011(10011, "该账户被禁用"),
    E_10012(10012, "密码错误"),
    E_10013(10013, "未查询到该用户"),


    E_20011(20011, "登陆已过期,请重新登陆"),
    E_20012(20012, "验证码已失效，请刷新"),
    E_20013(20013, "验证码错误，请重新输入"),
    E_20014(20014, "token已失效"),
    E_20015(20015, "未获取到Authorization"),
    E_20016(20016, "token解析报错"),
    E_20017(20017, "登录输错已超过最大限制次数"),
    E_20018(20018, "redis获取不到用户，请检查redis配置"),

    E_90003(90003, "缺少必填参数"),
    E_90004(90004, "操作失败");

    private int code;

    private String codeMsg;

    ResultCode(int code) {
        this.code = code;
    }
    ResultCode(int code, String codeMsg) {
        this.code = code;
        this.codeMsg = codeMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCodeMsg() {
        return codeMsg;
    }

    public void setCodeMsg(String codeMsg) {
        this.codeMsg = codeMsg;
    }
}
