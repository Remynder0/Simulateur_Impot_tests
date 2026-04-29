package com.kerware.simulateur_reusine;

import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.getlAbtMax;
import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.getlAbtMin;
import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.gettAbt;

/**
 * Gère le calcul de l'abattement forfaitaire appliqué au revenu net.
 *
 * Règles appliquées :
 * - taux forfaitaire {@code tAbt}
 * - plafond maximum {@code lAbtMax}
 * - plancher minimum {@code lAbtMin}
 */
public final class CalculAbattement {

    private final ParametreCalculImpot parametreCalculImpot;

    public CalculAbattement(ParametreCalculImpot parametreCalculImpotLocal) {
        parametreCalculImpot = parametreCalculImpotLocal;
    }

    /**
     * Calcule le montant d'abattement selon les paramètres courants.
     *
     * @return montant d'abattement arrondi à l'entier le plus proche
     */
    private double calculAbattement() {
        double abattement = parametreCalculImpot.getrNet() * gettAbt();

        if (abattement > getlAbtMax()) {
            abattement = getlAbtMax();
        } else if (abattement < getlAbtMin()) {
            abattement = getlAbtMin();
        }

        return Math.round(abattement);
    }

     /**
      * Calcule le revenu net après abattement.
      *
      * La valeur d'abattement calculée est également stockée dans
      * {@link ParametreCalculImpot#setAbt(double)}.
      *
      * @return revenu net après déduction de l'abattement
      */
     public double calculAbattementNet() {
         parametreCalculImpot.setAbt(calculAbattement());
         return parametreCalculImpot.getrNet() - parametreCalculImpot.getAbt();
     }
}
