package edu.icet.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.custom.CustomerBo;
import edu.icet.bo.custom.ItemBo;
import edu.icet.bo.custom.OrdersBo;
import edu.icet.bo.custom.impl.CustomerBoImpl;
import edu.icet.bo.custom.impl.ItemBoImpl;
import edu.icet.bo.custom.impl.OrdersBoImpl;
import edu.icet.dto.CustomerDto;
import edu.icet.dto.ItemDto;
import edu.icet.dto.OrdersDto;
import edu.icet.dto.tm.OrdersTm;
import edu.icet.entity.Customer;
import edu.icet.entity.Item;
import edu.icet.entity.Orders;
import edu.icet.entity.StatusType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class OrderPlacementController {

    @FXML
    private AnchorPane orderPlacementPane;

    @FXML
    private Label lblOrderId;

    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextField txtCategory;

    @FXML
    private JFXComboBox<String> cmbItemCode;

    @FXML
    private JFXTextArea txtRepair;

    private List<CustomerDto> customers;
    private List<ItemDto> items;
    private CustomerBo customerBo = new CustomerBoImpl();
    private OrdersBo ordersBo = new OrdersBoImpl();
    private ItemBo itemBo = new ItemBoImpl();
    private ObservableList<OrdersTm> tmList = FXCollections.observableArrayList();

    public void initialize(){
        loadCustomerIds();
        loadItemIds();
        generateId();

        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, customerId) -> {
            for (CustomerDto dto:customers) {
                if (dto.getCustomerId().equals(customerId)){
                    txtCustomerName.setText(dto.getCustomerName());
                    txtEmail.setText(dto.getCustomerEmail());
                }
            }
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, itemCode) -> {
            for (ItemDto dto:items) {
                if (dto.getItemCode().equals(itemCode)){
                    txtItemName.setText(dto.getItemName());
                    txtCategory.setText(dto.getCategory().name());
                    break; // Break the loop once the item is found
                }
            }
        });

    }

    private void loadItemIds() {
        try {
            items = itemBo.allItems();
            ObservableList<String> itemIdList = FXCollections.observableArrayList();
            for (ItemDto dto: items) {
                itemIdList.add(dto.getItemCode());
            }
            cmbItemCode.setItems(itemIdList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void generateId() {
        try {
            lblOrderId.setText(ordersBo.generateId());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerIds() {
        try {
            customers = customerBo.allCustomers();
            ObservableList<String> customerIdList = FXCollections.observableArrayList();
            for (CustomerDto dto: customers) {
                customerIdList.add(dto.getCustomerId());
            }
            cmbCustomerId.setItems(customerIdList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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
    void placeOrderButtonOnAction(ActionEvent event) {
        String orderId = lblOrderId.getText();
        String customerId = cmbCustomerId.getValue();
        String itemCode = cmbItemCode.getValue();
        String repair = txtRepair.getText();

        if (customerId == null || itemCode == null || repair.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields.").show();
            return;
        }

        OrdersDto orderDto = new OrdersDto();
        orderDto.setOrderId(orderId);
        orderDto.setCustomerId(customerId);
        orderDto.setItemCode(itemCode);
        orderDto.setRepair(repair);
        orderDto.setStatus(StatusType.PENDING);

        LocalDate orderDate = LocalDate.now();
        orderDto.setOrderDate(orderDate);

        try {
            boolean success = ordersBo.saveOrders(orderDto);
            if (success) {
                new Alert(Alert.AlertType.INFORMATION, "Order placed successfully!").show();
                clearFields();
                generateId();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to place order.").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred while placing the order.").show();
        }
    }

    private void clearFields() {
        cmbCustomerId.getSelectionModel().clearSelection();
        txtCustomerName.setText("");
        txtEmail.setText("");
        cmbItemCode.getSelectionModel().clearSelection();
        txtCategory.setText("");
        txtItemName.setText("");
        txtRepair.setText("");
    }

}
