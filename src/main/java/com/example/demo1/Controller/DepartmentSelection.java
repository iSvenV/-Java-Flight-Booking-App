package com.example.demo1.Controller;
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
            sceneSwitch("LoginPage.fxml", event);
        else if(selectedType.equals("airport"))
            sceneSwitch("AirportLogin.fxml", event);
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

    public void sceneSwitch(String url, ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo1/View/"+url));
        Parent root = fxmlLoader.load();
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 520, 400);
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.setTitle("Munix");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}