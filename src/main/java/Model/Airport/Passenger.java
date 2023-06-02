package Model.Airport;
public class Passenger extends User
{
    //Unique Attributes
    private double currency;

    //Constructor
    public Passenger(int id, String fullname, String username, String password, String phone, String email, double currency) {
        super(id, fullname, username, password, phone, email);
        this.currency = currency;
    }
}