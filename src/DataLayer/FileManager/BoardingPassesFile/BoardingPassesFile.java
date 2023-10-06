/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.FileManager.BoardingPassesFile;


import DataBussiness.Entity.BoardingPasses.BoardingPasses;
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
public class BoardingPassesFile implements IFileManager<BoardingPasses>{

    @Override
    @SuppressWarnings("empty-statement")
    public List<BoardingPasses> readDataFromFile(String url) {
        List<BoardingPasses> list  = new ArrayList<>();
        String IDReservation, name, flightNumber,departureLocation,arrivalLocation,seat;
        Date departureTime;
        try{
            File file = new File(url);
            if(!file.exists()){
                file.createNewFile();
            }
            FileReader fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while((details = bf.readLine()) != null){
                
                if(details.isEmpty()) throw new Exception();
                StringTokenizer stk = new StringTokenizer(details, ",");
               
                IDReservation = stk.nextToken().trim().toUpperCase();
                name = stk.nextToken().trim().toUpperCase();
                flightNumber = stk.nextToken().trim().toUpperCase();
                departureLocation = stk.nextToken().trim().toUpperCase();
                arrivalLocation = stk.nextToken().trim().toUpperCase();
                departureTime = Validation.executeNullDate(stk.nextToken().trim());
                seat = stk.nextToken().trim().toUpperCase();
           
                BoardingPasses boardingPasses = new BoardingPasses(IDReservation, name, flightNumber, departureLocation, arrivalLocation, departureTime, seat);
                list.add(boardingPasses);
            }
            bf.close();
            fr.close();
        }catch (Exception e){
            System.out.println("File is empty!");
        };
        
        return list;
    }

    @Override
    public void storeDataToFile(String url, List<BoardingPasses> list) {
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
