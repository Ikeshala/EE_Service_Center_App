package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserManagementController {

    @FXML
    private AnchorPane userPane;

    @FXML
    private JFXComboBox<?> cmbUserId;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXComboBox<?> cmbType;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXTreeTableView<?> tblItem;

    @FXML
    private TreeTableColumn<?, ?> colUserId;

    @FXML
    private TreeTableColumn<?, ?> colUserName;

    @FXML
    private TreeTableColumn<?, ?> colEmail;

    @FXML
    private TreeTableColumn<?, ?> colType;

    @FXML
    private TreeTableColumn<?, ?> colAction;

    @FXML
    void backButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) userPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashboard.fxml"))));
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logoutButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) userPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Login.fxml"))));
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void registerViewLinkButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) userPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserRegistration.fxml"))));
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.setTitle("User Registration");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchButtonOnAction(ActionEvent event) {

    }

    @FXML
    void updateButtonOnAction(ActionEvent event) {

    }

}
