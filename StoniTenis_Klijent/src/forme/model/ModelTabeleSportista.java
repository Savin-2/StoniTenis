/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Mesto;
import domen.Sportista;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Savin
 */
public class ModelTabeleSportista extends AbstractTableModel {

    private List<Sportista> lista;
    String[] kolone = {"id", "ime", "prezime", "mesto"};

    public ModelTabeleSportista(List<Sportista> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Sportista s = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return s.getIdSportista();
            case 1:
                return s.getImeSportista();
            case 2:
                return s.getPrezimeSportista();
            case 3:
                return s.getMesto().getNaziv();
            default:
                return "NA";
        }
    }

    public Sportista getSportista(int rowIndex) {
        return lista.get(rowIndex);
    }

    public List<Sportista> getLista() {
        return lista;
    }
}
