/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.FileManager.SeatFile;

import DataBussiness.Entity.Seat.Seat;
import DataLayer.FileManager.IFileManager;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;



/**
 *
 * @author nhutm
 */
public class SeatFile implements  IFileManager<Seat>{
    
    //F0001;100!A01, F0001-A01;A02, F0001-A02;A03, F0001-A03;A04, F0001-A04;

    @Override
    public List<Seat> readDataFromFile(String url) {
       List<Seat> list = new ArrayList<>();
  
       
       try{
            File file = new File(url);
            if(!file.exists()){
                file.createNewFile();
            }
            
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                if(line.isEmpty()) throw new Exception();
                String[] data = line.split("!");
                String[] flightAndSeat = data[0].split(";");
                String flightNumber = flightAndSeat[0].toUpperCase().trim();
                int seatAvailable = Integer.parseInt(flightAndSeat[1].toUpperCase().trim());
                
                HashMap<String,String> seats = new HashMap<>();
                String[] seat = data[1].split(";");
                for (String seat1 : seat) {
                    String[] keyValue = seat1.split(",");
                    String keySeat = keyValue[0].toUpperCase().trim();
                    String valueSeat = keyValue[1].toUpperCase().trim();
                    seats.put(keySeat, valueSeat);
                }
                
                Seat newSeat = new Seat(flightNumber, seatAvailable, seats);               
                list.add(newSeat);
            }
       }catch (Exception e){
           
       }
       return list;
    }

    @Override
    public void storeDataToFile(String url, List<Seat> list) {
        try{
            try (FileWriter writer = new FileWriter(url)) {
                
                for(int i =0 ; i< list.size(); i++){
                   String result ="";
                   result += list.get(i).getFlightNumber() +";" + list.get(i).getSeatAvailable() +"!";
                   for(String j : list.get(i).getSeats().keySet()){
                       result += j +", " + list.get(i).getSeats().get(j) +";";
                   }
                   writer.write(result +"\n");
                }
                
            }
        }catch(IOException e){
            System.out.println("An error occurred while writing the file.");
        }
    }
    
}
