package edu.icet.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.UserBo;
import edu.icet.bo.impl.UserBoImpl;
import edu.icet.entity.User;
import edu.icet.entity.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import edu.icet.bo.UserBo;
import edu.icet.entity.UserType;


public class LoginController {

    public AnchorPane loginPane;
    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private Label lblForget;

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

    private void openDashboard(UserBo userBo) {
//        Stage stage = (Stage) loginPane.getScene().getWindow();
//        try {
//            UserType userType = userBo.getType();
//            String dashboardPath = (userType == UserType.ADMIN) ? "/view/AdminDashboard.fxml" : "/view/UserDashboard.fxml";
//            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(dashboardPath))));
//            stage.centerOnScreen();
//            stage.setResizable(false);
//            stage.setTitle((userType == UserType.ADMIN) ? "Admin Dashboard" : "User Dashboard");
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    private void clearFields() {
        txtEmail.clear();
        txtPassword.clear();
    }
}
