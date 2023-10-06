/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Entity.Assignment;

import DataBussiness.Validation.Validation;
import java.util.Date;

/**
 *
 * @author nhutm
 */
public class Assignment {
    private String flightNumber;
    private String IDCrew;
    private String position;
    private Date departureTime;
    
    public Assignment() {
        
    }
    
    public Assignment(String flightNumber, String IDCrew, String position, Date departureTime) {
        this.flightNumber = flightNumber;
        this.IDCrew = IDCrew;
        this.position = position;
        this.departureTime = departureTime;
    }
    
    public String getFlightNumber() {
        return flightNumber;
    }
    
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    
    public String getIDCrew() {
        return IDCrew;
    }
    
    public void setIDCrew(String IDCrew) {
        this.IDCrew = IDCrew;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
    public Date getDepartureTime() {
        return departureTime;
    }
    
    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }
    
    @Override
    public String toString() {
        return   flightNumber + ", " + IDCrew + ", " + position + ", " + Validation.executeNullDate(departureTime) ;
    }
    public void display(){
        System.out.printf("|  %-15s |  %-15s |  %-16s |  %-17s |\n",flightNumber,IDCrew,position,Validation.executeNullDate(departureTime));
        for(int i =0; i <= 79; i++) System.out.print("-");
        System.out.println("");
    }
}
