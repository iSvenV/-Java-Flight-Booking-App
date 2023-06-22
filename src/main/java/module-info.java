module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.demo1;
    opens com.example.demo1.Controller;
    opens com.example.demo1.View;
    opens com.example.demo1.Controller.Municipality;
}