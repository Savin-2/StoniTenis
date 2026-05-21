/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.rezervacija;

import domen.Rezervacija;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Savin
 */
public class UcitajRezervacijeSO extends ApstraktnaGenerickaOperacija {

    private List<Rezervacija> rezervacije;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        rezervacije = broker.getAll(new Rezervacija(), "");
    }

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }
}
