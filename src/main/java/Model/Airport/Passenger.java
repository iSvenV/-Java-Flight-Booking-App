package Model.Airport;
public class Passenger extends User
{
    //Unique Attributes
    private double wallet;

    //Constructor
    public Passenger(int id, String fullname, String username, String password, String phone, String email, double wallet) {
        super(id, fullname, username, password, phone, email);
        this.wallet = wallet;
    }

    //Getter-Setter
    public double getWallet() {
        return wallet;
    }
    public void setWallet(double currency) {
        this.wallet = currency;
    }
}