package Meteor.controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import Meteor.core.DBHandler;
import Meteor.core.User;

public class LoginController {

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
    void initialize() {
        assert signInButton != null : "fx:id=\"signInButton\" was not injected: check your FXML file 'login.fxml'.";
        assert createAccountButton != null : "fx:id=\"createAccountButton\" was not injected: check your FXML file 'login.fxml'.";
        assert loginField != null : "fx:id=\"loginField\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'login.fxml'.";
        //TODO create passwordfields and implement it int his form
        signInButton.setOnAction(event -> {
            System.out.println("u pressed login button");
        });
        createAccountButton.setOnAction(event -> {
            System.out.println("u pressed reg button");
            DBHandler dbHandler = new DBHandler();

            //#TODO collect infro from textfields and radio box and then pass it to the method below


            dbHandler.addUser(new User("username" , "password", "first", "last", "gender"));
        });
        forgotPasswordButton.setOnAction(event -> {
            System.out.println("u pressed for button");
        });
    }
}
