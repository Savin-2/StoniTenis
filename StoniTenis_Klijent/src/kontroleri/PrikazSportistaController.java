/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Mesto;
import domen.Sportista;
import forme.PrikazSportistaForma;
import forme.model.ModelTabeleSportista;
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
public class PrikazSportistaController {

    private final PrikazSportistaForma psf;
    private List<Mesto> mesta;

    public PrikazSportistaController(PrikazSportistaForma psf) {
        this.psf = psf;
        addActionListener();
    }

    public void otvoriFormu() {
        popuniComboBox();
        pripremiFormu();
        psf.setVisible(true);

    }

    public void pripremiFormu() {
        List<Sportista> sportisti = komunikacija.Komunikacija.getInstance().ucitajSportiste();
        ModelTabeleSportista mts = new ModelTabeleSportista(sportisti);
        psf.getjTableSportisti().setModel(mts);
    }

    private void popuniComboBox() {
        mesta = komunikacija.Komunikacija.getInstance().ucitajMesta();
        psf.getjComboBoxMesta().addItem("");
        for (Mesto m : mesta) {
            psf.getjComboBoxMesta().addItem(m.toString());
        }
    }

    private void addActionListener() {

        psf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int red = psf.getjTableSportisti().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(psf, "Sistem ne može da obriše sportistu", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleSportista mts = (ModelTabeleSportista) psf.getjTableSportisti().getModel();
                    Sportista s = mts.getLista().get(red);
                    try {
                        Komunikacija.getInstance().obrisiSportistu(s);
                        JOptionPane.showMessageDialog(psf, "Sistem je obrisao sportistu.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(psf, "Sistem ne može da obriše sportistu", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        psf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int red = psf.getjTableSportisti().getSelectedRow();
                if (red == -1) {     
                    JOptionPane.showMessageDialog(psf, "Morate odabrati sportistu!", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    ModelTabeleSportista mts = (ModelTabeleSportista) psf.getjTableSportisti().getModel();
                    Sportista s = mts.getLista().get(red);
                    JOptionPane.showMessageDialog(psf, "Sistem je našao sportistu", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().otvoriIzmeniSportistuFormu(s);

                }

            }
        });

        psf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pretrazi();
            }

            private void pretrazi() {
                String ime = psf.getjTextFieldIme().getText().trim();
                String prezime = psf.getjTextFieldPrezime().getText().trim();
                String nazivMesta = (String) psf.getjComboBoxMesta().getSelectedItem();

                Mesto selektovanoMesto = new Mesto();
                if (nazivMesta != null && !nazivMesta.isEmpty()) {
                    for (Mesto m : mesta) {
                        if (m.getNaziv().equals(nazivMesta)) {
                            selektovanoMesto = m;
                            break;
                        }
                    }
                }

                Sportista kriterijum = new Sportista(-1, ime, prezime, selektovanoMesto);

                try {
                    List<Sportista> rezultat = Komunikacija.getInstance().pretraziSportistu(kriterijum);
                    if (rezultat.isEmpty()) {
                        JOptionPane.showMessageDialog(psf, "Sistem ne može da nađe sportiste po zadatim kriterijumima", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    } else {
                        ModelTabeleSportista model = new ModelTabeleSportista(rezultat);
                        psf.getjTableSportisti().setModel(model);
                        JOptionPane.showMessageDialog(psf, "Sistem je našao sportiste po zadatim kriterijumima", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(psf, "Sistem ne može da nađe sportiste po zadatim kriterijumima", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void osveziTabelu() {
        pripremiFormu();
    }

}
