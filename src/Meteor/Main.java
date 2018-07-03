package Meteor;

import Meteor.core.LostConnectionScene;
import Meteor.core.PropertiesConfig;
import Meteor.core.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.text.Font;
import javafx.stage.*;
import org.aeonbits.owner.ConfigFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main extends Application {

    public static Scene loginScene;
    public static Scene registrationScene;
    public static Scene postRegistrationScene;
    public static LostConnectionScene lostConnectionScene;
    public static Scene forgotPasswordScene;
    public static Scene postForgotPasswordScene;
    public static Scene dashboardMainScene;
    public static PropertiesConfig propertiesConfig = ConfigFactory.create(PropertiesConfig.class);

    public static User currentUser = new User();
    public static Stage globalStage;


    @Override
    public void start(Stage primaryStage) throws Exception {
        for (String nameOfFont : new ArrayList<>(Arrays.asList("SemiBold", "Bold", "Regular", "Light", "Medium"))) {
            Font.loadFont(Main.class.getResource("assets/fonts/Montserrat-" + nameOfFont + ".otf").toExternalForm(), 12);
        }
        primaryStage.setTitle("Meteor.java.Meteor");
        primaryStage.initStyle(StageStyle.UNDECORATED);

        loginScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/loginScreen.fxml")));
        registrationScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/registrationScreen.fxml")));
        postRegistrationScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/postRegistrationScreen.fxml")));
        lostConnectionScene = new LostConnectionScene(FXMLLoader.load(getClass().getResource("fxml/lostConnectionScreen.fxml")));
        forgotPasswordScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/forgotPasswordScreen.fxml")));
        postForgotPasswordScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/postForgotPasswordScreen.fxml")));
        dashboardMainScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/dashboardMainScreen.fxml")));

        for (Scene currentScene : new ArrayList<>(Arrays.asList(loginScene, registrationScene, postRegistrationScene, lostConnectionScene, forgotPasswordScene, postForgotPasswordScene, dashboardMainScene))) {
            currentScene.getRoot().getAccessibleHelp();
            currentScene.getStylesheets().add(this.getClass().getResource("css/general.css").toExternalForm());
        }

        primaryStage.setScene(loginScene);
        primaryStage.show();
        globalStage = primaryStage;
    }

    public static void changeScene(Scene givenScene, Stage currentStage) {
        currentStage.setScene(givenScene);
    }

    public static void callLostConnectionScene(Stage givenStage, Method givenMethod, HashMap<String, Object> givenParameters, Class givenClass) {
        lostConnectionScene.setPreviousScene(givenStage.getScene());
        lostConnectionScene.setPassedMethod(givenMethod);
        lostConnectionScene.setPassedParametrs(givenParameters);
        lostConnectionScene.setPassedClass(givenClass);
        changeScene(lostConnectionScene, givenStage);
    }

    public static void resetWindowCoords(){
        globalStage.setX(0);
        globalStage.setY(0);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
