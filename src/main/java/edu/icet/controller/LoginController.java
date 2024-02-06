package edu.icet.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.custom.UserBo;
import edu.icet.bo.custom.impl.UserBoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {

    @FXML
    private AnchorPane loginPane;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXRadioButton radAdmin;

    @FXML
    private JFXRadioButton radEmployee;
    UserBo userBo=new UserBoImpl();

    @FXML
    void LoginButtonOnAction(ActionEvent event) {
    }
    @FXML
    void forgotOrChangePasswordButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage)loginPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ForgetOrChangePassword.fxml"))));
        stage.setTitle("Forget or Change Password?");
        stage.show();
    }
}
