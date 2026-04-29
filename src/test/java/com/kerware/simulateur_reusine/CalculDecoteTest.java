package com.kerware.simulateur_reusine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class CalculDecoteTest {

    @Test
    void applique_la_decote_pour_un_declarant_seul_sous_le_seuil() {
        ParametreCalculImpot p = new ParametreCalculImpot();
        p.setNbPtsDecl(1);
        p.setmImp(1000);

        CalculDecote calculDecote = new CalculDecote(p);
        calculDecote.Decote();

        assertEquals(579d, p.getmImp());
    }

    @Test
    void applique_la_decote_pour_un_couple_sous_le_seuil() {
        ParametreCalculImpot p = new ParametreCalculImpot();
        p.setNbPtsDecl(ParametreCalculImpotCommun.getNbPartPacseMarie());
        p.setmImp(3000);

        CalculDecote calculDecote = new CalculDecote(p);
        calculDecote.Decote();

        assertEquals(2913d, p.getmImp());
    }

    @Test
    void limite_la_decote_a_l_impot_si_elle_le_depasse() {
        ParametreCalculImpot p = new ParametreCalculImpot();
        p.setNbPtsDecl(1);
        p.setmImp(100);

        CalculDecote calculDecote = new CalculDecote(p);
        calculDecote.Decote();

        assertEquals(0d, p.getmImp());
    }

    @Test
    void n_applique_pas_de_decote_au_seuil_declarant_seul() {
        ParametreCalculImpot p = new ParametreCalculImpot();
        p.setNbPtsDecl(1);
        p.setmImp(ParametreCalculImpotCommun.getSeuilDecoteDeclarantSeul());

        CalculDecote calculDecote = new CalculDecote(p);
        calculDecote.Decote();

        assertEquals(ParametreCalculImpotCommun.getSeuilDecoteDeclarantSeul(), p.getmImp());
    }
}