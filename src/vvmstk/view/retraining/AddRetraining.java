package vvmstk.view.retraining;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.bson.Document;
import org.json.simple.parser.ParseException;
import vvmstk.config.config;
import vvmstk.config.dateCalc;
import vvmstk.db.dbo;
import vvmstk.view.retraining.data.R_data;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class AddRetraining implements Initializable {


   private dbo database = new dbo();

    private Stage dialogStage;
    @FXML
    private JFXDatePicker dataB;
    @FXML
    private JFXDatePicker dataE;
    @FXML
    private JFXComboBox <Object> kateg;
    @FXML
    private JFXComboBox <Object> instr;
    @FXML
    private JFXComboBox <Object> car;
    @FXML
    private JFXListView<Object> listData;
    @FXML
    private JFXTextField numD;
    @FXML
    private JFXDatePicker dataV;

    ObservableList<R_data> retrainingList;
    private Object id;

    void setDialogStage(Stage dialogStage, Object id) {
        this.dialogStage = dialogStage;
        this.id = id;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            kateg.setItems(FXCollections.observableArrayList(new config().getListofValues("kateg")));
            instr.setItems(FXCollections.observableArrayList(new config().getTeacher("Водіння ТЗ")));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @FXML //close dialog
    private void btnCansel() {
        dialogStage.close();
    }


    @FXML
    private void KategAction() throws IOException, ParseException {
        car.setItems(FXCollections.observableArrayList(new config().getCar(kateg.getSelectionModel().getSelectedItem().toString())));
    }

    @FXML
    private void dataBaction() throws IOException, ParseException {
        listData.setItems(FXCollections.observableArrayList(new dateCalc().listOfDayas(dataB.getValue(), 10)));
        dataE.setValue(LocalDate.parse(listData.getItems().get(listData.getItems().size()-1).toString()));
        dataV.setValue(dataE.getValue());
    }

    @FXML
    private void dataVaction() {
        numD.setEditable(true);
        numD.setDisable(false);
    }

    void setRetrainingData(ObservableList<R_data> retrainingList){
        this.retrainingList = retrainingList;
    }

    private R_data setRetraining(){
        ArrayList d = new ArrayList();
        d.addAll(listData.getItems());
        return  new R_data(id,Date.from(dataB.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                            Date.from(dataE.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                            kateg.getSelectionModel().getSelectedItem().toString(),
                            instr.getSelectionModel().getSelectedItem().toString(),
                            car.getSelectionModel().getSelectedItem().toString(),
                            numD.getText(),
                            Date.from(dataV.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                            d);

    }
    private void saveData(R_data d){
        Document doc = new Document("r_id",id).
                append("dateBegin",d.getDateBegin()).
                append("dateEnd", d.getDateEnd()).
                append("kateg", d.getKateg()).
                append("instr", d.getInstr()).
                append("car", d.getCar()).
                append("numDov", d.getNumDov()).
                append("dateV", d.getDateV()).append("dataStady",d.getDataStady());
        database.insertData(database.getCollection("retraining"), doc);
    }



    @FXML
    private void btnSave() {

        saveData(setRetraining());
        retrainingList.add(setRetraining());
        dialogStage.close();
    }
}
