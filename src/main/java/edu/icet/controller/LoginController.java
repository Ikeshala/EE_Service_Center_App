package edu.icet.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    public AnchorPane loginPane;
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
        Stage stage = (Stage) loginPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashboard.fxml"))));
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.setTitle("Admin Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
