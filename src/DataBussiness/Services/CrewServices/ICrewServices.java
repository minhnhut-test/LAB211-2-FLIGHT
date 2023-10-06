/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Services.CrewServices;

import DataBussiness.Entity.Flight.Flight;
import DataBussiness.Entity.Person.Crew;
import DataBussiness.Services.IServices;
import java.util.List;

/**
 *
 * @author nhutm
 */
public interface ICrewServices extends IServices<Crew>{
    //------------------------------------------
    Crew get(String IDCrew);
    //------------------------------------------
    boolean checkDuplicate(String IDCrew, List<Crew> list);
    //------------------------------------------
    void add(Crew crew);
    //------------------------------------------
    void update(Crew newCrew, String oldID);
    //------------------------------------------
    void delete(Crew crew);
    //-----------------------------------------
    boolean checkTypeEmployee(String IDCrew, int type);
    //------------------------------------------
    List<Crew> get( int position, String flightNumber);
    //----------------------------------
    void displayList(List<Crew> crew);
    //-----------------------------------
    void printHeadTableFull();
     
}
