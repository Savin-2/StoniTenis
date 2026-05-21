/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.TerminDezurstva;
import domen.ZaposleniTermin;
import forme.PrikazDezurstavaForma;
import forme.model.ModelTabeleDezurstava;
import java.util.List;
import komunikacija.Komunikacija;

/**
 *
 * @author Savin
 */
public class PrikazDezurstavaController {

    private final PrikazDezurstavaForma pdf;

    public PrikazDezurstavaController(PrikazDezurstavaForma pdf) {
        this.pdf = pdf;
    }

    public void otvoriFormu() throws Exception {
        pripremiFormu();
        pdf.setVisible(true);
    }

    private void pripremiFormu() throws Exception {
        List<ZaposleniTermin> termini = Komunikacija.getInstance().ucitajZaposleneTermine();
        ModelTabeleDezurstava model = new ModelTabeleDezurstava(termini);
        pdf.getjTable1().setModel(model);
    }
}
