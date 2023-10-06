/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Services.AssignmentServices;

import DataBussiness.Entity.Assignment.Assignment;
import DataBussiness.Entity.Flight.Flight;
import DataBussiness.Entity.Person.Crew;
import DataBussiness.Services.IServices;
import java.util.List;

/**
 *
 * @author nhutm
 */
public interface IAssignmentServices extends IServices<Assignment> {
    //------------------------------------------
    void update(Assignment assignment);
    //-------Thao tác xóa ở assignment menu-----------------------------------
    boolean delete(String flightNumber, int choice);
    //-----------------------------------------
    boolean add(Assignment assignment,String idCrew);
    //------------------------------------------
    void update(String flightNumber, Flight flight);
    //---------Xử lý đồng bộ vs flight ---------------------------------
    void delete(String flightNumber);
    //------------------------------------------
    void update(Crew crew,String oldCrew);
    //------------------------------------------
    void update(String flight,String oldCrew);
    //-------Xử lý đồng bộ vs Crew-----------------------------------
    void delete(Crew crew);
    //-------------Trả về người dùng thông tin tất phi hành đoàn về chuyến bay đó-----------------------------
    List<Assignment> get(String flightNumber);
    //-------------------------------------------
    boolean checkIDcrewAvailable(String flightNumber, String idCrew);
    //-------------------------------------------
    void displayAssignment(String flightNumber);
    //--------------------------------------------
    boolean checkDuplicate(String flightNumber,String idCrew);
    //--------------------------------------------
    void getStatusAssignmentFlight(List<Flight> list);
    //--------------------------------------------
    List<String> getFlightHaveAssignment(List<Flight> list);
    //-------------------------------------------------
    void displayConfirmUpdate(String flightNumber, String oldIDCrew,String newIDCrew);
    
}
