import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {

    private int id;
    private String message;
    private Date date;
    private DateFormat df;

    public Event(int id, Date date, String message) {
        this.id = id;
        this.date = date;
        this.message = message;
    }

    public Event(Date date) {
        this.id = new Random().nextInt();
        this.date = date;
    }
    public Event(Date date, DateFormat df) {
        this.id = new Random().nextInt();
        this.date = date;
        this.df = df;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        df.format(date);
        return "Event [id=" + id + ", msg=" + message + ", date=" + df.format(date) + "]";
    }
}
