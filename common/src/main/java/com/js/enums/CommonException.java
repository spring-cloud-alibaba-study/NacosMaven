package com.js.enums;

import lombok.Data;

@Data
public class CommonException extends RuntimeException {
    /**
     * 异常状态码
     **/
    private String rerutnCode;
    /**
     * 异常信息
     **/
    private String errorMsg;
}
