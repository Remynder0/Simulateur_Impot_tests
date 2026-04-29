package com.kerware.simulateur_reusine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class SimulateurMainScenariosTest {

    @Test
    void scenario_main_1_marie_3_enfants_sans_handicap() {
        Simulateur simulateur = new Simulateur();

        long impot = simulateur.calculImpot(65000, SituationFamiliale.MARIE, 3, 0, false);

        assertEquals(685L, impot);
    }

    @Test
    void scenario_main_2_marie_3_enfants_avec_handicap() {
        Simulateur simulateur = new Simulateur();

        long impot = simulateur.calculImpot(65000, SituationFamiliale.MARIE, 3, 1, false);

        assertEquals(0L, impot);
    }

    @Test
    void scenario_main_3_divorce_1_enfant_parent_isole() {
        Simulateur simulateur = new Simulateur();

        long impot = simulateur.calculImpot(35000, SituationFamiliale.DIVORCE, 1, 0, true);

        assertEquals(550L, impot);
    }

    @Test
    void scenario_main_4_divorce_2_enfants_parent_isole() {
        Simulateur simulateur = new Simulateur();

        long impot = simulateur.calculImpot(35000, SituationFamiliale.DIVORCE, 2, 0, true);

        assertEquals(0L, impot);
    }

    @Test
    void scenario_main_5_divorce_3_enfants_parent_isole() {
        Simulateur simulateur = new Simulateur();

        long impot = simulateur.calculImpot(50000, SituationFamiliale.DIVORCE, 3, 0, true);

        assertEquals(1L, impot);
    }

    @Test
    void scenario_main_6_divorce_3_enfants_1_handicap_parent_isole() {
        Simulateur simulateur = new Simulateur();

        long impot = simulateur.calculImpot(50000, SituationFamiliale.DIVORCE, 3, 1, true);

        assertEquals(0L, impot);
    }

    @Test
    void scenario_main_7_celibataire_haut_revenu() {
        Simulateur simulateur = new Simulateur();

        long impot = simulateur.calculImpot(200000, SituationFamiliale.CELIBATAIRE, 0, 0, true);

        assertEquals(60768L, impot);
    }
}
