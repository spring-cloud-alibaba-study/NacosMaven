package com.js.util.easyexcel.domain;

import lombok.Data;

/**
 * 窗口冻结
 */
@Data
public class FreezePane {
    private int colSplit = 0;
    private int rowSplit = 0;
    private int leftmostColumn = 0;
    private int topRow = 0;

    public FreezePane(int colSplit, int rowSplit) {
        this.colSplit = colSplit;
        this.rowSplit = rowSplit;
    }

    public FreezePane(int colSplit, int rowSplit, int leftmostColumn, int topRow) {
        this.colSplit = colSplit;
        this.rowSplit = rowSplit;
        this.leftmostColumn = leftmostColumn;
        this.topRow = topRow;
    }

}