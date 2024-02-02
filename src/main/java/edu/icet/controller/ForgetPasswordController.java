package edu.icet.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.dto.EmailSenderDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ForgetPasswordController {

    @FXML
    private AnchorPane forgetPasswordPane;

    @FXML
    private JFXTextField txtEmail;

    private JFXButton btnRequest;

    @FXML
    void backButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) forgetPasswordPane.getScene().getWindow();
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
    void RequestButtonOnAction(ActionEvent event) {
        String to = txtEmail.getText();
        String subject = "Test Email";
        String body = "This is an email from E&E System.";


        EmailSenderDto.sendEmail(to, subject, body);


        verifiedPageLoader();
    }

    private void verifiedPageLoader() {
        Stage stage = (Stage) btnRequest.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Verification.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void logoutButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) forgetPasswordPane.getScene().getWindow();
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

}
