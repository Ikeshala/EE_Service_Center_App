package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardController {

    @FXML
    private AnchorPane adminDashboardPane;

    @FXML
    void inventoryButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) adminDashboardPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Inventory.fxml"))));
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.setTitle("Inventory");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void itemCatalogButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) adminDashboardPane.getScene().getWindow();
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
        Stage stage = (Stage) adminDashboardPane.getScene().getWindow();
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

    }

    @FXML
    void orderProcessingButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) adminDashboardPane.getScene().getWindow();
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

    }

    @FXML
    void userManagementButtonOnAction(ActionEvent event) {

    }

}
