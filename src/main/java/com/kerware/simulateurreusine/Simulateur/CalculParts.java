package com.kerware.simulateurreusine.Simulateur;

import static com.kerware.simulateurreusine.Simulateur.ParametreCalculImpotCommun.*;

public class CalculParts {

    private final ParametreCalculImpot p;


    public CalculParts(ParametreCalculImpot p) {
        this.p = p;
    }

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

    public double calculPartsNet() {
        calculPartsDeclarant();
        calculPartsEnfants();
        p.setNbPtsTotal(p.getNbPts() + p.getNbPtsDecl());
        return p.getNbPts();
    }
}
