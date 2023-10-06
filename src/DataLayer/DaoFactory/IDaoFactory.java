/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.DaoFactory;

import DataLayer.Dao.AssignmentDao.IAssignmentDao;
import DataLayer.Dao.BoardingPassesDao.IBoardingPassesDao;
import DataLayer.Dao.FlightDao.IFlightDao;
import DataLayer.Dao.Person.ICrewDao;
import DataLayer.Dao.Person.ICustomerDao;
import DataLayer.Dao.SeatDao.ISeatDao;

/**
 *
 * @author nhutm
 */
public interface IDaoFactory {
    IFlightDao flightDao();
    ISeatDao seatDao();
    ICrewDao crewDao();
    ICustomerDao customerDao();
    IBoardingPassesDao boardingPassesDao();
    IAssignmentDao asssignmentDao();
}
