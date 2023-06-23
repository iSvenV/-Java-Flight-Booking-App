package com.example.demo1.Controller;
import com.example.demo1.Main;
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

public class AirplaneEdit implements Initializable
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
    @FXML
    private Label appliedLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appliedLabel.setVisible(false);
        int index = AirplaneManagement.selectedAirplane;

        inputID.setText(Main.airplanes.get(index).getId()+"");
        inputSeats.setText(Main.airplanes.get(index).getSeats()+"");
    }

    @FXML
    void pressedCreate(ActionEvent event) {
        appliedLabel.setVisible(false);
        int index = AirplaneManagement.selectedAirplane;

        int id;
        try {
            id = Integer.parseInt(inputID.getText());
            if(!Main.airplaneCheckID(id, index)) {
                checker.setText("an airplane with this ID already exists!");
                return;
            }
        }
        catch(Exception e) {
            checker.setText("ID input is invalid!");
            System.out.println("Couldn't Cast String to Integer!");
            Main.appendToFile(e);
            appliedLabel.setVisible(false);
            return;
        }

        int seats;
        try { seats = Integer.parseInt(inputSeats.getText()); }
        catch(Exception e) {
            checker.setText("Number of Seats input is invalid!");
            System.out.println("Couldn't Cast String to Integer!");
            Main.appendToFile(e);
            appliedLabel.setVisible(false);
            return;
        }

        Main.airplanes.get(index).setId(id);
        Main.airplanes.get(index).setSeats(seats);

        checker.setText("");
        appliedLabel.setVisible(true);
    }

    @FXML
    void pressedBack(ActionEvent event) {
        Stage addStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        addStage.close();
    }
}