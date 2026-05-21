/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.terminidezurstva;

import domen.TerminDezurstva;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Savin
 */
public class UcitajTermineDezurstavaSO extends ApstraktnaGenerickaOperacija {

    private List<TerminDezurstva> termini;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        termini = broker.getAll(new TerminDezurstva(), "");
    }

    public List<TerminDezurstva> getTermini() {
        return termini;
    }
}
