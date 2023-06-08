package com.example.demo1.Controller;
import com.example.demo1.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PassengerPage implements Initializable
{
    @FXML
    private Label balanceLabel;
    @FXML
    private Button buyButton;
    @FXML
    private Button chargeButton;
    @FXML
    private Label greetLabel;
    @FXML
    private Button logoutButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button ticketsButton;
    @FXML
    private Button mailButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int index = AirportLogin.userIndex;

        greetLabel.setText(Main.passengers.get(index).getFullname()+"!");
        balanceLabel.setText(Main.passengers.get(index).getWallet()+"$");
    }

    @FXML
    void pressedBuy(ActionEvent event) {

    }

    @FXML
    void pressedCharge(ActionEvent event) {

    }

    @FXML
    void pressedProfile(ActionEvent event) throws IOException {
        sceneSwitch("PassengerEdit.fxml", event, 520, 400);
    }

    @FXML
    void pressedTickets(ActionEvent event) {

    }

    @FXML
    void pressedMail(ActionEvent event) {

    }

    @FXML
    void pressedLogout(ActionEvent event) throws IOException {
        sceneSwitch("AirportLogin.fxml", event, 520, 400);
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