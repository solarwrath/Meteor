package Meteor;

import Meteor.core.LostConnectionScene;
import Meteor.core.PropertiesConfig;
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
    public static PropertiesConfig propertiesConfig = ConfigFactory.create(PropertiesConfig.class);

    @Override
    public void start(Stage primaryStage) throws Exception{
        //TODO Need to go deeper and read more about all this stuff
        //TODO Pbbly need to rewrite to wrap some functionality in the functions
        //TODO Get rid of useless imports
        //TODO More static main methods to attach listeners/bindings for example. BTW Listeners r need to be examined. They have rules and now I can't write them without IDEA
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

    public static void callLostConnectionScene(Stage givenStage, Method givenMethod, HashMap<String, Object> givenParameters, Class givenClass){
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
