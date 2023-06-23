package com.example.demo1.Controller;
import Model.Airport.Airplane;
import Model.Airport.Employee;
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
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AirplaneManagement implements Initializable
{
    @FXML
    private Button addButton;
    @FXML
    private Button backButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Button flightsButton;
    @FXML
    private ListView<String> flightsList;
    @FXML
    private ListView<String> idList;
    @FXML
    private ListView<String> numList;
    @FXML
    private Button refreshButton;
    @FXML
    private ListView<String> seatsList;

    public static int selectedAirplane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editButton.setDisable(true);
        deleteButton.setDisable(true);
        flightsButton.setDisable(true);
        updateLists(numList, idList, seatsList, flightsList);

        numList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                deleteButton.setDisable(false);
                editButton.setDisable(false);
                flightsButton.setDisable(false);
                selectedAirplane = numList.getSelectionModel().getSelectedIndex();
            }
        });
    }

    @FXML
    void pressedAdd(ActionEvent event) throws IOException {
        Stage addStage = new Stage();
        addStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo1/View/AirplaneAdd.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 374, 296);
        addStage.getIcons().add(new Image("icon.png"));
        addStage.setTitle("Munix");
        addStage.setResizable(false);
        addStage.setScene(scene);
        addStage.show();
    }

    @FXML
    void pressedEdit(ActionEvent event) {

    }

    @FXML
    void pressedFlights(ActionEvent event) {

    }

    @FXML
    void pressedDelete(ActionEvent event) {
        int index = numList.getSelectionModel().getSelectedIndex();
        Main.airplanes.remove(index);
        updateLists(numList, idList, seatsList, flightsList);
        deleteButton.setDisable(true);
        editButton.setDisable(true);
        flightsButton.setDisable(true);
    }

    @FXML
    void pressedRefresh(ActionEvent event) {
        updateLists(numList, idList, seatsList, flightsList);
    }

    @FXML
    void pressedBack(ActionEvent event) throws IOException {
        if(AirportLogin.userRole.equals("admin"))
            Main.sceneSwitch("AdminMenu.fxml", event, 520, 400);
        else if(AirportLogin.userRole.equals("manager"))
            Main.sceneSwitch("ManagerPage.fxml", event, 520, 400);
        else
            Main.sceneSwitch("EmployeePage.fxml", event, 520, 400);
    }

    private static void updateLists(ListView<String> numList, ListView<String> idList, ListView<String> seatsList, ListView<String> flightsList) {
        try {
            numList.getItems().clear();
            idList.getItems().clear();
            seatsList.getItems().clear();
            flightsList.getItems().clear();

            for (Airplane obj : Main.airplanes) {
                numList.getItems().add(Integer.toString(Main.airplanes.indexOf(obj) + 1));
                idList.getItems().add(obj.getId()+"");
                seatsList.getItems().add(obj.getSeats()+"");
                flightsList.getItems().add(obj.getFlights().size()+"");
            }
        } catch(Exception e) { Main.appendToFile(e); }
    }
}