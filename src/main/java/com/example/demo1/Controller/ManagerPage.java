package com.example.demo1.Controller;
import com.example.demo1.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ManagerPage
{
    @FXML
    private Button airplanesButton;
    @FXML
    private Button employeesButton;
    @FXML
    private Button flightsButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button mailButton;
    @FXML
    private Button passengersButton;
    @FXML
    private Button profileButton;

    @FXML
    void pressedAirplanes(ActionEvent event) {

    }

    @FXML
    void pressedEmployees(ActionEvent event) {

    }

    @FXML
    void pressedFlights(ActionEvent event) {

    }

    @FXML
    void pressedLogout(ActionEvent event) throws IOException {
        Main.sceneSwitch("AirportLogin.fxml", event, 520, 400);
    }

    @FXML
    void pressedMail(ActionEvent event) {

    }

    @FXML
    void pressedPassengers(ActionEvent event) {

    }

    @FXML
    void pressedProfile(ActionEvent event) throws IOException {
        Main.sceneSwitch("ManagerEdit.fxml", event, 520, 400);
    }
}