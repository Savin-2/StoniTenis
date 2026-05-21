/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.login;

import domen.Zaposleni;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Savin
 */
public class LoginOperacija extends ApstraktnaGenerickaOperacija {

    Zaposleni zaposleni;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Zaposleni)) {
            throw new Exception("Korisničko ime i šifra nisu ispravni");
        }
        Zaposleni z = (Zaposleni) param;
        if (z.getKorisnickoIme() == null || z.getKorisnickoIme().isEmpty()) {
            throw new Exception("Korisničko ime i šifra nisu ispravni");
        }
        if (z.getSifra() == null || z.getSifra().isEmpty()) {
            throw new Exception("Korisničko ime i šifra nisu ispravni");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<Zaposleni> rezultat = broker.getAll((Zaposleni) param, ((Zaposleni) param).vratiUslovZaPretragu());
        if (rezultat.isEmpty()) {
            zaposleni = null;
        } else {
            zaposleni = (Zaposleni) rezultat.get(0);
        }
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

}
