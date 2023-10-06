/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.FileManager.AssignmentFile;

import DataBussiness.Entity.Assignment.Assignment;
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
 * flightNumber, IDCrew, position,
 * Date departureTime
 */
public class AssignmentFile implements  IFileManager<Assignment>{

    @Override
    public List<Assignment> readDataFromFile(String url) {
        List<Assignment> list = new ArrayList<>();
        String flightNumber,IDCrew,position;
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
             
                flightNumber = stk.nextToken().toUpperCase().trim();
                IDCrew = stk.nextToken().toUpperCase().trim();
                position = stk.nextToken().toUpperCase().trim();
                departureTime = Validation.executeNullDate(stk.nextToken().trim());
                Assignment assignment = new Assignment(flightNumber, IDCrew, position, departureTime);
                list.add(assignment);
            }
            bf.close();
            fr.close();
        }catch (Exception e){
           
        }
        return list;
    }

    @Override
    public void storeDataToFile(String url, List<Assignment> list) {
        try{
            try (FileWriter writer = new FileWriter(url)) {
                
                for(int i =0 ; i< list.size(); i++){
                    writer.write(list.get(i).toString()+"\n");
                }
                
            }
        }catch(IOException e){
            System.out.println("An error occurred while writing the file.");
        }
    }
    
}
