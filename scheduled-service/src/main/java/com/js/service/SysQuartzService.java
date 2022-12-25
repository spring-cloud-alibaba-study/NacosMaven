package com.js.service;

import com.js.entity.SysQuartz;
import com.js.enums.QuartzStatusEnum;
import com.js.mapper.SysQuartzMapper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SysQuartzService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private SysQuartzMapper sysQuartzMapper;

    /**
     * @return
     * @Description: 条件查询
     * @Param [condition]
     * @Author: 渡劫 dujie
     * @Date: 2021/4/21 2:25 PM
     */
    public List<SysQuartz> findByCondition(SysQuartz condition) {
        return sysQuartzMapper.selectList(condition);
    }

    public Boolean add(SysQuartz sysQuartz) {
        if (!CronExpression.isValidExpression(sysQuartz.getCronExpression())) {
            throw new RuntimeException("表达式拼写异常");
        }

        SysQuartz condition = new SysQuartz();
        condition.setClassName(sysQuartz.getClassName());
        Integer result = sysQuartzMapper.countByEntity(condition);
        if (result > 0) {
            return Boolean.FALSE;
        } else {
            sysQuartz.setId(System.currentTimeMillis());
            sysQuartzMapper.insert(sysQuartz);
        }
        // 启动
        if (QuartzStatusEnum.OPEN.getCode().equals(sysQuartz.getQuartzStatus())) {
            this.schedulerAdd(sysQuartz.getClassName().trim(), sysQuartz.getCronExpression().trim(), sysQuartz.getParam());
        }
        return Boolean.TRUE;
    }

    /**
     * 添加定时任务
     *
     * @param className
     * @param cronExpression
     * @param param
     */
    public void schedulerAdd(String className, String cronExpression, String param) {
        try {
            // 启动调度器
            scheduler.start();
            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(className).getClass()).withIdentity(className).usingJobData("param", param).build();
            // 表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(className).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 删除定时任务
     *
     * @param className
     */
    public void schedulerDelete(String className) {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(className));
            scheduler.unscheduleJob(TriggerKey.triggerKey(className));
            scheduler.deleteJob(JobKey.jobKey(className));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private static Job getClass(String className) throws Exception {
        Class<?> class1 = Class.forName(className);
        return (Job) class1.newInstance();
    }
}
