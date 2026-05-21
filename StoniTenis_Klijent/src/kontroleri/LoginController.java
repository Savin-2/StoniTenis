/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Zaposleni;
import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Savin
 */
public class LoginController {

    private final LoginForma lf;

    public LoginController(LoginForma lf) {
        this.lf = lf;

        addActionListeners();
    }

    private void addActionListeners() {

        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {

                String username = lf.getjTextFieldUsername().getText().trim();
                String password = String.valueOf(lf.getjPasswordField().getPassword()).trim();

                komunikacija.Komunikacija.getInstance().konekcija();
                Zaposleni ulogovani = komunikacija.Komunikacija.getInstance().login(username, password);

                if (ulogovani == null) {

                    JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra nisu ispravni", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    koordinator.Koordinator.getInstance().setUlogovani(ulogovani);
                    JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra su ispravni", "Uspeh", JOptionPane.INFORMATION_MESSAGE);

                    try {
                        koordinator.Koordinator.getInstance().otvoriGlavnuFormu();
                        lf.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(lf, "Ne može da se otvori glavna forma i meni", "Greška", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(lf, "Ne može da se otvori glavna forma i meni", "Greška", JOptionPane.ERROR_MESSAGE);

                    }
                }
            }
        });

    }

    public void otvoriFormu() {

        lf.setVisible(true);
    }

}
