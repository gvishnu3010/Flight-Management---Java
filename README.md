A Java-based Command-Line Interface (CLI) application simulating a basic flight booking system. This project allows users to interactively:

- **View available flights:** Search for flights based on origin and destination.
- **Book tickets:** Select a flight, specify the number of seats and seat class (Economy, Business, First Class), and optionally add meals.
- **Reserve tickets:** Similar to booking, but with a reservation status and potential later payment.
- **View bookings/reservations:** See a list of current bookings and reservations for all flights.
- **Cancel bookings/reservations:** Remove existing bookings or reservations.

The system models key entities like Flights (with details like name, number, origin, destination, capacity, departure time, and available seats), Passengers (with name, email, and phone number), and Reservations (linking passengers to specific flights and seat classes). It also includes basic input validation and cost calculation based on the airline and seat class.

This project demonstrates fundamental object-oriented programming concepts in Java and provides a practical example of a simple transaction-based application.
