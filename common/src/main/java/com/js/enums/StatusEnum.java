package com.js.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {
    OPEN(0, "开启"),
    CLOSE(1, "关闭"),
    ;

    /**
     * code码
     */
    private Integer code;
    /**
     * 描述
     */
    private String msg;

    StatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
