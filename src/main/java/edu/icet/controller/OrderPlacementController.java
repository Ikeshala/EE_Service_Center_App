package edu.icet.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.custom.CustomerBo;
import edu.icet.dao.util.BoType;
import edu.icet.dto.CustomerDto;
import edu.icet.dto.UserDto;
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
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class OrderPlacementController {

    @FXML
    private AnchorPane orderPlacementPane;

    @FXML
    private Label lblOrderId;

    @FXML
    private JFXComboBox<?> cmbCustomerId;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXComboBox<?> cmbCategory;

    @FXML
    private JFXComboBox<?> cmbItemId;

    @FXML
    private JFXTextArea txtRepair;

    private CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);

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
    void customerManagementViewLinkButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) orderPlacementPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CustomerManagement.fxml"))));
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.setTitle("Customer Management");
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
    void placeOrderButtonOnAction(ActionEvent event) {}



}
