package com.js.enums;

import lombok.Getter;

@Getter
public enum StatusCode {

    /**
     * 操作成功时返回的code码
     **/
    SUCCESS("0", "操作成功"),

    /**
     * 操作失败时返回的code码
     */
    FAIL("-1", "操作失败"),
    /**
     * 当出现参数异常时返回的code码
     */
    INVALIDPARAMS("201", "非法的参数!"),
    /**
     * 当用户未登录时返回的code码
     */
    USERNOTLOGIN("202", "用户没登录"),

    /**
     * 用户名或者密码错误
     **/
    UNKNOW_USER("203", "用户名或者密码错误"),

    /**
     * 系统异常
     **/
    SYSTEM_EXCEPTION("500", "系统出现异常");

    /**
     * 返回前端的code码
     */
    private String code;
    /**
     * 返回前端的数据信息
     */
    private String msg;

    StatusCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * @return java.lang.Integer
     * @Description 获取code码
     **/
    public String getCode() {
        return code;
    }

    /**
     * @return java.lang.String
     * @Description 返回的异常信息
     **/
    public String getMsg() {
        return msg;
    }
}

