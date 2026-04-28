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
        com.kerware.simulateurreusine.Simulateur.Simulateur modern = new com.kerware.simulateurreusine.Simulateur.Simulateur();

        assertEquals(
                legacy.calculImpot(65000, SituationFamiliale.MARIE, 3, 0, false),
                modern.calculImpot(65000, com.kerware.simulateurreusine.Simulateur.SituationFamiliale.MARIE, 3, 0, false)
        );
        assertEquals(
                legacy.calculImpot(200000, SituationFamiliale.CELIBATAIRE, 0, 0, true),
                modern.calculImpot(200000, com.kerware.simulateurreusine.Simulateur.SituationFamiliale.CELIBATAIRE, 0, 0, true)
        );
    }

    private void assertSameAsLegacy(
            int revenuNet,
            SituationFamiliale situationFamiliale,
            int nbEnfants,
            int nbEnfantsHandicapes,
            boolean parentIsole) {

        long legacyResultat = new Simulateur().calculImpot(revenuNet, situationFamiliale, nbEnfants, nbEnfantsHandicapes, parentIsole);
        long modernResultat = new com.kerware.simulateurreusine.Simulateur.Simulateur().calculImpot(
                revenuNet,
                com.kerware.simulateurreusine.Simulateur.SituationFamiliale.valueOf(situationFamiliale.name()),
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
