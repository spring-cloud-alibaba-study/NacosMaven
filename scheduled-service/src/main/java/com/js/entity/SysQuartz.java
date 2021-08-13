package com.js.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 定时任务信息表(SysQuartz)实体类
 *
 * @author 渡劫 dujie
 * @since 2021-04-21 12:12:31
 */
@Data
public class SysQuartz {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 任务类名
     */
    private String className;
    /**
     * cron表达式
     */
    private String cronExpression;
    /**
     * 参数
     */
    private String param;
    /**
     * 描述
     */
    private String descript;
    /**
     * 启动状态(0--启动1--停止)
     */
    private String quartzStatus;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 状态（0--正常1--停用）
     */
    private Integer status;
    /**
     * 删除状态（0，正常，1已删除）
     */
    private Integer delFlag;

}