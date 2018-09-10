package vvmstk.raport.xls;

import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.poi.ss.usermodel.*;
import org.bson.Document;
import vvmstk.db.db.Student;
import vvmstk.db.dbo;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class importData {

    final static String GRAPHIC = "in/g.xls";



    public importData() {
    }

    public void setImport(String group) throws IOException, BiffException, ParseException {
        dbo database = new dbo();
        File f = new File("xls");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        if(f.isDirectory()){
            for(File c:f.listFiles()){
                Workbook workbook = Workbook.getWorkbook(new File("xls/"+c.getName()));
                Sheet sheet = workbook.getSheet(0);
                Student student = new Student(group,
                        sheet.getCell(6,1).getContents(),
                        sheet.getCell(6,2).getContents(),
                        sheet.getCell(6,3).getContents(),
                        sheet.getCell(6,6).getContents(),
                        (!"".equals(sheet.getCell(6,4).getContents()))?sdf.parse(sheet.getCell(6,4).getContents()):new Date(),
                        sheet.getCell(4,10).getContents(),
                        sheet.getCell(9,10).getContents()+" "+sheet.getCell(4,11).getContents(),
                        sheet.getCell(4,8).getContents(),
                        (!"".equals(sheet.getCell(7, 10).getContents()))?sdf.parse(sheet.getCell(7, 10).getContents()):new Date(),
                        sheet.getCell(4,12).getContents(),
                        sheet.getCell(9,12).getContents()+" "+sheet.getCell(4,13).getContents(),
                        (!"".equals(sheet.getCell(7, 12).getContents()))?sdf.parse(sheet.getCell(7, 12).getContents()):new Date());

                Document doc = new Document("group",student.getGroup()).
                        append("surname",student.getSurname()).
                        append("firstname", student.getFirstname()).
                        append("middlename", student.getMiddlename()).
                        append("inn", student.getInn()).
                        append("datebirth",student.getDataB()).
                        append("numpass", student.getPasp()).
                        append("passvyd", student.getPaspVyd()).
                        append("address", student.getAddress()).
                        append("passdate", student.getPaspData()).
                        append("mednum", student.getMedNum()).
                        append("hosp", student.getHosp()).
                        append("meddate", student.getDataMed());
                database.insertData(database.getCollection("student"),doc);
                workbook.close();

            }
        }
    }


    public void setGraphic() throws IOException, BiffException {
        String student ="";
        String data="";
        String tema="";
        String tema_time;
        String t1="";
        String t2="";

        String[] time = {"одна", "дві", " ", "00", "30"};

        Workbook workbook = Workbook.getWorkbook(new File(GRAPHIC));
        for(int i=0;i<=workbook.getNumberOfSheets()-1;i++){
            int colStudent=0;
            Sheet sheet = workbook.getSheet(i);
            System.out.println("==========SART==========="+sheet.getName()+"========================"+sheet.getColumns());
            for(int st=5;st<=sheet.getRows()-1;st++){
                if(!sheet.getCell(1,st).getContents().equals("")&&sheet.getCell(1,st).getContents().length()>4){
                    colStudent++;

                }
            }
            System.out.println("count of student "+colStudent);
            for(int row=1;row<=colStudent;row++){ // количество студентов
                student = sheet.getCell(1,row+5).getContents();
                int r = 6;
                for(int col=5;col<=sheet.getColumns()-1;col++){ // количество колонок
                    DateCell dc = (DateCell) sheet.getCell(col, 5);
                    data = new SimpleDateFormat("dd.MM.yyyy").format(dc.getDate()) ;
                    tema = sheet.getCell(col, r).getContents();
                    String [] t = tema.split("/");
                    if(!"".equals(tema)){
                        tema_time=t[1];
                        tema=t[0];

                        if("1".equals(tema_time)){
                            t1= time[0];
                            t2 = time[3];
                        }
                        if("2".equals(tema_time)){
                            t1= time[1];
                            t2 = time[3];
                        }
                        if("0.5".equals(tema_time)){
                            t1= time[2];
                            t2 = time[4];
                        }
                        System.out.println(student+": "+data+" :"+tema+": "+tema_time+" :"+t1+" :"+t2);

                    }
                }
                r++;
            }
            System.out.println("==========END==========="+sheet.getName()+"========================");
        }
        workbook.close();
    }



    public static void main(String[] arg) throws IOException, BiffException {
        DataFormatter dataFormatter = new DataFormatter();
        org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(new File(GRAPHIC));

        workbook.forEach(sheet ->{
            AtomicInteger coutOfStudent = new AtomicInteger();
            /*
            finde count of student
             */
            System.out.println("sheet name====START========="+sheet.getSheetName()+"=============== ");
 //           sheet.forEach(row ->{
//                String student="";
//                String data = "";
//                if(row.getRowNum()>4){
//                    for(int i=5;i<=row.getLastCellNum()-1;i++){
//                        data = dataFormatter.formatCellValue(row.getCell(i));
//                        if(!dataFormatter.formatCellValue(row.getCell(1)).equals("") && !row.getCell(1).getCellType().equals(CellType.FORMULA) && dataFormatter.formatCellValue(row.getCell(1)).length()>3){
//                            coutOfStudent.getAndIncrement();
//                            student = dataFormatter.formatCellValue(row.getCell(1));
//
//                        }
//                        System.out.println(student+" "+data);
//                    }
//                }
//            });

            sheet.forEach(row -> {
                if(row.getRowNum()>4){
                    row.forEach(cell -> {

                        if(cell.getCellType()!=CellType.FORMULA){
                            String cellValue = dataFormatter.formatCellValue(cell);
                            System.out.print(cellValue + "\t");
                        }
                    });
                    System.out.println();
                }

            });

            System.out.println("sheet name====END========="+sheet.getSheetName()+"=============== count "+coutOfStudent.get());
        });
        workbook.close();
    }


}


