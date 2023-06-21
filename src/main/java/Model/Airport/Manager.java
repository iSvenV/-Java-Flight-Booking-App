package Model.Airport;

import java.util.ArrayList;

public class Manager extends User
{
    //Unique Attributes
    private String  address;
    private ArrayList<Feedback> feedbacks = new ArrayList<>();

    //Constructor
    public Manager(int id, String fullname, String username, String password, String phone, String email, String address) {
        super(id, fullname, username, password, phone, email);
        this.address = address;
        this.setRole(Role.Manager);
    }

    //Getters

    public String getAddress() {
        return address;
    }

    //Setters
    public void setAddress(String address) {
        this.address = address;
    }
}