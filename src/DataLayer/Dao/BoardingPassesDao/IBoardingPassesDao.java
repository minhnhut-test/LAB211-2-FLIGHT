/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.Dao.BoardingPassesDao;

import DataBussiness.Entity.BoardingPasses.BoardingPasses;
import DataBussiness.Entity.Flight.Flight;
import DataLayer.Dao.IUserDao;
import java.util.List;


/**
 *
 * @author nhutm
 */
public interface IBoardingPassesDao extends  IUserDao<BoardingPasses>{
   //------------------------------------------
    int getIndex(String IDReservation,String flightNumber);
    //------------------------------------------
    BoardingPasses get(String IDReservation, String flightNumber);
    //------------------------------------------
    void update(String flightNumber,Flight flight);
    //------------------------------------------
    void update(BoardingPasses newBoardingPasses);
    //------------------------------------------
    boolean delete(BoardingPasses boardingPasses);
    //-----------------------------------------
    boolean add(BoardingPasses boardingPasses);
    //----------------------------------------
    List<BoardingPasses> get(String flightNumber);
}
