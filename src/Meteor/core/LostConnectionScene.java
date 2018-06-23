package Meteor.core;

import Meteor.Main;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Paint;

public class LostConnectionScene extends Scene {

    public LostConnectionScene(Parent root) {
        super(root);
    }

    public LostConnectionScene(Parent root, double width, double height) {
        super(root, width, height);
    }

    public LostConnectionScene(Parent root, Paint fill) {
        super(root, fill);
    }

    public LostConnectionScene(Parent root, double width, double height, Paint fill) {
        super(root, width, height, fill);
    }

    public LostConnectionScene(Parent root, double width, double height, boolean depthBuffer) {
        super(root, width, height, depthBuffer);
    }

    public LostConnectionScene(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing) {
        super(root, width, height, depthBuffer, antiAliasing);
    }

    public Scene getPreviousScene() {
        return previousScene;
    }

    public void setPreviousScene(Scene previousScene) {
        this.previousScene = previousScene;
    }

    private Scene previousScene;
}