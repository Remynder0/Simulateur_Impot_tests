package com.kerware.simulateur;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class SimulateurLegacyEquivalenceTest {

    @Test
    void les_resultats_modernes_correspondent_aux_scenarios_attendus() {
        assertModernResult(685, 65000, SituationFamiliale.MARIE, 3, 0, false);
        assertModernResult(0, 65000, SituationFamiliale.MARIE, 3, 1, false);
        assertModernResult(550, 35000, SituationFamiliale.DIVORCE, 1, 0, true);
        assertModernResult(0, 35000, SituationFamiliale.DIVORCE, 2, 0, true);
        assertModernResult(1, 50000, SituationFamiliale.DIVORCE, 3, 0, true);
        assertModernResult(0, 50000, SituationFamiliale.DIVORCE, 3, 1, true);
        assertModernResult(60768, 200000, SituationFamiliale.CELIBATAIRE, 0, 0, true);
    }

    @Test
    void les_resultats_modernes_sont_stables_sur_un_enchainement_de_calculs() {
        com.kerware.simulateur_reusine.Simulateur modern = new com.kerware.simulateur_reusine.Simulateur();

        assertEquals(
                685,
                modern.calculImpot(65000, com.kerware.simulateur_reusine.SituationFamiliale.MARIE, 3, 0, false)
        );
        assertEquals(
                60768,
                modern.calculImpot(200000, com.kerware.simulateur_reusine.SituationFamiliale.CELIBATAIRE, 0, 0, true)
        );
    }

    private void assertModernResult(
            long expected,
            int revenuNet,
            SituationFamiliale situationFamiliale,
            int nbEnfants,
            int nbEnfantsHandicapes,
            boolean parentIsole) {

        long modernResultat = new com.kerware.simulateur_reusine.Simulateur().calculImpot(
                revenuNet,
                com.kerware.simulateur_reusine.SituationFamiliale.valueOf(situationFamiliale.name()),
                nbEnfants,
                nbEnfantsHandicapes,
                parentIsole
        );

        assertEquals(
                expected,
                modernResultat,
                "Résultat différent du scénario attendu pour le scénario "
                        + revenuNet + "/" + situationFamiliale + "/" + nbEnfants + "/" + nbEnfantsHandicapes + "/" + parentIsole
        );
    }
}
