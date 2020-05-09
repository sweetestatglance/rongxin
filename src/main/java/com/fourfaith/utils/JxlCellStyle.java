//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

public class JxlCellStyle {
    public JxlCellStyle() {
    }

    public static WritableCellFormat getHeaderCellStyle() {
        WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
        WritableCellFormat headerFormat = new WritableCellFormat(NumberFormats.TEXT);

        try {
            headerFormat.setFont(font);
            headerFormat.setAlignment(Alignment.CENTRE);
        } catch (WriteException var3) {
            System.out.println("表头单元格样式设置失败！");
        }

        return headerFormat;
    }
}
