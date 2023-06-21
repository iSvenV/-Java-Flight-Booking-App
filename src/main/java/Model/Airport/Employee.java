package Model.Airport;
public class Employee extends User
{
    //Unique Attributes
    private String  address;

    //Constructor
    public Employee(int id, String fullname, String username, String password, String phone, String email, String address) {
        super(id, fullname, username, password, phone, email);
        this.address = address;
        this.setRole(Role.Employee);
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