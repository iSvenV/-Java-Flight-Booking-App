package Model.Departments;
public class Library extends Department
{
    //Constructor
    public Library(String name, int establishmentYear) {
        super(name, establishmentYear, Type.LIBRARY);
    }
}