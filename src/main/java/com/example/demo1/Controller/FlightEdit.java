package com.example.demo1.Controller;
import Model.Airport.Flight;
import com.example.demo1.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class FlightEdit implements Initializable
{
    @FXML
    private RadioButton airborneBox;
    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonApply;
    @FXML
    private RadioButton canceledBox;
    @FXML
    private Label checker;
    @FXML
    private Label appliedLabel;
    @FXML
    private TextField inputDay;
    @FXML
    private TextField inputFrom;
    @FXML
    private TextField inputHour;
    @FXML
    private TextField inputID;
    @FXML
    private TextField inputLength;
    @FXML
    private TextField inputMinute;
    @FXML
    private TextField inputMonth;
    @FXML
    private TextField inputTo;
    @FXML
    private RadioButton landedBox;
    @FXML
    private RadioButton openBox;
    @FXML
    private ToggleGroup status;
    @FXML
    private Label titleLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appliedLabel.setVisible(false);

        int planeIndex = AirplaneManagement.selectedAirplane;
        int flightIndex = FlightManagement.selectedFlight;
        titleLabel.setText(Main.airplanes.get(planeIndex).getFlights().get(flightIndex).getId()+"");

        inputID.setText(Main.airplanes.get(planeIndex).getFlights().get(flightIndex).getId()+"");
        inputFrom.setText(Main.airplanes.get(planeIndex).getFlights().get(flightIndex).getFrom());
        inputTo.setText(Main.airplanes.get(planeIndex).getFlights().get(flightIndex).getTo());
        inputMonth.setText(Main.airplanes.get(planeIndex).getFlights().get(flightIndex).getMonth()+"");
        inputDay.setText(Main.airplanes.get(planeIndex).getFlights().get(flightIndex).getDay()+"");
        inputHour.setText(Main.airplanes.get(planeIndex).getFlights().get(flightIndex).getHour()+"");
        inputMinute.setText(Main.airplanes.get(planeIndex).getFlights().get(flightIndex).getMinute()+"");
        inputLength.setText(Main.airplanes.get(planeIndex).getFlights().get(flightIndex).getFlightLenght()+"");

        switch (Main.airplanes.get(planeIndex).getFlights().get(flightIndex).getStatus() + "") {
            case "OPEN":
                openBox.setSelected(true);
                selectedStatus = "open";
                break;
            case "CANCELED":
                canceledBox.setSelected(true);
                selectedStatus = "canceled";
                break;
            case "AIRBORNE":
                airborneBox.setSelected(true);
                selectedStatus = "airborne";
                break;
            case "LANDED":
                landedBox.setSelected(true);
                selectedStatus = "landed";
                break;
        }
    }

    @FXML
    void pressedApply(ActionEvent event) {
        appliedLabel.setVisible(false);
        int index = FlightManagement.selectedFlight;
        int generalIndex = Main.allFlights.indexOf(Main.airplanes.get(AirplaneManagement.selectedAirplane).getFlights().get(index));

        int id;
        try {
            id = Integer.parseInt(inputID.getText());
            if(!Main.flightCheckID(id, generalIndex)) {
                checker.setText("a flight with this ID already exists!");
                return;
            }
        }
        catch(Exception e) {
            checker.setText("ID input is invalid!");
            System.out.println("Couldn't Cast String to Integer!");
            Main.appendToFile(e);
            return;
        }

        String from;
        if(Main.regexAlpha(inputFrom.getText())) {
            from = inputFrom.getText();
        }
        else {
            checker.setText("Only alphabets are valid for Address!");
            return;
        }

        String to;
        if(Main.regexAlpha(inputTo.getText())) {
            to = inputTo.getText();
        }
        else {
            checker.setText("Only alphabets are valid for Address!");
            return;
        }

        int month;
        try {
            month = Integer.parseInt(inputMonth.getText());
            if(month<1 || month>12) {
                checker.setText("Month input is invalid!");
                return;
            }
        }
        catch(Exception e) {
            checker.setText("Month input is invalid!");
            System.out.println("Couldn't Cast String to Integer!");
            Main.appendToFile(e);
            return;
        }

        int day;
        try {
            day = Integer.parseInt(inputDay.getText());
            if(day<0 || day>31) {
                checker.setText("Day input is invalid!");
                return;
            }
        }
        catch(Exception e) {
            checker.setText("Day input is invalid!");
            System.out.println("Couldn't Cast String to Integer!");
            Main.appendToFile(e);
            return;
        }

        int hour;
        try {
            hour = Integer.parseInt(inputHour.getText());
            if(hour<0 || hour>23) {
                checker.setText("Hour input is invalid!");
                return;
            }
        }
        catch(Exception e) {
            checker.setText("Hour input is invalid!");
            System.out.println("Couldn't Cast String to Integer!");
            Main.appendToFile(e);
            return;
        }

        int minute;
        try {
            minute = Integer.parseInt(inputMinute.getText());
            if(minute<0 || minute>59) {
                checker.setText("Minute input is invalid!");
                return;
            }
        }
        catch(Exception e) {
            checker.setText("Minute input is invalid!");
            System.out.println("Couldn't Cast String to Integer!");
            Main.appendToFile(e);
            return;
        }

        int length;
        try { length = Integer.parseInt(inputLength.getText()); }
        catch(Exception e) {
            checker.setText("Flight Length input is invalid!");
            System.out.println("Couldn't Cast String to Integer!");
            Main.appendToFile(e);
            return;
        }

        int planeIndex = AirplaneManagement.selectedAirplane;
        int flightIndex = FlightManagement.selectedFlight;

        Main.airplanes.get(planeIndex).getFlights().get(flightIndex).setId(id);
        Main.airplanes.get(planeIndex).getFlights().get(flightIndex).setFrom(from);
        Main.airplanes.get(planeIndex).getFlights().get(flightIndex).setTo(to);
        Main.airplanes.get(planeIndex).getFlights().get(flightIndex).setMonth(month);
        Main.airplanes.get(planeIndex).getFlights().get(flightIndex).setDay(day);
        Main.airplanes.get(planeIndex).getFlights().get(flightIndex).setHour(hour);
        Main.airplanes.get(planeIndex).getFlights().get(flightIndex).setMinute(minute);
        Main.airplanes.get(planeIndex).getFlights().get(flightIndex).setFlightLenght(length);

        switch(selectedStatus) {
            case "open":
                Main.airplanes.get(planeIndex).getFlights().get(flightIndex).setStatus(Flight.Status.OPEN);
                break;
            case "canceled":
                Main.airplanes.get(planeIndex).getFlights().get(flightIndex).setStatus(Flight.Status.CANCELED);
                break;
            case "airborne":
                Main.airplanes.get(planeIndex).getFlights().get(flightIndex).setStatus(Flight.Status.AIRBORNE);
                break;
            case "landed":
                Main.airplanes.get(planeIndex).getFlights().get(flightIndex).setStatus(Flight.Status.LANDED);
                break;
        }

        checker.setText("");
        appliedLabel.setVisible(true);
    }

    @FXML
    void pressedBack(ActionEvent event) {
        Stage addStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        addStage.close();
    }

    private String selectedStatus;
    @FXML
    void selectedBox(ActionEvent event) {
        if(openBox.isSelected())
            selectedStatus="open";
        else if(canceledBox.isSelected())
            selectedStatus="canceled";
        else if(airborneBox.isSelected())
            selectedStatus="airborne";
        else if(landedBox.isSelected())
            selectedStatus="landed";
    }
}