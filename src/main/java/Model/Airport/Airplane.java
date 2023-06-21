package Model.Airport;
import java.util.ArrayList;
public class Airplane
{
    //Attributes
    private int id;
    private int seats;
    private ArrayList<Flight> flights;
    private boolean[] seatsAvaliablity;

    //Constructor
    public Airplane(int id, int seats) {
        this.id = id;
        this.seats = seats;
        this.flights = new ArrayList<>();
        this.seatsAvaliablity = new boolean[seats];
    }

    //Getters
    public int getId() {
        return id;
    }
    public int getSeats() {
        return seats;
    }
    public ArrayList<Flight> getFlights() {
        return flights;
    }
    public boolean[] getSeatsAvaliablity() {
        return seatsAvaliablity;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }
    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }
    public void setSeatsAvaliablity(boolean[] seatsAvaliablity) {
        this.seatsAvaliablity = seatsAvaliablity;
    }
}