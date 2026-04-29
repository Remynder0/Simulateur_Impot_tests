package com.kerware.simulateur;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class SimulateurLegacyEquivalenceTest {

    @Test
    void les_resultats_modernes_sont_identiques_au_legacy_sur_les_scenarios_principaux() {
        assertSameAsLegacy(65000, SituationFamiliale.MARIE, 3, 0, false);
        assertSameAsLegacy(65000, SituationFamiliale.MARIE, 3, 1, false);
        assertSameAsLegacy(35000, SituationFamiliale.DIVORCE, 1, 0, true);
        assertSameAsLegacy(35000, SituationFamiliale.DIVORCE, 2, 0, true);
        assertSameAsLegacy(50000, SituationFamiliale.DIVORCE, 3, 0, true);
        assertSameAsLegacy(50000, SituationFamiliale.DIVORCE, 3, 1, true);
        assertSameAsLegacy(200000, SituationFamiliale.CELIBATAIRE, 0, 0, true);
    }

    @Test
    void les_resultats_modernes_sont_identiques_au_legacy_sur_un_enchainement_de_calculs() {
        Simulateur legacy = new Simulateur();
        com.kerware.simulateur_reusine.Simulateur modern = new com.kerware.simulateur_reusine.Simulateur();

        assertEquals(
                legacy.calculImpot(65000, SituationFamiliale.MARIE, 3, 0, false),
                modern.calculImpot(65000, com.kerware.simulateur_reusine.SituationFamiliale.MARIE, 3, 0, false)
        );
        assertEquals(
                legacy.calculImpot(200000, SituationFamiliale.CELIBATAIRE, 0, 0, true),
                modern.calculImpot(200000, com.kerware.simulateur_reusine.SituationFamiliale.CELIBATAIRE, 0, 0, true)
        );
    }

    private void assertSameAsLegacy(
            int revenuNet,
            SituationFamiliale situationFamiliale,
            int nbEnfants,
            int nbEnfantsHandicapes,
            boolean parentIsole) {

        long legacyResultat = new Simulateur().calculImpot(revenuNet, situationFamiliale, nbEnfants, nbEnfantsHandicapes, parentIsole);
        long modernResultat = new com.kerware.simulateur_reusine.Simulateur().calculImpot(
                revenuNet,
                com.kerware.simulateur_reusine.SituationFamiliale.valueOf(situationFamiliale.name()),
                nbEnfants,
                nbEnfantsHandicapes,
                parentIsole
        );

        assertEquals(
                legacyResultat,
                modernResultat,
                "Résultat différent du legacy pour le scénario "
                        + revenuNet + "/" + situationFamiliale + "/" + nbEnfants + "/" + nbEnfantsHandicapes + "/" + parentIsole
        );
    }
}
