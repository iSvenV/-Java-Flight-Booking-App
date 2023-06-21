package Model.Airport;
public class Feedback
{
    //Attributes
    private String text;
    private String author;
    private authorRole role;

    //Enum
    public enum authorRole {
        Passenger, Employee
    }

    //Constructor
    public Feedback(String text, String author, authorRole role) {
        this.text = text;
        this.author = author;
        this.role = role;
    }

    //Getters
    public String getText() {
        return text;
    }
    public String getAuthor() {
        return author;
    }
    public authorRole getAuthorRole() {
        return role;
    }

    //Setters
    public void setText(String text) {
        this.text = text;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setRole(authorRole role) {
        this.role = role;
    }
}