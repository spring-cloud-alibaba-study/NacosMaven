package com.js.request;

import lombok.Data;

@Data
public class BasePageDto {
    /**
     * 当前页
     **/
    private Integer pageNum;
    /**
     * 每页条数
     **/
    private Integer pageSize;

    /**
     * 是否分页
     **/
    private Boolean isPage = true;
}
