package kontroleri;

import domen.Sportista;
import domen.StavkaRezervacije;
import domen.Rezervacija;
import domen.Zaposleni;
import forme.DodajStavkuRezervacijeForma;
import forme.GlavnaForma;
import forme.model.ModelTabeleStavki;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import komunikacija.Komunikacija;
import koordinator.Koordinator;

public class GlavnaFormaController {

    private final GlavnaForma gf;
    private List<Sportista> sportisti;
    private List<StavkaRezervacije> stavke = new ArrayList<>();
    private DodajStavkuRezervacijeController dsrController;
    private Rezervacija rezervacijaZaIzmenu = null;

    public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();
    }

    public void otvoriFormu() {
        gf.postaviWindowListener();
        Zaposleni ulogovani = Koordinator.getInstance().getUlogovani();
        gf.getjLabel3().setText(ulogovani.getImeZaposleni() + " " + ulogovani.getPrezimeZaposleni());
        podesiSpinner();
        popuniComboBoxSportisti();
        gf.postaviModKreiranja();
        osveziTabelu();
        gf.setVisible(true);
    }

    public void otvoriFormuZaIzmenu(Rezervacija r) {
        rezervacijaZaIzmenu = r;
        try {
            stavke = new ArrayList<>(r.getStavka());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(gf, "Greška pri učitavanju stavki!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            return;
        }
        gf.getjTextAreaNapomena().setText(r.getNapomena());
        Date datum = Date.from(r.getDatumRezervacija().atZone(java.time.ZoneId.systemDefault()).toInstant());
        gf.getjSpinnerDatumRez().setValue(datum);
        for (int i = 0; i < sportisti.size(); i++) {
            if (sportisti.get(i).getIdSportista() == r.getSportista().getIdSportista()) {
                gf.getjComboBoxSportisti().setSelectedIndex(i);
                break;
            }
        }

        gf.postaviModIzmene();
        Zaposleni ulogovani = Koordinator.getInstance().getUlogovani();
        gf.postaviNaslov("Izmena rezervacije — Zaposleni: " + ulogovani.getImeZaposleni() + " " + ulogovani.getPrezimeZaposleni());
        osveziTabelu();
    }

    private void podesiSpinner() {
        JSpinner.DateEditor editor = new JSpinner.DateEditor(gf.getjSpinnerDatumRez(), "dd.MM.yyyy");
        gf.getjSpinnerDatumRez().setEditor(editor);
    }

    private void popuniComboBoxSportisti() {
        try {
            sportisti = Komunikacija.getInstance().ucitajSportiste();
            gf.getjComboBoxSportisti().removeAllItems();
            for (Sportista s : sportisti) {
                gf.getjComboBoxSportisti().addItem(s.getImeSportista() + " " + s.getPrezimeSportista());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(gf, "Greška pri učitavanju sportista!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void osveziTabelu() {
        ModelTabeleStavki model = new ModelTabeleStavki(stavke);
        gf.getjTableStavkeRezervacije().setModel(model);
        double ukupno = stavke.stream().mapToDouble(StavkaRezervacije::getIznos).sum();
        gf.getjLabelUkupanIznos().setText(ukupno + " RSD");
    }

    public void dodajStavku(StavkaRezervacije stavka) {
        stavke.add(stavka);
        osveziTabelu();
    }

    private void addActionListeners() {
        gf.addBtnDodajStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dsrController = new DodajStavkuRezervacijeController(new DodajStavkuRezervacijeForma());
                dsrController.otvoriFormu();
            }
        });

        gf.addBtnUkloniStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ukloniStavku();
            }
        });

        gf.addBtnKreirajRezervacijuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kreirajRezervaciju();
            }
        });

        gf.addBtnSacuvajIzmeneActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvajIzmene();
            }
        });

        gf.addBtnIzmeniStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = gf.getjTableStavkeRezervacije().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(gf, "Morate odabrati stavku!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                StavkaRezervacije stavka = stavke.get(red);
                dsrController = new DodajStavkuRezervacijeController(new DodajStavkuRezervacijeForma());
                dsrController.otvoriFormuZaIzmenu(stavka, red);
            }
        });

    }

    private void ukloniStavku() {
        int red = gf.getjTableStavkeRezervacije().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(gf, "Morate odabrati stavku!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            return;
        }
        stavke.remove(red);
        osveziTabelu();
    }

    private void kreirajRezervaciju() {
        int index = gf.getjComboBoxSportisti().getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(gf, "Morate odabrati sportistu!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (stavke.isEmpty()) {
            JOptionPane.showMessageDialog(gf, "Morate dodati bar jednu stavku!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Sportista sportista = sportisti.get(index);
        Zaposleni ulogovani = Koordinator.getInstance().getUlogovani();
        String napomena = gf.getjTextAreaNapomena().getText().trim();

        Date datum = (Date) gf.getjSpinnerDatumRez().getValue();
        LocalDateTime ldt = datum.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();

        double ukupno = stavke.stream().mapToDouble(StavkaRezervacije::getIznos).sum();

        Rezervacija r = new Rezervacija(-1, napomena, ukupno, ldt, ulogovani, sportista);
        r.setStavka(stavke);

        try {
            Komunikacija.getInstance().kreirajRezervaciju(r);
            JOptionPane.showMessageDialog(gf, "Sistem je zapamtio rezervaciju.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
            stavke = new ArrayList<>();
            osveziTabelu();
            gf.getjTextAreaNapomena().setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(gf, "Sistem ne može da zapamti rezervaciju.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sacuvajIzmene() {
        int index = gf.getjComboBoxSportisti().getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(gf, "Morate odabrati sportistu!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (stavke.isEmpty()) {
            JOptionPane.showMessageDialog(gf, "Morate dodati bar jednu stavku!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Sportista sportista = sportisti.get(index);
        String napomena = gf.getjTextAreaNapomena().getText().trim();

        Date datum = (Date) gf.getjSpinnerDatumRez().getValue();
        LocalDateTime ldt = datum.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();

        double ukupno = stavke.stream().mapToDouble(StavkaRezervacije::getIznos).sum();

        rezervacijaZaIzmenu.setSportista(sportista);
        rezervacijaZaIzmenu.setNapomena(napomena);
        rezervacijaZaIzmenu.setDatumRezervacija(ldt);
        rezervacijaZaIzmenu.setUkupanIznos(ukupno);
        rezervacijaZaIzmenu.setStavka(stavke);

        try {
            Komunikacija.getInstance().izmeniRezervaciju(rezervacijaZaIzmenu);
            JOptionPane.showMessageDialog(gf, "Sistem je zapamtio rezervaciju.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
            rezervacijaZaIzmenu = null;
            stavke = new ArrayList<>();
            osveziTabelu();
            gf.getjTextAreaNapomena().setText("");
            gf.postaviModKreiranja();
            gf.postaviNaslov("Zdravo,");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(gf, "Sistem ne može da zapamti rezervaciju", "GREŠKA", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void resetujFormu() {
        stavke = new ArrayList<>();
        rezervacijaZaIzmenu = null;
        osveziTabelu();
        gf.getjTextAreaNapomena().setText("");
        gf.getjComboBoxSportisti().setSelectedIndex(0);
        Date now = new Date();
        gf.getjSpinnerDatumRez().setValue(now);
    }

    public void izmeniStavku(StavkaRezervacije stavka, int index) {
        stavke.set(index, stavka);
        osveziTabelu();
    }

    public void osveziComboBoxSportisti() {
        popuniComboBoxSportisti();
    }
}
