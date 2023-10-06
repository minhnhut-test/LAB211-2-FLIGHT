/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.EntityMenu;

import DataBussiness.Entity.Flight.Flight;
import DataBussiness.Entity.Person.Customer;
import DataBussiness.Services.CustomerServices.CustomerServices;
import DataBussiness.Services.CustomerServices.ICustomerServices;
import DataBussiness.Validation.DataInput;
import DataBussiness.Validation.Validation;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nhutm
 */
public class CustomerMenu {

    static ICustomerServices listCustomer = new CustomerServices();

    public static ICustomerServices getListCustomer() {
        return listCustomer;
    }

    //----------Passenger Reservation and Booking------------------------------------------------------
    public void addCustomer() throws ParseException, Exception {
        String departureLocation = DataInput.getString("Enter departure location : ");
        //----------------------------------------------------------------------------
        if(!Validation.checkStringEmpty(departureLocation)){
            System.out.println("Departure location is not null !");
            return;
        }
        //----------------------------------------------------------------------------
        String arrivalLocation = DataInput.getString("Enter arrival location : ");
        //----------------------------------------------------------------------------
        if(!Validation.checkStringEmpty(arrivalLocation)){
            System.out.println("Arrival location is not null !");
            return;
        }
        //----------------------------------------------------------------------------
        Date departureTime = DataInput.getDate("Enter departure time : ");
        //----------------------------------------------------------------------------
        if(departureTime == null){
            System.out.println("Departure time is not null !");
            return;
        }
        //--------------------------------------------------------------
        List<Flight> flightList = FlightMenu.flightList.searchFlight(departureTime, departureLocation, arrivalLocation);
        //----------------------------------------------------------------------------
        
        //----------------------------------------------------------------------------
        if(flightList.isEmpty() || flightList == null){
            System.out.println("Could not find a suitable flight!");
            return;
        }
        //----------------------------------------------------------------------------
        FlightMenu.flightList.printList(flightList);
        //--------------------------------------------------------------
        System.out.println("Reservation and Booking!".toUpperCase());
        String flightNumberBooking = DataInput.getString("Enter flight number : ");
        //--------------------------------------------------------------
        if(!FlightMenu.flightList.checkDuplicate(flightNumberBooking, flightList)){
            System.out.println("Flight number is not exist!");
            return;
        }
        //--------------------------------------------------------------
        if(!(FlightMenu.flightList.get(flightNumberBooking).getAvailableSeats() > 0)){
            System.out.println("Flight is full!");
            return;
        }
        //--------------------------------------------------------------
        String nameCustomer = Validation.convertNullString(DataInput.getString("Enter name please : "));
        //--------------------------------------------------------------
        String emailCustomer = Validation.convertNullString(DataInput.getString("Enter email please : "));
        //--------------------------------------------------------------
        String addressCustomer = Validation.convertNullString(DataInput.getString("Enter address please : "));
        //--------------------------------------------------------------
        Date birthdayCustomer = DataInput.getDate("Enter birthday please : ");
        //--------------------------------------------------------------
        String nationality = Validation.convertNullString(DataInput.getString("Enter your nationality please : "));
        
        //-----------Khởi tạo cutomer add to list----------------------------------------------------
        Customer customer = new Customer(Validation.getIDAuto("IDR",Customer.getAutoIDRservation()),nationality,"RESERVATION",flightNumberBooking,emailCustomer,nameCustomer,addressCustomer,birthdayCustomer);
        listCustomer.add(customer);
        //----------------------------------------------------------------------
        
    }
    
    //--------------------------------------------------------
   
    public void updateCustomer(){
        //---------------------------------------------------
        String IDReservation = Validation.removeBlankString(DataInput.getString("Enter id resrevation : "));
        //---------------------------------------------------
        if(!listCustomer.checkDuplicateCustomer(IDReservation)){
            System.out.println("Customer does not exist!");
            return;
        }
        //---------------------------------------------------
        Customer oldCustomer = listCustomer.get(IDReservation);      
        if(oldCustomer == null){
            System.out.println("The customer has completed checking !");
            return;
        }
        listCustomer.displayCustomerHeadTable();
        String seatChoice = listCustomer.displayCustomer(IDReservation);
        //---------------------------------------------------
        String newIDReservation =  Validation.convertNullString(DataInput.getString("Enter new id resrevation please : "));     
        //--------------------------------------------------------------------------
        if(listCustomer.checkDuplicateCustomer(newIDReservation)){
            System.out.println("New id reservation is duplicate!");
            return;
        }
        //--------------------------------------------------------------------------
        if(!newIDReservation.matches("^IDR\\d{4}$") && !newIDReservation.equals("N/A")){
            System.out.println("New id is not format ! (IDRxxxx)");
            return;
        }
        //---------------------------------------------------
        String newFlightNumber =  Validation.convertNullString(DataInput.getString("Enter new fight number please : "));
        if(!FlightMenu.getFlightList().checkDuplicate(newFlightNumber,FlightMenu.getFlightList().getAll()) && !newFlightNumber.equals("N/A") ){
            System.out.println("Flight number does not exist!");
            return;
        }
        //--------------------------------------------------------------
        String nameCustomer = Validation.convertNullString(DataInput.getString("Enter new name please : "));
        //--------------------------------------------------------------
        String emailCustomer = Validation.convertNullString(DataInput.getString("Enter new email please : "));
        //--------------------------------------------------------------
        String addressCustomer = Validation.convertNullString(DataInput.getString("Enter new address please : "));
        //--------------------------------------------------------------
        Date birthdayCustomer = DataInput.getDate("Enter new birthday please : ");
        //--------------------------------------------------------------
        String nationality = Validation.convertNullString(DataInput.getString("Enter new your nationality please : "));
        //--------------------------------------------------------------
       
        Customer newCustomer = new Customer(newIDReservation,nationality,oldCustomer.getStatus(),newFlightNumber,emailCustomer,nameCustomer,addressCustomer,birthdayCustomer);
        //--------------------------------------------------------------
        listCustomer.update(newCustomer, IDReservation,seatChoice);
    }
    //--------------------------------------------------------
    public void deleteCustomer(){
         //---------------------------------------------------
        String IDReservation = Validation.removeBlankString(DataInput.getString("Enter id resrevation : "));
        //---------------------------------------------------
        if(!listCustomer.checkDuplicateCustomer(IDReservation) || !Validation.checkStringEmpty(IDReservation)){
            System.out.println("Customer does not exist!");
            return;
        }
        //---------------------------------------------------
        listCustomer.displayCustomerHeadTable();
        String seatChoice = listCustomer.displayCustomer(IDReservation); 
        listCustomer.deleteOnIDReservation(IDReservation,seatChoice);
    }
    //--------------------------------------------------------
    public void saveToFile(){
        listCustomer.saveToFile();
    }
    //--------------------------------------------------------
    public void displayAll(){
        listCustomer.printAll();
    }
}
