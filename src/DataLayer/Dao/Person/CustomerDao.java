/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.Dao.Person;

import DataBussiness.Entity.Flight.Flight;
import DataBussiness.Entity.Person.Customer;
import DataLayer.FileManager.CustomerFile.CustomerFile;
import DataLayer.FileManager.IFileManager;
import DataBussiness.Validation.ConstantValue.LinkFile;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author nhutm
 */
public class CustomerDao implements ICustomerDao{
    List<Customer> customerList = new ArrayList<>();
    IFileManager<Customer> customerFile = new CustomerFile();

    public CustomerDao() {
        loadDataFromFile();
    }
    
    @Override
    public void loadDataFromFile() {
        this.customerList = customerFile.readDataFromFile(LinkFile.CUSTOMER_URL.getFileURL());
    }

    @Override
    public void saveDataToFile() {
        customerFile.storeDataToFile(LinkFile.CUSTOMER_URL.getFileURL(), customerList);
    }


    @Override
    public List<Customer> getAll() {
        return customerList;
    }

    @Override
    public int getIndex(String IDReservation, String flightNumber) {
       for(int i =0 ; i < customerList.size(); i++){
           if(customerList.get(i).getIDReservation().equals(IDReservation) && customerList.get(i).getFlightNumber().equals(flightNumber)){
               return i;
           }
       }
       return -1;
    }

    @Override
    public Customer get(String IDReservation,String flightNumber) {
        for(Customer customer : customerList){
            if(customer.getIDReservation().equals(IDReservation) && customer.getFlightNumber().equals(flightNumber)){
                return customer;
            }
        }
        return null;
    }

    @Override
    public void update(Customer newCustomer, String oldIDCustomer) {
        customerList.stream().filter((customer) -> (customer.getIDReservation().equals(oldIDCustomer))).map((customer) -> {
            customer.setName(newCustomer.getName());
            return customer;
        }).map((customer) -> {
            customer.setEmail(newCustomer.getEmail());
            return customer;
        }).map((customer) -> {
            customer.setAddress(newCustomer.getAddress());
            return customer;
        }).map((customer) -> {
            customer.setBirthday(newCustomer.getBirthday());
            return customer;
        }).map((customer) -> {
            customer.setFlightNumber(newCustomer.getFlightNumber());
            return customer;
        }).map((customer) -> {
            customer.setIDReservation(newCustomer.getIDReservation());
            return customer;
        }).map((customer) -> {
            customer.setStatus(newCustomer.getStatus());
            return customer;
        }).forEachOrdered((customer) -> {
            customer.setNationality(newCustomer.getNationality());
        });
    }


    @Override
    public void add(Customer customer) {
        customerList.add(customer);
    }
    
    @Override
    public Customer getNonCheckIn(String IDReservation) {
        for(Customer customer : customerList){
            if(customer.getIDReservation().equals(IDReservation) && (customer.getStatus().equals("RESERVATION")|| customer.getStatus().equals("TICKET-ONLINE"))){
                return customer;
            }
        }
        return null;
    }

    @Override
    public void update(String flightNumber, Flight flight) {
        customerList.stream().filter((customer) -> (customer.getFlightNumber().equals(flightNumber))).forEachOrdered((customer) -> {
            customer.setFlightNumber(flight.getFlightNumber());
        });
    }

 
    @Override
    public void delete(Customer customer) {
        customerList.remove(customer);
    }

    @Override
    public List<Customer> get(String flightNumber) {
        List<Customer> list = new ArrayList<>();
        for(Customer customer : customerList){
            if(customer.getFlightNumber().equals(flightNumber)){
                list.add(customer);
            }
        }
        return list;
    }
    
    @Override
    public Customer getACustomer(String IDReservation){
        for(Customer customer : customerList){
            if(customer.getIDReservation().equals(IDReservation)){
                return customer;
            }
        }
        return null;
    }
}
