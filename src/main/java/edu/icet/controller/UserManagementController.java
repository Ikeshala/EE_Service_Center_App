package edu.icet.controller;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.icet.bo.BoFactory;
import edu.icet.bo.custom.UserBo;
import edu.icet.dao.util.BoType;
import edu.icet.dto.UserDto;
import edu.icet.dto.tm.UserTm;
import edu.icet.entity.UserType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

public class UserManagementController {

    @FXML
    private AnchorPane userPane;

    @FXML
    private JFXTextField txtUserId;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXComboBox<UserType> cmbType;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXTreeTableView<UserTm> tblUsers;

    @FXML
    private TreeTableColumn colUserId;

    @FXML
    private TreeTableColumn colEmail;

    @FXML
    private TreeTableColumn colType;

    @FXML
    private TreeTableColumn colAction;

    private UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);

    public void initialize(){
        colUserId.setCellValueFactory(new TreeItemPropertyValueFactory<>("userId"));
        colEmail.setCellValueFactory(new TreeItemPropertyValueFactory<>("email"));
        colType.setCellValueFactory(new TreeItemPropertyValueFactory<>("type"));
        colAction.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));
        loadUsersTable();
        cmbType.getItems().addAll(UserType.ADMIN, UserType.EMPLOYEE);

        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String newValue) {
                String lowerCaseNewValue = newValue.toLowerCase();
                tblUsers.setPredicate(new Predicate<TreeItem<UserTm>>() {
                    @Override
                    public boolean test(TreeItem<UserTm> userTmTreeItem) {
                        String lowerCaseCode = userTmTreeItem.getValue().getUserId().toLowerCase();
                        String lowerCaseDescription = userTmTreeItem.getValue().getEmail().toLowerCase();

                        return lowerCaseCode.contains(lowerCaseNewValue) ||
                                lowerCaseDescription.contains(lowerCaseNewValue);
                    }
                });
            }
        });
    }
    private void loadUsersTable() {
        ObservableList<UserTm> tmList = FXCollections.observableArrayList();

        try {
            List<UserDto> dtoList = userBo.allUser();

            for (UserDto dto : dtoList) {
                JFXButton button = new JFXButton("DELETE");
                button.setFont(Font.font("System", FontWeight.BOLD, 13));
                button.setButtonType(JFXButton.ButtonType.RAISED);
                button.setBlendMode(BlendMode.HARD_LIGHT);
                button.setTextAlignment(TextAlignment.CENTER);
                button.setTextFill(Color.WHITE);
                button.setStyle("-fx-border-color:  #202046; -fx-border-radius: 5; -fx-background-color:   #202046;");

                UserTm userTm = new UserTm(
                        dto.getUserId(),
                        dto.getEmail(),
                        dto.getPassword(),
                        dto.getType(),
                        button
                );

                button.setOnAction(actionEvent -> {
                    deleteUser(userTm.getUserId());
                });

                tmList.add(userTm);
            }
            TreeItem<UserTm> treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
            tblUsers.setRoot(treeItem);
            tblUsers.setShowRoot(false);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        btnUpdate.setDisable(true);
        tblUsers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData(newValue != null ? newValue.getValue() : null);
        });
    }

    private void setData(UserTm userTm) {
        if (userTm != null) {
            txtUserId.setEditable(false);
            btnUpdate.setDisable(false);

            txtUserId.setText(userTm.getUserId());
            txtEmail.setText(userTm.getEmail());

            cmbType.setValue(userTm.getType());
        }
    }

    private void deleteUser(String id) {
        try {
            boolean isDeleted = userBo.deleteUser(id);
            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"User Deleted!").show();
                loadUsersTable();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void backButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) userPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashboard.fxml"))));
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.setTitle("Admin Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logoutButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) userPane.getScene().getWindow();
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
    void registerViewLinkButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) userPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserRegistration.fxml"))));
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.setTitle("User Registration");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updateButtonOnAction(ActionEvent event) {
        UserTm selectedUser = tblUsers.getSelectionModel().getSelectedItem().getValue();

        try {
            UserDto updatedUser = new UserDto(
                    selectedUser.getUserId(),
                    txtEmail.getText(),
                    selectedUser.getPassword(),
                    cmbType.getValue()
            );

            boolean isUpdated = userBo.updateUser(updatedUser);

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "User Updated!").show();
                loadUsersTable();
                clearFields();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtUserId.clear();
        txtEmail.clear();
        cmbType.getSelectionModel().clearSelection();
    }
}
