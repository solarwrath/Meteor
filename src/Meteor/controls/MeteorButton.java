package Meteor.controls;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Paint;

public class MeteorButton extends JFXButton{
    private ObjectProperty<Paint> borderFill = new SimpleObjectProperty((Object)null);

    public final ObjectProperty<Paint> borderFillProperty() {
        return this.borderFill;
    }

    public final Paint getBorderFill() {
        return (Paint)this.borderFillProperty().get();
    }

    public final void setBorderFill(Paint borderFill) {
        this.borderFillProperty().set(borderFill);
    }

}