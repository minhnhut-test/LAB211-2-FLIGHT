/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.FileManager.CrewFile;

import DataBussiness.Entity.Person.Crew;
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
public class CrewFile implements IFileManager<Crew>{

    @Override
    @SuppressWarnings("empty-statement")
    public List<Crew> readDataFromFile(String url) {
        List<Crew> list  = new ArrayList<>();
        String email, name, address, IDCrew,location;
        int type,status;
        Date birthday;
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
                    
                    email = stk.nextToken().trim().toUpperCase();
                    name = stk.nextToken().trim().toUpperCase();
                    address = stk.nextToken().trim().toUpperCase();
                    birthday = Validation.executeNullDate(stk.nextToken().trim());
                    IDCrew = stk.nextToken().trim().toUpperCase();
                    type = Integer.parseInt(stk.nextToken().trim().toUpperCase());
            
                    Crew crew = new Crew(IDCrew, type, email, name, address, birthday);
                    list.add(crew);
                }
            }
        }catch (Exception e){
           
        };
        
        return list;
    }

    @Override
    public void storeDataToFile(String url, List<Crew> list) {
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
