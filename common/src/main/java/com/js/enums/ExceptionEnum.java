package com.js.enums;

import com.js.intefaces.ErrorTypeException;
import lombok.Getter;

@Getter
public enum ExceptionEnum implements ErrorTypeException {
    /**
     * 通用异常信息ErrorType
     **/
    DEFAULT_ERROR_TYPE("DEFAULT_ERROR_TYPE", "默认异常信息"),
    /**
     * 重复点击
     **/
    NO_REPEAT_CLICK("NO_REPEAT_CLICK", "请勿重复点击,稍后再试"),
    /**
     * 网络异常
     **/
    NETWORK_ANOMALY_ERRORTYPE("NETWORK_ANOMALY_ERRORTYPE", "网络异常,请稍后再试"),
    /**
     * 当出现参数异常时返回的code码
     */
    INVALIDPARAMS("INVALIDPARAMS", "非法的参数!"),
    /**
     * 当用户未登录时返回的code码
     */
    USERNOTLOGIN("USERNOTLOGIN", "用户没登录"),

    /**
     * 用户名或者密码错误
     **/
    UNKNOW_USER("UNKNOW_USER", "用户名或者密码错误"),

    /**
     * 系统异常
     **/
    SYSTEM_EXCEPTION("SYSTEM_EXCEPTION", "系统出现异常");
    private String codeException;
    /**
     * 返回前端的数据信息
     */
    private String describe;

    ExceptionEnum(String codeException, String describe) {
        this.codeException = codeException;
        this.describe = describe;
    }
}
