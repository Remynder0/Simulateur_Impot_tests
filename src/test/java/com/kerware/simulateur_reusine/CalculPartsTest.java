package com.kerware.simulateur_reusine;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class CalculPartsTest {

    @Test
    void calcule_parts_celibataire_sans_enfant() {
        ParametreCalculImpot p = new ParametreCalculImpot();
        p.setSitFam(SituationFamiliale.CELIBATAIRE);
        p.setNbEnf(0);
        p.setNbEnfH(0);
        p.setParIso(false);

        CalculParts calculParts = new CalculParts(p);
        double nbPartsNet = calculParts.calculPartsNet();

        assertAll(
            () -> assertEquals(1d, p.getNbPtsDecl()),
            () -> assertEquals(1d, nbPartsNet),
            () -> assertEquals(1d, p.getNbPtsTotal())
        );
    }

    @Test
    void calcule_parts_pour_couple_avec_trois_enfants() {
        ParametreCalculImpot p = new ParametreCalculImpot();
        p.setSitFam(SituationFamiliale.MARIE);
        p.setNbEnf(3);
        p.setNbEnfH(0);
        p.setParIso(false);

        CalculParts calculParts = new CalculParts(p);
        double nbPartsNet = calculParts.calculPartsNet();

        assertAll(
            () -> assertEquals(2d, p.getNbPtsDecl()),
            () -> assertEquals(4d, nbPartsNet),
            () -> assertEquals(4d, p.getNbPtsTotal())
        );
    }

    @Test
    void calcule_parts_veuf_avec_un_enfant_parent_isole_et_un_enfant_handicape() {
        ParametreCalculImpot p = new ParametreCalculImpot();
        p.setSitFam(SituationFamiliale.VEUF);
        p.setNbEnf(1);
        p.setNbEnfH(1);
        p.setParIso(true);

        CalculParts calculParts = new CalculParts(p);
        double nbPartsNet = calculParts.calculPartsNet();

        assertAll(
            () -> assertEquals(ParametreCalculImpotCommun.getPartVeufEnf(), p.getNbPtsDecl()),
            () -> assertEquals(3.5d, nbPartsNet),
            () -> assertEquals(3.5d, p.getNbPtsTotal())
        );
    }
}
