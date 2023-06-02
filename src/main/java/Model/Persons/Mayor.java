package Model.Persons;
public class Mayor extends Municipality
{
    //Unique Attributes (Only Used for MAYOR Object)
    private String username;
    private String password;

    //Constructor for MAYOR Object
    public Mayor(String fullname, int personalNum, int recruitmentYear, String username, String password) {
        super(fullname, personalNum, recruitmentYear, 5);
        this.username = username;
        this.password = password;
        setRole(Roles.MAYOR);
        setBaseSalary(1000);
    }

    //Constructor for DEPUTY Objects
    public Mayor(String fullname, int personalNum, int recruitmentYear, double baseSalary) {
        super(fullname, personalNum, recruitmentYear, 5);
        this.setBaseSalary(baseSalary);
    }

    //Setters
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    //Getters
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    //Paycheck Calculations
    @Override
    public double payCheck() {
        return experienceBonus();
    }
}