package Model.Departments;
public class Institute extends Department
{
    //Constructor
    public Institute(String name, int establishmentYear) {
        super(name, establishmentYear, Type.INSTITUTE);
    }
}