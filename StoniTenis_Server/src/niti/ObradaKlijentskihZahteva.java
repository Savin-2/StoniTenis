/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import domen.Mesto;
import domen.Rezervacija;
import domen.Sportista;
import domen.TerminDezurstva;
import domen.Zaposleni;
import domen.ZaposleniTermin;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import operacije.rezervacija.IzmeniRezervacijuSO;
import operacije.rezervacija.KreirajRezervacijuSO;
import operacije.rezervacija.PretraziRezervacijuSO;
import operacije.rezervacija.UcitajRezervacijeSO;
import operacije.sportisti.PretraziSportistuSO;
import operacije.sto.UcitajStoloveSO;
import operacije.terminidezurstva.UcitajZaposleneTermineSO;
import operacije.zaposleni.UcitajZaposleneSO;

/**
 *
 * @author Savin
 */
public class ObradaKlijentskihZahteva extends Thread {

    Socket socket;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
        posiljalac = new Posiljalac(socket);
        primalac = new Primalac(socket);
    }

    @Override
    public void run() {

        while (!kraj) {
            try {
                Zahtev zahtev = (Zahtev) primalac.primi();
                Odgovor odgovor = new Odgovor();
                switch (zahtev.getOperacija()) {
                    case LOGIN:
                        try {
                            Zaposleni z = (Zaposleni) zahtev.getParametar();
                            z = controller.Controller.getInstance().login(z);
                            odgovor.setOdgovor(z);
                        } catch (Exception e) {
                            odgovor.setOdgovor(null);
                        }
                        break;
                    case UCITAJ_SPORTISTE:
                        List<Sportista> sportisti = Controller.getInstance().ucitajSportiste();
                        odgovor.setOdgovor(sportisti);
                        break;
                    case OBRISI_SPORTISTU:
                        try {
                            Sportista s = (Sportista) zahtev.getParametar();
                            controller.Controller.getInstance().obrisiSportistu(s);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case UCITAJ_MESTA:
                        List<Mesto> mesta = Controller.getInstance().ucitajMesta();
                        odgovor.setOdgovor(mesta);
                        break;
                    case DODAJ_SPORTISTU:
                        try {
                            Sportista s = (Sportista) zahtev.getParametar();
                            Controller.getInstance().dodajSportistu(s);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case IZMENI_SPORTISTU:
                        try {
                            Sportista s = (Sportista) zahtev.getParametar();
                            Controller.getInstance().izmeniSportistu(s);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case UCITAJ_TERMINE_DEZURSTAVA:
                        try {
                            List<TerminDezurstva> termini = Controller.getInstance().ucitajTermineDezurstava();
                            odgovor.setOdgovor(termini);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;

                    case DODAJ_ZAPOSLENI_TERMIN:
                        try {
                            ZaposleniTermin zt = (ZaposleniTermin) zahtev.getParametar();
                            Controller.getInstance().dodajZaposleniTermin(zt);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case UCITAJ_ZAPOSLENE_TERMINE:
                        try {
                            UcitajZaposleneTermineSO so = new UcitajZaposleneTermineSO();
                            so.izvrsi(null, null);
                            odgovor.setOdgovor(so.getTermini());
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case UCITAJ_STOLOVE:
                        try {
                            UcitajStoloveSO so = new UcitajStoloveSO();
                            so.izvrsi(null, null);
                            odgovor.setOdgovor(so.getStolovi());
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;

                    case KREIRAJ_REZERVACIJU:
                        try {
                            Rezervacija r = (Rezervacija) zahtev.getParametar();
                            new KreirajRezervacijuSO().izvrsi(r, null);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case UCITAJ_ZAPOSLENE:
                        try {
                            UcitajZaposleneSO so = new UcitajZaposleneSO();
                            so.izvrsi(null, null);
                            odgovor.setOdgovor(so.getZaposleni());
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;

                    case UCITAJ_REZERVACIJE:
                        try {
                            UcitajRezervacijeSO so = new UcitajRezervacijeSO();
                            so.izvrsi(null, null);
                            odgovor.setOdgovor(so.getRezervacije());
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case IZMENI_REZERVACIJU:
                        try {
                            Rezervacija r = (Rezervacija) zahtev.getParametar();
                            new IzmeniRezervacijuSO().izvrsi(r, null);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case PRETRAZI_REZERVACIJU:
                        try {
                            Rezervacija r = (Rezervacija) zahtev.getParametar();
                            PretraziRezervacijuSO so = new PretraziRezervacijuSO();
                            so.izvrsi(r, null);
                            odgovor.setOdgovor(so.getRezervacije());
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case PRETRAZI_SPORTISTU:
                        try {
                            Sportista s = (Sportista) zahtev.getParametar();
                            PretraziSportistuSO so = new PretraziSportistuSO();
                            so.izvrsi(s, null);
                            odgovor.setOdgovor(so.getSportisti());
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    default:
                        System.out.println("GRESKA, TA OPERACIJA NE POSTOJI");
                }
                posiljalac.posalji(odgovor);
            } catch (Exception ex) {
                System.getLogger(ObradaKlijentskihZahteva.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }

    public void prekini() throws IOException {

        kraj = true;
        socket.close();
        interrupt();
    }

}
