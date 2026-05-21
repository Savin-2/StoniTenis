/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Mesto;
import domen.Sportista;
import domen.TerminDezurstva;
import domen.Zaposleni;
import domen.ZaposleniTermin;
import java.util.List;
import operacije.sportisti.UcitajSportisteSO;
import operacije.login.LoginOperacija;
import operacije.mesto.UcitajMestaSO;
import operacije.sportisti.DodajSportistuSO;
import operacije.sportisti.IzmeniSportistuSO;
import operacije.sportisti.ObrisiSportistuSO;
import operacije.terminidezurstva.DodajZaposleniTerminSO;
import operacije.terminidezurstva.UcitajTermineDezurstavaSO;

/**
 *
 * @author Savin
 */
public class Controller {

    private static Controller instance;

    private Controller() {
    }

    public static Controller getInstance() {

        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Zaposleni login(Zaposleni z) throws Exception {
        LoginOperacija operacija = new LoginOperacija();
        operacija.izvrsi(z, null);
        System.out.println("KLASA CONTROLLER: " + operacija.getZaposleni());
        return operacija.getZaposleni();
    }

    public List<Sportista> ucitajSportiste() throws Exception {

        UcitajSportisteSO operacija = new UcitajSportisteSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA CONTROLLER :" + operacija.getSportisti());
        return operacija.getSportisti();
    }

    public void obrisiSportistu(Sportista s) throws Exception {

        ObrisiSportistuSO operacija = new ObrisiSportistuSO();
        operacija.izvrsi(s, null);

    }

    public List<Mesto> ucitajMesta() throws Exception {
        UcitajMestaSO operacija = new UcitajMestaSO();
        operacija.izvrsi(null, null);
        return operacija.getMesta();
    }

    public void dodajSportistu(Sportista s) throws Exception {

        DodajSportistuSO operacija = new DodajSportistuSO();
        operacija.izvrsi(s, null);

    }

    public void izmeniSportistu(Sportista s) throws Exception {
        IzmeniSportistuSO operacija = new IzmeniSportistuSO();
        operacija.izvrsi(s, null);
    }

    public List<TerminDezurstva> ucitajTermineDezurstava() throws Exception {
        UcitajTermineDezurstavaSO operacija = new UcitajTermineDezurstavaSO();
        operacija.izvrsi(null, null);
        return operacija.getTermini();
    }

    public void dodajZaposleniTermin(ZaposleniTermin zt) throws Exception {
        DodajZaposleniTerminSO operacija = new DodajZaposleniTerminSO();
        operacija.izvrsi(zt, null);
    }
    

}
