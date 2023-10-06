/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.Dao.AssignmentDao;

import DataBussiness.Entity.Assignment.Assignment;
import DataBussiness.Entity.Flight.Flight;
import DataBussiness.Entity.Person.Crew;
import DataBussiness.Validation.Validation;
import DataLayer.FileManager.AssignmentFile.AssignmentFile;
import UserInterface.EntityMenu.CrewMenu;
import DataLayer.FileManager.IFileManager;
import DataBussiness.Validation.ConstantValue.LinkFile;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author nhutm
 */
public class AssignmentDao implements IAssignmentDao{
    List<Assignment> assignmentList = new ArrayList<>();
    IFileManager<Assignment> assignmentFile = new AssignmentFile();

    public AssignmentDao() {
        loadDataFromFile();
    }
    
    
    @Override
    public void loadDataFromFile() {
        this.assignmentList = assignmentFile.readDataFromFile(LinkFile.ASSIGNMENT_URL.getFileURL());
    }

    @Override
    public void saveDataToFile() {
        assignmentFile.storeDataToFile(LinkFile.ASSIGNMENT_URL.getFileURL(), assignmentList);
    }

    @Override
    public List<Assignment> getAll() {
        return assignmentList;
    }

    @Override
    public Assignment get(String IDCrew, String flightNumber) {
        for(Assignment assignment : assignmentList){
            if(assignment.getIDCrew().equals(IDCrew) && assignment.getFlightNumber().equals(flightNumber)){
                return assignment;
            }
        }
        return null;
    }

    @Override
    public List<Assignment> get(String flightNumber) {
        List<Assignment> list = new ArrayList<>();
        assignmentList.stream().filter((assignment) -> (assignment.getFlightNumber().equals(flightNumber))).forEachOrdered((assignment) -> {
            list.add(assignment);
        });
        return list;
    }

    @Override
    public void update(Assignment newAssignment) {
        for(Assignment assignment : assignmentList){
            if(assignment.getIDCrew().equals(newAssignment.getIDCrew()) && assignment.getFlightNumber().equals(newAssignment.getFlightNumber())){
                assignment.setIDCrew(newAssignment.getIDCrew());
                assignment.setFlightNumber(newAssignment.getFlightNumber());
                assignment.setDepartureTime(newAssignment.getDepartureTime());
                assignment.setPosition(newAssignment.getPosition());
            }
        }
    }

    @Override
    public boolean delete(Assignment assignment) {
        return assignmentList.remove(assignment);
    }

    @Override
    public boolean add(Assignment assignment) {
        return assignmentList.add(assignment);
    }

    @Override
    public void update(String flightNumber, Flight flight) {
        assignmentList.stream().filter((assignment) -> (assignment.getFlightNumber().equals(flightNumber))).map((assignment) -> {
            assignment.setFlightNumber(flight.getFlightNumber());
            return assignment;
        }).forEachOrdered((assignment) -> {
            assignment.setDepartureTime(flight.getDepartureTime());
        });
    }

    @Override
    public void update(Crew crew, String oldCrew) {
        assignmentList.stream().filter((assignment) -> (assignment.getIDCrew().equals(oldCrew))).map((assignment) -> {
            assignment.setIDCrew(crew.getIDCrew());
            return assignment;
        }).forEachOrdered((assignment) -> {
            assignment.setPosition(Validation.getTypeOfCrew(crew.getType()));
        });
    }

    @Override
    public void delete(Crew crew) {
        for(int i = 0; i < assignmentList.size(); i++){
             if(assignmentList.get(i).getIDCrew().equals(crew.getIDCrew())){
                assignmentList.remove(i);
            }
        }
    }

    @Override
    public List<Assignment> getOnIDCrew(String idCrew) {
        List<Assignment> list = new ArrayList<>();
        for(Assignment assignment : assignmentList){
            if(assignment.getIDCrew().equals(idCrew)){
                list.add(assignment);
            }
        }
        return list;
    }


    @Override
    public void update(String flightNumber, String oldCrew, String newCrew) {
        for(Assignment assignment : this.get(flightNumber)){
            if(assignment.getIDCrew().equals(oldCrew) ){
                assignment.setIDCrew(newCrew);
            }
       }
    }
    
}
