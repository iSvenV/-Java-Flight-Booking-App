package com.example.demo1.Controller;
import Model.Persons.Municipality;
import com.example.demo1.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminPage implements Initializable
{
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private ListView<String> idList;
    @FXML
    private ListView<String> nameList;
    @FXML
    private ListView<String> paycheckList;
    @FXML
    private Button removeButton;
    @FXML
    private ListView<String> roleList;
    @FXML
    private ListView<String> yearList;
    @FXML
    private ListView<String> indexList;
    @FXML
    private ListView<String> baseSalaryList;
    @FXML
    private TextField editTextfield;
    @FXML
    private Button logoutButton;
    @FXML
    private Button refreshButton;

    private String selectedList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        removeButton.setDisable(true);
        editButton.setDisable(true);
        editTextfield.setDisable(true);
        updateLists(indexList, idList, nameList, roleList, yearList, baseSalaryList, paycheckList);

        indexList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                removeButton.setDisable(false);
                editButton.setDisable(true);
                editTextfield.setDisable(true);
            }
        });
        idList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                removeButton.setDisable(true);
                editButton.setDisable(false);
                editTextfield.setDisable(false);
                selectedList = "id";
            }
        });
        nameList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                removeButton.setDisable(true);
                editButton.setDisable(false);
                editTextfield.setDisable(false);
                selectedList = "name";
            }
        });
        roleList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                removeButton.setDisable(true);
                editButton.setDisable(true);
                editTextfield.setDisable(true);
                selectedList = "role";
            }
        });
        yearList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                removeButton.setDisable(true);
                editButton.setDisable(false);
                editTextfield.setDisable(false);
                selectedList = "year";
            }
        });
        baseSalaryList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                removeButton.setDisable(true);
                editButton.setDisable(true);
                editTextfield.setDisable(true);
                selectedList = "baseSalary";
            }
        });
        paycheckList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                removeButton.setDisable(true);
                editButton.setDisable(true);
                editTextfield.setDisable(true);
            }
        });
    }

    private static void updateLists(ListView<String> indexList, ListView<String> idList, ListView<String> nameList, ListView<String> roleList, ListView<String> yearList, ListView<String> baseSalaryList, ListView<String> paycheckList) {
        try {
            indexList.getItems().clear();
            idList.getItems().clear();
            nameList.getItems().clear();
            roleList.getItems().clear();
            yearList.getItems().clear();
            paycheckList.getItems().clear();
            baseSalaryList.getItems().clear();

            for (Municipality obj : Main.members) {
                indexList.getItems().add(Integer.toString(Main.members.indexOf(obj) + 1));
                idList.getItems().add(Integer.toString(obj.getpersonalNum()));
                nameList.getItems().add(obj.getFullname());
                roleList.getItems().add(obj.getRole().toString());
                yearList.getItems().add(Integer.toString(obj.getrecruitmentYear()));

                double paycheck = obj.payCheck();
                paycheckList.getItems().add(paycheck + "$");
                baseSalaryList.getItems().add(obj.getBaseSalary() + "$");
            }
        } catch(Exception e)  { Main.appendToFile(e); }
    }

    @FXML
    void pressedLogout(ActionEvent event) throws IOException {
        Main.sceneSwitch("LoginPage.fxml", event, 520, 400);
    }

    @FXML
    void pressedRefresh(ActionEvent event) {
        updateLists(indexList, idList, nameList, roleList, yearList, baseSalaryList, paycheckList);
    }

    @FXML
    void pressedAdd(ActionEvent event) throws IOException {
        //sceneSwitch("AdminAddPage.fxml", event, 300, 400);
        Stage addStage = new Stage();
        addStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo1/View/AdminAddPage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 300, 515);
        addStage.getIcons().add(new Image("icon.png"));
        addStage.setTitle("Munix");
        addStage.setResizable(false);
        addStage.setScene(scene);
        addStage.show();
    }

    @FXML
    void pressedRemove(ActionEvent event) {
        int index = indexList.getSelectionModel().getSelectedIndex();
        Main.members.remove(index);
        updateLists(indexList, idList, nameList, roleList, yearList, baseSalaryList, paycheckList);
        removeButton.setDisable(true);
    }

    @FXML
    void pressedEdit(ActionEvent event) {
        int index;
        String input = editTextfield.getText();

        switch(selectedList) {
            case "name": {
                index = nameList.getSelectionModel().getSelectedIndex();
                Main.members.get(index).setFullname(input);
                break;
            }
            case "id": {
                index = idList.getSelectionModel().getSelectedIndex();
                try { Main.members.get(index).setpersonalNum(Integer.parseInt(input)); }
                catch(Exception e) {
                    System.out.println("ERROR: Couldn't cast String to Integer.");
                    Main.appendToFile(e);
                }
                break;
            }
            case "year": {
                index = yearList.getSelectionModel().getSelectedIndex();
                try { Main.members.get(index).setrecruitmentYear(Integer.parseInt(input)); }
                catch(Exception e) {
                    System.out.println("ERROR: Couldn't cast String to Integer.");
                    Main.appendToFile(e);
                }
                break;
            }
        }

        updateLists(indexList, idList, nameList, roleList, yearList, baseSalaryList, paycheckList);
        editButton.setDisable(true);
        editTextfield.setDisable(true);
    }
}