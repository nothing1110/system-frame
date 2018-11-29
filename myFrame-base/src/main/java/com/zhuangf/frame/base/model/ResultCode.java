package com.zhuangf.frame.base.model;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    /**成功**/
    SUCCESS(200),
    /**失败**/
    FAIL(400),
    /**未认证（签名错误）**/
    UNAUTHORIZED(401),
    /**接口不存在**/
    NOT_FOUND(404),
    /**服务器内部错误**/
    INTERNAL_SERVER_ERROR(500),
    /**重定向至登录界面**/
    REDIRECT_LOGIN(10999),
    /**提示至登录界面**/
    REST_LOGIN(10989),
    /**REST退出**/
    REST_LOGOUT(10988),
    /**提示权限不足**/
    PROMPT_AUTH(10998),
    /**系统级别错误**/
    ERROR(10997),
    /**没有参数**/
    NO_PARAM(10901),
    /**空**/
    NULL(10902),
    /**没有此页**/
    NO_PAGE(10905),
    /**执行成功**/
    DO_OK(10906),
    /**执行失败**/
    DO_FAIL(10907),
    /**验证成功**/
    CHECK_OK(10996),
    /**验证失败**/
    CHECK_FAIL(10995),
    /**没有查询结果**/
    NO_RECORD(10908),
    /**没有EXPLAIN注解**/
    NO_EXPLAIN(10909),
    /**JPA HANDLER 通配符转换失败**/
    WBOOST_HANDLER_BASEPACKAGE_RESOLVE_ERROR(10910),
    /**连接接口失败**/
    CONNECTION_ERROR(10911),
    /**分页转换解析失败**/
    PAGE_RESOLVER_ERROR(10912),
    /**返回原样数据**/
    PROMPT(10913),
    /**解析失败**/
    PARSE_ERROR(10914),
    /**无含义**/
    NOTHING(0),

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

    private int errorCode;

    private String errorMsg;

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
    ResultCode(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
