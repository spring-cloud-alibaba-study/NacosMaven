package com.js.enums;

import lombok.Getter;

@Getter
public enum ExceptionEnum {
    /**
     * 通用异常信息ErrorType
     **/
    DefaultResponseErrorType("DEFAULT_ERROR_TYPE", "默认异常信息"),
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
