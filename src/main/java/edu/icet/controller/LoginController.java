package edu.icet.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.custom.UserBo;
import edu.icet.bo.custom.impl.UserBoImpl;
import edu.icet.dto.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class LoginController {

    public AnchorPane loginPane;
    @FXML
    private JFXTextField txtEmail;
    public JFXRadioButton radAdmin;
    public JFXRadioButton radEmployee;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnForgetPassword;

    UserBo userBo=new UserBoImpl();

    @FXML
    void LoginButtonOnAction(ActionEvent event) {
        if (radEmployee.isSelected()) {
            try {
                String enteredEmail = txtEmail.getText().toString();
                String enteredPassword = txtPassword.getText().toString();

                UserDto userDto = userBo.getUser(enteredEmail);

                if (userDto != null && userDto.getPassword().equals(enteredPassword) && userDto.getType().equals("User") ) {
                    Stage stage=(Stage) radEmployee.getScene().getWindow();
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserDashboard.fxml"))));
                        stage.setTitle("User");
                        stage.show();
                        stage.setResizable(true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    new Alert(Alert.AlertType.ERROR, "Incorrect Email or Password!").show();
                }
            } catch (RuntimeException e) {

                e.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if(radAdmin.isSelected()){
            String enteredEmail = txtEmail.getText().toString();
            String enteredPassword = txtPassword.getText().toString();

            try {
                UserDto userDto = userBo.getUser(enteredEmail);
                if (userDto != null && userDto.getPassword().equals(enteredPassword) && userDto.getType().equals("Admin")) {
                    Stage stage=(Stage) radAdmin.getScene().getWindow();
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashboard.fxml"))));
                        stage.setTitle("Admin");
                        stage.show();
                        stage.setResizable(true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    new Alert(Alert.AlertType.ERROR, "Incorrect Email or Password").show();
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void forgotPasswordButtonOnAction(ActionEvent event) {
        Stage stage=(Stage) btnForgetPassword.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ForgetPassword.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
