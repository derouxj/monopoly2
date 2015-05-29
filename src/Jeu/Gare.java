package Jeu;

import java.util.ArrayList;

public class Gare extends CarreauPropriete {

    public Gare(int numero, String nomCarreau) {
        super(numero, nomCarreau, 200);
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
                jProprio.recevoirLoyer(l);
                j.payerLoyer(l);

            }
        }
    }

    public int calculLoyer() { // renommé de calculLoyerGare (pour factoriser)
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
        return 25;
    }

}
