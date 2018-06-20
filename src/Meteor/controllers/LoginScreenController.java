package Meteor.controllers;

import Meteor.Main;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import Meteor.core.DBHandler;
import Meteor.core.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginScreenController {

    @FXML
    private JFXTextField loginField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXButton signInButton;

    @FXML
    private JFXButton createAccountButton;

    @FXML
    private JFXButton forgotPasswordButton;

    @FXML
    private AnchorPane loginScreenParent;


    @FXML
    void initialize() {
        /*signInButton.setOnAction(event -> {
            System.out.println("u pressed login button");
        });*/
        createAccountButton.setOnAction(event -> {

            Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            currentStage.setScene(Main.registrationScene);
            loginScreenParent.requestFocus();
        });
        forgotPasswordButton.setOnAction(event -> {
            
        });
    }
}
