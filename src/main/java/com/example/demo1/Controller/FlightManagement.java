package com.example.demo1.Controller;
import Model.Airport.Airplane;
import Model.Airport.Flight;
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

public class FlightManagement implements Initializable
{
    @FXML
    private Button addButton;
    @FXML
    private Button backButton;
    @FXML
    protected ListView<String> dateList;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    protected ListView<String> fromList;
    @FXML
    protected ListView<String> idList;
    @FXML
    protected ListView<String> lengthList;
    @FXML
    protected ListView<String> numList;
    @FXML
    private Button passengersButton;
    @FXML
    private Button refreshButton;
    @FXML
    protected ListView<String> soldticketsList;
    @FXML
    protected ListView<String> statusList;
    @FXML
    protected ListView<String> timeList;
    @FXML
    private Label titleLabel;
    @FXML
    protected ListView<String> toList;

    protected static int selectedFlight;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editButton.setDisable(true);
        deleteButton.setDisable(true);
        passengersButton.setDisable(true);
        titleLabel.setText(Main.airplanes.get(AirplaneManagement.selectedAirplane).getId()+"");
        updateLists(numList, idList, fromList, toList, statusList, dateList, timeList, lengthList, soldticketsList);

        numList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                deleteButton.setDisable(false);
                editButton.setDisable(false);
                passengersButton.setDisable(false);
                selectedFlight = numList.getSelectionModel().getSelectedIndex();
            }
        });
    }

    @FXML
    void pressedAdd(ActionEvent event) throws IOException {
        Stage addStage = new Stage();
        addStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo1/View/FlightAdd.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 374, 455);
        addStage.getIcons().add(new Image("icon.png"));
        addStage.setTitle("Munix");
        addStage.setResizable(false);
        addStage.setScene(scene);
        addStage.show();
    }

    @FXML
    void pressedEdit(ActionEvent event) throws IOException {
        Stage addStage = new Stage();
        addStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo1/View/FlightEdit.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 374, 455);
        addStage.getIcons().add(new Image("icon.png"));
        addStage.setTitle("Munix");
        addStage.setResizable(false);
        addStage.setScene(scene);
        addStage.show();
    }

    @FXML
    void pressedPassengers(ActionEvent event) throws IOException {
        Main.sceneSwitch("FlightPassengers.fxml", event, 520, 400);
    }

    @FXML
    void pressedDelete(ActionEvent event) {
        int index = numList.getSelectionModel().getSelectedIndex();
        Main.airplanes.get(AirplaneManagement.selectedAirplane).getFlights().remove(index);
        updateLists(numList, idList, fromList, toList, statusList, dateList, timeList, lengthList, soldticketsList);
        deleteButton.setDisable(true);
        editButton.setDisable(true);
        passengersButton.setDisable(true);
    }

    @FXML
    void pressedBack(ActionEvent event) throws IOException {
        Main.sceneSwitch("AirplaneManagement.fxml", event, 520, 400);
    }

    @FXML
    void pressedRefresh(ActionEvent event) {
        updateLists(numList, idList, fromList, toList, statusList, dateList, timeList, lengthList, soldticketsList);
    }

    protected static void updateLists(ListView<String> numList, ListView<String> idList, ListView<String> fromList, ListView<String> toList, ListView<String> statusList, ListView<String> dateList, ListView<String> timeList, ListView<String> lengthList, ListView<String> soldticketsList) {
        try {
            numList.getItems().clear();
            idList.getItems().clear();
            fromList.getItems().clear();
            toList.getItems().clear();
            statusList.getItems().clear();
            dateList.getItems().clear();
            timeList.getItems().clear();
            lengthList.getItems().clear();
            soldticketsList.getItems().clear();

            for (Flight obj : Main.airplanes.get(AirplaneManagement.selectedAirplane).getFlights()) {
                numList.getItems().add(Integer.toString(Main.airplanes.get(AirplaneManagement.selectedAirplane).getFlights().indexOf(obj) + 1));
                idList.getItems().add(obj.getId()+"");
                fromList.getItems().add(obj.getFrom());
                toList.getItems().add(obj.getTo());
                statusList.getItems().add(obj.getStatus()+"");
                dateList.getItems().add(obj.getMonth()+"/"+obj.getDay());
                timeList.getItems().add(obj.getHour()+":"+obj.getMinute());
                lengthList.getItems().add(obj.getFlightLenght()+"H");
                soldticketsList.getItems().add(obj.getSoldTickets()+"");
            }
        } catch(Exception e) { Main.appendToFile(e); }
    }
}