package vvmstk.view.retraining;

import com.jfoenix.controls.JFXTextField;
import com.mongodb.client.MongoCursor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.bson.Document;
import org.bson.types.ObjectId;
import vvmstk.db.dbo;
import vvmstk.view.retraining.data.R_data;
import vvmstk.view.retraining.data.Student;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static com.mongodb.client.model.Filters.eq;

public class retrainingController  implements Initializable {
    ObservableList<R_data> retrainingDataList = FXCollections.observableArrayList();
    ObservableList <Student> studentList = FXCollections.observableArrayList();
    Object id;

    private Student student;
    @FXML
    private JFXTextField surname;
    @FXML
    private JFXTextField firstname;
    @FXML
    private JFXTextField middlename;

     private dbo database = new dbo();
    @FXML
    private TableView <R_data> reatraingTable;
    @FXML
    private TableView<Student> studentTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getStudent();
        studentStatus(true);
        setData();
        changed();
    }

    @FXML
    private void addDov() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(retrainingController.class.getResource("/vvmstk/view/retraining/addRetraining.fxml"));
        AnchorPane pane = loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("ADD NEW ITEM");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setResizable(false);
        dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.setIconified(false);

        Scene scene = new Scene(pane);
        dialogStage.setScene(scene);
        AddRetraining dialog = loader.getController();
        dialog.setDialogStage(dialogStage,id);
        dialog.setRetrainingData(retrainingDataList);
        dialogStage.showAndWait();

    }

    private void setData(){
        List<R_data> l= new ArrayList<>();
        retrainingDataList.setAll(l);
        reatraingTable.setItems(retrainingDataList);
    }

    private void studentStatus(boolean b){
        surname.setDisable(b);
        firstname.setDisable(b);
        middlename.setDisable(b);
    }
    private void setStudent(Student student){
        surname.setText(student.getSurname());
        firstname.setText(student.getFirstname());
        middlename.setText(student.getMiddlename());
        id = student.getId();


    }
    private void getStudent(){
        MongoCursor <Document>  cursor = database.getCollection("restudent").find().iterator();
        List<Student> l = new ArrayList<>();
        l.clear();
        while (cursor.hasNext()){
            Document doc = cursor.next();
            student = new Student(doc.getObjectId("_id"),
                    doc.getString("surname"),
                    doc.getString("firstname"),
                    doc.getString("middlename"),
                    doc.getDate("dataB"),
                    doc.get("foto",org.bson.types.Binary.class));
            l.add(student);
        }
        if (!studentList.isEmpty()){
            studentList.clear();
        }
        studentList.addAll(l);
        studentTable.setItems(studentList);
    }

    private void getRetrainingById(Object id){
        List<R_data> l = new ArrayList<>();
        l.clear();

        MongoCursor <Document>  cursor = database.getCollection("retraining").find(eq("r_id",id)).iterator();
        while(cursor.hasNext()) {
            Document doc = cursor.next();
            R_data r_data = new R_data(doc.getObjectId("_id"),
                    doc.get("r_id"),
                    doc.getDate("dateBegin"),
                    doc.getDate("dateEnd"),
                    doc.getString("kateg"),
                    doc.getString("instr"),
                    doc.getString("car"),
                    doc.getString("numDov"),
                    doc.getDate("dateV"),
                    (ArrayList<LocalDate>) doc.get("dataStady"));
            l.add(r_data);
        }
        if(!retrainingDataList.isEmpty()){
            retrainingDataList.clear();
        }
        retrainingDataList.addAll(l);
        reatraingTable.setItems(retrainingDataList);
    }

    private void changed(){
        studentTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            student = new Student(newValue.getId(),
                    newValue.getSurname(),
                    newValue.getFirstname(),
                    newValue.getMiddlename(),
                    newValue.getFoto());
            setStudent(student);
            getRetrainingById(newValue.getId());
        });
    }


    @FXML
    private void addStudent() {
        studentStatus(false);
        surname.setText("");
        firstname.setText("");
        middlename.setText("");
    }

    @FXML
    private void saveUser() {
        Student student = new Student(surname.getText(),firstname.getText(),middlename.getText(),new Date(),null);
        Document doc = new Document("surname", student.getSurname())
                .append("firstname", student.getFirstname())
                .append("middlename", student.getMiddlename())
                .append("dataB",student.getDataB())
                .append("foto", student.getFoto());
        database.insertData(database.getCollection("restudent"), doc);
        //studentList.add(student);
        getStudent();
    }

    @FXML
    private void delUser() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + "this item will be deleted" + " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            System.out.println("deleted  " + id);
            database.deleteData(database.getCollection("restudent"), "_id",id);
            database.deleteData(database.getCollection("retraining"), "r_id",id);
            retrainingDataList.removeAll();

            studentList.remove(studentTable.getSelectionModel().getSelectedItem());
        }


    }

    @FXML
    private void editUser() {
    }

    @FXML
    private void delRetraining() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + "this item will be deleted" + " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            database.deleteData(database.getCollection("retraining"), "_id",reatraingTable.getSelectionModel().getSelectedItem().getId());
            retrainingDataList.remove(reatraingTable.getSelectionModel().getSelectedItem());
        }
    }

}
