import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PipedReader;
import java.io.RandomAccessFile;

public abstract class RandomFile {
    protected RandomAccessFile rFile;
    protected final int FIX_ID = 10;
    protected final int FIX_ORIGIN = 10;
    protected final int FIX_DESTINATION = 10;
    protected final int FIX_DATE = 10;
    protected final int FIX_TIME = 5;
    protected final int FIX_USERNAME = 10;
    protected final int FIX_PASSWORD = 10;
    protected final int FIX_TICKETS = 100;

    protected final int FIX_P = 100;

    public void createOpenFile(String filePath) throws FileNotFoundException {
        this.rFile = new RandomAccessFile(filePath, "rw");
    }

    public abstract Flight readFlight(int index) throws IOException;
    public abstract Passenger readPassenger(int index) throws IOException;
    public abstract void writePassenger(Passenger passenger,int index) throws IOException;
    public abstract void writeFlight(Flight flight, int index) throws IOException;


    //read fixed String with deleting spaces and then return the string.
    public String readFixChars(int n) throws IOException {
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < n; i++) {
            tmp.append(rFile.readChar());
        }
        return tmp.toString().trim();
    }


    //write a fix String to file due to the size we need.
    public void writeFixChars(String str ,int n)throws IOException {
        StringBuilder strBuilder = new StringBuilder(str);
        while (strBuilder.length() < n)
            strBuilder.append(" ");
        str = strBuilder.toString();
        rFile.writeChars(str.substring(0,n));
    }


}