package Model.Airport;
public class Manager extends User
{
    //Unique Attributes
    private String  address;

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