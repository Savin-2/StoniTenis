/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koordinator;

import domen.Rezervacija;
import domen.Sportista;
import domen.StavkaRezervacije;
import domen.Zaposleni;
import forme.DodajSportistuForma;
import forme.DodajTerminDezurstvaForma;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazDezurstavaForma;
import forme.PrikazRezervacijaForma;
import forme.PrikazSportistaForma;
import kontroleri.DodajSportistuController;
import kontroleri.DodajTerminDezurstvaController;
import kontroleri.GlavnaFormaController;
import kontroleri.LoginController;
import kontroleri.PrikazDezurstavaController;
import kontroleri.PrikazRezervacijaController;
import kontroleri.PrikazSportistaController;

/**
 *
 * @author Savin
 */
public class Koordinator {

    private static Koordinator instance;
    Zaposleni ulogovani;
    private LoginController loginController;
    private GlavnaFormaController glavnaFormaController;
    private PrikazSportistaController psController;
    private DodajSportistuController dsController;
    private DodajTerminDezurstvaController dtdController;
    private PrikazDezurstavaController pdController;
    private PrikazRezervacijaController prController;

    private Koordinator() {
    }

    public static Koordinator getInstance() {

        if (instance == null) {

            instance = new Koordinator();

        }
        return instance;
    }

    public void otvoriLoginFormu() {
        loginController = new LoginController(new LoginForma());
        loginController.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {

        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriFormu();

    }

    public void otvoriDodajSportistuFormu() {

        dsController = new DodajSportistuController(new DodajSportistuForma());
        dsController.otvoriFormu();

    }

    public void otvoriPrikazSportistaFormu() {

        psController = new PrikazSportistaController(new PrikazSportistaForma());
        psController.otvoriFormu();

    }

    public Zaposleni getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Zaposleni ulogovani) {
        this.ulogovani = ulogovani;
    }

    public void refreshSportisti() {
        if (psController != null) {
            psController.osveziTabelu();
        }
        glavnaFormaController.osveziComboBoxSportisti();
    }

    public void otvoriIzmeniSportistuFormu(Sportista s) {
        dsController = new DodajSportistuController(new DodajSportistuForma(), s);
        dsController.otvoriFormu();
    }

    public void otvoriDodajTerminDezurstvaFormu() throws Exception {
        dtdController = new DodajTerminDezurstvaController(new DodajTerminDezurstvaForma());
        dtdController.otvoriFormu();
    }

    public void otvoriPrikazTerminaDezurstavaFormu() throws Exception {
        pdController = new PrikazDezurstavaController(new PrikazDezurstavaForma());
        pdController.otvoriFormu();
    }

    public void dodajStavkuNaGlavnuFormu(StavkaRezervacije stavka) {
        glavnaFormaController.dodajStavku(stavka);
    }

    public void otvoriPrikazRezervacijaFormu() {
        prController = new PrikazRezervacijaController(new PrikazRezervacijaForma());
        prController.otvoriFormu();
    }

    public void otvoriIzmenuRezervacije(Rezervacija r) {
        glavnaFormaController.otvoriFormuZaIzmenu(r);
    }

    public void resetujGlavnuFormu() {
        glavnaFormaController.resetujFormu();
    }

    public void izmeniStavkuNaGlavnojFormi(StavkaRezervacije stavka, int index) {
        glavnaFormaController.izmeniStavku(stavka, index);
    }

   
}
