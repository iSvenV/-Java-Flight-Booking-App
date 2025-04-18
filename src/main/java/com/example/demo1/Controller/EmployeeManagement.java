package com.example.demo1.Controller;
import Model.Airport.Employee;
import Model.Airport.Passenger;
import com.example.demo1.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeManagement implements Initializable
{
    @FXML
    private Button addButton;
    @FXML
    private Button backButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private ListView<String> emailList;
    @FXML
    private ListView<String> idList;
    @FXML
    private Button infoButton;
    @FXML
    private ListView<String> nameList;
    @FXML
    private ListView<String> numList;
    @FXML
    private Button refreshButton;
    @FXML
    private ListView<String> usernameList;

    public static int selectedEmployee;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editButton.setDisable(true);
        deleteButton.setDisable(true);
        infoButton.setDisable(true);
        updateLists(numList, idList, nameList, usernameList, emailList);

        numList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                deleteButton.setDisable(false);
                editButton.setDisable(false);
                infoButton.setDisable(false);
                selectedEmployee = numList.getSelectionModel().getSelectedIndex();
            }
        });
    }

    @FXML
    void pressedAdd(ActionEvent event) throws IOException {
        ManagerAdd.creatingUserType = "Employee:";

        Stage addStage = new Stage();
        addStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo1/View/ManagerAdd.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 374, 455);
        addStage.getIcons().add(new Image("icon.png"));
        addStage.setTitle("Munix");
        addStage.setResizable(false);
        addStage.setScene(scene);
        addStage.show();
    }

    @FXML
    void pressedInfo(ActionEvent event) throws IOException {
        ManagerInfo.userType = "employee";
        Stage addStage = new Stage();
        addStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo1/View/ManagerInfo.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 378, 287);
        addStage.getIcons().add(new Image("icon.png"));
        addStage.setTitle("Munix");
        addStage.setResizable(false);
        addStage.setScene(scene);
        addStage.show();
    }

    @FXML
    void pressedEdit(ActionEvent event) throws IOException {
        Main.sceneSwitch("EmployeeEdit.fxml", event, 520, 400);
    }

    @FXML
    void pressedDelete(ActionEvent event) {
        int index = numList.getSelectionModel().getSelectedIndex();
        Main.employees.remove(index);
        updateLists(numList, idList, nameList, usernameList, emailList);
        deleteButton.setDisable(true);
        editButton.setDisable(true);
        infoButton.setDisable(true);
    }

    @FXML
    void pressedBack(ActionEvent event) throws IOException {
        if(AirportLogin.userRole.equals("admin"))
            Main.sceneSwitch("AdminMenu.fxml", event, 520, 400);
        else
            Main.sceneSwitch("ManagerPage.fxml", event, 520, 400);
    }

    @FXML
    void pressedRefresh(ActionEvent event) {
        updateLists(numList, idList, nameList, usernameList, emailList);
    }

    private static void updateLists(ListView<String> numList, ListView<String> idList, ListView<String> nameList, ListView<String> usernameList, ListView<String> emailList) {
        try {
            numList.getItems().clear();
            idList.getItems().clear();
            nameList.getItems().clear();
            usernameList.getItems().clear();
            emailList.getItems().clear();

            for (Employee obj : Main.employees) {
                numList.getItems().add(Integer.toString(Main.employees.indexOf(obj) + 1));
                idList.getItems().add(obj.getId()+"");
                nameList.getItems().add(obj.getFullname());
                usernameList.getItems().add(obj.getUsername());
                emailList.getItems().add(obj.getEmail());
            }
        } catch(Exception e) { Main.appendToFile(e); }
    }
}