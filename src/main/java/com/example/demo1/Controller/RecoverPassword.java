package com.example.demo1.Controller;
import Model.Airport.User;
import com.example.demo1.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RecoverPassword implements Initializable
{
    @FXML
    private Button applyButton1;
    @FXML
    private Button applyButton2;
    @FXML
    private Button backButton;
    @FXML
    private TextField inputEmail;
    @FXML
    private TextField inputPwd1;
    @FXML
    private TextField inputPwd2;
    @FXML
    private TextField inputUsername;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label checker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        inputPwd1.setVisible(false);
        inputPwd2.setVisible(false);
        applyButton1.setDisable(true);
        applyButton2.setDisable(true);
        applyButton2.setVisible(false);

        inputUsername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if(!(t1.equals("")))
                    inputEmail.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                            if(!(t1.equals("")))
                                applyButton1.setDisable(false);
                        }
                    });
            }
        });

        inputPwd1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if(!(t1.equals("")))
                    inputPwd2.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                            if(!(t1.equals("")))
                                applyButton2.setDisable(false);
                        }
                    });
            }
        });
    }

    private String username="";
    private int step=1;
    @FXML
    void pressedApply(ActionEvent event) {
        if(step==1) {
            //step1
            boolean userMatched=false;
            boolean emailMatched=false;

            if(Main.regexAlphaNum(inputUsername.getText())) {
                username = inputUsername.getText();
                if (!Main.checkUsername(username, -1)) {
                    userMatched = true;
                }
            }
            else {
                checker.setText("Only alphabets & numbers are valid for username!");
                return;
            }

            String email;
            if(Main.regexEmail(inputEmail.getText())) {
                email=inputEmail.getText();
                if(!Main.checkEmail(email, -1)) {
                    emailMatched = true;
                }
            }
            else {
                checker.setText("email format is invalid! (example@domain.com)");
                return;
            }

            if(userMatched && emailMatched) {
                label1.setVisible(true);
                label2.setVisible(true);
                label3.setVisible(true);
                label4.setVisible(true);
                inputPwd1.setVisible(true);
                inputPwd2.setVisible(true);
                applyButton1.setVisible(false);
                applyButton2.setVisible(true);
                step=2;
                return;
            }
            else {
                checker.setText("username & email weren't match!");
                return;
            }
        }
        else {
            //step 2
            String password;
            if(inputPwd1.getText().equals(inputPwd2.getText()))
                password = inputPwd1.getText();
            else {
                checker.setText("failed to confirm password!");
                return;
            }

            for(User obj : Main.users) {
                if(obj.getUsername().equals(username)) {
                    obj.setPassword(password);
                    break;
                }
            }

            username="";
            step=1;
            Stage addStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            addStage.close();
        }
    }

    @FXML
    void pressedBack(ActionEvent event) {
        Stage addStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        addStage.close();
    }
}