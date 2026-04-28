package com.kerware.simulateurreusine.Simulateur;

/**
 * Contrat d'un calculateur d'impôt sur le revenu.
 *
 * Les entrées sont fournies via les setters, puis le calcul est déclenché avec
 * {@link #calculImpotSurRevenuNet()}. Les différentes valeurs intermédiaires
 * et finales sont accessibles via les getters.
 */

public interface ICalculateurImpot {

    /**
     * Définit le revenu net annuel du foyer.
     *
     * @param rn revenu net
     */
    public void setRevenusNet( int rn );

    /**
     * Définit la situation familiale du foyer.
     *
     * @param sf situation familiale
     */
    public void setSituationFamiliale( SituationFamiliale sf );

    /**
     * Définit le nombre d'enfants à charge.
     *
     * @param nbe nombre d'enfants à charge
     */
    public void setNbEnfantsACharge( int nbe );

    /**
     * Définit le nombre d'enfants à charge en situation de handicap.
     *
     * @param nbesh nombre d'enfants en situation de handicap
     */
    public void setNbEnfantsSituationHandicap( int nbesh );

    /**
     * Indique si le déclarant est parent isolé.
     *
     * @param pi true si parent isolé, false sinon
     */
    public void setParentIsole( boolean pi );

    /**
     * Lance le calcul de l'impôt à partir des données renseignées.
     */
    public void calculImpotSurRevenuNet();

    /**
     * Retourne le revenu fiscal de référence.
     *
     * @return revenu fiscal de référence
     */
    public int getRevenuFiscalReference();

    /**
     * Retourne le montant d'abattement appliqué.
     *
     * @return abattement
     */
    public int getAbattement();

    /**
     * Retourne le nombre de parts du foyer fiscal.
     *
     * @return nombre de parts
     */
    public int getNbPartsFoyerFiscal();

    /**
     * Retourne l'impôt calculé avant application de la décote.
     *
     * @return impôt avant décote
     */
    public int getImpotAvantDecote();

    /**
     * Retourne le montant de la décote appliquée.
     *
     * @return décote
     */
    public int getDecote();

    /**
     * Retourne le montant final de l'impôt sur le revenu net.
     *
     * @return impôt sur le revenu net
     */
    public int getImpotSurRevenuNet();

}
