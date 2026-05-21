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
public class IzmeniSportistuSO extends ApstraktnaGenerickaOperacija {
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Sportista)) {
            throw new Exception("Sistem ne može da izmeni sportistu");
        }
        Sportista s = (Sportista) param;
        if (s.getImeSportista() == null || s.getImeSportista().isEmpty() || s.getImeSportista().length() < 3) {
            throw new Exception("Ime nije uneto u dobrom formatu");
        }
        if (s.getPrezimeSportista() == null || s.getPrezimeSportista().isEmpty() || s.getPrezimeSportista().length() < 3) {
            throw new Exception("Prezime nije uneto u dobrom formatu");
        }
    }
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.edit((Sportista) objekat);
    }
}
