/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Services.SeatServices;

import DataBussiness.Entity.Flight.Flight;
import DataBussiness.Entity.Seat.Seat;
import DataBussiness.Validation.Validation;
import DataLayer.Dao.SeatDao.ISeatDao;
import DataLayer.DaoFactory.DaoFactory;
import DataLayer.DaoFactory.IDaoFactory;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author nhutm
 */
public class SeatServices implements ISeatServices{
    ISeatDao seatAction;
    IDaoFactory seatDaoFactory;
    
    public SeatServices() {
        seatDaoFactory = new DaoFactory();
        seatAction = seatDaoFactory.seatDao();
    }
           
    @Override
    public  List<Seat> getAll() {
        return seatAction.getAll();
    }
    
    @Override
    public void add(Seat seat){
        seatAction.add(seat);
    }
    
    @Override
    public void printAll() {
        this.printHeadTable();
        for(Seat seat : seatAction.getAll()){
            if(!this.checkHashmap(seat.getSeats())){
                seat.display();
            }
        }
    }

    @Override
    public void saveToFile() {
       seatAction.saveDataToFile();
    }

    @Override
    public Seat get(String flightNumber) {
        return seatAction.get(flightNumber);
    }

    @Override
    public void displayAvailabilitySeats(String flightNumber) {
        Validation.sortSeatsAndDisplay(seatAction.get(flightNumber).getSeats());
    }

    @Override
    public boolean addSeatChoice(String flightNumber, String seatChoice, String IDReservation) {
        return seatAction.addSeatChoice(flightNumber, seatChoice, IDReservation);
    }

    @Override
    public void delete(String flightNumber) {
        seatAction.delete(flightNumber);
    }

    @Override
    public void update(Flight flight, String oldFlight) {
        seatAction.update(flight, oldFlight);
    }

    @Override
    public void printHeadTable() {
        for(int j = 0; j <= 50; j++) System.out.print("-");
        System.out.println("");
        System.out.printf("|  %-17s |  %-15s  |  %-4s  |\n","FLIGHT NUMBER","IDRESERVATION","SEAT");
        for(int j = 0; j <= 50; j++) System.out.print("-");
        System.out.println("");
    }

    @Override
    public boolean checkHashmap(HashMap<String, String> list) {
       for(String i : list.keySet()){
           if(!(list.get(i) == null || list.get(i).equals("NULL"))){
               return false;
           }
       }
       return true;
    }
    
    @Override
    public String getSeatChoice(String flightNumber, String IDReservation) {
        Seat seat = seatAction.get(flightNumber);
        if(seat.getSeats().keySet().isEmpty()) return null;
        for(String i : seat.getSeats().keySet()){
            if(seat.getSeats().get(i) != null )
            if(seat.getSeats().get(i).equals(IDReservation)){
                return i;
            }
        }
        return null;
    }
}
