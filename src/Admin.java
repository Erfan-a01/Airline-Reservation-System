import java.io.FileNotFoundException;
import java.io.IOException;

public class Admin {
    private String name;
    private String password ;

    public Admin() {
        this.name = "admin";
        this.password = "admin";
    }

    public void addNewFlight(Flight flight) throws IOException {
        FlightsFile flightsFile = new FlightsFile();
        flightsFile.writeFlight(flight,Flight.count);
    }
    public void editFlight(Flight flight,int index) throws IOException {
        FlightsFile flightsFile = new FlightsFile();
        flightsFile.writeFlight(flight,index);

    }
    public void removeFlight(int index) throws IOException {
        FlightsFile flightsFile = new FlightsFile();
        for (int i = index+1; i < Flight.count-1 ; i++) {
            flightsFile.writeFlight(flightsFile.readFlight(i+1),i);
        }
        Flight.count--;
        flightsFile.rFile.setLength(Flight.count*102L);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
