/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Services.SeatServices;

import DataBussiness.Entity.Flight.Flight;
import DataBussiness.Entity.Seat.Seat;
import DataBussiness.Services.IServices;
import java.util.HashMap;

/**
 *
 * @author nhutm
 */
public interface ISeatServices extends IServices<Seat>{
    void add(Seat seat);
     //--------------------------------------------------------------------------
    Seat get(String flightNumber);
     //--------------------------------------------------------------------------
    void displayAvailabilitySeats(String flightNumber);
    //-----------------Dùng để sắp xếp chổ ngồi cho khách-------------------
    boolean addSeatChoice(String flightNumber, String seatChoice, String IDReservation);
    //------------------------------------------
    void delete(String flightNumber);
       //-----------------update flight number-----------------
    void update(Flight flight, String oldFlight);
    //----------Checking hashmap null
    boolean checkHashmap(HashMap<String,String> list);
    //--------------------------------------------------------------------------
    String getSeatChoice(String flightNumber, String IDReservation);
}
