package Meteor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.text.Font;
import javafx.stage.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
        Font.loadFont(Main.class.getResource("assets/fonts/Montserrat-SemiBold.otf").toExternalForm(), 12);
        /*Font.loadFont(Main.class.getResource("assets/fonts/Montserrat-Bold.otf").toExternalForm(), 12);
        Font.loadFont(Main.class.getResource("assets/fonts/Montserrat-Light.otf").toExternalForm(), 12);*/
        primaryStage.setTitle("Meteor");
        primaryStage.initStyle(StageStyle.UNDECORATED);

        root.getAccessibleHelp();
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(this.getClass().getResource("css/login.css").toExternalForm(), this.getClass().getResource("css/general.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
