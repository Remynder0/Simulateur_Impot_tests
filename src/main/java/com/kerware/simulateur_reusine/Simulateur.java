package com.kerware.simulateur_reusine;


/**
 * Orchestrateur du calcul d'impôt sur le revenu net.
 *
 * Cette classe enchaîne les étapes métier dans l'ordre attendu : abattement,
 * parts fiscales, impôt par tranche, puis décote.
 */
public final class Simulateur implements ICalculateurImpot {

    private final ParametreCalculImpot parametreCalculImpot = new ParametreCalculImpot();


    /**
     * Calcule l'impôt sur le revenu net pour les paramètres fournis.
     */
    public long calculImpot(int revenuNet, SituationFamiliale situationFamiliale,
            int nombreEnfants, int nombreEnfantsHandicapes, boolean parentIsole) {

        parametreCalculImpot.setrNet(revenuNet);
        parametreCalculImpot.setSitFam(situationFamiliale);
        parametreCalculImpot.setNbEnf(nombreEnfants);
        parametreCalculImpot.setNbEnfH(nombreEnfantsHandicapes);
        parametreCalculImpot.setParIso(parentIsole);

        // Étape 1 : appliquer l'abattement forfaitaire et produire le revenu fiscal de référence.
        CalculAbattement calculAbattement = new CalculAbattement(parametreCalculImpot);

        double revenuFiscalReference = calculAbattement.calculAbattementNet();
        parametreCalculImpot.setrFRef(revenuFiscalReference);

        System.out.println("Abattement : " + parametreCalculImpot.getAbt());

        // Étape 2 : calculer le nombre de parts du foyer fiscal.
        CalculParts calculParts = new CalculParts(parametreCalculImpot);

        calculParts.calculPartsNet();

        System.out.println("Nombre de parts : " + parametreCalculImpot.getNbPtsTotal());

        // Étape 3 : calculer l'impôt brut, puis appliquer le plafonnement.
        CalculImpotTranche calculImpotTranche = new CalculImpotTranche(parametreCalculImpot);

        double impotApresPlafonnement = calculImpotTranche.calculImpot();
        parametreCalculImpot.setmImp(impotApresPlafonnement);

        System.out.println("Impot avant décote : " + parametreCalculImpot.getmImp());

        // Étape 4 : appliquer la décote si le foyer est éligible.
        CalculDecote calculDecote = new CalculDecote(parametreCalculImpot);

        calculDecote.calculerDecote();

        System.out.println("Decote : " + parametreCalculImpot.getDecote());
        parametreCalculImpot.setImpotRevenuNet((int) Math.round(parametreCalculImpot.getmImp()));

        return (long) parametreCalculImpot.getmImp();
    }

    public void setRevenusNet(int rn) {
        parametreCalculImpot.setrNet(rn);
    }

    public void setSituationFamiliale(SituationFamiliale sf) {
        parametreCalculImpot.setSitFam(sf);
    }

    public void setNbEnfantsACharge(int nbe) {
        parametreCalculImpot.setNbEnf(nbe);
    }

    public void setNbEnfantsSituationHandicap(int nbesh) {
        parametreCalculImpot.setNbEnfH(nbesh);
    }

    public void setParentIsole(boolean pi) {
        parametreCalculImpot.setParIso(pi);
    }

    public void calculImpotSurRevenuNet() {
        calculImpot(parametreCalculImpot.getrNet(), parametreCalculImpot.getSitFam(),
                parametreCalculImpot.getNbEnf(), parametreCalculImpot.getNbEnfH(),
                parametreCalculImpot.isParIso());
    }

    public int getRevenuFiscalReference() {
        return (int) parametreCalculImpot.getrFRef();
    }

    public int getAbattement() {
        return (int) parametreCalculImpot.getAbt();
    }

    public int getNbPartsFoyerFiscal() {
        return (int) parametreCalculImpot.getNbPtsTotal();
    }

    public int getImpotAvantDecote() {
        return (int) parametreCalculImpot.getmImp();
    }

    public int getDecote() {
        return (int) parametreCalculImpot.getDecote();
    }

    public int getImpotSurRevenuNet() {
        return parametreCalculImpot.getImpotRevenuNet();
    }
}
