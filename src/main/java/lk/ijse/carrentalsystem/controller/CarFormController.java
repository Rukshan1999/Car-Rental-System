package lk.ijse.carrentalsystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lk.ijse.carrentalsystem.db.DbConnection;
import lk.ijse.carrentalsystem.dto.CarDto;
import lk.ijse.carrentalsystem.entity.CarEntity;
import lk.ijse.carrentalsystem.service.ServiceFactory;
import lk.ijse.carrentalsystem.service.custom.CarCategoryService;
import lk.ijse.carrentalsystem.service.custom.CarService;
import lk.ijse.carrentalsystem.service.util.ServiceType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class CarFormController {

    @FXML
    private ComboBox<String> cmbStatus;

    @FXML
    private ComboBox<String> cmbCatId;

    @FXML
    private TextField txtCarId;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtYear;

    @FXML
    private TextField txtcarNum;

    @FXML
    private TextField txtprice;

    @FXML
    private TextField txtstatus;

    CarService carService = (CarService) ServiceFactory.getInstance().getService(ServiceType.CAR);
    CarCategoryService carCategoryService =(CarCategoryService) ServiceFactory.getInstance().getService(ServiceType.CARCATEGORY);

    public void initialize() {
        populateStatusComboBox();
        populateCategoryIdComboBox();
    }

    private void populateStatusComboBox() {
        ObservableList<String> statusOptions = FXCollections.observableArrayList("Available", "Not Available");
        cmbStatus.setItems(statusOptions);
    }

    private void populateCategoryIdComboBox() {

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            List<String> categoryIds = carCategoryService.getAllCategoryIds(connection);
            ObservableList<String> categoryIdOptions = FXCollections.observableArrayList(categoryIds);
            cmbCatId.setItems(categoryIdOptions);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnAddOnAction(ActionEvent event){
        int carid = Integer.parseInt(txtCarId.getText());
        String vehinumber = txtcarNum.getText();
        String model = txtModel.getText();
        String brand = txtBrand.getText();
        String year = txtYear.getText();
        double pricePerDay = Double.parseDouble(txtprice.getText());
        String status = cmbStatus.getValue();
        String catId = cmbCatId.getValue();



        try {
            Connection connection = DbConnection.getInstance().getConnection();
            CarDto carDto = new CarDto(carid, vehinumber, model, brand, year, pricePerDay, status, catId);

            boolean success = carService.addCar(carDto, connection);

            if (success) {
                new Alert(Alert.AlertType.CONFIRMATION, "Car added successfully!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to add car. Please check your input.").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtCarId.clear();
        txtcarNum.clear();
        txtModel.clear();
        txtBrand.clear();
        txtYear.clear();
        txtprice.clear();
        cmbStatus.setValue(null);
        cmbCatId.setValue(null);
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void idTextOnAction(ActionEvent event) {
        int carid = Integer.parseInt(txtCarId.getText());

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            CarEntity carDto = carService.getCarById(carid,connection);

            if (carDto!=null){
                txtcarNum.setText(carDto.getVehinumber());
                txtModel.setText(carDto.getModel());
                txtBrand.setText(carDto.getBrand());
                txtYear.setText(carDto.getYear());
                txtprice.setText(String.valueOf(carDto.getPricePerDay()));
                cmbStatus.getSelectionModel().select(carDto.getStatus());
                cmbCatId.getSelectionModel().select(carDto.getCatid());

            }else {

                clearFields();
                new Alert(Alert.AlertType.ERROR, "Car not found for ID: " + carid).show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        int carid = Integer.parseInt(txtCarId.getText());

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Delete Car");
        confirmationAlert.setContentText("Are you sure you want to delete this car?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK){

            try {
                Connection connection = DbConnection.getInstance().getConnection();

                boolean success =carService.deleteCar(carid,connection);

                if (success) {
                    // Show a success message
                    clearFields();
                    new Alert(Alert.AlertType.CONFIRMATION, "Car deleted successfully!").show();
                } else {
                    // Show an error message
                    new Alert(Alert.AlertType.ERROR, "Failed to delete car. Please check your input.").show();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        int carid = Integer.parseInt(txtCarId.getText());
        String vehinumber = txtcarNum.getText();
        String model = txtModel.getText();
        String brand = txtBrand.getText();
        String year = txtYear.getText();
        double pricePerDay = Double.parseDouble(txtprice.getText());
        String status = cmbStatus.getValue();
        String catId = cmbCatId.getValue();

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Update Car");
        confirmationAlert.setContentText("Are you sure you want to Update this car?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();


        try {
            Connection connection =DbConnection.getInstance().getConnection();

            CarDto dto = new CarDto(carid,vehinumber,model,brand,year,pricePerDay,status,catId);

            boolean sucess = carService.updateCar(dto,connection);

            if (sucess) {
                // Show a success message
                clearFields();
                new Alert(Alert.AlertType.CONFIRMATION, "Yeeeye! Car details updated successfully!").show();
            } else {
                // Show an error message
                new Alert(Alert.AlertType.ERROR, "ooo! Failed to update car details. Please check your input.").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
