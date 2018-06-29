package Meteor.controllers;

import Meteor.Main;
import com.jfoenix.controls.JFXButton;
import javafx.beans.binding.Binding;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.fxmisc.easybind.EasyBind;

import java.sql.SQLException;

public class dashboardMainScreenController {

    @FXML
    private AnchorPane dashboardMainScreenParent;

    @FXML
    private JFXButton continueButton;

    private Scene thisScene = Main.dashboardMainScene;

    @FXML
    private VBox dashboardMenu;

    @FXML
    private ImageView dashboardLogo;

    @FXML
    private ImageView dashboardAvatarImage;

    @FXML
    private Label dashboardAvatarName;

    @FXML
    private HBox dashboardMainMenuHome;

    @FXML
    void initialize() {
        Circle customCircle = new Circle();

        customCircle.centerXProperty().setValue(32);
        customCircle.centerYProperty().setValue(32);
        customCircle.radiusProperty().setValue(32);
        dashboardAvatarImage.setClip(customCircle);

        Service<Void> dashboardMainShown = new Service<>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<>() {
                    @Override
                    protected Void call() throws Exception {
                        boolean success = false;

                        while (!success) {
                            try {
                                Binding<Boolean> bb = EasyBind.select(Main.dashboardMainScene.getRoot().sceneProperty())
                                        .select(Scene::windowProperty)
                                        .selectObject(Window::showingProperty);

                                bb.addListener((observable, oldValue, newValue) -> {
                                    dashboardAvatarName.setText(Main.currentUser.getFullName());
                                });
                                success = true;
                            } catch (NullPointerException e) {
                            }
                        }
                        return null;
                    }
                };
            }
        };
        dashboardMainShown.start();

        dashboardAvatarImage.setOnMouseClicked(event -> System.out.println("succ"));
        dashboardMainMenuHome.setOnMouseClicked(event -> {
            System.out.println("u clicked");
        });

        continueButton.setStyle("-jfx-disable-visual-focus: true;");
        continueButton.setOnAction(event ->

        {/*
            Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            currentStage.setScene(Main.loginScene);
            dashboardMainScreenParent.requestFocus();*/
        });
    }


}
