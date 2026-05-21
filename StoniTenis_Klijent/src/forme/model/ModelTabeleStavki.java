package forme.model;

import domen.StavkaRezervacije;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleStavki extends AbstractTableModel {

    private List<StavkaRezervacije> lista;
    String[] kolone = {"Naziv stola", "Vreme početka", "Vreme završetka", "Iznos"};

    public ModelTabeleStavki(List<StavkaRezervacije> lista) {
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
        StavkaRezervacije s = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return s.getNazivSt();
            case 1:
                return s.getVremePocetka();
            case 2:
                return s.getVremeZavrsetka();
            case 3:
                return s.getIznos();
            default:
                return "NA";
        }
    }
}
