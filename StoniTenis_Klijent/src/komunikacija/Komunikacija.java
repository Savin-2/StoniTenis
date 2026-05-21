/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Mesto;
import domen.Rezervacija;
import domen.Sportista;
import domen.StavkaRezervacije;
import domen.Sto;
import domen.TerminDezurstva;
import domen.Zaposleni;
import domen.ZaposleniTermin;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Savin
 */
public class Komunikacija {

    private Socket soket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    private static Komunikacija instance;

    private Komunikacija() {

    }

    public static Komunikacija getInstance() {

        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    public void konekcija() {

        try {
            soket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(soket);
            primalac = new Primalac(soket);
        } catch (IOException ex) {
            System.out.println("SERVER NIJE POVEZAN");
        }

    }

    public Zaposleni login(String username, String password) {
        Zaposleni z = new Zaposleni();
        z.setSifra(password);
        z.setKorisnickoIme(username);
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, z);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();

        z = (Zaposleni) odg.getOdgovor();

        return z;
    }

    public List<Sportista> ucitajSportiste() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_SPORTISTE, null);
        List<Sportista> sportisti = new ArrayList<>();
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        sportisti = (List<Sportista>) odg.getOdgovor();
        return sportisti;
    }

    public void obrisiSportistu(Sportista s) throws Exception {

        Zahtev zahtev = new Zahtev(Operacija.OBRISI_SPORTISTU, s);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("USPEH");
        } else {
            System.out.println("GREŠKA");
            ((Exception) odg.getOdgovor()).printStackTrace();
            throw new Exception("GREšKA");
        }
    }

    public List<Mesto> ucitajMesta() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_MESTA, null);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        return (List<Mesto>) odg.getOdgovor();
    }

    public void dodajSportistu(Sportista s) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_SPORTISTU, s);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() instanceof Exception) {
            throw (Exception) odg.getOdgovor();
        }
    }

    public void izmeniSportistu(Sportista s) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_SPORTISTU, s);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() instanceof Exception) {
            throw (Exception) odg.getOdgovor();
        }
    }

    public List<TerminDezurstva> ucitajTermineDezurstava() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_TERMINE_DEZURSTAVA, null);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() instanceof Exception) {
            throw (Exception) odg.getOdgovor();
        }
        return (List<TerminDezurstva>) odg.getOdgovor();
    }

    public void dodajZaposleniTermin(ZaposleniTermin zt) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_ZAPOSLENI_TERMIN, zt);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() instanceof Exception) {
            throw (Exception) odg.getOdgovor();
        }
    }

    public List<ZaposleniTermin> ucitajZaposleneTermine() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_ZAPOSLENE_TERMINE, null);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() instanceof Exception) {
            throw (Exception) odg.getOdgovor();
        }
        return (List<ZaposleniTermin>) odg.getOdgovor();
    }

    public List<Sto> ucitajStolove() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_STOLOVE, null);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() instanceof Exception) {
            throw (Exception) odg.getOdgovor();
        }
        return (List<Sto>) odg.getOdgovor();
    }

    public void kreirajRezervaciju(Rezervacija r) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.KREIRAJ_REZERVACIJU, r);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() instanceof Exception) {
            throw (Exception) odg.getOdgovor();
        }
    }

    public List<Zaposleni> ucitajZaposlene() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_ZAPOSLENE, null);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() instanceof Exception) {
            throw (Exception) odg.getOdgovor();
        }
        return (List<Zaposleni>) odg.getOdgovor();
    }

    public List<Rezervacija> ucitajRezervacije() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_REZERVACIJE, null);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() instanceof Exception) {
            throw (Exception) odg.getOdgovor();
        }
        return (List<Rezervacija>) odg.getOdgovor();
    }

    public void izmeniRezervaciju(Rezervacija r) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_REZERVACIJU, r);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() instanceof Exception) {
            throw (Exception) odg.getOdgovor();
        }
    }

    public List<Rezervacija> pretraziRezervaciju(Rezervacija r) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_REZERVACIJU, r);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() instanceof Exception) {
            throw (Exception) odg.getOdgovor();
        }
        return (List<Rezervacija>) odg.getOdgovor();
    }

    public List<Sportista> pretraziSportistu(Sportista s) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_SPORTISTU, s);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() instanceof Exception) {
            throw (Exception) odg.getOdgovor();
        }
        return (List<Sportista>) odg.getOdgovor();
    }
}
