package vvmstk.raport.xls;

import javafx.collections.ObservableList;
import jxl.Workbook;
import jxl.format.*;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.*;
import vvmstk.config.stringSetting;
import vvmstk.db.db.Group;
import vvmstk.db.db.Student;
import vvmstk.view.retraining.data.R_data;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Raport {

    private ReportSetting rs = new ReportSetting();

    public Raport() {

    }

    /*
    anketa
     */

    public void make_anketa(Student student, String kateg) throws IOException, BiffException, WriteException {
        Workbook wb;
        WritableWorkbook new_wb;
        WritableSheet sheet;
            wb = Workbook.getWorkbook(new File("raport/xls/anketa.xls"));
            new_wb = Workbook.createWorkbook(new File("out/tmp.xls"),wb);
            sheet = new_wb.getSheet(0);
            WritableFont wf = new WritableFont(WritableFont.ARIAL,12, WritableFont.BOLD,true);


            WritableCellFormat cell1 = rs.cell(wf,true,Alignment.LEFT,VerticalAlignment.CENTRE,Border.NONE,BorderLineStyle.NONE);
            WritableCellFormat cell3 = rs.cell(wf,true,Alignment.LEFT,VerticalAlignment.TOP,Border.NONE,BorderLineStyle.NONE);
            WritableImage wi2 = new WritableImage(1, 9, 5, 9, student.getFoto().getData());
            sheet.addImage(wi2);


            sheet.addCell(new Label(6, 9,student.getSurname(), cell1));
            sheet.addCell(new Label(6, 11,student.getFirstname(), cell1));
            sheet.addCell(new Label(6, 13,student.getMiddlename(), cell1));
            sheet.addCell(new Label(6, 17,new SimpleDateFormat("dd.MM.yyyy").format(student.getDataB()), cell1));
            sheet.addCell(new Label(4, 19,student.getPasp(), cell1));
            sheet.addCell(new Label(1, 21,student.getPaspVyd(), cell1));
            sheet.addCell(new Label(1, 23,new SimpleDateFormat("dd.MM.yyyy").format(student.getPaspData()), cell1));
            sheet.addCell(new Label(1, 26,student.getAddress(), cell3));
            sheet.addCell(new Label(6, 30,student.getHosp(), cell1));
            sheet.addCell(new Label(4, 31,student.getMedNum(), cell1));
            sheet.addCell(new Label(14, 31,new SimpleDateFormat("dd.MM.yyyy").format(student.getDataMed()), cell1));
            sheet.addCell(new Label(1, 37,student.getInn(), cell1));
        sheet.addCell(new Label(22, 43,"''"+kateg+"''", cell1));
        new_wb.write();
        new_wb.close();
        wb.close();
    }

    /*
     * индивидуальная карта
     */
    public void make_ind_kart(Student student, Group group, String instruktor, String cars,  String kateg) throws IOException, BiffException, WriteException{

            WritableFont wf18_b = new WritableFont(WritableFont.ARIAL,18, WritableFont.BOLD);
            WritableFont wf12 = new WritableFont(WritableFont.ARIAL,12);

            Workbook wb;
            WritableWorkbook new_wb;

            wb = Workbook.getWorkbook(new File("raport/xls/kateg"+kateg+".xls"));
            new_wb = Workbook.createWorkbook(new File("out/tmp.xls"),wb);
            WritableSheet sheet = new_wb.getSheet(0);

            WritableCellFormat cell_surname = rs.cell(wf18_b,true,Alignment.LEFT,VerticalAlignment.TOP,Border.NONE,BorderLineStyle.NONE);
            WritableCellFormat cell_common = rs.cell(wf12,false,Alignment.LEFT,VerticalAlignment.CENTRE,Border.NONE,BorderLineStyle.NONE);
            sheet.addCell(new Label(8, 8,student.getSurname()+" "+student.getFirstname()+" "+student.getMiddlename(), cell_surname));

            WritableImage wi2 = new WritableImage(0, 8, 5, 9, student.getFoto().getData());
            sheet.addImage(wi2);
            sheet.addCell(new Label(11, 10,student.getGroup(), cell_common));
            sheet.addCell(new Label(6, 14,instruktor, cell_common));
            sheet.addCell(new Label(6, 16,cars, cell_common));
            sheet.addCell(new Label(12, 11,group.getDateBegin().toString(), cell_common));
            sheet.addCell(new Label(12, 12,group.getDateEnd().toString(), cell_common));
            new_wb.write();
            new_wb.close();
            wb.close();
    }

    /*
    make zajava
     */
    public void make_zajava(ObservableList<Student> studentList, String kateg, Group group) throws IOException, BiffException, WriteException{
        Workbook wb;
        WritableWorkbook new_wb;
        WritableSheet sheet;
        wb = Workbook.getWorkbook(new File("raport/xls/zajava1.xls"));
        new_wb = Workbook.createWorkbook(new File("out/tmp.xls"),wb);
        sheet = new_wb.getSheet(0);
        WritableSheet sheet2 = new_wb.getSheet(1);
        WritableFont wf = new WritableFont(WritableFont.ARIAL,12);
        WritableCellFormat cell = new WritableCellFormat(wf);
        WritableCellFormat cell_thems = rs.cell(wf,true,Alignment.LEFT,VerticalAlignment.TOP,Border.ALL,BorderLineStyle.THIN);
        sheet.addCell(new Label(9, 8, group.getGroupNum(), cell));
        sheet.addCell(new Label(18, 8, "''"+kateg+"''", cell));
        sheet.addCell(new Label(2,13, group.getDateBegin().toString(), cell));
        sheet.addCell(new Label(2,14, group.getDateEnd().toString(), cell));
        ////
        int num=1;
        int row=1;
        for(Student s: studentList){

            sheet2.addCell(new Label(0, row, Integer.toString(num), cell_thems));
            sheet2.addCell(new Label(1, row, s.getSurname()+" "+s.getFirstname()+" "+s.getMiddlename(), cell_thems));
            sheet2.addCell(new Label(2, row, new SimpleDateFormat("dd.MM.yyyy").format(s.getDataB()), cell_thems));
            sheet2.addCell(new Label(3, row, "", cell_thems));
            sheet2.addCell(new Label(4, row, "",cell_thems));
            sheet2.addCell(new Label(5, row, s.getAddress(), cell_thems));
            num++;
            row++;
        }
        sheet2.addCell(new Label(0, row+1, "\u041a\u0435\u0440\u0456\u0432\u043d\u0438\u043a \u0437\u0430\u043a\u043b\u0430\u0434\u0443 ________________________________ \u0412. \u041c. \u0413\u0430\u0437\u0443\u043a\u0456\u043d", cell));
        sheet2.addCell(new Label(0, row+2, new SimpleDateFormat("dd.MM.yyyy").format(new Date()), cell));
        sheet2.addCell(new Label(0, row+3, "\u041c\u041f", cell));
        new_wb.write();
        new_wb.close();
        wb.close();
        }
/*
make dovidka
 */
    public void make_dovidka(vvmstk.view.retraining.data.Student student, R_data r_data) throws IOException, BiffException, WriteException {
        Workbook wb;
        WritableWorkbook new_wb;
        WritableSheet sheet;
        WritableFont wf12 = new WritableFont(WritableFont.ARIAL,12);
        wb = Workbook.getWorkbook(Raport.class.getResourceAsStream("/vvmstk/raport/xls/raport/dovp.xls"));
        new_wb = Workbook.createWorkbook(new File("out/tmp.xls"),wb);
        sheet = new_wb.getSheet(0);
        WritableSheet sheet2 = new_wb.getSheet(1);
        WritableCellFormat cell = new WritableCellFormat(wf12);
        WritableCellFormat cell2 = rs.cell(wf12,true,Alignment.CENTRE,VerticalAlignment.CENTRE,Border.ALL,BorderLineStyle.THIN);
        cell2.setOrientation(Orientation.PLUS_90);
        sheet.addCell(new Label(5, 7, student.getSurname()+" "+student.getFirstname()+" "+student.getMiddlename(), cell));
        sheet.addCell(new Label(11, 1, r_data.getNumDov(), cell));
        sheet.addCell(new Label(23, 9, "\""+r_data.getKateg()+"\"", cell));
        sheet2.addCell(new Label(4, 0, r_data.getInstr(), cell));
        sheet2.addCell(new Label(3, 1, r_data.getCar(), cell));
        sheet2.addCell(new Label(8, 13, new stringSetting().getSurnameInic(r_data.getInstr()), cell));
        sheet2.addCell(new Label(0, 3, student.getSurname()+" "+student.getFirstname()+" "+student.getMiddlename(), cell));
        sheet2.addCell(new Label(6, 15, student.getSurname()+" "+student.getFirstname().substring(0,1)+". "+student.getMiddlename().substring(0,1)+".", cell));
        /*
        fill datas
         */
        int col =1;
        for(Object o: r_data.getDataStady()){
            sheet2.addCell(new Label(col, 9,new SimpleDateFormat("dd.MM.yyyy").format(o), cell2));
            col++;
        }
        new_wb.write();
        new_wb.close();
        wb.close();
    }

/*
open file
 */

    public   void openFILE(String filename) {
        if(Desktop.isDesktopSupported()){
            new Thread(()->{
                try {
                    Desktop.getDesktop().open(new File(filename));
                } catch (IOException e) {
                    System.out.println(e.toString());
                }
            }).start();


        }

    }
/*
perepidgotovka
 */

    public void make_kartka(vvmstk.view.retraining.data.Student st, R_data r_data) throws IOException, BiffException, WriteException {
        WritableFont wf18_b = new WritableFont(WritableFont.ARIAL,18, WritableFont.BOLD);
        WritableFont wf12 = new WritableFont(WritableFont.ARIAL,12);
        WritableFont wf12b = new WritableFont(WritableFont.ARIAL,12, WritableFont.BOLD);

        Workbook wb;
        WritableWorkbook new_wb;

        wb = Workbook.getWorkbook(new File("raport/xls/perepidgotovka.xls"));
        new_wb = Workbook.createWorkbook(new File("out/tmp.xls"),wb);
        WritableSheet sheet = new_wb.getSheet(0);
        WritableCellFormat cell2 = rs.cell(wf12,true,Alignment.CENTRE,VerticalAlignment.CENTRE,Border.ALL,BorderLineStyle.THIN);
        WritableCellFormat cell_surname = rs.cell(wf18_b,true,Alignment.LEFT,VerticalAlignment.TOP,Border.NONE,BorderLineStyle.NONE);
        WritableCellFormat cell_common = rs.cell(wf12,false,Alignment.LEFT,VerticalAlignment.CENTRE,Border.NONE,BorderLineStyle.NONE);
        WritableCellFormat cell_center = rs.cell(wf12b,false,Alignment.CENTRE,VerticalAlignment.CENTRE,Border.NONE,BorderLineStyle.NONE);
        sheet.addCell(new Label(8, 8,st.getSurname()+" "+st.getFirstname()+" "+st.getMiddlename(), cell_surname));

        WritableImage wi2 = new WritableImage(0, 8, 5, 9, st.getFoto().getData());
        sheet.addImage(wi2);
        sheet.addCell(new Label(0, 6,"обліку навчання на тренажерах і водіння транспортних засобів категорії \""+r_data.getKateg()+"\"", cell_center));
        sheet.addCell(new Label(6, 14,new stringSetting().getSurnameInic(r_data.getInstr()), cell_common));
        sheet.addCell(new Label(6, 16,r_data.getCar(), cell_common));
        sheet.addCell(new Label(12, 11,r_data.getDateBegin().toString(), cell_common));
        sheet.addCell(new Label(12, 12,r_data.getDateEnd().toString(), cell_common));
        int row = 24;
        sheet.addCell(new Label(0, 23,new SimpleDateFormat("dd.MM.yyyy").format(r_data.getDataStady().get(0)), cell2));
        for (Object o:r_data.getDataStady()){
            sheet.addCell(new Label(0, row,new SimpleDateFormat("dd.MM.yyyy").format(o), cell2));
            row++;
        }
        new_wb.write();
        new_wb.close();
        wb.close();
    }

    /*
    make plane
     */
    public void make_plan(ArrayList<LocalDate> days) throws IOException, BiffException, WriteException {
        Workbook wb;
        WritableWorkbook new_wb;
        WritableSheet sheet;
        WritableFont wf12 = new WritableFont(WritableFont.ARIAL,12);
        wb = Workbook.getWorkbook(new File("raport/xls/plane_B.xls"));
        new_wb = Workbook.createWorkbook(new File("out/tmp.xls"),wb);
        sheet = new_wb.getSheet(0);
        WritableCellFormat cell = new WritableCellFormat(wf12);
        WritableCellFormat cell2 = rs.cell(wf12,true,Alignment.CENTRE,VerticalAlignment.CENTRE,Border.ALL,BorderLineStyle.THIN);
        cell2.setOrientation(Orientation.PLUS_90);
//        sheet.addCell(new Label(5, 7, student.getSurname()+" "+student.getFirstname()+" "+student.getMiddlename(), cell));
//        sheet.addCell(new Label(11, 1, r_data.getNumDov(), cell));
//        sheet.addCell(new Label(23, 9, "\""+r_data.getKateg()+"\"", cell));
        /*
        fill datas
         */
        int col =5;
        for(LocalDate o: days){
            sheet.addCell(new Label(col++, 17,o.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), cell2));
            //col++;
        }
        new_wb.write();
        new_wb.close();
        wb.close();
    }
}
