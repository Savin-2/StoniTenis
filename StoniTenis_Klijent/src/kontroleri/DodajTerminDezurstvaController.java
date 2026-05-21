/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.TerminDezurstva;
import domen.Zaposleni;
import domen.ZaposleniTermin;
import forme.DodajTerminDezurstvaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import javax.swing.JSpinner;

public class DodajTerminDezurstvaController {

    private final DodajTerminDezurstvaForma dtdf;
    private List<TerminDezurstva> termini;

    public DodajTerminDezurstvaController(DodajTerminDezurstvaForma dtdf) {
        this.dtdf = dtdf;
        addActionListener();
    }

    public void otvoriFormu() throws Exception {
        popuniImePrezime();
        popuniComboBox();
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dtdf.getjSpinnerDatum(), "dd.MM.yyyy");
        dtdf.getjSpinnerDatum().setEditor(editor);
        dtdf.setVisible(true);
    }

    private void popuniImePrezime() {
        Zaposleni ulogovani = Koordinator.getInstance().getUlogovani();
        dtdf.getjLabelImePrezime().setText(
                ulogovani.getImeZaposleni() + " " + ulogovani.getPrezimeZaposleni()
        );
    }

    private void popuniComboBox() throws Exception {
        termini = Komunikacija.getInstance().ucitajTermineDezurstava();
        dtdf.getjComboBoxTermini().removeAllItems();
        for (TerminDezurstva t : termini) {
            dtdf.getjComboBoxTermini().addItem(t.getNazivSmena());
        }
    }

    private void addActionListener() {
        dtdf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj();
            }
        });
    }

    private void dodaj() {
        int selectedIndex = dtdf.getjComboBoxTermini().getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(dtdf, "Morate odabrati termin dežurstva!", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        TerminDezurstva odabranTermin = termini.get(selectedIndex);
        Zaposleni ulogovani = Koordinator.getInstance().getUlogovani();

        Date datum = (Date) dtdf.getjSpinnerDatum().getValue();
        LocalDateTime ldt = datum.toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDateTime();

        ZaposleniTermin zt = new ZaposleniTermin(ulogovani, odabranTermin, ldt);

        try {
            Komunikacija.getInstance().dodajZaposleniTermin(zt);
            JOptionPane.showMessageDialog(dtdf, "Sistem je zapamtio termin dežurstva.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
            dtdf.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dtdf, "Sistem ne može da zapamti termin dežurstva", "GREŠKA", JOptionPane.ERROR_MESSAGE);
        }
    }
}
