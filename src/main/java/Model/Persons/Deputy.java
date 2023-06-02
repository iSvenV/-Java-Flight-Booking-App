package Model.Persons;
public class Deputy extends Mayor
{
    //Constructor
    public Deputy(String fullname, int personalNum, int recruitmentYear, double baseSalary) {
        super(fullname, personalNum, recruitmentYear, baseSalary);
        setRole(Roles.DEPUTY);
    }
}