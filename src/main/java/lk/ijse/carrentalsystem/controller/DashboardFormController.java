package lk.ijse.carrentalsystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.fxml.FXMLLoader.load;

public class DashboardFormController {

    CustomerFormController customerFormController = new CustomerFormController();
    CarTypeFormController carTypeFormController =new CarTypeFormController();

    HomeFormController homeFormController = new HomeFormController();

    CarFormController carFormController = new CarFormController();
    RentFormController rentFormController = new RentFormController();
    public AnchorPane node;
    public JFXButton btnHome;



    @FXML
    private AnchorPane cus_form;

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException, SQLException {
        //
      Parent root = load(this.getClass().getResource("/view/customer_form.fxml"));





      this.node.getChildren().clear();
      this.node.getChildren().add(root);
      customerFormController.initialize();

    }

    public void btnCarTypeOnAction(ActionEvent actionEvent) throws IOException {
        Parent root =load(this.getClass().getResource("/view/carType_form.fxml"));

        this.node.getChildren().clear();
        this.node.getChildren().add(root);
        carTypeFormController.initialize();
    }

    public void btnCarOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = load(this.getClass().getResource("/view/car_form.fxml"));

        this.node.getChildren().clear();
        this.node.getChildren().add(root);
        carFormController.initialize();
    }

    public void btnRentOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = load(this.getClass().getResource("/view/rent_form.fxml"));



        this.node.getChildren().clear();
        this.node.getChildren().add(root);
        rentFormController.initialize();


    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        Parent root = load(this.getClass().getResource("/view/home_form.fxml"));


        this.node.getChildren().clear();
        this.node.getChildren().add(root);
        homeFormController.initialize();
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {
        Platform.exit();
    }
}
