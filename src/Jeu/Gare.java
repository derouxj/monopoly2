package Jeu;

import java.util.ArrayList;

public class Gare extends CarreauPropriete implements java.io.Serializable{
    private final int PRIXLOYER=25;

    public Gare(int numero, String nomCarreau,Monopoly monopoly) {
        super(numero, nomCarreau, 200,monopoly);
        //    numéro de la case, son nom, le prix de l'achat (toujours 200)
    }

    @Override
    public void action(Joueur j) {
        Joueur jProprio = this.getProprietaire();
        if (jProprio == null) {
            super.achatPropriete(j);
        } else {
            if (jProprio != j) {
                int l = this.calculLoyer();
                jProprio.recevoir(l);
                j.payer(l);

            }
        }
    }

    /**
     *Calcul le loyer en fonction du nombre de gare du propriétaire de cette dernière
     * @return int correspondant au loyer
     */
    @Override
    public int calculLoyer() {
        int nbGares = 0;
        int loyer = 0;
        ArrayList<Gare> collectGares = super.getProprietaire().getGares();
        for (Gare gares : collectGares) {
            nbGares++;
        }
        loyer = nbGares * getPrixLoyer();

        return loyer;

    }

    public int getPrixLoyer() {
        return PRIXLOYER;
    }

}
