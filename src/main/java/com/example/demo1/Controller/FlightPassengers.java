package com.example.demo1.Controller;
import Model.Airport.Passenger;
import com.example.demo1.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FlightPassengers implements Initializable
{
    @FXML
    private Button backButton;
    @FXML
    private ListView<String> idList;
    @FXML
    private Button infoButton;
    @FXML
    private ListView<String> nameList;
    @FXML
    private ListView<String> numList;
    @FXML
    private ListView<String> ticketsList;
    @FXML
    private Label titleLabel;
    @FXML
    private ListView<String> usernameList;

    protected static int selectedPassenger;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        infoButton.setDisable(true);
        titleLabel.setText(Main.airplanes.get(AirplaneManagement.selectedAirplane).getFlights().get(FlightManagement.selectedFlight).getId()+"");
        updateLists(numList, idList, nameList, usernameList, ticketsList);

        numList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                infoButton.setDisable(false);
                selectedPassenger = numList.getSelectionModel().getSelectedIndex();
            }
        });
    }

    @FXML
    void pressedInfo(ActionEvent event) throws IOException {
        Stage addStage = new Stage();
        addStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo1/View/PassengerInfo.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 378, 287);
        addStage.getIcons().add(new Image("icon.png"));
        addStage.setTitle("Munix");
        addStage.setResizable(false);
        addStage.setScene(scene);
        addStage.show();
    }

    @FXML
    void pressedBack(ActionEvent event) throws IOException {
        Main.sceneSwitch("FlightManagement.fxml", event, 694, 400);
    }

    private static void updateLists(ListView<String> numList, ListView<String> idList, ListView<String> nameList, ListView<String> usernameList, ListView<String> ticketsList) {
        try {
            numList.getItems().clear();
            idList.getItems().clear();
            nameList.getItems().clear();
            usernameList.getItems().clear();
            ticketsList.getItems().clear();

            for (Passenger obj : Main.airplanes.get(AirplaneManagement.selectedAirplane).getFlights().get(FlightManagement.selectedFlight).getPassengers()) {
                numList.getItems().add(Integer.toString(Main.airplanes.get(AirplaneManagement.selectedAirplane).getFlights().get(FlightManagement.selectedFlight).getPassengers().indexOf(obj) + 1));
                idList.getItems().add(obj.getId()+"");
                nameList.getItems().add(obj.getFullname());
                usernameList.getItems().add(obj.getUsername());
                ticketsList.getItems().add(obj.getTickets().size()+"");
            }
        } catch(Exception e) { Main.appendToFile(e); }
    }
}