/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.FileManager.CustomerFile;


import DataBussiness.Entity.Person.Customer;
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
public class CustomerFile implements IFileManager<Customer>{

    @Override
    public List<Customer> readDataFromFile(String url) {
        List<Customer> list  = new ArrayList<>();
        String email, name, address, IDReservation, nationality,status, flightNumber;
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
                    IDReservation = stk.nextToken().toUpperCase().trim();
                    nationality = stk.nextToken().trim().toUpperCase();
                    status = stk.nextToken().trim().toUpperCase();
                    flightNumber = stk.nextToken().toUpperCase().trim();
                    
                    Customer customer = new Customer(IDReservation, nationality, status, flightNumber, email, name, address, birthday);
                    list.add(customer);
                }   
            }
        }catch (Exception e){
           
        };
        
        return list;
    }

    @Override
    public void storeDataToFile(String url, List<Customer> list) {
       try{
            try (FileWriter writer = new FileWriter(url)) {
                
                for(int i =0 ; i< list.size(); i++){
                    writer.write(list.get(i).toString().toUpperCase()+"\n");
                }
                System.out.println("File save successfully!");
            }
        }catch(IOException e){
            System.out.println("An error occurred while writing the file.");
        }
    }
    
}
