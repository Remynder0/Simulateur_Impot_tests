package com.kerware.simulateur_reusine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class SituationFamilialeTest {

    @Test
    void doit_contienir_celibataire() {
        assertNotNull(SituationFamiliale.CELIBATAIRE);
        assertEquals("CELIBATAIRE", SituationFamiliale.CELIBATAIRE.name());
    }

    @Test
    void doit_contienir_pacse() {
        assertNotNull(SituationFamiliale.PACSE);
        assertEquals("PACSE", SituationFamiliale.PACSE.name());
    }

    @Test
    void doit_contienir_marie() {
        assertNotNull(SituationFamiliale.MARIE);
        assertEquals("MARIE", SituationFamiliale.MARIE.name());
    }

    @Test
    void doit_contienir_divorce() {
        assertNotNull(SituationFamiliale.DIVORCE);
        assertEquals("DIVORCE", SituationFamiliale.DIVORCE.name());
    }

    @Test
    void doit_contienir_veuf() {
        assertNotNull(SituationFamiliale.VEUF);
        assertEquals("VEUF", SituationFamiliale.VEUF.name());
    }
}
