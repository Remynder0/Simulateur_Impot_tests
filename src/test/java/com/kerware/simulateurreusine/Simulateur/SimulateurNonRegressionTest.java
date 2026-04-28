package com.kerware.simulateurreusine.Simulateur;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class SimulateurNonRegressionTest {

    @Test
    void non_regression_resultats_figes_sur_scenarios_legacy() {
        Simulateur simulateur = new Simulateur();

        assertAll(
            () -> assertEquals(2736L, simulateur.calculImpot(35000, SituationFamiliale.VEUF, 0, 0, false)),
            () -> assertEquals(1452L, simulateur.calculImpot(35000, SituationFamiliale.VEUF, 1, 0, false)),
            () -> assertEquals(550L, simulateur.calculImpot(35000, SituationFamiliale.VEUF, 1, 1, false)),
            () -> assertEquals(2736L, simulateur.calculImpot(35000, SituationFamiliale.PACSE, 0, 0, false)),
            () -> assertEquals(550L, simulateur.calculImpot(35000, SituationFamiliale.PACSE, 2, 0, false)),
            () -> assertEquals(2736L, simulateur.calculImpot(35000, SituationFamiliale.CELIBATAIRE, 0, 0, false))
        );
    }

    @Test
    void non_regression_api_setters_doit_rester_coherente_avec_calcul_direct() {
        Simulateur simulateur = new Simulateur();

        long direct = simulateur.calculImpot(65000, SituationFamiliale.MARIE, 3, 0, false);

        simulateur.setRevenusNet(65000);
        simulateur.setSituationFamiliale(SituationFamiliale.MARIE);
        simulateur.setNbEnfantsACharge(3);
        simulateur.setNbEnfantsSituationHandicap(0);
        simulateur.setParentIsole(false);
        simulateur.calculImpotSurRevenuNet();

        assertEquals(direct, simulateur.getImpotSurRevenuNet());
    }

    @Test
    void non_regression_recalcul_sur_meme_instance() {
        Simulateur simulateur = new Simulateur();

        long impot1 = simulateur.calculImpot(65000, SituationFamiliale.MARIE, 3, 0, false);
        long impot2 = simulateur.calculImpot(200000, SituationFamiliale.CELIBATAIRE, 0, 0, true);

        assertAll(
            () -> assertEquals(685L, impot1),
            () -> assertEquals(60768L, impot2)
        );
    }
}
