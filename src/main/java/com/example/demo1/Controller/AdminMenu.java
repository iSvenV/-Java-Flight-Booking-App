package com.example.demo1.Controller;
import com.example.demo1.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class AdminMenu
{
    @FXML
    private Button airplanesButton;
    @FXML
    private Button employeesButton;
    @FXML
    private Button financialsButton;
    @FXML
    private Button flightsButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button mailButton;
    @FXML
    private Button managersButton;
    @FXML
    private Button passengersButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button reportButton;

    @FXML
    void pressedAirplanes(ActionEvent event) {

    }

    @FXML
    void pressedEmployees(ActionEvent event) throws IOException {
        Main.sceneSwitch("EmployeeManagement.fxml", event, 520, 400);
    }

    @FXML
    void pressedFinancials(ActionEvent event) throws IOException {
        Main.sceneSwitch("Financials.fxml", event, 520, 400);
    }

    @FXML
    void pressedFlights(ActionEvent event) {

    }

    @FXML
    void pressedMail(ActionEvent event) throws IOException {
        Main.sceneSwitch("FeedbacksPage.fxml", event, 520, 400);
    }

    @FXML
    void pressedManagers(ActionEvent event) throws IOException {
        Main.sceneSwitch("ManagerManagement.fxml", event, 520, 400);
    }

    @FXML
    void pressedPassengers(ActionEvent event) throws IOException {
        PassengerEdit.editor = "admin";
        Main.sceneSwitch("PassengerManagement.fxml", event, 520, 400);
    }

    @FXML
    void pressedProfile(ActionEvent event) throws IOException {
        Main.sceneSwitch("AdminEdit.fxml", event, 520, 400);
    }

    @FXML
    void pressedReport(ActionEvent event) {

    }

    @FXML
    void pressedLogout(ActionEvent event) throws IOException {
        Main.sceneSwitch("AirportLogin.fxml", event, 520, 400);
    }
}