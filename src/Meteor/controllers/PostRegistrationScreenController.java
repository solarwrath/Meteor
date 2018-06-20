package Meteor.controllers;

import Meteor.Main;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PostRegistrationScreenController {

    @FXML
    private AnchorPane postRegistrationScreenParent;

    @FXML
    private JFXButton continueButton;


    @FXML
    void initialize(){
        //TODO Change this to work
        continueButton.setStyle("-jfx-disable-visual-focus: true;");
        continueButton.setOnAction(event -> {
            Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            currentStage.setScene(Main.loginScene);
            postRegistrationScreenParent.requestFocus();
        });
    }

}
