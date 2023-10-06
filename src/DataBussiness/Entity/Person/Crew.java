/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Entity.Person;

import DataBussiness.Validation.Validation;
import UserInterface.EntityMenu.CrewMenu;
import java.util.Date;

/**
 *
 * @author nhutm
 */
public class Crew extends Person{
    private String IDCrew;
    private int type;

    public Crew() {
    }

    public Crew(String IDCrew, int type, String email, String name, String address, Date birthday) {
        super(email, name, address, birthday);
        this.IDCrew = IDCrew;
        this.type = type;
        
    }

    public static int getAutoIDRservation() {
       int autoIDRservation = 0;
       int max = autoIDRservation;
       for(Crew crew :  CrewMenu.getCrewList().getAll()){
           int tmp = Integer.parseInt(crew.getIDCrew().substring(1));
           if(tmp > max) max = tmp;
       }
        autoIDRservation = max +1;
        return autoIDRservation;
    }
    



    public String getIDCrew() {
        return IDCrew;
    }

    public void setIDCrew(String IDCrew) {
        this.IDCrew = IDCrew;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return  super.toString() +", "  + IDCrew + ", " + type ;
    }
    public void display(){ 
        System.out.printf("|  %-15s  |  %-20s  |  %-40s  | %-20s  |\n",IDCrew,getName(),getEmail(),Validation.getTypeOfCrew(getType()));
        for(int i =0 ; i <=114; i++ )System.out.print("-");
        System.out.println("");
    }
      public void displayFull(){ 
        System.out.printf("|  %-15s  |  %-20s  |  %-40s  |  %-20s  |  %-40s  | %-20s  |\n",IDCrew,getName(),getEmail(),Validation.executeNullDate(getBirthday()),getAddress(),Validation.getTypeOfCrew(getType()));
        for(int i =0 ; i <=184; i++ )System.out.print("-");
        System.out.println("");
    }
}
