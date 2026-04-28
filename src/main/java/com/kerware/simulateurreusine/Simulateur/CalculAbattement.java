package com.kerware.simulateurreusine.Simulateur;

import static com.kerware.simulateurreusine.Simulateur.ParametreCalculImpotCommun.*;

public class CalculAbattement {

    private final ParametreCalculImpot parametreCalculImpot;

    public CalculAbattement(ParametreCalculImpot parametreCalculImpot) {
        this.parametreCalculImpot = parametreCalculImpot;
    }

    private double calculAbattement() {
        double abt = parametreCalculImpot.getrNet() * gettAbt();

        if (abt > getlAbtMax()) abt = getlAbtMax();
        else if (abt < getlAbtMin()) abt = getlAbtMin();

        return (int) Math.round(abt);
    }

     public double calculAbattementNet() {
         parametreCalculImpot.setAbt(calculAbattement());
         return parametreCalculImpot.getrNet() - parametreCalculImpot.getAbt();
     }
}
