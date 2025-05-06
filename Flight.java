import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Flight {
    private String flightname;
    private String flightNumber;
    private String origin;
    private String destination;
    private int capacity;
    private Date departureTime;
    private int economySeatsAvailable;
    private int businessSeatsAvailable;
    private int firstClassSeatsAvailable;
    private List<Reservation> reservations;

    
    public enum SeatClass {
        ECONOMY, BUSINESS, FIRST_CLASS
    }

    public Flight(String flightname,String flightNumber, String origin, String destination, int capacity, Date departureTime) {
        this.flightname=flightname;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.capacity = capacity;
        this.departureTime = departureTime;
        this.economySeatsAvailable = capacity/2;
        this.businessSeatsAvailable = capacity/4;
        this.firstClassSeatsAvailable = capacity/4;
        this.reservations = new ArrayList<>();
    }
    public String getFlightName() {
        return flightname;
    }
    public static double calculateTicketCost(Flight flight){
        String flightname=flight.getFlightName();
        double ticketcost=0.0;
        if(flightname.equalsIgnoreCase("Indigo")){
            ticketcost=250.00;
        }
        else if(flightname.equalsIgnoreCase("air india")){
            ticketcost=350.00;
        }
        else if(flightname.equalsIgnoreCase("vistara")){
            ticketcost=500.00;
        }
        return ticketcost;
    }
    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getCapacity() {
        return capacity;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public int getEconomySeatsAvailable() {
        return economySeatsAvailable;
    }

    public int getBusinessSeatsAvailable() {
        return businessSeatsAvailable;
    }

    public int getFirstClassSeatsAvailable() {
        return firstClassSeatsAvailable;
    }
    public boolean bookTickets(Passenger passenger, SeatClass seatClass, int numTickets) {
        int availableSeats;
        switch (seatClass) {
            case ECONOMY:
                availableSeats = economySeatsAvailable;
                break;
            case BUSINESS:
                availableSeats = businessSeatsAvailable;
                break;
            case FIRST_CLASS:
                availableSeats = firstClassSeatsAvailable;
                break;
            default:
                return false; 
        }
        if (availableSeats >= numTickets) {
            for (int i = 0; i < numTickets; i++) {
                reservations.add(new Reservation(passenger, this, seatClass));
            }
            switch (seatClass) {
                case ECONOMY:
                    economySeatsAvailable -= numTickets;
                    break;
                case BUSINESS:
                    businessSeatsAvailable -= numTickets;
                    break;
                case FIRST_CLASS:
                    firstClassSeatsAvailable -= numTickets;
                    break;
            }
            return true;
        }
        return false;
    }

    public boolean cancelReservation(Reservation reservation) {
        boolean removed = reservations.remove(reservation);
        if (removed) {
            SeatClass seatClass = reservation.getSeatClass();
            switch (seatClass) {
                case ECONOMY:
                    economySeatsAvailable++;
                    break;
                case BUSINESS:
                    businessSeatsAvailable++;
                    break;
                case FIRST_CLASS:
                    firstClassSeatsAvailable++;
                    break;
            }
        }
        return removed;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}