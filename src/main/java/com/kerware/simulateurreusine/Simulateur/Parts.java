package com.kerware.simulateurreusine.Simulateur;

import static com.kerware.simulateurreusine.Simulateur.ParametreCalculImpotCommun.*;

public class Parts {

    private final ParametreCalculImpot p;


    public Parts(ParametreCalculImpot p) {
        this.p = p;
    }

    private void calculParts() {
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

        if (p.getNbEnf() <= getNbSeuilEnf()) {
            p.setNbPts(p.getNbPtsDecl() + p.getNbEnf() * getDemiPartEnf());
        } else if (p.getNbEnf() > getNbSeuilEnf()) {
            p.setNbPts(p.getNbPtsDecl() + getPartEnf() + (p.getNbEnf() - getNbSeuilEnf()));
        }
    }

    public double calculPartsNet() {
        calculParts();
        return p.getNbPts();
    }
}
