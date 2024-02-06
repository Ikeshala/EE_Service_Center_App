package edu.icet.controller;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.icet.bo.BoFactory;
import edu.icet.bo.custom.ItemBo;
import edu.icet.dao.util.BoType;
import edu.icet.dto.CustomerDto;
import edu.icet.dto.ItemDto;
import edu.icet.dto.tm.CustomerTm;
import edu.icet.dto.tm.ItemTm;
import edu.icet.entity.ItemType;
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
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.function.Predicate;

public class ItemCatalogController {

    @FXML
    private AnchorPane itemPane;

    @FXML
    private JFXComboBox<ItemType> cmbCategory;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private JFXTextField txtQuantity;

    @FXML
    private JFXTreeTableView<ItemTm> tblItem;

    @FXML
    private TreeTableColumn colCategory;

    @FXML
    private TreeTableColumn colCode;

    @FXML
    private TreeTableColumn colItemName;

    @FXML
    private TreeTableColumn colDescription;

    @FXML
    private TreeTableColumn colQuantity;

    @FXML
    private JFXButton btnUpdate;

    private ItemBo itemBo = BoFactory.getInstance().getBo(BoType.ITEM);

    public void initialize(){
        colCategory.setCellValueFactory(new TreeItemPropertyValueFactory<>("category"));
        colCode.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemName"));
        colDescription.setCellValueFactory(new TreeItemPropertyValueFactory<>("description"));
        loadItemsTable();

        cmbCategory.getItems().addAll(ItemType.ELECTRIC, ItemType.ELECTRONIC);
    }

    private void loadItemsTable() {
        ObservableList<ItemTm> tmList = FXCollections.observableArrayList();

        try {
            List<ItemDto> dtoList = itemBo.allItems();

            for (ItemDto itemDto : dtoList) {
                ItemTm itemTm = new ItemTm(
                        itemDto.getCategory(),
                        itemDto.getItemCode(),
                        itemDto.getItemName(),
                        itemDto.getDescription()
                );
                tmList.add(itemTm);
            }

            TreeItem<ItemTm> treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
            tblItem.setRoot(treeItem);
            tblItem.setShowRoot(false);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        btnUpdate.setDisable(true);
        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData(newValue != null ? newValue.getValue() : null);
        });
    }

    private void setData(ItemTm itemTm) {
        if (itemTm != null) {
            txtItemCode.setEditable(false);
            btnUpdate.setDisable(false);

            cmbCategory.setValue(itemTm.getCategory());
            txtItemCode.setText(itemTm.getItemCode());
            txtItemName.setText(itemTm.getItemName());
            txtDescription.setText(itemTm.getDescription());
        }
    }

    @FXML
    void addButtonOnAction(ActionEvent event) {
        if (areFieldsEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill in all fields!").show();
            return;
        }

        try {
            ItemDto itemDto = new ItemDto(
                    cmbCategory.getValue(),
                    txtItemCode.getText(),
                    txtItemName.getText(),
                    txtDescription.getText()
            );

            boolean isSaved = itemBo.saveItem(itemDto);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Item Saved!").show();
                loadItemsTable();
                clearFields();
            }

        } catch (SQLIntegrityConstraintViolationException ex) {
            new Alert(Alert.AlertType.ERROR, "Duplicate Entry!").show();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean areFieldsEmpty() {
        return cmbCategory.getValue() == null ||
                txtItemCode.getText().isEmpty() ||
                txtItemName.getText().isEmpty() ||
                txtDescription.getText().isEmpty();
    }

    private void clearFields() {
        tblItem.refresh();

        cmbCategory.getSelectionModel().clearSelection();
        txtItemCode.clear();
        txtItemName.clear();
        txtDescription.clear();

        txtItemCode.setEditable(true);
    }

    @FXML
    void backButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) itemPane.getScene().getWindow();
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
    void deleteButtonOnAction(ActionEvent event) {
        ItemTm selectedItem = tblItem.getSelectionModel().getSelectedItem().getValue();
        if (selectedItem != null) {
            try {
                boolean isDeleted = itemBo.deleteItem(selectedItem.getItemCode());
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Item Deleted!").show();
                    loadItemsTable();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete item!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please select an item to delete!").show();
        }
    }

    @FXML
    void editButtonOnAction(ActionEvent event) {
        ItemTm selectedItem = tblItem.getSelectionModel().getSelectedItem().getValue();

        try {
            ItemDto updatedItem = new ItemDto(
                    cmbCategory.getValue(),
                    selectedItem.getItemCode(),
                    txtItemName.getText(),
                    txtDescription.getText()
            );

            boolean isUpdated = itemBo.updateItem(updatedItem);

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Item details Updated!").show();
                loadItemsTable();
                clearFields();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logoutButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) itemPane.getScene().getWindow();
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
