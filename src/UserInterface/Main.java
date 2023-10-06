/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import UserInterface.EntityMenu.SeatMenu;
import UserInterface.EntityMenu.FlightMenu;
import UserInterface.EntityMenu.AssignmentMenu;
import UserInterface.EntityMenu.BoardingPassesMenu;
import UserInterface.EntityMenu.CrewMenu;
import UserInterface.EntityMenu.CustomerMenu;



/**
 *
 * @author nhutm
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws Exception  {
     IMenu menu = new Menu();
     menu.addItem("Flight schedule management");
     menu.addItem("Passenger reservation and booking");
     menu.addItem("Passenger check-in and seat allocation and availability");
     menu.addItem("Crew management and assignments");
     menu.addItem("Administrator access for system management");
     menu.addItem("Data storage for flight details, reservations, and assignments");
     menu.addItem("Others - Quit");
     int choice;
     BoardingPassesMenu listBoardingPasses = new BoardingPassesMenu();
     FlightMenu listFlight = new FlightMenu();
     SeatMenu listSeat = new SeatMenu();
     CustomerMenu listCustomer = new CustomerMenu();
     CrewMenu listCrew = new CrewMenu();
     AssignmentMenu listAssignment = new AssignmentMenu();
     do{
        System.out.println("---------------------------------------------------------------");
        System.out.println("                  Flight Management System".toUpperCase());
        System.out.println("---------------------------------------------------------------");
        choice = menu.getChoice("You want to close application");
        
        switch(choice){
            case 1: 
                listFlight.add();
                break;
            case 2:
               
                listCustomer.addCustomer();
                break;
            case 3:
                listBoardingPasses.passengerCheckIn();
                break;
            case 4:
                IMenu crewAssignment = new Menu();
                crewAssignment.addItem("Crew management");
                crewAssignment.addItem("Assignments");
                crewAssignment.addItem("-Others-Quit");
                int choiceCrewAssignment;
                do{
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("                  Crew management and assignments".toUpperCase());
                    System.out.println("---------------------------------------------------------------");
                    choiceCrewAssignment= crewAssignment.getChoice("You want to back main menu");
                    switch(choiceCrewAssignment){
                        case 1:
                            IMenu crewMenu = new Menu();
                            crewMenu.addItem("Add crew");
                            crewMenu.addItem("Update crew");
                            crewMenu.addItem("Delete crew");
                            crewMenu.addItem("Display all crew");
                            crewMenu.addItem("-Others - Quit");
                            int crewChoice;
                            do{
                                System.out.println("---------------------------------------------------------------");
                                System.out.println("                  Crew management".toUpperCase());
                                System.out.println("---------------------------------------------------------------");
                                crewChoice = crewMenu.getChoice("You want to back main menu");
                                switch(crewChoice){
                                    case 1:
                                        listCrew.add();
                                        break;
                                    case 2:
                                        listCrew.update();
                                        break;
                                    case 3:
                                        listCrew.delete();
                                        break;
                                    case 4:
                                        listCrew.displayAll();
                                        break;
                                }
                            }while(crewChoice >= 1 && crewChoice <= 4);
                            break;
                        case 2:
                            listAssignment.assignmentCrew();
                            break;
                    }
                }while(choiceCrewAssignment >= 1 && choiceCrewAssignment <= 2);
                break;
            case 5:
                IMenu adminMenu = new Menu();
                adminMenu.addItem("Crew assignments management");
                adminMenu.addItem("Flight schedules management");
                adminMenu.addItem("Reservation and booking management");
                adminMenu.addItem("Display all database");
                adminMenu.addItem("-Others - Quit");
                int adminChoice;
                do{
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("                  Administrator access for system management".toUpperCase());
                    System.out.println("---------------------------------------------------------------");
                    adminChoice =  adminMenu.getChoice("You want to back main menu");
                    switch(adminChoice){
                        case 1: 
                            IMenu crewAssignmentMenu = new Menu();
                            crewAssignmentMenu.addItem("Add crew assignment");
                            crewAssignmentMenu.addItem("Update crew assignment");
                            crewAssignmentMenu.addItem("Delete crew assignment");
                            crewAssignmentMenu.addItem("Display all crew assignment");
                            crewAssignmentMenu.addItem("-Others - Quit");
                            int crewAssignmenChoice;
                            do{
                                System.out.println("---------------------------------------------------------------");
                                System.out.println("                  Crew assignments management".toUpperCase());
                                System.out.println("---------------------------------------------------------------");
                               crewAssignmenChoice =  crewAssignmentMenu.getChoice("You want to back main menu");
                               switch(crewAssignmenChoice){
                                   case 1:
                                       listAssignment.assignmentCrew();
                                       break;
                                   case 2:
                                       listAssignment.updateAssignmentCrew();
                                       break;
                                   case 3:
                                       listAssignment.delete();
                                       break;
                                   case 4:
                                       listAssignment.displayAll();
                                       break;
                               }
                            }while(crewAssignmenChoice >= 1 && crewAssignmenChoice <= 4);
                            break;
                        case 2:
                            IMenu flightSchedulesMenu = new Menu();
                            flightSchedulesMenu.addItem("Add flight");
                            flightSchedulesMenu.addItem("Update flight");
                            flightSchedulesMenu.addItem("Delete flight");
                            flightSchedulesMenu.addItem("Display all flight");
                            flightSchedulesMenu.addItem("-Others - Quit");
                            int flightScheduleChoice;
                            do{
                                System.out.println("---------------------------------------------------------------");
                                System.out.println("                  Flight schedules management".toUpperCase());
                                System.out.println("---------------------------------------------------------------");
                                flightScheduleChoice = flightSchedulesMenu.getChoice("You want to back main menu");
                                switch(flightScheduleChoice){
                                    case 1:
                                        listFlight.add();
                                        break;
                                    case 2:
                                        listFlight.update();
                                        break;
                                    case 3:
                                        listFlight.delete();
                                        break;
                                    case 4:
                                        listFlight.displayAll();
                                        break;
                                }
                            }while(flightScheduleChoice >= 1 && flightScheduleChoice <= 4);
                            break;
                        case 3:
                            IMenu resrevationManagementMenu = new Menu();
                            resrevationManagementMenu.addItem("Add reservation");
                            resrevationManagementMenu.addItem("Update reservation");
                            resrevationManagementMenu.addItem("Delete reservation");
                            resrevationManagementMenu.addItem("Display infor reservation");
                            resrevationManagementMenu.addItem("-Others - Quit");
                            int resrevationManagementChoice;
                            do{
                                System.out.println("---------------------------------------------------------------");
                                System.out.println("                  Reservation and booking management".toUpperCase());
                                System.out.println("---------------------------------------------------------------");
                                resrevationManagementChoice= resrevationManagementMenu.getChoice("You want to back main menu");
                                switch(resrevationManagementChoice){
                                    case 1:
                                        listCustomer.addCustomer();
                                        break;
                                    case 2:
                                        listCustomer.updateCustomer();
                                        break;
                                    case 3:
                                        listCustomer.deleteCustomer();
                                        break;
                                    case 4:
                                        listCustomer.displayAll();
                                        break;
                                }
                            }while(resrevationManagementChoice >=1 && resrevationManagementChoice <=4);
                            break;
                        case 4:
                            IMenu inforDatabaseMenu = new Menu();
                            inforDatabaseMenu.addItem("Display list flight");
                            inforDatabaseMenu.addItem("Display list customer");
                            inforDatabaseMenu.addItem("Display list assignment");
                            inforDatabaseMenu.addItem("Display list crew");
                            inforDatabaseMenu.addItem("Display list boarding pass");
                            inforDatabaseMenu.addItem("Display list seat");
                            inforDatabaseMenu.addItem("-Others - Quit");
                            int inforDatabaseChoice;
                            do{
                               System.out.println("---------------------------------------------------------------");
                               System.out.println("                  Display all database".toUpperCase());
                               System.out.println("---------------------------------------------------------------");
                               inforDatabaseChoice = inforDatabaseMenu.getChoice("You want to back main menu");
                               switch(inforDatabaseChoice){
                                   case 1:
                                       listFlight.displayAll();
                                       break;
                                   case 2:
                                       listCustomer.displayAll();
                                       break;
                                   case 3:
                                       listAssignment.displayAll();
                                       break;
                                   case 4:
                                       listCrew.displayAll();
                                       break;
                                   case 5:
                                       listBoardingPasses.displayAll();
                                       break;
                                   case 6:
                                       listSeat.displayAll();
                                       break;
                               }
                                 
                            }while(inforDatabaseChoice>=1 && inforDatabaseChoice <= 6);
                            break;
                    }
                }while(adminChoice >= 1 && adminChoice <= 4);
                break;
            case 6:
                listAssignment.saveToFile();
                listBoardingPasses.saveToFile();
                listCrew.saveToFile();
                listFlight.saveToFile();
                listCustomer.saveToFile();
                listSeat.saveToFile();
                System.out.println("---------------------------------------------------------------");
                System.out.println("                  File save success".toUpperCase());
                System.out.println("---------------------------------------------------------------");
                break;
        }
     }while(choice >= 1 && choice <= 6);
    }
    
}
