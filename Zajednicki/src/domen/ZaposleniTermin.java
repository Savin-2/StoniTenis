/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Savin
 */
public class ZaposleniTermin implements ApstraktniDomenskiObjekat {

    private Zaposleni zaposleni;
    private TerminDezurstva terminDezurstva;
    private LocalDateTime datumDezurstva;

    public ZaposleniTermin() {
    }

    public ZaposleniTermin(Zaposleni zaposleni, TerminDezurstva terminDezurstva, LocalDateTime datumDezurstva) {
        this.zaposleni = zaposleni;
        this.terminDezurstva = terminDezurstva;
        this.datumDezurstva = datumDezurstva;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public TerminDezurstva getTerminDezurstva() {
        return terminDezurstva;
    }

    public void setTerminDezurstva(TerminDezurstva terminDezurstva) {
        this.terminDezurstva = terminDezurstva;
    }

    public LocalDateTime getDatumDezurstva() {
        return datumDezurstva;
    }

    public void setDatumDezurstva(LocalDateTime datumDezurstva) {
        this.datumDezurstva = datumDezurstva;
    }

    @Override
    public String toString() {
        return "ZaposleniTermin{" + "zaposleni=" + zaposleni + ", terminDezurstva=" + terminDezurstva + ", datumDezurstva=" + datumDezurstva + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ZaposleniTermin other = (ZaposleniTermin) obj;
        if (!Objects.equals(this.zaposleni, other.zaposleni)) {
            return false;
        }
        if (!Objects.equals(this.terminDezurstva, other.terminDezurstva)) {
            return false;
        }
        return Objects.equals(this.datumDezurstva, other.datumDezurstva);
    }

    @Override
    public String vratiNazivTabele() {
        return "zaposlenitermin JOIN zaposleni ON zaposlenitermin.idZaposleni = zaposleni.idZaposleni JOIN termindezurstva ON zaposlenitermin.idTerminDezurstva = termindezurstva.idTerminDezurstva";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idZaposleni = rs.getInt("zaposlenitermin.idZaposleni");
            String imeZaposleni = rs.getString("zaposleni.imeZaposleni");
            String prezimeZaposleni = rs.getString("zaposleni.prezimeZaposleni");
            int idTerminDezurstva = rs.getInt("zaposlenitermin.idTerminDezurstva");
            String nazivSmena = rs.getString("termindezurstva.nazivSmena");
            String datumStr = rs.getString("zaposlenitermin.datumDezurstva");

            Zaposleni z = new Zaposleni(idZaposleni, imeZaposleni, prezimeZaposleni, null, null, null);
            TerminDezurstva td = new TerminDezurstva(idTerminDezurstva, nazivSmena);
            LocalDateTime datum = LocalDateTime.parse(datumStr + "T00:00:00");
            ZaposleniTermin zt = new ZaposleniTermin(z, td, datum);
            lista.add(zt);
        }
        return lista;
    }

    @Override
    public String vratiNazivTabeleBezJoin() {
        return "zaposlenitermin";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idZaposleni,idTerminDezurstva,datumDezurstva";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return zaposleni.getIdZaposleni() + "," + terminDezurstva.getIdTerminDezurstva() + ",'" + datumDezurstva + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {

        return "zaposlenitermin.idZaposleni=" + zaposleni.getIdZaposleni() + " AND zaposlenitermin.idTerminDezurstva=" + terminDezurstva.getIdTerminDezurstva() + " AND zaposlenitermin.datumDezurstva='" + datumDezurstva + "'";

    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
