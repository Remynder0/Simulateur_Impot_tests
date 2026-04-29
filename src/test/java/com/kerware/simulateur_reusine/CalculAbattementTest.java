package com.kerware.simulateur_reusine;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class CalculAbattementTest {

    @Test
    void applique_abattement_standard_quand_entre_min_et_max() {
        ParametreCalculImpot p = new ParametreCalculImpot();
        p.setrNet(50000);

        CalculAbattement calculAbattement = new CalculAbattement(p);
        double revenuNetApresAbattement = calculAbattement.calculAbattementNet();

        assertAll(
            () -> assertEquals(5000d, p.getAbt()),
            () -> assertEquals(45000d, revenuNetApresAbattement)
        );
    }

    @Test
    void applique_plancher_abattement_minimum() {
        ParametreCalculImpot p = new ParametreCalculImpot();
        p.setrNet(4000);

        CalculAbattement calculAbattement = new CalculAbattement(p);
        double revenuNetApresAbattement = calculAbattement.calculAbattementNet();

        assertAll(
            () -> assertEquals(ParametreCalculImpotCommun.getlAbtMin(), p.getAbt()),
            () -> assertEquals(3505d, revenuNetApresAbattement)
        );
    }

    @Test
    void applique_plafond_abattement_maximum() {
        ParametreCalculImpot p = new ParametreCalculImpot();
        p.setrNet(300000);

        CalculAbattement calculAbattement = new CalculAbattement(p);
        double revenuNetApresAbattement = calculAbattement.calculAbattementNet();

        assertAll(
            () -> assertEquals(ParametreCalculImpotCommun.getlAbtMax(), p.getAbt()),
            () -> assertEquals(285829d, revenuNetApresAbattement)
        );
    }
}
