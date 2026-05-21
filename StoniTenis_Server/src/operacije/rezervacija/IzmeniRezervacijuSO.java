/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.rezervacija;

import domen.ApstraktniDomenskiObjekat;
import domen.Rezervacija;
import domen.StavkaRezervacije;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;
import java.time.LocalDate;

/**
 *
 * @author Savin
 */
public class IzmeniRezervacijuSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Rezervacija)) {
            throw new Exception("Neispravan parametar");
        }
        Rezervacija r = (Rezervacija) param;
        if (r.getSportista() == null) {
            throw new Exception("Sportista nije odabran");
        }
        if (r.getDatumRezervacija() == null) {
            throw new Exception("Datum nije unet");
        }
        if (r.getStavka() == null || r.getStavka().isEmpty()) {
            throw new Exception("Rezervacija mora imati bar jednu stavku");
        }

        if (r.getDatumRezervacija().toLocalDate().isBefore(LocalDate.now())) {
            throw new Exception("Ne može se izmeniti rezervacija za datum u prošlosti");
        }

        List<ApstraktniDomenskiObjekat> sveStavke = broker.getAll(new StavkaRezervacije(), "");
        for (StavkaRezervacije novaStavka : r.getStavka()) {
            for (ApstraktniDomenskiObjekat obj : sveStavke) {
                StavkaRezervacije postojeca = (StavkaRezervacije) obj;

                if (postojeca.getRezervacija() != null
                        && postojeca.getRezervacija().getIdRezervacija() == r.getIdRezervacija()) {
                    continue;
                }
                if (postojeca.getSto().getIdSto() == novaStavka.getSto().getIdSto()
                        && postojeca.getRezervacija().getDatumRezervacija().toLocalDate()
                                .equals(r.getDatumRezervacija().toLocalDate())
                        && novaStavka.getVremePocetka().isBefore(postojeca.getVremeZavrsetka())
                        && novaStavka.getVremeZavrsetka().isAfter(postojeca.getVremePocetka())) {
                    throw new Exception("Sto je već rezervisan u tom terminu");
                }
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Rezervacija r = (Rezervacija) objekat;
        broker.edit(r);
        List<ApstraktniDomenskiObjekat> stareStavke = broker.getAll(new StavkaRezervacije(),
                "stavkarezervacije.idRezervacija=" + r.getIdRezervacija());
        for (ApstraktniDomenskiObjekat obj : stareStavke) {
            broker.delete((StavkaRezervacije) obj);
        }
        for (StavkaRezervacije s : r.getStavka()) {
            s.setRezervacija(r);
            broker.add(s);
        }
    }
}
