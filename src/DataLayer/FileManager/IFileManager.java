/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.FileManager;


import java.util.List;

/**
 *
 * @author nhutm
 */
public interface IFileManager<T> {
     List<T> readDataFromFile(String url) ;
     void storeDataToFile(String url,List<T> list);
}
