package com.kerware.simulateur_reusine;

/**
 * Regroupe les paramètres fiscaux partagés par les différents calculateurs.
 *
 * La classe centralise les barèmes, plafonds et seuils pour éviter de dupliquer
 * les constantes dans chaque étape du calcul.
 */
public final class ParametreCalculImpotCommun {

    private static final int[] LIMITES = new int[] {
        0, 11294, 28797, 82341, 177106, Integer.MAX_VALUE
    };
    private static final double[] TAUX = new double[] {0.0, 0.11, 0.3, 0.41, 0.45};

    private static final int L_ABT_MAX = 14171;
    private static final int L_ABT_MIN = 495;
    private static final double T_ABT = 0.1;

    private static final double PLAF_DEMI_PART = 1759;

    private static final double SEUIL_DECOTE_DECLARANT_SEUL = 1929;
    private static final double SEUIL_DECOTE_DECLARANT_COUPLE = 3191;

    private static final double DECOTE_MAX_DECLARANT_SEUL = 873;
    private static final double DECOTE_MAX_DECLARANT_COUPLE = 1444;
    private static final double TAUX_DECOTE = 0.4525;

    private static final double NB_SEUIL_ENF = 2;
    private static final double DEMI_PART_ENF = 0.5;
    private static final double PART_ENF = 1;
    private static final double NB_PART_PACSE_MARIE = 2;
    private static final double PART_VEUF_ENF = 2;

    private ParametreCalculImpotCommun() {
    }

    public static double getPartVeufEnf() {
        return PART_VEUF_ENF;
    }

    public static double getNbPartPacseMarie() {
        return NB_PART_PACSE_MARIE;
    }

    public static double getPartEnf() {
        return PART_ENF;
    }

    public static double getDemiPartEnf() {
        return DEMI_PART_ENF;
    }

    public static double getNbSeuilEnf() {
        return NB_SEUIL_ENF;
    }

    public static int[] getLimites() {
        return LIMITES;
    }

    public static double[] getTaux() {
        return TAUX;
    }

    public static int getlAbtMax() {
        return L_ABT_MAX;
    }

    public static int getlAbtMin() {
        return L_ABT_MIN;
    }

    public static double gettAbt() {
        return T_ABT;
    }

    public static double getPlafDemiPart() {
        return PLAF_DEMI_PART;
    }

    public static double getSeuilDecoteDeclarantSeul() {
        return SEUIL_DECOTE_DECLARANT_SEUL;
    }

    public static double getSeuilDecoteDeclarantCouple() {
        return SEUIL_DECOTE_DECLARANT_COUPLE;
    }

    public static double getDecoteMaxDeclarantSeul() {
        return DECOTE_MAX_DECLARANT_SEUL;
    }

    public static double getDecoteMaxDeclarantCouple() {
        return DECOTE_MAX_DECLARANT_COUPLE;
    }

    public static double getTauxDecote() {
        return TAUX_DECOTE;
    }
}
