package com.js.util.easyexcel.domain;

import com.alibaba.excel.annotation.write.style.ContentFontStyle;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

/**
 * Excel基本设置
 */
@HeadStyle(fillPatternType = FillPatternType.SOLID_FOREGROUND, fillForegroundColor = 30,
        horizontalAlignment = HorizontalAlignment.CENTER, verticalAlignment = VerticalAlignment.CENTER,
        wrapped = true, shrinkToFit = true,
        borderTop = BorderStyle.NONE, borderBottom = BorderStyle.NONE, borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE)
@HeadRowHeight(24)
@HeadFontStyle(bold = true, color = 9, fontName = "Arial", fontHeightInPoints = 10)
@ContentStyle(horizontalAlignment = HorizontalAlignment.CENTER, verticalAlignment = VerticalAlignment.CENTER)
@ContentRowHeight(19)
@ContentFontStyle(fontName = "Arial", fontHeightInPoints = 10)
public abstract class BaseExcelData {

}