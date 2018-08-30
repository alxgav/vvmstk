package vvmstk.view.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController  implements Initializable {
    @FXML
    private JFXTextField loginText;
    @FXML
    private JFXPasswordField passwordText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void loginBtnAction() throws IOException {
        ((Stage) loginText.getScene().getWindow()).close();
        loadStage();
    }

    @FXML
    private void canselBtnAction() {
        System.exit(0);
    }

    @FXML
    private void exitAction() {
        System.exit(0);
    }

    private void loadStage() throws IOException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Parent parent = FXMLLoader.load(getClass().getResource("/vvmstk/view/main/main.fxml"));
        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/vvmstk/img/gerb_tsou.png")));
        stage.setTitle("");


        stage.setScene(new Scene(parent, screenSize.getWidth() - 50, screenSize.getHeight() - 50));
        stage.show();
    }
}
