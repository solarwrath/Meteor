package Meteor.controllers;

import Meteor.Main;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PostForgotPasswordScreenController {

    @FXML
    private AnchorPane postForgotPasswordScreenParent;

    @FXML
    private JFXButton continueButton;

    @FXML
    void initialize(){
        continueButton.setStyle("-jfx-disable-visual-focus: true;");
        continueButton.setOnAction(event -> {
            Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            currentStage.setScene(Main.loginScene);
            postForgotPasswordScreenParent.requestFocus();
        });
    }

}
