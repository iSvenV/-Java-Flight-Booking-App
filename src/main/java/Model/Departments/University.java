package Model.Departments;
public class University extends Department
{
    //Constructor
    public University(String name, int establishmentYear) {
        super(name, establishmentYear, Type.UNIVERSITY);
    }
}