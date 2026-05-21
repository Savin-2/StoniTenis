package kontroleri;

import domen.Sto;
import domen.StavkaRezervacije;
import forme.DodajStavkuRezervacijeForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import java.util.Calendar;
import javax.swing.SpinnerDateModel;

public class DodajStavkuRezervacijeController {

    private final DodajStavkuRezervacijeForma dsrf;
    private List<Sto> stolovi;
    private StavkaRezervacije stavkaZaIzmenu = null;
    private int indexStavkeZaIzmenu = -1;

    public DodajStavkuRezervacijeController(DodajStavkuRezervacijeForma dsrf) {
        this.dsrf = dsrf;
        addActionListener();
    }

    public void otvoriFormu() {
        popuniComboBox();
        podesiSpinnere();
        dsrf.setVisible(true);
    }

    public void otvoriFormuZaIzmenu(StavkaRezervacije stavka, int index) {
        stavkaZaIzmenu = stavka;
        indexStavkeZaIzmenu = index;
        popuniComboBox();
        podesiSpinnere();
        dsrf.postaviModIzmene(stavka);
        dsrf.setVisible(true);
    }

    private void podesiSpinnere() {
        JSpinner.DateEditor editorPocetak = new JSpinner.DateEditor(dsrf.getjSpinnerVremePocetka(), "HH:00");
        dsrf.getjSpinnerVremePocetka().setEditor(editorPocetak);

        JSpinner.DateEditor editorZavrsetak = new JSpinner.DateEditor(dsrf.getjSpinnerVremeZavrsetka(), "HH:00");
        dsrf.getjSpinnerVremeZavrsetka().setEditor(editorZavrsetak);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        dsrf.getjSpinnerVremePocetka().setValue(cal.getTime());
        dsrf.getjSpinnerVremeZavrsetka().setValue(cal.getTime());

        ((SpinnerDateModel) dsrf.getjSpinnerVremePocetka().getModel()).setCalendarField(Calendar.HOUR_OF_DAY);
        ((SpinnerDateModel) dsrf.getjSpinnerVremeZavrsetka().getModel()).setCalendarField(Calendar.HOUR_OF_DAY);
    }

    private void popuniComboBox() {
        try {
            stolovi = Komunikacija.getInstance().ucitajStolove();
            dsrf.getjComboBoxStolovi().removeAllItems();
            for (Sto s : stolovi) {
                dsrf.getjComboBoxStolovi().addItem(s.getTipStola());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dsrf, "Greška pri učitavanju stolova!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addActionListener() {
        dsrf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj();
            }
        });
    }

    private void dodaj() {
        int index = dsrf.getjComboBoxStolovi().getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(dsrf, "Morate odabrati sto!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Sto sto = stolovi.get(index);

        Date datePocetka = (Date) dsrf.getjSpinnerVremePocetka().getValue();
        Date dateZavrsetka = (Date) dsrf.getjSpinnerVremeZavrsetka().getValue();

        LocalTime poc = datePocetka.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime().withSecond(0).withNano(0);
        LocalTime zav = dateZavrsetka.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime().withSecond(0).withNano(0);

        if (!zav.isAfter(poc)) {
            JOptionPane.showMessageDialog(dsrf, "Vreme završetka mora biti posle vremena početka!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (poc.isBefore(LocalTime.of(8, 0)) || zav.isAfter(LocalTime.of(23, 0))) {
            JOptionPane.showMessageDialog(dsrf, "Radno vreme je od 08:00 do 23:00!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int brojSati = (int) java.time.Duration.between(poc, zav).toHours();
        double iznos = brojSati * sto.getCenaPoSatu();

        dsrf.getjLabelIznos().setText(iznos + " RSD");

        StavkaRezervacije stavka = new StavkaRezervacije(null,
                stavkaZaIzmenu != null ? stavkaZaIzmenu.getRb() : 0,
                sto.getTipStola(), poc, zav, brojSati, iznos, sto.getCenaPoSatu(), sto);

        if (stavkaZaIzmenu != null) {
            Koordinator.getInstance().izmeniStavkuNaGlavnojFormi(stavka, indexStavkeZaIzmenu);
        } else {
            Koordinator.getInstance().dodajStavkuNaGlavnuFormu(stavka);
        }
        dsrf.dispose();
    }
}
