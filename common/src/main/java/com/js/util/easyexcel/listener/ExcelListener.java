package com.js.util.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * EasyExcel 导入监听，第一个为解析的结果类 ,在解析数据过程中开始分批处理，推荐使用
 */
@SuppressWarnings("ALL")
@Slf4j
public abstract class ExcelListener<T> extends AnalysisEventListener<T> {

    private static final int BATCH_COUNT = 2000;

    /**
     * 解析的数据
     */
    private List<T> datas = new ArrayList<>();

    /**
     * 自定义头可以构造方法直接创建
     */
    public ExcelListener() {

    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        //数据存储到list，供批量处理，或后续自己业务逻辑处理。
        this.datas.add(data);

        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (datas.size() >= BATCH_COUNT) {
            //根据自己业务做处理
            doSomething(datas);
            // 存储完成清理 list
            datas.clear();
        }
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if (!CollectionUtils.isNotEmpty(datas)) {
            doSomething(datas);
        }
        //解析结束销毁不用的资源
        datas.clear();
        // 如果有后续处理可以再加一个方法让子类继承并执行就可以了
    }

    /**
     * @return void
     * @Description: 需要重写的方法
     * @Param [object]
     * @Author: 渡劫 dujie
     * @Date: 5/14/21 9:26 PM
     */
    public abstract void doSomething(List<T> object);

//    /**
//     * 表头集合很少场景会使用所以注掉了
//     */
//    private List<Map<Integer, String>> headMapList = new ArrayList<>();
//    public ExcelListener(List<Map<Integer, String>> headMapList) {
//        this.headMapList = headMapList;
//    }
//    /**
//     * 这里会一行行的返回头，通常会在这里校验头信息，或者获取传入的excle头信息基本不用，生产不建议使用
//     *
//     * @param headMap
//     * @param context
//     */
//    @Override
//    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
//        log.info("解析到一条头数据:{}", JSON.toJSONString(headMap));
//        headMapList.add(headMap);
//    }
}