package vvmstk.raport.xls;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;


public class ReportSetting {


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
