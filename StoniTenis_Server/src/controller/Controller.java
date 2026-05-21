package controller;

import domen.Mesto;
import domen.Rezervacija;
import domen.Sportista;
import domen.TerminDezurstva;
import domen.Zaposleni;
import domen.ZaposleniTermin;
import java.util.List;
import operacije.login.LoginOperacija;
import operacije.mesto.UcitajMestaSO;
import operacije.rezervacija.IzmeniRezervacijuSO;
import operacije.rezervacija.KreirajRezervacijuSO;
import operacije.rezervacija.PretraziRezervacijuSO;
import operacije.rezervacija.UcitajRezervacijeSO;
import operacije.sportisti.DodajSportistuSO;
import operacije.sportisti.IzmeniSportistuSO;
import operacije.sportisti.ObrisiSportistuSO;
import operacije.sportisti.PretraziSportistuSO;
import operacije.sportisti.UcitajSportisteSO;
import operacije.sto.UcitajStoloveSO;
import operacije.terminidezurstva.DodajZaposleniTerminSO;
import operacije.terminidezurstva.UcitajTermineDezurstavaSO;
import operacije.terminidezurstva.UcitajZaposleneTermineSO;
import operacije.zaposleni.UcitajZaposleneSO;

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
        return operacija.getZaposleni();
    }

    public List<Sportista> ucitajSportiste() throws Exception {
        UcitajSportisteSO operacija = new UcitajSportisteSO();
        operacija.izvrsi(null, null);
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

    public List<ZaposleniTermin> ucitajZaposleneTermine() throws Exception {
        UcitajZaposleneTermineSO operacija = new UcitajZaposleneTermineSO();
        operacija.izvrsi(null, null);
        return operacija.getTermini();
    }

    public List ucitajStolove() throws Exception {
        UcitajStoloveSO operacija = new UcitajStoloveSO();
        operacija.izvrsi(null, null);
        return operacija.getStolovi();
    }

    public void kreirajRezervaciju(Rezervacija r) throws Exception {
        KreirajRezervacijuSO operacija = new KreirajRezervacijuSO();
        operacija.izvrsi(r, null);
    }

    public List<Zaposleni> ucitajZaposlene() throws Exception {
        UcitajZaposleneSO operacija = new UcitajZaposleneSO();
        operacija.izvrsi(null, null);
        return operacija.getZaposleni();
    }

    public List<Rezervacija> ucitajRezervacije() throws Exception {
        UcitajRezervacijeSO operacija = new UcitajRezervacijeSO();
        operacija.izvrsi(null, null);
        return operacija.getRezervacije();
    }

    public void izmeniRezervaciju(Rezervacija r) throws Exception {
        IzmeniRezervacijuSO operacija = new IzmeniRezervacijuSO();
        operacija.izvrsi(r, null);
    }

    public List<Rezervacija> pretraziRezervaciju(Rezervacija r) throws Exception {
        PretraziRezervacijuSO operacija = new PretraziRezervacijuSO();
        operacija.izvrsi(r, null);
        return operacija.getRezervacije();
    }

    public List<Sportista> pretraziSportiste(Sportista s) throws Exception {
        PretraziSportistuSO operacija = new PretraziSportistuSO();
        operacija.izvrsi(s, null);
        return operacija.getSportisti();
    }
}