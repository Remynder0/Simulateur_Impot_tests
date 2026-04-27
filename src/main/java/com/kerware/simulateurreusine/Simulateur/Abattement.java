package com.kerware.simulateurreusine.Simulateur;

public class Abattement {

    private final ParametreCalculImpot parametreCalculImpot;

    public Abattement(ParametreCalculImpot parametreCalculImpot) {
        this.parametreCalculImpot = parametreCalculImpot;
    }

    private double calculAbattement() {
        double abt = parametreCalculImpot.getrNet() * ParametreCalculImpotCommun.gettAbt();

        if (abt > ParametreCalculImpotCommun.getlAbtMax()) {
            abt = ParametreCalculImpotCommun.getlAbtMax();
        } else if (abt < ParametreCalculImpotCommun.getlAbtMin()) {
            abt = ParametreCalculImpotCommun.getlAbtMin();
        }

        return abt;
    }

     public double calculAbattementNet() {
         parametreCalculImpot.setAbt(calculAbattement());
         return parametreCalculImpot.getrNet() - parametreCalculImpot.getAbt();
     }
}
