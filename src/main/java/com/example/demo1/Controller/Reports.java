package com.example.demo1.Controller;
import Model.Airport.Manager;
import Model.Airport.Report;
import com.example.demo1.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Reports implements Initializable
{
    @FXML
    private Button backButton;
    @FXML
    private ListView<String> idList;
    @FXML
    private ListView<String> usernameList;
    @FXML
    private ListView<String> numList;
    @FXML
    private ListView<String> reportList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateLists(numList, idList, usernameList, reportList);
    }

    @FXML
    void pressedBack(ActionEvent event) throws IOException {
        Main.sceneSwitch("AdminMenu.fxml", event, 520, 400);
    }

    private static void updateLists(ListView<String> numList, ListView<String> idList, ListView<String> usernameList, ListView<String> reportList) {
        try {
            numList.getItems().clear();
            idList.getItems().clear();
            usernameList.getItems().clear();
            reportList.getItems().clear();

            for (Report obj : Main.reports) {
                numList.getItems().add(Integer.toString(Main.reports.indexOf(obj) + 1));
                idList.getItems().add(obj.getManager().getId()+"");
                usernameList.getItems().add(obj.getManager().getUsername());
                reportList.getItems().add(obj.getDiscription());
            }
        } catch(Exception e) { Main.appendToFile(e); }
    }
}