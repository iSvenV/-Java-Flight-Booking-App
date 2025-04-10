package com.example.demo1.Controller;
import Model.Airport.Feedback;
import com.example.demo1.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FeedbackSubmission implements Initializable
{
    @FXML
    private Button backButton;
    @FXML
    private Label checker;
    @FXML
    private TextField input;
    @FXML
    private Button sendButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sendButton.setDisable(true);

        input.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if(!(t1.equals("")))
                    sendButton.setDisable(false);
            }
        });
    }

    public static String authorRole;
    @FXML
    void pressedBack(ActionEvent event) throws IOException {
        authorRole = AirportLogin.userRole;
        if(authorRole.equals("passsenger"))
            Main.sceneSwitch("PassengerPage.fxml", event, 520, 400);
        else
            Main.sceneSwitch("EmployeePage.fxml", event, 520, 400);
    }

    @FXML
    void pressedSend(ActionEvent event) {
        checker.setText("feedback submitted!");
        String text = input.getText();
        String author;

        Feedback feedback;
        if(authorRole.equals("passenger")) {
            author = Main.passengers.get(AirportLogin.userIndex).getFullname();
            feedback = new Feedback(text, author, Feedback.authorRole.Passenger);
        }
        else {
            author = Main.employees.get(AirportLogin.userIndex).getFullname();
            feedback = new Feedback(text, author, Feedback.authorRole.Employee);
        }

        Main.feedbacks.add(feedback);
    }
}