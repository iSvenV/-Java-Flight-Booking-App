package Model.Airport;
public class Passenger extends User
{
    //Unique Attributes
    private double wallet;

    //Constructor
    public Passenger(int id, String fullname, String username, String password, String phone, String email) {
        super(id, fullname, username, password, phone, email);
    }

    //Getter-Setter
    public double getWallet() {
        return wallet;
    }
    public void setWallet(double wallet) {
        this.wallet = wallet;
    }
    //Wallet Increment
    public void incrementWallet(double value) {
        this.wallet += value;
    }
}