package com.example.demo1.Controller;
import Model.Airport.Passenger;
import com.example.demo1.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PassengerEdit implements Initializable
{
    @FXML
    private Label appliedLabel;
    @FXML
    private Button applyButton;
    @FXML
    private Button backButton;
    @FXML
    private Label checker;
    @FXML
    private TextField inputEmail;
    @FXML
    private TextField inputFirstname;
    @FXML
    private TextField inputID;
    @FXML
    private TextField inputLastname;
    @FXML
    private TextField inputPassword;
    @FXML
    private TextField inputPhone;
    @FXML
    private TextField inputUsername;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appliedLabel.setVisible(false);
        int index = AirportLogin.userIndex;

        String fullname[] = Main.passengers.get(index).getFullname().split(" ");
        String firstname = fullname[0];
        String lastname = fullname[1];

        inputID.setText(Main.passengers.get(index).getId()+"");
        inputFirstname.setText(firstname);
        inputLastname.setText(lastname);
        inputUsername.setText(Main.passengers.get(index).getUsername());
        inputPassword.setText(Main.passengers.get(index).getPassword());
        inputPhone.setText(Main.passengers.get(index).getPhone());
        inputEmail.setText(Main.passengers.get(index).getEmail());
    }

    @FXML
    void pressedApply(ActionEvent event) {
        int id;
        try {
            id = Integer.parseInt(inputID.getText());
            if(!Main.checkID(id)) {
                checker.setText("a user with this ID already exists!");
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

        int phone = 0;
        try {
            phone = Integer.parseInt(inputPhone.getText());
            if(!Main.checkPhone(phone+"")) {
                checker.setText("a user with this phone number already exists!");
                return;
            }
        }
        catch(Exception e) {
            checker.setText("Phone Number is invalid!");
            System.out.println("Couldn't Cast String to Integer!");
            Main.appendToFile(e);
            appliedLabel.setVisible(false);
            return;
        }

        String firstname, lastname;
        if(AirportSignup.regexAlpha(inputFirstname.getText()) && AirportSignup.regexAlpha(inputLastname.getText())) {
            firstname=inputFirstname.getText();
            lastname=inputLastname.getText();
        }
        else {
            checker.setText("Only alphabets are valid for Firstname & Lastname!");
            appliedLabel.setVisible(false);
            return;
        }

        String username = null;
        if(AirportSignup.regexAlphaNum(inputUsername.getText())) {
            username = inputUsername.getText();
            if (!Main.checkUsername(username)) {
                checker.setText("a user with this username already exists!");
                return;
            }
        }
        else {
            checker.setText("Only alphabets & numbers are valid for username!");
            return;
        }

        String email = null;
        if(AirportSignup.regexEmail(inputEmail.getText())) {
            email=inputEmail.getText();
            if(!Main.checkEmail(email)) {
                checker.setText("a user with this email alraedy exists!");
                return;
            }
        }
        else {
            checker.setText("email format is invalid! (example@domain.com)");
            return;
        }

        String password = inputPassword.getText();

        int index = AirportLogin.userIndex;
        Main.passengers.get(index).setId(id);
        Main.passengers.get(index).setFullname(firstname+" "+lastname);
        Main.passengers.get(index).setUsername(username);
        Main.passengers.get(index).setPassword(password);
        Main.passengers.get(index).setPhone(phone+"");
        Main.passengers.get(index).setEmail(email);

        checker.setText("");
        appliedLabel.setVisible(true);
    }

    @FXML
    void pressedBack(ActionEvent event) throws IOException {
        sceneSwitch("PassengerPage.fxml", event, 520, 400);
    }

    public void sceneSwitch(String url, ActionEvent event, int v, int v1) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo1/View/"+url));
        Parent root = fxmlLoader.load();
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, v, v1);
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.setTitle("Munix");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}