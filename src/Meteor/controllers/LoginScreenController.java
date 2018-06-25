package Meteor.controllers;

import Meteor.Main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.Node;
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

            Main.changeScene(Main.registrationScene, (Stage) ((Node)event.getSource()).getScene().getWindow());
            loginScreenParent.requestFocus();
        });
        forgotPasswordButton.setOnAction(event -> {
            
        });
    }
}
