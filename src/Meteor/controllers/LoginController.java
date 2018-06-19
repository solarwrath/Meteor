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
import javafx.stage.Stage;

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
        /*signInButton.setOnAction(event -> {
            System.out.println("u pressed login button");
        });*/
        createAccountButton.setOnAction(event -> {
            System.out.println("u pressed reg button");
            try {
                Scene registrationScreen = new Scene(FXMLLoader.load(getClass().getResource("../fxml/registrationScreen.fxml")));
                Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                currentStage.setScene(registrationScreen);
            }
            catch (IOException | NullPointerException exception){
                System.out.println("Got " + exception.getMessage());
                exception.printStackTrace();
            }
            //DBHandler dbHandler = new DBHandler();

            //#TODO collect infro from textfields and radio box and then pass it to the method below


            //dbHandler.addUser(new User("username" , "password", "first", "last", "gender"));
        });
        forgotPasswordButton.setOnAction(event -> {
            System.out.println("u pressed for button");
        });
    }
}
