package vvmstk.view.retraining;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vvmstk.db.dbo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class retrainingController  implements Initializable {
    @FXML
    private JFXTextField surname;
    @FXML
    private JFXTextField firstname;
    @FXML
    private JFXTextField middlename;

     private dbo database = new dbo();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            studentStatus(true);
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
        dialog.setDialogStage(dialogStage);
        dialogStage.showAndWait();

    }

    private void studentStatus(boolean b){
        surname.setDisable(b);
        firstname.setDisable(b);
        middlename.setDisable(b);
    }


    @FXML
    private void addStudent(MouseEvent mouseEvent) {
    }

    @FXML
    private void saveUser(MouseEvent mouseEvent) {
    }

    @FXML
    private void delUser(MouseEvent mouseEvent) {
    }

    @FXML
    private void editUser(MouseEvent mouseEvent) {
    }
}
