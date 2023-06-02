package Model.Persons;
public class Municipality implements Paycheck
{
    //Attributes
    private String fullname;
    private int personalNum;
    private int recruitmentYear;
    private Roles role;

    private double baseSalary;
    private double experinecePercentage;

    //Constructor
    public Municipality(String fullname, int personalNum, int recruitmentYear, int experinecePercentage) {
        this.fullname = fullname;
        this.personalNum = personalNum;
        this.recruitmentYear = recruitmentYear;
        this.experinecePercentage = experinecePercentage;
    }

    //Roles enum
    public enum Roles {
        MAYOR,
        DEPUTY,
        INSPECTOR,
        EMPLOYEE,
        SECURITY
    }

    //Getters
    public String getFullname() {
        return fullname;
    }
    public int getpersonalNum() {
        return personalNum;
    }
    public int getrecruitmentYear() {
        return recruitmentYear;
    }
    public double getBaseSalary() {
        return baseSalary;
    }
    public double getExperinecePercentage() {
        return experinecePercentage;
    }
    public Roles getRole() {
        return role;
    }

    //Setters
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public void setpersonalNum(int personalNum) {
        this.personalNum = personalNum;
    }
    public void setrecruitmentYear(int recruitmentYear) {
        this.recruitmentYear = recruitmentYear;
    }
    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }
    public void setExperinecePercentage(double experinecePercentage) {
        this.experinecePercentage = experinecePercentage;
    }
    public void setRole(Roles role) {
        this.role = role;
    }

    //Paycheck Calculations
    @Override
    public double payCheck() { //Will be Overrided Based on Sub-Class
        return 0;
    }

    public double experienceBonus() {
        int years = 2023-getrecruitmentYear();
        double base=getBaseSalary();

        for(int i=1; i<=years; i++)
            base += getExperinecePercentage() * base;


        return base;
    }


}