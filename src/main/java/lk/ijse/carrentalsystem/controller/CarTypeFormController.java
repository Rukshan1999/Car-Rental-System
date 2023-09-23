package lk.ijse.carrentalsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import lk.ijse.carrentalsystem.db.DbConnection;
import lk.ijse.carrentalsystem.dto.CarCategoryDto;
import lk.ijse.carrentalsystem.dto.CustomerDto;
import lk.ijse.carrentalsystem.entity.CarCategoryEntity;
import lk.ijse.carrentalsystem.service.ServiceFactory;
import lk.ijse.carrentalsystem.service.custom.CarCategoryService;
import lk.ijse.carrentalsystem.service.custom.CustomerService;
import lk.ijse.carrentalsystem.service.util.ServiceType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class CarTypeFormController {

    @FXML
    private TextField txtCatId;

    @FXML
    private TextField txtCatName;


    private CarCategoryService carCategoryService;

    public CarTypeFormController() {
        carCategoryService = (CarCategoryService) ServiceFactory.getInstance().getService(ServiceType.CARCATEGORY);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String catid = txtCatId.getText();
        String catName = txtCatName.getText();


        try {
            Connection connection = DbConnection.getInstance().getConnection();

            CarCategoryDto carCategoryDto =new CarCategoryDto(catid,catName);

            boolean success = carCategoryService.addCarCategory(carCategoryDto,connection);

            if (success) {
                // Show a success message
                clearFields();
                new Alert(Alert.AlertType.CONFIRMATION, "Car type added successfully!").show();
            } else {
                // Show an error message
                new Alert(Alert.AlertType.ERROR, "Failed to add car type. Please check your input.").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtCatId.clear();
        txtCatName.clear();

    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
        String catId = txtCatId.getText();


        try {
            Connection connection = DbConnection.getInstance().getConnection();
            int carCattId = Integer.parseInt((catId));

            CarCategoryEntity carCategoryDto = carCategoryService.getCarCatById(carCattId,connection);

            if (carCategoryDto!=null){
                //Populate the other textFeild with other details
                txtCatName.setText(carCategoryDto.getName());

            } else {

                // Customer not found, Display an Error message
                clearFields();
                new Alert(Alert.AlertType.ERROR, "Customer not found for ID: " + carCattId).show();
            }

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        int catId = Integer.parseInt(txtCatId.getText());
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Delete Car Category");
        confirmationAlert.setContentText("Are you sure you want to delete this car category?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                Connection connection = DbConnection.getInstance().getConnection();


                boolean success = carCategoryService.deleteCarCategory(catId, connection);

                if (success) {
                    clearFields();
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer deleted successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete customer. Please check the customer ID.").show();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String catid =txtCatId.getText();
        String name = txtCatName.getText();

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            CarCategoryDto carCategoryDto = new CarCategoryDto(catid,name);

            boolean success = carCategoryService.updateCarCategory(carCategoryDto,connection);

            if (success) {
                clearFields();
                new Alert(Alert.AlertType.CONFIRMATION, "yoohoo! Car Category details updated successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Oppps! Failed to update Car Category details. Please check your input.").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
