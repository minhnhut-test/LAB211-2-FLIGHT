/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.Dao.BoardingPassesDao;

import DataBussiness.Entity.BoardingPasses.BoardingPasses;
import DataBussiness.Entity.Flight.Flight;
import DataLayer.FileManager.BoardingPassesFile.BoardingPassesFile;
import DataLayer.FileManager.IFileManager;
import DataBussiness.Validation.ConstantValue.LinkFile;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author nhutm
 */
public class BoardingPassesDao implements IBoardingPassesDao{
    List<BoardingPasses> boardingPassesList = new ArrayList<>();
    IFileManager<BoardingPasses> boardingPassesFile = new BoardingPassesFile();

    public BoardingPassesDao() {
        loadDataFromFile();
    }
    
    @Override
    public void loadDataFromFile() {
        this.boardingPassesList = boardingPassesFile.readDataFromFile(LinkFile.BOARDINGPASSES_URL.getFileURL());
    }

    @Override
    public void saveDataToFile() {
        boardingPassesFile.storeDataToFile(LinkFile.BOARDINGPASSES_URL.getFileURL(), boardingPassesList);
    }

    @Override
    public List<BoardingPasses> getAll() {
        return boardingPassesList;
    }

    @Override
    public int getIndex(String IDReservation, String flightNumber) {
          for(int i =0 ; i < boardingPassesList.size(); i++){
           if(boardingPassesList.get(i).getIDReservation().equals(IDReservation) && boardingPassesList.get(i).getFlightNumber().equals(flightNumber)){
               return i;
           }
       }
       return -1;
    }

    @Override
    public BoardingPasses get(String IDReservation, String flightNumber) {
        for(BoardingPasses boardingPasses : boardingPassesList){
            if(boardingPasses.getIDReservation().equals(IDReservation) && boardingPasses.getFlightNumber().equals(flightNumber)){
                return boardingPasses;
            }
        }
        return null;
    }

    @Override
    public void update(BoardingPasses newBoardingPasses) {
        boardingPassesList.stream().filter((boardingPasses) -> (boardingPasses.getIDReservation().equals(newBoardingPasses.getIDReservation()) && boardingPasses.getFlightNumber().equals(newBoardingPasses.getFlightNumber()))).map((boardingPasses) -> {
            boardingPasses.setIDReservation(newBoardingPasses.getIDReservation());
            return boardingPasses;
        }).map((boardingPasses) -> {
            boardingPasses.setFlightNumber(newBoardingPasses.getFlightNumber());
            return boardingPasses;
        }).map((boardingPasses) -> {
            boardingPasses.setName(newBoardingPasses.getName());
            return boardingPasses;
        }).map((boardingPasses) -> {
            boardingPasses.setDepartureLocation(newBoardingPasses.getDepartureLocation());
            return boardingPasses;
        }).map((boardingPasses) -> {
            boardingPasses.setDepartureTime(newBoardingPasses.getDepartureTime());
            return boardingPasses;
        }).map((boardingPasses) -> {
            boardingPasses.setArrivalLocation(newBoardingPasses.getArrivalLocation());
            return boardingPasses;
        }).forEachOrdered((boardingPasses) -> {
            boardingPasses.setSeat(newBoardingPasses.getSeat());
        });
    }

    @Override
    public boolean delete(BoardingPasses boardingPasses) {
        return boardingPassesList.remove(boardingPasses);
    }

    @Override
    public boolean add(BoardingPasses boardingPasses) {
        return boardingPassesList.add(boardingPasses);
    }

    @Override
    public void update(String flightNumber,Flight flight) {  
        boardingPassesList.stream().filter((boardingPasses) -> (boardingPasses.getFlightNumber().equals(flightNumber))).forEachOrdered((boardingPasses) -> {
            boardingPasses.setFlightNumber(flight.getFlightNumber());
            boardingPasses.setDepartureLocation(flight.getDepartureLocation());
            boardingPasses.setArrivalLocation(flight.getArrivalLocation());
            boardingPasses.setDepartureTime(flight.getDepartureTime());
        });
    }

    @Override
    public List<BoardingPasses> get(String flightNumber) {
        List<BoardingPasses> list = new ArrayList<>();
        for(BoardingPasses boarding : getAll()){
            list.add(boarding);
        }
        return list;
    }

   
    
}
