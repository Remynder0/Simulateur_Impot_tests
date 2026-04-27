package com.kerware.simulateurreusine.Simulateur;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ICalculateurImpotGettersTest {

    @Test
    void getters_doivent_renvoyer_les_bonnes_valeurs_cas_marie_3_enfants() {
        ICalculateurImpot calculateur = newCalculateur();

        calculateur.setRevenusNet(65000);
        calculateur.setSituationFamiliale(SituationFamiliale.MARIE);
        calculateur.setNbEnfantsACharge(3);
        calculateur.setNbEnfantsSituationHandicap(0);
        calculateur.setParentIsole(false);
        calculateur.calculImpotSurRevenuNet();

        assertAll(
                () -> assertEquals(58500, calculateur.getRevenuFiscalReference()),
                () -> assertEquals(6500, calculateur.getAbattement()),
                () -> assertEquals(4, calculateur.getNbPartsFoyerFiscal()),
                () -> assertEquals(1466, calculateur.getImpotAvantDecote()),
                () -> assertEquals(781, calculateur.getDecote()),
                () -> assertEquals(685, calculateur.getImpotSurRevenuNet())
        );
    }

    @Test
    void getters_doivent_respecter_abattement_minimum_et_impot_nul() {
        ICalculateurImpot calculateur = newCalculateur();

        calculateur.setRevenusNet(4000);
        calculateur.setSituationFamiliale(SituationFamiliale.CELIBATAIRE);
        calculateur.setNbEnfantsACharge(0);
        calculateur.setNbEnfantsSituationHandicap(0);
        calculateur.setParentIsole(false);
        calculateur.calculImpotSurRevenuNet();

        assertAll(
                () -> assertEquals(495, calculateur.getAbattement()),
                () -> assertEquals(3505, calculateur.getRevenuFiscalReference()),
                () -> assertEquals(1, calculateur.getNbPartsFoyerFiscal()),
                () -> assertEquals(0, calculateur.getImpotAvantDecote()),
                () -> assertEquals(0, calculateur.getDecote()),
                () -> assertEquals(0, calculateur.getImpotSurRevenuNet())
        );
    }

    @Test
    void getters_doivent_respecter_abattement_maximum_et_sans_decote() {
        ICalculateurImpot calculateur = newCalculateur();

        calculateur.setRevenusNet(300000);
        calculateur.setSituationFamiliale(SituationFamiliale.CELIBATAIRE);
        calculateur.setNbEnfantsACharge(0);
        calculateur.setNbEnfantsSituationHandicap(0);
        calculateur.setParentIsole(false);
        calculateur.calculImpotSurRevenuNet();

        assertAll(
                () -> assertEquals(14171, calculateur.getAbattement()),
                () -> assertEquals(285829, calculateur.getRevenuFiscalReference()),
                () -> assertEquals(1, calculateur.getNbPartsFoyerFiscal()),
                () -> assertEquals(105768, calculateur.getImpotAvantDecote()),
                () -> assertEquals(0, calculateur.getDecote()),
                () -> assertEquals(105768, calculateur.getImpotSurRevenuNet())
        );
    }

    @Test
    void getter_nb_parts_doit_integrer_enfants_handicap_et_parent_isole() {
        ICalculateurImpot calculateur = newCalculateur();

        calculateur.setRevenusNet(50000);
        calculateur.setSituationFamiliale(SituationFamiliale.DIVORCE);
        calculateur.setNbEnfantsACharge(3);
        calculateur.setNbEnfantsSituationHandicap(1);
        calculateur.setParentIsole(true);
        calculateur.calculImpotSurRevenuNet();

        assertEquals(4, calculateur.getNbPartsFoyerFiscal());
    }

    private ICalculateurImpot newCalculateur() {
        List<String> candidates = List.of(
                "com.kerware.simulateurreusine.Simulateur.CalculateurImpot",
                "com.kerware.simulateurreusine.Simulateur.Calculateur",
                "com.kerware.simulateurreusine.Simulateur.CalculateurImpot2024"
        );

        for (String className : candidates) {
            try {
                Class<?> clazz = Class.forName(className);
                if (!ICalculateurImpot.class.isAssignableFrom(clazz)) {
                    continue;
                }
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                return (ICalculateurImpot) constructor.newInstance();
            } catch (Exception ignored) {
                // On essaie la classe candidate suivante.
            }
        }

        fail("Aucune implémentation de ICalculateurImpot trouvée. " +
                "Créez une classe (ex: CalculateurImpot) avec un constructeur sans argument " +
                "dans le package com.kerware.simulateurreusine.Simulateur.");
        return null;
    }
}
