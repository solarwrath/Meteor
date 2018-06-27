package Meteor.controllers;

import Meteor.Main;
import Meteor.core.MailHandler;
import Meteor.core.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.beans.binding.Binding;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.fxmisc.easybind.EasyBind;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static java.util.Arrays.asList;

public class ForgotPasswordFormController {

    private Scene thisScene = Main.forgotPasswordScene;

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

    private boolean executed = false;

    @FXML
    public void initialize() {
        sendMessageButton.setOnAction(event -> {


            Binding<Boolean> bb = EasyBind.select(Main.forgotPasswordScene.getRoot().sceneProperty())
                    .select(Scene::windowProperty)
                    .selectObject(Window::showingProperty);

            bb.addListener((observable, oldValue, newValue) -> {
                try {
                    if (newValue) {
                        if (Main.forgotPasswordScene.getProperties().containsKey("displayErrorsRequired")) {
                            if ((Boolean) Main.forgotPasswordScene.getProperties().get("displayErrorsRequired")) {
                                try {
                                    displayErrors(validateGivenEmail(emailField.getText().trim()));
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                } catch (NullPointerException e) {
                    //System.out.println(e);
                }
            });
            sendForgotMessage(event);
        });

        goBackButton.setOnAction(event -> {
            Main.changeScene(Main.loginScene, (Stage) ((Node) event.getSource()).getScene().getWindow());
            forgotPasswordFormParent.requestFocus();
        });
    }

    private void sendForgotMessage(Event event) {
        HashMap<String, Object> passedParameters = new HashMap<>();
        passedParameters.put("event", event);
        sendForgotMessage(passedParameters);
    }

    private void sendForgotMessage(HashMap<String, Object> reflectedArguments) {
        Service<Void> backgroundRegistrationThread = new Service<>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<>() {
                    @Override
                    protected Void call() throws Exception {
                        Event event = (Event) reflectedArguments.get("event");
                        if (reflectedArguments.size() > 1) {
                            if (reflectedArguments.containsKey("email") && reflectedArguments.containsKey("lost_connection_stage")) {
                                String givenEmail = (String) reflectedArguments.get("email");
                                try {
                                    if (validateGivenEmail(givenEmail).isEmpty()) {
                                        MailHandler mailHandler = new MailHandler();
                                        try {
                                            mailHandler.sendEmailTest(
                                                    givenEmail,
                                                    "Retrieving access to your Meteor account",
                                                    "This is placeholder for now. I will change it later after the beginning of using servers.\n" +
                                                            "For now your creds are:\n" +
                                                            "Login: " + User.getUsernameFromEmail(givenEmail) + "\n" +
                                                            "Password: " + User.getPasswordFromEmail(givenEmail) + "\n\n" +
                                                            "Meteor Dev Team"
                                            );
                                            Platform.runLater(() -> {
                                                Main.changeScene(Main.postForgotPasswordScene, (Stage) reflectedArguments.get("lost_connection_stage"));
                                                ((Stage) reflectedArguments.get("lost_connection_stage")).requestFocus();
                                            });

                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                            callLostConnectionScene(event, givenEmail);
                                        }
                                    } else {
                                        Platform.runLater(() -> {
                                            Main.forgotPasswordScene.getProperties().put("displayErrorsRequired", true);

                                            Main.changeScene(thisScene, (Stage) reflectedArguments.get("lost_connection_stage"));
                                            ((Stage) reflectedArguments.get("lost_connection_stage")).requestFocus();
                                        });
                                    }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                    callLostConnectionScene(event, givenEmail);
                                }
                            }
                        } else {
                            String givenEmail = emailField.getText().trim();
                            try {
                                ArrayList<String> listOfErrors = validateGivenEmail(givenEmail);
                                if (listOfErrors.isEmpty()) {
                                    for (Node currentNode : new ArrayList<>(asList(emailImportantMarker, emailImportantMessage, emailImportantMessageNotFound))) {
                                        currentNode.setVisible(false);
                                    }
                                    MailHandler mailHandler = new MailHandler();
                                    try {
                                        mailHandler.sendEmailTest(
                                                givenEmail,
                                                "Retrieving access to your Meteor account",
                                                "This is placeholder for now. I will change it later after the beginning of using servers.\n" +
                                                        "For now your creds are:\n" +
                                                        "Login: " + User.getUsernameFromEmail(givenEmail) + "\n" +
                                                        "Password: " + User.getPasswordFromEmail(givenEmail) + "\n\n" +
                                                        "Meteor Dev Team"
                                        );

                                        Platform.runLater(() -> {
                                            emailField.setText("");
                                            Main.changeScene(Main.postForgotPasswordScene, (Stage) ((Node) event.getSource()).getScene().getWindow());
                                            forgotPasswordFormParent.requestFocus();
                                        });

                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                        callLostConnectionScene(event, givenEmail);
                                    }
                                } else {
                                    displayErrors(listOfErrors);
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                                callLostConnectionScene(event, givenEmail);
                            }
                        }
                        return null;
                    }
                };
            }
        };

        if(!executed){
            backgroundRegistrationThread.start();
            executed = true;
        }

    }


    private ArrayList<String> validateGivenEmail(String givenEmail) throws SQLException {
        ArrayList<String> listOfErrors = new ArrayList<>();
        if (User.isValidEmail(givenEmail)) {
            if (!User.emailAlreadyTaken(givenEmail)) {
                listOfErrors.add("no_such_email");
            }
        } else {
            listOfErrors.add("invalid_email");
        }
        return listOfErrors;
    }

    private void displayErrors(ArrayList<String> givenListOfErrors) {
        emailImportantMarker.setVisible(true);
        switch (givenListOfErrors.get(0)) {
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

    private void callLostConnectionScene(Event givenEvent, String givenEmail) {

        Platform.runLater(() -> {
            try {
                Main.callLostConnectionScene(
                        (Stage) ((Node) givenEvent.getSource()).getScene().getWindow(),
                        getClass().getDeclaredMethod("sendForgotMessage", HashMap.class),
                        new HashMap<>() {{
                            put("event", givenEvent);
                            put("email", givenEmail);
                        }},
                        this.getClass());
            } catch (NoSuchMethodException noSuchMethodException) {
                noSuchMethodException.printStackTrace();
            }
            forgotPasswordFormParent.requestFocus();
        });
    }

}
