package Meteor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.text.Font;
import javafx.stage.*;

public class Main extends Application {

    public static Scene loginScene;
    public static Scene registrationScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Font.loadFont(Main.class.getResource("assets/fonts/Montserrat-SemiBold.otf").toExternalForm(), 12);
        /*Font.loadFont(Main.class.getResource("assets/fonts/Montserrat-Bold.otf").toExternalForm(), 12);
        Font.loadFont(Main.class.getResource("assets/fonts/Montserrat-Light.otf").toExternalForm(), 12);*/
        primaryStage.setTitle("Meteor");
        primaryStage.initStyle(StageStyle.UNDECORATED);

        loginScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/login.fxml")));
        loginScene.getRoot().getAccessibleHelp();
        loginScene.getStylesheets().addAll(this.getClass().getResource("css/login.css").toExternalForm(), this.getClass().getResource("css/general.css").toExternalForm());

        registrationScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/registrationScreen.fxml")));
        registrationScene.getRoot().getAccessibleHelp();
        registrationScene.getStylesheets().addAll(this.getClass().getResource("css/login.css").toExternalForm(), this.getClass().getResource("css/general.css").toExternalForm());

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
