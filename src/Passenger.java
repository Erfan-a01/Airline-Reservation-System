public class Passenger {

    private String username;
    private String password;
    private int charge;
    public Ticket[] userTickets = new Ticket[10];
    public int ticketNum; // the number of tickets
    public static int count; //number of elements (customer)

    public Passenger(String username, String password, int charge, Ticket[] userTickets,int ticketNum,boolean isNew) {
        this.username = username;
        this.password = password;
        this.charge = charge;
        this.userTickets = userTickets;
        this.ticketNum = ticketNum;
        if (isNew)
            count++;
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
