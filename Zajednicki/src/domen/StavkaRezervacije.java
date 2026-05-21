/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Savin
 */
public class StavkaRezervacije implements ApstraktniDomenskiObjekat {

    private int rb;
    private String nazivSt;
    private LocalTime vremePocetka;
    private LocalTime vremeZavrsetka;
    private int brojSati;
    private double iznos;
    private double cenaPoSatu;
    private Sto sto;
    private Rezervacija rezervacija;

    public StavkaRezervacije() {
    }

    public StavkaRezervacije(Rezervacija rezervacija, int rb, String nazivSt, LocalTime vremePocetka, LocalTime vremeZavrsetka, int brojSati, double iznos, double cenaPoSatu, Sto sto) {
        this.rezervacija = rezervacija;
        this.rb = rb;
        this.nazivSt = nazivSt;
        this.vremePocetka = vremePocetka;
        this.vremeZavrsetka = vremeZavrsetka;
        this.brojSati = brojSati;
        this.iznos = iznos;
        this.cenaPoSatu = cenaPoSatu;
        this.sto = sto;
    }

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public String getNazivSt() {
        return nazivSt;
    }

    public void setNazivSt(String nazivSt) {
        this.nazivSt = nazivSt;
    }

    public LocalTime getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(LocalTime vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public LocalTime getVremeZavrsetka() {
        return vremeZavrsetka;
    }

    public void setVremeZavrsetka(LocalTime vremeZavrsetka) {
        this.vremeZavrsetka = vremeZavrsetka;
    }

    public int getBrojSati() {
        return brojSati;
    }

    public void setBrojSati(int brojSati) {
        this.brojSati = brojSati;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public double getCenaPoSatu() {
        return cenaPoSatu;
    }

    public void setCenaPoSatu(double cenaPoSatu) {
        this.cenaPoSatu = cenaPoSatu;
    }

    public Sto getSto() {
        return sto;
    }

    public void setSto(Sto sto) {
        this.sto = sto;
    }

    @Override
    public String toString() {
        return "StavkaRezervacije{" + "rezervacija=" + rezervacija + ", nazivSt=" + nazivSt + ", vremePocetka=" + vremePocetka + ", vremeZavrsetka=" + vremeZavrsetka + ", iznos=" + iznos + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final StavkaRezervacije other = (StavkaRezervacije) obj;
        if (this.brojSati != other.brojSati) {
            return false;
        }
        if (!Objects.equals(this.vremePocetka, other.vremePocetka)) {
            return false;
        }
        if (!Objects.equals(this.vremeZavrsetka, other.vremeZavrsetka)) {
            return false;
        }
        return Objects.equals(this.sto, other.sto);
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkarezervacije JOIN rezervacija ON stavkarezervacije.idRezervacija = rezervacija.idRezervacija";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int rb = rs.getInt("stavkarezervacije.rb");
            String nazivSt = rs.getString("stavkarezervacije.nazivSt");
            LocalTime vremePocetka = LocalTime.parse(rs.getString("stavkarezervacije.vremePocetka"));
            LocalTime vremeZavrsetka = LocalTime.parse(rs.getString("stavkarezervacije.vremeZavrsetka"));
            int brojSati = rs.getInt("stavkarezervacije.brojSati");
            double iznos = rs.getDouble("stavkarezervacije.iznos");
            double cenaPoSatu = rs.getDouble("stavkarezervacije.cenaPoSatu");
            int idSto = rs.getInt("stavkarezervacije.idSto");
            int idRezervacije = rs.getInt("rezervacija.idRezervacija");

            String datumStr = rs.getString("rezervacija.datumRezervacija");
            LocalDateTime datum = LocalDate.parse(datumStr).atStartOfDay();

            Sto sto = new Sto();
            sto.setIdSto(idSto);

            Rezervacija rez = new Rezervacija();
            rez.setDatumRezervacija(datum);
            rez.setIdRezervacija(idRezervacije);

            StavkaRezervacije s = new StavkaRezervacije(rez, rb, nazivSt, vremePocetka, vremeZavrsetka, brojSati, iznos, cenaPoSatu, sto);
            lista.add(s);
        }
        return lista;
    }

    @Override
    public String vratiNazivTabeleBezJoin() {
        return "stavkarezervacije";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivSt,vremePocetka,vremeZavrsetka,brojSati,iznos,cenaPoSatu,idSto,idRezervacija";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + nazivSt + "','" + vremePocetka + "','" + vremeZavrsetka + "'," + brojSati + "," + iznos + "," + cenaPoSatu + "," + sto.getIdSto() + "," + rezervacija.getIdRezervacija();
    }

    @Override
    public String vratiPrimarniKljuc() {

        return "stavkarezervacije.rb=" + rb;

    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "nazivSt='" + nazivSt + "',vremePocetka='" + vremePocetka
                + "',vremeZavrsetka='" + vremeZavrsetka + "',brojSati=" + brojSati
                + ",iznos=" + iznos + ",cenaPoSatu=" + cenaPoSatu
                + ",idSto=" + sto.getIdSto()
                + " WHERE rb=" + rb;
    }
}
