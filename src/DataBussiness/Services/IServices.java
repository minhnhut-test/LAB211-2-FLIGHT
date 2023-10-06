/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Services;

import java.util.List;

/**
 *
 * @author nhutm
 * @param <T>
 */
public interface IServices<T> {
    //-----------------------------------
    List<T> getAll();
    //-----------------------------------
    void printAll();
     //-----------------------------------
    void saveToFile();
    //-----------------------------------
    void printHeadTable();
}
