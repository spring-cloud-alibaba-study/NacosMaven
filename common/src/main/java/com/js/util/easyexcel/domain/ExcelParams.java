package com.js.util.easyexcel.domain;

import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Data;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Excel读写参数类
 */
@Data
public class ExcelParams<T> {

    /**
     * Excel文件名
     */
    private String fileName;

    /**
     * Excel工作表名
     */
    private String sheetName;

    /**
     * 表头Class
     */
    private Class<T> dataClass;

    /**
     * 数据
     */
    private List<T> dataList;

    /**
     * 文件输出源
     */
    private OutputStream fileDest;

    /**
     * 文件读取源
     */
    private InputStream fileSrc;

    /**
     * Excel读取监听器
     */
    private AnalysisEventListener<T> listener;

    /**
     * 表格冻结
     */
    private FreezePane freezePane;

    public ExcelParams() {

    }

    public ExcelParams(String fileName, OutputStream fileDest, String sheetName, Class<T> dataClass, List<T> dataList) {
        this.fileName = fileName;
        this.fileDest = fileDest;
        this.sheetName = sheetName;
        this.dataClass = dataClass;
        this.dataList = dataList;
    }

    public ExcelParams(Class<T> dataClass, List<T> dataList) {
        this.dataClass = dataClass;
        this.dataList = dataList;
    }

    public ExcelParams(InputStream fileSrc, Class<T> dataClass) {
        this.fileSrc = fileSrc;
        this.dataClass = dataClass;
    }

    public ExcelParams(String fileName, InputStream fileSrc, String sheetName, Class<T> dataClass, List<T> dataList) {
        this.fileName = fileName;
        this.fileSrc = fileSrc;
        this.sheetName = sheetName;
        this.dataClass = dataClass;
        this.dataList = dataList;
    }
}