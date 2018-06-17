package sample.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import sample.core.DBHandler;
import sample.core.User;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField loginField;

    @FXML
    private JFXTextField passwordField;

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
        passwor
        signInButton.setOnAction(event -> {
            System.out.println("u pressed login button");
        });
        createAccountButton.setOnAction(event -> {
            System.out.println("u pressed reg button");
            DBHandler dbHandler = new DBHandler();

            //#TODO collect infro from textfields and radio box and then pass it to the method below


            //#TODO Connect to GitHub and integrate it into the IDEA

            dbHandler.addUser(new User("username" , "password", "first", "last", "gender"));
        });
        forgotPasswordButton.setOnAction(event -> {
            System.out.println("u pressed for button");
        });
    }
}
