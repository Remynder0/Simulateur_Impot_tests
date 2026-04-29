package com.kerware.simulateur_reusine;


public class Simulateur implements ICalculateurImpot {

    ParametreCalculImpot parametreCalculImpot = new ParametreCalculImpot();

    private int l00 = 0 ;
    private int l01 = 11294;
    private int l02 = 28797;
    private int l03 = 82341;
    private int l04 = 177106;
    private int l05 = Integer.MAX_VALUE;

    private int[] limites = new int[6];

    private double t00 = 0.0;
    private double t01 = 0.11;
    private double t02 = 0.3;
    private double t03 = 0.41;
    private double t04 = 0.45;

    private double[] taux = new double[5];

    private  int lAbtMax = 14171;
    private  int lAbtMin = 495;
    private double tAbt = 0.1;

    private double plafDemiPart = 1759;

    private double seuilDecoteDeclarantSeul = 1929;
    private double seuilDecoteDeclarantCouple    = 3191;

    private double decoteMaxDeclarantSeul = 873;
    private double decoteMaxDeclarantCouple = 1444;
    private double tauxDecote = 0.4525;

    private int rNet = 0;
    private int nbEnf = 0;
    private int nbEnfH = 0;

    private double rFRef = 0;
    private double rImposable = 0;

    private double abt = 0;

    private double nbPtsDecl = 0;
    private double nbPts = 0;
    private double decote = 0;

    private double mImpDecl = 0;
    private double mImp = 0;

    private boolean parIso = false;

    private SituationFamiliale sitFam = SituationFamiliale.CELIBATAIRE;

    private int impotRevenuNet = 0;


    // Fonction de calcul de l'impôt sur le revenu net en France en 2024 sur les revenu 2023

    public long calculImpot( int revNet, SituationFamiliale sitFam, int nbEnfants, int nbEnfantsHandicapes, boolean parentIsol) {

        parametreCalculImpot.setrNet(revNet);
        parametreCalculImpot.setSitFam(sitFam);
        parametreCalculImpot.setNbEnf(nbEnfants);
        parametreCalculImpot.setNbEnfH(nbEnfantsHandicapes);
        parametreCalculImpot.setParIso(parentIsol);

        CalculAbattement calculAbattement = new CalculAbattement(parametreCalculImpot);

        double revenuFiscalReference = calculAbattement.calculAbattementNet();
        parametreCalculImpot.setrFRef(revenuFiscalReference);

        System.out.println("Abattement : " + parametreCalculImpot.getAbt());

        CalculParts calculParts = new CalculParts(parametreCalculImpot);

        calculParts.calculPartsNet();

        System.out.println("Nombre de parts : " + parametreCalculImpot.getNbPtsTotal());

        CalculImpotTranche calculImpotTranche = new CalculImpotTranche(parametreCalculImpot);

        double impotApresPlafonnement = calculImpotTranche.calculImpot();
        parametreCalculImpot.setmImp(impotApresPlafonnement);

        System.out.println("Impot avant décote : " + parametreCalculImpot.getmImp());

        CalculDecote calculDecote = new CalculDecote(parametreCalculImpot);

        calculDecote.Decote();

        System.out.println("Decote : " + parametreCalculImpot.getDecote());
        parametreCalculImpot.setImpotRevenuNet((int) Math.round(parametreCalculImpot.getmImp()));

        return (long) parametreCalculImpot.getmImp();
    }

    public static void main(String[] args) {
        Simulateur simulateur = new Simulateur();
        long impot = simulateur.calculImpot(65000, SituationFamiliale.MARIE, 3, 0, false);
        System.out.println("Impot sur le revenu net : " + impot);
        impot = simulateur.calculImpot(65000, SituationFamiliale.MARIE, 3, 1, false);
        System.out.println("Impot sur le revenu net : " + impot);
        impot = simulateur.calculImpot(35000, SituationFamiliale.DIVORCE, 1, 0, true);
        System.out.println("Impot sur le revenu net : " + impot);
        impot = simulateur.calculImpot(35000, SituationFamiliale.DIVORCE, 2, 0, true);
        System.out.println("Impot sur le revenu net : " + impot);
        impot = simulateur.calculImpot(50000, SituationFamiliale.DIVORCE, 3, 0, true);
        System.out.println("Impot sur le revenu net : " + impot);
        impot = simulateur.calculImpot(50000, SituationFamiliale.DIVORCE, 3, 1, true);
        System.out.println("Impot sur le revenu net : " + impot);
        impot = simulateur.calculImpot(200000, SituationFamiliale.CELIBATAIRE, 0, 0, true);
        System.out.println("Impot sur le revenu net : " + impot);

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
        calculImpot( parametreCalculImpot.getrNet(), parametreCalculImpot.getSitFam(), parametreCalculImpot.getNbEnf(), parametreCalculImpot.getNbEnfH(), parametreCalculImpot.isParIso() );
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
