package vvmstk.view.main.toolbar;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import vvmstk.view.main.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Toolbar implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void loadGroup() throws IOException {
        Main.setNode(FXMLLoader.load(getClass().getResource("/vvmstk/view/student/student.fxml")));
    }

    @FXML
    private void loadRetraining(ActionEvent actionEvent) throws IOException {
       // loadContent("/vvmstk/view/retraining/retraining.fxml");
        Main.setNode(FXMLLoader.load(getClass().getResource("/vvmstk/view/retraining/retraining.fxml")));
    }

    @FXML
    private void loadAccounting(ActionEvent actionEvent) {
    }

    @FXML
    private void loadSetting(ActionEvent actionEvent) {
    }

    public static Object loadWindow(URL loc, String title, Stage parentStage) {
        Object controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(loc);
            Parent parent = loader.load();
            controller = loader.getController();
            Stage stage;
            if (parentStage != null) {
                stage = parentStage;
            }
            else {
                stage = new Stage(StageStyle.DECORATED);
            }
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        }
        catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return controller;
    }



}
