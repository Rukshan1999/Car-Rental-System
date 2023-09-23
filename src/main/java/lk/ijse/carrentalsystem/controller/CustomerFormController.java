package lk.ijse.carrentalsystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.carrentalsystem.db.DbConnection;
import lk.ijse.carrentalsystem.dto.CustomerDto;
import lk.ijse.carrentalsystem.entity.CustomerEntity;
import lk.ijse.carrentalsystem.service.ServiceFactory;
import lk.ijse.carrentalsystem.service.custom.CustomerService;
import lk.ijse.carrentalsystem.service.util.ServiceType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CustomerFormController {


    @FXML
    private TableView<CustomerDto> customerTable;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private AnchorPane cus_form;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txttitle;

    @FXML
    private TableColumn<CustomerDto, Integer> colId;

    @FXML
    private TableColumn<CustomerDto, String> colTitle;

    @FXML
    private TableColumn<CustomerDto, String> colName;

    @FXML
    private TableColumn<CustomerDto, String> colNic;

    @FXML
    private TableColumn<CustomerDto, String> colAddress;

    @FXML
    private TableColumn<CustomerDto, String> colMobile;


    private final CustomerService customerService = (CustomerService) ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);

    private static final Logger LOGGER = Logger.getLogger(CustomerFormController.class.getName());


    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            int custid = Integer.parseInt(txtId.getText());
            String custTitle = txttitle.getText();
            String custName = txtName.getText();
            String custNic = txtNic.getText();
            String address = txtAddress.getText();
            String mobile = txtMobile.getText();

            CustomerDto customerDto = new CustomerDto(custid, custTitle, custName, custNic, address, mobile);

            boolean success = customerService.addCustomer(customerDto, connection);

            if (success) {
                clearFields();
                new Alert(Alert.AlertType.CONFIRMATION, "Customer saved successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save customer. Please check your input.").show();
            }


        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Invalid customer ID. Please enter a valid number.", e);
            new Alert(Alert.AlertType.ERROR, "Invalid customer ID. Please enter a valid number.").show();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e);
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtId.clear();
        txttitle.clear();
        txtName.clear();
        txtNic.clear();
        txtAddress.clear();
        txtMobile.clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        int custid = Integer.parseInt(txtId.getText());

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Delete Customer");
        confirmationAlert.setContentText("Are you sure you want to delete this Customer?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK){
            try {
                Connection connection = DbConnection.getInstance().getConnection();
                boolean success =customerService.deleteCar(custid,connection);
                if (success) {
                    // Show a success message
                    clearFields();
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer deleted successfully!").show();
                } else {
                    // Show an error message
                    new Alert(Alert.AlertType.ERROR, "Failed to delete Customer. Please check your input.").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            int custid = Integer.parseInt(txtId.getText());
            String custTitle = txttitle.getText();
            String custName = txtName.getText();
            String custNic = txtNic.getText();
            String address = txtAddress.getText();
            String mobile = txtMobile.getText();

            CustomerDto customerDto = new CustomerDto(custid, custTitle, custName, custNic, address, mobile);

            boolean success = customerService.updateCustomer(customerDto, connection);

            if (success) {
                clearFields();
                new Alert(Alert.AlertType.CONFIRMATION, "Customer details updated successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update customer details. Please check your input.").show();
            }


        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Invalid customer ID. Please enter a valid number.", e);
            new Alert(Alert.AlertType.ERROR, "Invalid customer ID. Please enter a valid number.").show();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e);
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
        }
    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
        String cusIdText = txtId.getText();
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            int customerId = Integer.parseInt(cusIdText);
            CustomerEntity customerDto = customerService.getCustomerById(customerId, connection);

            if (customerDto != null) {
                // Populate the other text fields with customer details
                txttitle.setText(customerDto.getCustTitle());
                txtName.setText(customerDto.getCustName());
                txtNic.setText(customerDto.getCustNic());
                txtAddress.setText(customerDto.getAddress());
                txtMobile.setText(customerDto.getMobile());
            } else {

                // Customer not found, Display an Error message
                clearFields();
                new Alert(Alert.AlertType.ERROR, "Customer not found for ID: " + customerId).show();
            }


        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Invalid customer ID. Please enter a valid number.", e);
            new Alert(Alert.AlertType.ERROR, "Invalid customer ID. Please enter a valid number.").show();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e);
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
        }
    }

    public void initialize() {
        getAllCustomers();
    }

    private void getAllCustomers() {

    }
}
