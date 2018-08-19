package vvmstk.xls;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;


public class ReportSetting {

//    final public WritableFont wf12_i_b = new WritableFont(WritableFont.ARIAL,12, WritableFont.BOLD,true);
//    final public WritableFont wf18_b = new WritableFont(WritableFont.ARIAL,18, WritableFont.BOLD);
//    final public WritableFont wf16_b = new WritableFont(WritableFont.ARIAL,16, WritableFont.BOLD);
//    final public WritableFont wf11 = new WritableFont(WritableFont.ARIAL,11);
//    final public WritableFont wf12 = new WritableFont(WritableFont.ARIAL,12);

    public ReportSetting() {

    }



    public WritableCellFormat cell(WritableFont font,
                                   boolean wrap,
                                   Alignment aligment,
                                   VerticalAlignment verticalAlignment,
                                   Border border,
                                   BorderLineStyle borderLineStyle) throws WriteException {
        WritableCellFormat cell = new WritableCellFormat(font);
        cell.setWrap(wrap);
        cell.setAlignment(aligment);
        cell.setVerticalAlignment(verticalAlignment);
        cell.setBorder(border,borderLineStyle);


        return  cell;
    }
}
