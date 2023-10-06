/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.Dao.SeatDao;

import DataBussiness.Entity.Flight.Flight;
import DataLayer.Dao.IUserDao;
import DataBussiness.Entity.Seat.Seat;

/**
 *
 * @author nhutm
 */
public interface ISeatDao extends  IUserDao<Seat>{
    //-----------------Dùng để xóa list chổ ngồi khi chuyến bay bị xóa -------------------------
    void delete(String flightNumber);
    //-----------------Dùng để xóa người ngồi ở chổ ngồi truyền vào-------------------------
    void delete(String flightNumber, String seatChoice);
    //-----------------Dùng để tìm kiếm phần tử trong seatList qua thông số flightNumber và trả về Seat-------------------------
    Seat get(String flightNumber);
    //-----------------Dùng để tìm kiếm vị trí phần tử qua tham số flightNumber -------------------------
    int getIndex(String flightNumber);
    //-----------------Dùng để add vào phần tử Seat : flightNumber, seatAvailable , hashmap-------------------------
    void add(Seat seats);
    //-----------------Dùng để sắp xếp chổ ngồi cho khách-------------------
    boolean addSeatChoice(String flightNumber, String seatChoice, String IDReservation);
    //-----------------Dùng để update chổ ngồi khi khách yêu cầu đổi chổ--------------------------------
    void updateSeatChoice(String flightNumber,String newSeatChoice, String IDReservation);
    //-----------------Dùng để check xem chổ ngồi ở chuyến bay đó full chưa-----------------
    //-----------------update flight number-----------------
    void update(Flight flight, String oldFlight);
   
    
  
}
