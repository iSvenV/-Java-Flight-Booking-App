package Model.Persons;
public class Employee extends Municipality
{
    //Unique Attribute
    private int shift;

    //Constructor
    public Employee(String fullname, int personalNum, int recruitmentYear, int shift) {
        super(fullname, personalNum, recruitmentYear, 3);
        this.shift = shift;
        setRole(Roles.EMPLOYEE);
    }

    //Getter-Setter
    public void setShift(int shift) {
        this.shift = shift;
    }
    public int getShift() {
        return shift;
    }

    //Paycheck Calculations
    @Override
    public double payCheck() {
        employeeBaseSalary();
        return experienceBonus();
    }
    public void employeeBaseSalary() {
        setBaseSalary(getShift()*5);
    }
}