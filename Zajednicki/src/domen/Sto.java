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
public class Sto implements ApstraktniDomenskiObjekat {

    private int idSto;
    private String tipStola;
    private double cenaPoSatu;

    public Sto() {
    }

    public Sto(int idSto, String tipStola, double cenaPoSatu) {
        this.idSto = idSto;
        this.tipStola = tipStola;
        this.cenaPoSatu = cenaPoSatu;
    }

    public int getIdSto() {
        return idSto;
    }

    public void setIdSto(int idSto) {
        this.idSto = idSto;
    }

    public String getTipStola() {
        return tipStola;
    }

    public void setTipStola(String tipStola) {
        this.tipStola = tipStola;
    }

    public double getCenaPoSatu() {
        return cenaPoSatu;
    }

    public void setCenaPoSatu(double cenaPoSatu) {
        this.cenaPoSatu = cenaPoSatu;
    }

    @Override
    public String toString() {
        return "Sto{" + "tipStola=" + tipStola + ", cenaPoSatu=" + cenaPoSatu + '}';
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
        final Sto other = (Sto) obj;
        if (Double.doubleToLongBits(this.cenaPoSatu) != Double.doubleToLongBits(other.cenaPoSatu)) {
            return false;
        }
        return Objects.equals(this.tipStola, other.tipStola);
    }

    @Override
    public String vratiNazivTabele() {
        return "sto";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idSto = rs.getInt("sto.idSto");
            String tipStola = rs.getString("sto.tipStola");
            double cenaPoSatu = rs.getDouble("sto.cenaPoSatu");
            Sto s = new Sto(idSto, tipStola, cenaPoSatu);
            lista.add(s);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "tipStola,cenaPoSatu";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + tipStola + "'," + cenaPoSatu;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "sto.idSto=" + idSto;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {

        return "tipStola='" + tipStola + "',cenaPoSatu=" + cenaPoSatu;

    }

}
