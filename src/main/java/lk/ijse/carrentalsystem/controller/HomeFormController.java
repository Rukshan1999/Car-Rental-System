package lk.ijse.carrentalsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lk.ijse.carrentalsystem.service.ServiceFactory;
import lk.ijse.carrentalsystem.service.custom.HomeService;
import lk.ijse.carrentalsystem.service.custom.RentService;
import lk.ijse.carrentalsystem.service.util.ServiceType;

public class HomeFormController {

    @FXML
    private Label lblAllCarCat;

    @FXML
    private Label lblAllTot;

    @FXML
    private Label lblCUs;

    @FXML
    private Label lblCar;

    private HomeService homeService = ServiceFactory.getInstance().getService(ServiceType.HOME);
    private RentService rentService = ServiceFactory.getInstance().getService(ServiceType.RENT);


    public void initialize() {
        getAllCustomerCount();
        getAllCarCount();
        getAllTotalIncome();
        getAllCarCategory();
    }

    private void getAllCarCategory() {
        int carCatCount = homeService.getCarCatCount();
        lblAllCarCat.setText(String.valueOf(carCatCount));
    }

    private void getAllTotalIncome() {
        Double allTotal = Double.valueOf(homeService.getTotalIncome());
        lblAllTot.setText(String.valueOf(allTotal));
    }

    private void getAllCarCount() {
        int carCount = homeService.getAllCarCount();
        lblCar.setText(String.valueOf(carCount));
    }

    private void getAllCustomerCount() {
        int customercount = homeService.getCustomerCount();
        lblCUs.setText(String.valueOf(customercount));
    }
    
    

}
