/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Validation.ConstantValue;

/**
 *
 * @author nhutm
 */
public enum LinkFile {
    
    FLIGHT_URL("D:\\FPT University\\Fall2023\\LAB211\\Lab2\\ManagerSystemFlight\\src\\DataLayer\\FileManager\\FlightFile\\flight.dat"),
    SEAT_URL("D:\\FPT University\\Fall2023\\LAB211\\Lab2\\ManagerSystemFlight\\src\\DataLayer\\FileManager\\SeatFile\\seat.dat"),
    CUSTOMER_URL("D:\\FPT University\\Fall2023\\LAB211\\Lab2\\ManagerSystemFlight\\src\\DataLayer\\FileManager\\CustomerFile\\customer.dat"),
    CREW_URL("D:\\FPT University\\Fall2023\\LAB211\\Lab2\\ManagerSystemFlight\\src\\DataLayer\\FileManager\\CrewFile\\crew.dat"),
    BOARDINGPASSES_URL("D:\\FPT University\\Fall2023\\LAB211\\Lab2\\ManagerSystemFlight\\src\\DataLayer\\FileManager\\BoardingPassesFile\\boardingPasses.dat"),
    ASSIGNMENT_URL("D:\\FPT University\\Fall2023\\LAB211\\Lab2\\ManagerSystemFlight\\src\\DataLayer\\FileManager\\AssignmentFile\\assignment.dat");
    
    private String fileURL;

    private LinkFile(String fileURL) {
        this.fileURL = fileURL;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }
    
}
