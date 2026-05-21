/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.ZaposleniTermin;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Savin
 */
public class ModelTabeleDezurstava extends AbstractTableModel {

    private List<ZaposleniTermin> lista;
    String[] kolone = {"Zaposleni", "Smena", "Datum"};

    public ModelTabeleDezurstava(List<ZaposleniTermin> lista) {
        this.lista = lista.stream()
                .filter(zt -> zt.getDatumDezurstva().isAfter(LocalDateTime.now()))
                .sorted((a, b) -> a.getDatumDezurstva().compareTo(b.getDatumDezurstva()))
                .collect(java.util.stream.Collectors.toList());
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
        ZaposleniTermin zt = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return zt.getZaposleni().getImeZaposleni() + " " + zt.getZaposleni().getPrezimeZaposleni();
            case 1:
                return zt.getTerminDezurstva().getNazivSmena();
            case 2:
                return zt.getDatumDezurstva().toLocalDate();
            default:
                return "NA";
        }
    }

    public ZaposleniTermin getZaposleniTermin(int rowIndex) {
        return lista.get(rowIndex);
    }

    public List<ZaposleniTermin> getLista() {
        return lista;
    }
}
