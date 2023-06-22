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

public class ManagerInfo implements Initializable
{
    @FXML
    private Label address;
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
    private Label salary;
    @FXML
    private Label username;

    public static String userType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(userType.equals("manager")) {
            int index = ManagerManagement.selectedManager;
            id.setText(Main.managers.get(index).getId()+"");
            name.setText(Main.managers.get(index).getFullname());
            username.setText(Main.managers.get(index).getUsername());
            password.setText(Main.managers.get(index).getPassword());
            phone.setText(Main.managers.get(index).getPhone());
            email.setText(Main.managers.get(index).getEmail());
            address.setText(Main.managers.get(index).getAddress());
            salary.setText(Main.managers.get(index).getSalary()+"$");
        }
        else {
            int index = EmployeeManagement.selectedEmployee;
            id.setText(Main.employees.get(index).getId()+"");
            name.setText(Main.employees.get(index).getFullname());
            username.setText(Main.employees.get(index).getUsername());
            password.setText(Main.employees.get(index).getPassword());
            phone.setText(Main.employees.get(index).getPhone());
            email.setText(Main.employees.get(index).getEmail());
            address.setText(Main.employees.get(index).getAddress());
            salary.setText(Main.employees.get(index).getSalary()+"$");
        }
    }

    @FXML
    void pressedOk(ActionEvent event) {
        Stage addStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        addStage.close();
    }
}