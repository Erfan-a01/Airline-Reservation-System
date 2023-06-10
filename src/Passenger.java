import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Passenger {

    private String username;
    private String password;
    private int charge;
    public Ticket[] userTickets = new Ticket[10];
    public int ticketNum; // the number of tickets
    private final int index;
    public static int count; //number of elements (customer)

    public Passenger(String username, String password, int charge, Ticket[] userTickets,int ticketNum,boolean isNew) {
        this.username = username;
        this.password = password;
        this.charge = charge;
        this.userTickets = userTickets;
        this.ticketNum = ticketNum;
        this.index = count;
        if (isNew)
            count++;
    }
    public void changePassword(String newPass) throws IOException {
        this.password=newPass;
        PassengersFile passengersFile = new PassengersFile();
        passengersFile.writePassenger(this,this.index);

    }

    public ArrayList<Flight> searchFlightTicket(
                                        Flight[] allFlights,
                                        String origin,
                                        String destination,
                                        String date,
                                        String time,
                                        int priceStartRange,
                                        int priceEndRange){
        ArrayList<Flight> searchedFlights = new ArrayList<>();
        Flight optimalFlight = new Flight(origin,destination,date,time,0,0,false);
        for (Flight f : allFlights) {
                if (f.equals(optimalFlight) && (f.getPrice()>=priceStartRange && f.getPrice()<=priceEndRange))
                    searchedFlights.add(f);
        }
        return searchedFlights;
    }

    public boolean bookTicket(String flightId) throws IOException {
        FlightsFile flightsFile = new FlightsFile();
        Flight flight = flightsFile.searchFlightByFlightId(flightId);
        flight.setRemainingSeats(flight.getRemainingSeats()-1);
        Ticket ticket = new Ticket(flight.getOrigin(),flight.getDestination(),flight.getDate(),flight.getTime(),flight.getPrice(),flight.getSeats(),false);
        if (this.charge>=ticket.getPrice()){
            this.charge-=ticket.getPrice();
            this.userTickets[this.ticketNum] = ticket;
            this.ticketNum++;
            PassengersFile passengersFile = new PassengersFile();
            passengersFile.writePassenger(this, this.index);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean cancelTicket(String ticketId) throws IOException {
        Ticket ticketToCancel = searchTicketByTicketId(ticketId);
        if (ticketToCancel==null){
            return false;
        }
        else {
            ArrayList<Ticket> ticketArrayList = new ArrayList<>(Arrays.stream(this.userTickets).toList());
            ticketArrayList.remove(ticketToCancel);
            this.ticketNum--;
            Ticket[] newTickets = new Ticket[this.ticketNum];
            this.userTickets = ticketArrayList.toArray(newTickets);
            PassengersFile passengersFile = new PassengersFile();
            passengersFile.writePassenger(this, this.index);
            return true;
        }
    }

    public Ticket searchTicketByTicketId(String ticketId){
        for (Ticket t : this.userTickets) {
            if(t.getTicketId().equals(ticketId))
                return t;
        }
        return null;

    }

    public void addCharge(int amount) throws IOException {
        this.charge+=amount;
        PassengersFile passengersFile = new PassengersFile();
        passengersFile.writePassenger(this,this.index);
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }
}
