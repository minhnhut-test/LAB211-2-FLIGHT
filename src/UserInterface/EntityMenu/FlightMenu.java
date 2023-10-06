/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.EntityMenu;

import DataBussiness.Entity.Flight.Flight;
import DataBussiness.Services.FlightServices.FlightServices;
import DataBussiness.Services.FlightServices.IFlightServices;
import DataBussiness.Validation.DataInput;
import DataBussiness.Validation.Validation;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author nhutm
 */
public class FlightMenu {
    static IFlightServices flightList = new FlightServices();

    public static IFlightServices getFlightList() {
        return flightList;
    }
     
     //------------Flight schedule management----------------------------------------------------
     
     //------------add flight----------------------------------------------------
     public void add() throws ParseException, Exception{
          
           
            String departureLocation = Validation.convertNullString(DataInput.getString("Enter departure city : "));
            if(departureLocation.matches(".*[^a-zA-Z0-9\\\\s].*")){
                System.out.println("City does not exist");
                return;
            }
            //-----------Check----------------------------
            String destinationLocation =  Validation.convertNullString(DataInput.getString("Enter destination city : "));
             if(destinationLocation.matches(".*[^a-zA-Z0-9\\\\s].*")){
                System.out.println("City does not exist");
                return;
            }
            //-----------Check----------------------------
            Date departureTime = DataInput.getDate("Enter departure time : ");
            //--------------------------------------------
            if(departureTime != null)
            if(Validation.subDate(departureTime, Validation.getNowDate()) >= 0){
                System.out.println("Departure time must greater than now time !");
                return;
            }
            //-----------Check----------------------------
            Date arrivalTime = DataInput.getDate("Enter arrival time : ");
            if(arrivalTime != null && departureTime != null)
            if(Validation.subDate(departureTime, arrivalTime) <= 0){
                System.out.println("Arrival time must greater than departure time !");
                return;
            }
            //-----------Check----------------------------
            int availableSeats = DataInput.getIntegerNumber("Enter available seats : ");
            //----------------------------------------------------------
            if(availableSeats <= 0){
                System.out.println("Available seats must not negative number!");
                return;
            }
            //-----------khởi tạo chuyến bay----------------------------
            Flight flight = new Flight(Validation.getIDAuto("F",Flight.getAutoIDRservation()), departureLocation, destinationLocation, departureTime, arrivalTime, availableSeats);
            
           
            flightList.add(flight);
            
     }
     //------------Update flight----------------------------------------------------
     public void update() throws ParseException, Exception{
            String oldFlightNumber = Validation.removeBlankString(DataInput.getString("Enter flight number : "));
            //-----------Check flight number isExist? ----------------------------
            if(!flightList.checkDuplicate(oldFlightNumber, flightList.getAll()) || !Validation.checkStringEmpty(oldFlightNumber)){
                System.out.println("Flight does not exist !");
                return;
            }
            //----------- ----------------------------
            String newFlightNumber = Validation.removeBlankString(DataInput.getString("Enter new flight number : "));
            //---------------------------------------------------------------------------
            if(flightList.checkDuplicate(newFlightNumber,flightList.getAll())){
                System.out.println("Flight number is duplicate");
                return;
            }
            //---------------------------------------------------------------------------
            String departureLocation = Validation.removeBlankString(DataInput.getString("Enter new departure location : "));
            //---------------------------------------------------------------------------
            String arrivalLocation = Validation.removeBlankString(DataInput.getString("Enter new arrival location : "));
            //---------------------------------------------------------------------------
            Date departureTime = DataInput.getDate("Enter new departure time : ");
            //---------------------------------------------------------------------------
            Date arrivalTime = DataInput.getDate("Enter new arrival time : ");      
            //-----------Khoi tạo chuyến bay ----------------------------------------------------------
            Flight flight = new Flight(newFlightNumber, departureLocation, arrivalLocation, departureTime, arrivalTime, 0);
            flightList.update(flight, oldFlightNumber);
     }
     //------------Delete flight----------------------------------------------------
     public void delete(){
         String flightNumber = Validation.removeBlankString(DataInput.getString("Enter flight number : "));
         //---------------------------------------------------------------------------
         if(!flightList.checkDuplicate(flightNumber, flightList.getAll()) || !Validation.checkStringEmpty(flightNumber)){
                System.out.println("Flight does not exist !");
                return;
         }
         //-----------Display flight----------------------------------------------------------------
         flightList.get(flightNumber).display();
         //-----------Confirm message----------------------------------------------------------------
         String choice = DataInput.getString("Do you want to delete it ? Y/N");
         if(!("Y".equals(choice))){
            System.out.println("Delete fail!");
            return;
         }
         System.out.println("Delete success!");
         flightList.delete(flightList.get(flightNumber));
     }
     //------------Print all flight----------------------------------------------------
     public void displayAll(){
         flightList.printAll();
     }
     //------------save file----------------------------------------------------
     public void saveToFile(){
        flightList.saveToFile();
     }
}
