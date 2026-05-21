/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.sportisti;

import domen.Sportista;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Savin
 */
public class ObrisiSportistuSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param==null || !(param instanceof Sportista)){
            throw new Exception("Sistem ne može da obriše sportistu");
        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {

        broker.delete((Sportista)objekat);

    }
    
}
