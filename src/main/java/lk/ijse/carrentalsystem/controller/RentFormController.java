package lk.ijse.carrentalsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.carrentalsystem.db.DbConnection;
import lk.ijse.carrentalsystem.dto.RentDto;
import lk.ijse.carrentalsystem.service.custom.CarService;
import lk.ijse.carrentalsystem.service.custom.CustomerService;
import lk.ijse.carrentalsystem.service.custom.RentService;
import lk.ijse.carrentalsystem.service.ServiceFactory;
import lk.ijse.carrentalsystem.service.util.ServiceType;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class RentFormController {

    @FXML
    private ComboBox<String> cmbCarId;

    @FXML
    private ComboBox<String> cmbCustId;

    @FXML
    private DatePicker dteFromDate;

    @FXML
    private DatePicker dteToDate;

    @FXML
    private Label lblBalance;

    @FXML
    private Label lblTot;

    @FXML
    private TextField txtAdvanced;

    @FXML
    private TextField txtPerDayRent;

    @FXML
    private TextField txtRentId;

    RentService rentService = ServiceFactory.getInstance().getService(ServiceType.RENT);
    CustomerService customerService = ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);
    CarService carService =ServiceFactory.getInstance().getService(ServiceType.CAR);

    public  void initialize(){

        // Initialize and configure combo boxes
        populateCustIdCmoboBox();
        populateCarIdComboBox();

    }

    private void populateCarIdComboBox() {

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            List<String> carids = carService.getAllAvailableCarIds(connection);
            ObservableList<String> carIdOptions = FXCollections.observableArrayList(carids);
            cmbCarId.setItems(carIdOptions);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void populateCustIdCmoboBox() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            List<String> custids = customerService.getAllCategoryIds(connection);
            ObservableList<String> custIdOptions = FXCollections.observableArrayList(custids);
            cmbCustId.setItems(custIdOptions);
            System.out.println("Customer IDs loaded: " + custids);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnBalanceOnAction(ActionEvent actionEvent) {
        //Get the advanced as adouble
        double advanced = Double.parseDouble(txtAdvanced.getText());
        double balance ;

        try {
            //Get the total from lable tot
            double total = Double.parseDouble(lblTot.getText());

            //Calculate the balance
            balance=total-advanced;

            //SetText
            lblBalance.setText(String.valueOf(balance));


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public void btnTotalOnAction(ActionEvent actionEvent) {
          getTotal();
    }

    private void getTotal() {
        // Get the per day rent as a double from the TextField
        double perDayRent = Double.parseDouble(txtPerDayRent.getText());

        // Get the selected from date and to date from the DatePickers
        LocalDate fromdate =dteFromDate.getValue();
        LocalDate todate = dteToDate.getValue();

        if (fromdate != null && todate != null){
            // Calculate the number of days between from date and to date
            Period period =Period.between(fromdate,todate);
            int days = period.getDays();

            if (days <= 30 && days >= 0) {
                // Calculate the total rent (per day rent * number of days)

                //Calculate the total rent( perdayrent * days )
                double totalrent = perDayRent * days;

                // Set the calculated total rent in the Label
                lblTot.setText(String.valueOf(totalrent));
            }else {
                lblTot.setText("Please select dates within a 30-day range");
            }
        }else {
            lblTot.setText("Invalied Input");
        }
    }

    @FXML
    void btnRentOnAction(ActionEvent event) {

        int rentid = Integer.parseInt(txtRentId.getText());
        LocalDate fromdate = dteFromDate.getValue();
        LocalDate todate = dteToDate.getValue();
        double perdayrent = Double.parseDouble(txtPerDayRent.getText());

        //Get the selected customer id and car id from combobox
        String selectedCustId = cmbCustId.getValue();
        String selectedCarId = cmbCarId.getValue();

        // Parse the selected ids to integers if needed
        Integer custid = Integer.valueOf(selectedCustId);
        Integer carid = Integer.valueOf(selectedCarId);

        double total = Double.parseDouble(lblTot.getText());
        double advanced = Double.parseDouble(txtAdvanced.getText());
        double balance = Double.parseDouble(lblBalance.getText());

        RentDto dto =new RentDto(rentid,fromdate,todate,perdayrent,total,balance,advanced,custid,carid);

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            // Save the rental record
            boolean success = rentService.rentCar(dto, connection);


            if (success) {


                // Show a success message
                new Alert(Alert.AlertType.CONFIRMATION, "Car rented successfully!").show();
                clearFields();

            } else {
                // Rollback the transaction if unsuccessful
                connection.rollback();
                connection.setAutoCommit(true);

                // Show an error message
                new Alert(Alert.AlertType.ERROR, "Failed to rent the car. Please check your input.").show();
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
        txtRentId.clear();
        cmbCarId.setValue(null);
        cmbCustId.setValue(null);
        dteFromDate.setValue(null);
        dteToDate.setValue(null);
        txtPerDayRent.clear();
        lblTot.setText("");
        txtAdvanced.clear();
        lblBalance.setText("");
    }
}
