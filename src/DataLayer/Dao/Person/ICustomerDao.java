/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.Dao.Person;


import DataBussiness.Entity.Flight.Flight;
import DataBussiness.Entity.Person.Customer;

import DataLayer.Dao.IUserDao;
import java.util.List;


/**
 *
 * @author nhutm
 */
public interface ICustomerDao extends IUserDao<Customer> {
    //------------------------------------------
    int getIndex(String IDReservation,String flightNumber);
    //------------------------------------------
    Customer get(String IDReservation, String flightNumber);
    //-------------------------------------------
    List<Customer> get(String flightNumber);
    //------------------------------------------
    Customer getNonCheckIn(String IDReservation);
    //------------------------------------------
    void update(Customer newCustomer,String oldIDReservation);
    //-----------------------------------------
    void add(Customer customer);
    //------------------------------------------
    void update(String flightNumber, Flight flight);
    //------------------------------------------
    void delete(Customer customer);
    //-----------------------------------------
    Customer getACustomer(String IDReservation);
}
