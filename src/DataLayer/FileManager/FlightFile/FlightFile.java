/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.FileManager.FlightFile;

import DataBussiness.Entity.Flight.Flight;
import DataBussiness.Validation.Validation;
import DataLayer.FileManager.IFileManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author nhutm
 */
public class FlightFile implements IFileManager<Flight>{

    @Override
    @SuppressWarnings("empty-statement")
    public List<Flight> readDataFromFile(String url) {
        List<Flight> list  = new ArrayList<>();
        String flightNumber, departureLocation, arrivalLocation;
        Date departureTime,arrivalTime;
        int duration, availableSeats;
        try{
            File file = new File(url);
          
            if(!file.exists()){
                file.createNewFile();
            }
            try (FileReader fr = new FileReader(file); BufferedReader bf = new BufferedReader(fr)) {
                String details;
                while((details = bf.readLine()) != null){
                    if(details.isEmpty()) throw new Exception();
                    StringTokenizer stk = new StringTokenizer(details, ",");
                    flightNumber = stk.nextToken().trim().toUpperCase();
                    departureLocation = stk.nextToken().trim().toUpperCase();
                    arrivalLocation = stk.nextToken().trim().toUpperCase();
                    departureTime = Validation.executeNullDate(stk.nextToken().trim());
                    arrivalTime = Validation.executeNullDate(stk.nextToken().trim());
                    duration = Integer.parseInt(stk.nextToken().trim().toUpperCase());
                    availableSeats = Integer.parseInt(stk.nextToken().trim());
                    
                    Flight flight = new Flight(flightNumber, departureLocation, arrivalLocation, departureTime, arrivalTime, duration, availableSeats);
                    list.add(flight);
                }   
            }
        }catch (Exception e){
            
        }
        
        return list;
    }

    @Override
    public void storeDataToFile(String url, List<Flight> list) {
         try{
            try (FileWriter writer = new FileWriter(url)) {
                
                for(int i =0 ; i< list.size(); i++){
                    writer.write(list.get(i).toString().toUpperCase()+"\n");
                }
                
            }
        }catch(IOException e){
            System.out.println("An error occurred while writing the file.");
        }
    }
    
}
