package com.example.demo1.Controller;
import Model.Airport.Passenger;
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
import java.util.regex.Pattern;

public class AirportSignup implements Initializable
{
    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonCreate;
    @FXML
    private Label checker;
    @FXML
    private TextField inputEmail;
    @FXML
    private TextField inputFirstname;
    @FXML
    private TextField inputID;
    @FXML
    private TextField inputLastname;
    @FXML
    private TextField inputPassword1;
    @FXML
    private TextField inputPassword2;
    @FXML
    private TextField inputPhone;
    @FXML
    private TextField inputUsername;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonCreate.setDisable(true);

        inputID.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if(!(t1.equals("")))
                    inputFirstname.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                            if(!(t1.equals("")))
                                inputLastname.textProperty().addListener(new ChangeListener<String>() {
                                    @Override
                                    public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                                        if(!(t1.equals("")))
                                            inputUsername.textProperty().addListener(new ChangeListener<String>() {
                                                @Override
                                                public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                                                    if(!(t1.equals("")))
                                                        inputPassword1.textProperty().addListener(new ChangeListener<String>() {
                                                            @Override
                                                            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                                                                if(!(t1.equals("")))
                                                                    inputPassword2.textProperty().addListener(new ChangeListener<String>() {
                                                                        @Override
                                                                        public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                                                                            if(!(t1.equals("")))
                                                                                inputPhone.textProperty().addListener(new ChangeListener<String>() {
                                                                                    @Override
                                                                                    public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                                                                                        if(!(t1.equals("")))
                                                                                            inputEmail.textProperty().addListener(new ChangeListener<String>() {
                                                                                                @Override
                                                                                                public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                                                                                                    if (!(t1.equals("")))
                                                                                                        buttonCreate.setDisable(false);
                                                                                                }
                                                                                            });
                                                                                    }
                                                                                });
                                                                        }
                                                                    });
                                                            }
                                                        });
                                                }
                                            });
                                    }
                                });
                        }
                    });
            }
        });
    }

    @FXML
    void pressedBack(ActionEvent event) {
        Stage addStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        addStage.close();
    }

    @FXML
    void pressedCreate(ActionEvent event) {
        int id = 0;
        try {
            id = Integer.parseInt(inputID.getText());
            if(!Main.checkID(id, -1)) {
                checker.setText("a user with this ID already exists!");
                return;
            }
        }
        catch(Exception e) {
            checker.setText("ID input is invalid!");
            System.out.println("Couldn't Cast String to Integer!");
            Main.appendToFile(e);
            return;
        }

        int phone = 0;
        try {
            phone = Integer.parseInt(inputPhone.getText());
            if(!Main.checkPhone(phone+"", -1)) {
                checker.setText("a user with this phone number already exists!");
                return;
            }
        }
        catch(Exception e) {
            checker.setText("Phone Number is invalid!");
            System.out.println("Couldn't Cast String to Integer!");
            Main.appendToFile(e);
            return;
        }

        String firstname, lastname;
        if(Main.regexAlpha(inputFirstname.getText()) && Main.regexAlpha(inputLastname.getText())) {
            firstname=inputFirstname.getText();
            lastname=inputLastname.getText();
        }
        else {
            checker.setText("Only alphabets are valid for Firstname & Lastname!");
            return;
        }

        String username = null;
        if(Main.regexAlphaNum(inputUsername.getText())) {
            username = inputUsername.getText();
            if (!Main.checkUsername(username, -1)) {
                checker.setText("a user with this username already exists!");
                return;
            }
        }
        else {
            checker.setText("Only alphabets & numbers are valid for username!");
            return;
        }

        String email = null;
        if(Main.regexEmail(inputEmail.getText())) {
            email=inputEmail.getText();
            if(!Main.checkEmail(email, -1)) {
                checker.setText("a user with this email alraedy exists!");
                return;
            }
        }
        else {
            checker.setText("email format is invalid! (example@domain.com)");
            return;
        }

        String password;
        if(inputPassword1.getText().equals(inputPassword2.getText()))
            password=inputPassword1.getText();
        else {
            checker.setText("failed to confirm password!");
            return;
        }

        Passenger obj = new Passenger(id, firstname+" "+lastname, username, password, phone+"", email);
        Main.passengers.add(obj);
        Main.users.add(obj);

        Stage addStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        addStage.close();
    }
}