package Meteor.controllers;

import Meteor.Main;
import Meteor.core.LostConnectionScene;

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
        //TODO Change this to have some actual functionality
        tryAgainButton.setOnAction(event -> {
            LostConnectionScene lostConnectionScene = (LostConnectionScene)((Node)event.getSource()).getScene();
            try {
                lostConnectionScene.getPassedParametrs().put("lost_connection_stage", (Stage) lostConnectionScene.getWindow());
                lostConnectionScene.getPassedMethod().invoke(Class.forName(lostConnectionScene.getPassedClass().getName()).newInstance(), lostConnectionScene.getPassedParametrs());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
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
