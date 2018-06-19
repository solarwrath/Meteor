package Meteor.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.css.CssMetaData;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class RegistrationFormController {

    @FXML
    private JFXTextField loginField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXTextField fullNameField;

    @FXML
    private JFXButton createAccountButton;

    @FXML
    private JFXButton alreadySignedUpButton;

    @FXML
    private JFXRadioButton femaleRadioButton;

    @FXML
    private ToggleGroup gender;

    @FXML
    private JFXRadioButton maleRadioButton;

}
