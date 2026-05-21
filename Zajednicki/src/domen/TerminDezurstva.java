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
public class TerminDezurstva implements ApstraktniDomenskiObjekat {

    private int idTerminDezurstva;
    private String nazivSmena;

    public TerminDezurstva() {
    }

    public TerminDezurstva(int idTerminDezurstva, String nazivSmena) {
        this.idTerminDezurstva = idTerminDezurstva;
        this.nazivSmena = nazivSmena;
    }

    public int getIdTerminDezurstva() {
        return idTerminDezurstva;
    }

    public void setIdTerminDezurstva(int idTerminDezurstva) {
        this.idTerminDezurstva = idTerminDezurstva;
    }

    public String getNazivSmena() {
        return nazivSmena;
    }

    public void setNazivSmena(String nazivSmena) {
        this.nazivSmena = nazivSmena;
    }

    @Override
    public String toString() {
        return "TerminDezurstva{" + "nazivSmena=" + nazivSmena + '}';
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
        final TerminDezurstva other = (TerminDezurstva) obj;
        return Objects.equals(this.nazivSmena, other.nazivSmena);
    }

    @Override
    public String vratiNazivTabele() {
        return "termindezurstva";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
    List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
    while (rs.next()) {
        int idTerminDezurstva = rs.getInt("termindezurstva.idTerminDezurstva");
        String nazivSmena = rs.getString("termindezurstva.nazivSmena");
        TerminDezurstva td = new TerminDezurstva(idTerminDezurstva, nazivSmena);
        lista.add(td);
    }
    return lista;
}

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivSmena";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + nazivSmena + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "termindezurstva.idTerminDezurstva=" + idTerminDezurstva;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {

        return "nazivSmena='"+nazivSmena+"'";

    }

}
