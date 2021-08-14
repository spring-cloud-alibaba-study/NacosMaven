package com.js.util.easyexcel.enums;

/**
 * Excel背景颜色枚举类
 *
 * @author test
 */
public enum ExcelColor {

    RED((short) 10), YELLOW((short) 53), BLUE((short) 30), GREEN((short) 57);

    public final short value;

    private ExcelColor(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }
}