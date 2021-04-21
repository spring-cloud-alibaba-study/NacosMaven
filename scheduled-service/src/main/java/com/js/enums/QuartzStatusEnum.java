package com.js.enums;

import lombok.Getter;

@Getter
public enum QuartzStatusEnum {
    OPEN("0", "启动"),
    CLOSE("1", "停止");

    QuartzStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 状态码
     **/
    private String code;
    /**
     * 描述信息
     **/
    private String desc;
}
