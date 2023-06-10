import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



//betaSerk

public class Menus {

    private Scanner input = new Scanner(System.in);
    Airport airport = new Airport();

    public Menus() throws FileNotFoundException {
    }


    //this method will clear console in any OS:
    //reference: www.javatpoint.com
    public static void clearConsole() {
            try {
                final String os = System.getProperty("os.name");
                if (os.contains("Windows"))
                {
                    Runtime.getRuntime().exec("cls");
                }
                else
                {
                    Runtime.getRuntime().exec("clear");
                }
            } catch (final Exception e) {
                e.printStackTrace();
            }
    }

    public void mainMenuAppearance() throws IOException {
        clearConsole();
        System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                .......................Welcome!.........................
                .            <1> Sign up                               .
                .            <2> Sign in                               .
                .            <0> Exit                                  .
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
        String command = input.next();
        mainMenuBack(command);
    }

    public void mainMenuBack(String command) throws IOException {
        while (true) {
            switch (command) {
                case "1" -> signUpMenuAppearance();
                case "2" -> signInMenuAppearance();
                case "0" -> {return;}
                default -> wrongCommand();
            }
            command=input.next();
        }
    }
    public void wrongCommand(){
        System.out.println("::::::::Command was wrong! try again:::::::::::");
    }

    public void signUpMenuAppearance() throws IOException {
        clearConsole();
        System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                .......................Sign up..........................
                .           Please enter your username:                .
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
        String enteredUsername = input.next();
        while (!airport.checkDuplicateUsername(enteredUsername)){
            System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                .......................Sign up..........................
                .           Please enter another username:             .
                .    The entered username has used by another user     .
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
            enteredUsername = input.next();
        }
        clearConsole();
        System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                .......................Sign up..........................
                .           Please enter your password:                .
                .                      WARNING!                        .
                .    Your password must have attlist 5 characters,     .
                .           contain numbers and alphabet.              .
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
        String enteredPassword = input.next();
        while (!(enteredPassword.matches("[a-zA-Z0-9]+") && (enteredPassword.length()>5))) {
            clearConsole();
            System.out.print("""
                    ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                    .......................Sign up..........................
                    .         Your password is not enough strong!          .
                    .           Please ReEnter your password:              .
                    .                      WARNING!                        .
                    .    Your password must have attlist 5 characters,     .
                    .           contain numbers and alphabet.              .
                    ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                                  >>""");
            enteredPassword = input.next();
        }
        clearConsole();
        airport.signUpUser(enteredUsername,enteredPassword);
        System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                .......................Sign up..........................
                .               Signed Up successfully!                .
                .      Enter anything to back to main menu:            .
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
        String command = input.next();
        mainMenuAppearance();
    }


    public void signInMenuAppearance() throws IOException {
        clearConsole();
        System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                .......................Sign in..........................
                .           Please enter your username:                .
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
        String enteredUsername = input.next();
        clearConsole();
        System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                .......................Sign in..........................
                .           Please enter your password:                .
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
        String enteredPassword = input.next();
        while (airport.signInUser(enteredUsername,enteredPassword)==null){
            System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                .......................Sign in..........................
                .       The username or password is incorrect.         .
                .          please ReEnter your username:               .
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
            enteredUsername = input.next();
            clearConsole();
            System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                .......................Sign in..........................
                .           Please enter your password:                .
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
            enteredPassword = input.next();
        }
        if (enteredUsername.equals("admin") && enteredPassword.equals("admin"))
            adminMenuAppearance();
        else
            passengerMenuAppearance(airport.signInUser(enteredUsername,enteredPassword));

    }

    public void passengerMenuAppearance(Passenger passenger) throws IOException {
        System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                .................PASSENGER MENU.........................
                .            <1> Change Password                       .
                .            <2> Search flight ticket                  .
                .            <3> Booking ticket                        .
                .            <4> Ticket cancellation                   .
                .            <5> Booked ticket                         .
                .            <6> Add charge                            .
                .            <0> Sign out                              .
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
        String command = input.next();
        passengerMenuBack(command,passenger);

    }

    public void passengerMenuBack(String command,Passenger passenger) throws IOException {
        while (true) {
            switch (command) {
                case "1" -> changePassMenu(passenger);
                case "2" -> searchFlightMenu(passenger);
                case "3" -> bookingTicketMenu(passenger);
                case "4" -> ticketCancellation(passenger);
                case "5" -> bookedTicket();
                case "6" -> addChargeMenu();
                case "0" -> mainMenuAppearance();
                default -> wrongCommand();
            }
        }

    }
    private void changePassMenu(Passenger passenger) throws IOException {
        clearConsole();
        System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                .......................Change Pass......................
                .           Please enter your new password:            .
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
        String newPass = input.next();
        passenger.changePassword(newPass);
    }




    private void searchFlightMenu(Passenger passenger) throws IOException {
        clearConsole();
        System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                ....................SEARCH FLIGHT.......................
                .           Please enter the flight origin:            .
                .  Enter 0 if you dont want to filter flight origin.   .
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
        String origin = input.next();
        clearConsole();
        System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                ....................SEARCH FLIGHT.......................
                .           Please enter the flight destination:       .
                .   Enter 0 if you dont want to filter destination.    .
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
        String destination = input.next();
        clearConsole();
        System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                ....................SEARCH FLIGHT.......................
                .           Please enter the flight date:              .
                .  Enter 0 if you dont want to filter flight date.     .
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
        String date = input.next();
        clearConsole();
        System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                ....................SEARCH FLIGHT.......................
                .           Please enter the flight time:              .
                .  Enter 0 if you dont want to filter flight time.     .
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
        String time = input.next();
        clearConsole();
        System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                ....................SEARCH FLIGHT.......................
                .           Please enter the flight price start:       .
                .Enter 0 if you dont want to filter by price start range.
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
        int priceStartRange = input.nextInt();
        clearConsole();
        System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                ....................SEARCH FLIGHT.......................
                .           Please enter the flight origin:            .
                .  Enter 0 if you dont want to filter flight origin.   .
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
        int priceEndRange = input.nextInt();
        ArrayList<Flight> flights =  passenger.searchFlightTicket(airport.flightsFile.readAllFlights(),origin,destination,date,time,priceStartRange,priceEndRange);
        Flight[] flightsArray = flights.toArray(new Flight[0]);
        showFlights(flightsArray);
        System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                :::::::::::::::::::::Enter anything to back to passenger menu:::::::::::::::::::
                ................................................................................
                              >>""");
        String command = input.next();
        passengerMenuAppearance(passenger);

    }
    public void showFlights(Flight[] flights){
        System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                |FlightId |Origin |Destination |Date    |Time   |Price   |Seats|Remaining Seats
                ................................................................................
                              >>""");
        for (Flight flight : flights) {
            System.out.printf("|%s\t|%s\t|%s\t|%s\t|%s\t|%d\t|%d\t|%d\t|",
                    flight.getId(),
                    flight.getOrigin(),
                    flight.getDestination(),
                    flight.getDate(),
                    flight.getTime(),
                    flight.getPrice(),
                    flight.getSeats(),
                    flight.getRemainingSeats());
        }
    }
    public void bookingTicketMenu(Passenger passenger) throws IOException {
        clearConsole();
        System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                ....................BOOKING TICKET......................
                .                Please enter flightId:                .
                .                  Enter 0 to back                     .
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
        String flightId = input.next();
        if (flightId.equals("0"))
            passengerMenuAppearance(passenger);
        else {
            while (airport.flightsFile.searchFlightByFlightId(flightId)==null){
                clearConsole();
                System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                ....................BOOKING TICKET......................
                .            Entered FlightId is incorrect.            .
                .                  please try again:                   .
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
                flightId = input.next();
            }
            Flight targetFlight = airport.flightsFile.searchFlightByFlightId(flightId);
            clearConsole();
            System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                ....................BOOKING TICKET......................
                .        You want to buy this flight's ticket:         .
                """);
            System.out.printf("|%s\t|%s\t|%s\t|%s\t|%s\t|%d\t|%d\t|%d\t|",
                    targetFlight.getId(),
                    targetFlight.getOrigin(),
                    targetFlight.getDestination(),
                    targetFlight.getDate(),
                    targetFlight.getTime(),
                    targetFlight.getPrice(),
                    targetFlight.getSeats(),
                    targetFlight.getRemainingSeats());
            System.out.println("""
                              ............Enter anything to next step.................
                              ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
            String anything = input.next();
            clearConsole();
            System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                ....................BOOKING TICKET......................
                .                      Warnings!                       .
                .          this action will reduce your charge         .
                .           Enter anything to buy this ticket          .
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
            anything = input.next();
            while ((!passenger.bookTicket(flightId)) && (!anything.equals("0"))){
                clearConsole();
                System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                ....................BOOKING TICKET......................
                .                   Condolences!!!                     .
                .   You dont have enough charge to buy this ticket     .
                .           Enter 0 to back to main menu:              .
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
                anything = input.next();
            }
            if (anything.equals("0")) {
                passengerMenuAppearance(passenger);
            }
            while (!anything.equals("0")) {
                clearConsole();
                System.out.print("""
                        ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                        ....................BOOKING TICKET......................
                        .                  Congratulation!                     .
                        .             You have bought the ticket               .
                        .             Enter 0 to passenger menu                .
                        ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                                      >>""");
            }
            passengerMenuAppearance(passenger);
        }


    }
    public void ticketCancellation(Passenger passenger) throws IOException {
        clearConsole();
        System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                .....................CANCEL TICKET......................
                .         Enter the ticketID to cancel the ticket      .
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
        String ticketId = input.next();
        while (!passenger.cancelTicket(ticketId)){
            clearConsole();
            System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                .....................CANCEL TICKET......................
                .         The Entered ticket id was not found          .
                .                      Try again:                      .
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                              >>""");
            ticketId = input.next();
        }
        if (passenger.cancelTicket(ticketId)) {
            clearConsole();
            System.out.print("""
                    ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                    .....................CANCEL TICKET......................
                    .         Ticket Cancelled successfully!               .
                    ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                                  >>""");
            ticketId = input.next();
        }
        passengerMenuAppearance(passenger);

    }
    public void bookedTicket(Passenger passenger){


    }





    public void adminMenuAppearance(){


    }




    public Scanner getInput() {
        return input;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }
}
