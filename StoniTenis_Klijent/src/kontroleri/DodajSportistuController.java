/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Mesto;
import domen.Sportista;
import forme.DodajSportistuForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;

/**
 *
 * @author Savin
 */
public class DodajSportistuController {

    private final DodajSportistuForma dsf;
    private List<Mesto> mesta;
    private Sportista sportista;

    public DodajSportistuController(DodajSportistuForma dsf) {
        this.dsf = dsf;
        this.sportista = null;
        addActionListener();
    }

    public DodajSportistuController(DodajSportistuForma dsf, Sportista s) {
        this.dsf = dsf;
        this.sportista = s;
        addActionListener();
    }

    public void otvoriFormu() {
        popuniComboBox();
        if (sportista != null) {
            dsf.getjTextFieldIme().setText(sportista.getImeSportista());
            dsf.getjTextFieldPrezime().setText(sportista.getPrezimeSportista());
            dsf.getjButtonDodaj().setText("Izmeni");
            dsf.getjComboBoxMesta().setSelectedItem(sportista.getMesto().getNaziv());
        }
        dsf.setVisible(true);
    }

    private void popuniComboBox() {
        mesta = komunikacija.Komunikacija.getInstance().ucitajMesta();
        for (Mesto m : mesta) {
            dsf.getjComboBoxMesta().addItem(m.toString());
        }
    }

    private void addActionListener() {
        dsf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                String ime = dsf.getjTextFieldIme().getText().trim();
                String prezime = dsf.getjTextFieldPrezime().getText().trim();
                String nazivMesta = (String) dsf.getjComboBoxMesta().getSelectedItem();

                Mesto selektovanoMesto = null;
                for (Mesto m : mesta) {
                    if (m.getNaziv().equals(nazivMesta)) {
                        selektovanoMesto = m;
                        break;
                    }
                }

                if (ime.isEmpty()) {
                    JOptionPane.showMessageDialog(dsf, "Ime ne sme biti prazno!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(dsf, "Sistem ne može da zapamti sportistu", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (prezime.isEmpty()) {
                    JOptionPane.showMessageDialog(dsf, "Prezime ne sme biti prazno!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(dsf, "Sistem ne može da zapamti sportistu", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (selektovanoMesto == null) {
                    JOptionPane.showMessageDialog(dsf, "Morate odabrati mesto!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(dsf, "Sistem ne može da zapamti sportistu", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    if (sportista == null) {

                        Sportista s = new Sportista(-1, ime, prezime, selektovanoMesto);
                        Komunikacija.getInstance().dodajSportistu(s);
                        JOptionPane.showMessageDialog(dsf, "Sistem je zapamtio sportistu.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    } else {

                        sportista.setImeSportista(ime);
                        sportista.setPrezimeSportista(prezime);
                        sportista.setMesto(selektovanoMesto);
                        Komunikacija.getInstance().izmeniSportistu(sportista);
                        JOptionPane.showMessageDialog(dsf, "Sistem je zapamtio sportistu.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    }
                    Koordinator.getInstance().refreshSportisti();
                    dsf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dsf, "Sistem ne može da zapamti sportistu.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
