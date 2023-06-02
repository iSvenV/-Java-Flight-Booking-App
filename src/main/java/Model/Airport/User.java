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
}