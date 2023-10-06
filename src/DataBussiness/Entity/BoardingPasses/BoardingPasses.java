/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Entity.BoardingPasses;

import DataBussiness.Validation.Validation;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author nhutm
 */
public class BoardingPasses {
    private String IDReservation;
    private String name;
    private String flightNumber;
    private String departureLocation;
    private String arrivalLocation;
    private Date departureTime;
    private String seat;
    
    public BoardingPasses(){
        
    }
    public BoardingPasses(String IDReservation, String name, String flightNumber, String departureLocation, String arrivalLocation, Date departureTime, String seat) {
        this.IDReservation = IDReservation;
        this.name = name;
        this.flightNumber = flightNumber;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureTime = departureTime;
        this.seat = seat;
    }
    
    public String getIDReservation() {
        return IDReservation;
    }

    public void setIDReservation(String IDReservation) {
        this.IDReservation = IDReservation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getBoardingTime(){
        return Validation.getHoursBoardingTimeFormat(departureTime);
    }
    @Override
    public String toString() {
        return   IDReservation + ", " + name + ", " + flightNumber + ", " + departureLocation + ", " + arrivalLocation + ", " + Validation.executeNullDate(departureTime) + ", " + seat ;
    }
    public void display() throws ParseException{        
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("\t AIRLINE NONAME \t\t\t\t\t BOARDING PASS");
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.format("\t NAME: %S \n",getName().toUpperCase());
        System.out.format("\t FROM: %s \t\t TO: %s \n",getDepartureLocation(),getArrivalLocation());
        System.out.format("\t FLIGHT: %s \t\t DATE: %s \t\t SEAT: %s \n",getFlightNumber(),Validation.geFormatDateInBoarding(departureTime),getSeat());
        System.out.format("\t BOARDING TIME : %s \n",getBoardingTime());
        System.out.println("---------------------------------------------------------------------------------------------");
    }
    
}
