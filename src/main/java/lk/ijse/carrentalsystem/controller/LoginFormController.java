package lk.ijse.carrentalsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.carrentalsystem.service.ServiceFactory;
import lk.ijse.carrentalsystem.service.custom.LoginService;
import lk.ijse.carrentalsystem.service.util.ServiceType;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private TextField txtPw;

    @FXML
    private TextField txtUserName;

    private LoginService loginService = ServiceFactory.getInstance().getService(ServiceType.LOGIN);

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {

        String userName =txtUserName.getText();
        String password = txtPw.getText();

        if (userName.isEmpty() || password.isEmpty()){

            new Alert(Alert.AlertType.ERROR, "Oops! Please enter both username and password.").show();

        }else {

            boolean authenticated = loginService.authenticateUser(userName,password);
            if (authenticated){
                new Alert(Alert.AlertType.INFORMATION, "Yeeeyi! Login successful! Welcome to the dashboard.").show();
                navigate();
            }else{
                new Alert(Alert.AlertType.ERROR, "Oops! Invalid username or password. Please try again.").show();
            }
        }

    }

    private void navigate() throws IOException {

        //load scene graph to this method
        AnchorPane rootPane = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));

        //set scene graph to the Scene
        Scene scene = new Scene(rootPane);

        Stage primaryStage = (Stage) txtUserName.getScene().getWindow();

        //set Scene to the Primary Stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("LOGIN");
        primaryStage.centerOnScreen();

        //show stage
        primaryStage.show();

    }

}