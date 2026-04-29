package com.kerware.simulateur_reusine;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class CalculImpotTrancheTest {

    @Test
    void calcule_impot_tranche_a_0_pour_revenu_imposable_sous_premier_seuil() {
        ParametreCalculImpot p = new ParametreCalculImpot();
        p.setrFRef(10000);
        p.setNbPtsDecl(1);
        p.setNbPtsTotal(1);

        CalculImpotTranche calcul = new CalculImpotTranche(p);
        double impot = calcul.calculImpot();

        assertEquals(0d, impot);
    }

    @Test
    void calcule_impot_dans_la_deuxieme_tranche() {
        ParametreCalculImpot p = new ParametreCalculImpot();
        p.setrFRef(20000);
        p.setNbPtsDecl(1);
        p.setNbPtsTotal(1);

        CalculImpotTranche calcul = new CalculImpotTranche(p);
        double impot = calcul.calculImpot();

        assertEquals(958d, impot);
    }

    @Test
    void calcule_impot_dans_la_troisieme_tranche() {
        ParametreCalculImpot p = new ParametreCalculImpot();
        p.setrFRef(50000);
        p.setNbPtsDecl(1);
        p.setNbPtsTotal(1);

        CalculImpotTranche calcul = new CalculImpotTranche(p);
        double impot = calcul.calculImpot();

        assertEquals(8286d, impot);
    }

    @Test
    void calcule_impot_dans_la_quatrieme_tranche() {
        ParametreCalculImpot p = new ParametreCalculImpot();
        p.setrFRef(100000);
        p.setNbPtsDecl(1);
        p.setNbPtsTotal(1);

        CalculImpotTranche calcul = new CalculImpotTranche(p);
        double impot = calcul.calculImpot();

        assertEquals(25229d, impot);
    }

    @Test
    void calcule_impot_dans_la_cinquieme_tranche() {
        ParametreCalculImpot p = new ParametreCalculImpot();
        p.setrFRef(200000);
        p.setNbPtsDecl(1);
        p.setNbPtsTotal(1);

        CalculImpotTranche calcul = new CalculImpotTranche(p);
        double impot = calcul.calculImpot();

        assertEquals(67144d, impot);
    }

    @Test
    void applique_plafonnement_quand_baisse_impot_depasse_plafond() {
        ParametreCalculImpot p = new ParametreCalculImpot();
        p.setrFRef(50000);
        p.setNbPtsDecl(1);
        p.setNbPtsTotal(2);

        CalculImpotTranche calcul = new CalculImpotTranche(p);
        double impot = calcul.calculImpot();

        assertAll(
            () -> assertEquals(8286d, p.getmImpDecl()),
            () -> assertEquals(3015d, p.getmImp()),
            () -> assertEquals(4768d, impot)
        );
    }

    @Test
    void n_applique_pas_plafonnement_quand_baisse_impot_reste_sous_plafond() {
        ParametreCalculImpot p = new ParametreCalculImpot();
        p.setrFRef(30000);
        p.setNbPtsDecl(1);
        p.setNbPtsTotal(2);

        CalculImpotTranche calcul = new CalculImpotTranche(p);
        double impot = calcul.calculImpot();

        assertAll(
            () -> assertEquals(2286d, p.getmImpDecl()),
            () -> assertEquals(815d, p.getmImp()),
            () -> assertEquals(815d, impot)
        );
    }
}
