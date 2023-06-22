package com.example.demo1.Controller.Municipality;
import Model.Departments.Department;
import Model.Departments.Type;
import Model.Persons.Municipality;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MayorPage implements Initializable
{
    @FXML
    private Button refreshButton;
    @FXML
    private Button editButton;
    @FXML
    private Button logoutButton;
    @FXML
    private TextField editTextfield;
    @FXML
    private ListView<String> indexList;
    @FXML
    private ListView<String> typeList;
    @FXML
    private ListView<String> nameList;
    @FXML
    private Button removeButton;
    @FXML
    private ListView<String> yearList;

    private String selectedList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        removeButton.setDisable(true);
        editButton.setDisable(true);
        editTextfield.setDisable(true);
        updateLists(indexList, nameList, typeList, yearList);

        indexList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                removeButton.setDisable(false);
                editButton.setDisable(true);
                editTextfield.setDisable(true);
            }
        });
        nameList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                removeButton.setDisable(true);
                editButton.setDisable(false);
                editTextfield.setDisable(false);
                selectedList = "name";
            }
        });
        typeList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                removeButton.setDisable(true);
                editButton.setDisable(false);
                editTextfield.setDisable(false);
                selectedList = "type";
            }
        });
        yearList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                removeButton.setDisable(true);
                editButton.setDisable(false);
                editTextfield.setDisable(false);
                selectedList = "year";
            }
        });
    }

    public static void updateLists(ListView<String> indexList, ListView<String> nameList, ListView<String> typeList, ListView<String> yearList) {
        try {
            indexList.getItems().clear();
            nameList.getItems().clear();
            typeList.getItems().clear();
            yearList.getItems().clear();

            for (Department obj : Main.departments) {
                indexList.getItems().add(Integer.toString(Main.departments.indexOf(obj) + 1));
                nameList.getItems().add(obj.getName());
                typeList.getItems().add(obj.getType().toString());
                yearList.getItems().add(Integer.toString(obj.getEstablishmentYear()));
            }
        } catch(Exception e) { Main.appendToFile(e); }
    }

    @FXML
    void pressedLogout(ActionEvent event) throws IOException {
        Main.sceneSwitch("Municipality/LoginPage.fxml", event, 520, 400);
    }

    @FXML
    void pressedAdd(ActionEvent event) throws IOException {
        //sceneSwitch("MayorAddPage.fxml", event, 300, 400);
        Stage addStage = new Stage();
        addStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo1/View/Municipality/MayorAddPage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 300, 400);
        addStage.getIcons().add(new Image("icon.png"));
        addStage.setTitle("Munix");
        addStage.setResizable(false);
        addStage.setScene(scene);
        addStage.show();
    }

    @FXML
    void pressedRemove(ActionEvent event) {
        int index = indexList.getSelectionModel().getSelectedIndex();
        Main.departments.remove(index);
        updateLists(indexList, nameList, typeList, yearList);
        removeButton.setDisable(true);
    }

    @FXML
    void pressedRefresh(ActionEvent event) {
        updateLists(indexList, nameList, typeList, yearList);
    }

    @FXML
    void pressedEdit(ActionEvent event) {
        int index;
        String input = editTextfield.getText();

        switch(selectedList) {
            case "name": {
                index = nameList.getSelectionModel().getSelectedIndex();
                Main.departments.get(index).setName(input);
                break;
            }
            case "year": {
                index = yearList.getSelectionModel().getSelectedIndex();
                try { Main.departments.get(index).setEstablishmentYear(Integer.parseInt(input)); }
                catch(Exception e) {
                    System.out.println("ERROR: Couldn't cast String to Integer.");
                    Main.appendToFile(e);
                }
                break;
            }
            case "type": {
                index = typeList.getSelectionModel().getSelectedIndex();
                try {
                    Enum r = Enum.valueOf(Type.class, input);
                    r = Type.valueOf(input);
                    Main.departments.get(index).setType((Type) r);
                } catch(Exception e) {
                    System.out.println("ERROR: Couldn't cast String to Type.");
                    Main.appendToFile(e);
                }
                break;
            }
        }

        updateLists(indexList, nameList, typeList, yearList);
        editButton.setDisable(true);
        editTextfield.setDisable(true);
    }
}
