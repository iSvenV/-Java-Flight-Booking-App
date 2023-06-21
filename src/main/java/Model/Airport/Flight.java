package Model.Airport;
import java.util.ArrayList;
public class Flight
{
    //Attributes
    private int id;
    private Airplane plane;
    private Ticket ticket;
    private String from;
    private String to;
    private String date;
    private int hour;
    private int soldTickets;
    private ArrayList<Passenger> passengers;
    private int flightLenght;
    private Status status;

    //Flight Status Enum
    public enum Status {
        OPEN,
        CANCELED,
        AIRBORNE,
        LANDED
    }

    //Constructor
    public Flight(int id, Airplane plane, Ticket ticket, String from, String to, String date, int hour, int soldTickets, int flightLenght, Status status) {
        this.id = id;
        this.plane = plane;
        this.ticket = ticket;
        this.from = from;
        this.to = to;
        this.date = date;
        this.hour = hour;
        this.soldTickets = soldTickets;
        this.passengers = new ArrayList<>();
        this.flightLenght = flightLenght;
        this.status = status;
    }

    //Getters
    public int getId() {
        return id;
    }
    public Airplane getPlane() {
        return plane;
    }
    public Ticket getTicket() {
        return ticket;
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    public String getDate() {
        return date;
    }
    public int getHour() {
        return hour;
    }
    public int getSoldTickets() {
        return soldTickets;
    }
    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }
    public int getFlightLenght() {
        return flightLenght;
    }
    public Status getStatus() {
        return status;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setPlane(Airplane plane) {
        this.plane = plane;
    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setHour(int hour) {
        this.hour = hour;
    }
    public void setSoldTickets(int soldTickets) {
        this.soldTickets = soldTickets;
    }
    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }
    public void setFlightLenght(int flightLenght) {
        this.flightLenght = flightLenght;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}