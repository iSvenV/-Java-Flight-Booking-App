package com.example.demo1.Controller.Municipality;
import Model.Departments.*;
import Model.Persons.Admin;
import Model.Persons.Mayor;
import Model.Persons.Municipality;
import com.example.demo1.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MayorAddPage implements Initializable
{
    @FXML
    private RadioButton airportBox;
    @FXML
    private RadioButton hospitalBox;
    @FXML
    private TextField inputName;
    @FXML
    private TextField inputYear;
    @FXML
    private RadioButton instituteBox;
    @FXML
    private RadioButton libraryBox;
    @FXML
    private RadioButton universityBox;
    @FXML
    private Button cancelButton;
    @FXML
    private Button createButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createButton.setDisable(true);

        inputName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if(!(t1.equals("")))
                    inputYear.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                            if(!(t1.equals("")))
                                createButton.setDisable(false);
                        }
                    });
            }
        });
    }

    @FXML
    void pressedCancel(ActionEvent event) {
        Stage addStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        addStage.close();
    }

    @FXML
    void pressedCreate(ActionEvent event) {
        String name = inputName.getText();

        int year = 0;
        try{ year = Integer.parseInt(inputYear.getText()); }
        catch(Exception e) {
            System.out.println("Couldn't Cast String to Integer!");
            Main.appendToFile(e);
            return;
        }

        Department obj = null;
        switch(selectedType) {
            case "airport":
                obj = new Airport(name, year);
                break;
            case "hospital":
                obj = new Hospital(name, year);
                break;
            case "library":
                obj = new Library(name, year);
                break;
            case "institute":
                obj = new Institute(name, year);
                break;
            case "university":
                obj = new University(name, year);
                break;
        }
        
        Main.departments.add(obj);
        Stage addStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        addStage.close();
    }

    private String selectedType;
    @FXML
    void selectedBox(ActionEvent event) {
        if(airportBox.isSelected())
            selectedType="airport";
        else if(hospitalBox.isSelected())
            selectedType="hospital";
        else if(instituteBox.isSelected())
            selectedType="institute";
        else if(libraryBox.isSelected())
            selectedType="library";
        else if(universityBox.isSelected())
            selectedType="university";
    }
}