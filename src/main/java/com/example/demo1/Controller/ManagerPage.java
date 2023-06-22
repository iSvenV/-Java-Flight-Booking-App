package com.example.demo1.Controller;
import com.example.demo1.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ManagerPage
{
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
    void pressedEmployees(ActionEvent event) throws IOException {
        Main.sceneSwitch("EmployeeManagement.fxml", event, 520, 400);
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
        Main.sceneSwitch("FeedbacksPage.fxml", event, 520, 400);
    }

    @FXML
    void pressedPassengers(ActionEvent event) throws IOException {
        PassengerEdit.editor = "manager";
        Main.sceneSwitch("PassengerManagement.fxml", event, 520, 400);
    }

    @FXML
    void pressedProfile(ActionEvent event) throws IOException {
        Main.sceneSwitch("ManagerEdit.fxml", event, 520, 400);
    }
}