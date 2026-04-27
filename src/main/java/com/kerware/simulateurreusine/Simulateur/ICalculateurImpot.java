package com.kerware.simulateurreusine.Simulateur;

/**
 * TODO PERSONNE 1
 *  - Créer une classe (ex: CalculateurImpot) qui implémente cette interface.
 *  - Implémenter la saisie des entrées via les setters.
 *  - Implémenter calculImpotSurRevenuNet().
 *  - Implémenter les 6 getters de résultat.
 *
 * TODO PERSONNE 2
 *  - Préparer les jeux de données de tests pour chaque getter.
 *  - Vérifier les résultats attendus sur des cas nominaux et limites.
 */

public interface ICalculateurImpot {

    public void setRevenusNet( int rn );
    public void setSituationFamiliale( SituationFamiliale sf );
    public void setNbEnfantsACharge( int nbe );
    public void setNbEnfantsSituationHandicap( int nbesh );
    public void setParentIsole( boolean pi );

    public void calculImpotSurRevenuNet();

    public int getRevenuFiscalReference();
    public int getAbattement();
    public int getNbPartsFoyerFiscal();
    public int getImpotAvantDecote();
    public int getDecote();
    public int getImpotSurRevenuNet();

}
