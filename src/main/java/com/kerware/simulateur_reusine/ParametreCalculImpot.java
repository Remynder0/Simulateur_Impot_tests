package com.kerware.simulateur_reusine;

/**
 * Porte l'état intermédiaire du calcul d'impôt.
 *
 * Chaque étape du simulateur lit et écrit ici ses résultats pour permettre
 * de suivre le détail du calcul sans multiplier les objets métiers.
 */
public final class ParametreCalculImpot {

    private int rNet = 0;
    private int nbEnf = 0;
    private int nbEnfH = 0;

    private double rFRef = 0;
    private double rImposable = 0;

    private double abt = 0;

    private double nbPtsTotal = 0;
    private double nbPtsDecl = 0;
    private double nbPts = 0;
    private double decote = 0;

    private double mImpDecl = 0;
    private double mImp = 0;

    private boolean parIso = false;

    private SituationFamiliale sitFam = SituationFamiliale.CELIBATAIRE;

    private int impotRevenuNet = 0;

    public int getrNet() {
        return rNet;
    }

    public void setrNet(int newRNet) {
        rNet = newRNet;
    }

    public int getNbEnf() {
        return nbEnf;
    }

    public void setNbEnf(int newNbEnf) {
        nbEnf = newNbEnf;
    }

    public int getNbEnfH() {
        return nbEnfH;
    }

    public void setNbEnfH(int newNbEnfH) {
        nbEnfH = newNbEnfH;
    }

    public double getrFRef() {
        return rFRef;
    }

    public void setrFRef(double newRFRef) {
        rFRef = newRFRef;
    }

    public double getrImposable() {
        return rImposable;
    }

    public void setrImposable(double newRImposable) {
        rImposable = newRImposable;
    }

    public double getAbt() {
        return abt;
    }

    public void setAbt(double newAbt) {
        abt = newAbt;
    }

    public double getNbPtsDecl() {
        return nbPtsDecl;
    }

    public void setNbPtsDecl(double newNbPtsDecl) {
        nbPtsDecl = newNbPtsDecl;
    }

    public double getNbPts() {
        return nbPts;
    }

    public void setNbPts(double newNbPts) {
        nbPts = newNbPts;
    }

    public double getDecote() {
        return decote;
    }

    public void setDecote(double newDecote) {
        decote = newDecote;
    }

    public double getmImpDecl() {
        return mImpDecl;
    }

    public void setmImpDecl(double newMImpDecl) {
        mImpDecl = newMImpDecl;
    }

    public double getmImp() {
        return mImp;
    }

    public void setmImp(double newMImp) {
        mImp = newMImp;
    }

    public boolean isParIso() {
        return parIso;
    }

    public void setParIso(boolean newParIso) {
        parIso = newParIso;
    }

    public SituationFamiliale getSitFam() {
        return sitFam;
    }

    public void setSitFam(SituationFamiliale newSitFam) {
        sitFam = newSitFam;
    }

    public int getImpotRevenuNet() {
        return impotRevenuNet;
    }

    public void setImpotRevenuNet(int newImpotRevenuNet) {
        impotRevenuNet = newImpotRevenuNet;
    }

    public double getNbPtsTotal() {
        return nbPtsTotal;
    }

    public void setNbPtsTotal(double newNbPtsTotal) {
        nbPtsTotal = newNbPtsTotal;
    }
}
