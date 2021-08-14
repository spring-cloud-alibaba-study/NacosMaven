package com.js.util.easyexcel.domain;

import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Data;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Excel多sheet读写参数类
 */
@SuppressWarnings("rawtypes")
@Data
public class ExcelParamsMulti {

    /**
     * Excel文件名
     */
    private String fileName;

    /**
     * Excel工作表名
     */
    private String[] sheetName;

    /**
     * 表头Class
     */
    private Class[] dataClassArray;

    /**
     * 数据
     */
    private List[] dataListArray;

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
    private AnalysisEventListener[] listenerArray;

    /**
     * 表格冻结
     */
    private FreezePane[] freezePane;

    public ExcelParamsMulti() {

    }

    public ExcelParamsMulti(String fileName, OutputStream fileDest, String[] sheetName, Class[] dataClassArray, List[] dataListArray) {
        this.fileName = fileName;
        this.fileDest = fileDest;
        this.sheetName = sheetName;
        this.dataClassArray = dataClassArray;
        this.dataListArray = dataListArray;
    }

    public ExcelParamsMulti(InputStream fileSrc, Class[] dataClassArray) {
        this.fileSrc = fileSrc;
        this.dataClassArray = dataClassArray;
    }

    public ExcelParamsMulti(String fileName, InputStream fileSrc, String[] sheetName, Class[] dataClassArray, List[] dataListArray) {
        this.fileName = fileName;
        this.fileSrc = fileSrc;
        this.sheetName = sheetName;
        this.dataClassArray = dataClassArray;
        this.dataListArray = dataListArray;
    }

}