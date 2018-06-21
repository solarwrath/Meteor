package Meteor.controllers;

import Meteor.Main;
import Meteor.core.DBHandler;
import Meteor.core.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.CssMetaData;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RegistrationFormController {

    @FXML
    private AnchorPane registrationScreenParent;

    @FXML
    private JFXTextField loginField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXTextField fullNameField;

    @FXML
    private ToggleGroup genderField;

    @FXML
    private JFXRadioButton maleRadioButton;

    @FXML
    private JFXRadioButton femaleRadioButton;

    @FXML
    private JFXButton createAccountButton;

    @FXML
    private JFXButton alreadySignedUpButton;

    @FXML
    private ImageView loginMarker;


    @FXML
    void initialize(){
        createAccountButton.setOnAction(event -> {
            User givenUser = new User(loginField.getText() , passwordField.getText(), emailField.getText(), fullNameField.getText(), ((JFXRadioButton) genderField.getSelectedToggle()).getText());
            System.out.println(givenUser.validateUser(givenUser).toString());
            if(givenUser.validateUser(givenUser).isEmpty()){
                DBHandler dbHandler = new DBHandler();
                dbHandler.addUser(givenUser);
                loginMarker.setVisible(false);

                Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                currentStage.setScene(Main.postRegistrationScene);
                registrationScreenParent.requestFocus();
            }
            else{
                System.out.println("debug 2");
                ArrayList<String> listOfErrors = givenUser.validateUser(givenUser);
                if(listOfErrors.contains("username")){
                    loginMarker.setVisible(true);
                    // Maybe not very user-friendly
                    // loginField.setPromptText("2-20 characters without whitespaces");
                }
            }
        });
        alreadySignedUpButton.setOnAction(event -> {
            Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            currentStage.setScene(Main.loginScene);
            registrationScreenParent.requestFocus();
        });
    }

}
