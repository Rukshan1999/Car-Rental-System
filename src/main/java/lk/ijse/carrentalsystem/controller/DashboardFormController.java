package lk.ijse.carrentalsystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.fxml.FXMLLoader.load;

public class DashboardFormController {

    CustomerFormController customerFormController= new CustomerFormController();
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
    }

    public void btnCarOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = load(this.getClass().getResource("/view/car_form.fxml"));

        this.node.getChildren().clear();
        this.node.getChildren().add(root);
    }

    public void btnRentOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = load(this.getClass().getResource("/view/rent_form.fxml"));



        this.node.getChildren().clear();
        this.node.getChildren().add(root);


    }
}
