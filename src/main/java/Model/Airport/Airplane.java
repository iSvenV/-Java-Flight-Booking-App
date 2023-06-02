package Model.Airport;
import java.util.ArrayList;
public class Airplane
{
    //Attributes
    private int id;
    private int seats;
    private ArrayList<Flight> flights = new ArrayList<>();

    //Constructor
    public Airplane(int id, int seats, ArrayList<Flight> flights) {
        this.id = id;
        this.seats = seats;
        this.flights = flights;
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
}