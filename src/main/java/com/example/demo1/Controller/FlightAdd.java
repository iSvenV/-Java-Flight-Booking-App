package com.example.demo1.Controller;
import Model.Airport.Flight;
import Model.Departments.*;
import com.example.demo1.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FlightAdd implements Initializable
{
    @FXML
    private RadioButton airborneBox;
    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonCreate;
    @FXML
    private RadioButton canceledBox;
    @FXML
    private TextField inputMonth;
    @FXML
    private TextField inputDay;
    @FXML
    private Label checker;
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
    private TextField inputTo;
    @FXML
    private RadioButton landedBox;
    @FXML
    private RadioButton openBox;
    @FXML
    private ToggleGroup status;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonCreate.setDisable(true);
        openBox.setSelected(true);

//        String[] dayBoxes = new String[31];
//        for(int i=0; i<31; i++)
//            dayBoxes[i] = (i++)+"";
//        checkboxDay.getItems().addAll(dayBoxes);
//        checkboxDay.setValue("1");
//        checkboxDay.setOnAction(this::selectedDay);
//
//        String[] monthBoxes = new String[12];
//        for(int i=0; i<12; i++)
//            dayBoxes[i] = (i++)+"";
//        checkboxMonth.getItems().addAll(monthBoxes);
//        checkboxMonth.setValue("1");
//        checkboxMonth.setOnAction(this::selectedDay);

        inputID.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (!(t1.equals("")))
                    inputFrom.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                            if(!(t1.equals("")))
                                inputTo.textProperty().addListener(new ChangeListener<String>() {
                                    @Override
                                    public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                                        if(!(t1.equals("")))
                                            inputLength.textProperty().addListener(new ChangeListener<String>() {
                                                @Override
                                                public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                                                    if(!(t1.equals("")))
                                                        buttonCreate.setDisable(false);
                                                }
                                            });
                                    }
                                });
                        }
                    });
            }
        });
    }

    @FXML
    void pressedCreate(ActionEvent event) {
        int id = 0;
        try {
            id = Integer.parseInt(inputID.getText());
            if(!Main.flightCheckID(id, -1)) {
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

        Flight obj = null;
        switch(selectedStatus) {
            case "open":
                obj = new Flight(id, Main.airplanes.get(AirplaneManagement.selectedAirplane), from, to, day, month, minute, hour, length, Flight.Status.OPEN);
                break;
            case "canceled":
                obj = new Flight(id, Main.airplanes.get(AirplaneManagement.selectedAirplane), from, to, day, month, minute, hour, length, Flight.Status.CANCELED);
                break;
            case "airborne":
                obj = new Flight(id, Main.airplanes.get(AirplaneManagement.selectedAirplane), from, to, day, month, minute, hour, length, Flight.Status.AIRBORNE);
                break;
            case "landed":
                obj = new Flight(id, Main.airplanes.get(AirplaneManagement.selectedAirplane), from, to, day, month, minute, hour, length, Flight.Status.LANDED);
                break;
        }

        Main.allFlights.add(obj);
        Main.airplanes.get(AirplaneManagement.selectedAirplane).getFlights().add(obj);

        Stage addStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        addStage.close();
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

//    private static String selectedDay;
//    private void selectedDay(ActionEvent event) {
//        selectedDay = checkboxDay.getValue();
//    }
//
//    private static String selectedMonth;
//    private void selectedMonth(ActionEvent event) {
//        selectedMonth = checkboxMonth.getValue();
//    }
}