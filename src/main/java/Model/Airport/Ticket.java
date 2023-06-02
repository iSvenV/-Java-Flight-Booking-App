package Model.Airport;
public class Ticket
{
    //Attributes
    private int id;
    private double price;
    private double penalty;

    //Constructor
    public Ticket(int id, double price, double penalty) {
        this.id = id;
        this.price = price;
        this.penalty = penalty;
    }

    //Getters
    public int getId() {
        return id;
    }
    public double getPrice() {
        return price;
    }
    public double getPenalty() {
        return penalty;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }
}