package com.example.demo1.Controller;
import com.example.demo1.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PassengerInfo implements Initializable
{
    @FXML
    private Label email;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Button okButton;
    @FXML
    private Label password;
    @FXML
    private Label phone;
    @FXML
    private Label username;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int index = PassengerManagement.selectedPassenger;
        id.setText(Main.passengers.get(index).getId()+"");
        name.setText(Main.passengers.get(index).getFullname());
        username.setText(Main.passengers.get(index).getUsername());
        password.setText(Main.passengers.get(index).getPassword());
        phone.setText(Main.passengers.get(index).getPhone());
        email.setText(Main.passengers.get(index).getEmail());
    }

    @FXML
    void pressedOk(ActionEvent event) {
        Stage addStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        addStage.close();
    }
}