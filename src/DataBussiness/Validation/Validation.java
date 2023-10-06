/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Validation;

import DataBussiness.Entity.Flight.Flight;
import DataBussiness.Validation.ConstantValue.Position;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author nhutm
 */
public final class Validation {
    
    //--------------------------------------------
    static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
    //--------------------------------------------
    public static String executeNullDate(Date dateInput){
     
        String dateOutput ="";
        if(dateInput == null ){
                dateOutput ="N/A";
            }else{
                dateOutput = dateFormat.format(dateInput);
         }
        return dateOutput.trim();
    }
    //---------------------------------------------
    public static Date executeNullDate(String dateInput) throws ParseException{
        Date dateOutput;
        dateInput = executeInputStringDate(dateInput);
        if(dateInput == null || dateInput.equals("00:00 N/A")){
                return null;
        }else{
            
                dateOutput = dateFormat.parse(dateInput);
        }
        return dateOutput;
    }
    //----------------------------------------------
    public static String executeInputStringDate(String str){
        String result = "";
        if(str.contains(":")){
            return str;
        }else{
            result = "00:00 " + str;  
        }
        return result.trim();
    } 
    //------------------------------------------------------
    public static boolean checkDateValid(String date){
       boolean result = false;
       try{
       String[] data = date.split(" ");
       String[] dayMonthYear = data[1].split("/");
       int day = Integer.parseInt(dayMonthYear[0]);
       int month = Integer.parseInt(dayMonthYear[1]);
       int year = Integer.parseInt(dayMonthYear[2]);
       if(dayMonthYear[0].length() > 2 || dayMonthYear[1].length() > 2 ||  year < 1900) return false;
       if((year % 4 == 0 && year % 100 != 0 )||  year % 400 == 0){
           if(month >= 1 && month <= 12){
               switch (month) {
                   case 2:
                       if(day >= 1 && day <= 29){
                           result = true;
                       }   break;
                   case 1:
                   case 3:
                   case 5:
                   case 7:
                   case 8:
                   case 10:
                   case 12:
                       if(day >= 1 && day <= 31) result = true;
                       break;
                   default:
                       if(day >=1 && day <= 30) result = true;
                       break;
               }
           }
       }else{
           if(month >= 1 && month <= 12){
               switch (month) {
                   case 2:
                       if(day >= 1 && day <= 28){
                           result = true;
                       }   break;
                   case 1:
                   case 3:
                   case 5:
                   case 7:
                   case 8:
                   case 10:
                   case 12:
                       if(day >= 1 && day <= 31) result = true;
                       break;
                   default:
                       if(day >=1 && day <= 30) result = true;
                       break;
               }
           }
         }
       }catch(NumberFormatException e){
           return result;
       }
       return result;
    }
    //----------------------------------------------
    public static int changeToDurationTime(Date departureTime, Date arrivalTime){
        if(departureTime == null || arrivalTime == null) return 0;
        long duration = (arrivalTime.getTime() - departureTime.getTime())/(60*1000);
        return (int)duration;
    }
    //----------------------------------------------
    public static String printOutDuration(int duration){
        String hours = duration /60 +"h";
        String minutes = String.format("%02d",duration % 60);
        return (hours + minutes).trim();
    }
    //----------------------------------------------
    public static String getHoursFromDate(Date dateInput){ 
        String[] data = executeNullDate(dateInput).substring(0,5).split(":");
        return data[0] +"h" + data[1];
    }
    //----------------------------------------------
    public static String getHoursBoardingTimeFormat(Date dateInput){
        String data = executeNullDate(dateInput).substring(0,5);
        return data.trim();
    }
    //----------------------------------------------
    public static String geFormatDateInBoarding(Date dateInput) throws ParseException{
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        String data = executeNullDate(dateInput).substring(6);
        Date date = formatDate.parse(data);
        SimpleDateFormat formatBoarding = new SimpleDateFormat("MMMMM dd, yyyy");
        data = formatBoarding.format(date);
        return data.trim().toUpperCase();
    }
    //-------------------------------------------------
     public static boolean checkStringWithFormat(String value,String pattern){
         boolean result = false;
         if(value.matches(pattern)){
             result = true;                     
         }
         return result;
    }
    //------------------------------------------------------
    public static boolean checkStringEmpty(String value) {
        boolean result = true;
         if(Validation.removeBlankString(value).isEmpty()){
             result = false;                     
         }
         return result;        
    }
    //------------------------------------------------------
    public static String removeBlankString(String str) {
        String result = " ";
        String[] data = str.trim().split("\\s+");
        for(int i  =0 ; i < data.length; i++){
            if(!data[i].isEmpty()){
                result += data[i] +" ";
            }
        }
        return result.trim();
    }
    //------------------------------------------------------
     public static String getIDAuto(String prefix, int number){
         String result = prefix.trim();
         String formatNumber = String.format("%04d",number); 
         return result+formatNumber;
     }
     //------------------------------------------------------
      public static void sortSeatsAndDisplay(HashMap<String,String> list) {
        ArrayList<String> sortedKeys = new ArrayList<>(list.keySet());
        if(sortedKeys.isEmpty()) return;
        Collections.sort(sortedKeys, (String s1, String s2) -> {
            String s1Row = s1.substring(0, 1);
            String s2Row = s2.substring(0, 1);
            int s1Num = Integer.parseInt(s1.substring(1));
            int s2Num = Integer.parseInt(s2.substring(1));
            
            if (s1Num == s2Num) {
                return s1Row.compareTo(s2Row);
            } else {
                return Integer.compare(s1Num, s2Num);
            }
        });

        // Hiển thị danh sách đã sắp xếp
        int count = 0;
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("\t\t\t the availability of seats".toUpperCase());
        System.out.println("---------------------------------------------------------------------------------------------");
        for (String x : sortedKeys){
            if((list.get(x) == null) || list.get(x).equals("NULL")){
                System.out.print(x +"  \t");
            }else {
                System.out.print(" " +"  \t");
            }
            
            count++;
            if(count == 6){
                System.out.println("");
                count =0;
            }
        }
    }
    //--------------------------------
     public static String convertNullString(String str){
         if(Validation.removeBlankString(str).isEmpty()){
             return "N/A";
         }
         return Validation.removeBlankString(str);
     }
    //--------------------------------
    public static String getTypeOfCrew(int index){
        switch(index){
            case 1:
                return Position.PILOT.getTYPE();
            case 2:
                return Position.FLIGHT_ATTENDANT.getTYPE();
            case 3:
                return Position.GROUND_STAFF.getTYPE();
        }
        return null;
    } 
    //--------------------------------
    public static List<Flight> sortFlightList(List<Flight> list){
 
        for(int i =0; i < list.size()-1; i++){
            int min_index = i;
            for(int j = i+1; j < list.size(); j++){
                if(list.get(min_index).getDepartureTime() == null){
                    min_index = j;
                    continue;
                }
                if(list.get(j).getDepartureTime() == null){
                    continue;
                }
                int date1 = (int)list.get(min_index).getDepartureTime().getTime();
                int date2 = (int)list.get(j).getDepartureTime().getTime();
                if(date2 > date1){
                    min_index = j;
                }
            }
            
            Flight temp = list.get(min_index);
            list.set(min_index, list.get(i));
            list.set(i, temp);
        }
        return list;
    }
    //--------------------------------
    public static boolean checkStringInListString(List<String> list,String str){
        for(String string : list){
            if(string.equals(str)){
                return true;
            }
        }
        return false;
    }
    //--------------------------------
    public static int subDate(Date date1, Date date2) {
        long dayNumber = date2.getTime() - date1.getTime();
        return (int)dayNumber/(1000*60*60); // giờ
    }
     //--------------------------------
    public static Date getNowDate(){
        Date nowDate = new Date();
        return nowDate;
    }
}
