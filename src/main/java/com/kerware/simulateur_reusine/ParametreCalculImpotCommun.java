package com.kerware.simulateur_reusine;

/**
 * Regroupe les paramètres fiscaux partagés par les différents calculateurs.
 *
 * La classe centralise les barèmes, plafonds et seuils pour éviter de dupliquer
 * les constantes dans chaque étape du calcul.
 */
public class ParametreCalculImpotCommun {

    private static int[] limites = new int[]{0, 11294, 28797, 82341, 177106, Integer.MAX_VALUE};
    private static double[] taux = new double[]{0.0, 0.11, 0.3, 0.41, 0.45};

    private static int lAbtMax = 14171;
    private static int lAbtMin = 495;
    private static double tAbt = 0.1;

    private static double plafDemiPart = 1759;

    private static double seuilDecoteDeclarantSeul = 1929;
    private static double seuilDecoteDeclarantCouple    = 3191;

    private static double decoteMaxDeclarantSeul = 873;
    private static double decoteMaxDeclarantCouple = 1444;
    private static double tauxDecote = 0.4525;

    private static double nbSeuilEnf = 2;
    private static double demiPartEnf = 0.5;
    private static double partEnf = 1;
    private static double nbPartPacseMarie = 2;

    public static double getPartVeufEnf() {
        return partVeufEnf;
    }

    public static double getNbPartPacseMarie() {
        return nbPartPacseMarie;
    }

    public static double getPartEnf() {
        return partEnf;
    }

    public static double getDemiPartEnf() {
        return demiPartEnf;
    }

    public static double getNbSeuilEnf() {
        return nbSeuilEnf;
    }

    private static double partVeufEnf = 2;


    public static int[] getLimites() {
        return limites;
    }

    public static double[] getTaux() {
        return taux;
    }

    public static int getlAbtMax() {
        return lAbtMax;
    }

    public static int getlAbtMin() {
        return lAbtMin;
    }

    public static double gettAbt() {
        return tAbt;
    }

    public static double getPlafDemiPart() {
        return plafDemiPart;
    }

    public static double getSeuilDecoteDeclarantSeul() {
        return seuilDecoteDeclarantSeul;
    }

    public static double getSeuilDecoteDeclarantCouple() {
        return seuilDecoteDeclarantCouple;
    }

    public static double getDecoteMaxDeclarantSeul() {
        return decoteMaxDeclarantSeul;
    }

    public static double getDecoteMaxDeclarantCouple() {
        return decoteMaxDeclarantCouple;
    }

    public static double getTauxDecote() {
        return tauxDecote;
    }
}
