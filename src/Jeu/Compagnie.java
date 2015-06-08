package Jeu;

public class Compagnie extends CarreauPropriete {

    public Compagnie(int numero, String nomCarreau, int prixAchat,Monopoly monopoly) {
        super(numero, nomCarreau, prixAchat,monopoly);
    }

    @Override
    public void action(Joueur j) {
        Joueur jProprio = this.getProprietaire();
        if (jProprio == null) {
            super.achatPropriete(j);
        } else {
            if (jProprio != j) {
                int l = this.calculLoyer();
                j.payer(l);
                jProprio.recevoir(l);
            }
        }
    }

    /**
     *Calcul le loyer en fonction des dés
     * @return int correspondant au loyer
     */
    @Override
    public int calculLoyer() {
        if (super.getProprietaire().getCompagnies().size() == 2) {
            return 10 * (super.getMonopoly().getD1() + super.getMonopoly().getD2());
        } else {
            return 4 * (super.getMonopoly().getD1() + super.getMonopoly().getD2());
        }
    }
}
