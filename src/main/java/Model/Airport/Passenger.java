package Model.Airport;
import java.util.ArrayList;

public class Passenger extends User
{
    //Unique Attributes
    private double wallet;
    private ArrayList<Ticket> tickets;

    //Constructor
    public Passenger(int id, String fullname, String username, String password, String phone, String email) {
        super(id, fullname, username, password, phone, email);
        this.setRole(Role.Passenger);
        this.tickets = new ArrayList<>();
    }

    //Getters
    public double getWallet() {
        return wallet;
    }
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
    //Setters
    public void setWallet(double wallet) {
        this.wallet = wallet;
    }
    public void incrementWallet(double value) {
        this.wallet += value;
    }
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}