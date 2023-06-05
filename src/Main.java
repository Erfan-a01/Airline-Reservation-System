import java.awt.*;


public class Main {
    public static void main(String[] args){
        Flight flight = new Flight("yazd","tehran","1401-08-12","19:00",19.5,15);
        Flight flight1 = new Flight("Kish","Mashhad","24-12-8","20:00",150,145);
        System.out.println(flight.getId());
        System.out.println(flight1.getId());
        System.out.println(Flight.count);
    }



}
