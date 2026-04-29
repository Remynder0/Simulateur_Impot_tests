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
public final class CalculDecote {

    private final ParametreCalculImpot parametreCalculImpot;

    public CalculDecote(ParametreCalculImpot parametreCalculImpotLocal) {
        parametreCalculImpot = parametreCalculImpotLocal;
    }

    /**
     * Calcule la décote à partir de l'impôt courant et la conserve dans l'état.
     */
    public void calculerDecote() {
        double decote = 0;

        // Le seuil dépend du profil du déclarant principal.
        if (parametreCalculImpot.getNbPtsDecl() == getNbPartPacseMarie()) {
            if (parametreCalculImpot.getmImp() < getSeuilDecoteDeclarantCouple()) {
                decote = getDecoteMaxDeclarantCouple()
                    - (parametreCalculImpot.getmImp() * getTauxDecote());
            }
        } else if (parametreCalculImpot.getmImp() < getSeuilDecoteDeclarantSeul()) {
                decote = getDecoteMaxDeclarantSeul()
                    - (parametreCalculImpot.getmImp() * getTauxDecote());
        }

        decote = Math.round(decote);
        // La décote ne peut jamais dépasser l'impôt restant à payer.
        if (parametreCalculImpot.getmImp() <= decote) {
            decote = parametreCalculImpot.getmImp();
        }

        parametreCalculImpot.setDecote(decote);
        parametreCalculImpot.setmImp(Math.round(parametreCalculImpot.getmImp() - decote));
    }
}
