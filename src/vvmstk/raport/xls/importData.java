package vvmstk.raport.xls;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.bson.Document;
import vvmstk.db.db.Student;
import vvmstk.db.dbo;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class importData {

    private dbo database = new dbo();

    public importData() {
    }

    public void setImport(String group) throws IOException, BiffException, ParseException {
        ArrayList data = new ArrayList();
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

//                MongoCollection<Document> collection = database.getCollection("student");
//                collection.insertOne(doc);
                database.insertData(database.getCollection("student"),doc);
                workbook.close();

            }
        }
    }


}
