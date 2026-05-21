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
                            z = Controller.getInstance().login(z);
                            odgovor.setOdgovor(z);
                        } catch (Exception e) {
                            odgovor.setOdgovor(null);
                        }
                        break;
                    case UCITAJ_SPORTISTE:
                        try {
                            List<Sportista> sportisti = Controller.getInstance().ucitajSportiste();
                            odgovor.setOdgovor(sportisti);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case OBRISI_SPORTISTU:
                        try {
                            Sportista s = (Sportista) zahtev.getParametar();
                            Controller.getInstance().obrisiSportistu(s);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case UCITAJ_MESTA:
                        try {
                            List<Mesto> mesta = Controller.getInstance().ucitajMesta();
                            odgovor.setOdgovor(mesta);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
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
                            List<ZaposleniTermin> termini = Controller.getInstance().ucitajZaposleneTermine();
                            odgovor.setOdgovor(termini);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case UCITAJ_STOLOVE:
                        try {
                            List stolovi = Controller.getInstance().ucitajStolove();
                            odgovor.setOdgovor(stolovi);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case KREIRAJ_REZERVACIJU:
                        try {
                            Rezervacija r = (Rezervacija) zahtev.getParametar();
                            Controller.getInstance().kreirajRezervaciju(r);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case UCITAJ_ZAPOSLENE:
                        try {
                            List<Zaposleni> zaposleni = Controller.getInstance().ucitajZaposlene();
                            odgovor.setOdgovor(zaposleni);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case UCITAJ_REZERVACIJE:
                        try {
                            List<Rezervacija> rezervacije = Controller.getInstance().ucitajRezervacije();
                            odgovor.setOdgovor(rezervacije);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case IZMENI_REZERVACIJU:
                        try {
                            Rezervacija r = (Rezervacija) zahtev.getParametar();
                            Controller.getInstance().izmeniRezervaciju(r);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case PRETRAZI_REZERVACIJU:
                        try {
                            Rezervacija r = (Rezervacija) zahtev.getParametar();
                            List<Rezervacija> rezervacije = Controller.getInstance().pretraziRezervaciju(r);
                            odgovor.setOdgovor(rezervacije);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case PRETRAZI_SPORTISTU:
                        try {
                            Sportista s = (Sportista) zahtev.getParametar();
                            List<Sportista> sportisti = Controller.getInstance().pretraziSportiste(s);
                            odgovor.setOdgovor(sportisti);
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
