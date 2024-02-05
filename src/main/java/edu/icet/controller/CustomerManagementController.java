package edu.icet.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.icet.bo.BoFactory;
import edu.icet.bo.custom.CustomerBo;
import edu.icet.dao.util.BoType;
import edu.icet.dto.CustomerDto;
import edu.icet.dto.tm.CustomerTm;
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
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.function.Predicate;

public class CustomerManagementController {

    @FXML
    private AnchorPane customerPane;

    @FXML
    private JFXTextField txtCustomerId;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXTreeTableView<CustomerTm> tblCustomers;

    @FXML
    private TreeTableColumn colCustomerId;

    @FXML
    private TreeTableColumn colCustomerName;

    @FXML
    private TreeTableColumn colEmail;

    @FXML
    private TreeTableColumn colAction;

    private CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);

    public void initialize(){
        colCustomerId.setCellValueFactory(new TreeItemPropertyValueFactory<>("customerId"));
        colCustomerName.setCellValueFactory(new TreeItemPropertyValueFactory<>("customerName"));
        colEmail.setCellValueFactory(new TreeItemPropertyValueFactory<>("customerEmail"));
        colAction.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));
        loadCustomersTable();
        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String newValue) {
                String lowerCaseNewValue = newValue.toLowerCase();
                tblCustomers.setPredicate(new Predicate<TreeItem<CustomerTm>>() {
                    @Override
                    public boolean test(TreeItem<CustomerTm> customerTmTreeItem) {
                        String lowerCaseCode = customerTmTreeItem.getValue().getCustomerId().toLowerCase();
                        String lowerCaseDescription = customerTmTreeItem.getValue().getCustomerName().toLowerCase();

                        return lowerCaseCode.contains(lowerCaseNewValue) ||
                                lowerCaseDescription.contains(lowerCaseNewValue);
                    }
                });
            }
        });
    }
    private void loadCustomersTable() {
        ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();

        try {
            List<CustomerDto> dtoList = customerBo.allCustomers();

            for (CustomerDto dto : dtoList) {
                JFXButton button = new JFXButton("DELETE");
                button.setFont(Font.font("System", FontWeight.BOLD, 13));
                button.setButtonType(JFXButton.ButtonType.RAISED);
                button.setBlendMode(BlendMode.HARD_LIGHT);
                button.setTextAlignment(TextAlignment.CENTER);
                button.setTextFill(Color.WHITE);
                button.setStyle("-fx-border-color:  #202046; -fx-border-radius: 5; -fx-background-color:   #202046;");

                CustomerTm customerTm = new CustomerTm(
                        dto.getCustomerId(),
                        dto.getCustomerName(),
                        dto.getCustomerEmail(),
                        button
                );

                button.setOnAction(actionEvent -> {
                    deleteCustomer(customerTm.getCustomerId());
                });

                tmList.add(customerTm);
            }
            TreeItem<CustomerTm> treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
            tblCustomers.setRoot(treeItem);
            tblCustomers.setShowRoot(false);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        btnUpdate.setDisable(true);
        tblCustomers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData(newValue != null ? newValue.getValue() : null);
        });
    }

    private void setData(CustomerTm customerTm) {
        if (customerTm != null) {
            txtCustomerId.setEditable(false);
            btnUpdate.setDisable(false);

            txtCustomerId.setText(customerTm.getCustomerId());
            txtCustomerName.setText(customerTm.getCustomerName());
            txtEmail.setText(customerTm.getCustomerEmail());
        }
    }

    private void deleteCustomer(String id) {
        try {
            boolean isDeleted = customerBo.deleteCustomer(id);
            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted!").show();
                loadCustomersTable();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addButtonOnAction(ActionEvent event) {
        if (txtCustomerId.getText().isEmpty() || txtCustomerName.getText().isEmpty() ||
                txtEmail.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill in all fields!").show();
            return;
        }

        try {
            CustomerDto customerDto = new CustomerDto(
                    txtCustomerId.getText(),
                    txtCustomerName.getText(),
                    txtEmail.getText()
            );

            boolean isSaved = customerBo.saveCustomer(customerDto);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Customer Saved!").show();
                loadCustomersTable();
                clearFields();
            }

        } catch (SQLIntegrityConstraintViolationException ex) {
            new Alert(Alert.AlertType.ERROR, "Duplicate Entry!").show();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        tblCustomers.refresh();

        txtCustomerId.clear();
        txtCustomerName.clear();
        txtEmail.clear();

        txtCustomerId.setEditable(true);
    }

    @FXML
    void backButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) customerPane.getScene().getWindow();
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
        Stage stage = (Stage) customerPane.getScene().getWindow();
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
    void orderPlacementViewLinkButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) customerPane.getScene().getWindow();
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
    void updateButtonOnAction(ActionEvent event) {
        CustomerTm selectedCustomer = tblCustomers.getSelectionModel().getSelectedItem().getValue();

        try {
            CustomerDto updatedCustomer = new CustomerDto(
                    selectedCustomer.getCustomerId(),
                    txtCustomerName.getText(),
                    txtEmail.getText()
            );

            boolean isUpdated = customerBo.updateCustomer(updatedCustomer);

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Customer Updated!").show();
                loadCustomersTable();
                clearFields();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
