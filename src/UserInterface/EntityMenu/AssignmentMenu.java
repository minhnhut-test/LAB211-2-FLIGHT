/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.EntityMenu;

import DataBussiness.Entity.Assignment.Assignment;
import DataBussiness.Services.AssignmentServices.AssignmentServices;
import DataBussiness.Services.AssignmentServices.IAssignmentServices;
import DataBussiness.Validation.DataInput;
import DataBussiness.Validation.Validation;
import java.util.List;

/**
 *
 * @author nhutm
 */
public class AssignmentMenu {
    static IAssignmentServices listAssignment = new AssignmentServices();

    public static IAssignmentServices getListAssignment() {
        return listAssignment;
    }
    
    //------HỆ THỐNG PHÂN CÔNG----------------------------------------------
    
    public void assignmentCrew() throws Exception{
        listAssignment.getStatusAssignmentFlight(FlightMenu.flightList.getAll());
        
        String lightNumber = DataInput.getString("Enter flight number : ");
        if(!FlightMenu.flightList.checkDuplicate(lightNumber, FlightMenu.flightList.getAll()) || !Validation.checkStringEmpty(lightNumber)){
            System.out.println("Flight does not exist");
            return;
        }
        //-----------Display trình trạng chuyến bay đó----------------------------
        listAssignment.displayAssignment(lightNumber);
        //-----------------------------------------------------------------
       System.out.println("Assignment position : ");
       int choicePosition = DataInput.getIntegerNumber("1. PILOTS  | 2. FLIGHT ATTENDANTS  | 3. GROUND STAFF \n");
       if(choicePosition > 3 || choicePosition <1 ){
             System.out.println("Choice is not correct!");
             return;
       }
       
       CrewMenu.crewList.displayList(CrewMenu.crewList.get(choicePosition,lightNumber));
       String idCrew = DataInput.getString("Enter id employee : ");
       //-----------------------------------------------------------------------------------------------
        if(!CrewMenu.crewList.checkDuplicate(idCrew,CrewMenu.crewList.get(choicePosition,lightNumber))){
            System.out.println("Employee does not exist "); 
            return;
        }
       //---------Khởi tạo assginment-------------------------------------------------------------
       Assignment assignment = new Assignment(lightNumber, idCrew, Validation.getTypeOfCrew(choicePosition),FlightMenu.flightList.get(lightNumber).getDepartureTime());
       String choice = DataInput.getString("Do you want to add assignment ? Y/N");
            if(!("Y".equals(choice))){
               System.out.println("Add assignment fail!");
               return;
        }
       listAssignment.add(assignment,idCrew);
       //-----------Display lại kết quả thực hiện---------------------------
       listAssignment.displayAssignment(lightNumber);
    }

    //------update ----------------------------------------------
    public void updateAssignmentCrew() throws Exception{
        List<String> flightNumberList =  listAssignment.getFlightHaveAssignment(FlightMenu.flightList.getAll());
        //---------------------------------------------------------------------
        String flightNumber = DataInput.getString("Enter flight number : ");
        if( !Validation.checkStringInListString(flightNumberList, flightNumber)|| !Validation.checkStringEmpty(flightNumber)){
            System.out.println("Flight does not correct");
            return;
        }

        //-----------Display trình trạng chuyến bay đó----------------------------
        listAssignment.displayAssignment(flightNumber);
        //-----------IDCrew cần thay thế----------------------------
        String oldIDCrew = DataInput.getString("IDCrew is replaced :");
        
        //---------------------------------------------------------
        if(!Validation.checkStringEmpty(oldIDCrew)){
            System.out.println("IDCrew must not null !");
            return;
        }
        //---------------------------------------------------------
        if(!listAssignment.checkDuplicate(flightNumber, oldIDCrew)){
            System.out.println("This ID crew does not exists in this flight!");
            return;
        }
       
        
        listAssignment.update(flightNumber, oldIDCrew);
    }
    //------delete ----------------------------------------------
    public void delete() throws Exception{
        List<String> flightNumberList =  listAssignment.getFlightHaveAssignment(FlightMenu.flightList.getAll());
        //---------------------------------------------------------------------
        String flightNumber = DataInput.getString("Enter flight number : ");
        if( !Validation.checkStringInListString(flightNumberList, flightNumber)|| !Validation.checkStringEmpty(flightNumber)){
            System.out.println("Flight does not correct");
            return;
        }
        //-----------Display trình trạng chuyến bay đó----------------------------
        listAssignment.displayAssignment(flightNumber);
        //------------------------------------------------------------------------
        int choiceDelete = DataInput.getIntegerNumber("1 delete all flight | 2 delete a staff".toUpperCase());
        if(choiceDelete !=1 && choiceDelete != 2){
            System.out.println("Choice is not correct!");
            return;
        }
        //------------------------------------------------------------------------
        listAssignment.delete(flightNumber,choiceDelete);
    }
    public void displayAll(){
        listAssignment.printAll();
    }
    public void saveToFile(){
        listAssignment.saveToFile();
    }
}
