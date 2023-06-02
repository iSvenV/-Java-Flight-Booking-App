package Model.Persons;
public interface Paycheck
{
    public abstract double payCheck();
//    public default double payCheck(Mayor obj) {
//        double base = obj.getBaseSalary();
//        double experience = experienceBonus(obj);
//        return base + experience;
//    }
//
//    public default double payCheck(Employee obj) {
//        employeeBaseSalary(obj);
//        double base = obj.getBaseSalary();
//        double experience = experienceBonus(obj);
//        return base+experience;
//    }

//    public default double payCheck(Security obj) {
//        securityBaseSalary(obj);
//        double base = obj.getBaseSalary();
//        double extra = extraShiftBonus(obj);
//        double experience = experienceBonus(obj);
//        return base+experience+extra;
//    }

//    public default double experienceBonus() {
//        return getBaseSalary() * (2023-getrecruitmentYear())*getExperinecePercentage();
//    }

//    public default void employeeBaseSalary(Employee obj) {
//        obj.setBaseSalary(obj.getShift()*5);
//    }

//    public default void securityBaseSalary(Security obj) {
//        Security.Shifts shift = obj.getShift();
//        int workedShift = obj.getWorkedShift();
//        int dutyShift = obj.getDutyShift();
//
//        int totalShift;
//        if(shift == Security.Shifts.NIGHT)
//            totalShift = workedShift*2;
//        else
//            totalShift = workedShift;
//
//        if(totalShift <= dutyShift)
//            obj.setBaseSalary(totalShift*20);
//        else
//            obj.setBaseSalary(dutyShift * 20);
//    }
//
//    public default double extraShiftBonus(Security obj) {
//        Security.Shifts shift = obj.getShift();
//        int workedShift = obj.getWorkedShift();
//        int dutyShift = obj.getDutyShift();
//
//        int totalShift;
//        if(shift == Security.Shifts.NIGHT)
//            totalShift = workedShift*2;
//        else
//            totalShift = workedShift;
//
//        if(totalShift > dutyShift)
//            return obj.getBaseSalary() * (totalShift-dutyShift)*0.01;
//        else
//            return 0;
//    }
}