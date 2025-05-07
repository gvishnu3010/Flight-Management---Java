public class Passenger {
    private String name;
    private String email;
    private String phoneNumber;
    private Flight.SeatClass seatClass; 
    private int numSeats;

    public Passenger(String name, String email, String phoneNumber, Flight.SeatClass seatClass, int numSeats) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.seatClass = seatClass;
        this.numSeats = numSeats;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Flight.SeatClass getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(Flight.SeatClass seatClass) {
        this.seatClass = seatClass;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public void setNumSeats(int numSeats) {
        this.numSeats = numSeats;
    }
}
