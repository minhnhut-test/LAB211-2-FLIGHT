/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Services.BoardingPassesServices;

import DataBussiness.Entity.BoardingPasses.BoardingPasses;
import DataBussiness.Entity.Flight.Flight;
import DataLayer.Dao.BoardingPassesDao.IBoardingPassesDao;
import DataLayer.DaoFactory.DaoFactory;
import DataLayer.DaoFactory.IDaoFactory;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nhutm
 */
public class BoardingPassesServices implements  IBoardingPassesServices{
    IBoardingPassesDao boardingPassAction;
    IDaoFactory boardingPassesDaoFactory;

    public BoardingPassesServices() {
        boardingPassesDaoFactory = new DaoFactory();
        boardingPassAction = boardingPassesDaoFactory.boardingPassesDao();
    }
    
    
    @Override
    public List<BoardingPasses> getAll() {
        return boardingPassAction.getAll();
    }

    @Override
    public void printAll() {
       boardingPassAction.getAll().forEach(element -> {
           try {
               element.display();
           } catch (ParseException ex) {
               Logger.getLogger(BoardingPassesServices.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
    }

    @Override
    public void saveToFile() {
        boardingPassAction.saveDataToFile();
    }

    @Override
    public void add(BoardingPasses boardingPasses) {
        boardingPassAction.add(boardingPasses);
    }

    @Override
    public void update(String flightNumber, Flight flight) {
        boardingPassAction.update(flightNumber, flight);
    }

    @Override
    public void delete(String flightNumber) {
        List<BoardingPasses> list = boardingPassAction.get(flightNumber);
        for(BoardingPasses boarding : list){
            boardingPassAction.delete(boarding);
        }

    }

    @Override
    public void printHeadTable() {
        
    }

    @Override
    public BoardingPasses getBoardingPasses(String IDRservation) {
        for(BoardingPasses boardingPasses : boardingPassAction.getAll()){
            if(boardingPasses.getIDReservation().equals(IDRservation)){
                return boardingPasses;
            }
        }
        return null;
    }
    
    
    
}
