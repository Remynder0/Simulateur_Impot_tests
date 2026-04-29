package com.kerware.simulateur_reusine;

import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.getDemiPartEnf;
import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.getLimites;
import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.getPlafDemiPart;
import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.getTaux;

/**
 * Calcule l'impôt brut à partir du revenu fiscal de référence et des parts.
 *
 * La classe applique les tranches progressives puis le plafonnement lié aux
 * demi-parts supplémentaires du foyer.
 */
public final class CalculImpotTranche {

    private static final int FIRST_INDEX = 0;
    private static final int NEXT_INDEX = 1;

    private final ParametreCalculImpot parametreCalculImpot;


    public CalculImpotTranche(ParametreCalculImpot parametreCalculImpotLocal) {
        parametreCalculImpot = parametreCalculImpotLocal;
    }

    public double calculImpot() {

        parametreCalculImpot.setmImpDecl(
                calcul(parametreCalculImpot.getrFRef(), parametreCalculImpot.getNbPtsDecl()));
        parametreCalculImpot.setmImp(
                calcul(parametreCalculImpot.getrFRef(), parametreCalculImpot.getNbPtsTotal()));


        return Math.round(plafonnement());
    }

    private double calcul(double revenuFiscalReference, double nbParts) {
        double impot = 0;
        int[] limites = getLimites();
        double[] taux = getTaux();

        parametreCalculImpot.setrImposable(revenuFiscalReference / nbParts);

        for (int index = FIRST_INDEX; index < taux.length; index++) {
            double debutTranche = limites[index];
            double finTranche = limites[index + NEXT_INDEX];

            if (parametreCalculImpot.getrImposable() < finTranche) {
                impot += (parametreCalculImpot.getrImposable() - debutTranche) * taux[index];
                return Math.round(impot * nbParts);
            }

            impot += (finTranche - debutTranche) * taux[index];
        }

        return Math.round(impot * nbParts);
    }

    private double plafonnement() {

        double baisseImpot = parametreCalculImpot.getmImpDecl() - parametreCalculImpot.getmImp();

        double ecartParts = parametreCalculImpot.getNbPtsTotal()
            - parametreCalculImpot.getNbPtsDecl();

        double plafond = (ecartParts / getDemiPartEnf())
            * getPlafDemiPart();

        if ( baisseImpot >= plafond ) {
            return parametreCalculImpot.getmImpDecl() - plafond;
        }

        return parametreCalculImpot.getmImp();

    }
}
