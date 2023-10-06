/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Services.FlightServices;

import DataBussiness.Entity.Flight.Flight;
import DataBussiness.Services.IServices;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nhutm
 */
public interface IFlightServices extends IServices<Flight>{
    //--------------------------------
    void add(Flight flight);
    //-----------------------------------------
    List<Flight> searchFlight(Date departureTime, String departureLocation, String arrivalLocation);
    //-----------------------------------------
    void printList(List<Flight> list);
    //------------------------------------------
    Flight get(String flightNumber);
    //------------------------------------------
    boolean checkDuplicate(String flightNumber, List<Flight> list);
    //------------------------------------------
    void update(Flight flight, String oldFlightNumber);
    //------------------------------------------
    boolean delete(Flight flightNumber);
}
