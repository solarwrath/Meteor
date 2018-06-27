package Meteor.core;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Paint;

import java.lang.reflect.Method;
import java.util.HashMap;

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

    public Method getPassedMethod() {
        return passedMethod;
    }

    public void setPassedMethod(Method passedMethod) {
        this.passedMethod = passedMethod;
    }

    private Method passedMethod;

    private HashMap<String, Object> passedParametrs;

    public HashMap<String, Object> getPassedParametrs() {
        return passedParametrs;
    }

    public void setPassedParametrs(HashMap<String, Object> passedParametrs) {
        this.passedParametrs = passedParametrs;
    }

    private Class passedClass;

    public Class getPassedClass() {
        return passedClass;
    }

    public void setPassedClass(Class passedClass) {
        this.passedClass = passedClass;
    }
}