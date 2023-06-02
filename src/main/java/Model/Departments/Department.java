package Model.Departments;
import Model.Persons.Municipality;
import java.util.ArrayList;

public class Department
{
    //Attributes
    private String name;
    private int establishmentYear;
    private Type type;
    private ArrayList<Municipality> users = new ArrayList<>();

    //Constructor
    public Department(String name, int establishmentYear, Type type) {
        this.name = name;
        this.establishmentYear = establishmentYear;
        this.type = type;
    }

    //Getters
    public void setEstablishmentYear( int establishmentYear) {
        this.establishmentYear = establishmentYear;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public void setUsers(ArrayList<Municipality> users) {
        this.users = users;
    }
    //Setters
    public int getEstablishmentYear() {
        return establishmentYear;
    }
    public String getName() {
        return name;
    }
    public Type getType() {
        return type;
    }
    public ArrayList<Municipality> getUsers() {
        return users;
    }
}