package com.example.demo1;
import Model.Airport.*;
import Model.Departments.*;
import Model.Persons.*;
import Model.Persons.Employee;
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

    public static ArrayList<Flight> allFlights = new ArrayList<>();
    public static ArrayList<Airplane> airplanes = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Passenger> passengers = new ArrayList<>();
    public static ArrayList<Model.Airport.Employee> employees = new ArrayList<>();
    public static ArrayList<Manager> managers = new ArrayList<>();
    public static ArrayList<Feedback> feedbacks = new ArrayList<>();
    public static Manager superAdmin = new Manager(0, "ADMIN ADMIN", "admin", "admin", "0918914", "admin@gmail.com", "Ahvaz");

    private static void initiate() {
        //PHASE 1
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

        //PHASE 2
        users.add(superAdmin);

        Manager defaultmanager = new Manager(1, "DEFAULT MANAGER", "manager", "manager", "09181212", "manager@gmail.com", "Ahvaz");
        users.add(defaultmanager);
        managers.add(defaultmanager);

        Model.Airport.Employee employee1 = new Model.Airport.Employee(101, "first employee", "emp", "emp", "09182222", "emp1@gmail.com", "Ahvaz");
        users.add(employee1);
        employees.add(employee1);

        Passenger passenger1 = new Passenger(201, "first passenger", "pas", "pas", "09189999", "pas1@gmail.com");
        users.add(passenger1);
        passengers.add(passenger1);
    }

    //Error Logging
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

    //Scene Switcher
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

    //Methods to check if Signup/Editing inputs already exsits for other users
    public static boolean checkID(int id, int index) {
        for(User obj : users)  {
            int currentIndex = users.indexOf(obj);
            if(!(index ==-1))
                if(currentIndex == index)
                    continue;

            if(id == obj.getId())
                return false;
        }
        return true;
    }

    public static boolean checkUsername(String username, int index) {
        for(User obj : users)  {
            int currentIndex = users.indexOf(obj);
            if(!(index ==-1))
                if(currentIndex == index)
                    continue;

            if(username.equals(obj.getUsername()))
                return false;
        }
        return true;
    }

    public static boolean checkPhone(String phone, int index) {
        for(User obj : users)  {
            int currentIndex = users.indexOf(obj);
            if(!(index ==-1))
                if(currentIndex == index)
                    continue;

            if(phone.equals(obj.getPhone()))
                return false;
        }
        return true;
    }

    public static boolean checkEmail(String email, int index) {
        for(User obj : users)  {
            int currentIndex = users.indexOf(obj);
            if(!(index ==-1))
                if(currentIndex == index)
                    continue;

            if(email.equals(obj.getEmail()))
                return false;
        }
        return true;
    }

    //Regex Methods for controlling inputs
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

    //ID-Checkers for Airplanes & Flights
    public static boolean airplaneCheckID(int id, int index) {
        for(Airplane obj : airplanes)  {
            int currentIndex = airplanes.indexOf(obj);
            if(!(index ==-1))
                if(currentIndex == index)
                    continue;

            if(id == obj.getId())
                return false;
        }
        return true;
    }

    public static boolean flightCheckID(int id, int index) {
        for(Flight obj : allFlights)  {
            int currentIndex = allFlights.indexOf(obj);
            if(!(index ==-1))
                if(currentIndex == index)
                    continue;

            if(id == obj.getId())
                return false;
        }
        return true;
    }
}