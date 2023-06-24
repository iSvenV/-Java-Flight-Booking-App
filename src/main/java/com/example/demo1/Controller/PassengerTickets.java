package com.example.demo1.Controller;
import Model.Airport.Flight;
import Model.Airport.Ticket;
import com.example.demo1.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PassengerTickets implements Initializable
{
    @FXML
    private Button backButton;
    @FXML
    private ListView<String> dateList;
    @FXML
    private ListView<String> fromList;
    @FXML
    private ListView<String> idList;
    @FXML
    private ListView<String> lengthList;
    @FXML
    private ListView<String> numList;
    @FXML
    private ListView<String> penaltyList;
    @FXML
    private Button removeButton;
    @FXML
    private ListView<String> statusList;
    @FXML
    private ListView<String> timeList;
    @FXML
    private ListView<String> toList;
    @FXML
    private Label checker;

    protected int selectedTicket;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        removeButton.setDisable(true);
        updateLists(numList, idList, fromList, toList, statusList, dateList, timeList, lengthList, penaltyList);

        numList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                removeButton.setDisable(false);
                selectedTicket = numList.getSelectionModel().getSelectedIndex();
            }
        });
    }

    @FXML
    void pressedRemove(ActionEvent event) {
        double price = Main.passengers.get(AirportLogin.userIndex).getTickets().get(selectedTicket).getPrice();
        double penalty = Main.passengers.get(AirportLogin.userIndex).getTickets().get(selectedTicket).getPenalty();
        double refund = price - penalty;
        checker.setText("Ticket Canceled! Cancellation Penalty Applied( "+refund+"$ refunded).");
        removeButton.setDisable(true);


        Main.passengers.get(AirportLogin.userIndex).incrementWallet(refund);
        Main.passengers.get(AirportLogin.userIndex).getTickets().get(selectedTicket).getFlight().decreaseSoldTickets(1);
        Main.passengers.get(AirportLogin.userIndex).getTickets().get(selectedTicket).getFlight().getPassengers().remove(Main.passengers.get(AirportLogin.userIndex));
        Main.passengers.get(AirportLogin.userIndex).getTickets().remove(Main.passengers.get(AirportLogin.userIndex).getTickets().get(selectedTicket));

        updateLists(numList, idList, fromList, toList, statusList, dateList, timeList, lengthList, penaltyList);
    }

    @FXML
    void pressedBack(ActionEvent event) throws IOException {
        Main.sceneSwitch("PassengerPage.fxml", event, 520, 400);
    }

    protected static void updateLists(ListView<String> numList, ListView<String> idList, ListView<String> fromList, ListView<String> toList, ListView<String> statusList , ListView<String> dateList, ListView<String> timeList, ListView<String> lengthList, ListView<String> penaltyList) {
        try {
            numList.getItems().clear();
            idList.getItems().clear();
            fromList.getItems().clear();
            toList.getItems().clear();
            statusList.getItems().clear();
            dateList.getItems().clear();
            timeList.getItems().clear();
            lengthList.getItems().clear();
            penaltyList.getItems().clear();

            for (Ticket obj : Main.passengers.get(AirportLogin.userIndex).getTickets()) {
                numList.getItems().add(Integer.toString(Main.passengers.get(AirportLogin.userIndex).getTickets().indexOf(obj)+1));
                idList.getItems().add(obj.getId()+"");
                fromList.getItems().add(obj.getFlight().getFrom());
                toList.getItems().add(obj.getFlight().getTo());
                statusList.getItems().add(obj.getFlight().getStatus()+"");
                dateList.getItems().add(obj.getFlight().getMonth()+"/"+obj.getFlight().getDay());
                timeList.getItems().add(obj.getFlight().getHour()+":"+obj.getFlight().getMinute());
                lengthList.getItems().add(obj.getFlight().getFlightLenght()+"H");
                penaltyList.getItems().add(obj.getPenalty()+"$");
            }
        } catch(Exception e) { Main.appendToFile(e); }
    }
}