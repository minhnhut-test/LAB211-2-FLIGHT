/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Services.FlightServices;

import DataBussiness.Entity.Flight.Flight;
import DataBussiness.Entity.Seat.Seat;
import DataBussiness.Validation.DataInput;
import DataBussiness.Validation.Validation;

import DataLayer.Dao.FlightDao.IFlightDao;
import DataLayer.DaoFactory.DaoFactory;
import DataLayer.DaoFactory.IDaoFactory;
import UserInterface.EntityMenu.AssignmentMenu;
import UserInterface.EntityMenu.BoardingPassesMenu;
import UserInterface.EntityMenu.CustomerMenu;
import UserInterface.EntityMenu.SeatMenu;
import java.util.Date;

import java.util.List;

/**
 *
 * @author nhutm
 */
public class FlightServices implements IFlightServices{
    IFlightDao flightAction;
    IDaoFactory flightDaoFactory;

    public FlightServices() {
        flightDaoFactory = new DaoFactory();
        flightAction = flightDaoFactory.flightDao();
    }
    
     
    @Override
    public List<Flight> getAll() {
        return flightAction.getAll();
    }


    @Override
    public void printAll() {
        this.printHeadTable();
        List<Flight> list = Validation.sortFlightList(flightAction.getAll());
        list.forEach(element -> element.display());
    }

    @Override
    public void saveToFile() {
       flightAction.saveDataToFile();
    }

    @Override
    public void add(Flight flight)  {
     //----------------------------------------------------------
     List<Flight> list = flightAction.searchFlightForCheckDuplicate(flight.getDepartureTime(),flight.getDepartureLocation(),flight.getArrivalLocation());
     if(!list.isEmpty()){
         for(int i = 0; i <= 143 ; i++)System.out.print("-");
         System.out.println("\n  WARNING : The flight similar to : ".toUpperCase());
         this.printHeadTable();
         list.forEach((tmp) -> {
             tmp.display();
         });
     }
     //----------------------------------------------------------
     for(int i = 0; i <= 143 ; i++)System.out.print("-");
     System.out.println("\n  THE FLIGHT WILL BE ADDED");
     this.printHeadTable();
     flight.display();
     
     //----------------------------------------------------------
     String choice = DataInput.getString("Do you want to add it ? Y/N");
     if(!("Y".equals(choice))){
        System.out.println("Add flight fail!");
        return;
     }
     
     //-----------khởi tạo chổ ngồi----------------------------
     Seat seat = new Seat(flight.getFlightNumber(), flight.getAvailableSeats());
     SeatMenu.getSeatList().add(seat);
     flightAction.add(flight);
    }
    
    
    //chỉ dùng cho đặt vé
    @Override
    public List<Flight> searchFlight(Date departureTime, String departureLocation, String arrivalLocation) {
        List<Flight> list = flightAction.searchFlight(departureTime, departureLocation.toUpperCase(), arrivalLocation.toUpperCase());
        for(int i  = 0; i < list.size(); i++){
            if(list.get(i).getAvailableSeats() == 0){
                list.remove(i);
            }
        }
        return list;
    }

    @Override
    public void printList(List<Flight> list) {
        this.printHeadTable();
        list.forEach(flight -> flight.display());
    }

    @Override
    public Flight get(String flightNumber) {
        return flightAction.get(flightNumber);
    }

    @Override
    public boolean checkDuplicate(String flightNumber, List<Flight> list) {
         return list.stream().anyMatch((flight) -> (flight.getFlightNumber().equals(flightNumber)));
    }

    @Override
    public void update(Flight flight, String oldFlightNumber) {
        Flight oldFlight = flightAction.get(oldFlightNumber);
        if(flight.getFlightNumber().isEmpty()){
            flight.setFlightNumber(oldFlight.getFlightNumber());
        }
        if(flight.getDepartureLocation().isEmpty()){
            flight.setDepartureLocation(oldFlight.getDepartureLocation());
        }
        if(flight.getArrivalLocation().isEmpty()){
            flight.setArrivalLocation(oldFlight.getArrivalLocation());
        }
        if(flight.getDepartureTime() == null){
            flight.setDepartureTime(oldFlight.getDepartureTime());
        }
        if(flight.getArrivalTime() == null){
            flight.setArrivalTime(oldFlight.getArrivalTime());
        }
        
        if(flight.getAvailableSeats() == 0){
            flight.setAvailableSeats(oldFlight.getAvailableSeats());
        }
        if(flight.getDepartureTime() != null && flight.getArrivalTime() != null){
            flight.setDuration(Validation.changeToDurationTime(flight.getDepartureTime(), flight.getArrivalTime()));
        }
        
        if(Validation.subDate(flight.getDepartureTime(), Validation.getNowDate()) >= 0 && flight.getDepartureTime() != null){
                System.out.println("Departure time must greater than now time !");
                return;
        }
         if(flight.getDepartureTime() != null && flight.getArrivalTime() != null){
            if(Validation.subDate(flight.getDepartureTime(), flight.getArrivalTime()) <= 0){
                System.out.println("Arrival time must greater than departure time !");
                return;
            }
         }
        //-----------Display information affter update and confirm------------------------------------------------------------------------------------
        this.printHeadTable();
        flight.display();
        //-----------Message confirm ---------------------------------
        String choice = DataInput.getString("Do you want to update it ? Y/N");
          if(!("Y".equals(choice))){
            System.out.println("Update flight fail!");
          return;
        }
        //-----------Xử lý đồng bộ với boarding passes --------------------------------------------------
        BoardingPassesMenu.getBoardingPassesList().update(oldFlightNumber, flight);
        //-----------Xử lý đồng bộ với seat ----------------------------------------------------------
        SeatMenu.getSeatList().update(flight, oldFlightNumber);
        //-----------Xử lý đồng bộ với customer ----------------------------------------------------------
        CustomerMenu.getListCustomer().update(oldFlightNumber, flight);
        //-----------Xử lý đồng bộ với assignment ----------------------------------------------------------
        AssignmentMenu.getListAssignment().update(oldFlightNumber, flight);
       
        flightAction.update(flight, oldFlightNumber);
    }

    @Override
    public boolean delete(Flight flightNumber) {
        //-----------Xử lý đồng bộ với boarding passes ----------------------------------------------------------
        BoardingPassesMenu.getBoardingPassesList().delete(flightNumber.getFlightNumber());
        //-----------Xử lý đồng bộ với seat ----------------------------------------------------------
        SeatMenu.getSeatList().delete(flightNumber.getFlightNumber());
        //-----------Xử lý đồng bộ với customer ----------------------------------------------------------
        CustomerMenu.getListCustomer().delete(flightNumber.getFlightNumber());
        //-----------Xử lý đồng bộ với assignment ----------------------------------------------------------
        AssignmentMenu.getListAssignment().delete(flightNumber.getFlightNumber());
        return  flightAction.delete(flightNumber);
    }

    @Override
    public void printHeadTable() {
        for(int i = 0; i <= 143 ; i++)System.out.print("-");
        System.out.println("");
        System.out.printf("|  %-15s  |  %-18s  |  %-18s  |  %-17s  |  %-17s  |  %-8s  |  %-15s  |\n","FLIGHT NUMBER","DEPARTURE LOCATION","ARRIVAL LOCATION","DEPARTURE TIME","ARRIVAL TIME","DURATION","AVAILABLE SEATS");
        for(int i = 0; i <= 143 ; i++)System.out.print("-");
        System.out.println("");
    }
    
}
