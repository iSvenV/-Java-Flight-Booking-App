package com.example.demo1.Controller;
import Model.Airport.Passenger;
import Model.Airport.Report;
import Model.Departments.Airport;
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

public class EmployeeEdit implements Initializable
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
    @FXML
    private TextField inputLocation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appliedLabel.setVisible(false);

        int index;
        if(AirportLogin.userRole.equals("employee"))
            index = AirportLogin.userIndex;
        else
            index = EmployeeManagement.selectedEmployee;

        String fullname[] = Main.employees.get(index).getFullname().split(" ");
        String firstname = fullname[0];
        String lastname = fullname[1];

        inputID.setText(Main.employees.get(index).getId()+"");
        inputFirstname.setText(firstname);
        inputLastname.setText(lastname);
        inputUsername.setText(Main.employees.get(index).getUsername());
        inputPassword.setText(Main.employees.get(index).getPassword());
        inputPhone.setText(Main.employees.get(index).getPhone());
        inputEmail.setText(Main.employees.get(index).getEmail());
        inputLocation.setText(Main.employees.get(index).getAddress());
    }

    @FXML
    void pressedApply(ActionEvent event) {
        appliedLabel.setVisible(false);

        int index;
        if(AirportLogin.userRole.equals("employee"))
            index = AirportLogin.userIndex;
        else
            index = EmployeeManagement.selectedEmployee;

        int generalIndex = Main.users.indexOf(Main.employees.get(index));

        int id;
        try {
            id = Integer.parseInt(inputID.getText());
            if(!Main.checkID(id, generalIndex)) {
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
            if(!Main.checkPhone(phone+"", generalIndex)) {
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
        if(Main.regexAlpha(inputFirstname.getText()) && Main.regexAlpha(inputLastname.getText())) {
            firstname=inputFirstname.getText();
            lastname=inputLastname.getText();
        }
        else {
            checker.setText("Only alphabets are valid for Firstname & Lastname!");
            appliedLabel.setVisible(false);
            return;
        }

        String username = null;
        if(Main.regexAlphaNum(inputUsername.getText())) {
            username = inputUsername.getText();
            if (!Main.checkUsername(username, generalIndex)) {
                checker.setText("a user with this username already exists!");
                return;
            }
        }
        else {
            checker.setText("Only alphabets & numbers are valid for username!");
            return;
        }

        String email = null;
        if(Main.regexEmail(inputEmail.getText())) {
            email=inputEmail.getText();
            if(!Main.checkEmail(email, generalIndex)) {
                checker.setText("a user with this email alraedy exists!");
                return;
            }
        }
        else {
            checker.setText("email format is invalid! (example@domain.com)");
            return;
        }

        String password = inputPassword.getText();

        Main.employees.get(index).setId(id);
        Main.employees.get(index).setFullname(firstname+" "+lastname);
        Main.employees.get(index).setUsername(username);
        Main.employees.get(index).setPassword(password);
        Main.employees.get(index).setPhone(phone+"");
        Main.employees.get(index).setEmail(email);

        checker.setText("");
        appliedLabel.setVisible(true);
        Main.reports.add(new Report("profile edited.", Main.employees.get(index)));
    }

    @FXML
    void pressedBack(ActionEvent event) throws IOException {
        if(AirportLogin.userRole.equals("employee"))
            Main.sceneSwitch("EmployeePage.fxml", event, 520, 400);
        else
            Main.sceneSwitch("EmployeeManagement.fxml", event, 520, 400);
    }
}