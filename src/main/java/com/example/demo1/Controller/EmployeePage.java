package com.example.demo1.Controller;
import com.example.demo1.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeePage implements Initializable
{
    @FXML
    private Button flightsButton;
    @FXML
    private Label greetLabel;
    @FXML
    private Button logoutButton;
    @FXML
    private Button mailButton;
    @FXML
    private Button profileButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int index = AirportLogin.userIndex;
        greetLabel.setText(Main.employees.get(index).getFullname()+"!");
    }

    @FXML
    void pressedFlights(ActionEvent event) {

    }

    @FXML
    void pressedLogout(ActionEvent event) throws IOException {
        Main.sceneSwitch("AirportLogin.fxml", event, 520, 400);
    }

    @FXML
    void pressedMail(ActionEvent event) throws IOException {
        Main.sceneSwitch("FeedbackSubmission.fxml", event, 520, 400);
    }

    @FXML
    void pressedProfile(ActionEvent event) {

    }
}