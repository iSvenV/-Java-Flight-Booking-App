package com.example.demo1.Controller;
import Model.Airport.Flight;
import com.example.demo1.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PassengerFlights implements Initializable
{
    @FXML
    private Button backButton;
    @FXML
    private Button buyButton;
    @FXML
    private Label checker;
    @FXML
    private Label purchaseLabel;
    @FXML
    private ListView<String> dateList;
    @FXML
    private ListView<String> fromList;
    @FXML
    private ListView<String> idList;
    @FXML
    private TextField inputTicketNum;
    @FXML
    private ListView<String> lengthList;
    @FXML
    private ListView<String> numList;
    @FXML
    private ListView<String> priceList;
    @FXML
    private ListView<String> ticketsList;
    @FXML
    private ListView<String> timeList;
    @FXML
    private ListView<String> toList;

    protected static int selectedFlight;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buyButton.setDisable(true);
        inputTicketNum.setDisable(true);
        purchaseLabel.setVisible(false);
        updateLists(numList, idList, fromList, toList, dateList, timeList, lengthList, ticketsList, priceList);

        numList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                inputTicketNum.setDisable(false);
                selectedFlight = numList.getSelectionModel().getSelectedIndex();
            }
        });
        inputTicketNum.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if(!(t1.equals("")))
                    buyButton.setDisable(false);
            }
        });
    }

    @FXML
    void pressedBuy(ActionEvent event) {
        purchaseLabel.setVisible(false);
        int input;
        try { input = Integer.parseInt(inputTicketNum.getText()); }
        catch (Exception e) {
            checker.setText("input is invalid!");
            System.out.println("Couldn't Cast String to Integer!");
            Main.appendToFile(e);
            return;
        }

        if(input<=0) {
            checker.setText("input is invalid!");
            return;
        }

        double payment = input * Main.allFlights.get(selectedFlight).getTicket().getPrice();
        if(Main.passengers.get(AirportLogin.userIndex).getWallet() < payment) {
            checker.setText("you don't have enough balance!");
            return;
        }

        int availableTickets = Main.allFlights.get(selectedFlight).getPlane().getSeats() - Main.allFlights.get(selectedFlight).getSoldTickets();
        if(availableTickets < input) {
            checker.setText("only "+availableTickets+" Seats are available for purchase!");
            return;
        }

        purchaseLabel.setVisible(true);

        for(int i=0; i<input; i++)
            Main.passengers.get(AirportLogin.userIndex).getTickets().add(Main.allFlights.get(selectedFlight).getTicket());

        Main.passengers.get(AirportLogin.userIndex).decrementWallet(payment);
        Main.allFlights.get(selectedFlight).increaseSoldTickets(input);
        Main.allFlights.get(selectedFlight).getPassengers().add(Main.passengers.get(AirportLogin.userIndex));
        updateLists(numList, idList, fromList, toList, dateList, timeList, lengthList, ticketsList, priceList);
    }

    @FXML
    void pressedBack(ActionEvent event) throws IOException {
        Main.sceneSwitch("PassengerPage.fxml", event, 520, 400);
    }

    protected static void updateLists(ListView<String> numList, ListView<String> idList, ListView<String> fromList, ListView<String> toList, ListView<String> dateList, ListView<String> timeList, ListView<String> lengthList, ListView<String> ticketsList, ListView<String> priceList) {
        try {
            numList.getItems().clear();
            idList.getItems().clear();
            fromList.getItems().clear();
            toList.getItems().clear();
            dateList.getItems().clear();
            timeList.getItems().clear();
            lengthList.getItems().clear();
            ticketsList.getItems().clear();
            priceList.getItems().clear();

            for (Flight obj : Main.allFlights) {
                int seats = obj.getPlane().getSeats();
                int soldTickets = obj.getSoldTickets();
                if(seats-soldTickets == 0)
                    continue;
                if(!(obj.getStatus() == Flight.Status.OPEN))
                    continue;

                numList.getItems().add(Integer.toString(Main.allFlights.indexOf(obj)+1));
                idList.getItems().add(obj.getId()+"");
                fromList.getItems().add(obj.getFrom());
                toList.getItems().add(obj.getTo());
                dateList.getItems().add(obj.getMonth()+"/"+obj.getDay());
                timeList.getItems().add(obj.getHour()+":"+obj.getMinute());
                lengthList.getItems().add(obj.getFlightLenght()+"H");
                priceList.getItems().add(obj.getTicket().getPrice()+"$");
                ticketsList.getItems().add((seats-soldTickets)+"");
            }
        } catch(Exception e) { Main.appendToFile(e); }
    }
}