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
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Savin
 */
public class Rezervacija implements ApstraktniDomenskiObjekat {

    private int idRezervacija;
    private String napomena;
    private double ukupanIznos;
    private LocalDateTime datumRezervacija;
    private Zaposleni zaposleni;
    private Sportista sportista;
    private List<StavkaRezervacije> stavka = new ArrayList<>();

    public Rezervacija() {
    }

    public Rezervacija(int idRezervacija, String napomena, double ukupanIznos, LocalDateTime datumRezervacija, Zaposleni zaposleni, Sportista sportista) {
        this.idRezervacija = idRezervacija;
        this.napomena = napomena;
        this.ukupanIznos = ukupanIznos;
        this.datumRezervacija = datumRezervacija;
        this.zaposleni = zaposleni;
        this.sportista = sportista;
    }

    public int getIdRezervacija() {
        return idRezervacija;
    }

    public void setIdRezervacija(int idRezervacija) {
        this.idRezervacija = idRezervacija;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public LocalDateTime getDatumRezervacija() {
        return datumRezervacija;
    }

    public void setDatumRezervacija(LocalDateTime datumRezervacija) {
        this.datumRezervacija = datumRezervacija;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Sportista getSportista() {
        return sportista;
    }

    public void setSportista(Sportista sportista) {
        this.sportista = sportista;
    }

    public List<StavkaRezervacije> getStavka() {
        return stavka;
    }

    public void setStavka(List<StavkaRezervacije> stavka) {
        this.stavka = stavka;
    }

    @Override
    public String toString() {
        return "Rezervacija{" + "napomena=" + napomena + ", ukupanIznos=" + ukupanIznos + ", datumRezervacija=" + datumRezervacija + ", zaposleni=" + zaposleni + ", sportista=" + sportista + '}';
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
        final Rezervacija other = (Rezervacija) obj;
        if (!Objects.equals(this.datumRezervacija, other.datumRezervacija)) {
            return false;
        }
        if (!Objects.equals(this.zaposleni, other.zaposleni)) {
            return false;
        }
        return Objects.equals(this.sportista, other.sportista);
    }

    @Override
    public String vratiNazivTabele() {
        return "rezervacija "
                + "JOIN zaposleni ON rezervacija.idZaposleni = zaposleni.idZaposleni "
                + "JOIN sportista ON rezervacija.idSportista = sportista.idSportista "
                + "JOIN stavkarezervacije ON rezervacija.idRezervacija = stavkarezervacije.idRezervacija";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        Rezervacija trenutna = null;

        while (rs.next()) {
            int idRezervacija = rs.getInt("rezervacija.idRezervacija");

            if (trenutna == null || trenutna.getIdRezervacija() != idRezervacija) {
                String napomena = rs.getString("rezervacija.napomena");
                double ukupanIznos = rs.getDouble("rezervacija.ukupanIznos");
                String datumStr = rs.getString("rezervacija.datumRezervacija");
                LocalDateTime datum = LocalDate.parse(datumStr).atStartOfDay();

                int idZaposleni = rs.getInt("zaposleni.idZaposleni");
                String imeZ = rs.getString("zaposleni.imeZaposleni");
                String prezimeZ = rs.getString("zaposleni.prezimeZaposleni");
                Zaposleni z = new Zaposleni(idZaposleni, imeZ, prezimeZ, null, null, null);

                int idSportista = rs.getInt("sportista.idSportista");
                String imeS = rs.getString("sportista.imeSportista");
                String prezimeS = rs.getString("sportista.prezimeSportista");
                Sportista s = new Sportista(idSportista, imeS, prezimeS, null);

                trenutna = new Rezervacija(idRezervacija, napomena, ukupanIznos, datum, z, s);
                lista.add(trenutna);
            }

            int rb = rs.getInt("stavkarezervacije.rb");
            String nazivSt = rs.getString("stavkarezervacije.nazivSt");
            LocalTime vremePocetka = LocalTime.parse(rs.getString("stavkarezervacije.vremePocetka"));
            LocalTime vremeZavrsetka = LocalTime.parse(rs.getString("stavkarezervacije.vremeZavrsetka"));
            int brojSati = rs.getInt("stavkarezervacije.brojSati");
            double iznos = rs.getDouble("stavkarezervacije.iznos");
            double cenaPoSatu = rs.getDouble("stavkarezervacije.cenaPoSatu");
            int idSto = rs.getInt("stavkarezervacije.idSto");
            Sto sto = new Sto();
            sto.setIdSto(idSto);

            StavkaRezervacije stavka = new StavkaRezervacije(trenutna, rb, nazivSt, vremePocetka, vremeZavrsetka, brojSati, iznos, cenaPoSatu, sto);
            trenutna.getStavka().add(stavka);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "napomena,ukupanIznos,datumRezervacija,idZaposleni,idSportista";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {

        return "'" + napomena + "'," + ukupanIznos + ",'" + datumRezervacija + "'," + zaposleni.getIdZaposleni() + "," + sportista.getIdSportista();

    }

    @Override
    public String vratiNazivTabeleBezJoin() {
        return "rezervacija";
    }

    @Override
    public String vratiPrimarniKljuc() {

        return "rezervacija.idRezervacija=" + idRezervacija;

    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "napomena='" + napomena + "',ukupanIznos=" + ukupanIznos
                + ",datumRezervacija='" + datumRezervacija
                + "',idZaposleni=" + zaposleni.getIdZaposleni()
                + ",idSportista=" + sportista.getIdSportista()
                + " WHERE idRezervacija=" + idRezervacija;
    }

    @Override
    public void setGenerisaniKljuc(int kljuc) {
        this.idRezervacija = kljuc;
    }

    @Override
    public String vratiUslovZaPretragu() {
        StringBuilder sb = new StringBuilder();

        if (sportista != null && sportista.getIdSportista() > 0) {
            sb.append(" AND rezervacija.idSportista=").append(sportista.getIdSportista());
        }
        if (zaposleni != null && zaposleni.getIdZaposleni() > 0) {
            sb.append(" AND rezervacija.idZaposleni=").append(zaposleni.getIdZaposleni());
        }
        if (datumRezervacija != null) {
            sb.append(" AND DATE(rezervacija.datumRezervacija)='").append(datumRezervacija.toLocalDate()).append("'");
        }

        if (stavka != null && !stavka.isEmpty() && stavka.get(0).getSto() != null && stavka.get(0).getSto().getIdSto() > 0) {
            sb.append(" AND stavkarezervacije.idSto=").append(stavka.get(0).getSto().getIdSto());
        }

        String uslov = sb.toString();
        if (uslov.startsWith(" AND ")) {
            uslov = uslov.substring(5);
        }
        return uslov;
    }

}
