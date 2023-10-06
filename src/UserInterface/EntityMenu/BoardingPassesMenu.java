/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.EntityMenu;

import DataBussiness.Entity.BoardingPasses.BoardingPasses;
import DataBussiness.Entity.Person.Customer;
import DataBussiness.Services.BoardingPassesServices.BoardingPassesServices;
import DataBussiness.Services.BoardingPassesServices.IBoardingPassesServices;
import DataBussiness.Validation.DataInput;


/**
 *
 * @author nhutm
 */
public class BoardingPassesMenu {
    static IBoardingPassesServices boardingPassesList = new BoardingPassesServices();

    public static IBoardingPassesServices getBoardingPassesList() {
        return boardingPassesList;
    }
    
    
    //-----------Passenger Check-In and Seat Allocation and Availability--------------------
    
    public void passengerCheckIn() throws Exception{
        System.out.println("Passenger Check-In and Seat Allocation and Availability");
        String IDReservation = DataInput.getString("Enter your IDReservation : ");
        //-----------------------------------------------------------------------
        Customer customer = CustomerMenu.listCustomer.get(IDReservation);
        
        //-----------------------------------------------------------------------
        if(customer == null){
            System.out.println("IDReservation is invalid!");
            return;
        }
        //-----------------------------------------------------------------------
        CustomerMenu.getListCustomer().displayCustomerHeadTable();
        String seatChoice =  CustomerMenu.getListCustomer().displayCustomer(IDReservation);
        //-----------------------------------------------------------------------
        if(customer.getStatus().equals("RESERVATION")){
            SeatMenu.seatList.displayAvailabilitySeats(customer.getFlightNumber().trim());
            seatChoice = DataInput.getString("\n Enter your choice : ");
        
        //-------------Xử lý đồng bộ chổ ngồi--------------------------------------------------------
            if(!SeatMenu.seatList.addSeatChoice(customer.getFlightNumber(), seatChoice, IDReservation)){
                System.out.println("Your seat choice is invalid!");
                return;
            }
        }
        //----------------------------------------
        String choice = DataInput.getString("Do you want to check in ? Y/N");
            if(!("Y".equals(choice))){
               System.out.println("Check in fail!");
               return;
        }
        //-------------Xử lý set status cho sticket là cornfirm--------------------------------------------------------
        CustomerMenu.listCustomer.get(IDReservation).setStatus("CONFIRM");
         //-------------Khởi tạo boarding passes--------------------------------------------------------
        BoardingPasses boardingPasses = new BoardingPasses(IDReservation,customer.getName(),customer.getFlightNumber(),FlightMenu.flightList.get(customer.getFlightNumber()).getDepartureLocation(),FlightMenu.flightList.get(customer.getFlightNumber()).getArrivalLocation(),FlightMenu.flightList.get(customer.getFlightNumber()).getDepartureTime(),seatChoice);
        boardingPassesList.add(boardingPasses);
        //--------------Display boarding passes----------------------------------------------------
        boardingPasses.display();
        //-------------------------------------------------------------------
    }
    public void displayAll(){
        boardingPassesList.printAll();
    }
    public void saveToFile(){
        boardingPassesList.saveToFile();
    }
}
