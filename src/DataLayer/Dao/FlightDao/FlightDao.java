/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.Dao.FlightDao;

import DataBussiness.Entity.Flight.Flight;
import DataBussiness.Validation.Validation;
import DataLayer.FileManager.FlightFile.FlightFile;
import DataLayer.FileManager.IFileManager;
import DataBussiness.Validation.ConstantValue.LinkFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author nhutm
 */
public class FlightDao implements IFlightDao{
    static List<Flight> flightList = new ArrayList<>();
    IFileManager<Flight> flightFile = new FlightFile();
    
    
    public FlightDao() {
        loadDataFromFile();
    }

    
    @Override
    public void loadDataFromFile() {
        this.flightList = flightFile.readDataFromFile(LinkFile.FLIGHT_URL.getFileURL());
    }

    @Override
    public void saveDataToFile() {
        flightFile.storeDataToFile(LinkFile.FLIGHT_URL.getFileURL(), flightList);
    }

    @Override
    public List<Flight> getAll() {
        return flightList;
    }

    @Override
    public int getIndex(String flightNumber) {
        for(int i =0 ; i < flightList.size(); i++){
            if(flightList.get(i).getFlightNumber().equals(flightNumber)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public Flight get(String flightNumber) {
        for(Flight flight : flightList){
            if(flight.getFlightNumber().equals(flightNumber)){
                return flight;
            }
        }
        return null;
    }

    @Override
    public void update(Flight flightNumber,String oldFlightNumber) {
        flightList.stream().filter((flight) -> (flight.getFlightNumber().equals(oldFlightNumber))).map((flight) -> {
            flight.setFlightNumber(flightNumber.getFlightNumber());
            return flight;
        }).map((flight) -> {
            flight.setDepartureLocation(flightNumber.getDepartureLocation());
            return flight;
        }).map((flight) -> {
            flight.setArrivalLocation(flightNumber.getArrivalLocation());
            return flight;
        }).map((flight) -> {
            flight.setDepartureTime(flightNumber.getDepartureTime());
            return flight;
        }).map((flight) -> {
            flight.setArrivalTime(flightNumber.getArrivalTime());
            return flight;
        }).map((flight) -> {
            flight.setDuration(flightNumber.getDuration());
            return flight;
        }).forEachOrdered((flight) -> {
            flight.setAvailableSeats(flight.getAvailableSeats());
        });
    }

    @Override
    public boolean delete(Flight flightNumber) {
        return flightList.remove(flightNumber);
    }

    @Override
    public boolean add(Flight flightNumber) {
        return flightList.add(flightNumber);
    }

    @Override
    public List<Flight> searchFlight(Date departureTime, String departureLocation, String arrivalLocation) {
        List<Flight> list = new ArrayList<>();
       
        String  departureTimeS = Validation.executeNullDate(departureTime).substring(6);
        
        
        flightList.forEach((flight) -> {
            String departureTimeSystem;
            if(flight.getDepartureTime() == null){
                
            }else{
                departureTimeSystem = Validation.executeNullDate(flight.getDepartureTime()).substring(6);
                if (departureTimeSystem.equals(departureTimeS) && flight.getDepartureLocation().equals(departureLocation) && flight.getArrivalLocation().equals(arrivalLocation)) {
                    list.add(flight);
                }
            }
          
           
        });
        return list;
    }
    
    @Override
    public List<Flight> searchFlightForCheckDuplicate(Date departureTime, String departureLocation, String arrivalLocation){
        List<Flight> list = new ArrayList<>();
        String  departureTimeS = Validation.executeNullDate(departureTime);
        flightList.forEach((flight) -> {
            String departureTimeSystem;
            if(flight.getDepartureTime() == null){
                
            }else{
                departureTimeSystem = Validation.executeNullDate(flight.getDepartureTime());
                if (departureTimeSystem.equals(departureTimeS) && flight.getDepartureLocation().equals(departureLocation) && flight.getArrivalLocation().equals(arrivalLocation)) {
                    list.add(flight);
                }
            }
          
           
        });
        return list;
    }
 
    
    
}
