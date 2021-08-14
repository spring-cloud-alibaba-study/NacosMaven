package com.js.util.easyexcel.handle;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.js.util.easyexcel.domain.FreezePane;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Excel冻结窗口Handler
 */
public class SheetFreezeWriteHandler implements SheetWriteHandler {

    private FreezePane freezePane;

    public SheetFreezeWriteHandler() {

    }

    public SheetFreezeWriteHandler(FreezePane freezePane) {
        this.freezePane = freezePane;
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Sheet sheet = writeSheetHolder.getSheet();

        if (freezePane != null) {
            int colSplit = freezePane.getColSplit();
            int rowSplit = freezePane.getRowSplit();
            int leftmostColumn = freezePane.getLeftmostColumn();
            int topRow = freezePane.getTopRow();

            if (leftmostColumn == 0 && topRow == 0) {
                sheet.createFreezePane(colSplit, rowSplit);
            } else {
                sheet.createFreezePane(colSplit, rowSplit, leftmostColumn, topRow);
            }
        }

    }

}