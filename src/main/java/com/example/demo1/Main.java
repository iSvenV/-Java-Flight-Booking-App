package com.example.demo1;
import Model.Airport.Manager;
import Model.Airport.Passenger;
import Model.Airport.User;
import Model.Departments.*;
import Model.Persons.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main extends Application
{
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        if(!firstTimeInitial) {
            initiate();
            firstTimeInitial=true;
        }
        //else readFile();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo1/View/DepartmentSelection.fxml"));
        Parent root = null;
        try { root = fxmlLoader.load(); }
        catch(Exception e) { Main.appendToFile(e); }
        Scene scene = new Scene(root, 520, 400);

        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.setTitle("Munix");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static boolean firstTimeInitial;
    public static Mayor mayor = new Mayor("name1", 1, 2000, "mayor", "mayor");
    public static Admin admin = new Admin();

    public static ArrayList<Municipality> members = new ArrayList<>();
//    public static ArrayList<Mayor> mayors = new ArrayList<>();
//    public static ArrayList<Deputy> deputies = new ArrayList<>();
//    public static ArrayList<Employee> employees = new ArrayList<>();
//    public static ArrayList<Inspector> inspectors = new ArrayList<>();
//    public static ArrayList<Security> securities = new ArrayList<>();

    public static ArrayList<Department> departments = new ArrayList<>();
//    public static ArrayList<Airport> airports = new ArrayList<>();
//    public static ArrayList<Hospital> hospitals = new ArrayList<>();
//    public static ArrayList<Institute> institutes = new ArrayList<>();
//    public static ArrayList<Library> libraries = new ArrayList<>();
//    public static ArrayList<University> universities = new ArrayList<>();

    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Passenger> passengers = new ArrayList<>();
    public static ArrayList<Model.Airport.Employee> employees = new ArrayList<>();
    public static ArrayList<Manager> managers = new ArrayList<>();
    public static Manager superAdmin = new Manager(0, "ADMIN ADMIN", "admin", "admin", "091891929394", "admin@gmail.com",0, "Ahvaz");
    public static Manager defaultmanager = new Manager(1, "DEFAULT MANAGER", "manager", "manager", "09181212121", "manager@gmail.com", 100, "Ahvaz");

    private static void initiate() {
        Deputy deputy = new Deputy("name2", 2, 2022, 500);
        Employee employee = new Employee("name3", 3, 2010, 30);
        Inspector inspector = new Inspector("name4", 4, 2015, 25);
        Security security = new Security("name5", 5, 2020, 30, Security.Shifts.NIGHT);
        Airport airport = new Airport("airport1", 1990);
        Hospital hospital = new Hospital("hospital1", 2000);
        Institute institute = new Institute("institute1", 2001);
        Library library = new Library("library1", 2012);
        University university = new University("university1", 1999);

        members.add(mayor);
        members.add(deputy);
        members.add(employee);
        members.add(inspector);
        members.add(security);
//        mayors.add(mayor);
//        deputies.add(deputy);
//        employees.add(employee);
//        inspectors.add(inspector);
//        securities.add(security);
        departments.add(airport);
        departments.add(hospital);
        departments.add(institute);
        departments.add(library);
        departments.add(university);
//        airports.add(airport);
//        hospitals.add(hospital);
//        institutes.add(Institute);
//        libraries.add(library);
//        universities.add(university);

        users.add(superAdmin);
        users.add(defaultmanager);
        managers.add(defaultmanager);
    }

    public static void appendToFile(Exception e) {
        try {
            FileWriter New_File = new FileWriter("loggin.log", true);
            BufferedWriter Buff_File = new BufferedWriter(New_File);
            PrintWriter Print_File = new PrintWriter(Buff_File, true);
            e.printStackTrace(Print_File);
        }
        catch (Exception ie) {
            throw new RuntimeException("Cannot write the Exception to file", ie);
        }
    }

    public static void sceneSwitch(String url, ActionEvent event, int v, int v1) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/demo1/View/"+url));
        Parent root = fxmlLoader.load();
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, v, v1);
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.setTitle("Munix");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static boolean checkID(int id) {
        for(User obj : users)  {
            if(id == obj.getId())
                return false;
        }
        return true;
    }

    public static boolean checkUsername(String username) {
        for(User obj : users)  {
            if(username.equals(obj.getUsername()))
                return false;
        }
        return true;
    }

    public static boolean checkPhone(String phone) {
        for(User obj : users)  {
            if(phone.equals(obj.getPhone()))
                return false;
        }
        return true;
    }

    public static boolean checkEmail(String email) {
        for(User obj : users)  {
            if(email.equals(obj.getEmail()))
                return false;
        }
        return true;
    }

    public static boolean regexAlpha(String str) {
        Pattern p = Pattern.compile("^[a-zA-Z]*$");
        return p.matcher(str).find();
    }

    public static boolean regexAlphaNum(String str) {
        Pattern p = Pattern.compile("^[A-Za-z0-9]*$");
        return p.matcher(str).find();
    }

    public static boolean regexEmail(String str) {
        Pattern p = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        return p.matcher(str).find();
    }
}