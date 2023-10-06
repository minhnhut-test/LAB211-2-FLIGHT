/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Entity.Flight;

import DataBussiness.Validation.Validation;
import UserInterface.EntityMenu.FlightMenu;
import java.util.Date;


/**
 *
 * @author nhutm
 */
public class Flight {
    private String flightNumber;
    private String departureLocation;
    private String arrivalLocation;
    private Date departureTime;
    private Date arrivalTime;
    private int duration;
    private int availableSeats;
                                                                                                      
    
    public Flight(String flightNumber, String departureLocation, String arrivalLocation, Date departureTime, Date arrivalTime, int availableSeats) {
        this.flightNumber = flightNumber;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableSeats = availableSeats;
        //set duration
        this.duration = Validation.changeToDurationTime(departureTime, arrivalTime);
    }
    
    
    public Flight(String flightNumber, String departureLocation, String arrivalLocation, Date departureTime, Date arrivalTime, int duration, int availableSeats) {
        this.flightNumber = flightNumber;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.availableSeats = availableSeats;
    }
    
    public static int getAutoIDRservation() {
       int autoIDRservation = 0;
       int max = autoIDRservation;
       for(Flight flight :  FlightMenu.getFlightList().getAll()){
           int tmp = Integer.parseInt(flight.getFlightNumber().substring(1));
           if(tmp > max) max = tmp;
       }
        autoIDRservation = max +1;
        return autoIDRservation;
    }
    
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return   flightNumber + ", " + departureLocation + ", " + arrivalLocation + ", " + Validation.executeNullDate(departureTime) + ", " + Validation.executeNullDate(arrivalTime) + ", " + duration + ", " + availableSeats ;
    }
    
    public void display(){
        System.out.printf("|  %-15s  |  %-18s  |  %-18s  |  %-17s  |  %-17s  |  %-8s  |  %-15s  |\n",flightNumber.trim(),departureLocation.trim(),arrivalLocation.trim(),Validation.executeNullDate(departureTime).trim(),Validation.executeNullDate(arrivalTime).trim(),Validation.printOutDuration(duration).trim(),availableSeats);
        for(int i = 0; i <= 143 ; i++)System.out.print("-");
        System.out.println("");
    }
    
    
}
