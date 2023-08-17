import java.io.FileNotFoundException;
import java.io.IOException;

public class Airport {

    Admin admin = new Admin();

    PassengersFile passengersFile = new PassengersFile();
    FlightsFile flightsFile = new FlightsFile();

    public Airport() throws FileNotFoundException {

    }

    public void signUpUser(String username, String password) throws IOException {
        Ticket[] tickets = new Ticket[10];
        Passenger newPassenger = new Passenger(username,password,0,tickets,0,false);
        this.passengersFile.writePassenger(newPassenger,Passenger.count);
        Passenger.count++;
    }
    public boolean checkDuplicateUsername(String username) throws IOException {
        if (username.equals("admin"))
            return false;
        Passenger[] allPassengers = this.passengersFile.readAllPassengers();
        for (Passenger p : allPassengers) {
            if (p.getUsername().equals(username))
                return false;
        }
        return true;
    }

    public Passenger signInUser(String username, String password) throws IOException {
        Passenger[] allPassengers = this.passengersFile.readAllPassengers();
        for (Passenger p : allPassengers) {
            if (p.getUsername().equals(username) && p.getPassword().equals(password))
                return p;
        }
        return null;
    }














}
