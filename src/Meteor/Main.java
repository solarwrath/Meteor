package Meteor;

import Meteor.core.LostConnectionScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.text.Font;
import javafx.stage.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

public class Main extends Application {

    public static Scene loginScene;
    public static Scene registrationScene;
    public static Scene postRegistrationScene;
    public static LostConnectionScene lostConnectionScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        for(String nameOfFont : new ArrayList<String>(Arrays.asList("SemiBold", "Bold", "Regular", "Light", "Medium"))){
            Font.loadFont(Main.class.getResource("assets/fonts/Montserrat-"+nameOfFont+".otf").toExternalForm(), 12);
        }
        primaryStage.setTitle("Meteor");
        primaryStage.initStyle(StageStyle.UNDECORATED);

        loginScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/loginScreen.fxml")));
        registrationScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/registrationScreen.fxml")));
        postRegistrationScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/postRegistrationScreen.fxml")));
        lostConnectionScene = new LostConnectionScene(FXMLLoader.load(getClass().getResource("fxml/lostConnectionScreen.fxml")));

        for (Scene currentScene: new ArrayList<Scene>(Arrays.asList(loginScene, registrationScene, postRegistrationScene, lostConnectionScene))) {
            currentScene.getRoot().getAccessibleHelp();
            currentScene.getStylesheets().add(this.getClass().getResource("css/general.css").toExternalForm());
        }

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public static void changeScene(Scene givenScene, Stage currentStage){
        currentStage.setScene(givenScene);
    }

    public static void callLostConnectionScene(Stage givenStage, Method givenMethod, ArrayList<Object> givenParameters, Class givenClass){
        lostConnectionScene.setPreviousScene(givenStage.getScene());
        lostConnectionScene.setPassedMethod(givenMethod);
        lostConnectionScene.setPassedParametrs(givenParameters);
        lostConnectionScene.setPassedClass(givenClass);
        changeScene(lostConnectionScene, givenStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
