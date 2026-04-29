package com.kerware.simulateur_reusine;

/**
 * Porte l'état intermédiaire du calcul d'impôt.
 *
 * Chaque étape du simulateur lit et écrit ici ses résultats pour permettre
 * de suivre le détail du calcul sans multiplier les objets métiers.
 */
public class ParametreCalculImpot {

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

    public void setrNet(int rNet) {
        this.rNet = rNet;
    }

    public int getNbEnf() {
        return nbEnf;
    }

    public void setNbEnf(int nbEnf) {
        this.nbEnf = nbEnf;
    }

    public int getNbEnfH() {
        return nbEnfH;
    }

    public void setNbEnfH(int nbEnfH) {
        this.nbEnfH = nbEnfH;
    }

    public double getrFRef() {
        return rFRef;
    }

    public void setrFRef(double rFRef) {
        this.rFRef = rFRef;
    }

    public double getrImposable() {
        return rImposable;
    }

    public void setrImposable(double rImposable) {
        this.rImposable = rImposable;
    }

    public double getAbt() {
        return abt;
    }

    public void setAbt(double abt) {
        this.abt = abt;
    }

    public double getNbPtsDecl() {
        return nbPtsDecl;
    }

    public void setNbPtsDecl(double nbPtsDecl) {
        this.nbPtsDecl = nbPtsDecl;
    }

    public double getNbPts() {
        return nbPts;
    }

    public void setNbPts(double nbPts) {
        this.nbPts = nbPts;
    }

    public double getDecote() {
        return decote;
    }

    public void setDecote(double decote) {
        this.decote = decote;
    }

    public double getmImpDecl() {
        return mImpDecl;
    }

    public void setmImpDecl(double mImpDecl) {
        this.mImpDecl = mImpDecl;
    }

    public double getmImp() {
        return mImp;
    }

    public void setmImp(double mImp) {
        this.mImp = mImp;
    }

    public boolean isParIso() {
        return parIso;
    }

    public void setParIso(boolean parIso) {
        this.parIso = parIso;
    }

    public SituationFamiliale getSitFam() {
        return sitFam;
    }

    public void setSitFam(SituationFamiliale sitFam) {
        this.sitFam = sitFam;
    }

    public int getImpotRevenuNet() {
        return impotRevenuNet;
    }

    public void setImpotRevenuNet(int impotRevenuNet) {
        this.impotRevenuNet = impotRevenuNet;
    }

    public double getNbPtsTotal() {
        return nbPtsTotal;
    }

    public void setNbPtsTotal(double nbPtsTotal) {
        this.nbPtsTotal = nbPtsTotal;
    }
}
