package com.js.config;

import com.js.entity.SysQuartz;
import com.js.enums.QuartzStatusEnum;
import com.js.enums.StatusEnum;
import com.js.service.SysQuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName SystemStartTask
 * 项目启动任务--启动定时任务
 * @Author dujie
 * @Date 2020-07-21 12:56:56
 **/
@Component
@Order(100)
public class SystemQuartzStartTask implements CommandLineRunner {

    @Autowired
    private SysQuartzService sysQuartzService;

    @Override
    public void run(String... args) throws Exception {
        // 查询启动的定时任务
        SysQuartz condition = new SysQuartz();
        condition.setStatus(StatusEnum.OPEN.getCode());
        condition.setQuartzStatus(QuartzStatusEnum.OPEN.getCode());
        List<SysQuartz> list = sysQuartzService.findByCondition(condition);
        if (null != list && !list.isEmpty()) {
            for (SysQuartz item : list) {
                // 删除定时任务
                sysQuartzService.schedulerDelete(item.getClassName().trim());
                // 添加定时任务
                sysQuartzService.schedulerAdd(item.getClassName().trim(), item.getCronExpression().trim(), item.getParam());
            }
        }
    }
}