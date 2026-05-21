package kontroleri;

import domen.Rezervacija;
import domen.Sportista;
import domen.StavkaRezervacije;
import domen.Sto;
import domen.Zaposleni;
import forme.PrikazRezervacijaForma;
import forme.model.ModelTabeleRezervacija;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import komunikacija.Komunikacija;
import koordinator.Koordinator;

public class PrikazRezervacijaController {

    private final PrikazRezervacijaForma prf;
    private List<Sportista> sportisti;
    private List<Zaposleni> zaposleni;
    private List<Sto> stolovi;

    public PrikazRezervacijaController(PrikazRezervacijaForma prf) {
        this.prf = prf;
        addActionListeners();
    }

    public void otvoriFormu() {
        podesiSpinner();
        popuniComboBoxeve();
        pripremiFormu();
        prf.setVisible(true);
    }

    private void podesiSpinner() {
        JSpinner.DateEditor editor = new JSpinner.DateEditor(prf.getjSpinnerDatum(), "dd.MM.yyyy");
        prf.getjSpinnerDatum().setEditor(editor);
        prf.getjSpinnerDatum().setEnabled(false);
    }

    private void popuniComboBoxeve() {
        try {
            sportisti = Komunikacija.getInstance().ucitajSportiste();
            prf.getjComboBoxSportista().addItem("");
            for (Sportista s : sportisti) {
                prf.getjComboBoxSportista().addItem(s.getImeSportista() + " " + s.getPrezimeSportista());
            }

            zaposleni = Komunikacija.getInstance().ucitajZaposlene();
            prf.getjComboBoxZaposleni().addItem("");
            for (Zaposleni z : zaposleni) {
                prf.getjComboBoxZaposleni().addItem(z.getImeZaposleni() + " " + z.getPrezimeZaposleni());
            }

            stolovi = Komunikacija.getInstance().ucitajStolove();
            prf.getjComboBoxStolovi().addItem("");
            for (Sto s : stolovi) {
                prf.getjComboBoxStolovi().addItem(s.getTipStola());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(prf, "Greška pri učitavanju podataka!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pripremiFormu() {
        try {
            List<Rezervacija> rezervacije = Komunikacija.getInstance().ucitajRezervacije();
            ModelTabeleRezervacija model = new ModelTabeleRezervacija(rezervacije);
            prf.getjTableRezervacije().setModel(model);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(prf, "Greška pri učitavanju rezervacija!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addActionListeners() {
        prf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pretrazi();
            }
        });

        prf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni();
            }
        });

        prf.getjCheckBoxDatum().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prf.getjSpinnerDatum().setEnabled(prf.getjCheckBoxDatum().isSelected());
            }
        });
    }

    private void pretrazi() {
        Rezervacija kriterijum = new Rezervacija();

        // Sportista
        String imePrezimeSportiste = (String) prf.getjComboBoxSportista().getSelectedItem();
        if (imePrezimeSportiste != null && !imePrezimeSportiste.isEmpty()) {
            for (Sportista s : sportisti) {
                if ((s.getImeSportista() + " " + s.getPrezimeSportista()).equals(imePrezimeSportiste)) {
                    kriterijum.setSportista(s);
                    break;
                }
            }
        }

        // Zaposleni
        String imePrezimeZaposlenog = (String) prf.getjComboBoxZaposleni().getSelectedItem();
        if (imePrezimeZaposlenog != null && !imePrezimeZaposlenog.isEmpty()) {
            for (Zaposleni z : zaposleni) {
                if ((z.getImeZaposleni() + " " + z.getPrezimeZaposleni()).equals(imePrezimeZaposlenog)) {
                    kriterijum.setZaposleni(z);
                    break;
                }
            }
        }

        // Datum
        if (prf.getjCheckBoxDatum().isSelected()) {
            Date datum = (Date) prf.getjSpinnerDatum().getValue();
            LocalDateTime ldt = datum.toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDateTime();
            kriterijum.setDatumRezervacija(ldt);
        }

        // Sto
        String tipStola = (String) prf.getjComboBoxStolovi().getSelectedItem();
        if (tipStola != null && !tipStola.isEmpty()) {
            for (Sto s : stolovi) {
                if (s.getTipStola().equals(tipStola)) {
                    StavkaRezervacije stavka = new StavkaRezervacije();
                    stavka.setSto(s);
                    kriterijum.getStavka().add(stavka);
                    break;
                }
            }
        }

        try {
            List<Rezervacija> rezultat = Komunikacija.getInstance().pretraziRezervaciju(kriterijum);
            if (rezultat.isEmpty()) {
                JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe rezervacije po zadatim kriterijumima", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            } else {
                ModelTabeleRezervacija model = new ModelTabeleRezervacija(rezultat);
                prf.getjTableRezervacije().setModel(model);
                JOptionPane.showMessageDialog(prf, "Sistem je našao rezervacije po zadatim kriterijumima", "USPEH", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe rezervacije po zadatim kriterijumima", "GREŠKA", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void izmeni() {
        int red = prf.getjTableRezervacije().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(prf, "Morate odabrati rezervaciju!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ModelTabeleRezervacija model = (ModelTabeleRezervacija) prf.getjTableRezervacije().getModel();
        Rezervacija r = model.getRezervacija(red);
        Koordinator.getInstance().otvoriIzmenuRezervacije(r);
        prf.dispose();
    }
}
