/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Services.BoardingPassesServices;

import DataBussiness.Entity.BoardingPasses.BoardingPasses;
import DataBussiness.Entity.Flight.Flight;
import DataBussiness.Services.IServices;

/**
 *
 * @author nhutm
 */
public interface IBoardingPassesServices extends IServices<BoardingPasses>{
    //-------------------------------------
    void add(BoardingPasses boardingPasses);
    //------------------------------------------
    void update(String flightNumber,Flight flight);
    //------------------------------------------
    void delete(String flightNumber);
    //------------------------------------------
    BoardingPasses getBoardingPasses(String IDRservation);
}
