/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Validation;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author nhutm
 */
public class DataInput {
    //--------------------------------
    public static String getString(String displayMessage){
        String s;
        Scanner sc = new Scanner(System.in);
        System.out.print(displayMessage);
        s = sc.nextLine().toUpperCase();
        return s.trim();
    }
    //--------------------------------
    public static Date getDate(String displayMessage){
       try{
        String s;
        Scanner sc = new Scanner(System.in);
        System.out.print(displayMessage);
        s = sc.nextLine().trim();    
        
        if(Validation.removeBlankString(s).isEmpty()){
            return null;
        }
        if(!(s.matches("\\d{1,2}\\/\\d{1,2}\\/\\d{2,4}") || s.matches("\\d{1,2}:\\d{1,2} \\d{1,2}\\/\\d{1,2}\\/\\d{2,4}"))){
            System.out.println("Invalid date !");
            return null;
        }
        s = Validation.executeInputStringDate(s);
        
        if(!Validation.checkDateValid(s)){
            System.out.println("Invalid date !");
            return null;
        }
        return Validation.executeNullDate(s);
       }catch (ParseException e){
            return null;
       }
    }
     //--------------------------------
     public static int getIntegerNumber(String displayMessage) throws Exception {
        int number = 0;
        String s;
        System.out.print(displayMessage);
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        if (s.matches("\\d{1,10}") == false || Validation.removeBlankString(s).isEmpty()) {
              return number;
        } else {
            number = Integer.parseInt(s);
        }
        return number;
    }
  
   
}
