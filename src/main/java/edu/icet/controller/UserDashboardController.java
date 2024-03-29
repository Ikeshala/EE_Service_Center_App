package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserDashboardController {

    @FXML
    private AnchorPane userDashboardPane;

    @FXML
    void inventoryButtonOnAction(ActionEvent event) {

    }

    @FXML
    void itemCatalogButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) userDashboardPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ItemCatalog.fxml"))));
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.setTitle("Item Catalog");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logoutButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) userDashboardPane.getScene().getWindow();
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
    void orderPlacementButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) userDashboardPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/OrderPlacement.fxml"))));
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.setTitle("Order Placement");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void orderProcessingButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) userDashboardPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/OrderProcessing.fxml"))));
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.setTitle("Order Processing");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void reportsButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) userDashboardPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Reports.fxml"))));
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.setTitle("Reports");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
