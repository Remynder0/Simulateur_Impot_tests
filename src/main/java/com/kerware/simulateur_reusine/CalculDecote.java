package com.kerware.simulateur_reusine;


import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.getDecoteMaxDeclarantCouple;
import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.getDecoteMaxDeclarantSeul;
import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.getNbPartPacseMarie;
import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.getSeuilDecoteDeclarantCouple;
import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.getSeuilDecoteDeclarantSeul;
import static com.kerware.simulateur_reusine.ParametreCalculImpotCommun.getTauxDecote;

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

        p.setDecote(decote);
        p.setmImp(Math.round(p.getmImp() - decote));
    }
}
