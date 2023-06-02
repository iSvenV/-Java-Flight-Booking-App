package Model.Airport;
public class Employee extends Manager
{
    //Constructor
    public Employee(int id, String fullname, String username, String password, String phone, String email, double salary, String address) {
        super(id, fullname, username, password, phone, email, salary, address);
    }
}