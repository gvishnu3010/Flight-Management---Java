import java.util.Date;
import java.util.Scanner;
public class FlightDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Flight[] flights = {
            new Flight("Vistara","VI101", "Hyderabad", "Mumbai", 1148, createDate(2024, 4, 25, 10, 30)),
            new Flight("Indigo","IN195", "Hyderabad", "Mumbai", 1500, createDate(2024, 4, 25, 12, 00)),
            new Flight("Air India","AI103", "Hyderabad", "Mumbai", 500, createDate(2024, 4, 26, 14, 30)),
            new Flight("Vistara","VI118", "Hyderabad", "Delhi", 1148, createDate(2024, 4, 25, 10, 00)),
            new Flight("Indigo","IN109", "Hyderabad", "Delhi", 1500, createDate(2024, 4, 25, 12, 30)),
            new Flight("Air India","AI103", "Hyderabad", "Delhi", 500, createDate(2024, 4, 26, 14, 00)),
            new Flight("Vistara","VI178", "Hyderabad", "Bengaluru", 1148, createDate(2024, 4, 25, 10, 45)),
            new Flight("Indigo","IN142", "Hyderabad", "Bengaluru", 1500, createDate(2024, 4, 25, 12, 15)),
            new Flight("Air India","AI153", "Hyderabad", "Bengaluru", 500, createDate(2024, 4, 26, 14, 15))
        };
        boolean exit = false;
        while (!exit) {
            System.out.println("Select an option:");
            System.out.println("1. Book a ticket");
            System.out.println("2. View Booking");
            System.out.println("3. Reserve a ticket");
            System.out.println("4. View my reservations");
            System.out.println("5. Cancel my reservation");
            System.out.println("6. Cancel my booking");
            System.out.println("7. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            if (choice == 7) {
                System.out.println("Exiting...");
                break; 
            }
            System.out.println("Enter passenger details:");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            name = validateName(name);
            if (name == null) {
                System.out.println("Invalid name. Name should only contain alphabets.");
                continue; 
            }
            System.out.print("Email: ");
            String email = scanner.nextLine();
            email = validateEmail(email);
            if (email == null) {
                System.out.println("Invalid email format.");
                continue; 
            }
            System.out.print("Phone Number: ");
            String phoneNumber = scanner.nextLine();
            phoneNumber = validatePhoneNumber(phoneNumber);
            if (phoneNumber == null) {
                System.out.println("Invalid phone number. Phone number should not contain alphabets.");
                continue;
            }
            switch (choice) {
                case 1:
                    bookTicket(scanner, flights, name, email, phoneNumber);
                    break;
                case 2:
                    viewBooking(scanner, flights);
                    break;
                case 3:
                    reserveTicket(scanner, flights, name, email, phoneNumber);
                    break;
                case 4:
                    viewReservations(scanner,flights);
                    break;
                case 5:
                    cancelReservation(scanner, flights);
                    break;
                case 6: 
                    cancelBooking(scanner, flights);
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
        scanner.close();
    }
    private static void viewReservations(Scanner scanner, Flight[] flights){
        System.out.println("Current Reservations: ");
        for (Flight flight : flights) {
            int i = 1;
            for (Reservation reservation : flight.getReservations()) {
                Passenger passenger = reservation.getPassenger();
                System.out.println(i + ". Passenger: " + passenger.getName() + ", Flight: " + flight.getFlightNumber()+" Departue Time: "+flight.getDepartureTime());
                i++;
            }
        }
    }
    private static void viewBooking(Scanner scanner, Flight[] flights){
        System.out.println("Current Bookings: ");
        for (Flight flight : flights) {

            int i = 1;
            for (Reservation reservation : flight.getReservations()) {
                Passenger passenger = reservation.getPassenger();
                System.out.println(i + ". Passenger: " + passenger.getName() + ", Flight: " + flight.getFlightNumber()+" Departue Time: "+flight.getDepartureTime());
                i++;
            }
        }
    }
    private static void bookTicket(Scanner scanner, Flight[] flights, String name, String email, String phoneNumber) {
        System.out.print("Enter origin: ");
        String origin = scanner.nextLine();
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();
        System.out.println("Available Flights:");
        for (Flight flight : flights) {
            if (flight.getOrigin().equalsIgnoreCase(origin) && flight.getDestination().equalsIgnoreCase(destination)) {
                System.out.println("Flight Name: "+flight.getFlightName()+"  Departure Time: "+flight.getDepartureTime()+" Cost per Ticket: "+Flight.calculateTicketCost(flight));
            }
        }
        System.out.print("Enter the Airline of your choice: ");
        String flightNumber = scanner.nextLine();
        flightNumber.toLowerCase();
        if(validateFlightNumber(flightNumber).equalsIgnoreCase("LMAO")){
            System.out.println("Invalid Choice.");
            return;
        }
        Flight selectedFlight = null;
        for (Flight flight : flights) {
            if (flight.getFlightName().equalsIgnoreCase(flightNumber)) {
                selectedFlight = flight;
                break;
            }
        }
        if (selectedFlight != null) {
            System.out.println("Available seats:");
            System.out.println("Economy: " + selectedFlight.getEconomySeatsAvailable());
            System.out.println("Business: " + selectedFlight.getBusinessSeatsAvailable());
            System.out.println("First Class: " + selectedFlight.getFirstClassSeatsAvailable());
            System.out.print("Choose seat class (Economy/Business/First Class): ");
            String seatClassString = scanner.nextLine();
            Flight.SeatClass seatClass;
            try {
                seatClass = Flight.SeatClass.valueOf(seatClassString.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid seat class. Please choose from available seat classes.");
                return; 
            }
            System.out.print("Would you like to add meals to your ticket(s):(Y/N) ");
            char mealChoice = scanner.next().charAt(0);
            if(validatemealChoice(mealChoice)=='A'){
                System.out.println("Invalid Choice");
                return;
            }
            String specialRequests = scanner.nextLine();
            System.out.print("Enter the number of seats: ");
            int numSeats = scanner.nextInt();
            scanner.nextLine(); 
            if (selectedFlight.bookTickets(new Passenger(name, email, phoneNumber, seatClass, numSeats), seatClass, numSeats)) {
                double ticketPrice = calculateCost(seatClass,mealChoice,Flight.calculateTicketCost(selectedFlight)) * numSeats;
                System.out.println("Booking confirmed for " + name + " on flight " + selectedFlight.getFlightNumber() + ".");
                System.out.println("Flight Details:");
                System.out.println("Airline: "+selectedFlight.getFlightName());
                System.out.println("Origin: " + origin);
                System.out.println("Destination: " + destination);
                System.out.println("Departure Time: " + selectedFlight.getDepartureTime());
                System.out.println("Date of travel: " + selectedFlight.getDepartureTime());
                System.out.println("Seat Class: " + seatClassString);
                System.out.println("Special Requests: " + specialRequests);
                System.out.println("Number of Seats: " + numSeats);
                System.out.println("Total Ticket Price: Rs" + ticketPrice);
                System.out.println("Thank You for choosing our airline.Please collect your boarding pass at the airline counter");
            } else {
                System.out.println("Sorry, the selected seats are not available.");
            }
        } else {
            System.out.println("Sorry, the flight from " + origin + " to " + destination + " is not available.");
        }
    }
    private static void reserveTicket(Scanner scanner, Flight[] flights, String name, String email, String phoneNumber) {
        System.out.print("Enter origin: ");
        String origin = scanner.nextLine();
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();
        System.out.println("Available Flights:");
        for (Flight flight : flights) {
            if (flight.getOrigin().equalsIgnoreCase(origin) && flight.getDestination().equalsIgnoreCase(destination)) {
                System.out.println("Flight Name: "+flight.getFlightName()+"  Departure Time: "+flight.getDepartureTime()+" Cost per Ticket: "+Flight.calculateTicketCost(flight));
            }
        }
        System.out.print("Enter the Airline of your choice: ");
        String flightNumber = scanner.nextLine();
        flightNumber.toLowerCase();
        if(validateFlightNumber(flightNumber).equalsIgnoreCase("LMAO")){
            System.out.println("Invalid Choice.");
            return;
        }
        Flight selectedFlight = null;
        for (Flight flight : flights) {
            if (flight.getFlightName().equalsIgnoreCase(flightNumber)) {
                selectedFlight = flight;
                break;
            }
        }
        if (selectedFlight != null) {
            System.out.println("Available seats:");
            System.out.println("Economy: " + selectedFlight.getEconomySeatsAvailable());
            System.out.println("Business: " + selectedFlight.getBusinessSeatsAvailable());
            System.out.println("First Class: " + selectedFlight.getFirstClassSeatsAvailable());
            System.out.print("Choose seat class (Economy/Business/First Class): ");
            String seatClassString = scanner.nextLine();
            Flight.SeatClass seatClass;
            try {
                seatClass = Flight.SeatClass.valueOf(seatClassString.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid seat class. Please choose from available seat classes.");
                return;
            }
            System.out.print("Enter the number of seats: ");
            int numSeats = scanner.nextInt();
            scanner.nextLine(); 
    
            if (selectedFlight.bookTickets(new Passenger(name, email, phoneNumber, seatClass, numSeats), seatClass, numSeats)) {
                System.out.println("Reservation confirmed for " + name + " on flight " + selectedFlight.getFlightNumber() + ". Additional 500 Rs will be added.");
                System.out.println("Please pay for your ticket at the airline counter.");
            } else {
                System.out.println("Sorry, the selected seats are not available.");
            }
        } else {
            System.out.println("Sorry, the flight from " + origin + " to " + destination + " is not available.");
        }
    }
    private static void cancelReservation(Scanner scanner, Flight[] flights) {
        System.out.println("Current Reservations:");
        for (Flight flight : flights) {
            int i = 1;
            for (Reservation reservation : flight.getReservations()) {
                Passenger passenger = reservation.getPassenger();
                System.out.println(i + ". Passenger: " + passenger.getName() + ", Flight: " + flight.getFlightNumber());
                i++;
            }
        }       
        System.out.print("Enter the number of the reservation to cancel (1-): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        int flightIndex = 0;
        int reservationIndex = choice;
        for (Flight flight : flights) {
            if (reservationIndex > flight.getReservations().size()) {
                reservationIndex -= flight.getReservations().size();
                flightIndex++;
            } else {
                boolean canceled = flight.cancelReservation(flight.getReservations().get(reservationIndex - 1));
                if (canceled) {
                    System.out.println("Reservation canceled successfully. Money will be refunded to your back account shortly.");
                } else {
                    System.out.println("Failed to cancel reservation.");
                }
                return;
            }
        }
        System.out.println("Invalid reservation number.");
    }
    private static void cancelBooking(Scanner scanner,Flight[] flights){
        System.out.println("Current Reservations:");
        for (Flight flight : flights) {
            int i = 1;
            for (Reservation reservation : flight.getReservations()) {
                Passenger passenger = reservation.getPassenger();
                System.out.println(i + ". Passenger: " + passenger.getName() + ", Flight: " + flight.getFlightNumber());
                i++;
            }
        }       
        System.out.print("Enter the number of the reservation to cancel (1-): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        int flightIndex = 0;
        int reservationIndex = choice;
        for (Flight flight : flights) {
            if (reservationIndex > flight.getReservations().size()) {
                reservationIndex -= flight.getReservations().size();
                flightIndex++;
            } else {
                boolean canceled = flight.cancelReservation(flight.getReservations().get(reservationIndex - 1));
                if (canceled) {
                    System.out.println("Boooking canceled successfully. Money will be refunded to your back account shortly.");
                } else {
                    System.out.println("Failed to cancel booking.");
                }
                return;
            }
        }
        System.out.println("Invalid booking number.");
    } 
    private static Date createDate(int year, int month, int day, int hour, int minute) {
        return new Date(year - 1900, month - 1, day, hour, minute);
    }
    
    private static Flight findFlight(Flight[] flights, String origin, String destination) {
        for (Flight flight : flights) {
            if (flight.getOrigin().equalsIgnoreCase(origin) && flight.getDestination().equalsIgnoreCase(destination)) {
                return flight;
            }
        }
        return null;
    }
    
    private static double calculateCost(Flight.SeatClass seatClass, char mealChoice, double a) {
        
        double economyCost = 0.0;
        double businessCost = 150.0;
        double firstClassCost = 200.0;
    
        
        switch (seatClass) {
            case ECONOMY:
                if(mealChoice=='Y'){
                    return economyCost+50+a;
                }
                else{
                    return economyCost+a;
                }
            case BUSINESS:
                if(mealChoice=='Y'){
                    return businessCost+50+a;
                }
                else{
                    return businessCost+a;
                }
            case FIRST_CLASS:
                if(mealChoice=='Y'){
                    return firstClassCost+50+a;
                }
                else{
                    return firstClassCost+a;
                }   
            default:
                return 0.0; 
        }
    }
    private static String validateName(String name) {
        if (name.matches("[a-zA-Z ]+")) {
            return name;
        }
        return null;
    }
    
    private static String validateEmail(String email) {
        if (email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            return email;
        }
        return null;
    }
    
    private static String validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("\\d{10}")) {
            return phoneNumber;
        }
        return null;
    }
    private static char validatemealChoice(char mealChoice){
        if(mealChoice=='Y'||mealChoice=='N')
        {
            return mealChoice;
        }
        else{
            return 'A';
        }
    }
    private static String validateFlightNumber(String flightNumber){
        if(flightNumber.equalsIgnoreCase("vistara")||flightNumber.equalsIgnoreCase("indigo")||flightNumber.equalsIgnoreCase("air india")){
            return flightNumber;
        }
        else{
            return "LMAO";
        }
    }
}
