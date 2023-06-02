package com.example.demo1.Controller;
import Model.Departments.*;
import Model.Persons.*;
import com.example.demo1.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminAddPage implements Initializable
{
    @FXML
    private RadioButton afternoonBox;
    @FXML
    private Button cancelButton;
    @FXML
    private Button createButton;
    @FXML
    private RadioButton deputyBox;
    @FXML
    private RadioButton employeeBox;
    @FXML
    private TextField inputBaseSalary;
    @FXML
    private TextField inputID;
    @FXML
    private TextField inputName;
    @FXML
    private TextField inputWorkedShifts;
    @FXML
    private TextField inputYear;
    @FXML
    private RadioButton inspectorBox;
    @FXML
    private RadioButton mayorBox;
    @FXML
    private RadioButton morningBox;
    @FXML
    private RadioButton nightBox;
    @FXML
    private ToggleGroup role;
    @FXML
    private RadioButton securityBox;
    @FXML
    private ToggleGroup shift;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inputBaseSalary.setVisible(false);
        inputWorkedShifts.setVisible(false);
        morningBox.setVisible(false);
        afternoonBox.setVisible(false);
        nightBox.setVisible(false);
        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);

        createButton.setDisable(true);
        inputName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if(!(t1.equals("")))
                    inputID.textProperty().addListener(new ChangeListener<String>() {
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
        Security.Shifts shift = null;            /// could be "new Shifts();

        int year = 0;
        try{ year = Integer.parseInt(inputYear.getText()); }
        catch(Exception e) {
            System.out.println("Couldn't Cast String to Integer!");
            Main.appendToFile(e);
            return;
        }

        int id = 0;
        try{ id = Integer.parseInt(inputID.getText()); }
        catch(Exception e) {
            System.out.println("Couldn't Cast String to Integer!");
            Main.appendToFile(e);
            return;
        }

        int workedShifts = 0;
        if(selectedRole.equals("employee") || selectedRole.equals("inspector") || selectedRole.equals("security")) {
            try{ workedShifts = Integer.parseInt(inputWorkedShifts.getText()); }
            catch(Exception e) {
                System.out.println("Couldn't Cast String to Integer!");
                Main.appendToFile(e);
                return;
            }
        }

        double baseSalary = 0;
        if(selectedRole.equals("mayor") || selectedRole.equals("deputy")) {
            try{ baseSalary = Double.parseDouble(inputBaseSalary.getText()); }
            catch(Exception e) {
                System.out.println("Couldn't Cast String to Double!");
                Main.appendToFile(e);
                return;
            }
        }

        Municipality obj = null;
        switch(selectedRole) {
            case "mayor":
                obj = new Mayor(name, id, year, "mayor", "mayor");
                obj.setBaseSalary(baseSalary);
                break;
            case "deputy":
                obj = new Deputy(name, id, year, baseSalary);
                break;
            case "employee":
                obj = new Employee(name, id, year, workedShifts);
                break;
            case "inspector":
                obj = new Inspector(name, id, year, workedShifts);
                break;
            case "security": {
                switch(selectedshift) {
                    case "morning":
                        obj = new Security(name, id, year, workedShifts, Security.Shifts.MORNING);
                        break;
                    case "afternoon":
                        obj = new Security(name, id, year, workedShifts, Security.Shifts.AFTERNOON);
                        break;
                    case "night":
                        obj = new Security(name, id, year, workedShifts, Security.Shifts.NIGHT);
                        break;
                }
                break;
            }
        }

        Main.members.add(obj);
        Stage addStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        addStage.close();
    }

    private String selectedRole;
    private String selectedshift;
    @FXML
    void selectedBox(ActionEvent event) {
        if(mayorBox.isSelected()) {
            selectedRole="mayor";
            label1.setVisible(true);
            label2.setVisible(false);
            label3.setVisible(false);
            inputBaseSalary.setVisible(true);
            inputWorkedShifts.setVisible(false);
            morningBox.setVisible(false);
            afternoonBox.setVisible(false);
            nightBox.setVisible(false);
        }
        else if(deputyBox.isSelected()) {
            selectedRole="deputy";
            label1.setVisible(true);
            label2.setVisible(false);
            label3.setVisible(false);
            inputBaseSalary.setVisible(true);
            inputWorkedShifts.setVisible(false);
            morningBox.setVisible(false);
            afternoonBox.setVisible(false);
            nightBox.setVisible(false);
        }
        else if(employeeBox.isSelected()) {
            label1.setVisible(false);
            label2.setVisible(true);
            label3.setVisible(false);
            selectedRole="employee";
            inputBaseSalary.setVisible(false);
            inputWorkedShifts.setVisible(true);
            morningBox.setVisible(false);
            afternoonBox.setVisible(false);
            nightBox.setVisible(false);
        }
        else if(inspectorBox.isSelected()) {
            label1.setVisible(false);
            label2.setVisible(true);
            label3.setVisible(false);
            selectedRole="inspector";
            inputBaseSalary.setVisible(false);
            inputWorkedShifts.setVisible(true);
            morningBox.setVisible(false);
            afternoonBox.setVisible(false);
            nightBox.setVisible(false);
        }
        else if(securityBox.isSelected()) {
            label1.setVisible(false);
            label2.setVisible(true);
            label3.setVisible(true);
            selectedRole="security";
            inputBaseSalary.setVisible(false);
            inputWorkedShifts.setVisible(true);
            morningBox.setVisible(true);
            afternoonBox.setVisible(true);
            nightBox.setVisible(true);
        }

        if(morningBox.isSelected())
            selectedshift="morning";
        else if(afternoonBox.isSelected())
            selectedshift="afternoon";
        else if(nightBox.isSelected())
            selectedshift="night";
    }
}
