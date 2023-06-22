package com.example.demo1.Controller;
import com.example.demo1.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentSelection implements Initializable
{
    @FXML
    private RadioButton airportButton;
    @FXML
    private Button buttonEnter;
    @FXML
    private ToggleGroup group;
    @FXML
    private RadioButton hospitalButton;
    @FXML
    private RadioButton instituteButton;
    @FXML
    private RadioButton libraryButton;
    @FXML
    private Label checker;
    @FXML
    private RadioButton muniButton;
    @FXML
    private RadioButton universityButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonEnter.setDisable(true);
    }

    @FXML
    void pressedEnter(ActionEvent event) throws IOException {
        if(selectedType.equals("municipality"))
            Main.sceneSwitch("Municipality/LoginPage.fxml", event, 520, 400);
        else if(selectedType.equals("airport"))
            Main.sceneSwitch("AirportLogin.fxml", event, 520, 400);
        else
            checker.setText(selectedType+" system in currently unavailable!");
    }

    private String selectedType;
    @FXML
    void selectedBox(ActionEvent event) {
        if(muniButton.isSelected())
            selectedType="municipality";
        else if(airportButton.isSelected())
            selectedType="airport";
        else if(hospitalButton.isSelected())
            selectedType="hospital";
        else if(universityButton.isSelected())
            selectedType="university";
        else if(instituteButton.isSelected())
            selectedType="institute";
        else if(libraryButton.isSelected())
            selectedType="library";

        buttonEnter.setDisable(false);
    }
}