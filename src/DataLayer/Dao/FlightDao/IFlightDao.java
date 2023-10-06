/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.Dao.FlightDao;

import DataBussiness.Entity.Flight.Flight;
import DataLayer.Dao.IUserDao;
import java.util.Date;
import java.util.List;


/**
 *
 * @author nhutm
 */
public interface IFlightDao extends IUserDao<Flight> {
    //------------------------------------------
    int getIndex(String flightNumber);
    //------------------------------------------
    Flight get(String flightNumber);
    //------------------------------------------
    void update(Flight flight, String oldFlightNumber);
    //------------------------------------------
    boolean delete(Flight flightNumber);
    //-----------------------------------------
    boolean add(Flight flightNumber);
    //-----------------------------------------
    List<Flight> searchFlightForCheckDuplicate(Date departureTime, String departureLocation, String arrivalLocation);
    //-----------------------------------------
    List<Flight> searchFlight(Date departureTime, String departureLocation, String arrivalLocation);
}
