package edu.icet.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.custom.UserBo;
import edu.icet.dao.util.BoType;
import edu.icet.dto.UserDto;
import edu.icet.entity.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class UserRegistrationController {

    public JFXRadioButton radAdmin;
    public JFXRadioButton radEmployee;
    @FXML
    private AnchorPane registrationPane;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUserId;

    private ToggleGroup userType;

    private UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);

    @FXML
    void initialize() {
        userType = new ToggleGroup();
        radAdmin.setToggleGroup(userType);
        radEmployee.setToggleGroup(userType);
    }

    @FXML
    void backButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) registrationPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserManagement.fxml"))));
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.setTitle("User Management");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logoutButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) registrationPane.getScene().getWindow();
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
    void registerUserButtonOnAction(ActionEvent event) {
        if (areFieldsEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill in all fields!").show();
            return;
        }
        try {
            String selectedRole = radAdmin.isSelected() ? "ADMIN" : "EMPLOYEE";
            UserDto userDto = new UserDto(
                    txtUserId.getText(),
                    txtEmail.getText(),
                    txtPassword.getText(),
                    UserType.valueOf(selectedRole)
            );

            boolean isSaved = userBo.saveUser(userDto);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "User Registered!").show();
                clearFields();
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            new Alert(Alert.AlertType.ERROR, "Duplicate Entry!").show();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean areFieldsEmpty() {
        return txtUserId.getText().isEmpty() ||
                txtEmail.getText().isEmpty() ||
                txtPassword.getText().isEmpty() ||
                (!radAdmin.isSelected() && !radEmployee.isSelected());
    }

    private void clearFields() {
        txtUserId.clear();
        txtEmail.clear();
        txtPassword.clear();
        radAdmin.setSelected(false);
        radEmployee.setSelected(false);
    }
}
