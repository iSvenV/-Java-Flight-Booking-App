package Model.Departments;
public class Hospital extends Department
{
    //Constructor
    public Hospital(String name, int establishmentYear) {
        super(name, establishmentYear, Type.HOSPITAL);
    }
}