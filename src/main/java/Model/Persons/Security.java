package Model.Persons;
public class Security extends Municipality
{
    //Unique Attribute
    private int workedShift;
    private int dutyShift;
    private Shifts shift;

    //Constructor
    public Security(String fullname, int personalNum, int recruitmentYear, int workedShift, Shifts shift) {
        super(fullname, personalNum, recruitmentYear, 2);
        setRole(Roles.SECURITY);
        this.workedShift = workedShift;
        this. dutyShift = 20;
        this.shift = shift;
    }

    //Shifts enum
    public enum Shifts {
        MORNING,
        AFTERNOON,
        NIGHT
    }

    //Setters
    public void setShift(Shifts shift) {
        this.shift = shift;
    }
    public void setWorkedShiftShift(int workedShift) {
        this.workedShift = workedShift;
    }
    public void setDutyShift(int dutyShift) {
        this.dutyShift = dutyShift;
    }
    //Getters
    public Shifts getShift() {
        return shift;
    }
    public int getWorkedShift() {
        return workedShift;
    }
    public int getDutyShift() {
        return dutyShift;
    }

    //Paycheck Calculations
    @Override
    public double payCheck() {
        securityBaseSalary();
        double experience = experienceBonus();
        double extra = extraShiftBonus();
        return experience+extra;
    }

    public void securityBaseSalary() {
        Security.Shifts shift = getShift();
        int workedShift = getWorkedShift();
        int dutyShift = getDutyShift();

        int totalShift;
        if(shift == Security.Shifts.NIGHT)
            totalShift = workedShift*2;
        else
            totalShift = workedShift;

        if(totalShift <= dutyShift)
            setBaseSalary(totalShift*20);
        else
            setBaseSalary(dutyShift*20);
    }
    public double extraShiftBonus() {
        Security.Shifts shift = getShift();
        int workedShift = getWorkedShift();
        int dutyShift = getDutyShift();

        int totalShift;
        if(shift == Security.Shifts.NIGHT)
            totalShift = workedShift*2;
        else
            totalShift = workedShift;

        if(totalShift > dutyShift)
            return experienceBonus() * (totalShift-dutyShift)*0.01;
        else
            return 0;
    }
}