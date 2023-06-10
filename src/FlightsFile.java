import java.io.FileNotFoundException;
import java.io.IOException;

public class FlightsFile extends RandomFile{



    public static final String flightsDataPath = "./Data/FlightsFile.txt";

    public FlightsFile() throws FileNotFoundException {
        createOpenFile(flightsDataPath);
    }

    @Override
    public Flight readFlight(int index) throws IOException {
        rFile.seek((index)* 102L);
        String id = readFixChars(10);
        String origin = readFixChars(10);
        String destination = readFixChars(10);
        String date = readFixChars(10);
        String time = readFixChars(5);
        int price = rFile.readInt();
        int seats = rFile.readInt();
        int remainingSeats = rFile.readInt();
        return new Flight(origin,destination,date,time,price,seats,false);
    }
    public Flight[] readAllFlights() throws IOException {
        Flight[] flights = new Flight[Flight.count];
        rFile.seek(0);
        for (int i = 0; i < Flight.count; i++) {
            flights[i] = readFlight(i);
        }
        return flights;
    }
    public Flight searchFlightByFlightId(String flightId) throws IOException {
        Flight[] allFlights = this.readAllFlights();
        for (Flight f : allFlights) {
            if (f.getId().equals(flightId))
                return f;
        }
        return null;
    }

    @Override
    public Passenger readPassenger(int index) throws IOException {
        return null;
    }

    @Override
    public void writePassenger(Passenger passenger,int index) throws IOException {

    }

    @Override
    public void writeFlight(Flight flight,int index) throws IOException {
        rFile.seek(index * 102L);
        writeFixChars(flight.getId(),FIX_ID);
        writeFixChars(flight.getOrigin(),FIX_ORIGIN);
        writeFixChars(flight.getDestination(),FIX_DESTINATION);
        writeFixChars(flight.getDate(),FIX_DATE);
        writeFixChars(flight.getTime(),FIX_TIME);
        rFile.writeInt(flight.getPrice());
        rFile.writeInt(flight.getSeats());
        rFile.writeInt(flight.getRemainingSeats());
        rFile.close();
    }




}
