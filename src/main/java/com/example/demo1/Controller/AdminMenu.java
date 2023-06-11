package com.example.demo1.Controller;
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
    void pressedAirplanes(ActionEvent event) {

    }

    @FXML
    void pressedEmployees(ActionEvent event) {

    }

    @FXML
    void pressedFinancials(ActionEvent event) {

    }

    @FXML
    void pressedFlights(ActionEvent event) {

    }

    @FXML
    void pressedMail(ActionEvent event) {

    }

    @FXML
    void pressedManagers(ActionEvent event) {

    }

    @FXML
    void pressedPassengers(ActionEvent event) {

    }

    @FXML
    void pressedProfile(ActionEvent event) {

    }

    @FXML
    void pressedLogout(ActionEvent event) throws IOException {
        sceneSwitch("AirportLogin.fxml", event, 520, 400);
    }

    public void sceneSwitch(String url, ActionEvent event, int v, int v1) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo1/View/"+url));
        Parent root = fxmlLoader.load();
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, v, v1);
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.setTitle("Munix");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}