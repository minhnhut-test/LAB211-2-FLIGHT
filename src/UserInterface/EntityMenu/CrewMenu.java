/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.EntityMenu;

import DataBussiness.Entity.Person.Crew;
import DataBussiness.Services.CrewServices.CrewServices;
import DataBussiness.Services.CrewServices.ICrewServices;
import DataBussiness.Validation.DataInput;
import DataBussiness.Validation.Validation;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author nhutm
 */
public class CrewMenu {
    static ICrewServices crewList = new CrewServices();
    //------Management Crew--------------------------------

    public static ICrewServices getCrewList() {
        return crewList;
    }
    
    //---------------------------------
    public void add() throws ParseException, Exception{
        //--------------------------------------------------------------
        String nameCrew = Validation.convertNullString(DataInput.getString("Enter name please : "));
        //-------------------------------------------------------------
        if(nameCrew.equals("N/A")){
            System.out.println("Name must not null ");
            return;
        }
        //--------------------------------------------------------------
        String emailCrew = Validation.convertNullString(DataInput.getString("Enter email please : "));
        //--------------------------------------------------------------
        String addressCrew = Validation.convertNullString(DataInput.getString("Enter address please : "));
        //--------------------------------------------------------------
        Date birthdayCrew = DataInput.getDate("Enter birthday please : ");
        //--------------------------------------------------------------
        System.out.print("Choice type of employee: ");
        int typeCrew = DataInput.getIntegerNumber("1. PILOTS \t | 2. FLIGHT ATTENDANTS \t | 3. GROUND STAFF");
        //--------------------------------------------------------------
         if(typeCrew > 3 || typeCrew <1 ){
             System.out.println("Choice is not correct!");
             return;
         }   
        //--------------------------------------------------------------
        Crew crew = new Crew(Validation.getIDAuto("E",Crew.getAutoIDRservation()), typeCrew, emailCrew, nameCrew, addressCrew, birthdayCrew);
        //--------------------------------------------------------------
        crewList.add(crew);
    }
    //---------------------------------
    public void update() throws ParseException, Exception{
        //--------------------------------------------------------------
        String IDCrew = Validation.removeBlankString(DataInput.getString("Enter id employee : "));
        //---------------------------------
        if(!crewList.checkDuplicate(IDCrew,crewList.getAll())){
            System.out.println("Employee does not exist "); 
            return;
        }
        //---------------------------------
        String newIDCrew = Validation.removeBlankString(DataInput.getString("Enter new id employee : "));
        //---------------------------------
        if(crewList.checkDuplicate(newIDCrew,crewList.getAll())){
            System.out.println("ID employee is duplicate "); 
            return;
        }
        //--------------------------------------------------------------
        String nameCrew = Validation.convertNullString(DataInput.getString("Enter new name please : "));
        //--------------------------------------------------------------
        String emailCrew = Validation.convertNullString(DataInput.getString("Enter new email please : "));
        //--------------------------------------------------------------
        String addressCrew = Validation.convertNullString(DataInput.getString("Enter new address please : "));
        //--------------------------------------------------------------
        Date birthdayCrew = DataInput.getDate("Enter new birthday please : ");
        //--------------------------------------------------------------
        System.out.println("Choice new type of employee : ");
        String typeCrewS = DataInput.getString("1. PILOTS \t | 2. FLIGHT ATTENDANTS \t | 3. GROUND STAFF");
        
        int typeCrew;
        if(Validation.removeBlankString(typeCrewS).isEmpty()){
            typeCrew = 0;
        }else {
            typeCrew = Integer.parseInt(typeCrewS);
            if(typeCrew <1 || typeCrew > 3) {
                System.out.println("Choice is not correct!");
                return;
            }
        }
      
        //--------------------------------------------------------------
        Crew crew = new Crew(newIDCrew, typeCrew, emailCrew, nameCrew, addressCrew, birthdayCrew);
        //--------------------------------------------------------------
        crewList.update(crew, IDCrew);
        //--------Xử lý đồng bộ với assignment------------------------------------------------------
        
    }
    //---------------------------------
    public void delete(){
        //--------------------------------------------------------------
        String IDCrew = Validation.removeBlankString(DataInput.getString("Enter id employee : "));
        //---------------------------------
        if(!crewList.checkDuplicate(IDCrew,crewList.getAll())){
            System.out.println("Employee does not exist "); 
            return;
        }
        //---------Display crew------------------------
        CrewMenu.crewList.printHeadTableFull();
        crewList.get(IDCrew).displayFull();
        //---------------------------------
        String choice = DataInput.getString("Do you want to delete it ? Y/N");
        if(!("Y".equals(choice))){
            System.out.println("Delete fail!");
            return;
        }
        System.out.println("Delete success!");
        crewList.delete(crewList.get(IDCrew));
        
        
    }
    //--------------------------------------------------------------
    public void displayAll(){
     
        crewList.printAll();
    }
 
    //--------------------------------------------------------------
    public void saveToFile(){
        crewList.saveToFile();
    }
}
