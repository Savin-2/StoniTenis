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
public class PretraziRezervacijuSO extends ApstraktnaGenerickaOperacija {

    private List<Rezervacija> rezervacije;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Rezervacija)) {
            throw new Exception("Neispravan parametar");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Rezervacija r = (Rezervacija) objekat;
        rezervacije = broker.getAll(new Rezervacija(), r.vratiUslovZaPretragu());
    }

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }
}
