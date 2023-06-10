import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class PassengersFile extends RandomFile{
//    private ArrayList<Passenger> passengers;


    public static final String passengersDataPath = "./Data/PassengersFile.txt";

    public PassengersFile() throws FileNotFoundException {
        createOpenFile(passengersDataPath);
    }

    @Override
    public Flight readFlight(int index) throws IOException {
        return  null;
    }

    @Override
    public Passenger readPassenger(int index) throws IOException {
        rFile.seek((index)* 248L);
        String username = readFixChars(10);
        String password = readFixChars(10);
        int charge = rFile.readInt();
        int ticketNum = rFile.readInt();
        Ticket[] tickets = readTickets();
        return new Passenger(username,password,charge,tickets,ticketNum,false);
    }
    public Passenger[] readAllPassengers() throws IOException {
        Passenger[] passengers = new Passenger[Passenger.count];
        createOpenFile(passengersDataPath);
        rFile.seek(0);
        for (int i = 0; i < Passenger.count; i++) {
            passengers[i] = readPassenger(i);
        }
        return passengers;
    }

    //the cursor must be at the beginning of the ticket id records.
    public Ticket[] readTickets() throws IOException {
        FlightsFile flightsFile = new FlightsFile();
        Flight[] flights = flightsFile.readAllFlights();
        String ticketIdsRecord = readFixChars(100);
        //String[] ticketIds2 = ticketIdsRecord.split("",5);
        String[] ticketIds = ticketIdsRecord.split(",");
        Ticket[] tickets = new Ticket[ticketIds.length];
        String flightId;
        for (int i = 0; i < ticketIds.length; i++) {
            for (int j = 0; j < Flight.count; j++) {
                flightId = ticketIds[i].substring(0,7);
                if (flightId.equals(flights[j].getId()))
                    tickets[i]= new Ticket(
                            flights[j].getOrigin(),
                            flights[j].getDestination(),
                            flights[j].getDate(),
                            flights[j].getTime(),
                            flights[j].getPrice(),
                            flights[j].getSeats(),
                            false);
            }
        }
        return tickets;

    }

    @Override
    public void writePassenger(Passenger passenger,int index) throws IOException {
        rFile.seek(index*248L);
        writeFixChars(passenger.getUsername(),FIX_USERNAME);
        writeFixChars(passenger.getPassword(),FIX_PASSWORD);
        rFile.writeInt(passenger.getCharge());
        rFile.writeInt(passenger.ticketNum);
        writeFixChars(fixTicketIdsToWrite(passenger.userTickets),FIX_TICKETS);
        rFile.close();
    }
    

    @Override
    public void writeFlight(Flight flight, int index) throws IOException {
    }
    public String fixTicketIdsToWrite(Ticket[] tickets){
        StringBuilder fixedTicketIds= new StringBuilder();
        for (Ticket i :
                tickets) {
            if (i==null){
                fixedTicketIds.append("          ");
            }
            else {
                fixedTicketIds.append(i.getTicketId());
            }
            fixedTicketIds.append(",");
        }
        //fixedTicketIds.deleteCharAt(fixedTicketIds.length()-1);
        return fixedTicketIds.toString();
    }
    


}
