/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.sportisti;

import domen.Sportista;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Savin
 */
public class UcitajSportisteSO extends ApstraktnaGenerickaOperacija {

    List<Sportista> sportisti;
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {

        sportisti = broker.getAll(new Sportista(), "");
        
    }

    public List<Sportista> getSportisti() {
        return sportisti;
    }
    
    
}
