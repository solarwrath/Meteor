package Meteor.controllers;

import Meteor.core.LostConnectionScene;
import Meteor.java.Meteor.Main;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.lang.reflect.InvocationTargetException;

public class LostConnectionScreenController {

    @FXML
    private AnchorPane lostConnectionScreenParent;

    @FXML
    private JFXButton tryAgainButton;

    @FXML
    private JFXButton goBackButton;

    @FXML
    void initialize(){
        tryAgainButton.setOnAction(event -> {
            LostConnectionScene lostConnectionScene = (LostConnectionScene)((Node)event.getSource()).getScene();
            try {
                lostConnectionScene.getPassedParametrs().put("lost_connection_stage", lostConnectionScene.getWindow());
                //TODO Get a way to use it without newInstance()
                lostConnectionScene.getPassedMethod().invoke(Class.forName(lostConnectionScene.getPassedClass().getName()).newInstance(), lostConnectionScene.getPassedParametrs());
            } catch (IllegalAccessException|InvocationTargetException|ClassNotFoundException|InstantiationException e) {
                e.printStackTrace();
            }
        });
        goBackButton.setOnAction(event -> {
            LostConnectionScene lostConnectionScene = (LostConnectionScene)((Node)event.getSource()).getScene();
            Main.changeScene(lostConnectionScene.getPreviousScene(), (Stage) lostConnectionScene.getWindow());
            lostConnectionScreenParent.requestFocus();
        });
    }

}
