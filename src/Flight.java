import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
public class Flight {
    public static int count; //the number of Flights have been newed.
    private int index; //a number what is unique for every flight.
    private String id;
    private String origin;
    private String destination;
    private String date;
    private String time;
    private int price;
    private int seats;
    private int remainingSeats;

    public Flight(String origin, String destination, String date, String time, int price, int seats,boolean isNew) {
        this.index = count;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.price = price;
        this.seats = seats;
        this.remainingSeats=seats;
        Random random =  new Random();
        char c = (char) (random.nextInt(26)+'a');
        this.id=""+origin.toLowerCase().charAt(0)+destination.toLowerCase().charAt(0)+c+"-"+time.substring(0,2)+Integer.toString(count);
        if(isNew)
            count++;
    }

//    public Flight() {
//    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRemainingSeats() {
        return remainingSeats;
    }

    public void setRemainingSeats(int remainingSeats) {
        this.remainingSeats = remainingSeats;
    }
}
