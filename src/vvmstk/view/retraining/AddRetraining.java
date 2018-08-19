package vvmstk.view.retraining;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import vvmstk.config.config;
import vvmstk.config.dateCalc;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddRetraining implements Initializable {
   // private dbo database = new dbo();

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

    void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
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
        dataE.setValue(new dateCalc().getLastD(dataB.getValue(),9));
        listData.setItems(FXCollections.observableArrayList(new dateCalc().listOfDayas(dataB.getValue(), 10)));
        dataV.setValue(dataE.getValue());
    }

    @FXML
    private void dataVaction() {
        numD.setEditable(true);
        numD.setDisable(false);
    }
}
