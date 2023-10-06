/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.DaoFactory;

import DataLayer.Dao.AssignmentDao.AssignmentDao;
import DataLayer.Dao.AssignmentDao.IAssignmentDao;
import DataLayer.Dao.BoardingPassesDao.BoardingPassesDao;
import DataLayer.Dao.BoardingPassesDao.IBoardingPassesDao;
import DataLayer.Dao.FlightDao.FlightDao;
import DataLayer.Dao.FlightDao.IFlightDao;
import DataLayer.Dao.Person.CrewDao;
import DataLayer.Dao.Person.CustomerDao;
import DataLayer.Dao.Person.ICrewDao;
import DataLayer.Dao.Person.ICustomerDao;
import DataLayer.Dao.SeatDao.ISeatDao;
import DataLayer.Dao.SeatDao.SeatDao;

/**
 *
 * @author nhutm
 */
public class DaoFactory implements  IDaoFactory{

    public DaoFactory() {
        
    }
    
    @Override
    public IFlightDao flightDao() {
       return new FlightDao();
    }

    @Override
    public ISeatDao seatDao() {
        return new SeatDao();
    }

    @Override
    public ICrewDao crewDao() {
        return new CrewDao();
    }

    @Override
    public ICustomerDao customerDao() {
        return new CustomerDao();
    }

    @Override
    public IBoardingPassesDao boardingPassesDao() {
        return new BoardingPassesDao();
    }

    @Override
    public IAssignmentDao asssignmentDao() {
       return new AssignmentDao();
    }
    
}
