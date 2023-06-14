package Model.Airport;

import java.util.ArrayList;

public class Manager extends User
{
    //Unique Attributes
    private double salary;
    private String  address;
    private ArrayList<Feedback> feedbacks = new ArrayList<>();

    //Constructor
    public Manager(int id, String fullname, String username, String password, String phone, String email, double salary, String address) {
        super(id, fullname, username, password, phone, email);
        this.salary = salary;
        this.address = address;
    }

    //Getters
    public double getSalary() {
        return salary;
    }
    public String getAddress() {
        return address;
    }

    //Setters
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}