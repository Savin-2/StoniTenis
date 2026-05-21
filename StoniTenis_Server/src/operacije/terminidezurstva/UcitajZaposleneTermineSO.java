/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.terminidezurstva;

import domen.ZaposleniTermin;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Savin
 */
public class UcitajZaposleneTermineSO extends ApstraktnaGenerickaOperacija {
    private List<ZaposleniTermin> termini;
    
    @Override
    protected void preduslovi(Object param) throws Exception {}
    
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        termini = broker.getAll(new ZaposleniTermin(), "");
    }
    
    public List<ZaposleniTermin> getTermini() {
        return termini;
    }
}
