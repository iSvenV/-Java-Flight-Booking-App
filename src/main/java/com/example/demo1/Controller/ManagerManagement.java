package com.example.demo1.Controller;
import Model.Airport.Manager;
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

public class ManagerManagement implements Initializable
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
    private ListView<String> usernameList;

    public static int selectedManager;

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
                selectedManager = numList.getSelectionModel().getSelectedIndex();
            }
        });
    }

    @FXML
    void pressedAdd(ActionEvent event) throws IOException {
        Main.sceneSwitch("ManagerAdd.fxml", event, 520, 400);
    }

    @FXML
    void pressedInfo(ActionEvent event) throws IOException {
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
    void pressedDelete(ActionEvent event) {
        int index = numList.getSelectionModel().getSelectedIndex();
        Main.managers.remove(index);
        updateLists(numList, idList, nameList, usernameList, emailList);
        deleteButton.setDisable(true);
        editButton.setDisable(true);
        infoButton.setDisable(true);
    }

    @FXML
    void pressedEdit(ActionEvent event) throws IOException {
        Main.sceneSwitch("ManagerEdit.fxml", event, 520, 400);
    }

    @FXML
    void pressedBack(ActionEvent event) throws IOException {
        Main.sceneSwitch("AdminMenu.fxml", event, 520, 400);
    }

    public static void updateLists(ListView<String> numList, ListView<String> idList, ListView<String> nameList, ListView<String> usernameList, ListView<String> emailList) {
        try {
            numList.getItems().clear();
            idList.getItems().clear();
            nameList.getItems().clear();
            usernameList.getItems().clear();
            emailList.getItems().clear();

            for (Manager obj : Main.managers) {
                numList.getItems().add(Integer.toString(Main.managers.indexOf(obj) + 1));
                idList.getItems().add(obj.getId()+"");
                nameList.getItems().add(obj.getFullname());
                usernameList.getItems().add(obj.getUsername());
                emailList.getItems().add(obj.getEmail());
            }
        } catch(Exception e) { Main.appendToFile(e); }
    }
}