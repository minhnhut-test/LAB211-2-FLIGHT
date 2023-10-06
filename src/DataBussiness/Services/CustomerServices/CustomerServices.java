/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Services.CustomerServices;

import DataBussiness.Entity.BoardingPasses.BoardingPasses;
import DataBussiness.Entity.Flight.Flight;
import DataBussiness.Entity.Person.Customer;
import DataBussiness.Entity.Seat.Seat;
import DataBussiness.Validation.DataInput;
import DataBussiness.Validation.Validation;
import DataLayer.Dao.Person.ICustomerDao;
import DataLayer.DaoFactory.DaoFactory;
import DataLayer.DaoFactory.IDaoFactory;
import UserInterface.EntityMenu.BoardingPassesMenu;
import UserInterface.EntityMenu.FlightMenu;
import UserInterface.EntityMenu.SeatMenu;
import java.util.List;

/**
 *
 * @author nhutm
 */
public class CustomerServices implements ICustomerServices{
    ICustomerDao customerAction;
    IDaoFactory customerDaoFactory;

    public CustomerServices() {
        customerDaoFactory = new DaoFactory();
        customerAction = customerDaoFactory.customerDao();
    }
    
    @Override
    public List<Customer> getAll() {
       return customerAction.getAll();
    }

    @Override
    public void printAll() {
        this.printHeadTable();
        customerAction.getAll().forEach((customer) -> {
            customer.display();
        });
    }

    @Override
    public void saveToFile() {
        customerAction.saveDataToFile();
    }

    @Override
    public void add(Customer customer) {
        //--------Online ticket------------------------------------------------------
        String choiceOnlineTicket = DataInput.getString("Do you want to choose your seat location ? You will pay an additional fee for this ? Y/N : ");
        if(("Y".equals(choiceOnlineTicket.toUpperCase()))){
            SeatMenu.getSeatList().displayAvailabilitySeats(customer.getFlightNumber());
            String seatChoice = DataInput.getString("\n Enter your choice : ");
            if(!SeatMenu.getSeatList().addSeatChoice(customer.getFlightNumber(), seatChoice, customer.getIDReservation())){
                System.out.println("Your seat choice is invalid!");
                return;
            }
            customer.setStatus("TICKET-ONLINE");
        }
        //-------confirm booking------------------------------------------------------
        String choice = DataInput.getString("Do you want to booking it ? Y/N");
        if(!("Y".equals(choice))){
            System.out.println("Booking fail!");
            return;
        }
         //-----------Xử lý đồng bộ dữ liệu----------------------------------------------------
        FlightMenu.getFlightList().get(customer.getFlightNumber()).setAvailableSeats(FlightMenu.getFlightList().get(customer.getFlightNumber()).getAvailableSeats()-1);
        customerAction.add(customer);
        //-----------Xác nhận IDReservation với khách hàng----------------------
        System.out.println("Your IDReservation : " + customer.getIDReservation());
        System.out.println("Note: Please keep it to check in!");
    }

    @Override
    public Customer get(String IDReservation) {
        return customerAction.getNonCheckIn(IDReservation);
    }

    @Override
    public void update(String flightNumber, Flight flight) {
        customerAction.update(flightNumber, flight);
    }

    @Override
    public void delete(String flightNumber) {
        List<Customer> list = customerAction.get(flightNumber);
        for(Customer customer : list){
            customerAction.delete(customer);
        }
    }

    @Override
    public void printHeadTable() {
        for(int i =0; i <= 104 ; i++)System.out.print("-");
        System.out.println("");
        System.out.printf("|  %-15s  |  %-17s  |  %-20s  |  %-12s  |  %-15s  |\n","IDRESERVATION","FLIGHT NUMBER","NAME","NATIONALITY","STATUS");
        for(int i =0; i <= 104 ; i++)System.out.print("-");
        System.out.println("");
    }

    @Override
    public String displayCustomer(String IDReservation) {
        //------------------------------------------------------
        Customer customer = get(IDReservation);
        //------------------------------------------------------
        String seatChoice = SeatMenu.getSeatList().getSeatChoice(customer.getFlightNumber(), IDReservation);
        if(seatChoice ==null){
            seatChoice ="N/A";
        }else{
            seatChoice = Validation.convertNullString(seatChoice);
        }
        //------------------------------------------------------
        for(int i = 0; i <= 216; i++)System.out.print("-");
        System.out.println("");
        System.out.printf("|  %-15s  |  %-15s  |  %-20s  |  %-22s  |  %-37s  |  %-18s  |  %-15s  |  %-20s  |  %-10s  |\n",customer.getIDReservation(),customer.getFlightNumber(),customer.getName(),customer.getEmail(),customer.getAddress(),Validation.executeNullDate(customer.getBirthday()),customer.getNationality(),customer.getStatus(),seatChoice);
        for(int i = 0; i <= 216; i++)System.out.print("-");
        System.out.println("");
        //-------------------------------------------------------
        return seatChoice;
    }
    
    @Override
    public void displayCustomerHeadTable(){
        for(int i = 0; i <= 216; i++)System.out.print("-");
        System.out.println("");
        System.out.printf("|  %-15s  |  %-15s  |  %-20s  |  %-22s  |  %-37s  |  %-18s  |  %-15s  |  %-20s  |  %-10s  |\n","IDRESREVATION","FLIGHT NUMBER","NAME","EMAIL","ADDRESS","BIRTHDAY","NATIONALITY","STATUS","SEAT");
        for(int i = 0; i <= 216; i++)System.out.print("-");
        System.out.println("");
    }

    @Override
    public void update(Customer customer, String oldIDR,String seatChoice) {
        Customer oldCustomer = this.get(oldIDR);
        String newSeat = seatChoice;
        if(customer.getIDReservation().equals("N/A")){
            customer.setIDReservation(oldCustomer.getIDReservation());
        }
        if(customer.getFlightNumber().equals("N/A")){
            customer.setFlightNumber(oldCustomer.getFlightNumber());
        }else{
           if(!seatChoice.equals("N/A")){
               SeatMenu.getSeatList().get(oldCustomer.getFlightNumber()).getSeats().put(seatChoice, null);
               SeatMenu.getSeatList().displayAvailabilitySeats(customer.getFlightNumber());
               newSeat = DataInput.getString("\n Enter your new seat : ");
               if(!SeatMenu.getSeatList().addSeatChoice(customer.getFlightNumber(), newSeat, customer.getIDReservation())){
                System.out.println("Your seat choice is invalid!");
                return;
            }
              
           } 
           FlightMenu.getFlightList().get(oldCustomer.getFlightNumber()).setAvailableSeats(FlightMenu.getFlightList().get(oldCustomer.getFlightNumber()).getAvailableSeats()+1);
           FlightMenu.getFlightList().get(customer.getFlightNumber()).setAvailableSeats(FlightMenu.getFlightList().get(customer.getFlightNumber()).getAvailableSeats()-1);
        }
        if(customer.getName().equals("N/A")){
            customer.setName(oldCustomer.getName());
        }
        if(customer.getEmail().equals("N/A")){
            customer.setEmail(oldCustomer.getEmail());
        }
        if(customer.getAddress().equals("N/A")){
            customer.setAddress(oldCustomer.getAddress());
        }
        if(customer.getBirthday() == null){
            customer.setBirthday(oldCustomer.getBirthday());
        }
        if(customer.getNationality().equals("N/A")){
            customer.setNationality(oldCustomer.getNationality());
        }
        
        for(int i = 0; i <= 216; i++)System.out.print("-");
        System.out.println("");
        System.out.printf("|  %-15s  |  %-15s  |  %-20s  |  %-22s  |  %-37s  |  %-18s  |  %-15s  |  %-20s  |  %-10s  |\n",customer.getIDReservation(),customer.getFlightNumber(),customer.getName(),customer.getEmail(),customer.getAddress(),Validation.executeNullDate(customer.getBirthday()),customer.getNationality(),customer.getStatus(),seatChoice);
        for(int i = 0; i <= 216; i++)System.out.print("-");
        System.out.println("");
        
        String choice = DataInput.getString("Do you want to update it ? Y/N");
        if(!("Y".equals(choice))){
            System.out.println("Update fail!");
            return;
        }
        customerAction.update(customer, oldIDR);
    }

    @Override
    public boolean checkDuplicateCustomer(String IDReservation) {
        for(Customer customer : customerAction.getAll()){
            if(customer.getIDReservation().equals(IDReservation)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Customer getCustomer(String IDReservation) {
        return customerAction.getACustomer(IDReservation);
    }

    @Override
    public void deleteOnIDReservation(String IDReservation,String seatChoice) {
        Customer customer = customerAction.getACustomer(IDReservation);
        if(customer.getStatus().equals("TICKET-ONLINE")){
            SeatMenu.getSeatList().get(customer.getFlightNumber()).getSeats().put(seatChoice,null);
        }
        FlightMenu.getFlightList().get(customer.getFlightNumber()).setAvailableSeats(FlightMenu.getFlightList().get(customer.getFlightNumber()).getAvailableSeats()+1);
        customerAction.delete(customer);
    }
    
    
}
