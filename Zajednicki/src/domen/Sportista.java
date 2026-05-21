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
public class Sportista implements ApstraktniDomenskiObjekat {

    private int idSportista;
    private String imeSportista;
    private String prezimeSportista;
    private Mesto mesto;

    public Sportista() {
    }

    public Sportista(int idSportista, String imeSportista, String prezimeSportista, Mesto mesto) {
        this.idSportista = idSportista;
        this.imeSportista = imeSportista;
        this.prezimeSportista = prezimeSportista;
        this.mesto = mesto;
    }

    public int getIdSportista() {
        return idSportista;
    }

    public void setIdSportista(int idSportista) {
        this.idSportista = idSportista;
    }

    public String getImeSportista() {
        return imeSportista;
    }

    public void setImeSportista(String imeSportista) {
        this.imeSportista = imeSportista;
    }

    public String getPrezimeSportista() {
        return prezimeSportista;
    }

    public void setPrezimeSportista(String prezimeSportista) {
        this.prezimeSportista = prezimeSportista;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    @Override
    public String toString() {
        return imeSportista + " " + prezimeSportista;
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
        final Sportista other = (Sportista) obj;
        if (!Objects.equals(this.imeSportista, other.imeSportista)) {
            return false;
        }
        return Objects.equals(this.prezimeSportista, other.prezimeSportista);
    }

    @Override
    public String vratiNazivTabele() {
        return "sportista JOIN mesto ON sportista.idMesto = mesto.idMesto";
    }

    @Override
    public String vratiNazivTabeleBezJoin() {
        return "sportista";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idSportista = rs.getInt("sportista.idSportista");
            String imeSportista = rs.getString("sportista.imeSportista");
            String prezimeSportista = rs.getString("sportista.prezimeSportista");
            int idMesto = rs.getInt("sportista.idMesto");
            String nazivMesto = rs.getString("mesto.naziv");
            Mesto m = new Mesto(idMesto, nazivMesto);
            Sportista s = new Sportista(idSportista, imeSportista, prezimeSportista, m);
            lista.add(s);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "imeSportista, prezimeSportista,idMesto";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + imeSportista + "','" + prezimeSportista + "'," + mesto.getIdMesto();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "sportista.idSportista = " + idSportista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "imeSportista='" + imeSportista + "',prezimeSportista='" + prezimeSportista + "',idMesto=" + mesto.getIdMesto() + " WHERE idSportista=" + idSportista;
    }

    @Override
    public String vratiUslovZaPretragu() {
        StringBuilder sb = new StringBuilder();

        if (imeSportista != null && !imeSportista.isEmpty()) {
            sb.append(" AND sportista.imeSportista LIKE '%").append(imeSportista).append("%'");
        }
        if (prezimeSportista != null && !prezimeSportista.isEmpty()) {
            sb.append(" AND sportista.prezimeSportista LIKE '%").append(prezimeSportista).append("%'");
        }
        if (mesto != null && mesto.getIdMesto() > 0) {
            sb.append(" AND sportista.idMesto=").append(mesto.getIdMesto());
        }

        String uslov = sb.toString();
        if (uslov.startsWith(" AND ")) {
            uslov = uslov.substring(5);
        }
        return uslov;
    }

}
