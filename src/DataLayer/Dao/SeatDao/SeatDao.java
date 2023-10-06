/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.Dao.SeatDao;

import DataBussiness.Entity.Flight.Flight;
import DataBussiness.Entity.Seat.Seat;
import DataLayer.FileManager.IFileManager;
import DataBussiness.Validation.ConstantValue.LinkFile;
import DataLayer.FileManager.SeatFile.SeatFile;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author nhutm
 */
public class SeatDao implements ISeatDao{
    
    List<Seat> seatList = new ArrayList<>();
    IFileManager<Seat> seatFile = new SeatFile(); 
   
   
    public SeatDao() {
        this.loadDataFromFile();
    }
    

    @Override
    public void loadDataFromFile() {
        this.seatList = seatFile.readDataFromFile(LinkFile.SEAT_URL.getFileURL());
    }

    @Override
    public void saveDataToFile() {
        seatFile.storeDataToFile(LinkFile.SEAT_URL.getFileURL(), seatList);
    }

    @Override
    public List<Seat> getAll() {
        return seatList;
    }

    @Override
    public void delete(String flightNumber, String seatChoice) {
        seatList.stream().filter((seat) -> (seat.getFlightNumber().equals(flightNumber))).forEachOrdered((seat) -> {
            seat.getSeats().put(seatChoice, null);
        });
    }

    @Override
    public void delete(String flightNumber) {
        for(int i =0 ; i < seatList.size(); i++){
            if(seatList.get(i).getFlightNumber().equals(flightNumber)){
                seatList.remove(i);
            }
        }
    }

    @Override
    public Seat get(String flightNumber) {
        for(Seat seat : seatList){
            if(seat.getFlightNumber().equals(flightNumber)){
                return seat;
            }
        }
        return null;
    }

    @Override
    public void add(Seat seats) {
        seatList.add(seats);
    }

    @Override
    public int getIndex(String flightNumber) {
       for(int i = 0; i < seatList.size(); i++){
           if(seatList.get(i).getFlightNumber().equals(flightNumber)){
               return i;
           }
       }
       return -1;
    }

    @Override
    public boolean addSeatChoice(String flightNumber, String seatChoice, String IDReservation) {
        for(Seat seat : seatList){
           if(seat.getFlightNumber().equals(flightNumber)){
               for(String key : seat.getSeats().keySet()){
                   if(key.equals(seatChoice)){
                       seat.getSeats().put(key, IDReservation);
                       return true;
                   }
               }
           }
       }
       return false;
    }

    @Override
    public void updateSeatChoice(String flightNumber, String newSeatChoice, String IDReservation) {
        for(Seat seat: seatList){
            if(seat.getFlightNumber().equals(flightNumber)){
                for(String i : seat.getSeats().keySet()){
                    if(seat.getSeats().get(i).equals(IDReservation)){
                        seat.getSeats().put(i, null);
                        seat.getSeats().put(newSeatChoice, IDReservation);
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void update(Flight flight, String oldFlight) {
        seatList.stream().filter((seat) -> (seat.getFlightNumber().equals(oldFlight))).map((seat) -> {
            seat.setFlightNumber(flight.getFlightNumber());
            return seat;
        }).forEachOrdered((seat) -> {
            seat.setSeatAvailable(flight.getAvailableSeats());
        });
    }

  

  

  
    

    
   
   
}
