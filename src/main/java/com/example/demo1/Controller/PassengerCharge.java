package com.example.demo1.Controller;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PassengerCharge implements Initializable
{
    @FXML
    private Button backButton;
    @FXML
    private Button chargeButton;
    @FXML
    private TextField inputValue;
    @FXML
    private Label checker;
    @FXML
    private Label chargedLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chargeButton.setDisable(true);
        chargedLabel.setVisible(false);

        inputValue.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if(!(t1.equals("")))
                    chargeButton.setDisable(false);
            }
        });
    }

    @FXML
    void pressedBack(ActionEvent event) throws IOException {
        sceneSwitch("PassengerPage.fxml", event, 520, 400);
    }

    @FXML
    void pressedCharge(ActionEvent event) {
        double value;
        try {
            value = Double.parseDouble(inputValue.getText());
            if(value==0) {
                chargedLabel.setVisible(false);
                checker.setText("zero value doesn't affect your wallet!");
                return;
            }
            else if(value<0) {
                chargedLabel.setVisible(false);
                checker.setText("negative input is invalid!");
                return;
            }
        }
        catch(Exception e) {
            System.out.println("Couldn't Cast String to Double!");
            Main.appendToFile(e);
            chargedLabel.setVisible(false);
            checker.setText("input is invalid!");
            return;
        }

        checker.setText("");
        chargedLabel.setVisible(true);
        Main.passengers.get(AirportLogin.userIndex).incrementWallet(value);
    }

    public void sceneSwitch(String url, ActionEvent event, int v, int v1) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo1/View/"+url));
        Parent root = fxmlLoader.load();
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, v, v1);
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.setTitle("Munix");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}