package com.kerware.simulateurreusine.Simulateur;


import static com.kerware.simulateurreusine.Simulateur.ParametreCalculImpotCommun.*;

public class CalculDecote {

    ParametreCalculImpot p;

    CalculDecote(ParametreCalculImpot p) {
        this.p = p;
    }

    public void Decote() {
        double decote = 0;

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
        if ( p.getmImp() <= decote ) {
            decote = p.getmImp();
        }

        p.setmImp(Math.round(p.getmImp() - decote));
    }
}
