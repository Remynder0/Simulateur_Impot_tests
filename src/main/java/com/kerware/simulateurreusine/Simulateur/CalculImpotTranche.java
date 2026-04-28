package com.kerware.simulateurreusine.Simulateur;

public class CalculImpotTranche {

    private final ParametreCalculImpot p;


    public CalculImpotTranche(ParametreCalculImpot p) {
        this.p = p;
    }

    public double calculImpot() {

        p.setmImpDecl(calcul(p.getrFRef(), p.getNbPtsDecl()));
        p.setmImp(calcul(p.getrFRef(), p.getNbPtsTotal()));


        return (int) Math.round(plafonnement());
    }

    private double calcul(double rFRef, double nbParts) {
        double impot = 0;
        int[] limites = ParametreCalculImpotCommun.getLimites();
        double[] taux = ParametreCalculImpotCommun.getTaux();

        p.setrImposable(rFRef / nbParts);

        if ( p.getrImposable() < limites[1] ) {
            impot = p.getrImposable() * taux[0];
        } else if ( p.getrImposable() < limites[2] ) {
            impot = ( limites[1] - limites[0] ) * taux[0] + ( p.getrImposable() - limites[1] ) * taux[1];
        } else if ( p.getrImposable() < limites[3] ) {
            impot = ( limites[1] - limites[0] ) * taux[0] + ( limites[2] - limites[1] ) * taux[1] + ( p.getrImposable() - limites[2] ) * taux[2];
        } else if ( p.getrImposable() < limites[4] ) {
            impot = ( limites[1] - limites[0] ) * taux[0] + ( limites[2] - limites[1] ) * taux[1] + ( limites[3] - limites[2] ) * taux[2] + ( p.getrImposable() - limites[3] ) * taux[3];
        } else {
            impot = ( limites[1] - limites[0] ) * taux[0] + ( limites[2] - limites[1] ) * taux[1] + ( limites[3] - limites[2] ) * taux[2] + ( limites[4] - limites[3]) * taux [3]+ ( p.getrImposable() - limites [4]) * taux [4];
        }

        impot = impot * p.getNbPtsDecl();

        return (int) Math.round(impot);
    }

    private double plafonnement(){

        double baisseImpot = p.getmImpDecl() - p.getmImp();

        double ecartParts = p.getNbPtsTotal() - p.getNbPtsDecl();

        double plafond = (ecartParts / 0.5) * ParametreCalculImpotCommun.getPlafDemiPart();

        if ( baisseImpot >= plafond ) {
            return p.getmImpDecl() - plafond;
        } else {
            return p.getmImp();
        }

    }
}
