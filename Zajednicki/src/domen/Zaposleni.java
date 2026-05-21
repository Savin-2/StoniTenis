/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Savin
 */
public class Zaposleni implements ApstraktniDomenskiObjekat {

    private int idZaposleni;
    private String imeZaposleni;
    private String prezimeZaposleni;
    private String emailZaposleni;
    private String korisnickoIme;
    private String sifra;

    public Zaposleni() {
    }

    public Zaposleni(int idZaposleni, String imeZaposleni, String prezimeZaposleni, String emailZaposleni, String korisnickoIme, String sifra) {
        this.idZaposleni = idZaposleni;
        this.imeZaposleni = imeZaposleni;
        this.prezimeZaposleni = prezimeZaposleni;
        this.emailZaposleni = emailZaposleni;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public int getIdZaposleni() {
        return idZaposleni;
    }

    public void setIdZaposleni(int idZaposleni) {
        this.idZaposleni = idZaposleni;
    }

    public String getImeZaposleni() {
        return imeZaposleni;
    }

    public void setImeZaposleni(String imeZaposleni) {
        this.imeZaposleni = imeZaposleni;
    }

    public String getPrezimeZaposleni() {
        return prezimeZaposleni;
    }

    public void setPrezimeZaposleni(String prezimeZaposleni) {
        this.prezimeZaposleni = prezimeZaposleni;
    }

    public String getEmailZaposleni() {
        return emailZaposleni;
    }

    public void setEmailZaposleni(String emailZaposleni) {
        this.emailZaposleni = emailZaposleni;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return "Zaposleni{" + "imeZaposleni=" + imeZaposleni + ", prezimeZaposleni=" + prezimeZaposleni + ", emailZaposleni=" + emailZaposleni + '}';
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
        final Zaposleni other = (Zaposleni) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

    @Override
    public String vratiNazivTabele() {
        return "zaposleni";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idZaposleni = rs.getInt("zaposleni.idZaposleni");
            String imeZaposleni = rs.getString("zaposleni.imeZaposleni");
            String prezimeZaposleni = rs.getString("zaposleni.prezimeZaposleni");
            String emailZaposleni = rs.getString("zaposleni.emailZaposleni");
            String korisnickoIme = rs.getString("zaposleni.korisnickoIme");
            String sifra = rs.getString("zaposleni.sifra");
            Zaposleni z = new Zaposleni(idZaposleni, imeZaposleni, prezimeZaposleni, emailZaposleni, korisnickoIme, sifra);
            lista.add(z);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "imeZaposleni,prezimeZaposleni,emailZaposleni,korisnickoIme,sifra";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + imeZaposleni + "','" + prezimeZaposleni + "','" + emailZaposleni + "','" + korisnickoIme + "','" + sifra + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "zaposleni.idZaposleni=" + idZaposleni;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {

        return "imeZaposleni='" + imeZaposleni + "',prezimeZaposleni='" + prezimeZaposleni + "',emailZaposleni='" + emailZaposleni + "',korisnickoIme='" + korisnickoIme + "',sifra='" + sifra + "'";

    }

    @Override
    public String vratiUslovZaPretragu() {
        return "zaposleni.korisnickoIme='" + korisnickoIme + "' AND zaposleni.sifra='" + sifra + "'";
    }

}
