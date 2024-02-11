package edu.icet.controller;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.icet.bo.BoFactory;
import edu.icet.bo.custom.OrdersBo;
import edu.icet.dao.util.BoType;
import edu.icet.dto.OrdersDto;
import edu.icet.dto.UserDto;
import edu.icet.dto.tm.OrdersTm;
import edu.icet.dto.tm.UserTm;
import edu.icet.entity.StatusType;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Predicate;

public class OrderProcessingController {

    @FXML
    private AnchorPane orderProcessingPane;

    @FXML
    private JFXTextField txtCustomerId;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXComboBox<StatusType> cmbStatus;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXTreeTableView<OrdersTm> tblOrders;

    @FXML
    private TreeTableColumn colOrderId;

    @FXML
    private TreeTableColumn colOrderDate;

    @FXML
    private TreeTableColumn colCustomerId;

    @FXML
    private TreeTableColumn colItemCode;

    @FXML
    private TreeTableColumn colItemName;

    @FXML
    private TreeTableColumn colRepair;

    @FXML
    private TreeTableColumn colStatus;

    @FXML
    private TreeTableColumn colAction;

    private OrdersBo ordersBo = BoFactory.getInstance().getBo(BoType.ORDERS);
    public void initialize(){
        colOrderId.setCellValueFactory(new TreeItemPropertyValueFactory<>("orderId"));
        colOrderDate.setCellValueFactory(new TreeItemPropertyValueFactory<>("orderDate"));
        colCustomerId.setCellValueFactory(new TreeItemPropertyValueFactory<>("customerId"));
        colItemCode.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemName"));
        colRepair.setCellValueFactory(new TreeItemPropertyValueFactory<>("repair"));
        colStatus.setCellValueFactory(new TreeItemPropertyValueFactory<>("status"));
        colAction.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));

        loadOrdersTable();
        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String newValue) {
                String lowerCaseNewValue = newValue.toLowerCase();
                tblOrders.setPredicate(new Predicate<TreeItem<OrdersTm>>() {
                    @Override
                    public boolean test(TreeItem<OrdersTm> ordersTmTreeItem) {
                        String lowerCaseOrderId = ordersTmTreeItem.getValue().getOrderId().toLowerCase();
                        String orderDate = ordersTmTreeItem.getValue().getOrderDate();
                        String customerId = ordersTmTreeItem.getValue().getCustomerId();
                        String lowerCaseItemName = ordersTmTreeItem.getValue().getItemName().toLowerCase();

                        return lowerCaseOrderId.contains(lowerCaseNewValue) ||
                                orderDate.toString().toLowerCase().contains(lowerCaseNewValue) ||
                                customerId.toLowerCase().contains(lowerCaseNewValue) ||
                                lowerCaseItemName.contains(lowerCaseNewValue);
                    }
                });
            }
        });
    }
    private void loadOrdersTable() {
        ObservableList<OrdersTm> tmList = FXCollections.observableArrayList();

        try {
            List<OrdersDto> dtoList = ordersBo.allOrders();

            for (OrdersDto dto : dtoList) {

                JFXButton button = new JFXButton("Bill");
                button.setFont(Font.font("System", FontWeight.BOLD, 13));
                button.setButtonType(JFXButton.ButtonType.RAISED);
                button.setBlendMode(BlendMode.HARD_LIGHT);
                button.setTextAlignment(TextAlignment.CENTER);
                button.setTextFill(Color.WHITE);
                button.setStyle("-fx-border-color:  #202046; -fx-border-radius: 5; -fx-background-color:   #202046;");


                String orderDateStr = dto.getOrderDate();
                LocalDate orderDate = null;
                if (orderDateStr != null) {
                    orderDate = LocalDate.parse(orderDateStr);
                } else {
                    orderDate = LocalDate.now();
                    System.out.println("Warning: Order date is null for order ID " + dto.getOrderId());
                }


                String formattedDate = orderDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                OrdersTm ordersTm = new OrdersTm(
                        dto.getOrderId(),
                        formattedDate,
                        dto.getCustomerId(),
                        dto.getItemCode(),
                        dto.getItemName(),
                        dto.getRepair(),
                        dto.getStatus(),
                        button
                );

                button.setOnAction(actionEvent -> {
                    Stage stage = (Stage) orderProcessingPane.getScene().getWindow();
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BillProcessing.fxml"));
                        AnchorPane root = loader.load();
                        BillProcessingController controller = loader.getController();

                        stage.setScene(new Scene(root));
                        stage.centerOnScreen();
                        stage.setResizable(false);
                        stage.setTitle("Bill");
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });


                tmList.add(ordersTm);
            }
            TreeItem<OrdersTm> treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
            tblOrders.setRoot(treeItem);
            tblOrders.setShowRoot(false);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        btnUpdate.setDisable(true);
        tblOrders.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData(newValue != null ? newValue.getValue() : null);
        });
    }

    private void setData(OrdersTm ordersTm) {
        if (ordersTm != null) {
            txtCustomerId.setEditable(false);
            btnUpdate.setDisable(false);

            txtOrderId.setText(ordersTm.getOrderId());
            txtCustomerId.setText(ordersTm.getCustomerId());
            txtItemCode.setText(ordersTm.getItemCode());

            cmbStatus.setItems(FXCollections.observableArrayList(StatusType.values()));
            cmbStatus.setValue(ordersTm.getStatus());
        }
    }

    @FXML
    void backButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) orderProcessingPane.getScene().getWindow();
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
    void logoutButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) orderProcessingPane.getScene().getWindow();
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
    void updateButtonOnAction(ActionEvent event) {
        OrdersTm selectedOrderTm = tblOrders.getSelectionModel().getSelectedItem().getValue();

        try {
            OrdersDto selectedOrderDto = new OrdersDto(
                    selectedOrderTm.getOrderId(),
                    selectedOrderTm.getOrderDate(),
                    selectedOrderTm.getCustomerId(),
                    selectedOrderTm.getItemCode(),
                    selectedOrderTm.getItemName(),
                    selectedOrderTm.getRepair(),
                    cmbStatus.getValue()
            );

            boolean isUpdated = ordersBo.updateOrders(selectedOrderDto);

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Order Status Updated!").show();
                loadOrdersTable();
                clearFields();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtOrderId.setText("");
        txtCustomerId.setText("");
        txtItemCode.setText("");
        cmbStatus.getSelectionModel().clearSelection();
    }

}
