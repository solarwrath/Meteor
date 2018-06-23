package Meteor.controllers;

import Meteor.Main;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LostConnectionScreenController {

    public Scene previousScene;

    public Scene getPreviousScene() {
        return previousScene;
    }

    public void setPreviousScene(Scene previousScene) {
        this.previousScene = previousScene;
    }

    @FXML
    private AnchorPane lostConnectionScreenParent;

    @FXML
    private JFXButton tryAgainButton;

    @FXML
    private JFXButton goBackButton;

    @FXML
    void initialize(){
        //TODO Change this to have some actual functionality
        tryAgainButton.setOnAction(event -> {

        });
        goBackButton.setOnAction(event -> {
            System.out.println("call button");
            Main.changeScene(previousScene, (Stage) ((Node)event.getSource()).getScene().getWindow());

            lostConnectionScreenParent.requestFocus();
        });
    }

}
