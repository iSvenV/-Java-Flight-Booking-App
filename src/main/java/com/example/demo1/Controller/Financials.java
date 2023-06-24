package com.example.demo1.Controller;
import Model.Airport.Employee;
import Model.Airport.Manager;
import Model.Airport.Passenger;
import Model.Airport.User;
import com.example.demo1.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Financials implements Initializable
{
    @FXML
    private Button backButton;
    @FXML
    private Button editButton;
    @FXML
    private ListView<String> idList;
    @FXML
    private TextField inputSalary;
    @FXML
    private ListView<String> nameList;
    @FXML
    private ListView<String> roleList;
    @FXML
    private ListView<String> salaryList;
    @FXML
    private ListView<String> numList;
    @FXML
    private ChoiceBox<String> choiceBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editButton.setDisable(true);
        inputSalary.setDisable(true);

        String[] boxes = {"Managers", "Employees", "Passengers"};
        choiceBox.getItems().addAll(boxes);
        choiceBox.setValue("Managers");
        choiceBox.setOnAction(this::selectedBox);
        updateLists(numList, idList, nameList, roleList, salaryList);

        numList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(!choiceBox.getValue().equals("Passengers")) {
                    editButton.setDisable(false);
                    inputSalary.setDisable(false);
                }
            }
        });
    }

    @FXML
    void pressedBack(ActionEvent event) throws IOException {
        Main.sceneSwitch("AdminMenu.fxml", event, 520, 400);
    }

    @FXML
    void pressedEdit(ActionEvent event) {
        int index = numList.getSelectionModel().getSelectedIndex();
        String input = inputSalary.getText();

        try {
            switch (selectedBox) {
                case "Managers" -> Main.managers.get(index).setSalary(Double.parseDouble(input));
                case "Employees" -> Main.employees.get(index).setSalary(Double.parseDouble(input));
                case "Passengers" -> Main.passengers.get(index).setWallet(Double.parseDouble(input));
            }
            updateLists(numList, idList, nameList, roleList, salaryList);
            editButton.setDisable(true);
            inputSalary.setDisable(true);
        }
        catch(Exception e) {
            System.out.println("ERROR: Couldn't cast String to Double.");
            Main.appendToFile(e);
        }
    }

    private static String selectedBox = "Managers";
    public static void updateLists(ListView<String> numList, ListView<String> idList, ListView<String> nameList, ListView<String> roleList, ListView<String> salaryList) {
        try {
            numList.getItems().clear();
            idList.getItems().clear();
            nameList.getItems().clear();
            roleList.getItems().clear();
            salaryList.getItems().clear();

            switch (selectedBox) {
                case "Managers" -> {
                    for (Manager obj : Main.managers) {
                        numList.getItems().add(Integer.toString(Main.managers.indexOf(obj) + 1));
                        idList.getItems().add(obj.getId() + "");
                        nameList.getItems().add(obj.getFullname());
                        roleList.getItems().add(obj.getRole().toString());
                        salaryList.getItems().add(obj.getSalary() + "");
                    }
                }
                case "Employees" -> {
                    for (Employee obj : Main.employees) {
                        numList.getItems().add(Integer.toString(Main.employees.indexOf(obj) + 1));
                        idList.getItems().add(obj.getId() + "");
                        nameList.getItems().add(obj.getFullname());
                        roleList.getItems().add(obj.getRole().toString());
                        salaryList.getItems().add(obj.getSalary() + "");
                    }
                }
                case "Passengers" -> {
                    for (Passenger obj : Main.passengers) {
                        numList.getItems().add(Integer.toString(Main.passengers.indexOf(obj) + 1));
                        idList.getItems().add(obj.getId() + "");
                        nameList.getItems().add(obj.getFullname());
                        roleList.getItems().add(obj.getRole().toString());
                        salaryList.getItems().add(obj.getWallet() + "");
                    }
                }
            }
        } catch(Exception e) { Main.appendToFile(e);}
    }

    private void selectedBox(ActionEvent event) {
        selectedBox = choiceBox.getValue();
        editButton.setDisable(true);
        inputSalary.setDisable(true);
        updateLists(numList, idList, nameList, roleList, salaryList);
    }
}