package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginController {

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private Label lblForget;

    @FXML
    private Label lblnvalidEmail;

    @FXML
    private Label lblInvalidPassword;

    @FXML
    void LoginButtonOnAction(ActionEvent event) {

    }

}
