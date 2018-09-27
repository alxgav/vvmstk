package vvmstk.view.student;

import com.dropbox.core.DbxException;
import com.jfoenix.controls.*;
import com.mongodb.client.MongoCursor;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.bson.Document;
import vvmstk.config.config;
import vvmstk.config.dateCalc;
import vvmstk.config.image.imageIO;
import vvmstk.config.stringSetting;
import vvmstk.db.db.Group;
import vvmstk.db.db.Student;
import vvmstk.db.dbo;
import vvmstk.raport.odt.kontrakt;
import vvmstk.raport.odt.odtx_report;
import vvmstk.raport.xls.Raport;
import vvmstk.raport.xls.importData;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.mongodb.client.model.Filters.eq;
import static javafx.collections.FXCollections.observableArrayList;

public class student implements Initializable {
    private dbo database = new dbo();
    private Student student;
    private Group group;
    private imageIO img = new imageIO();
    private ObservableList<Student> studentList = observableArrayList();
    private Raport raport = new Raport();
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private JFXTextField surnameField;
    @FXML
    private JFXTextField firstnameField;
    @FXML
    private JFXTextField middlenameField;
    @FXML
    private JFXTextField innField;
    @FXML
    private JFXDatePicker dataBField;
    @FXML
    private JFXTextField paspField;
    @FXML
    private JFXTextField paspVydField;
    @FXML
    private JFXTextField addressField;
    @FXML
    private JFXDatePicker paspDataField;
    @FXML
    private JFXTextField medNumField;
    @FXML
    private JFXTextField hospField;
    @FXML
    private JFXDatePicker dataMedField;
    @FXML
    private JFXComboBox groups;
    @FXML
    private Label groupLabel;
    @FXML
    private Label dataBLabel;
    @FXML
    private Label kategLabel;
    @FXML
    private Label dataELabel;
    @FXML
    private ImageView imageStudent;
    @FXML
    private StackPane surnamePane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setGroups();

        changed();
    }

    private void getStudent(){
        MongoCursor<Document> cursor = database.getCollection("student").find(eq("group",groups.getSelectionModel().getSelectedItem().toString())).iterator();
        List<Student> l= new ArrayList<>();
        l.clear();
        while (cursor.hasNext()){
            Document doc = cursor.next();
            student = new Student(doc.getObjectId("_id"),
                    doc.getString("group"),
                    doc.getString("surname"),
                    doc.getString("firstname"),
                    doc.getString("middlename"),
                    doc.getString("inn"),
                    doc.getDate("datebirth"),
                    doc.getString("numpass"),
                    doc.getString("passvyd"),
                    doc.getString("address"),
                    doc.getDate("passdate"),
                    doc.getString("mednum"),
                    doc.getString("hosp"),
                    doc.getDate("meddate"),
                    doc.get("foto",org.bson.types.Binary.class));
            l.add(student);
        }
        if(!studentList.isEmpty()){
            studentList.clear();
        }
        studentList.addAll(l);
        studentTable.setItems(studentList);

    }

    private void changed(){

        studentTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            student = new Student(newValue.getId(),
                    newValue.getGroup(),
                    newValue.getSurname(),
                    newValue.getFirstname(),
                    newValue.getMiddlename(),
                    newValue.getInn(),
                    newValue.getDataB(),
                    newValue.getPasp(),
                    newValue.getPaspVyd(),
                    newValue.getAddress(),
                    newValue.getPaspData(),
                    newValue.getMedNum(),
                    newValue.getHosp(),
                    newValue.getDataMed(),
                    newValue.getFoto());

            setStudent(student);

        });

    }

    private void setStudent(Student student){
        surnameField.setText(student.getSurname());
        firstnameField.setText(student.getFirstname());
        middlenameField.setText(student.getMiddlename());
        innField.setText(student.getInn());
        if (student.getDataB()!=null){
            dataBField.setValue(student.getDataB().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }

        paspField.setText(student.getPasp());
        if (student.getPaspData()!=null){
            paspDataField.setValue(student.getPaspData().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }

        paspVydField.setText(student.getPaspVyd());
        addressField.setText(student.getAddress());
        medNumField.setText(student.getMedNum());
        hospField.setText(student.getHosp());
        if (student.getDataMed()!=null){
            dataMedField.setValue(student.getDataMed().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }

        try {
           // getFoto(student.getFoto());
            img.getFoto(student.getFoto(),imageStudent);
        } catch (IOException e) {
            System.out.println("foto upsend");
        }


    }


    @FXML
    private void settingAction() throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(mainController.class.getResource("/vvmstk/view/groups/groups.fxml"));
//        BorderPane page = loader.load();
//        Stage dialogStage = new Stage();
//        dialogStage.setTitle("ADD NEW ITEM");
//        dialogStage.initModality(Modality.APPLICATION_MODAL);
//        dialogStage.setResizable(false);
//        dialogStage.initStyle(StageStyle.UNDECORATED);
//        dialogStage.setIconified(false);
//
//        Scene scene = new Scene(page);
//        dialogStage.setScene(scene);
//        groupsController controller = loader.getController();
//        controller.setDialogStage(dialogStage);
//        dialogStage.showAndWait();
    }

    @FXML
    private void groupAction() {
        Format formatter = new SimpleDateFormat("dd.MM.yyyy");
        kategLabel.setText(setGroup().getKateg());
        dataBLabel.setText("ДАТА ПОЧАТКУ \n"+formatter.format(setGroup().getDateBegin()));
        dataELabel.setText("ДАТА ЗАКІНЧЕННЯ \n"+formatter.format(setGroup().getDateEnd()));
        getStudent();
        groupLabel.setText(setGroup().getGroupNum()+"\n\u25AC\n всього учнів "+studentList.size());



    }

    private Group setGroup(){
        for (Document doc : database.getCollection("groups").find(eq("group", groups.getSelectionModel().getSelectedItem().toString()))) {
            group = new Group(doc.getString("group"),
                    doc.getString("kateg"),
                    doc.getDate("dateB"),
                    doc.getDate("dateE"),
                    doc.getInteger("price"),
                    doc.getInteger("termin"));

        }
        return group;

    }


    private void setGroups(){
        List<String> l = new ArrayList<>();
        l.clear();
        for (Document doc : database.getCollection("groups").find()) {
            l.add(doc.getString("group"));
        }
        groups.setItems(observableArrayList(l));

    }



    @FXML // import data from excel
    private void importBtnAction() throws ParseException, IOException, BiffException {
        /*remove to other button important*/
        importData id = new importData();
        id.setImport(groups.getSelectionModel().getSelectedItem().toString());

    }






    private void setFoto(Object id)throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedImage originalImage = ImageIO.read(new File("tmp_img/out.jpg"));
        ImageIO.write(originalImage, "jpg", baos);
        baos.flush();
        database.updateDataID(database.getCollection("student"),id,new Document("foto",baos.toByteArray()));
    }

    private  void openODT()  {
        if(Desktop.isDesktopSupported()){
            new Thread(()->{
                try {
                    Desktop.getDesktop().open(new File("template.odt"));
                } catch (IOException e) {
                    System.out.println(e.toString());
                }
            }).start();


        }

    }


    @FXML
    private void contractAction() throws IOException, XDocReportException {
        String surname = surnameField.getText()+" "+firstnameField.getText()+" "+middlenameField.getText();
        String passport = paspField.getText()+" "+paspVydField.getText()+" "+paspDataField.getValue().toString();
        odtx_report odtx = new odtx_report();
        IXDocReport report ;
        report = odtx.loadODT("kontrakt");
        FieldsMetadata fieldsMetadata = report.createFieldsMetadata();

        fieldsMetadata.load("kontrakt", kontrakt.class, true);
        List<kontrakt> content = new ArrayList<>();
        IContext context = report.createContext();
        Group gr = setGroup();
        content.add(new kontrakt(dataBLabel.getText(),
                surname,
                kategLabel.getText(),
                gr.getTermin().toString(),
                gr.getPrice().toString(),
                passport,
                addressField.getText()));
        context.put("DATA1",dataBLabel.getText());
        context.put("KATEGORIJA",kategLabel.getText() );
        context.put("SURNAME",surname);
        context.put("TERMIN",gr.getTermin().toString());
        context.put("VARTIST",gr.getPrice().toString());
        context.put("SURNAME2",new stringSetting().getSurnameInic(surname));
        context.put("kontrakt",content);
        OutputStream out = odtx.outODT();
        report.process(context,out);
        openODT();
    }

    @FXML
    private void driveAction() throws WriteException, IOException, BiffException, org.json.simple.parser.ParseException {

        ArrayList<Object> inst = new config().getTeacher("Водіння ТЗ");
        ArrayList<Object> car = new config().getCar(kategLabel.getText());
        System.out.println(kategLabel.getText());
        System.out.println(car.size());
        String instruktor = "";
        String cars = "";
        for (Object o: inst){
            instruktor = instruktor +", "+ new stringSetting().getSurnameInic(o.toString());
        }
        for (Object o: car){
            cars = cars +", "+ o;
        }
        raport.make_ind_kart(getSt(), setGroup(), instruktor.substring(1), cars.substring(1), kategLabel.getText());
        raport.openFILE("out/tmp.xls");
    }

    @FXML
    private void orderAction() throws WriteException, IOException, BiffException {
        raport.make_zajava(studentList, kategLabel.getText(),setGroup());
        raport.openFILE("out/tmp.xls");
    }

    @FXML
    private void anketaAction() throws WriteException, IOException, BiffException {

        raport.make_anketa(getSt(),kategLabel.getText());
        raport.openFILE("out/tmp.xls");

    }

    private Student getSt(){
        studentTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)-> student = new Student(newValue.getId(),
                newValue.getGroup(),
                newValue.getSurname(),
                newValue.getFirstname(),
                newValue.getMiddlename(),
                newValue.getInn(),
                newValue.getDataB(),
                newValue.getPasp(),
                newValue.getPaspVyd(),
                newValue.getAddress(),
                newValue.getPaspData(),
                newValue.getMedNum(),
                newValue.getHosp(),
                newValue.getDataMed(),
                newValue.getFoto()));
        return  student;
    }

    @FXML
    private void downloadImage() throws IOException {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Виберіть зображення учня");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("image Files", "*.jpg")
                ,new FileChooser.ExtensionFilter("image Files", "*.png")
        );
        File fc = fileChooser.showOpenDialog(new Stage());
        File fotoFile = new File(fc.getAbsolutePath());
        saveImage(fotoFile);
    }

    @FXML
    private void downloadFromDropbox() throws IOException, DbxException {
            img.getDropBox();
            File fotoFile = new File("scan_img/tmp.jpg");
            File f = new File("tmp_img/out.jpg");
            img.scale(fotoFile, 168, 209, f);
            imageStudent.setImage(new Image(f.toURI().toString()));
            setFoto(studentTable.getSelectionModel().getSelectedItems().get(0).getId());
    }

    private void saveImage(File fotoFile) throws IOException {
        File f = new File("tmp_img/out.jpg");
        img.scale(fotoFile, 168, 209, f);
        imageStudent.setImage(new Image(f.toURI().toString()));
        setFoto(studentTable.getSelectionModel().getSelectedItems().get(0).getId());
    }

    @FXML
    private void graphBtnAction() {
        JFXDatePicker startDay = new JFXDatePicker();
        JFXCheckBox d1 = new JFXCheckBox("понеділок");
        JFXCheckBox d2 = new JFXCheckBox("вівторок");
        JFXCheckBox d3 = new JFXCheckBox("середа");
        JFXCheckBox d4 = new JFXCheckBox("четвер");
        JFXCheckBox d5 = new JFXCheckBox("п'ятниця");
        startDay.setPrefSize(150, 50);
        startDay.setPadding(new Insets(10, 5, 10, 5));
        startDay.setStyle("-fx-font-size:13px; -fx-font-weight:bold;-fx-text-fill:#2A2E37");

        VBox vb = new VBox();
        vb.setSpacing(8);

        vb.getChildren().addAll(startDay,d1,d2,d3,d4,d5);

        // Heading text
        Text t = new Text("РОЗДРУКУВАТИ ПЛАН ЗАНЯТЬ");
        t.setStyle("-fx-font-size:14px;");

        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(t);
        dialogLayout.setPrefSize(300,50);

        dialogLayout.setBody(vb);

        JFXDialog dialog = new JFXDialog(surnamePane, dialogLayout, JFXDialog.DialogTransition.CENTER);
        // close button
        JFXButton closeButton = new JFXButton("Відмінити");
        closeButton.setStyle("-fx-button-type: RAISED;-fx-background-color: rgb(36,42,43);-fx-font-size: 14px;-fx-text-fill: WHITE;");
        //Add button
        JFXButton addBtn = new JFXButton("Додати");
        addBtn.setStyle("-fx-button-type: RAISED;-fx-background-color: rgb(36,42,43);-fx-font-size: 14px;-fx-text-fill: WHITE;"
                + "");
        closeButton.setOnAction((ActionEvent event1) -> dialog.close());
        addBtn.setOnAction((ActionEvent event1) -> {
                ArrayList<Integer> days = new ArrayList<>();
                if (d1.isSelected()){
                    days.add(1);
                }
                if (d2.isSelected()){
                    days.add(2);
                }
                if (d3.isSelected()){
                    days.add(3);
                }
                if (d4.isSelected()){
                    days.add(4);
                }
                if (d5.isSelected()){
                    days.add(5);
                }

            try {


                new Raport().make_plan(new dateCalc().listDaysOfPlane_B(startDay.getValue(),38,days));
                raport.openFILE("out/tmp.xls");
            } catch (IOException | WriteException | BiffException | org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }


            dialog.close();
        });
        HBox box=new HBox();
        box.setSpacing(10);
        box.setPrefSize(200, 50);
        box.setAlignment(Pos.CENTER_RIGHT);
        box.getChildren().addAll(addBtn,closeButton);

        dialogLayout.setActions(box);

        dialog.show();

    }

    @FXML
    private void driveBtnAction() throws IOException, BiffException {
//        Document doc = new Document().append("data","07.08.2018").append("tema","1").append("tema_t","2");
//        database.getCollection("student").updateOne(eq("_id",studentTable.getSelectionModel().getSelectedItem().getId()), Updates.addToSet("garphic",doc));


    }

    @FXML
    private void vidomistAction() throws WriteException, IOException, BiffException {
        raport.make_vedomost(studentList);
        raport.openFILE("out/tmp.xls");
    }
}