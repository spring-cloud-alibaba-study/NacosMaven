package com.js.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum StatusEnum {
    OPEN(0, "正常"),
    CLOSE(1, "停止");

    StatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 状态码
     **/
    private Integer code;
    /**
     * 描述信息
     **/
    private String desc;
}
