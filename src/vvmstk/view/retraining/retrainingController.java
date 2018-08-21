package vvmstk.view.retraining;

import com.jfoenix.controls.*;
import com.mongodb.client.MongoCursor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.bson.Document;
import org.bson.types.ObjectId;
import vvmstk.db.db.Teacher;
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
    @FXML
    private StackPane printPane;

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

    @FXML
    private void kartkaAction(MouseEvent mouseEvent) {
    }

    @FXML
    private void dovAction() {
        String dov = reatraingTable.getSelectionModel().getSelectedItem().getNumDov();
        if (dov.isEmpty()){
            JFXTextField dovText = new JFXTextField();
            dovText.setPromptText("№ ДОВІДКИ");
            dovText.setLabelFloat(false);
            dovText.setPrefSize(150, 50);
            dovText.setPadding(new Insets(10, 5, 10, 5));
            dovText.setStyle("-fx-font-size:13px; -fx-font-weight:bold;-fx-text-fill:#2A2E37");

            VBox vb = new VBox();
            vb.setSpacing(8);
            vb.getChildren().addAll(dovText);

            // Heading text
            Text t = new Text("ДОВІДКА ПРО ЗАКІНЧЕННЯ ПЕРЕПІДГОТОВКИ");
            t.setStyle("-fx-font-size:14px;");

            JFXDialogLayout dialogLayout = new JFXDialogLayout();
            dialogLayout.setHeading(t);
            dialogLayout.setPrefSize(300,50);

            dialogLayout.setBody(vb);

            JFXDialog dialog = new JFXDialog(printPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
            //dialog.setPrefHeight(50);
            // close button
            JFXButton closeButton = new JFXButton("Відмінити");
            closeButton.setStyle("-fx-button-type: RAISED;-fx-background-color: rgb(77,102,204);-fx-font-size: 14px;-fx-text-fill: WHITE;");
            //Add button
            JFXButton addBtn = new JFXButton("Додати");
            addBtn.setStyle("-fx-button-type: RAISED;-fx-background-color: rgb(77,102,204);-fx-font-size: 14px;-fx-text-fill: WHITE;"
                    + "");
            closeButton.setOnAction((ActionEvent event1) -> {
                dialog.close();
            });
            addBtn.setOnAction((ActionEvent event1) -> {
                Object _id = reatraingTable.getSelectionModel().getSelectedItem().getId();
                database.updateDataID(database.getCollection("retraining"),_id,new Document("numDov", dovText.getText()));

                dialog.close();
            });

            HBox box=new HBox();
            box.setSpacing(10);
            box.setPrefSize(200, 50);
            box.setAlignment(Pos.CENTER_RIGHT);
            box.getChildren().addAll(addBtn,closeButton);

            dialogLayout.setActions(box);

            dialog.show();
        } else {

        }

    }
}
