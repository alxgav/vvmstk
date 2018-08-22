package vvmstk.view.student;

import com.dropbox.core.DbxException;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.UpdateResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.bson.Document;
import org.bson.types.Binary;
import vvmstk.config.image.imageIO;
import vvmstk.db.db.*;
import vvmstk.db.dbo;
import vvmstk.xls.Raport;
import vvmstk.xls.importData;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.mongodb.client.model.Filters.eq;
import static javafx.collections.FXCollections.*;

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
                    doc.getInteger("price"));

        }
        return group;

    }

    private ArrayList<Thems> setThems(String kategorja){
        MongoCursor<Document> cursor = database.getCollection("carthems").find(eq("\"KATEGORIJA\"",kategorja)).iterator();
        ArrayList<Thems> l = new ArrayList<>();
        l.clear();
        while (cursor.hasNext()){
            Document doc = cursor.next();
            Thems thems = new Thems(doc.getString("\"NUM_TEMA\""), doc.getString("\"T_TIME\""), doc.getString("\"T_OPIS\""));
            l.add(thems);
        }
        return l;
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


    @FXML
    private void contractAction() {
    }

    @FXML
    private void driveAction() throws WriteException, IOException, BiffException {


        raport.make_ind_kart(getSt(), setGroup(), setThems(kategLabel.getText()), kategLabel.getText());
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
}