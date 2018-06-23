package Meteor.controllers;

import Meteor.Main;
import Meteor.core.DBHandler;
import Meteor.core.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.exceptions.ConnectionIsClosedException;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private Label loginImportantMessageDups;

    @FXML
    private ImageView passwordImportantMarker;

    @FXML
    private Label passwordImportantMessage;

    @FXML
    private ImageView emailImportantMarker;

    @FXML
    private Label emailImportantMessage;

    @FXML
    private Label emailImportantMessageDups;

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
           try{
               if(givenUser.validateUser(givenUser).isEmpty()){
                   DBHandler.addUser(givenUser);
                   for (Node givenNode: new ArrayList<Node>(asList(loginImportantMessage, loginImportantMessageDups, loginImportantMarker, passwordImportantMarker, passwordImportantMessage, emailImportantMarker, emailImportantMessage, fullNameImportantMarker, fullNameImportantMessage))) {
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

                   Main.changeScene(Main.postRegistrationScene, (Stage) ((Node)event.getSource()).getScene().getWindow());
                   registrationScreenParent.requestFocus();
               }
               else{
                   ArrayList<String> listOfErrors = givenUser.validateUser(givenUser);
                   switch (listOfErrors.get(0)){
                       case "username":
                       case "username_already_taken":
                           loginField.requestFocus();
                           break;
                       case "password":
                           passwordField.requestFocus();
                           break;
                       case "email":
                       case "email_already_taken":
                           emailImportantMarker.requestFocus();
                           break;
                       case "full_name":
                           fullNameField.requestFocus();
                           break;
                   }

                   if(listOfErrors.contains("username")){
                       loginImportantMessage.setVisible(true);
                   }
                   else{
                       loginImportantMessage.setVisible(false);
                   }

                   if(listOfErrors.contains("username_already_taken")){
                       loginImportantMessageDups.setVisible(true);
                   }
                   else{
                       loginImportantMessageDups.setVisible(false);
                   }


                   if (listOfErrors.contains("username") || listOfErrors.contains("username_already_taken")) {
                       loginImportantMarker.setVisible(true);
                   } else {
                       loginImportantMarker.setVisible(false);
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
                       emailImportantMessage.setVisible(true);
                   }
                   else{
                       emailImportantMessage.setVisible(false);
                   }

                   if(listOfErrors.contains("email_already_taken")){
                       emailImportantMessageDups.setVisible(true);
                   }
                   else{
                       emailImportantMessageDups.setVisible(false);
                   }

                   if (listOfErrors.contains("email") || listOfErrors.contains("email_already_taken")) {
                       emailImportantMarker.setVisible(true);
                   } else {
                       emailImportantMarker.setVisible(false);
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
           }
           catch (CommunicationsException e){
               //Call handle exception
               e.printStackTrace();
               Main.callLostConnectionScene((Stage) ((Node)event.getSource()).getScene().getWindow());
               registrationScreenParent.requestFocus();
           }
           catch(SQLException|ConnectionIsClosedException|ConnectException e){
               e.printStackTrace();
               Main.callLostConnectionScene((Stage) ((Node)event.getSource()).getScene().getWindow());
               registrationScreenParent.requestFocus();
           }

        });

        alreadySignedUpButton.setOnAction(event -> {
            Main.changeScene(Main.loginScene, (Stage) ((Node)event.getSource()).getScene().getWindow());
            registrationScreenParent.requestFocus();
        });
    }

}
