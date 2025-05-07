// Source code is decompiled from a .class file using FernFlower decompiler.
import Flight.1;
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

   public Flight(String var1, String var2, String var3, String var4, int var5, Date var6) {
      this.flightname = var1;
      this.flightNumber = var2;
      this.origin = var3;
      this.destination = var4;
      this.capacity = var5;
      this.departureTime = var6;
      this.economySeatsAvailable = var5 / 2;
      this.businessSeatsAvailable = var5 / 4;
      this.firstClassSeatsAvailable = var5 / 4;
      this.reservations = new ArrayList();
   }

   public String getFlightName() {
      return this.flightname;
   }

   public static double calculateTicketCost(Flight var0) {
      String var1 = var0.getFlightName();
      double var2 = 0.0;
      if (var1.equalsIgnoreCase("Indigo")) {
         var2 = 250.0;
      } else if (var1.equalsIgnoreCase("air india")) {
         var2 = 350.0;
      } else if (var1.equalsIgnoreCase("vistara")) {
         var2 = 500.0;
      }

      return var2;
   }

   public String getFlightNumber() {
      return this.flightNumber;
   }

   public String getOrigin() {
      return this.origin;
   }

   public String getDestination() {
      return this.destination;
   }

   public int getCapacity() {
      return this.capacity;
   }

   public Date getDepartureTime() {
      return this.departureTime;
   }

   public int getEconomySeatsAvailable() {
      return this.economySeatsAvailable;
   }

   public int getBusinessSeatsAvailable() {
      return this.businessSeatsAvailable;
   }

   public int getFirstClassSeatsAvailable() {
      return this.firstClassSeatsAvailable;
   }

   public boolean bookTickets(Passenger var1, Flight$SeatClass var2, int var3) {
      int var4;
      switch (1.$SwitchMap$Flight$SeatClass[var2.ordinal()]) {
         case 1:
            var4 = this.economySeatsAvailable;
            break;
         case 2:
            var4 = this.businessSeatsAvailable;
            break;
         case 3:
            var4 = this.firstClassSeatsAvailable;
            break;
         default:
            return false;
      }

      if (var4 < var3) {
         return false;
      } else {
         for(int var5 = 0; var5 < var3; ++var5) {
            this.reservations.add(new Reservation(var1, this, var2));
         }

         switch (1.$SwitchMap$Flight$SeatClass[var2.ordinal()]) {
            case 1:
               this.economySeatsAvailable -= var3;
               break;
            case 2:
               this.businessSeatsAvailable -= var3;
               break;
            case 3:
               this.firstClassSeatsAvailable -= var3;
         }

         return true;
      }
   }

   public boolean cancelReservation(Reservation var1) {
      boolean var2 = this.reservations.remove(var1);
      if (var2) {
         Flight$SeatClass var3 = var1.getSeatClass();
         switch (1.$SwitchMap$Flight$SeatClass[var3.ordinal()]) {
            case 1:
               ++this.economySeatsAvailable;
               break;
            case 2:
               ++this.businessSeatsAvailable;
               break;
            case 3:
               ++this.firstClassSeatsAvailable;
         }
      }

      return var2;
   }

   public List<Reservation> getReservations() {
      return this.reservations;
   }
}
