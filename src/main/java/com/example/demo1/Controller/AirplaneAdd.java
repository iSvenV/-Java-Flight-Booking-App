package com.example.demo1.Controller;
import Model.Airport.Airplane;
import Model.Airport.Passenger;
import com.example.demo1.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AirplaneAdd implements Initializable
{
    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonCreate;
    @FXML
    private Label checker;
    @FXML
    private TextField inputID;
    @FXML
    private TextField inputSeats;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonCreate.setDisable(true);

        inputID.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (!(t1.equals("")))
                    inputSeats.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                            if(!(t1.equals("")))
                                buttonCreate.setDisable(false);
                        }
                    });
            }
        });
    }

    @FXML
    void pressedBack(ActionEvent event) {
        Stage addStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        addStage.close();
    }

    @FXML
    void pressedCreate(ActionEvent event) {
        int id = 0;
        try {
            id = Integer.parseInt(inputID.getText());
            if(!Main.airplaneCheckID(id, -1)) {
                checker.setText("an airplane with this ID already exists!");
                return;
            }
        }
        catch(Exception e) {
            checker.setText("ID input is invalid!");
            System.out.println("Couldn't Cast String to Integer!");
            Main.appendToFile(e);
            return;
        }

        int seats = 0;
        try { seats = Integer.parseInt(inputSeats.getText()); }
        catch(Exception e) {
            checker.setText("ID input is invalid!");
            System.out.println("Couldn't Cast String to Integer!");
            Main.appendToFile(e);
            return;
        }

        Airplane obj = new Airplane(id, seats);
        Main.airplanes.add(obj);

        Stage addStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        addStage.close();
    }
}