package edu.icet.controller;

import edu.icet.bo.BoFactory;
import edu.icet.bo.custom.impl.CustomerBoImpl;
import edu.icet.dao.util.BoType;
import edu.icet.dto.CustomerDto;
import edu.icet.entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportsController {

    public AnchorPane reportsPane;

    CustomerBoImpl customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);

    @FXML
    void annualSalesButtonOnAction(ActionEvent event) {

    }

    @FXML
    void backButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) reportsPane.getScene().getWindow();
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
    void customerDetailsButtonOnAction(ActionEvent event) {
        try {
            JasperDesign design = JRXmlLoader.load("src/main/resources/reports/customer_report.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(design);

            List<CustomerDto> customerDtos = customerBo.allCustomers();
            List<Customer> customers = new ArrayList<>();
            for(CustomerDto customer: customerDtos){
                customers.add(
                        new Customer(
                                customer.getCustomerId(),
                                customer.getCustomerName(),
                                customer.getCustomerEmail()
                        )
                );
            }
            JRDataSource dataSource = new JRBeanCollectionDataSource(customerDtos);

            Map<String, Object> parameters = new HashMap<>();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void customerOrdersButtonOnAction(ActionEvent event) {

    }

    @FXML
    void dailySalesButtonOnAction(ActionEvent event) {

    }

    @FXML
    void logoutButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) reportsPane.getScene().getWindow();
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
    void monthlySalesButtonOnAction(ActionEvent event) {

    }

    @FXML
    void orderDetailsButtonOnAction(ActionEvent event) {

    }

}
