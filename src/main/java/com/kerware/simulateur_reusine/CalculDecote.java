package com.kerware.simulateur_reusine;


import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.getDecoteMaxDeclarantCouple;
import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.getDecoteMaxDeclarantSeul;
import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.getNbPartPacseMarie;
import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.getSeuilDecoteDeclarantCouple;
import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.getSeuilDecoteDeclarantSeul;
import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.getTauxDecote;

/**
 * Applique la décote après le calcul de l'impôt brut.
 *
 * La décote dépend de la situation du foyer et ne s'applique que si l'impôt
 * calculé reste sous les seuils réglementaires.
 */
public class CalculDecote {

    ParametreCalculImpot p;

    CalculDecote(ParametreCalculImpot p) {
        this.p = p;
    }

    /**
     * Calcule la décote à partir de l'impôt courant et la conserve dans l'état.
     */
    public void Decote() {
        double decote = 0;

        // Le seuil dépend du profil du déclarant principal.
        if (  p.getNbPtsDecl() == getNbPartPacseMarie()) {
            if ( p.getmImp() < getSeuilDecoteDeclarantCouple()) {
                decote =  getDecoteMaxDeclarantCouple() - ( p.getmImp() * getTauxDecote());
            }
        } else {
            if ( p.getmImp() < getSeuilDecoteDeclarantSeul() ) {
                decote = getDecoteMaxDeclarantSeul() - ( p.getmImp() * getTauxDecote());
            }
        }

        decote = Math.round( decote );
        // La décote ne peut jamais dépasser l'impôt restant à payer.
        if ( p.getmImp() <= decote ) {
            decote = p.getmImp();
        }

        p.setDecote(decote);
        p.setmImp(Math.round(p.getmImp() - decote));
    }
}
