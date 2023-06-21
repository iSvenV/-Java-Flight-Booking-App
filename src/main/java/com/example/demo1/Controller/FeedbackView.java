package com.example.demo1.Controller;
import com.example.demo1.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class FeedbackView implements Initializable
{
    @FXML
    private Button okButton;
    @FXML
    private TextArea textArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textArea.setText(Main.feedbacks.get(FeedbacksPage.selectedFeedback).getText());
    }

    @FXML
    void pressedOk(ActionEvent event) {
        Stage addStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        addStage.close();
    }
}