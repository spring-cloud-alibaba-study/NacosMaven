package com.js.util.poi;

import com.js.constants.CommonConstants;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SuppressWarnings("ALL")
public class ExcelExport {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelExport.class);

    HttpServletResponse response;
    //文件名
    private String fileName;
    //sheet名
    private String sheetName;
    //表头字体
    private String titleFontType = "Arial Unicode MS";
    //表头背景色 十六进制
    private String titleBackColor = "C1FBEE";
    //表头字号
    private short titleFontSize = 12;
    //Float类型数据小数位
    private String floatDecimal = "0.00";
    //Double类型数据小数位
    private String doubleDecimal = "0.00";
    //设置列的公式
    private String[] colFormula = null;
    //添加自动筛选的列 如 A:M
    private String address = "";

    DecimalFormat floatDecimalFormat = new DecimalFormat(floatDecimal);
    DecimalFormat doubleDecimalFormat = new DecimalFormat(doubleDecimal);

    private HSSFWorkbook workbook = null;


    public ExcelExport(String sheetName) {
        this.sheetName = sheetName;
        workbook = new HSSFWorkbook();
    }

    public ExcelExport(HttpServletResponse response, String fileName, String sheetName) {
        this.fileName = fileName;
        this.response = response;
        this.sheetName = sheetName;
        workbook = new HSSFWorkbook();
    }

    /**
     * 设置表头字体.
     *
     * @param titleFontType
     */
    public void setTitleFontType(String titleFontType) {
        this.titleFontType = titleFontType;
    }

    /**
     * 设置表头背景色.
     *
     * @param titleBackColor
     */
    public void setTitleBackColor(String titleBackColor) {
        this.titleBackColor = titleBackColor;
    }

    /**
     * 设置表头字体大小.
     *
     * @param titleFontSize
     */
    public void setTitleFontSize(short titleFontSize) {
        this.titleFontSize = titleFontSize;
    }

    /**
     * 设置表头自动筛选栏位,如A:AC.
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 设置float类型数据小数位 默认.00
     *
     * @param doubleDecimal 如 ".00"
     */
    public void setDoubleDecimal(String doubleDecimal) {
        this.doubleDecimal = doubleDecimal;
    }

    /**
     * 设置doubel类型数据小数位 默认.00
     *
     * @param floatDecimalFormat 如 ".00
     */
    public void setFloatDecimalFormat(DecimalFormat floatDecimalFormat) {
        this.floatDecimalFormat = floatDecimalFormat;
    }

    /**
     * 设置列的公式
     *
     * @param colFormula 存储i-1列的公式 涉及到的行号使用@替换 如A@+B@
     */
    public void setColFormula(String[] colFormula) {
        this.colFormula = colFormula;
    }

    /**
     * 写excel.
     * xls方式
     *
     * @param titleColumn 对应bean的属性名
     * @param titleName   excel要导出的列名
     * @param titleSize   列宽
     * @param dataList    数据
     */
    public void writeExcel(String[] titleColumn, String[] titleName, int[] titleSize, List<?> dataList) {
        //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
        Sheet sheet = workbook.createSheet(this.sheetName);
        //新建文件
        OutputStream out = null;
        try {
            //否则，直接写到输出流中
            out = createOutputStream();
            fileName = fileName + CommonConstants.XLS;
            //写入excel的表头
            Row titleNameRow = workbook.getSheet(sheetName).createRow(0);
            //设置样式
            CellStyle titleStyle = workbook.createCellStyle();
            titleStyle = setFontAndBorder(titleStyle, titleFontType, titleFontSize);
            titleStyle = setColor(titleStyle, titleBackColor, (short) 10);
            setColWidth(titleName, titleSize, titleNameRow, sheet, titleStyle);

            //为表头添加自动筛选
            if (!"".equals(address)) {
                CellRangeAddress c = CellRangeAddress.valueOf(address);
                sheet.setAutoFilter(c);
            }
            //通过反射获取数据并写入到excel中
            if (!dataList.isEmpty()) {
                if (titleColumn.length > 0) {
                    for (int rowIndex = 1; rowIndex <= dataList.size(); rowIndex++) {
                        // 获得该对象
                        Object obj = dataList.get(rowIndex - 1);
                        //获得该对对象的class实例
                        Class clazz = obj.getClass();
                        Row dataRow = workbook.getSheet(sheetName).createRow(rowIndex);
                        for (int columnIndex = 0; columnIndex < titleColumn.length; columnIndex++) {
                            String title = titleColumn[columnIndex].trim();
                            if (!"".equals(title)) {
                                //字段不为空
                                setReturnType(title, clazz, obj, columnIndex, dataRow);
                            } else {
                                //字段为空 检查该列是否是公式
                                checkolFormulaC(columnIndex, rowIndex, dataRow);

                            }
                        }
                    }
                }
            }
            workbook.write(out);
        } catch (Exception e) {
            LOGGER.error("导出Excel失败", e);
            throw new RuntimeException("导出Excel失败");
        } finally {
            closeOutStream(out);
        }
    }

    /**
     * xlsx方式
     *
     * @param fileName
     * @param titleColumn
     * @param titleName
     * @param titleSize
     * @param dataList
     */
    public void writeBigExcel(String fileName, String[] titleColumn,
                              String[] titleName, int[] titleSize, List<?> dataList) {
        OutputStream out = null;
        try {
            SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(100);
            out = createOutputStream();
            int k = 0;
            int rowIndex;
            Sheet sheet = sxssfWorkbook.createSheet(fileName + (k + 1));
            //写入excel的表头
            Row titleNameRow = sxssfWorkbook.getSheet(fileName + (k + 1)).createRow(0);
            setColWidth(titleName, titleSize, titleNameRow, sheet, null);

            //表头写入到excel的多个sheet中
            if (dataList != null && !dataList.isEmpty()) {
                if (titleColumn.length > 0) {
                    for (int index = 0; index < dataList.size(); index++) {
                        //每个sheet3W条数据
                        if (index != 0 && (index) % 30000 == 0) {
                            k = k + 1;
                            sheet = sxssfWorkbook.createSheet(fileName + (k + 1));
                            //写入excel的表头
                            titleNameRow = sxssfWorkbook.getSheet(fileName + (k + 1)).createRow(0);
                            setColWidth(titleName, titleSize, titleNameRow, sheet, null);
                        }
                        if (index < 30000) {
                            rowIndex = index + 1;
                        } else {
                            rowIndex = index - 30000 * ((index) / 30000) + 1;
                        }
                        Object obj = dataList.get(index);
                        Class clazz = obj.getClass();
                        Row dataRow = sxssfWorkbook.getSheet(fileName + (k + 1)).createRow(rowIndex);
                        for (int columnIndex = 0; columnIndex < titleColumn.length; columnIndex++) {
                            String title = titleColumn[columnIndex].trim();
                            if (!"".equals(title)) {
                                setReturnType(title, clazz, obj, columnIndex, dataRow);
                            } else {
                                //字段为空 检查该列是否是公式
                                checkolFormulaC(columnIndex, rowIndex, dataRow);
                            }
                        }
                    }
                }
            }
            sxssfWorkbook.write(out);
        } catch (Exception e) {
            LOGGER.error("导出Excel失败", e);
            throw new RuntimeException("导出失败");
        } finally {
            closeOutStream(out);
        }
    }


    /**
     * 将16进制的颜色代码写入样式中来设置颜色
     *
     * @param style 保证style统一
     * @param color 颜色：66FFDD
     * @param index 索引 8-64 使用时不可重复
     * @return
     */
    public CellStyle setColor(CellStyle style, String color, short index) {
        if ("".equals(color)) {
            //转为RGB码  转为16进制
            int r = Integer.parseInt((color.substring(0, 2)), 16);
            int g = Integer.parseInt((color.substring(2, 4)), 16);
            int b = Integer.parseInt((color.substring(4, 6)), 16);
            //自定义cell颜色
            HSSFPalette palette = workbook.getCustomPalette();
            palette.setColorAtIndex((short) index, (byte) r, (byte) g, (byte) b);

            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setFillForegroundColor(index);
        }
        return style;
    }

    /**
     * 设置字体并加外边框
     *
     * @param style    样式
     * @param fontName 字体名
     * @param size     大小
     * @return
     */
    public CellStyle setFontAndBorder(CellStyle style, String fontName, short size) {
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints(size);
        font.setFontName(fontName);
        font.setBold(true);
        style.setFont(font);
        //下边框 左边框 上边框 右边框
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    /**
     * @return java.io.OutputStream
     * @Description: 创建输出流
     * @Param []
     * @Author: 渡劫 dujie
     * @Date: 5/14/21 8:28 PM
     */
    private OutputStream createOutputStream() throws Exception {
        OutputStream out = response.getOutputStream();
        fileName = fileName + ".xls";
        response.setContentType("application/msexcel;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename="
                + URLEncoder.encode(fileName, "UTF-8"));
        return out;
    }

    /**
     * @return void
     * @Description: 设置返回类型
     * @Param [title, clazz, obj, columnIndex, dataRow]
     * @Author: 渡劫 dujie
     * @Date: 5/14/21 8:55 PM
     */
    private void setReturnType(String title, Class clazz, Object obj, Integer columnIndex, Row dataRow) throws Exception {
        //使首字母大写
        String methodName = "get" + Character.toUpperCase(title.charAt(0)) + title.substring(1);

        //设置要执行的方法
        Method method = clazz.getDeclaredMethod(methodName);
        //获取返回类型
        String returnType = method.getReturnType().getName();
        Object object = method.invoke(obj);
        String data = method.invoke(obj) == null ? "" : object.toString();
        Cell cell = dataRow.createCell(columnIndex);
        if (data != null && !"".equals(data)) {
            if (CommonConstants.INT.equals(returnType)) {
                cell.setCellValue(Integer.parseInt(data));
            } else if (CommonConstants.LONG.equals(returnType)) {
                cell.setCellValue(Long.parseLong(data));
            } else if (CommonConstants.FLOAT.equals(returnType)) {
                cell.setCellValue(floatDecimalFormat.format(Float.parseFloat(data)));
            } else if (CommonConstants.DOUBLE.equals(returnType)) {
                cell.setCellValue(doubleDecimalFormat.format(Double.parseDouble(data)));
            } else if (Date.class.getName().equals(returnType)) {
                cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(object));
            } else {
                cell.setCellValue(data);
            }
        }
    }

    /**
     * @return void
     * @Description: 判断是否为公式
     * @Param [columnIndex, rowIndex, dataRow]
     * @Author: 渡劫 dujie
     * @Date: 5/14/21 9:13 PM
     */
    private void checkolFormulaC(Integer columnIndex, Integer rowIndex, Row dataRow) {
        if (colFormula != null) {
            String sixBuf = colFormula[columnIndex].replace("@", (rowIndex + 1) + "");
            Cell cell = dataRow.createCell(columnIndex);
            cell.setCellFormula(sixBuf);
        }
    }

    /**
     * @return void
     * @Description: 设置列宽
     * @Param [titleName, titleSize, titleNameRow, sheet]
     * @Author: 渡劫 dujie
     * @Date: 5/14/21 9:06 PM
     */
    private void setColWidth(String[] titleName, int[] titleSize, Row titleNameRow, Sheet sheet, CellStyle titleStyle) {
        for (int i = 0; i < titleName.length; i++) {
            //设置宽度
            sheet.setColumnWidth(i, titleSize[i] * 256);
            Cell cell = titleNameRow.createCell(i);
            if (titleStyle != null) {
                cell.setCellStyle(titleStyle);
            }
            cell.setCellValue(titleName[i]);
        }
    }

    /**
     * @return void
     * @Description: 关闭流
     * @Param [out]
     * @Author: 渡劫 dujie
     * @Date: 5/14/21 9:21 PM
     */
    private void closeOutStream(OutputStream out) {
        if (out != null) {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                LOGGER.debug("导出写Excel异常", e);
            }
        }
    }
}

