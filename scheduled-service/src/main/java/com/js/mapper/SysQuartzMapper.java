package com.js.mapper;

import com.js.entity.SysQuartz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 定时任务信息表(SysQuartz)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-21 12:12:33
 */
@Mapper
public interface SysQuartzMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysQuartz selectById(@Param("id") Long id);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysQuartz 实例对象
     * @return 对象列表
     */
    List<SysQuartz> selectList(SysQuartz sysQuartz);

    /**
     * 新增数据
     *
     * @param sysQuartz 实例对象
     * @return 影响行数
     */
    int insert(SysQuartz sysQuartz);

    /**
     * 批量新增
     *
     * @param sysQuartzs 实例对象的集合
     * @return 影响行数
     */
    int batchInsert(@Param("sysQuartzs") List<SysQuartz> sysQuartzs);

    /**
     * 修改数据
     *
     * @param sysQuartz 实例对象
     * @return 影响行数
     */
    int update(SysQuartz sysQuartz);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") Long id);

    /**
     * 通过条件删除数据
     *
     * @param sysQuartz
     * @return 影响行数
     */
    int deleteByEntity(SysQuartz sysQuartz);

    /**
     * 通过条件删批量删除数据除数据
     *
     * @param list 主键集合
     * @return 影响行数
     */
    int deleteByIds(@Param("list") List<Long> list);

    /**
     * 条件查询总数
     *
     * @return 数据总数
     */
    int countByEntity(SysQuartz sysQuartz);

    /**
     * 批量修改
     *
     * @return 修改成功的条数
     */
    int updateBatch(@Param("list") List<SysQuartz> list);

}