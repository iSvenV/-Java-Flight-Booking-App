package Model.Airport;
public class Report
{
    //Attributes
    private String discription;
    private User user;

    //Constructor
    public Report(String discription, User user) {
        this.discription = discription;
        this.user = user;
    }

    //Getters
    public String getDiscription() {
        return discription;
    }
    public User getUser() {
        return user;
    }

    //Setters
    public void setDiscription(String discription) {
        this.discription = discription;
    }
    public void setUser(User user) {
        this.user = user;
    }
}