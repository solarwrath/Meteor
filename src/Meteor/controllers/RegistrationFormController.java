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
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.asList;

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
    private ImageView loginImportantMarker;

    @FXML
    private Label loginImportantMessage;

    @FXML
    private ImageView passwordImportantMarker;

    @FXML
    private Label passwordImportantMessage;

    @FXML
    private ImageView emailmportantMarker;

    @FXML
    private Label emailImportantMessage;

    @FXML
    private ImageView fullNameImportantMarker;

    @FXML
    private Label fullNameImportantMessage;

    @FXML
    private JFXButton createAccountButton;

    @FXML
    private JFXButton alreadySignedUpButton;


    @FXML
    void initialize(){
        createAccountButton.setOnAction(event -> {
            User givenUser = new User(loginField.getText() , passwordField.getText(), emailField.getText(), fullNameField.getText(), ((JFXRadioButton) genderField.getSelectedToggle()).getText());
            System.out.println(givenUser.validateUser(givenUser).toString());
            if(givenUser.validateUser(givenUser).isEmpty()){
                DBHandler dbHandler = new DBHandler();
                dbHandler.addUser(givenUser);
                for (Node givenNode: new ArrayList<Node>(asList(loginImportantMessage, loginImportantMarker, passwordImportantMarker, passwordImportantMessage, emailmportantMarker, emailImportantMessage, fullNameImportantMarker, fullNameImportantMessage))) {
                    givenNode.setVisible(false);
                };
                for (Control givenControl: new ArrayList<Control>(asList(loginField, emailField, fullNameField))) {
                    if(givenControl.getClass() == JFXTextField.class || givenControl.getClass() == JFXPasswordField.class) {
                        ((JFXTextField)givenControl).setText("");
                    }
                };
                passwordField.setText("");
                femaleRadioButton.setSelected(false);
                maleRadioButton.setSelected(true);

                Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                currentStage.setScene(Main.postRegistrationScene);
                registrationScreenParent.requestFocus();
            }
            else{
                ArrayList<String> listOfErrors = givenUser.validateUser(givenUser);
                switch (listOfErrors.get(0).toString()){
                    case "username":
                    case "username_already_taken":
                        loginField.requestFocus();
                        break;
                    case "password":
                        passwordField.requestFocus();
                        break;
                    case "email":
                    case "email_already_taken":
                        emailmportantMarker.requestFocus();
                        break;
                    case "full_name":
                        fullNameField.requestFocus();
                        break;
                }
                if(listOfErrors.contains("username")){
                    loginImportantMarker.setVisible(true);
                    loginImportantMessage.setVisible(true);
                }
                else{
                    loginImportantMarker.setVisible(false);
                    loginImportantMessage.setVisible(false);
                }
                if(listOfErrors.contains("password")){
                    passwordImportantMarker.setVisible(true);
                    passwordImportantMessage.setVisible(true);
                }
                else{
                    passwordImportantMarker.setVisible(false);
                    passwordImportantMessage.setVisible(false);
                }
                if(listOfErrors.contains("email")){
                    emailmportantMarker.setVisible(true);
                    emailImportantMessage.setVisible(true);
                }
                else{
                    emailmportantMarker.setVisible(false);
                    emailImportantMessage.setVisible(false);
                }
                if(listOfErrors.contains("full_name")){
                    fullNameImportantMarker.setVisible(true);
                    fullNameImportantMessage.setVisible(true);
                }
                else{
                    fullNameImportantMarker.setVisible(false);
                    fullNameImportantMessage.setVisible(false);
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
