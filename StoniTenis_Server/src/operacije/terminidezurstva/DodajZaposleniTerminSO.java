/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.terminidezurstva;

import domen.ApstraktniDomenskiObjekat;
import domen.ZaposleniTermin;
import operacije.ApstraktnaGenerickaOperacija;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Savin
 */
public class DodajZaposleniTerminSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof ZaposleniTermin)) {
            throw new Exception("Sistem ne može da doda termin dežurstva");
        }
        ZaposleniTermin zt = (ZaposleniTermin) param;
        if (zt.getDatumDezurstva() == null) {
            throw new Exception("Datum nije unet");
        }
        if (zt.getTerminDezurstva() == null) {
            throw new Exception("Termin dežurstva nije odabran");
        }

        if (!zt.getDatumDezurstva().isAfter(LocalDateTime.now())) {
            throw new Exception("Ne može se dodati termin sa datumom u prošlosti");
        }
        
        List<ApstraktniDomenskiObjekat> svi = broker.getAll(new ZaposleniTermin(), "");
        for (ApstraktniDomenskiObjekat obj : svi) {
            ZaposleniTermin postojeci = (ZaposleniTermin) obj;

            if (postojeci.getZaposleni().getIdZaposleni() == zt.getZaposleni().getIdZaposleni()
                    && postojeci.getDatumDezurstva().toLocalDate().equals(zt.getDatumDezurstva().toLocalDate())) {
                throw new Exception("Zaposleni već ima termin dežurstva na taj datum");
            }

            if (postojeci.getTerminDezurstva().getIdTerminDezurstva() == zt.getTerminDezurstva().getIdTerminDezurstva()
                    && postojeci.getDatumDezurstva().toLocalDate().equals(zt.getDatumDezurstva().toLocalDate())) {
                throw new Exception("Ta smena na taj datum već postoji");
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((ZaposleniTermin) objekat);
    }
}
