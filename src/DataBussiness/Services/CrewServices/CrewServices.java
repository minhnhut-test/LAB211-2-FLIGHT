/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Services.CrewServices;

import DataBussiness.Entity.Assignment.Assignment;
import DataBussiness.Entity.Person.Crew;
import DataBussiness.Validation.DataInput;
import DataLayer.Dao.Person.ICrewDao;
import DataLayer.DaoFactory.DaoFactory;
import DataLayer.DaoFactory.IDaoFactory;
import UserInterface.EntityMenu.AssignmentMenu;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nhutm
 */
public class CrewServices implements ICrewServices{
    ICrewDao crewAction;
    IDaoFactory crewDaoFactory;
    
    public CrewServices(){
        crewDaoFactory = new DaoFactory();
        crewAction = crewDaoFactory.crewDao();
    }
    
    @Override
    public List<Crew> getAll() {
        return crewAction.getAll();
    }

    @Override
    public void printAll() {
        this.printHeadTableFull();
        crewAction.displayAll();
    }

    @Override
    public void saveToFile() {
        crewAction.saveDataToFile();
    }

    @Override
    public Crew get(String IDCrew) {
        return crewAction.get(IDCrew);
    }

    @Override
    public boolean checkDuplicate(String IDCrew, List<Crew> list) {
        return list.stream().anyMatch((crew) -> (crew.getIDCrew().equals(IDCrew)));
    }
    
    @Override
    public void printHeadTableFull(){
        for(int i =0 ; i <=184; i++ )System.out.print("-");
        System.out.println("");
        System.out.printf("|  %-15s  |  %-20s  |  %-40s  |  %-20s  |  %-40s  | %-20s  |\n","ID","NAME","EMAIL","BIRTHDAY","ADDRESS","TYPE");
        for(int i =0 ; i <=184; i++ )System.out.print("-");
        System.out.println("");
    }
    
    @Override
    public void add(Crew crew) {
        List<Crew> list = crewAction.getCheckSimilar(crew);
        if(!list.isEmpty()){
            for(int i =0 ; i <=184; i++ )System.out.print("-");
            System.out.println("\n  WARING: THE EMPLOYEE SIMILAR TO: ");
            this.printHeadTableFull();
            for(Crew tmp : list){
                tmp.displayFull();
            }
        }
        //--------------------------------------------------------------
        for(int i =0 ; i <=184; i++ )System.out.print("-");
        System.out.println("\n  THE EMPLOYEE WILL BE ADDED: ");
        this.printHeadTableFull();
        crew.displayFull();
        //--------------------------------------------------------------
        String choice = DataInput.getString("Do you want to add employee ? Y/N");
            if(!("Y".equals(choice))){
               System.out.println("Add fail!");
               return;
        }
        crewAction.add(crew);
    }

    @Override
    public void update(Crew newCrew, String oldID) {
        Crew oldCrew = crewAction.get(oldID);
        if(newCrew.getIDCrew().isEmpty()){
            newCrew.setIDCrew(oldID);
        }
        if(newCrew.getName().equals("N/A")){
            newCrew.setName(oldCrew.getName());
        }
        if(newCrew.getEmail().equals("N/A")){
            newCrew.setEmail(oldCrew.getEmail());
        }
        if(newCrew.getAddress().equals("N/A")){
            newCrew.setAddress(oldCrew.getAddress());
        }
        if(newCrew.getBirthday() == null){
            newCrew.setBirthday(oldCrew.getBirthday());
        }
        if(newCrew.getType() == 0){
            newCrew.setType(oldCrew.getType());
        }
        //------------------------------------------------------------
        for(int i =0 ; i <=184; i++ )System.out.print("-");
        System.out.println("\n  AFTER UPDATE: ");
        this.printHeadTableFull();
        newCrew.displayFull();
        //--------------------------------------------------------------
        String choice = DataInput.getString("Do you want to update employee ? Y/N");
            if(!("Y".equals(choice))){
               System.out.println("Update fail!");
               return;
        }
        //------Xử lý đồng bộ với assignment-----------------------------------
       AssignmentMenu.getListAssignment().update(newCrew, oldID);
       crewAction.update(newCrew, oldID);
    }

    @Override
    public void delete(Crew crew) {
        //------Xử lý đồng bộ với assignment-----------------------------------
       AssignmentMenu.getListAssignment().delete(crew);
       crewAction.delete(crew);
    }

    @Override
    public boolean checkTypeEmployee(String IDCrew, int type) {
        if(crewAction.checkTypeEmployee(IDCrew, type)){
            return true;
        }else{
            System.out.println("The employee does not meet the position !");
            return false;
        }
    }

    @Override
    public void printHeadTable() {
        for(int i =0 ; i <=114; i++ )System.out.print("-");
        System.out.println("");
        System.out.printf("|  %-15s  |  %-20s  |  %-40s  | %-20s  |\n","IDCREW","NAME","EMAIL","TYPE");
        for(int i =0 ; i <=114; i++ )System.out.print("-");
        System.out.println("");
    }

    @Override
    public List<Crew> get( int position,String flightNumber) {
        List<Crew> list = new ArrayList<>();
        if(AssignmentMenu.getListAssignment().get(flightNumber).isEmpty()){
            list =  crewAction.get(position);
        }else{
            for(Assignment assignment :  AssignmentMenu.getListAssignment().get(flightNumber)){
            for(Crew crew : crewAction.get(position)){
                if(!crew.getIDCrew().equals(assignment.getIDCrew())){
                    boolean check = true;
                    for(Crew listCrew : list){
                        if(listCrew.getIDCrew().equals(crew.getIDCrew())){
                            check = false;
                        }
                    }
                    if(check) list.add(crew);
                    
                    }
            }
            }
        }
        for(int i  =0 ; i < list.size(); i++){
            if(!AssignmentMenu.getListAssignment().checkIDcrewAvailable(flightNumber,list.get(i).getIDCrew())){
                list.remove(i);
            }
        }
        return list;
    }

    @Override
    public void displayList(List<Crew> crews) {
        this.printHeadTable();
        crews.forEach((crew) -> {
            crew.display();
        });
    }
    
}
