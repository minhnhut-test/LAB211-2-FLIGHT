/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Services.AssignmentServices;

import DataBussiness.Entity.Assignment.Assignment;
import DataBussiness.Entity.Flight.Flight;
import DataBussiness.Entity.Person.Crew;
import DataBussiness.Validation.Validation;
import DataBussiness.Validation.ConstantValue.Position;
import DataBussiness.Validation.DataInput;
import DataLayer.Dao.AssignmentDao.IAssignmentDao;
import DataLayer.DaoFactory.DaoFactory;
import DataLayer.DaoFactory.IDaoFactory;
import UserInterface.EntityMenu.CrewMenu;
import UserInterface.EntityMenu.FlightMenu;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nhutm
 */
public class AssignmentServices implements IAssignmentServices{
    IAssignmentDao assignmentAction;
    IDaoFactory assignmentDaoFactory;
    
    public AssignmentServices(){
        assignmentDaoFactory = new DaoFactory();
        assignmentAction = assignmentDaoFactory.asssignmentDao();
    }

    @Override
    public List<Assignment> getAll() {
        return assignmentAction.getAll();
    }

    @Override
    public void printAll() {
        this.printHeadTable();
       assignmentAction.getAll().forEach(element -> element.display());
    }

    @Override
    public void saveToFile() {
        assignmentAction.saveDataToFile();
    }

    @Override
    public void update(Assignment assignment) {
        assignmentAction.update(assignment);
    }

    @Override
    public boolean delete(String flightNumber, int choice) {
        switch(choice){
            case 1:
                   
                displayAssignment(flightNumber);
                String choiceConfirm = DataInput.getString("Do you want to update assignment ? Y/N");
                if(!("Y".equals(choiceConfirm))){
                    System.out.println("Delete assignment fail!");
                    return false;
                }  
                this.delete(flightNumber);
                return true;
            case 2:
                String idCrew = DataInput.getString("Enter id employee : ");
                if(!Validation.checkStringEmpty(idCrew)){
                    return false;
                }
                Assignment assignment = assignmentAction.get(idCrew, flightNumber);
                this.printHeadTable();
                assignment.display();
                String choiceConfirmCrew = DataInput.getString("Do you want to delete assignment ? Y/N");
                if(!("Y".equals(choiceConfirmCrew))){
                    System.out.println("Delete assignment fail!");
                    return false;
                }  
                return assignmentAction.delete(assignment);           
        }
        return false;
    }

    @Override
    public boolean add(Assignment assignment,String idCrew) {
        return assignmentAction.add(assignment);
    }

    @Override
    public void update(String flightNumber, Flight flight) {
        assignmentAction.update(flightNumber, flight);
    }

    @Override
    public void delete(String flightNumber) {
        List<Assignment> list = assignmentAction.get(flightNumber);
        for(Assignment assignment : list){
            assignmentAction.delete(assignment);
        }
    }

    @Override
    public void update(Crew crew, String oldCrew) {
        assignmentAction.update(crew, oldCrew);
    }

    @Override
    public void delete(Crew crew) {
        assignmentAction.delete(crew);
    }

    @Override
    public void printHeadTable() {
        for(int i =0; i <= 79; i++) System.out.print("-");
        System.out.println("");
        System.out.printf("|  %-15s |  %-15s |  %-16s |  %-17s |\n","FIGHT NUMBER","IDCREW","POSITION","DEPARTURE TIME");
        for(int i =0; i <= 79; i++) System.out.print("-");
        System.out.println("");
    }

    @Override
    public List<Assignment> get(String flightNumber) {
        return assignmentAction.get(flightNumber);
    }

    @Override
    public boolean checkIDcrewAvailable(String flightNumber, String idCrew) {
        //------------List assignment co idcrew
        List<Assignment> list = assignmentAction.getOnIDCrew(idCrew);
        Flight flight = FlightMenu.getFlightList().get(flightNumber);
        long departureTimeCompare = flight.getDepartureTime().getTime();
        long arrivalTimeCompare = flight.getArrivalTime().getTime();
        for(Assignment assignment : list){
            Flight tmp = FlightMenu.getFlightList().get(assignment.getFlightNumber());
            long depatureTime = tmp.getDepartureTime().getTime();
            long arrivalTime = tmp.getArrivalTime().getTime();
            if((departureTimeCompare >= depatureTime && departureTimeCompare <= arrivalTime) || (arrivalTimeCompare >= depatureTime && arrivalTimeCompare <= arrivalTime) ){              
                return false;
            }
        }
        return true;
    }

    @Override
    public void displayAssignment(String flightNumber) {
        Flight flight = FlightMenu.getFlightList().get(flightNumber);
        for(int j = 0; j <= 135; j++) System.out.print("-");
        System.out.println();
        System.out.printf("|  %-20s  |  %-20s  |  %-20s  |  %-25s  |  %-25s  |\n","FLIGHT NUMBER","DEPARTURE TIME","PILOTS","FLIGHT ATTENDANTS","GROUND STAFF");
        for(int j = 0; j <= 135; j++) System.out.print("-");
        System.out.println();
        List<String> pilots = new ArrayList<>();
        List<String> flightAttendants = new ArrayList<>();
        List<String> groundStaff = new ArrayList<>();

        for(Assignment assignment : assignmentAction.get(flightNumber)){
            if(assignment.getPosition().trim().equals(Position.PILOT.getTYPE())){
                pilots.add(assignment.getIDCrew());
            }

            if(assignment.getPosition().trim().equals(Position.FLIGHT_ATTENDANT.getTYPE())){
                flightAttendants.add(assignment.getIDCrew());
            }

            if(assignment.getPosition().trim().equals(Position.GROUND_STAFF.getTYPE())){
                groundStaff.add(assignment.getIDCrew());
            }
        }

        for(int i =0 ; i <pilots.size() || i < flightAttendants.size() || i < groundStaff.size() ; i++){
            String pilotS,  flightAttendantS, groundStaffS, flightNumberS,departureTime;
            if(i >= pilots.size()){
                pilotS = " ";
            }else pilotS = pilots.get(i);

            if(i >= flightAttendants.size()){
                flightAttendantS= " ";
            }else flightAttendantS = flightAttendants.get(i);

            if(i >= groundStaff.size()){
                groundStaffS =" ";
            }else groundStaffS = groundStaff.get(i);
            flightNumberS = " ";
            departureTime =" ";
            if(i == 0){
                flightNumberS = flightNumber;
                departureTime = Validation.executeNullDate(flight.getDepartureTime());
            } 

            System.out.printf("|  %-20s  |  %-20s  |  %-20s  |  %-25s  |  %-25s  |\n",flightNumberS,departureTime,pilotS,flightAttendantS,groundStaffS);
        }
        for(int j = 0; j <= 135; j++) System.out.print("-");
        System.out.println();
    }

    @Override
    public boolean checkDuplicate(String flightNumber, String idCrew) {
       for(Assignment assignment : this.get(flightNumber)){
        if(assignment.getIDCrew().equals(idCrew)){
            return true;
        }
       }
       return false;
    }
    
    
    @Override
    public void displayConfirmUpdate(String flightNumber, String oldIDCrew,String newIDCrew){
        Flight flight = FlightMenu.getFlightList().get(flightNumber);
        for(int j = 0; j <= 135; j++) System.out.print("-");
        System.out.println();
        System.out.printf("|  %-20s  |  %-20s  |  %-20s  |  %-25s  |  %-25s  |\n","FLIGHT NUMBER","DEPARTURE TIME","PILOTS","FLIGHT ATTENDANTS","GROUND STAFF");
        for(int j = 0; j <= 135; j++) System.out.print("-");
        System.out.println();
        
        List<String> pilots = new ArrayList<>();
        List<String> flightAttendants = new ArrayList<>();
        List<String> groundStaff = new ArrayList<>();
     
        for(Assignment assignment : assignmentAction.get(flightNumber)){
            if(assignment.getPosition().trim().equals(Position.PILOT.getTYPE())){
                if(assignment.getIDCrew().equals(oldIDCrew)){
                    pilots.add(newIDCrew);
                    continue;
                }
                pilots.add(assignment.getIDCrew());
            }

            if(assignment.getPosition().trim().equals(Position.FLIGHT_ATTENDANT.getTYPE())){
                if(assignment.getIDCrew().equals(oldIDCrew)){
                    pilots.add(newIDCrew);
                    continue;
                }
                flightAttendants.add(assignment.getIDCrew());
            }

            if(assignment.getPosition().trim().equals(Position.GROUND_STAFF.getTYPE())){
                if(assignment.getIDCrew().equals(oldIDCrew)){
                    pilots.add(newIDCrew);
                    continue;
                }
                groundStaff.add(assignment.getIDCrew());
            }
        }

        for(int i =0 ; i <pilots.size() || i < flightAttendants.size() || i < groundStaff.size() ; i++){
            String pilotS,  flightAttendantS, groundStaffS, flightNumberS,departureTime;
            if(i >= pilots.size()){
                pilotS = " ";
            }else pilotS = pilots.get(i);

            if(i >= flightAttendants.size()){
                flightAttendantS= " ";
            }else flightAttendantS = flightAttendants.get(i);

            if(i >= groundStaff.size()){
                groundStaffS =" ";
            }else groundStaffS = groundStaff.get(i);
            flightNumberS = " ";
            departureTime =" ";
            if(i == 0){
                flightNumberS = flightNumber;
                departureTime = Validation.executeNullDate(flight.getDepartureTime());
            } 

            System.out.printf("|  %-20s  |  %-20s  |  %-20s  |  %-25s  |  %-25s  |\n",flightNumberS,departureTime,pilotS,flightAttendantS,groundStaffS);
        }
        for(int j = 0; j <= 135; j++) System.out.print("-");
        System.out.println();
    }
    
    
    @Override
    public void update(String flightNumber, String oldCrew) {
        //-----------display employe co1 kha nang thay the----------------------------
        List<Crew> listCrew = CrewMenu.getCrewList().get(CrewMenu.getCrewList().get(oldCrew).getType(), flightNumber);
        CrewMenu.getCrewList().displayList(listCrew);
        //----------------------------------------------------------------------------
        String newCrew = Validation.convertNullString(DataInput.getString("IDCrew replaces " +oldCrew+" : "));
        //----------------------------------------------------------------------------
        if(!CrewMenu.getCrewList().checkDuplicate(newCrew, listCrew)){
            System.out.println("ID crew does not exist !");
            return;
        }
        
        if(newCrew.equals("N/A")) return;
        if(CrewMenu.getCrewList().get(newCrew).getType() != CrewMenu.getCrewList().get(oldCrew).getType()){
            CrewMenu.getCrewList().printHeadTable();
            CrewMenu.getCrewList().get(newCrew).display();
            System.out.println("Position is not suitable !");
            return;
        }
        if(checkIDcrewAvailable(flightNumber, newCrew) && CrewMenu.getCrewList().checkDuplicate(newCrew, CrewMenu.getCrewList().getAll())){
            //-------------------------------------------------
            displayConfirmUpdate(flightNumber, oldCrew, newCrew);
            //--------------------------------------------------
            String choice = DataInput.getString("Do you want to update assignment ? Y/N");
            if(!("Y".equals(choice))){
               System.out.println("Update assignment fail!");
               return;
            }
            assignmentAction.update(flightNumber, oldCrew, newCrew);
        }else System.out.println("IDCrew is not valid !");
        System.out.println("Update successful!");
    }

    @Override
    public void getStatusAssignmentFlight(List<Flight> list) {
        for(int i = 0; i <= 131; i++) System.out.print("-");
        System.out.println("");
        System.out.printf("|  %-20s  |  %-25s  |  %-20s  |  %-20s  |  %-20s  |\n","FLIGHT NUMBER","DEPARTURE TIME","PILOTS","FLIGHT ATTENDANTS","GROUND STAFFS");
        for(int i = 0; i <= 131; i++) System.out.print("-");
        System.out.println("");
        for(Flight flight : list){
            List<Assignment> listAssignment = this.get(flight.getFlightNumber());
            int numberOfPilot = 0, numberOfFlightAttendants = 0, numberOfStaffGround =0;
            for(Assignment assignment : listAssignment){
                if(assignment.getPosition().equals(Validation.getTypeOfCrew(1))){
                    numberOfPilot++;
                }
                if(assignment.getPosition().equals(Validation.getTypeOfCrew(2))){
                    numberOfFlightAttendants++;
                }
                if(assignment.getPosition().equals(Validation.getTypeOfCrew(3))){
                    numberOfStaffGround++;
                }
            }
            
            System.out.printf("|  %-20s  |  %-25s  |  %-20s  |  %-20s  |  %-20s  |\n",flight.getFlightNumber(),Validation.executeNullDate(flight.getDepartureTime()),numberOfPilot,numberOfFlightAttendants,numberOfStaffGround);
            for(int i = 0; i <= 131; i++) System.out.print("-");
            System.out.println("");
        }
    }
    
    @Override
    public List<String> getFlightHaveAssignment(List<Flight> list) {
        List<String> flightNumberList = new ArrayList<>();
        for(int i = 0; i <= 131; i++) System.out.print("-");
        System.out.println("");
        System.out.printf("|  %-20s  |  %-25s  |  %-20s  |  %-20s  |  %-20s  |\n","FLIGHT NUMBER","DEPARTURE TIME","PILOTS","FLIGHT ATTENDANTS","GROUND STAFFS");
        for(int i = 0; i <= 131; i++) System.out.print("-");
        System.out.println("");
        for(Flight flight : list){
            List<Assignment> listAssignment = this.get(flight.getFlightNumber());
            int numberOfPilot = 0, numberOfFlightAttendants = 0, numberOfStaffGround =0;
            for(Assignment assignment : listAssignment){
                if(assignment.getPosition().equals(Validation.getTypeOfCrew(1))){
                    numberOfPilot++;
                }
                if(assignment.getPosition().equals(Validation.getTypeOfCrew(2))){
                    numberOfFlightAttendants++;
                }
                if(assignment.getPosition().equals(Validation.getTypeOfCrew(3))){
                    numberOfStaffGround++;
                }
            }
            
           if(numberOfPilot >= 1 || numberOfFlightAttendants >= 1 || numberOfStaffGround >= 1){
                flightNumberList.add(flight.getFlightNumber());
                System.out.printf("|  %-20s  |  %-25s  |  %-20s  |  %-20s  |  %-20s  |\n",flight.getFlightNumber(),Validation.executeNullDate(flight.getDepartureTime()),numberOfPilot,numberOfFlightAttendants,numberOfStaffGround);
                for(int i = 0; i <= 131; i++) System.out.print("-");
                System.out.println("");
           }
        }
        return flightNumberList;
    }
}
