package com.js.enums;

import lombok.Getter;

@Getter
public enum ExceptionEnum {
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
    NETWORH_ANOMALY_ERRORTYPE("NETWORH_ANOMALY_ERRORTYPE", "网络异常,请稍后再试")
    ;
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
