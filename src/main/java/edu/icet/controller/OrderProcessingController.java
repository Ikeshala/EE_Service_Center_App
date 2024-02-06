package edu.icet.controller;

import com.jfoenix.controls.JFXButton;
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

public class OrderProcessingController {

    @FXML
    private AnchorPane orderProcessingPane;

    @FXML
    private JFXTextField txtCustomerId;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXComboBox<?> cmbStatus;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXTreeTableView<?> tblItem;

    @FXML
    private TreeTableColumn colOrderId;

    @FXML
    private TreeTableColumn colCustomerId;

    @FXML
    private TreeTableColumn colItemCode;

    @FXML
    private TreeTableColumn colItemName;

    @FXML
    private TreeTableColumn colRepair;

    @FXML
    private TreeTableColumn colStatus;

    @FXML
    private TreeTableColumn colAction;

    @FXML
    void backButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) orderProcessingPane.getScene().getWindow();
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
        Stage stage = (Stage) orderProcessingPane.getScene().getWindow();
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
    void saveButtonOnAction(ActionEvent event) {

    }

    @FXML
    void updateButtonOnAction(ActionEvent event) {

    }

}
