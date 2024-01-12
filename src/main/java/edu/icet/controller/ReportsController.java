package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportsController {

    public AnchorPane reportsPane;

    @FXML
    void annualSalesButtonOnAction(ActionEvent event) {

    }

    @FXML
    void backButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) reportsPane.getScene().getWindow();
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
    void customerDetailsButtonOnAction(ActionEvent event) {

    }

    @FXML
    void customerOrdersButtonOnAction(ActionEvent event) {

    }

    @FXML
    void dailySalesButtonOnAction(ActionEvent event) {

    }

    @FXML
    void logoutButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) reportsPane.getScene().getWindow();
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
    void monthlySalesButtonOnAction(ActionEvent event) {

    }

    @FXML
    void orderDetailsButtonOnAction(ActionEvent event) {

    }

}
