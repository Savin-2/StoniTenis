/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repozitorijum;

import java.util.List;

/**
 *
 * @author Savin
 */
public interface Repozitorijum<T> {
    
     List<T> getAll(T param, String uslov) throws Exception;
     void add(T param) throws Exception;
     void edit(T param) throws Exception;
     void delete(T Param) throws Exception;
     
     List<T> getAll();
    
}
