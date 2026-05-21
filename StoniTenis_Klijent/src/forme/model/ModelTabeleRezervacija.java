package forme.model;

import domen.Rezervacija;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleRezervacija extends AbstractTableModel {

    private List<Rezervacija> lista;
    String[] kolone = {"Zaposleni", "Sportista", "Datum", "Napomena", "Iznos"};

    public ModelTabeleRezervacija(List<Rezervacija> lista) {
        
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
        Rezervacija r = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return r.getZaposleni().getImeZaposleni() + " " + r.getZaposleni().getPrezimeZaposleni();
            case 1:
                return r.getSportista().getImeSportista() + " " + r.getSportista().getPrezimeSportista();
            case 2:
                return r.getDatumRezervacija().toLocalDate();
            case 3:
                return r.getNapomena();
            case 4:
                return r.getUkupanIznos();
            default:
                return "NA";
        }
    }

    public Rezervacija getRezervacija(int rowIndex) {
        return lista.get(rowIndex);
    }

    public List<Rezervacija> getLista() {
        return lista;
    }
}
