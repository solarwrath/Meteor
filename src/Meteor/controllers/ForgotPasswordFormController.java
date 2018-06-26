package Meteor.controllers;

import Meteor.Main;
import Meteor.core.DBHandler;
import Meteor.core.MailHandler;
import Meteor.core.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.exceptions.ConnectionIsClosedException;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import javafx.beans.binding.Binding;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.fxmisc.easybind.EasyBind;

import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static java.util.Arrays.asList;

public class ForgotPasswordFormController {

    private Scene thisScene = Main.registrationScene;

    @FXML
    private AnchorPane forgotPasswordFormParent;

    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXButton sendMessageButton;

    @FXML
    private JFXButton goBackButton;

    @FXML
    private ImageView emailImportantMarker;

    @FXML
    private Label emailImportantMessage;

    @FXML
    private Label emailImportantMessageNotFound;

    @FXML
    public void initialize() {
        sendMessageButton.setOnAction(event -> {
            sendForgotMessage(event);
        });

        goBackButton.setOnAction(event -> {
            Main.changeScene(Main.loginScene, (Stage) ((Node) event.getSource()).getScene().getWindow());
            forgotPasswordFormParent.requestFocus();
        });
    }

    public void sendForgotMessage(Event event) {
        HashMap<String, Object> passedParameters = new HashMap<String, Object>();
        passedParameters.put("event", event);
        sendForgotMessage(passedParameters);
    }

    @SuppressWarnings("Duplicates")
    public void sendForgotMessage(HashMap<String, Object> reflectedArguments) {
        Event event = (Event) reflectedArguments.get("event");
        if (reflectedArguments.size() > 1) {
            if(reflectedArguments.containsKey("email") && reflectedArguments.containsKey("lost_connection_stage")){
            }
        } else {
            String givenEmail = emailField.getText();
            ArrayList<String> listOfErrors = validateGivenEmail(givenEmail);
            if(listOfErrors.isEmpty()){
                for(Node currentNode: new ArrayList<Node>(asList(emailImportantMarker, emailImportantMessage, emailImportantMessageNotFound))){
                    currentNode.setVisible(false);
                }
                MailHandler mailHandler = new MailHandler();
                try{
                    mailHandler.sendEmailTest(
                            givenEmail,
                            "Retrieving access to your Meteor account",
                            "This is placeholder for now. I will change it later after the beginning of using servers.\n" +
                            "For now your creds are:\n" +
                            "Login: " + User.getUsernameFromEmail(givenEmail) + "\n"+
                            "Password: " + User.getPasswordFromEmail(givenEmail) + "\n\n" +
                            "Meteor Dev Team"
                    );

                    emailField.setText("");

                    Main.changeScene(Main.postForgotPasswordScene, (Stage) ((Node) event.getSource()).getScene().getWindow());
                    forgotPasswordFormParent.requestFocus();

                }catch (SQLException e){
                    //TODO maintain this
                    e.printStackTrace();
                }
            }else{
                emailImportantMarker.setVisible(true);
                switch (listOfErrors.get(0)){
                    case "invalid_email":
                        emailImportantMessage.setVisible(true);
                        emailImportantMessageNotFound.setVisible(false);
                        break;
                    case "no_such_email":
                        emailImportantMessageNotFound.setVisible(true);
                        emailImportantMessage.setVisible(false);
                        break;
                }
            }
        }
    }

    public ArrayList<String> validateGivenEmail(String givenEmail){
        ArrayList<String> listOfErrors = new ArrayList<>();
        if(User.isValidEmail(givenEmail)){
            try {
                if(!User.emailAlreadyTaken(givenEmail)){
                    listOfErrors.add("no_such_email");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            listOfErrors.add("invalid_email");
        }
        return listOfErrors;
    }


}
