/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.sto;

import domen.Sto;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Savin
 */
public class UcitajStoloveSO extends ApstraktnaGenerickaOperacija {

    private List<Sto> stolovi;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        stolovi = broker.getAll(new Sto(), "");
    }

    public List<Sto> getStolovi() {
        return stolovi;
    }
}
