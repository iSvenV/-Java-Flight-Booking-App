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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AirportLogin implements Initializable
{
    @FXML
    private Button buttonEnter;
    @FXML
    private Button buttonCreate;
    @FXML
    private Button departmentButton;
    @FXML
    private PasswordField inputPassword;
    @FXML
    private TextField inputUsername;
    @FXML
    private Label loginChecker;
    @FXML
    private TextField passwordVisible;
    @FXML
    private CheckBox pwdCheckbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.pressedCheckbox(null);
        buttonEnter.setDisable(true);

        inputUsername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if(!(t1.equals("")))
                    inputPassword.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                            if(!(t1.equals("")))
                                buttonEnter.setDisable(false);
                        }
                    });
            }
        });
    }

    @FXML
    void pressedEnter(ActionEvent event) throws IOException {
        String userInput = inputUsername.getText();
        String pwdInput;
        if(!pwdCheckbox.isSelected())
            pwdInput = inputPassword.getText();
        else
            pwdInput = passwordVisible.getText();

        if(userInput.equals(Main.admin.getUsername()) && pwdInput.equals(Main.admin.getPassword()))
            sceneSwitch("AdminPage.fxml", event, 520, 400);
        else if(userInput.equals(Main.mayor.getUsername()) && pwdInput.equals(Main.mayor.getPassword()))
            sceneSwitch("MayorPage.fxml", event, 520, 400);
        else
            loginChecker.setText("username or password is wrong!");
    }

    @FXML
    void pressedCreate(ActionEvent event) throws IOException {
        //sceneSwitch("AirportSignup.fxml", event, 374, 455);
        Stage addStage = new Stage();
        addStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo1/View/AirportSignup.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 374, 455);
        addStage.getIcons().add(new Image("icon.png"));
        addStage.setTitle("Munix");
        addStage.setResizable(false);
        addStage.setScene(scene);
        addStage.show();
    }

    @FXML
    void pressedChange(ActionEvent event) throws IOException {
        sceneSwitch("DepartmentSelection.fxml", event, 520, 400);
    }

    @FXML
    void pressedCheckbox(ActionEvent event) {
        if (pwdCheckbox.isSelected()) {
            passwordVisible.setText(inputPassword.getText());
            passwordVisible.setVisible(true);
            inputPassword.setVisible(false);
            return;
        }
        inputPassword.setText(passwordVisible.getText());
        inputPassword.setVisible(true);
        passwordVisible.setVisible(false);
    }

    public void sceneSwitch(String url, ActionEvent event, int x, int y) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo1/View/"+url));
        Parent root = fxmlLoader.load();
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, x, y);
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.setTitle("Munix");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}