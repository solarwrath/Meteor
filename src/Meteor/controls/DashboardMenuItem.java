package Meteor.controls;


import java.io.IOException;

//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class DashboardMenuItem extends HBox {

    @FXML
    private Label menuItemLabel;

    /*@FXML
    private FontAwesomeIconView menuItemIcon;*/

    public DashboardMenuItem() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "dashboardMenuItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    public String getText() {
        return labelTextProperty().get();
    }

    public void setText(String value) {
        labelTextProperty().set(value);
    }

    public StringProperty labelTextProperty() {
        return menuItemLabel.textProperty();
    }


}
