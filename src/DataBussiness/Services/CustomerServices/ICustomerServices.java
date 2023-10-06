/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Services.CustomerServices;


import DataBussiness.Entity.Flight.Flight;
import DataBussiness.Entity.Person.Customer;
import DataBussiness.Services.IServices;

/**
 *
 * @author nhutm
 */
public interface ICustomerServices extends IServices<Customer>{
    void add(Customer customer);
    //------------------------------------------
    Customer get(String IDReservation);
    //------------------------------------------
    void update(String flightNumber, Flight flight);
    //------------------------------------------
    void update(Customer customer, String oldIDR,String seatChoice);
    //------------------------------------------
    void delete(String flightNumber);
    //------------------------------------------
    String displayCustomer(String IDReservation);
    //------------------------------------------
    void displayCustomerHeadTable();
     //------------------------------------------
    boolean checkDuplicateCustomer(String IDReservation);
    //----------------------------------------
    Customer getCustomer(String IDReservation);
    //--------------------------------------
     void deleteOnIDReservation(String IDReservation,String seatChoice);
}
