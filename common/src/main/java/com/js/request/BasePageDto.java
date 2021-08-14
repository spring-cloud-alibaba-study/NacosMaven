package com.js.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "")
@Data
public class BasePageDto {
    /**
     * 当前页
     **/
    @ApiModelProperty(value = "当前页", required = false)
    private Integer pageNum;
    /**
     * 每页条数
     **/
    @ApiModelProperty(value = "每页条数", required = false)
    private Integer pageSize;

    /**
     * 是否分页
     **/
    @ApiModelProperty(value = "是否分页", required = false)
    private Boolean isPage = true;
}
