/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.EntityMenu;

import DataBussiness.Services.SeatServices.ISeatServices;
import DataBussiness.Services.SeatServices.SeatServices;

/**
 *
 * @author nhutm
 */
public class SeatMenu {
   static ISeatServices seatList = new SeatServices();

    public static ISeatServices getSeatList() {
        return seatList;
    }

   public void saveToFile(){
        seatList.saveToFile();
   }
   public void displayAll(){
       seatList.printAll();
   }
}
