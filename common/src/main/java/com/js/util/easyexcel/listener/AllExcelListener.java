package com.js.util.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * EasyExcel 导入监听，解析的结果统一处理，数据量大时集合会很大，不推荐
 */
@SuppressWarnings("ALL")
@Slf4j
public abstract class AllExcelListener<T> extends AnalysisEventListener<T> {

    /**
     * 解析的数据
     */
    private List<T> datas = new ArrayList<>();

    /**
     * 自定义头可以构造方法直接创建
     */
    public AllExcelListener() {

    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        //数据存储到list，供批量处理，或后续自己业务逻辑处理。
        this.datas.add(data);
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 存在场景需要数据读取完成之后用户统一处理 此时 可以统一在doSomeThing
        // 进行处理，比如说数据去重，数据分组保存需要用户自己处理继承类自己实现
        doSomething(datas);
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
}