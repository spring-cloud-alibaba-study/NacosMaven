CREATE TABLE `sys_quartz`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `class_name`      varchar(32) DEFAULT NULL COMMENT '任务类名',
    `cron_expression` varchar(32) DEFAULT NULL COMMENT 'cron表达式',
    `param`           varchar(32) DEFAULT NULL COMMENT '参数',
    `descript`        varchar(11) DEFAULT NULL COMMENT '描述',
    `quartz_status`   varchar(2)  DEFAULT NULL COMMENT '启动状态(0--启动1--停止)',
    `create_time`     datetime    DEFAULT NULL COMMENT '创建时间',
    `create_user`     varchar(32) DEFAULT NULL COMMENT '创建人',
    `status`          tinyint(1) DEFAULT '0' COMMENT '状态（0--正常1--停用）',
    `del_flag`        tinyint(1) DEFAULT '0' COMMENT '删除状态（0，正常，1已删除）',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='定时任务信息表';