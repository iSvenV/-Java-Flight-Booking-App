package Model.Persons;
public class Inspector extends Employee
{
    //Constructor
    public Inspector(String fullname, int personalNum, int recruitmentYear, int shift) {
        super(fullname, personalNum, recruitmentYear, 3);
        setShift(shift);
        setRole(Roles.INSPECTOR);
    }
}