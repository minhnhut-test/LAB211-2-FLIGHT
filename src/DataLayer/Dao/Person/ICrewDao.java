/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.Dao.Person;



import DataBussiness.Entity.Person.Crew;


import DataLayer.Dao.IUserDao;
import java.util.List;


/**
 *
 * @author nhutm
 */
public interface ICrewDao extends IUserDao<Crew>{
    //------------------------------------------
    int getIndex(String IDCrew);
    //------------------------------------------
    Crew get(String IDCrew);
    //------------------------------------------
    List<Crew> get(int position);
    //------------------------------------------
    List<Crew> getCheckSimilar(Crew crew);
    //------------------------------------------
    void update(Crew newCrew, String oldIDCrew);
    //------------------------------------------
    void delete(Crew crew);
    //-----------------------------------------
    void add(Crew crew);
    //-----------------------------------------
    void displayAll();
    //-----------------------------------------
    boolean checkTypeEmployee(String IDCrew, int type);
     
}
