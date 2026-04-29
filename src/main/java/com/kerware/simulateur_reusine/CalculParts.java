package com.kerware.simulateur_reusine;

import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.getDemiPartEnf;
import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.getNbPartPacseMarie;
import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.getNbSeuilEnf;
import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.getPartEnf;
import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.getPartVeufEnf;

/**
 * Calcule le nombre de parts fiscales d'un foyer selon sa situation.
 *
 * Cette classe encapsule la logique de calcul des parts :
 * - détermination du nombre de parts du déclarant selon la situation familiale
 *   (célibataire, marié/pacsé, divorcé, veuf)
 * - ajout des demi-parts pour les enfants à charge et des majorations
 *   (au-delà d'un seuil d'enfants)
 */

public final class CalculParts {

    private final ParametreCalculImpot parametreCalculImpot;

    public CalculParts(ParametreCalculImpot parametreCalculImpotLocal) {
        parametreCalculImpot = parametreCalculImpotLocal;
    }

    /**
     * Calcule et stocke dans {@link ParametreCalculImpot} les valeurs suivantes :
     * - `nbPtsDecl` : nombre de parts pour le(s) déclarant(s)
     * - `nbPts` : nombre total de parts du foyer (déclarant(s) + enfants)
     *
     * La logique suit les règles métier contenues dans
     * {@link ParametreCalculImpotCommun} (valeurs de demi-part, seuils, plafonds).
     */
    private void calculPartsDeclarant() {
        parametreCalculImpot.setNbPtsDecl(1);

        switch (parametreCalculImpot.getSitFam()) {
            case CELIBATAIRE:
                break;
            case PACSE, MARIE:
                parametreCalculImpot.setNbPtsDecl(getNbPartPacseMarie());
                break;
            case DIVORCE:
                break;
            case VEUF:
                if (parametreCalculImpot.getNbEnf() > 0) {
                    parametreCalculImpot.setNbPtsDecl(getPartVeufEnf());
                }
                break;
            default:
                break;
        }
    }

    private void calculPartsEnfants() {

        // Enfant à charge
        if (parametreCalculImpot.getNbEnf() <= getNbSeuilEnf()) {
                parametreCalculImpot.setNbPts(
                    parametreCalculImpot.getNbPtsDecl()
                        + parametreCalculImpot.getNbEnf() * getDemiPartEnf());
        } else {
            parametreCalculImpot.setNbPts(
                    parametreCalculImpot.getNbPtsDecl()
                        + getPartEnf()
                            + (parametreCalculImpot.getNbEnf() - getNbSeuilEnf()));
        }

        // Parent Isolé
        if (parametreCalculImpot.isParIso() && parametreCalculImpot.getNbEnf() > 0) {
            parametreCalculImpot.setNbPts(parametreCalculImpot.getNbPts() + getDemiPartEnf());
        }

        // Enfant handicapé
        parametreCalculImpot.setNbPts(
            parametreCalculImpot.getNbPts()
                + parametreCalculImpot.getNbEnfH() * getDemiPartEnf());
    }

    /**
     * Expose le nombre total de parts calculé pour le foyer.
     *
     * @return nombre total de parts (peut contenir des demi-parts)
     */
    public double calculPartsNet() {
        calculPartsDeclarant();
        calculPartsEnfants();
        parametreCalculImpot.setNbPtsTotal(parametreCalculImpot.getNbPts());
        return parametreCalculImpot.getNbPts();
    }
}
