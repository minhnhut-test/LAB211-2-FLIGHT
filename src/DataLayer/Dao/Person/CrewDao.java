/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.Dao.Person;


import DataBussiness.Entity.Person.Crew;

import DataLayer.FileManager.CrewFile.CrewFile;
import DataLayer.FileManager.IFileManager;
import DataBussiness.Validation.ConstantValue.LinkFile;
import java.util.ArrayList;
import java.util.List;




/**
 *
 * @author nhutm
 */
public final class CrewDao implements ICrewDao{
    List<Crew> crewList = new ArrayList<>();
    IFileManager<Crew> crewFile = new CrewFile();

    public CrewDao() {
        loadDataFromFile();
    }
    
    
    @Override
    public void loadDataFromFile() {
       this.crewList =  crewFile.readDataFromFile(LinkFile.CREW_URL.getFileURL());
    }

    @Override
    public void saveDataToFile() {
        crewFile.storeDataToFile(LinkFile.CREW_URL.getFileURL(), crewList);
    }

    @Override
    public List<Crew> getAll() {
        return crewList;
    }

    @Override
    public int getIndex(String IDCrew) {
        for(int i =0 ; i < crewList.size(); i++){
            if(crewList.get(i).getIDCrew().equals(IDCrew)) return i;
        }
        return -1;
    }

    @Override
    public Crew get(String IDCrew) {
        for(Crew crew : crewList){
            if(crew.getIDCrew().equals(IDCrew)){
                return crew;
            }
        }
        return null;
    }

    @Override
    public void update(Crew newCrew, String oldIDCrew) {
        crewList.stream().filter((crew) -> (crew.getIDCrew().equals(oldIDCrew))).map((crew) -> {
            crew.setName(newCrew.getName());
            return crew;
        }).map((crew) -> {
            crew.setEmail(newCrew.getEmail());
            return crew;
        }).map((crew) -> {
            crew.setAddress(newCrew.getAddress());
            return crew;
        }).map((crew) -> {
            crew.setBirthday(newCrew.getBirthday());
            return crew;
        }).map((crew) -> {
            crew.setType(newCrew.getType());
            return crew;
        }).forEachOrdered((crew) -> {
            crew.setIDCrew(newCrew.getIDCrew());
        });
    }

    @Override
    public void delete(Crew crew) {
        crewList.remove(crew);
    }

    @Override
    public void add(Crew crew) {
        crewList.add(crew);
    }

    @Override
    public void displayAll() {
        crewList.forEach((crew) -> {
            crew.displayFull();
        });
    }

    @Override
    public boolean checkTypeEmployee(String IDCrew, int type) {
        Crew crew = get(IDCrew);
        return (crew.getType() == type);
    }

    @Override
    public List<Crew> get(int position) {
        List<Crew> list = new ArrayList<>();
        crewList.stream().filter((crew) -> ( crew.getType()== position)).forEachOrdered((crew) -> {
            list.add(crew);
        });
        return list;
    }

    @Override
    public List<Crew> getCheckSimilar(Crew crewCheck) {
       List<Crew> list = new ArrayList<>();
       for(Crew crew : this.getAll()){
           if(crew.getName().equals(crewCheck.getName())  ){
               list.add(crew);
           }
       }
       return list;
    }



      
}
