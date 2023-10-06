/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Entity.Seat;


import java.util.HashMap;

/**
 *
 * @author nhutm
 */
public class Seat {
    private String flightNumber;
    private int seatAvailable;
    private HashMap<String,String> seats; 

    public Seat() {
       
    }
    
    public Seat(String flightNumber, int seatAvailable) {
        this.flightNumber = flightNumber;
        this.seatAvailable = seatAvailable;
        this.seats = initSeatHashMap(seatAvailable);
    }
    
    public int getSeatAvailable() {
        return seatAvailable;
    }

    public void setSeatAvailable(int seatAvailable) {
        this.seatAvailable = seatAvailable;
    }

    public Seat(String flightNumber, int seatAvailable, HashMap<String, String> seats) {
        this.flightNumber = flightNumber;
        this.seatAvailable = seatAvailable;
        this.seats = seats;
    }
    
   
    
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    
    public HashMap<String, String> getSeats() {
        return seats;
    }

    public void setSeats(HashMap<String, String> seats) {
        this.seats = seats;
    }
    
      public HashMap<String,String> initSeatHashMap(int seatAvailable) {
        HashMap<String,String> list = new HashMap<>();
        int numberSeat = (int)Math.floor(seatAvailable/6);
        int remainderNumberSeat = seatAvailable % 6;
        
        for(int a = 1; a <= numberSeat; a++){
            String tmp = String.format("A%02d",a);
            list.put(tmp, null);
        }
        for(int b = 1; b <= numberSeat; b++){
            String tmp = String.format("B%02d",b);
            list.put(tmp, null);
        }
        for(int c = 1; c <= numberSeat; c++){
            String tmp = String.format("C%02d",c);
            list.put(tmp, null);
        }
        for(int d = 1; d <= numberSeat; d++){
            String tmp = String.format("D%02d",d);
            list.put(tmp, null);
        }
        for(int e = 1; e <= numberSeat; e++){
            String tmp = String.format("E%02d",e);
            list.put(tmp, null);
        }
        for(int f = 1; f <= numberSeat; f++){
            String tmp = String.format("F%02d",f);
            list.put(tmp, null);
        }
        
        switch(remainderNumberSeat){
            case 5:
                String tmp = String.format("E%02d",numberSeat+1);
                list.put(tmp, null);
            case 4:
                tmp = String.format("D%02d",numberSeat+1);
                list.put(tmp, null);
            case 3:
                tmp = String.format("C%02d",numberSeat+1);
                list.put(tmp, null);
            case 2:
                tmp = String.format("B%02d",numberSeat+1);
                list.put(tmp, null);
            case 1:
                tmp = String.format("A%02d",numberSeat+1);
                list.put(tmp, null);
        }   
        return list;
    }
    
    public void display(){
       for(String i : seats.keySet()){
           if(seats.get(i) != null){
               if(!seats.get(i).equals("NULL"))
               System.out.printf("|  %-17s |  %-15s  |  %-4s  |\n",flightNumber,seats.get(i),i);
           }
       }
       for(int j = 0; j <= 50; j++) System.out.print("-");
       System.out.println("");
    }
    
}
