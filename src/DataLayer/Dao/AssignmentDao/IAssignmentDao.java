/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.Dao.AssignmentDao;

import DataBussiness.Entity.Assignment.Assignment;
import DataBussiness.Entity.Flight.Flight;
import DataBussiness.Entity.Person.Crew;
import DataLayer.Dao.IUserDao;
import java.util.List;
;

/**
 *
 * @author nhutm
 */
public interface IAssignmentDao extends IUserDao<Assignment> {
    //--------------Trả về đối tượng assignment bao gồm----------------------------
    Assignment get(String IDCrew, String flightNumber);
    //-------------Trả về người dùng thông tin tất phi hành đoàn về chuyến bay đó-----------------------------
    List<Assignment> get(String flightNumber);
    //-------------Trả về người dùng thông tin tất phi hành đoàn về chuyến bay đó-----------------------------
    List<Assignment> getOnIDCrew(String idCrew);
    //------------------------------------------
    void update(Assignment assignment);
    //------------------------------------------
    void update(Crew crew,String oldCrew);
    //------------------------------------------
    void update(String flightNumber, Flight flight);
    //------------------------------------------
    boolean delete(Assignment assignment);
    //------------------------------------------
    void delete(Crew crew);
    //-----------------------------------------
    boolean add(Assignment assignment);
    //------------------------------------------
    void update(String flight,String oldCrew,String newCrew);
    
}
