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

    private int day;
    private int month;

    private int minute;
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
    public Flight(int id, Airplane plane, String from, String to, int day, int month, int minute, int hour, int flightLenght, Status status) {
        this.id = id;
        this.plane = plane;
        this.from = from;
        this.to = to;
        this.passengers = new ArrayList<>();
        this.flightLenght = flightLenght;
        this.status = status;

        this.day = day;
        this.month = month;
        this.minute = minute;
        this.hour = hour;
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
    public int getHour() {
        return hour;
    }
    public int getMinute() {
        return minute;
    }
    public int getMonth() {
        return month;
    }
    public int getDay() {
        return day;
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
    public void setHour(int hour) {
        this.hour = hour;
    }
    public void setMinute(int minute) {
        this.minute = minute;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public void setDay(int day) {
        this.day = day;
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