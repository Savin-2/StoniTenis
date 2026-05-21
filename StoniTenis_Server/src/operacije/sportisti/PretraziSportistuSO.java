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
public class PretraziSportistuSO extends ApstraktnaGenerickaOperacija {

    private List<Sportista> sportisti;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Sportista)) {
            throw new Exception("Neispravan parametar");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Sportista s = (Sportista) objekat;
        sportisti = broker.getAll(new Sportista(), s.vratiUslovZaPretragu());
    }

    public List<Sportista> getSportisti() {
        return sportisti;
    }
}
