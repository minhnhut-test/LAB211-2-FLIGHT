/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Entity.Person;

import UserInterface.EntityMenu.CustomerMenu;
import java.util.Date;

/**
 *
 * @author nhutm
 */
public class Customer extends Person{
    private String IDReservation;
    private String nationality;
    private String status;
    private String flightNumber;
  

   
    public Customer() {
       
    }

    public Customer(String IDReservation, String nationality, String status, String flightNumber, String email, String name, String address, Date birthday) {
        super(email, name, address, birthday);
        this.IDReservation = IDReservation;
        this.nationality = nationality;
        this.status = status;
        this.flightNumber = flightNumber;
    }
    
    public static int getAutoIDRservation() {
       int autoIDRservation = 0;
       int max = autoIDRservation;
       for(Customer customer :  CustomerMenu.getListCustomer().getAll()){
           int tmp = Integer.parseInt(customer.getIDReservation().substring(3));
           if(tmp > max) max = tmp;
       }
        autoIDRservation = max +1;
        return autoIDRservation;
    }
    
    public String getIDReservation() {
        return IDReservation;
    }

    public void setIDReservation(String IDReservation) {
        this.IDReservation = IDReservation;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    @Override
    public String toString() {
        return super.toString()+", "  + IDReservation + ", " + nationality + ", " + status + ", " + flightNumber ;
    }
    
    public void display(){
        System.out.printf("|  %-15s  |  %-17s  |  %-20s  |  %-12s  |  %-15s  |\n",getIDReservation(),getFlightNumber(),getName(),getNationality(),getStatus());
        for(int i =0; i <= 104 ; i++)System.out.print("-");
        System.out.println("");
    }
    
}
