package Model.Airport;
public class User
{
    //Attributes
    private int id;
    private String fullname;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Role role;
    private double salary;

    //Enum
    public enum Role {
        Manager, Employee, Passenger
    }

    //Constructor
    public User(int id, String fullname, String username, String password, String phone, String email) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    //Getters
    public int getId() {
        return id;
    }
    public String getFullname() {
        return fullname;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public Role getRole() {
        return role;
    }
    public double getSalary() {
        return salary;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

}