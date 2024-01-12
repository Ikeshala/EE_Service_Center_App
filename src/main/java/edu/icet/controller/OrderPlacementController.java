package edu.icet.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class OrderPlacementController {

    @FXML
    private AnchorPane orderPlacementPane;

    @FXML
    private Label lblOrderId;

    @FXML
    private JFXTextField txtCustomerId;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPhoneNumber;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXComboBox<?> cmbCategory;

    @FXML
    private JFXComboBox<?> cmbItemId;

    @FXML
    private JFXTextField txtRepairingFee;

    @FXML
    private JFXTextField txtAdditionalPartsFee;

    @FXML
    private Label lblTotal;

    @FXML
    void backButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) orderPlacementPane.getScene().getWindow();
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
        Stage stage = (Stage) orderPlacementPane.getScene().getWindow();
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
    void placeOrderButtonOnAction(ActionEvent event) {

    }

}
