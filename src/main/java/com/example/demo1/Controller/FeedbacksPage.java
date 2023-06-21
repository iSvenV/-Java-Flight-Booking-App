package com.example.demo1.Controller;
import Model.Airport.Feedback;
import Model.Departments.Department;
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
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FeedbacksPage implements Initializable
{
    @FXML
    private ListView<String> authorList;
    @FXML
    private Button backButton;
    @FXML
    private Button deleteButton;
    @FXML
    private ListView<String> messageList;
    @FXML
    private ListView<String> numList;
    @FXML
    private ListView<String> roleList;
    @FXML
    private Button viewButton;

    public static int selectedFeedback;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewButton.setDisable(true);
        deleteButton.setDisable(true);
        updateLists(numList, authorList, roleList, messageList);

        numList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                deleteButton.setDisable(false);
                viewButton.setDisable(false);
                selectedFeedback = numList.getSelectionModel().getSelectedIndex();
            }
        });
    }

    @FXML
    void pressedBack(ActionEvent event) throws IOException {
        if(AirportLogin.userRole.equals("admin"))
            Main.sceneSwitch("AdminMenu.fxml", event, 520, 400);
        else
            Main.sceneSwitch("ManagerPage.fxml", event, 520, 400);
    }

    @FXML
    void pressedView(ActionEvent event) throws IOException {
        Stage addStage = new Stage();
        addStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo1/View/FeedbackView.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 433, 287);
        addStage.getIcons().add(new Image("icon.png"));
        addStage.setTitle("Munix");
        addStage.setResizable(false);
        addStage.setScene(scene);
        addStage.show();
    }

    @FXML
    void pressedDelete(ActionEvent event) {
        int index = numList.getSelectionModel().getSelectedIndex();
        Main.feedbacks.remove(index);
        updateLists(numList, authorList, roleList, messageList);
        deleteButton.setDisable(true);
        viewButton.setDisable(true);
    }

    public static void updateLists(ListView<String> numList, ListView<String> authorList, ListView<String> roleList, ListView<String> messageList) {
        try {
            numList.getItems().clear();
            authorList.getItems().clear();
            roleList.getItems().clear();
            messageList.getItems().clear();

            for (Feedback obj : Main.feedbacks) {
                numList.getItems().add(Integer.toString(Main.feedbacks.indexOf(obj) + 1));
                authorList.getItems().add(obj.getAuthor());
                roleList.getItems().add(obj.getAuthorRole().toString());
                messageList.getItems().add(obj.getText());
            }
        } catch(Exception e) { Main.appendToFile(e); }
    }
}