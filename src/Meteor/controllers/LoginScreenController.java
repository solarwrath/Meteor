package Meteor.controllers;

import Meteor.java.Meteor.Main;

import Meteor.java.Meteor.core.User;
import Meteor.java.Meteor.core.exceptions.NotCompletedUser;
import Meteor.java.Meteor.core.exceptions.NotFoundUserException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import com.mysql.cj.exceptions.ConnectionIsClosedException;
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

import java.net.ConnectException;
import java.sql.SQLException;
import java.util.HashMap;


public class LoginScreenController {

    @FXML
    private JFXTextField loginField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXButton signInButton;

    @FXML
    private ImageView notFoundImportantMarker;

    @FXML
    private Label notFoundImportantMessage;

    @FXML
    private JFXButton createAccountButton;

    @FXML
    private JFXButton forgotPasswordButton;

    @FXML
    private AnchorPane loginScreenParent;

    private boolean executed = false;

    private Scene thisScene = Main.loginScene;

    @FXML
    void initialize() {
        signInButton.setOnAction(event -> {

            Binding<Boolean> bb = EasyBind.select(Main.loginScene.getRoot().sceneProperty())
                    .select(Scene::windowProperty)
                    .selectObject(Window::showingProperty);

            bb.addListener((observable, oldValue, newValue) -> {
                try {
                    if (newValue) {
                        if (Main.loginScene.getProperties().containsKey("displayErrorRequired")) {
                            if ((Boolean) Main.loginScene.getProperties().get("displayErrorRequired")) {
                                displayError();
                                Main.loginScene.getProperties().remove("displayErrorRequired");
                            }
                        }
                    }
                } catch (NullPointerException e) {
                    //TODO logging there
                }
            });
            loginUser(event);
        });


        createAccountButton.setOnAction(event -> {
            Main.changeScene(Main.registrationScene, (Stage) ((Node) event.getSource()).getScene().getWindow());
            loginScreenParent.requestFocus();
        });
        forgotPasswordButton.setOnAction(event -> {
            Main.changeScene(Main.forgotPasswordScene, (Stage) ((Node) event.getSource()).getScene().getWindow());
            loginScreenParent.requestFocus();

        });
    }

    public void loginUser(Event event) {
        HashMap<String, Object> passedParameters = new HashMap<>();
        passedParameters.put("event", event);
        loginUser(passedParameters);
    }

    public void loginUser(HashMap<String, Object> reflectedArguments) {
        Service<Void> backgroundLoginTask = new Service<>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<>() {
                    @Override
                    protected Void call() throws Exception {
                        Event event = (Event) reflectedArguments.get("event");
                        if (reflectedArguments.size() > 1) {
                            if (reflectedArguments.containsKey("username") && reflectedArguments.containsKey("password") && reflectedArguments.containsKey("lost_connection_stage")) {
                                String givenUsername = (String) reflectedArguments.get("username");
                                String givenPassword = (String) reflectedArguments.get("password");
                                try {
                                    Main.currentUser = User.returnLoginnedUser(givenUsername, givenPassword);
                                    Platform.runLater(() -> {
                                        Main.changeScene(Main.dashboardMainScene, (Stage) reflectedArguments.get("lost_connection_stage"));
                                        Main.resetWindowCoords();
                                        ((Stage) reflectedArguments.get("lost_connection_stage")).requestFocus();
                                    });
                                } catch (SQLException | ConnectException e) {
                                    e.printStackTrace();
                                } catch (NotFoundUserException e) {
                                    Platform.runLater(() -> {
                                        Main.loginScene.getProperties().put("displayErrorRequired", true);
                                        Main.changeScene(thisScene, (Stage) reflectedArguments.get("lost_connection_stage"));
                                        ((Stage) reflectedArguments.get("lost_connection_stage")).requestFocus();
                                    });
                                }
                            }
                        } else {
                            String givenUsername = loginField.getText().trim();
                            String givenPassword = passwordField.getText().trim();
                            try {

                                Main.currentUser = User.returnLoginnedUser(givenUsername, givenPassword);
                                Platform.runLater(() -> {
                                    Main.changeScene(Main.dashboardMainScene, (Stage) ((Node) event.getSource()).getScene().getWindow());
                                    Main.resetWindowCoords();
                                    loginScreenParent.requestFocus();
                                });

                            } catch (SQLException | ConnectionIsClosedException | ConnectException e) {
                                e.printStackTrace();
                                callLostConnectionScene(event, givenUsername, givenPassword);
                            } catch (NotFoundUserException e) {
                                displayError();
                            } catch (NotCompletedUser e) {
                                e.printStackTrace();
                            }
                        }
                        return null;
                    }
                };
            }
        };

        if (!executed) {
            backgroundLoginTask.start();
            executed = true;
        }

        backgroundLoginTask.setOnSucceeded(event -> {
            executed = false;
        });

        backgroundLoginTask.setOnFailed(event -> {
            executed = false;
        });

    }

    private void displayError() {
        notFoundImportantMarker.setVisible(true);
        notFoundImportantMessage.setVisible(true);
    }

    private void callLostConnectionScene(Event givenEvent, String givenUsername, String givenPassword) {
        Platform.runLater(() -> {
            try {
                Main.callLostConnectionScene(
                        (Stage) ((Node) givenEvent.getSource()).getScene().getWindow(),
                        getClass().getDeclaredMethod("loginUser", HashMap.class),
                        new HashMap<>() {{
                            put("event", givenEvent);
                            put("username", givenUsername);
                            put("password", givenPassword);
                        }},
                        this.getClass());
            } catch (NoSuchMethodException noSuchMethodException) {
                noSuchMethodException.printStackTrace();
            }
            loginScreenParent.requestFocus();
        });
    }
}
