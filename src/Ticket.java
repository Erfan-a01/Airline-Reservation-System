public class Ticket extends Flight{
    private String ticketId;

    public Ticket(String origin, String destination, String date, String time, int price, int seats, boolean isNew) {
        super(origin, destination, date, time, price, seats, isNew);
        this.ticketId = super.getId()+"-"+Integer.toString(super.getSeats()-super.getRemainingSeats());
    }


    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}
