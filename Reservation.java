public class Reservation {
    private Passenger passenger;
    private Flight flight;
    private Flight.SeatClass seatClass;

    public Reservation(Passenger passenger, Flight flight, Flight.SeatClass seatClass) {
        this.passenger = passenger;
        this.flight = flight;
        this.seatClass = seatClass;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public Flight.SeatClass getSeatClass() {
        return seatClass;
    }
}
