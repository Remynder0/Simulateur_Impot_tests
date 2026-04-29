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

public class CalculParts {

    private final ParametreCalculImpot p;

    public CalculParts(ParametreCalculImpot p) {
        this.p = p;
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
        p.setNbPtsDecl(1);

        switch (p.getSitFam()) {
            case CELIBATAIRE:
                break;
            case PACSE, MARIE:
                p.setNbPtsDecl(getNbPartPacseMarie());
                break;
            case DIVORCE:
                break;
            case VEUF:
                if ( p.getNbEnf() > 0 ) p.setNbPtsDecl(getPartVeufEnf());
                break;
            default:
                break;
        }
    }

    private void calculPartsEnfants() {

        // Enfant à charge
        if (p.getNbEnf() <= getNbSeuilEnf()) {
            p.setNbPts(p.getNbPtsDecl() + p.getNbEnf() * getDemiPartEnf());
        } else if (p.getNbEnf() > getNbSeuilEnf()) {
            p.setNbPts(p.getNbPtsDecl() + getPartEnf() + (p.getNbEnf() - getNbSeuilEnf()));
        }

        // Parent Isolé
        if (p.isParIso()) {
            if (p.getNbEnf() > 0) {
                p.setNbPts(p.getNbPts() + getDemiPartEnf());
            }
        }

        // Enfant handicapé
        p.setNbPts(p.getNbPts() + p.getNbEnfH() * getDemiPartEnf());
    }

    /**
     * Expose le nombre total de parts calculé pour le foyer.
     *
     * @return nombre total de parts (peut contenir des demi-parts)
     */
    public double calculPartsNet() {
        calculPartsDeclarant();
        calculPartsEnfants();
        p.setNbPtsTotal(p.getNbPts());
        return p.getNbPts();
    }
}
